package com.xin.apidemo.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.xin.apidemo.model.Message;
import com.xin.apidemo.service.MessageService;

@Path("/messages")
//we can use this instead of using each on top of each method
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
   
	MessageService ms = new MessageService();
   
   @GET
//   @Produces(MediaType.APPLICATION_XML)
   @Produces(MediaType.APPLICATION_JSON)
   public List<Message> getAllMessage(){
	   return ms.getAllMessages();
   }
   
   @GET
   @Path("/{messageId}")
   @Produces(MediaType.APPLICATION_JSON)
   public Message getMessage(@PathParam("messageId")long messageId){
	   // jersey will convert messageId from String to long automatically
	   return ms.getMessage(messageId);
   }
   
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Message addMessage(Message msg){
	   return ms.addMessage(msg);
   }
   
   @PUT
   @Path("/{messageId}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Message updateMessage(@PathParam("messageId")long id, Message msg){
	   // jersey will convert messageId from String to long automatically
	   msg.setId(id);
	   return ms.updateMessage(msg);
   }
   
   @DELETE
   @Path("/{messageId}")
   @Produces(MediaType.APPLICATION_JSON)
   public void deleteMessage(@PathParam("messageId")long id){
	   // jersey will convert messageId from String to long automatically
	   ms.deleteMessage(id);
   }
}
