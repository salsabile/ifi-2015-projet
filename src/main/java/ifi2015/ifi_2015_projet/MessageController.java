package ifi2015.ifi_2015_projet;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class MessageController {
	
	public static ArrayList<Message> messages = new ArrayList<Message>();
		
	@RequestMapping(value = "/messageEnvoyer", method=RequestMethod.POST)
	public String sendMessage(@ModelAttribute Message message, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders requestHttpHeader = new HttpHeaders();
		requestHttpHeader.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("{\"content\":\""+message.getContent()+"\", \"login\":\""+UserController.username+"\" }", requestHttpHeader);
		restTemplate.exchange("http://localhost:9393/messages/message", HttpMethod.POST, requestHttpEntity, String.class);
		
		model.addAttribute("message", new Message());
		model.addAttribute("name",UserController.name);
		
		messages = new ArrayList<Message>();
		messages = userForm(messages, model);
		model.addAttribute("messages", messages);
		
		return "index";
	}
	
	public static ArrayList<Message> userForm(ArrayList<Message> messages, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHttpHeader = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("", requestHttpHeader);
		HttpEntity<String> responseHttpEntity = restTemplate.exchange("http://localhost:9393/messages", HttpMethod.GET, requestHttpEntity, String.class);
	
		String reponse = responseHttpEntity.getBody();
		String[] messageContenuLogin = reponse.split("}");
		
		for(int i=0;i<messageContenuLogin.length-1;i++){
			Message message = new Message();
			String messageContenu = messageContenuLogin[i].substring(1);
			String[] contenuMessage = messageContenu.split(",");
			String[] contenu = contenuMessage[0].split(":");
			String[] auteur = contenuMessage[1].split(":");
			message.setContent(contenu[1].split("\"")[1]);
			message.setLogin(auteur[1].split("\"")[1]);
			messages.add(message);
		}
		
		return messages;
	}
}