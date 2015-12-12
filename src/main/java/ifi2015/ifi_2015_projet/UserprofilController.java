package ifi2015.ifi_2015_projet;

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
public class UserprofilController {
	
	// Le get pour avoir les informations en lecture
	
	@RequestMapping(value = "/userprofil", method=RequestMethod.GET)
	public String userprofilForm(@ModelAttribute Userprofil userprofil, Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders requestHttpHeaderNa = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntityNa = new HttpEntity<String>("", requestHttpHeaderNa);
		HttpEntity<String> responseHttpEntityNa = restTemplate.exchange("http://localhost:9292/userprofil/"+UserController.username, HttpMethod.GET, requestHttpEntityNa, String.class);
		
		String userprofils = responseHttpEntityNa.getBody();
		
		
		// Infos du profil 
		//{"login":"a","email":"email","facebookid":"facebook",
		//"twitterid":"twitter","linkedinid":"linkedinid"} !
		
		String[] infosprofil = userprofils.split("\"");
		
		/*String login = infosprofil[3];
		String email = infosprofil[7];
		String facebookid = infosprofil[11];
		String twitterid = infosprofil[15];
		String linkedinid = infosprofil[19];
		*/
		
		userprofil.setLogin(infosprofil[3]);
		userprofil.setEmail(infosprofil[7]);
		userprofil.setFacebookid(infosprofil[11]);
		userprofil.setTwitterid(infosprofil[15]);
		userprofil.setLinkedinid(infosprofil[19]);
		
		/*model.addAttribute("login",login);
		model.addAttribute("email",email);
		model.addAttribute("facebookid",facebookid);
		model.addAttribute("twitterid",twitterid);
		model.addAttribute("linkedinid",linkedinid);*/
		
		model.addAttribute("userprofil",userprofil);
		
		return "userprofilform";
		
		//return "userprofilform";
	}
	
	// Un post pour le formulaire afin de mette à jour les informations
	
	/*@RequestMapping(value = "/userprofil", method=RequestMethod.POST)
	public String userprofilForm(Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders requestHttpHeaderNa = new HttpHeaders();
		
		HttpEntity<String> requestHttpEntityNa = new HttpEntity<String>("", requestHttpHeaderNa);
		HttpEntity<String> responseHttpEntityNa = restTemplate.exchange("http://localhost:9292/userprofil/a", HttpMethod.GET, requestHttpEntityNa, String.class);
		
		String userprofil = responseHttpEntityNa.getBody();
		
		model.addAttribute("userprofil",userprofil);
		
		return "userprofil";
	}*/
	
	

}