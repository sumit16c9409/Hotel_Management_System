package com.src.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class consumeService {
	/**
	 * This method is written consume second party API i.e. Travel Reminder
	 * @param accessToken
	 * @param boardingLocation
	 * @param boardingPostalCode
	 * @param sourcePostalCode
	 * @param travelDate
	 * @param travel_options
	 * @return
	 * @throws Exception
	 */
	 public boolean setTravelReminder(Object accessToken, String boardingLocation, String boardingPostalCode, String sourcePostalCode, String travelDate, String travel_options) throws Exception {
		 boolean status = false;
		 try {
			 Client client = Client.create();
				WebResource webResource = client.resource("http://travelreminderapi.azurewebsites.net/api/Values/SetReminder");
				String input = "{\"AccessToken\":\""+accessToken+"\",\"Location\":\""+boardingLocation+"\",\"BoradingPointPostalCode\":\""+boardingPostalCode+"\","
							  + "\"SourcePostalCode\":\""+sourcePostalCode+"\",\"TravelDate\":\""+travelDate+"\",\"ModeOfTravel\":\""+travel_options+"\",\"ApiKey\":\"abc-1\"}";
				ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus());
				}
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				if(output.equals("true")) {
					status = true;
				}
				System.out.println(output);
			} catch (Exception e) {
				System.out.println("Problem in consumeService"+e);
				e.printStackTrace();
			}
		return status;
	   }
	 
	 /**
	  * This method is written consume second party API i.e. Foody
	  * @param city
	  * @return
	  * @throws Exception
	  */
	 public HashMap<String, ArrayList<String>> getFoody(String city) throws Exception {
		 HashMap<String, ArrayList<String>> foody = new HashMap<String, ArrayList<String>>();
		 ArrayList<String> city_name = new ArrayList<String>();
		 ArrayList<String> country_name = new ArrayList<String>();
		 ArrayList<String> description = new ArrayList<String>();
		 ArrayList<String> image_url = new ArrayList<String>();
		 ArrayList<String> title = new ArrayList<String>();
		 ArrayList<String> id = new ArrayList<String>();
		 HttpURLConnection connection = null;
		 String inputLine;
		 StringBuffer response1 = new StringBuffer();
		 try {
			 	URL url = new URL("https://webapp-180725203117.azurewebsites.net/webapi/restuarant/"+city);	
				connection = (HttpURLConnection) url.openConnection();
			    connection.setRequestMethod("GET");
			    connection.setRequestProperty("Content-Type","application/json");
			    connection.setUseCaches(false);
			    connection.setDoOutput(true);
			    if (connection.getResponseCode()==200) {
			        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			        while ((inputLine = in.readLine()) != null) {
			            response1.append(inputLine);
			        }
			        in.close();
			    JSONArray name = new JSONArray(response1.toString());
			        for(int i=0;i<=name.length()-1;i++)
			        {
			        	id.add(Integer.toString(i));
			 	        city_name.add(name.getJSONObject(i).getString("city_name"));
			 	        country_name.add(name.getJSONObject(i).getString("country_name"));
 			 	        description.add(name.getJSONObject(i).getString("description"));
			 	        image_url.add(name.getJSONObject(i).getString("image_url"));
			 	        title.add(name.getJSONObject(i).getString("title"));
			         }
			    }
			    foody.put("id", id);
				foody.put("city_name", city_name);
				foody.put("country_name", country_name);
				foody.put("description", description);
				foody.put("image_url", image_url);
				foody.put("title", title);
			} catch (Exception e) {
				System.out.println("Problem in consumeService"+e);
				e.printStackTrace();
			}
		return foody;
	   }
	 
	 /**
	  * This method is written consume second party API i.e. Tourism
	  * @param city
	  * @return
	  * @throws Exception
	  */
	 public HashMap<String, ArrayList<String>> getTourism(String city) throws Exception {
		 HashMap<String, ArrayList<String>> foody = new HashMap<String, ArrayList<String>>();
		 ArrayList<String> city_name = new ArrayList<String>();
		 ArrayList<String> country_name = new ArrayList<String>();
		 ArrayList<String> description = new ArrayList<String>();
		 ArrayList<String> image_url = new ArrayList<String>();
		 ArrayList<String> title = new ArrayList<String>();
		 HttpURLConnection connection = null;
		 String inputLine;
		 StringBuffer response1 = new StringBuffer();
		 try {
			 	URL url = new URL("http://localhost:8080/foody/webapi/restuarant/"+city);	
				connection = (HttpURLConnection) url.openConnection();
			    connection.setRequestMethod("GET");
			    connection.setRequestProperty("Content-Type","application/json");
			    connection.setUseCaches(false);
			    connection.setDoOutput(true);
			    if (connection.getResponseCode()==200) {
			        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			        while ((inputLine = in.readLine()) != null) {
			            response1.append(inputLine);
			        }
			        in.close();
			    JSONArray name = new JSONArray(response1.toString());
			        for(int i=0;i<=name.length()-1;i++)
			        {
			 	        city_name.add(name.getJSONObject(i).getString("city_name"));
			 	        country_name.add(name.getJSONObject(i).getString("country_name"));
 			 	        description.add(name.getJSONObject(i).getString("description"));
			 	        image_url.add(name.getJSONObject(i).getString("image_url"));
			 	        title.add(name.getJSONObject(i).getString("title"));
			         }
			    }
				foody.put("city_name", city_name);
				foody.put("country_name", country_name);
				foody.put("description", description);
				foody.put("image_url", image_url);
				foody.put("title", title);
			} catch (Exception e) {
				System.out.println("Problem in consumeService"+e);
				e.printStackTrace();
			}
		return foody;
	   }
}
