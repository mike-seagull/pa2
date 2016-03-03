package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import clients.MysqlConnector;

/**
 * Servlet implementation class Room
 */
@WebServlet("/Room")
public class Room extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MysqlConnector sql = new MysqlConnector();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Room() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Returns a list of all vacant rooms including their room types.
		 */
		// or
		/*
		 * Returns a list of all rooms currently occupied by a customer. Include the customer first name/last name in the output.
		 */
		String occupied = request.getParameter("occupied");
		List<Map<String, String>> rooms = new ArrayList<Map<String, String>>();

		// get vacant rooms by default
		try {
			if (occupied.equals("true") || occupied.equals("") || occupied == null) {
				rooms = sql.getVacancies();
			}else {
				rooms = sql.getReservations();
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		JSONArray json = new JSONArray(rooms.toArray());
		// return as json
    	response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(json.toString());
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * reserves a room for a given customer id. 
		 * This will take the customer id as well as a room number. 
		 * A success message is displayed when the room is successfully reserved else display an error.
		 */
		String customerId = request.getParameter("customerId");
		String roomNumber = request.getParameter("roomNumber");
    	response.setContentType("application/json");
        PrintWriter out = response.getWriter();

		try {
			sql.reserveRoom(Integer.parseInt(customerId), Integer.parseInt(roomNumber));
	        out.println("{\"success\": true}");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        out.println("{\"success\": false}");
			
		}
		out.close();
	}

}
