package clients;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

public class MysqlConnector {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String db = null;
	String host = null;
	
	public MysqlConnector() {
		String url = "localhost/pa1_michaelhollister";
		String[] urlsplit = url.split("/");
		host = urlsplit[0];
		db = urlsplit[1];
		try {
			System.out.println("Trying to connect to database @ "+ url);
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://" + url +"?" + "useSSL=false", "root", "comp6302");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Cannot connect to DB: "+ db);
			try {
				// connect to the mysql server and create the db
				conn = DriverManager.getConnection("jdbc:mysql://" + host + "?" + "useSSL=false", "root", "comp6302");
				System.out.println("Going to try to create DB: "+db);
				ps = conn.prepareStatement("CREATE DATABASE "+ db);
				ps.execute();
				System.out.println("Created DB: "+ db + " sucessfully");
				conn.close();
			} catch (SQLException sqle) {
				System.out.println("Cannot create DB: "+ db +" on MySQL Server. Is it running? Exiting...");
				System.exit(1);
			}
		}
		// lets create the tables
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + url + "?" + "useSSL=false", "root", "comp6302");
			System.out.println("Got Mysql database connection");
			String customerSql = "CREATE TABLE `pa1_michaelhollister`.`customers` (" +
								"`id` INT NOT NULL AUTO_INCREMENT," +
								"`first_name` VARCHAR(45) NOT NULL," +
								"`last_name` VARCHAR(45) NOT NULL," +
								"`phonenumber` VARCHAR(15) NOT NULL," +
								"`address` VARCHAR(254) NOT NULL," +
								"`city` VARCHAR(254) NOT NULL," +
								"`state` VARCHAR(254) NOT NULL," +
								"`zip` VARCHAR(10) NOT NULL," +
								"`checkin` DATETIME NULL," +
								"`checkout` DATETIME NULL," +
								"PRIMARY KEY (`id`)," +
								"UNIQUE INDEX `id_UNIQUE` (`id` ASC));";

