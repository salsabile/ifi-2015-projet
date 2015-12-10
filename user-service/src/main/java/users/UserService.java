package users;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserService {
	
	public static String xSessionId = null;
	public static String xName = null;


	@RequestMapping(value = "/userlogin", method=RequestMethod.POST)
    public void postLoginUser(@RequestParam String userLogin, @RequestParam String userPassword){
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	// HTTP request and response headers
    	HttpHeaders requestHttpHeader = new HttpHeaders();
    	HttpHeaders responseHttpHeader = new HttpHeaders();
    	
    	requestHttpHeader.setContentType(MediaType.APPLICATION_JSON);
    	
    	// requete avec le nom et mot de passe
    	HttpEntity<String> requestHttpEntity = new HttpEntity<String>("{\"userName\":\""+userLogin+"\", \"password\":\""+userPassword+"\" }", requestHttpHeader);
    	
    	// appel à session pour avoir un xSessionId en fonction du nom et mot de passe d'avant
    	HttpEntity<String> responseHttpEntity = restTemplate.exchange("http://localhost:9090/session", HttpMethod.POST, requestHttpEntity, String.class);
    	
    	responseHttpHeader = responseHttpEntity.getHeaders();
    	
    	this.xSessionId = responseHttpHeader.get("xSessionId").get(0);
        
    }

	@RequestMapping(value = "/{userLogin}", method=RequestMethod.GET)
	public String getUser(@PathVariable String userLogin) {
		String test = this.xSessionId;
		return test;
	}
	
	@RequestMapping(value = "/userliste", method=RequestMethod.GET)
    public String getLoginUser(@PathVariable String userLogin){
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	// HTTP request and response headers
    	HttpHeaders requestHttpHeader = new HttpHeaders();
    	HttpHeaders responseHttpHeader = new HttpHeaders();
    	
    	requestHttpHeader.setContentType(MediaType.APPLICATION_JSON);
    	requestHttpHeader.set
    	
    	HttpEntity<String> requestHttpEntity = new HttpEntity<String>("{\"xSessionId\":"+this.xSessionId, requestHttpHeader);
    	
    	HttpEntity<String> responseHttpEntity = restTemplate.exchange("http://localhost:9090/employee", HttpMethod.GET, requestHttpEntity, String.class);
    	
    	responseHttpHeader = responseHttpEntity.getHeaders();
    	
    	this.xName = responseHttpHeader.get("userName").get(0);
    	
    	return xName;
        
    }

}
