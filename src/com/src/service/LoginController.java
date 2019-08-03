package com.src.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.dao.GenericDao;
import com.src.bean.*;

/**
 * Servlet implementation class myServlet
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GenericDao gDao = new GenericDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String locationParam="";
		if(request.getParameter("destination")!=null && request.getParameter("destination")!="") {
		    try {
		    	GenericDao gDao = new GenericDao();
				consumeService service = new consumeService();
				String destination = request.getParameter("destination");
			    Map<String, ArrayList<String>> hotelDetails = gDao.getHotelDetails(destination);
//			    Enable code for Bhavya shukla
			    Map<String, ArrayList<String>> foody = service.getFoody(destination);
				request.setAttribute("foody", foody);
				request.setAttribute("message", hotelDetails);
		        request.getRequestDispatcher("/hotel_listing.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("bookNow")!=null && request.getParameter("bookNow")!="") {
			locationParam = request.getParameter("bookNow");
			System.out.println("locationParam -"+locationParam);
			request.setAttribute("tableEntry", "notConfirmed");
			request.getRequestDispatcher("/hotel_book.jsp").forward(request, response);
		} else if (request.getParameter("locationParam")!=null && request.getParameter("locationParam")!="") {
			locationParam = request.getParameter("locationParam");
			System.out.println("locationParam -"+locationParam);
	        String words[] = locationParam.split(",");

			request.setAttribute("latitude", words[0]);
			request.setAttribute("longitude", words[1]);
			request.getRequestDispatcher("/view_location.jsp").forward(request, response);
		} else if (request.getParameter("saveBooking")!=null && request.getParameter("saveBooking")!="") {
			BookingDetails bean = new BookingDetails();
			GenericDao gDao = new GenericDao();
			bean.setFirstName(request.getParameter("firstName"));
			bean.setLastName(request.getParameter("lastName"));
			bean.setEmail(request.getParameter("email"));
			bean.setPhoneNumber(request.getParameter("phoneNumber"));
			bean.setMode_of_commute(request.getParameter("mode_of_commute"));
			bean.setFromDate(request.getParameter("fromDate"));
			bean.setToDate(request.getParameter("toDate"));
			bean.setReasonForVisit(request.getParameter("reasonForVisit"));
			if(gDao.saveEntry(bean)) {
				// send email
				request.setAttribute("tableEntry", "confirmed");
				request.getRequestDispatcher("/hotel_book.jsp").forward(request, response);
				System.out.println("Got confirmation");
			} else {
				request.setAttribute("tableEntry", "notConfirmed");
				request.getRequestDispatcher("/hotel_book.jsp").forward(request, response);
				System.out.println("Redirected");
			}
		} else {
			System.out.println("Please check the parameters you are passing from jsp");
			System.out.println("locationParam -"+locationParam);
		}
		
	}
}
