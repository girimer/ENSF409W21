package edu.ucalgary.ensf409;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author Zach Welsh <a href="mailto:zachary.welsh@ucalgary.ca">zachary.welsh@ucalgary.ca</a> 
* @author Girimer Singh <a href="mailto:girimer.singh@ucalgary.ca">girimer.singh@ucalgary.ca</a>
* @version 1.0
* @since 1.0
 */

public interface OrderInfo{

    /**
     * Generates the output file.
     * @param cheapestCombo A formatted String containing the cheapest combination of equipment and its total price.
     * @param request The initial furniture request supplied to the program, as a single String
     */
    public default void orderForm(String request, String cheapestCombo) throws IOException{
        FileWriter out = null;
        try{
            out = new FileWriter("OrderForm.txt");
            out.write("Furniture Order Form \nRequest: ");
            out.write(request);
            out.write("\nItems ordered\n");
            out.write(cheapestCombo);
            DateFormat td = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
            Date now = new Date();
            out.write("\n\nRequest filled: " + td.format(now) + "\n");

        }finally {
            if(out != null){
                out.close();
            }
        }
        //updateInventory();
    }

    /**
     * When an order is placed, removes ordered items from the inventory database.
     */
    public default void updateInventory(Inventory toUpdate, String[] removeIDs){
        //need database connection. so we pass our Inventory object
        toUpdate.updateInventory(removeIDs);
    }

    
    /*public default String suggestedSupplier(String suggestedManufacturers){
        //suggested suppliers only exist as instance variables, so we're already passing them
        //what purpose does this method serve?
        return suggestedManufacturers;
    }*/
}
