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
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MysqlConnector sql = new MysqlConnector();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Returns a list of transactions for a specified customer. 
		 * The list contains the transaction id, amount, and customer first name, last name.
		 */
		String customerId = request.getPathInfo();
		customerId = customerId.substring(1); // remove the slash
		List<Map<String, String>> transactions = new ArrayList<Map<String, String>>();
		try {
			transactions = sql.getTransactions(customerId);

		}catch (SQLException se) {
			se.printStackTrace();
		}
		JSONArray json = new JSONArray(transactions.toArray());
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
		 * creates a payment transaction for a given customer id and room id. 
		 * The customer service rep will enter in all payment information. 
		 * Upon success a transaction id is displayed else display an error. 
		 * The amount of the transaction is entered by the customer service rep as well 
		 * meaning that multiple transactions can be processed for the same customer and room. 
		 */
		String customerId = request.getParameter("customerId");
		String roomNumber =  request.getParameter("roomNumber");
		String creditCardNumber =  request.getParameter("creditCardNumber");
		String expirationDate =  request.getParameter("expirationDate");
		String transaction_id = "";
		try {
			transaction_id = sql.createPayment(Integer.parseInt(customerId), Integer.parseInt(roomNumber), creditCardNumber, expirationDate);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println("{\"transactionId\":"+transaction_id+"}");
        out.close();
		
	}

}
