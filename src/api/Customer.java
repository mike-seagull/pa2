package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import clients.MysqlConnector;

/**
 * Servlet implementation class Customer
 */
@WebServlet("/Customer")
public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MysqlConnector sql = new MysqlConnector();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		/*
		 * gets the customer with the specified id and prints that customer’s information.
		 */
		String id = request.getPathInfo();
		id = id.substring(1); // remove the slash
		Map<String, String> customer = new HashMap<String, String>();
		try {
			customer = sql.getCustomer(Integer.parseInt(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return as json
		JSONObject json = new JSONObject(customer);
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
		 * creates a new customer in the customer table. Gather all information for the customer. 
		 * After the customer is created display the customer id generated (using autoincremented id). 
		 * For any errors display them.
		 */
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String phonenumber = request.getParameter("phonenumber");
		String address = request.getParameter("address"); 
		String city = request.getParameter("city"); 
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String customerId = "";
		try {
			customerId = sql.createCustomer(first, last, phonenumber, address, city, state, zip);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return as json
		JSONObject json = new JSONObject("{\"customerId\":\"" + customerId +"\"}") ;
    	response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(json.toString());
        out.close();
	}

}
