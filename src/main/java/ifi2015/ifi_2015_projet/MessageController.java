package ifi2015.ifi_2015_projet;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		messages = afficherMessage(messages, model);
		afficherHashtag(messages);
		model.addAttribute("messages", messages);
		
		return "index";
	}
	
	@RequestMapping(value = "/hashtag/{hashtag}", method=RequestMethod.GET)
	public String messageHashtag(@PathVariable String hashtag, Model model) {
		
		messages = new ArrayList<Message>();
		messages = MessageController.afficherMessage(messages, model);
		
		afficherHashtag(messages);
		ArrayList<Message> messagesHashtag = new ArrayList<Message>();
		
		for (Message message : messages) {
			if(message.getHashtags().contains(hashtag)) {
				messagesHashtag.add(message);
			}
		}
		
		model.addAttribute("message", new Message());
		model.addAttribute("hashtag", hashtag);
		model.addAttribute("messages", messagesHashtag);
		
		return "hashtag";
	}
	
	public static ArrayList<Message> afficherMessage(ArrayList<Message> messages, Model model) {
		
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
	
	public static ArrayList<Message> afficherMessageContact(ArrayList<Message> messages, String contact, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHttpHeader = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("", requestHttpHeader);
		HttpEntity<String> responseHttpEntity = restTemplate.exchange("http://localhost:9393/messages/" + contact, HttpMethod.GET, requestHttpEntity, String.class);
	
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
	
	public static void afficherHashtag(ArrayList<Message> messages) {
		
		for (Message message : messages) {
			ArrayList<String> listeHashtag = new ArrayList<String>();
			String[] listeMot = message.getContent().split(" ");
			for (String mot : listeMot) {
				if (mot.charAt(0) == '#') {
					listeHashtag.add(mot.substring(1));
				}
			}
			message.setHashtags(listeHashtag);
		}
	}
	
	
}