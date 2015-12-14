package messages;

import messages.model.Message;
import messages.repository.MessageRepository;
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
@RequestMapping("/messages")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;
   
	@RequestMapping(value="/message", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveMessage(@RequestBody Message message, HttpServletResponse response) {
		messageRepository.save(new Message(message.getContent(), message.getLogin()));
    }
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<Message>> message(HttpServletRequest request){
		return new ResponseEntity<Iterable<Message>>(messageRepository.findAll(), HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "{contact}")
    @ResponseBody
    public ResponseEntity<Iterable<Message>> messageContact(@PathVariable String contact, HttpServletRequest request){
		return new ResponseEntity<Iterable<Message>>(messageRepository.findMessageByLogin(contact), HttpStatus.OK);
	}
}