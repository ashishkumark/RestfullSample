package com.ashish.webservices;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/UserService")
public class UserService {

   UserDao userDao = new UserDao();

   @GET
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   public List<User> getUsers(){
      return userDao.getAllUsers();
   }

   @GET
   @Path("/userstext")
   @Produces(MediaType.TEXT_XML)
   public List<User> getUsersText(){
      return userDao.getAllUsers();
   }
   
   @POST
   @Path("/add/{r}/{u}")
   @Produces(MediaType.APPLICATION_XML)
   public List<User> addUser(@PathParam("r") String role, @PathParam("u") String user){
      return userDao.addUser(role, user);
   }
   
   @DELETE
   @Path("/remove/{r}/{u}")
   @Produces(MediaType.APPLICATION_XML)
   public List<User> removeUser(@PathParam("r") String role, @PathParam("u") String user){
      return userDao.removeUser(role, user);
   }
  
}