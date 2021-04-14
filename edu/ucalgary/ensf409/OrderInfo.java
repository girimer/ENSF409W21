//package edu.ucalgary.ensf409; //this causes a compilation error for me.
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author Zach Welsh <a href="mailto:zachary.welsh@ucalgary.ca">zachary.welsh@ucalgary.ca</a> 
* @author Girimer Singh <a href="mailto:girimer.singh@ucalgary.ca">girimer.singh@ucalgary.ca</a>
* @version 1.4
* @since 1.0
 */

public class OrderInfo{

    private String request;
    private String cheapestCombo;
    private Inventory toUpdate;
    private String[] removeIDs;
    private String suggestedSuppliers;

    //need constructor and instance variables to reduce number of arguments
    public OrderInfo(String request, String cheapestCombo, Inventory invToUpdate, String[] cheapestIDs, 
            String suggestedManufacturers){
        this.request = request;
        this.cheapestCombo = cheapestCombo;
        this.toUpdate = invToUpdate;
        this.removeIDs = cheapestIDs; //shallow copy
        this.suggestedSuppliers = suggestedManufacturers;
    }

    /**
     * Generates the output file.
     */
    public void orderForm() throws IOException{
        FileWriter out = null;
        try{
            out = new FileWriter("OrderForm.txt");
            out.write("Furniture Order Form \nRequest: ");
            out.write(request);
            out.write("\nItems ordered\n");
            out.write(cheapestCombo);
            out.write("\nSuggested suppliers are: ");
            out.write(suggestedSuppliers);
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
     * Current implementation is hardcoded for 4 types of furniture: chairs, desks, lamps and
     * filing cabinets. Must be called after an order is completed.
     */
    public void updateInventory(){
        //need database connection, so we pass our Inventory object
        //each type of furniture has a unique ID format, so just call deleteID with each type
        for(int i=0; i<removeIDs.length; i++){
            toUpdate.deleteID("CHAIR",removeIDs[i]);
            toUpdate.deleteID("DESK",removeIDs[i]);
            toUpdate.deleteID("LAMP",removeIDs[i]);
            toUpdate.deleteID("FILING",removeIDs[i]);
        }
        
    }
}
