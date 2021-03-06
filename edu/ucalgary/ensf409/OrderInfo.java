package edu.ucalgary.ensf409;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author Zach Welsh <a href="mailto:zachary.welsh@ucalgary.ca">zachary.welsh@ucalgary.ca</a> 
* @author Girimer Singh <a href="mailto:girimer.singh@ucalgary.ca">girimer.singh@ucalgary.ca</a>
* @version 1.5
* @since 1.0
 */

public class OrderInfo{


    /**
    * Default constructor 
    */
    public OrderInfo(){
        //no instance variables to create
    }

     /**
     * Generates the output file.
     * @param table Furniture item which was ordered.
     * @param typeOfItem Function of ordered item.
     * @param quantity Quantity ordered.
     * @param cheapestCombo A formatted string containing the cheapest combination of equipment to fulfill the order,
     * including their IDs and total price.
     */
    public void orderForm(String table, String typeOfItem, int quantity, String cheapestCombo) throws IOException{
        FileWriter out = null;
        try{
            out = new FileWriter("OrderForm.txt");
            File file = new File("OrderForm.txt");
            out.write("Furniture Order Form \n\nOriginal Order: ");
            out.write(typeOfItem+" "+ table+"\n"+"Quantity requested: "+quantity);
            DateFormat td = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
            Date now = new Date();
            out.write("\n\nRequest filled: " + td.format(now) + "\n");
            out.write("\nItems Ordered\n");
            String [] ids = cheapestCombo.split(" ");
            System.out.println("Order complete: ");
            for (int index=2; index<ids.length;index++)
                { out.write("ID:"+ ids[index]+"\n");
                    System.out.println("ID: "+ ids[index]);
                }

            out.write("Quantity fulfilled: "+quantity+"\n");
            System.out.println("Order Quantity: "+ quantity);
            out.write("Total cost: $"+ cheapestCombo.split(" ", 3)[1]+"\n\n");
            System.out.println("Total cost: $" + cheapestCombo.split(" ", 3)[1]);
            out.write("Faculty: Schulich School of Engineering\n");
            out.write("Contact: Zach Welsh, Girimer Singh\n\n");

            System.out.println("Order form saved in "+ file.getAbsolutePath()+"\n");


        }finally {
            if(out != null){
                out.close();
            }
        }
    }


}
