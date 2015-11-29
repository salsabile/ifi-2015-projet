package com.ifi.authentication;

import com.ifi.authentication.model.User;
import com.ifi.authentication.repository.SessionRepository;
import com.ifi.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/employee", produces = "application/json")
public class EmployeeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<User>> listEployees(HttpServletRequest request){
        String sessionId = request.getHeader("xSessionId");
        if( ! sessionRepository.isSessionActive(sessionId)){
            return new ResponseEntity<Iterable<User>>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Iterable<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{userName}")
    @ResponseBody
    public ResponseEntity<User> employee(@PathVariable String userName, HttpServletRequest request){
        String sessionId = request.getHeader("xSessionId");
        if( ! sessionRepository.isSessionActive(sessionId)){
            return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<User>(userRepository.findOne(userName), HttpStatus.OK);
    }

}
