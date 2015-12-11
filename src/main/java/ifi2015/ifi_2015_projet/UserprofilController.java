package ifi2015.ifi_2015_projet;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserprofilController {
	

	@RequestMapping(value = "/userprofil", method=RequestMethod.GET)
	public String userprofilForm(Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders requestHttpHeaderNa = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntityNa = new HttpEntity<String>("", requestHttpHeaderNa);
		HttpEntity<String> responseHttpEntityNa = restTemplate.exchange("http://localhost:9292/userprofil/a", HttpMethod.GET, requestHttpEntityNa, String.class);
		
		String userprofil = responseHttpEntityNa.getBody();
		
		model.addAttribute("userprofil",userprofil);
		
		return "userprofil";
	}

}