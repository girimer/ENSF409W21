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
     * @param cheapestCombo An array of type furniture containing the cheapest combination of used furniture for the application.
     * @param request The initial furniture request supplied to the program, as a single String
     */
    public default void orderForm(String request, Furniture[] cheapestCombo) throws IOException{
        FileWriter out = null;
        try{
            out = new FileWriter("OrderForm.txt");
            out.write("Furniture Order Form \nRequest: ");
            out.write(request);
            out.write("\nItems ordered");
            for(int i=0; i<cheapestCombo.length; i++){
                out.write("\nID:");
                out.write(cheapestCombo[i].ID); //to implement: show price, department, contact
            }
            DateFormat td = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
            Date now = new Date();
            out.write("\n\nRequest filled: " + td.format(now) + "\n");

        }finally {
            if(out != null){
                out.close();
            }
        }
    }

    /**
     * When an order is placed, removes ordered items from the inventory database.
     */
    public default void updateInventory(){
        //need database connection. are we adding one to this interface?
    }

    /**
     * Returns a suggested supplier for a piece of furniture.
     * @return A String containing the name of the suggested supplier for the requested item.
     */
    public default String suggestedSupplier(Furniture[] cheapestCombo){
        //also needs database connection
        //uses database info to return the manufacturer associated with each furniture's manuID
        return "";
    }
}
