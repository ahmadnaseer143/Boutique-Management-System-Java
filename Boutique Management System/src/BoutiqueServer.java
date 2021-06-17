import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class BoutiqueServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Server Started");
        ServerSocket welcomeSocket = new ServerSocket(147);
        //through this port number communication between the client and the server will be done
        Socket connectionSocket = welcomeSocket.accept();
        //now server will wait for the client to make a connection due to accept method
        System.out.println("Connection established");
        //After the connections are established, communication can occur using I/O streams.
        //Each socket has both an OutputStream and an InputStream.
        //The client's OutputStream is connected to the server's InputStream, and the client's InputStream is connected to the server's OutputStream.
        //input recieve kerne ke liye hoti hai
        BufferedReader br= new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        //object read
        ObjectOutputStream os = new ObjectOutputStream(connectionSocket.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(connectionSocket.getInputStream());
        //Returns the input stream of the socket.
        while (true) {
        	//while loop so that it can listen again and again
//            Object opt = is.readObject();
//            int option = (Integer) opt;
        	
        	String option=br.readLine();
//        	System.out.println("Option1: "+ option);
        	
        	
//            System.out.println("Option: "+option);
            //option is for which menu client selected
            switch(option) {
            ////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////
            //Dress Menu
            ////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////
            case "1":{
            	//DRESS MENU
//            	Object opti = is.readObject();
//                int option2 = (Integer) opti;
            	String option2= br.readLine();
            	
//                System.out.println("Option 2:"+ option2);
                //in dress menu what option the client selected
                switch (option2) {
                case "1": {
                    //now writing to the file
                    Dress d1 = (Dress) is.readObject();
                     d1.writeObjectToFile(d1);
                    //writeObjectToFile(d1);
                }
                break;
                case "2": {
                    //Reading or viewing all data in Dress file
                	
                	Dress d1=new Dress();
                    os.writeObject( d1.readAllData());
                }
                break;
                case "3": {
                    //Searching by name
//                	System.out.println("Option1:"+option);
//                	System.out.println("Option1:"+option2);
                	Dress d1=new Dress();
                    String name = (String) is.readObject();
                    ArrayList<Dress> getName= d1.search(name);
                    os.writeObject(getName);
                }
                break;
                default:
                    System.out.println("You pressed the wrong key");
            }
//            os.reset();
                
            }
            break;
			////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////
			//Customer Menu
			////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////
            case "2":{
            	//Customer Menu
//            	Object opti = is.readObject();
//                int option2 = (Integer) opti;
            	String option2=br.readLine();
//            	System.out.println("Option:"+option2);
                //in Customer what option the client selected
                switch (option2) {
                case "1": {
                    //now writing to the file
                    Customer c1 = (Customer) is.readObject();
//                    writeObjectToFile(c1);
                    c1.writeObjectToFile(c1);
                }
                break;
                case "2": {
//                	System.out.println("View Customer");
                    //Reading or viewing all data in customer file
                	Customer c1=new Customer();
                    os.writeObject(c1.readAllData());
                }
                break;
                case "3": {
                    //Searching by name
                	Customer c1= new Customer();
                    String name = (String) is.readObject();
                    ArrayList<Customer> getName=c1.search(name);
                    os.writeObject(getName);
                }
                break;
                default:
                    System.out.println("You sent me the wrong option from Client. I am in Server Customer");
            }
//            os.reset();
                
            }
            break;
            case "3":{
            	//ORDERS MENU
            	String option2= br.readLine();
              switch (option2) {
              case "1": {
                  //now writing to the file
                  Order o1 = (Order) is.readObject();
                  o1.writeObjectToFile(o1);
                  //writeObjectToFile(d1);
              }
              break;
              case "2": {
                  //View All Orders
            	  Order o1=new Order();
            	  os.writeObject(o1.readAllData());
              }
              break;
              }
            }
            break;
            case "4": {
                //exit the System
                System.out.println("Exiting...");
                System.exit(0);
            }
            break;
            
            }
        }
    }
//  public static ArrayList<Dress> search(String name) {
//  ArrayList<Dress> resultList = new ArrayList<>();
//  ArrayList<Dress> list = readAllData();
//  for (int i = 0; i < list.size(); i++) {
//      if (list.get(i).getName().equalsIgnoreCase(name)) {
//          resultList.add(list.get(i));
//      }
//  }
//  return resultList;
//}
//
//public static void writeObjectToFile(Dress s) {
//
//  ObjectOutputStream outputStream = null;
//  try {
//      ArrayList<Dress> DressArrayList = readAllData();
//      DressArrayList.add(s);
//      outputStream = new ObjectOutputStream(new FileOutputStream("Dress.txt"));
//      for (int i = 0; i < DressArrayList.size(); i++) {
//          outputStream.writeObject(DressArrayList.get(i));
//      }
//  } catch (IOException exp) {
//      System.out.println("IO Exception while opening file");
//  } finally {
//      try {
//          if (outputStream != null) {
//              outputStream.close();
//          }
//      } catch (IOException exp) {
//          System.out.println("IO Exception while closing file");
//      }
//  }
//}
//
//public static ArrayList<Dress> readAllData() {
//  ArrayList<Dress> DressArrayList = new ArrayList<Dress>(0);
//  ObjectInputStream inputStream = null;
//  try {
//      inputStream = new ObjectInputStream(new FileInputStream("Dress.txt"));
//      //we can store in ser in object
//      boolean EOF = false;
//      while (!EOF) {
//      	//whole file is read
//          try {
//              Dress myObj = (Dress) inputStream.readObject();
//              DressArrayList.add(myObj);
//          } catch (ClassNotFoundException e) {
//          } catch (EOFException end) {
//              EOF = true;
//          } catch (IOException e) {
//              e.printStackTrace();
//          }}
//  } catch (FileNotFoundException e) {
//  } catch (IOException e) {
//  } finally {
//  	// cleanup code to close stream if it was opened
//      try {
//          if (inputStream != null) {
//              inputStream.close();
//          }
//      } catch (IOException e) {
//          System.out.println("IO Exception while closing file");
//      }}
//  return DressArrayList;
//}
}