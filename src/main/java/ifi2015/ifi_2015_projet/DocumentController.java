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
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		HttpHeaders requestHttpHeader = new HttpHeaders();
		requestHttpHeader.setContentType(MediaType.MULTIPART_FORM_DATA);
		requestHttpHeader.setContentDispositionFormData("file", filename);
		
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		File tempFile = File.createTempFile("xyz", "");
		FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(tempFile));
		map.add("file", new FileSystemResource(tempFile));
		
		HttpEntity<Object> requestHttpEntity = new HttpEntity<Object>(map, requestHttpHeader);
		//HttpEntity<String> requestHttpEntity = new HttpEntity<String>("", requestHttpHeader);
		
		restTemplate.exchange("http://localhost:9191/documents/"+filename, HttpMethod.POST, requestHttpEntity, String.class);
		
		MessageController.messages = new ArrayList<Message>();
		MessageController.messages = MessageController.afficherMessage(MessageController.messages, model);
		MessageController.afficherHashtag(MessageController.messages);
		model.addAttribute("message", new Message());
		model.addAttribute("name",UserController.name);
		model.addAttribute("messages", MessageController.messages);
		
		return "index";
	}
}