package userprofil;

import userprofil.model.Userprofil;
import userprofil.repository.UserprofilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/userprofil")
public class UserprofilController {
	
	@Autowired
	private UserprofilRepository userprofilRepository;
    
	@RequestMapping(method = RequestMethod.GET, value = "{login}")
	@ResponseBody
	public ResponseEntity<Userprofil> profil(@PathVariable String login, HttpServletRequest request){
		return new ResponseEntity<Userprofil>(userprofilRepository.findOne(login), HttpStatus.OK);
	}

	@RequestMapping(method= RequestMethod.POST, value="/update/{login}/{email}/{facebook}/{twitter}/{linkedin}/{competence}/{projet}")
    @ResponseBody
    public void saveProfil(@PathVariable String login, @PathVariable String email, @PathVariable String facebook, @PathVariable String twitter, @PathVariable String linkedin, @PathVariable String competence, @PathVariable String projet, HttpServletRequest request) {
		userprofilRepository.save(new Userprofil(login, email, facebook, twitter, linkedin, competence, projet));
    }
}