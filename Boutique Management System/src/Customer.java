import java.io.*;
import java.util.ArrayList;
public class Customer implements Serializable {
    private String name;
    private int id;
    private String contactNo;
    public Customer(){
        this.name = name;
        this.id = id;
        this.contactNo = contactNo;
    }
    public Customer(String name, int id, String contactNo){
        this.name = name;
        this.id = id;
        this.contactNo = contactNo;
    }
// Getter Methods
    public int getId() {
        return id;
    }
    public String getContactNo() {
        return contactNo;
    }
    public String getName() {
        return name;
    }
// Setter Methods
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
    	return "Customer Name: " + name + " Customer ID: "+id+" Contact: "+ contactNo ;
    }
    
    public static ArrayList<Customer> search(String name) {
        ArrayList<Customer> resultList = new ArrayList<>();
        ArrayList<Customer> list = readAllData();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(name)) {
                resultList.add(list.get(i));
            }
        }
        return resultList;
    }

    public static ArrayList<Customer> readAllData() {
        ArrayList<Customer> CustomerArrayList = new ArrayList<Customer>(0);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("Customer.txt"));
            boolean EOF = false;
            while (!EOF) {
                try {
                    Customer myObj = (Customer) inputStream.readObject();
                    CustomerArrayList.add(myObj);

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
        return CustomerArrayList;
    }



    public void writeObjectToFile(Customer s) {
//        System.out.println("object is: ");
//        System.out.println(s.getName());
        ObjectOutputStream outputStream = null;
        try {
            ArrayList<Customer> CustomerArrayList = readAllData();
            CustomerArrayList.add(s);
            outputStream = new ObjectOutputStream(new FileOutputStream("Customer.txt"));
            for (int i = 0; i < CustomerArrayList.size(); i++) {
                outputStream.writeObject(CustomerArrayList.get(i));
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
