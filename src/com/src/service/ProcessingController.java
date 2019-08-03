package com.src.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.bean.BookingDetails;
import com.src.dao.GenericDao;

/**
 * Servlet implementation class ProcessingController
 */
public class ProcessingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status = false; 
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			BookingDetails bean = new BookingDetails();
			consumeService service = new consumeService();
			GenericDao gDao = new GenericDao();
			bean.setFirstName(request.getParameter("firstName"));
			bean.setLastName(request.getParameter("lastName"));
			bean.setEmail(request.getParameter("email"));
			bean.setPhoneNumber(request.getParameter("phoneNumber"));
			bean.setMode_of_commute(request.getParameter("mode_of_commute"));
			bean.setFromDate(request.getParameter("fromDate"));
			bean.setToDate(request.getParameter("toDate"));
			bean.setReasonForVisit(request.getParameter("reasonForVisit"));
			//Call second party API for setting travel reminder
			if(request.getParameter("travelAPI")!=null && request.getParameter("travelAPI").equals("on")) {
				status = service.setTravelReminder(request.getParameter("dynamicTextbox"), request.getParameter("boardingLocation"), request.getParameter("boardingPostalCode"), request.getParameter("sourcePostalCode"),
					request.getParameter("travelDate"), request.getParameter("travel_options"));
			}
			if(gDao.saveEntry(bean) && status) {
				// send email
				request.setAttribute("tableEntry", "confirmed");
				request.setAttribute("travelStatus", "reminderAdded");
				request.getRequestDispatcher("/hotel_book.jsp").forward(request, response);
				System.out.println("Got confirmation");
			} else {
				request.setAttribute("tableEntry", "notConfirmed");
				request.getRequestDispatcher("/hotel_book.jsp").forward(request, response);
				System.out.println("Redirected");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
