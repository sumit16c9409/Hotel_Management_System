package com.src.service;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.src.bean.BookingDetails;
import com.src.bean.InterfaceBean;
import com.src.dao.GenericDao;

@Path("/UserService") 
public class UserService {  
   GenericDao genericDao = new GenericDao();  
   final String senderEmailId = "x17156220@student.ncirl.ie";
   final String senderPassword = "Mahalaxmi@1";

   @GET 
   @Path("{users}/{city}")
   @Produces({MediaType.APPLICATION_XML}) 
   public List<InterfaceBean> getInterfaceXML(@PathParam("city") String city){
      return genericDao.getAllInformation(city); 
   }
   
   @GET 
   @Path("{booking}")
   @Produces({MediaType.APPLICATION_XML}) 
   public List<BookingDetails> getBookingInterfaceXML(){
      return genericDao.getBookinDetails(); 
   }
}