package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import clients.MysqlConnector;

/**
 * Servlet implementation class Customers
 */
public class Customers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MysqlConnector sql = new MysqlConnector();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Returns a list of customers who are currently checked into the hotel with same output as above.
		 */
		// or
		/*
		 * Returns a list of customers who have the same first or last name specified by the input parameter. 
		 * This list only contains the customer last name, first name, id, and phone number.
		 */
		String name = "";
		try {
			name = request.getPathInfo();
			name = name.substring(1); // remove the slash
		}catch (NullPointerException npe) {
			name = "";
		}
		
		List<Map<String, String>> customers = new ArrayList<Map<String, String>>();
		try {
			if (name == null || name.equals("")) {
				customers = sql.getCustomersCurrent();
			}else {
				customers = sql.getCustomersByName(name);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		JSONArray json = new JSONArray(customers.toArray());
		// return as json
    	response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(json.toString());
        out.close();
	}

}
