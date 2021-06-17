import java.io.*;
import java.util.ArrayList;
public class Order implements Serializable {
    Customer customer;
    Dress DressesPurchased;
    String dateOfPurchase;
    
    public Order(Customer customer, Dress DressesPurchased, String dateOfPurchase) {
//        Customer customer1 = new Customer(customer.getName(), customer.getId(), customer.getContactNo());
        this.customer = customer;
        this.dateOfPurchase = dateOfPurchase;
        this.DressesPurchased = DressesPurchased;
    }

    public Order() {
    	this.customer = customer;
        this.dateOfPurchase = dateOfPurchase;
        this.DressesPurchased = DressesPurchased;
	}

	public Dress getDressesPurchased() {
        return DressesPurchased;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setDressesPurchased(Dress DressesPurchased) {
        this.DressesPurchased = DressesPurchased;
    }
    
    public String toString() {
    	return "Customer: " + getCustomer() + " Dress "+getDressesPurchased()+ " Date: "+dateOfPurchase ;
    }

    public static ArrayList<Order> readAllData() {
        ArrayList<Order> OrderArrayList = new ArrayList<Order>(0);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("Order.txt"));
            boolean EOF = false;
            while (!EOF) {
                try {
                    Order myObj = (Order) inputStream.readObject();
                    OrderArrayList.add(myObj);

                } catch (ClassNotFoundException e) {
                } catch (EOFException end) {
                    EOF = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally { // cleanup code to close stream if it was opened
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
// TODO Auto-generated catch block
                System.out.println("IO Exception while closing file");
            }
        }
        return OrderArrayList;
    }



    public static void writeObjectToFile(Order s) {
//        System.out.println("object is: ");
//        System.out.println(s.getName());
        ObjectOutputStream outputStream = null;
        try {
            ArrayList<Order> OrderArrayList = readAllData();
            OrderArrayList.add(s);
            outputStream = new ObjectOutputStream(new FileOutputStream("Order.txt"));
            for (int i = 0; i < OrderArrayList.size(); i++) {
                outputStream.writeObject(OrderArrayList.get(i));
            }
        } catch (IOException exp) {
            System.out.println("IO Exception while opening file");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException exp) {
                System.out.println("IO Exception while closing file");
            }
        }
    }
}
