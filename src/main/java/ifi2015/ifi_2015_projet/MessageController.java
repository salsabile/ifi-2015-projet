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
		
	@RequestMapping(value = "/messageEnvoyer", method=RequestMethod.POST)
	public String sendMessage(@ModelAttribute Message message, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders requestHttpHeader = new HttpHeaders();
		requestHttpHeader.setContentType(MediaType.APPLICATION_JSON);
		
		message.setHashtag("hashtag");
		
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("{\"content\":\""+message.getContent()+"\", \"hashtag\":\""+message.getHashtag()+"\" }", requestHttpHeader);
		restTemplate.exchange("http://localhost:9393/messages/message", HttpMethod.POST, requestHttpEntity, String.class);
		
		model.addAttribute("message", new Message());
		model.addAttribute("name",UserController.name);
		
		return "index";
	}
	
	@RequestMapping(value = "/message", method=RequestMethod.GET)
	public String userForm(ArrayList<Message> messages, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHttpHeader = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntity = new HttpEntity<String>("", requestHttpHeader);
		HttpEntity<String> responseHttpEntity = restTemplate.exchange("http://localhost:9393/messages", HttpMethod.GET, requestHttpEntity, String.class);

		String name = responseHttpEntity.getBody();
		String[] messageContenuHastag = name.split("}");
		String [] contenuMessage;
		String [] c;
		String [] h;
		
		for(int i=0;i<messageContenuHastag.length-1;i++){
			Message message= new Message();
			String m = messageContenuHastag[i].substring(1);
			contenuMessage = m.split(",");
			c = contenuMessage[0].split(":");
			h = contenuMessage[1].split(":");
			message.setContent(c[1].split("\"")[1]);
			message.setHashtag(h[1].split("\"")[1]);
			messages.add(message);
		}
		
		model.addAttribute("messages",messages);
		
		return "message";
	}
	
}