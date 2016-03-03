package clients;

import java.util.Scanner;

public class ClientUI {
	Requests custReq = new Requests("http://localhost:8080/customer");
	Requests custsReq = new Requests("http://localhost:8080/customers");
	Requests roomReq = new Requests("http://localhost:8080/room");
	Requests transReq = new Requests("http://localhost:8080/transaction");
	

	public static void main(String[] args) {
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
			
			switch (command) {
			case 0: break;
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 7: break;
			case 8: break;
			case 9: break;
			default: System.out.println("Invalid command! Try again");
			}
		}
	}

}
