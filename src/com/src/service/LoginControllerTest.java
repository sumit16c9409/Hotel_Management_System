package com.src.service;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class LoginControllerTest {

	@SuppressWarnings("null")
	@Test
	public void test() {
		try {
			HttpServletRequest request = null; 
			HttpServletResponse response = null;
			String destination = "Dublin";
			LoginController ctrl = new LoginController();
			request.setAttribute("destination", destination);
			ctrl.doGet(request, response);
			assertEquals(request.getAttribute("message").toString(), true);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
