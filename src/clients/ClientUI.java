package clients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ClientUI {
	Requests custReq = new Requests("http://localhost:8080/customer");
	Requests custsReq = new Requests("http://localhost:8080/customers");
	Requests roomReq = new Requests("http://localhost:8080/room");
	Requests transReq = new Requests("http://localhost:8080/transaction");
	

	public static void main(String[] args) throws IOException {
		ClientUI ui = new ClientUI();
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int command = -99;
		while(command != 0) {
			System.out.println("Main menu: Select an option from below");
			System.out.println("1. Create Customer");
			System.out.println("2. Reserve a room");
			System.out.println("3. Create Payment");
			System.out.println("4. Get a customer by id");
			System.out.println("5. Get a customer by name");
			System.out.println("6. Get customers currently checked in");
			System.out.println("7. Get transactions of a customer by customer id");
			System.out.println("8. Get Vacancies");
			System.out.println("9. Get Reservations");
			System.out.println("0 to exit");
			command = sc.nextInt();
			
			HashMap<String, String> data = new HashMap<String, String>();
			switch (command) {
			case 0: System.out.println("Exiting");break;
			case 1: System.out.println("Enter the customer's information.");
					System.out.println("First name:");
					data.put("first", sc.nextLine());
					System.out.println("Last name:");
					data.put("last", sc.nextLine());
					System.out.println("Phone number:");
					data.put("phonenumber", sc.nextLine());
					System.out.println("Billing address:");
					data.put("address", sc.nextLine());
					System.out.println("Billing city:");
					data.put("city", sc.nextLine());
					System.out.println("Billing state:");
					data.put("state", sc.nextLine());
					System.out.println("Billing zip:");
					data.put("zip", sc.nextLine());
					ui.custReq.post(data);
					break;
			case 2: System.out.println("Customer id:");
					data.put("customerId", sc.nextLine());
					System.out.println("Room number:");
					data.put("roomNumber", sc.nextLine());
					ui.roomReq.post(data);
					break;
			case 3: System.out.println("Customer id:");
					data.put("customerId", sc.nextLine());
					System.out.println("Room number:");
					data.put("roomNumber", sc.nextLine());
					System.out.println("Amount:");
					data.put("amount", sc.nextLine());
					System.out.println("Credit card number:");
					data.put("creditCardNumber", sc.nextLine());
					System.out.println("Expiration date(in the form of Month/Year):");
					data.put("expiration", sc.nextLine());
					ui.transReq.post(data);
					break;
			case 4: System.out.println("Customer id:");
					ui.custReq.get(sc.nextLine());
					break;
			case 5: System.out.println("Enter a first or last name:");
					String name = sc.nextLine();
					ui.custsReq.get(name);
					break;
			case 6: ui.custsReq.get(""); break;
			case 7: System.out.println("Customer id:");
					ui.transReq.get(sc.nextLine());
					break;
			case 8: ui.roomReq.get("false");break;
			case 9: ui.roomReq.get("true");break;
			default: System.out.println("Invalid command! Try again");
			}
		}
	}

}
