package com.xin.apidemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xin.apidemo.database.DataBaseClass;
import com.xin.apidemo.model.Message;

public class MessageService {
   
//	public List<Message> getAllMessages(){
//		Message m1 = new Message(11, "Hello There", "Anne");
//		Message m2 = new Message(12, "Hello Anne", "Bob");
//		List<Message> mList = new ArrayList<>();
//		mList.add(m1);
//		mList.add(m2);
//		return mList;
//	}
	
	private Map<Long, Message> messages = DataBaseClass.getMessages();
	
	public MessageService(){
	messages.put(1L, new Message(1, "Hello Anna", "Anna"));
	messages.put(2L, new Message(1, "Hello Bob", "Bob"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values()); 
	}
	
	public Message getMessage(Long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message msg){
		msg.setId(messages.size() + 1);
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message updateMessage(Message msg){
		if(msg.getId() <= 0){
			return null;
		}
		else{
			messages.put(msg.getId(), msg);
			return msg;
		}		
	}
	
	public Message deleteMessage(long id){
		return messages.remove(id);
	}
}
