package com.src.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.src.bean.BookingDetails;
import com.src.bean.InterfaceBean;

public class UserServiceTest {

	/**
	 * Success for - Dublin, Limrick, Galway and Cork
	 * Fail for - other than success cities 
	 */
	@Test
	public void testGetInterfaceXML() {
		UserService us = new UserService();
		String param = "Dublin";
		List<InterfaceBean> list = new ArrayList<InterfaceBean>();
		list = us.getInterfaceXML(param);
		assertEquals(list.size() > 0, true);
	}
	
	/**
	 * Success for - list.size() > 0
	 * Fail for - list.size() < 0
	 */
	@Test
	public void testGetBookingInterfaceXML() {
		UserService us = new UserService();
		List<BookingDetails> list = new ArrayList<BookingDetails>();
		list = us.getBookingInterfaceXML();
		assertEquals(list.size() > 0, true);
	}
}
