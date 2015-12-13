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
		
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("{\"content\":\""+message.getContent()+"\" }", requestHttpHeader);
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
		String[] messageContenu = reponse.split("}");
		
		for(int i=0;i<messageContenu.length-1;i++){
			String[] contenu = messageContenu[i].split(":");
			String content = contenu[1].substring(1, contenu[1].length()-1);
			Message message= new Message(content);
			messages.add(message);
		}
		
		return messages;
	}
}