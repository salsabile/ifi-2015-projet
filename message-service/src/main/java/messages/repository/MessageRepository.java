package messages.repository;

import messages.model.Message;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, String>{
	public ArrayList<Message> findMessageByLogin(String login);
}
