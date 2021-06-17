import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class BoutiqueClient {
	static Scanner input = new Scanner(System.in);
	static ObjectOutputStream os;
	static ObjectInputStream is;
	static PrintWriter pw;
	
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 147);
        pw= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
        //for message send. print is its function which writes
        //connection established
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
      //Returns the input stream of the socket.
        
        int choice = 0;
        do {
        	System.out.println("Main Menu");
        	System.out.println("Enter 1 for Dresses Menu");
        	System.out.println("Enter 2 for Customer Menu");
        	System.out.println("Enter 3 to View Orders");
        	System.out.println("Enter 4 to exit");
        	choice=input.nextInt();
        	switch(choice) {
        	case 1:{
        		DressMenu(choice);
        	}
        		break;
        	case 2:{
        		CustomerMenu(choice);
        	}
        		break;
        	case 3:{
//        		os.writeObject(choice);
//                os.reset();
        		ViewOrders(choice);
        	}
        		break;
        	case 4: {
                os.writeObject(choice);
                socket.close();
                System.exit(0);
            }
            break;
            default:
                System.out.println("You pressed the wrong key. Try Again!");
        	}
        }while(choice!=0);
}

	private static void ViewOrders(int choice) throws IOException, ClassNotFoundException {
		System.out.println("ORDERS");
		pw.flush();
        pw.println(3);
        System.out.println("You will be shown all orders now");
        pw.flush();
        pw.println(2);
        ArrayList<Order> list = new ArrayList<Order>();
        list = (ArrayList<Order>) is.readObject();

        if (list.isEmpty()) {
            System.out.println("There are no Orders");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            	}
            }
