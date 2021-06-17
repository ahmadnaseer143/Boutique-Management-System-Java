import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class Dress implements Serializable {

    private String name;
    private int quantity;
    private String size;
    private String color;
    private int price;

//    public Dress(String name, int quantity) {
//        this.name = name;
//        this.quantity = quantity;
//        size = "N/A";
//        color = "N/A";
//        this.price = 0;
//    }
//
//    public Dress(String name, int quantity, String size, String color) {
//        this.name = name;
//        this.quantity = quantity;
//        this.size = size;
//        this.color = color;
//        this.price = 0;
//
//    }
    
    public Dress() {
        this.name = name;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.price = price;

    }

    public Dress(String name, int quantity, String size, String color, int price) {
        this.name = name;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.price = price;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {

        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }
    public String toString() {
    	return "Dress Name: " + name + " Quantity: "+quantity+ " Size: "+size+" Color: "+ color+" Price: "+price ;
    }
    
    public static ArrayList<Dress> search(String name) {
        ArrayList<Dress> resultList = new ArrayList<>();
        ArrayList<Dress> list = readAllData();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(name)) {
                resultList.add(list.get(i));
            }
        }
        return resultList;
    }

    public static ArrayList<Dress> readAllData() {
        ArrayList<Dress> DressArrayList = new ArrayList<Dress>(0);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("Dresses.txt"));
            boolean EOF = false;
            while (!EOF) {
                try {
                    Dress myObj = (Dress) inputStream.readObject();
                    DressArrayList.add(myObj);

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
        return DressArrayList;
    }



    public void writeObjectToFile(Dress s) {
//        System.out.println("object is: ");
//        System.out.println(s.getName());
        ObjectOutputStream outputStream = null;
        try {
            ArrayList<Dress> DressList = readAllData();
            DressList.add(s);
            outputStream = new ObjectOutputStream(new FileOutputStream("Dresses.txt"));
            for (int i = 0; i < DressList.size(); i++) {
                outputStream.writeObject(DressList.get(i));
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

//    public static  void updateInventory(ArrayList<Dress> s) {
//        // Code for writing data to file
//        ObjectOutputStream outputStream = null;
//
//        try {
//
//            // Append new object into existing list
//            outputStream = new ObjectOutputStream(new FileOutputStream("Dresses.txt"));
//
//            // overwrite into the file
//            for (int i = 0; i < s.size(); i++) {
//                outputStream.writeObject(s.get(i));
//            }
//
//        } catch (IOException exp) {
//            System.out.println("IO Exception while opening file");
//        }
//
//        finally {
//            // cleanup code which closes output stream if its object was created
//            try {
//                if (outputStream != null) {
//                    outputStream.close();
//                    // flag of success
//
//                }
//
//            } catch (IOException exp) {
//                System.out.println("IO Exception while closing file");
//            }
//        }
//
//    }
}
