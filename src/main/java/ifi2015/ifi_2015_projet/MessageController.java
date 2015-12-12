package ifi2015.ifi_2015_projet;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class MessageController {
	
	/*@RequestMapping(value = "/userlogin", method=RequestMethod.POST)
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
    	    	
		model.addAttribute("user", user);
		
		HttpHeaders requestHttpHeaderNa = new HttpHeaders();
		requestHttpHeaderNa.set("xSessionId", xSessionId);
		
		HttpEntity<String> requestHttpEntityNa = new HttpEntity<String>("", requestHttpHeaderNa);
		HttpEntity<String> responseHttpEntityNa = restTemplate.exchange("http://localhost:9090/employee/"+user.getLogin(), HttpMethod.GET, requestHttpEntityNa, String.class);
		
		String name = responseHttpEntityNa.getBody();
		String[] nomprenom = name.split("\"");

		name=" "+nomprenom[7]+" "+nomprenom[11];
		model.addAttribute("name",name);
		
		return "index";
    }*/
	
	@RequestMapping(value = "/messageEnvoyer", method=RequestMethod.GET)
	public String sendMessage(Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHttpHeaderNa = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntityNa = new HttpEntity<String>("", requestHttpHeaderNa);
		restTemplate.exchange("http://localhost:9393/messages/message", HttpMethod.GET, requestHttpEntityNa, String.class);
		
		model.addAttribute("name",UserController.name);
		return "index";
	}
	
	@RequestMapping(value = "/message", method=RequestMethod.POST)
	public String userForm(@ModelAttribute ArrayList<Message> messages,Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		//ArrayList<Message> messages = new ArrayList<Message>();
		HttpHeaders requestHttpHeaderNa = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntityNa = new HttpEntity<String>("", requestHttpHeaderNa);
		HttpEntity<String> responseHttpEntityNa = restTemplate.exchange("http://localhost:9393/messages", HttpMethod.GET, requestHttpEntityNa, String.class);
		
		String name = responseHttpEntityNa.getBody();
		String[] messageContenuHastag = name.split("}");
		String [] contenuMessage;
		String [] c;
		String [] h;
		//}}}]
		/*
		 [{"content":"bonjour","hashtag":"test"
		,{"content":"Dark Maul","hashtag":"StarWars"
		,{"content":"really","hashtag":"nigger"*/
		
		for(int i=0;i<messageContenuHastag.length-1;i++){
			Message message= new Message();
			String m = messageContenuHastag[i].substring(1);
			contenuMessage = m.split(",");
			c = contenuMessage[0].split(":");
			h = contenuMessage[1].split(":");
			message.setContent(c[1].split("\"")[1]);
			message.setHashtag(h[1].split("\"")[1]);
			messages.add(message);
			
			//model.addAttribute("name",messageContenuHastag[i]);
		}
		//model.addAttribute("name",name);
		model.addAttribute("messages",messages);
		
		return "message";
	}
	
}