			ps = conn.prepareStatement(customerSql);
			try {
				ps.execute();
			}catch (SQLException se) {}
			String roomSql = "CREATE TABLE `pa1_michaelhollister`.`rooms` (" +
						  "`number` INT NOT NULL," +
						  "`type` VARCHAR(12) NOT NULL," +
						  "`price` DECIMAL(5,2) NOT NULL," +
						  "`current_occupant` INT NULL," +
						  "PRIMARY KEY (`number`)," +
						  "UNIQUE INDEX `number_UNIQUE` (`number` ASC)," +
						  "INDEX `id_idx` (`current_occupant` ASC));";
			ps = conn.prepareStatement(roomSql);
			try {
				ps.execute();
			}catch (SQLException se) {}
			String transactionSql = "CREATE TABLE `pa1_michaelhollister`.`transactions` (" +
								  "`id` INT NOT NULL AUTO_INCREMENT," +
								  "`customer_id` INT NOT NULL," +
								  "`roomNumber` int NOT NULL," +
								  "`credit_card_number` VARCHAR(16) NOT NULL," +
								  "`expiration_date` DATE NOT NULL," +
								  "PRIMARY KEY (`id`)," +
								  "UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
								  "INDEX `id_idx` (`customer_id` ASC)," +
								  "CONSTRAINT `id`" +
								    "FOREIGN KEY (`customer_id`)" +
								    "REFERENCES `pa1_michaelhollister`.`customers` (`id`)" +
								    "ON DELETE NO ACTION" +
								    "ON UPDATE NO ACTION);";
			ps = conn.prepareStatement(transactionSql);
			try {
				ps.execute();
			}catch (SQLException se) {}
			String sql = "";
			for (int i=1;i<=40;i++) {
				sql = "INSERT INTO `pa1_michaelhollister`.`rooms` (`number`, `type`, `price`) VALUES (?, 'single', '100.00');";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, 100+i);
				try {
					ps.execute();
				}catch (SQLException se) {}
			}
			for (int i=1;i<=50;i++) {
				sql = "INSERT INTO `pa1_michaelhollister`.`rooms` (`number`, `type`, `price`) VALUES (?, 'double', '150.00');";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, 200+i);
				try {
					ps.execute();
				}catch (SQLException se) {}
			}
			for (int i=1;i<=10;i++) {
				sql = "INSERT INTO `pa1_michaelhollister`.`rooms` (`number`, `type`, `price`) VALUES (?, 'presidential', '300.00');";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, 300+i);
				try {
					ps.execute();
				}catch (SQLException se) {}
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("Cannot create DB: "+ db +" on MySQL Server. Is it running?");
		}
	}
	public String createCustomer(String first, String last, String phonenumber, String address, String city, String state, String zip) throws SQLException {
		String sql = "INSERT INTO `pa1_michaelhollister`.`customers` (`first_name`, `last_name`, `phonenumber`, `address`, `city`, `state`, `zip`) VALUES (?, ?, ?, ?, ?, ?, ?);";
		ps = conn.prepareStatement(sql);
		ps.setString(1, first);
		ps.setString(2, last);
		ps.setString(3, phonenumber);
		ps.setString(4, address);
		ps.setString(5, city);
		ps.setString(6, state);
		ps.setString(7, zip);
		ps.execute();
		
		Map<String, String> data = new HashMap<String, String>();
		sql = "SELECT id FROM customers " + 
				"WHERE first_name = ? AND last_name = ? AND phonenumber = ? " +
				"AND address = ? AND city = ? AND state = ? AND zip = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, first);
		ps.setString(2, last);
		ps.setString(3, phonenumber);
		ps.setString(4, address);
		ps.setString(5, city);
		ps.setString(6, state);
		ps.setString(7, zip);
		rs = ps.executeQuery();
		
		String customerId = "-99";
		while(rs.next()) {
			customerId = Integer.toString(rs.getInt("id"));
		}
		return customerId;
	}
	public void reserveRoom(int customerId, int roomNumber) throws SQLException {
		ps = conn.prepareStatement("UPDATE `pa1_michaelhollister`.`rooms` SET `current_occupant`=? WHERE `number`=?;");
		ps.setInt(1, customerId);
		ps.setInt(2, roomNumber);
		ps.execute();
	}
	public String createPayment(int customerId, int roomNumber, String creditCardNumber, String expirationDate) throws SQLException {
		String sql = "INSERT INTO `pa1_michaelhollister`.`transactions` (`customer_id`, `room_id`, `credit_card_number`) VALUES (?, ?, ?, ?);";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, customerId);
		ps.setInt(2, roomNumber);
		ps.setString(3, creditCardNumber);
		String[] dateSplit = expirationDate.split("/");
		String month = dateSplit[0];
		String year = "20"+dateSplit[1];
		ps.setDate(4, Date.valueOf(year + "-" + month + "-00"));
		ps.execute();
		
		String transaction_id = "";
		sql = "SELECT `id` FROM `pa1_michaelhollister`.`transactions` WHERE `customer_id` = ? AND `room_id` = ? AND `credit_card_number` = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, customerId);
		ps.setInt(2, roomNumber);
		ps.setString(3, creditCardNumber);
		rs = ps.executeQuery();
		while(rs.next()) {
			transaction_id = Integer.toString(rs.getInt("id"));
		}
		return transaction_id;
	}
	public Map<String, String> getCustomer(int customerId) throws SQLException {
		Map<String, String> data = new HashMap<String, String>();
		ps = conn.prepareStatement("SELECT * FROM customers WHERE id = ?");
		ps.setInt(1, customerId);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			data.put("first_name", rs.getString("first_name").toString());
			data.put("last_name", rs.getString("last_name").toString());
			data.put("phonenumber", rs.getString("phonenumber").toString());
			data.put("address", rs.getString("address").toString());
			data.put("city", rs.getString("city").toString());
			data.put("state", rs.getString("state").toString());
			data.put("zip", rs.getString("zip").toString());
		}
		return data;
	}
	public List<Map<String, String>> getCustomersByName(String firstOrLastName) throws SQLException {
		ps = conn.prepareStatement("SELECT id, first_name, last_name, phonenumber FROM customers WHERE first_name = ? OR last_name = ?");
		ps.setString(1, firstOrLastName);
		ps.setString(2, firstOrLastName);
		rs = ps.executeQuery();
		List<Map<String, String>> customers = new ArrayList<Map<String, String>>();

		while(rs.next()) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("id", Integer.toString(rs.getInt("id")));
			data.put("first_name", rs.getString("first_name").toString());
			data.put("last_name", rs.getString("last_name").toString());
			data.put("phonenumber", rs.getString("phonenumber").toString());
			customers.add(data);
		}
		return customers;
	}
	public List<Map<String, String>> getCustomersCurrent() throws SQLException {
		ps = conn.prepareStatement("SELECT c.id, c.first_name, c.last_name, c.phonenumber from rooms as r, customers as c where r.current_occupant = c.id");
		rs = ps.executeQuery();
		List<Map<String, String>> customers = new ArrayList<Map<String, String>>();
		while(rs.next()) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("id", Integer.toString(rs.getInt("id")));
			data.put("first_name", rs.getString("first_name").toString());
			data.put("last_name", rs.getString("last_name").toString());
			data.put("phonenumber", rs.getString("phonenumber").toString());
			customers.add(data);
		}
		return customers;
	}
	public List<Map<String, String>> getTransactions(String customerId) throws SQLException {
		String sql = "SELECT t.id, r.amount, c.first_name, c.last_name "+
					"FROM customers AS c, transactions AS t, rooms AS r "+
					"WHERE c.id = ? "+
						"AND c.id = t.customer_id "+
						"AND c.id = r.current_occupant";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		List<Map<String, String>> transactions = new ArrayList<Map<String, String>>();
		
		while(rs.next()) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("transactionId", Integer.toString(rs.getInt("t.id")));
			data.put("amount", rs.getBigDecimal("r.amount").toString());
			data.put("first_name", rs.getString("c.first_name").toString());
			data.put("last_name", rs.getString("c.last_name").toString());
			transactions.add(data);
		}
		return transactions;
		
	}
	public List<Map<String, String>> getVacancies() throws SQLException {
		ps = conn.prepareStatement("SELECT number, type FROM rooms WHERE current_occupant IS NULL");
		rs = ps.executeQuery();
		List<Map<String, String>> vacancies = new ArrayList<Map<String, String>>();

		while(rs.next()) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("number", Integer.toString(rs.getInt("number")));
			data.put("type", rs.getString("type").toString());
			vacancies.add(data);
		}
		return vacancies;
		
	}
	public List<Map<String, String>> getReservations() throws SQLException {
		ps = conn.prepareStatement("SELECT r.number, c.first_name, c.last_name "+
								"FROM customers AS c, rooms AS r "+
								"WHERE r.current_occupant IS NOT NULL AND r.current_occupant = c.id");
		rs = ps.executeQuery();
		List<Map<String, String>> reservations = new ArrayList<Map<String, String>>();
		while(rs.next()) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("roomNumber", Integer.toString(rs.getInt("number")));
			data.put("first_name", rs.getString("first_name").toString());
			data.put("last_name", rs.getString("last_name").toString());
			reservations.add(data);
		}
		return reservations;
	}
}
