package ifi2015.ifi_2015_projet;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	public static String xSessionId;
	public static String username;
	public static String name;
	
	@RequestMapping(value = "/userlogin", method=RequestMethod.POST)
	public String userSubmit(@ModelAttribute User user, Model model){
		
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders requestHttpHeader = new HttpHeaders();
    	HttpHeaders responseHttpHeader = new HttpHeaders();
    	requestHttpHeader.setContentType(MediaType.APPLICATION_JSON);
    	
    	HttpEntity<String> requestHttpEntity = new HttpEntity<String>("{\"userName\":\""+user.getLogin()+"\", \"password\":\""+user.getPass()+"\" }", requestHttpHeader);
    	HttpEntity<String> responseHttpEntity = restTemplate.exchange("http://localhost:9090/session", HttpMethod.POST, requestHttpEntity, String.class);
    	
    	responseHttpHeader = responseHttpEntity.getHeaders();
    	xSessionId = responseHttpHeader.get("xSessionId").get(0);
    	
    	model.addAttribute("xsessionId", xSessionId);
    	user.setSession(xSessionId);

		HttpHeaders requestHttpHeaderNa = new HttpHeaders();
		requestHttpHeaderNa.set("xSessionId", xSessionId);
		
		HttpEntity<String> requestHttpEntityNa = new HttpEntity<String>("", requestHttpHeaderNa);
		HttpEntity<String> responseHttpEntityNa = restTemplate.exchange("http://localhost:9090/employee/"+user.getLogin(), HttpMethod.GET, requestHttpEntityNa, String.class);
		
		name = responseHttpEntityNa.getBody();
		String[] nomprenom = name.split("\"");
		
		String prenom = nomprenom[7];
		String nom = nomprenom[11];
		
		name=" "+prenom+" "+nom;
		username = user.getLogin();
		
		MessageController.messages = new ArrayList<Message>();
		MessageController.messages = MessageController.afficherMessage(MessageController.messages, model);
		
		MessageController.afficherHashtag(MessageController.messages);
		
		model.addAttribute("user", user);
		model.addAttribute("name",name);
		model.addAttribute("message", new Message());
		model.addAttribute("messages", MessageController.messages);
		
		return "index";
    }

	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("name",name);
		model.addAttribute("message", new Message());
		
		MessageController.messages = new ArrayList<Message>();
		MessageController.messages = MessageController.afficherMessage(MessageController.messages, model);
		MessageController.afficherHashtag(MessageController.messages);
		model.addAttribute("messages", MessageController.messages);
		return "index";
	}
	
	@RequestMapping(value = "/deconnexion", method=RequestMethod.GET)
	public String userDeconnexion(Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:9090/session/"+xSessionId);
		xSessionId = null;

		model.addAttribute("user", new User());
		model.addAttribute("message", new Message());
		return "user";
	}
	
	@RequestMapping(value = "/userlogin", method=RequestMethod.GET)
	public String userForm(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
	
	@ResponseStatus(value=HttpStatus.FORBIDDEN, reason = "Pseudo ou mot de pass incorrect")
    @ExceptionHandler(SecurityException.class)
	public ModelAndView workflowExceptionCaught(SecurityException ex) {
    	return new ModelAndView("error", "errorCode", HttpStatus.FORBIDDEN.value());
	}
}
