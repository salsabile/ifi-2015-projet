package com.ifi.authentication;

import com.ifi.authentication.model.Authentication;
import com.ifi.authentication.model.User;
import com.ifi.authentication.repository.SessionRepository;
import com.ifi.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> startSession(@RequestBody Authentication authentication, HttpServletResponse response) throws Exception{
    	
        User user = userRepository.findUserByUserNameAndPassword(authentication.getUserName(), authentication.getPassword());

        if( user == null ){
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }

        ResponseEntity<Void> responseEntity = new ResponseEntity<Void>(HttpStatus.OK);
        response.setHeader("xSessionId",  sessionRepository.getNewSession() );
        return responseEntity;
    }

    @RequestMapping(value="{sessionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> endSession( @PathVariable String sessionId ){
        sessionRepository.endSession(sessionId) ;
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
