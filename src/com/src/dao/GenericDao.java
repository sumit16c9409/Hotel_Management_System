package com.src.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.src.bean.BookingDetails;
import com.src.bean.InterfaceBean;
import com.src.dao.ConnectionDatabase;

public class GenericDao {
	
	private static PreparedStatement statement;
	private static ResultSet resultSet;
	public static Connection connect;
	
	/**
	 * Get Information of hotels by providing city name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InterfaceBean> getAllInformation(String destination){ 
          ConnectionDatabase cDB = new ConnectionDatabase();
	      List<InterfaceBean> parameterList = new ArrayList<InterfaceBean>(); 
	      try { 
	    	    connect = cDB.connectToDB();
	      	    String sql = "SELECT id, name, address_line, city, rating, price FROM hotel_master WHERE city='"+destination+"'";
	  			statement = connect.prepareStatement(sql); 
	  			resultSet = statement.executeQuery(sql);
	  				while(resultSet.next()){
	  					ArrayList<Integer> list = new ArrayList<Integer>();
	  					list.add(resultSet.getInt("id"));
	  					InterfaceBean instance = new InterfaceBean(
	  							resultSet.getInt("id"), 
	  							resultSet.getString("name"),
	  							resultSet.getString("address_line"),
	  							resultSet.getString("city"),
	  							resultSet.getInt("rating"),
	  							resultSet.getFloat("price"));
			        	parameterList.add(instance);
						}
	  				saveParameterList(parameterList); 
		      } catch (Exception exp) { 
		    	  System.out.println("Problem :: "+exp);
				  System.out.println("----- Problem in GenericDao getAllInformation() -----"); 
		      } finally{ 
		    	  try {
					if(resultSet != null){
						resultSet.close();
						}
					if(statement != null){
						statement.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		      return parameterList; 
		   } 
	
	/**
	 * 
	 * @param userList
	 */
	private void saveParameterList(List<InterfaceBean> parameterList){ 
	      try { 
	         File file = new File("Users.dat"); 
	         FileOutputStream fos = new FileOutputStream(file);  
	         ObjectOutputStream oos = new ObjectOutputStream(fos); 
	         oos.writeObject(parameterList); 
	         oos.close(); 
	      } catch (FileNotFoundException e) { 
	         e.printStackTrace(); 
	      } catch (IOException e) { 
	         e.printStackTrace(); 
	      } 
	   }
	
	/**
	 * Get Hotel information from database
	 * @return
	 */
	public Map<String, ArrayList<String>> getHotelDetails(String destination) {
		Map<String, ArrayList<String>> output = new HashMap<String, ArrayList<String>>();
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> address_line = new ArrayList<String>();
		ArrayList<String> city = new ArrayList<String>();
		ArrayList<String> rating = new ArrayList<String>();
		ArrayList<String> price = new ArrayList<String>();
		ConnectionDatabase cDB = new ConnectionDatabase();
		try {
        	    connect = cDB.connectToDB();
        	    String sql = "SELECT id, name, address_line, city, rating, price FROM hotel_master WHERE city='"+destination+"'";
    			statement = connect.prepareStatement(sql); 
    			resultSet = statement.executeQuery(sql);
    				while(resultSet.next()){
						id.add(""+resultSet.getInt("id"));
						name.add(resultSet.getString("name"));
						address_line.add(resultSet.getString("address_line"));
						city.add(resultSet.getString("city"));
						rating.add(""+resultSet.getInt("rating"));
						price.add(""+resultSet.getFloat("price"));
					} 
				output.put("id", id);
				output.put("name", name);
				output.put("address_line", address_line);
				output.put("city", city);
				output.put("rating", rating);
				output.put("price", price);
	      } catch (Exception exp) { 
	    	  System.out.println("Problem :: "+exp);
			  System.out.println("----- Problem in GenericDao getHotelDetails() -----"); 
	      } finally{ 
	    	  try {
				if(resultSet != null){
					resultSet.close();
					}
				if(statement != null){
					statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return output; 
	}

	/**
	 * Make bookings from UI
	 * @param bean
	 * @return
	 */
	public boolean saveEntry(BookingDetails bean) {
		boolean returnFlag =  false; 
		ConnectionDatabase cDB = new ConnectionDatabase();
		try {
    	    connect = cDB.connectToDB();
    	    String sql = "INSERT INTO booking_details (first_name, last_name, email, phone_number, from_date, to_date, mode_of_commute, reasons_for_visit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			statement = connect.prepareStatement(sql); 
			statement.setString(1, bean.getFirstName());
			statement.setString(2, bean.getLastName());
			statement.setString(3, bean.getEmail());
			statement.setString(4, bean.getPhoneNumber());
			statement.setString(5, bean.getFromDate());
			statement.setString(6, bean.getToDate());
			statement.setString(7, bean.getMode_of_commute());
			statement.setString(8, bean.getReasonForVisit());
			// execute insert SQL statement
			statement.executeUpdate();
			returnFlag =  true; 
			System.out.println("Record is inserted into DBUSER table!");
      } catch (Exception exp) { 
    	  System.out.println("Problem :: "+exp);
		  System.out.println("----- Problem in GenericDao saveEntry() -----"); 
      } finally{ 
    	  try {
			if(resultSet != null){
				resultSet.close();
				}
			if(statement != null){
				statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnFlag;
	}
	
	/**
	 * Get all booking details
	 * @return
	 */
	public List<BookingDetails> getBookinDetails() {
		ConnectionDatabase cDB = new ConnectionDatabase();
		List<BookingDetails> parameterList = new ArrayList<BookingDetails>(); 
		try {
        	    connect = cDB.connectToDB();
        	    String sql = "SELECT idbooking_details, first_name, last_name, email, phone_number, from_date, to_date, mode_of_commute, reasons_for_visit FROM booking_details";
    			statement = connect.prepareStatement(sql); 
    			resultSet = statement.executeQuery(sql);
    				while(resultSet.next()){
	  					BookingDetails instance = new BookingDetails(
	  							resultSet.getString("first_Name"), 
	  							resultSet.getString("last_Name"),
	  							resultSet.getString("email"),
	  							resultSet.getString("mode_of_commute"),
	  							resultSet.getString("reasons_for_visit"),
	  							resultSet.getString("phone_number"),
	  							resultSet.getString("from_date"),
	  							resultSet.getString("to_date"));
			        	parameterList.add(instance);
						} 
//    				saveParameterList(parameterList); 
	      } catch (Exception exp) { 
	    	  System.out.println("Problem :: "+exp);
			  System.out.println("----- Problem in GenericDao getBookinDetails() -----"); 
	      } finally{ 
	    	  try {
				if(resultSet != null){
					resultSet.close();
					}
				if(statement != null){
					statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return parameterList; 
	}
}