//        os.reset();
        pw.flush();
		
	}

	private static void CustomerMenu(int choice) throws IOException, ClassNotFoundException {
//		os.writeObject(choice);
//        os.reset();
		pw.println(choice);
		//for string sending
		int choice2=0;
            System.out.println("Customer Menu");
            System.out.println("Enter 1 to Add a Customer");
            System.out.println("Enter 2 to View all Customers");
            System.out.println("Enter 3 to Search a Customer");
            System.out.println("Enter 4 to Buy a dress");
            System.out.println("Enter 5 to go back");
            choice2 = input.nextInt();
            switch (choice2) {
                case 1: {
                	
                	//add customer here
                    System.out.println("Adding Customer");
                    System.out.println("Enter the name of the Customer");
                    String nameCustomer = input.nextLine();
                    nameCustomer = input.nextLine();
                    System.out.println("Enter id of customer");
                    int id = input.nextInt();
                    System.out.println("Enter Contact no of Customer");
                    String contactNo = input.nextLine();
                    contactNo = input.nextLine();
                    System.out.println("Adding customer now");
                    
                    Customer c1 = new Customer(nameCustomer, id, contactNo);

//                    os.writeObject(choice2);
//                    os.reset();
                    pw.println(choice2);
                    //this is used to clean the stream
                    os.writeObject(c1);
                    System.out.println("Customer Added");
                    System.out.println(c1.toString());
//                    os.reset();
                    pw.flush();
                }
                break;
                case 2: {
                    //view every Customer
                    System.out.println("All Customers");
//                    os.writeObject(choice2);
                    pw.println(choice2);
                    ArrayList<Customer> list = new ArrayList<Customer>();
                    list = (ArrayList<Customer>) is.readObject();

                    if (list.isEmpty()) {
                        System.out.println("There are no Customers");
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(list.get(i).toString());
                        	}
                        }
//                    os.reset();
                    pw.flush();
                    }
                break;
                case 3: {
                    System.out.println("Searching Function");
                    System.out.println("Enter name to Search");
                    String name = input.next();
//                    os.writeObject(choice2);
                    pw.println(choice2);
                    os.writeObject(name);
                    ArrayList<Customer> list = new ArrayList<Customer>();
                    list = (ArrayList<Customer>) is.readObject();
                    if (list.isEmpty() == true) {
                        System.out.println("Customer not Found");
                    } else {
                        System.out.println("Customers Found");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(list.get(i).toString());
                        	}
                        }
//                    os.reset();
                    pw.flush();
                    }
                break;
                case 4: {
                	//buying a dress
                	Customer c2 = null;
                	Dress d2 = null;
                	Order o1;
                	System.out.println("Enter your name");
                	String customerName=input.next();
                	pw.println(3);
                    os.writeObject(customerName);
                    ArrayList<Customer> list = new ArrayList<Customer>();
                    list = (ArrayList<Customer>) is.readObject();
                    if (list.isEmpty() == true) {
                        System.out.println("There are no Customers");
                    } else {
                        
                        for (int i = 0; i < list.size(); i++) {
                        	if(list.get(i).getName().equalsIgnoreCase(customerName)) {
                        		System.out.println("Customer Found");
                        		System.out.println(list.get(i).toString());
                        		c2=new Customer(list.get(i).getName(),list.get(i).getId(),list.get(i).getContactNo());
                        		
                        		//show all dresses so that he chooses one
                        		pw.flush();
                        		//clear the stream
                        		pw.println(1);
                        		pw.flush();
                        		//view every Dress
                                System.out.println("Select Dress by name");
                        		pw.println(2);
                        		pw.flush();
                                ArrayList<Dress> list2 = new ArrayList<Dress>();
                                list2 = (ArrayList<Dress>) is.readObject();

                                if (list2.isEmpty()) {
                                    System.out.println("There are no Dresss. Sorry");
                                } else {
                                    for (int y = 0; y < list2.size(); y++) {
                                        System.out.println(list2.get(y).toString());
                                    	}
                                    System.out.println("Enter name of the dress you want to buy");
                                    String dressName=input.next();
                                    for (int y = 0; y < list2.size(); y++) {
                                    	if(list2.get(y).getName().equalsIgnoreCase(dressName)) {
                                    		System.out.println("Your order for "+list2.get(y).toString()+" is Ready");
                                    		d2=new Dress(list2.get(y).getName(),list2.get(y).getQuantity(),list2.get(y).getSize(),list2.get(y).getColor(),list2.get(y).getPrice());
                                    		System.out.println("Congratulations on your Purchase Mr."+ c2.getName());
                                    	}
                                    	}
                                    }
                              
                        		
                        	}
                        	else {
                        		System.out.println("Please Register First");
                        		return;
                        	}
                            
                        	}
                        pw.flush();
                        pw.println(3);
                        
                        o1=new Order(c2,d2,"2/2/2");
                        pw.flush();
                        pw.println(1);
//                        os.reset();
                        os.writeObject(o1);
                        System.out.println("Your Order is: "+ o1.toString());
                        }
//                    os.reset();
                    pw.flush();
                	
                    
                }
                break;
                case 5: {
                	pw.flush();
                    return;
                }
                default:
                    System.out.println("You pressed the wrong key. Try Again!");
            }
		
	}

	public static void DressMenu(int choice) throws IOException, ClassNotFoundException {
		pw.println(choice);
		//for string sending
		int choice2=0;
            System.out.println("Dress Menu");
            System.out.println("Enter 1 to Add a Dress");
            System.out.println("Enter 2 to View all Dresses");
            System.out.println("Enter 3 to Search a Dress");
            System.out.println("Enter 4 to go back");
            choice2 = input.nextInt();
            switch (choice2) {
                case 1: {
                    //add Dress here
                    System.out.println("Adding Dress");
                    System.out.println("Enter the name of the Dress");
                    String nameDress = input.nextLine();
                    nameDress = input.nextLine();
                    System.out.println("Enter quantity of Dress");
                    int quantity = input.nextInt();
                    System.out.println("Enter size of Dress");
                    String size = input.nextLine();
                    size = input.nextLine();
                    System.out.println("Enter color of Dress");
                    String color = input.nextLine();
                    System.out.println("Enter price of Dress");
                    int price = input.nextInt();
                    System.out.println("Adding Dress now");
              
                    Dress d1 = new Dress(nameDress, quantity, size, color, price);

//                    os.writeObject(choice2);
//                    os.reset();
                    pw.println(choice2);
                    //this is used to clean the stream
                    os.writeObject(d1);
                    System.out.println("Dress Added");
                    System.out.println(d1.toString());
//                    os.reset();
                    pw.flush();
                }
                break;
                case 2: {
                    //view every Dress
                    System.out.println("Dresss View Page");
//                    os.writeObject(choice2);
                    pw.println(choice2);
                    ArrayList<Dress> list = new ArrayList<Dress>();
                    
                    list = (ArrayList<Dress>) is.readObject();
//                    System.out.println("2) This should be 2 : "+choice2);
                    

                    if (list.isEmpty()) {
                        System.out.println("There are no Dresss");
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(list.get(i).toString());
                        	}
                        }
//                    os.reset();
                    pw.flush();
                    }
                break;
                case 3: {
                    System.out.println("Searching Function");
                    System.out.println("Enter name to Search");
                    String name = input.next();
//                    os.writeObject(choice2);
                    pw.println(choice2);
                    os.writeObject(name);
                    ArrayList<Dress> list = new ArrayList<Dress>();
                    list = (ArrayList<Dress>) is.readObject();
                    if (list.isEmpty() == true) {
                        System.out.println("Dress not Found");
                    } else {
                        System.out.println("Dress Found");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(list.get(i).toString());
                        	}
                        }
//                    os.reset();
                    os.flush();
                    pw.flush();
                    }
                break;
                case 4: {
                    return;
                }
                default:
                    System.out.println("You pressed the wrong key. Try Again!");
            }
		
	}
    }
