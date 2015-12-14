package ifi2015.ifi_2015_projet;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserprofilController {
		
	@RequestMapping(value = "/userprofil", method=RequestMethod.GET)
	public String userprofil(@ModelAttribute Userprofil userprofil, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders requestHttpHeaderNa = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntityNa = new HttpEntity<String>("", requestHttpHeaderNa);
		HttpEntity<String> responseHttpEntityNa = restTemplate.exchange("http://localhost:9292/userprofil/"+UserController.username, HttpMethod.GET, requestHttpEntityNa, String.class);
		
		String userprofils = responseHttpEntityNa.getBody();

		String[] infosprofil = userprofils.split("\"");
		
		userprofil.setLogin(infosprofil[3]);
		userprofil.setEmail(infosprofil[7]);
		userprofil.setFacebookid(infosprofil[11]);
		userprofil.setTwitterid(infosprofil[15]);
		userprofil.setLinkedinid(infosprofil[19]);
		userprofil.setCompetence(infosprofil[23]);
		userprofil.setProjet(infosprofil[27]);
	
		model.addAttribute("userprofil",userprofil);
		
		return "userprofilform";
	}

	@RequestMapping(value = "/profilupdate", method=RequestMethod.POST)
	public String updateProfil(@ModelAttribute Userprofil userprofil, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHttpHeader = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("", requestHttpHeader);

		userprofil.hydrater();
		restTemplate.exchange("http://localhost:9292/userprofil/update/"+userprofil.getLogin()+"/"+userprofil.getEmail()+"/"+userprofil.getFacebookid()+"/"+userprofil.getTwitterid()+"/"+userprofil.getLinkedinid()+"/"+userprofil.getCompetence()+"/"+userprofil.getProjet(), HttpMethod.POST, requestHttpEntity, String.class);
		
		model.addAttribute("name",UserController.name);
		model.addAttribute("message", new Message());
		
		MessageController.messages = new ArrayList<Message>();
		MessageController.messages = MessageController.afficherMessage(MessageController.messages, model);
		MessageController.afficherHashtag(MessageController.messages);
		model.addAttribute("messages", MessageController.messages);
		return "index";
	}
	
	@RequestMapping(value = "/contact/{contact}", method=RequestMethod.GET)
	public String friendprofil(@PathVariable String contact, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHttpHeader = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("", requestHttpHeader);
		HttpEntity<String> responseHttpEntity = restTemplate.exchange("http://localhost:9292/userprofil/"+contact, HttpMethod.GET, requestHttpEntity, String.class);
		
		Userprofil userprofil = new Userprofil();
		String userprofils = responseHttpEntity.getBody();
		
		String[] infosprofil = userprofils.split("\"");
		userprofil.setLogin(infosprofil[3]);
		userprofil.setEmail(infosprofil[7]);
		userprofil.setFacebookid(infosprofil[11]);
		userprofil.setTwitterid(infosprofil[15]);
		userprofil.setLinkedinid(infosprofil[19]);
		userprofil.setCompetence(infosprofil[23]);
		userprofil.setProjet(infosprofil[27]);
	
		model.addAttribute("userprofil",userprofil);
		MessageController.messages = new ArrayList<Message>();
		MessageController.messages = MessageController.afficherMessageContact(MessageController.messages, contact, model);
		MessageController.afficherHashtag(MessageController.messages);
		model.addAttribute("messages", MessageController.messages);
		model.addAttribute("name",UserController.name);
		return "profil";
	}
	
}