package ifi2015.ifi_2015_projet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentController {

		
	@RequestMapping(value = "/document", method=RequestMethod.POST)
	public String sendDocument(@RequestParam("file") MultipartFile file, Model model) throws IOException {
		
		String filename = file.getOriginalFilename();
		String name = filename.split("\\.")[0];
		String extension = filename.split("\\.")[1];
		String messagePieceJointe = "Je vous partage ce fichier <a href=/document/"+filename+">"+filename+"</a> #File";
		
		File fileTemporaire = File.createTempFile(name, extension);
		FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(fileTemporaire));
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file", new FileSystemResource(fileTemporaire));
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders requestHttpHeader = new HttpHeaders();
		requestHttpHeader.setContentType(MediaType.MULTIPART_FORM_DATA);
		requestHttpHeader.setContentDispositionFormData("file", filename);
		
		HttpEntity<Object> requestHttpEntity = new HttpEntity<Object>(map, requestHttpHeader);
		restTemplate.exchange("http://localhost:9191/documents/"+filename, HttpMethod.POST, requestHttpEntity, String.class);
		
		restTemplate = new RestTemplate();
		requestHttpHeader = new HttpHeaders();
		requestHttpHeader.setContentType(MediaType.APPLICATION_JSON);
		Message message = new Message(messagePieceJointe, UserController.name);
		
		HttpEntity<String> requestHttpEntityMessage = new HttpEntity<String>("{\"content\":\""+message.getContent()+"\", \"login\":\""+UserController.username+"\" }", requestHttpHeader);
		restTemplate.exchange("http://localhost:9393/messages/message", HttpMethod.POST, requestHttpEntityMessage, String.class);
		
		MessageController.messages = new ArrayList<Message>();
		MessageController.messages = MessageController.afficherMessage(MessageController.messages, model);
		MessageController.afficherHashtag(MessageController.messages);
		model.addAttribute("message", new Message());
		model.addAttribute("name",UserController.name);
		model.addAttribute("messages", MessageController.messages);
		
		return "index";
	}
}