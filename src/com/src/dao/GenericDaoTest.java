/**
 * 
 */
package com.src.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.src.bean.BookingDetails;
import com.src.bean.InterfaceBean;

/**
 * @author Akash Hande
 *
 */
public class GenericDaoTest {

	/**
	 * Success for - Dublin, Limrick, Galway and Cork
	 * Fail for - other than success cities 
	 */
	@Test
	public void testGetAllInformation() {
		String destination = "Dublin";
		GenericDao gDao = new GenericDao();
		List<InterfaceBean> list = new ArrayList<InterfaceBean>();
		list = gDao.getAllInformation(destination);
		assertEquals(list.size() > 0, true);
	}
	
	/**
	 * Success for - bean must be filled
	 * Fail for - empty bean
	 */
	@Test
	public void testSaveEntry() {
			BookingDetails bean = new BookingDetails();
			GenericDao gDao = new GenericDao();
			boolean status = false;
			bean.setFirstName("Akash");
			bean.setLastName("Hande");
			bean.setEmail("akash@example.com");
			bean.setPhoneNumber("0892248726");
			bean.setFromDate("2018/07/20");
			bean.setToDate("2018/07/25");
			bean.setMode_of_commute("Bus");
			bean.setReasonForVisit("Business Trip");
			status = gDao.saveEntry(bean);
			assertEquals(status, true);
		}
	
	/**
	 * Success for - parameterList.size()>0
	 * Fail for - parameterList.size()<0
	 */
	@Test
	public void testGetBookinDetails() {
			List<BookingDetails> parameterList = new ArrayList<BookingDetails>();
			GenericDao gDao = new GenericDao();
			parameterList = gDao.getBookinDetails();
			assertEquals(parameterList.size()>0, true);
		} 
	
	/**
	 * Success for - Dublin, Limrick, Galway and Cork
	 * Fail for - other than success cities 
	 */
	@Test
	public void testGetHotelDetails() {
			Map<String, ArrayList<String>> output = new HashMap<String, ArrayList<String>>();
			GenericDao gDao = new GenericDao();
			String destination = "Dublin";
			output = gDao.getHotelDetails(destination);
			assertEquals(output.get("id").size()>0, true);
		} 
}
