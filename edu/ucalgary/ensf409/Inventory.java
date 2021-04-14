package edu.ucalgary.ensf409;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Zach Welsh <a href="mailto:zachary.welsh@ucalgary.ca">zachary.welsh@ucalgary.ca</a>
 * @author Girimer Singh <a href="mailto:girimer.singh@ucalgary.ca">girimer.singh@ucalgary.ca</a>
 * @version 1.6
 * @since 1.0
 */

public class Inventory{
    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Connection dbConnect;
    private ResultSet results;



    /**
     * This constructor takes 3 parameters.
     * @param DBURL Database URL String.
     * @param USERNAME Database access Username String.
     * @param PASSWORD Database access Password String.
     */
    public Inventory(String DBURL, String USERNAME, String PASSWORD){
        this.DBURL= DBURL;
        this.USERNAME= USERNAME;
        this.PASSWORD =PASSWORD;

    }

    /**
     * This is a default constructor.
     */

    public Inventory() {
        this.DBURL = null;
        this.PASSWORD = null;
        this.USERNAME=null;
    }

    //getter and setter methods
    public String getDBURL() {
        return DBURL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setDbConnect(Connection dbConnect) {
        this.dbConnect = dbConnect;
    }

    public void setResults(ResultSet results) {
        this.results = results;
    }

    public ResultSet getResults() {
        return results;
    }


    /**
     * Method initializes connection to provided database with credentials.
     *
     */
    public void initializeConnection(){

        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returns stock details from the database
     * @param tableName Name of the table to get data from.
     * @return A formatted String containing all stock details.
     */
    public String stockInventory (String tableName) {
        StringBuffer allStock = new StringBuffer();
        int i = 0;
        if (tableName.equals("chair")) {
            try {
                Statement myStmt = dbConnect.createStatement();
                results = myStmt.executeQuery("SELECT * FROM " + tableName);

                while (results.next()) {
                    if (i > 0) {
                        allStock.append('\n');
                    }
                    allStock.append(results.getString("ID") + " " + results.getString("Type").replace(" ", "_")+ " " +results.getString("Legs")
                            + " " +results.getString("Arms")+" " + results.getString("Seat")+" " + results.getString("Cushion")+" " +
                            results.getString("Price") +" " + results.getString("ManuID"));
                    i++;
                }

                myStmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        else if (tableName.equals("desk")){
            try {
                Statement myStmt = dbConnect.createStatement();
                results = myStmt.executeQuery("SELECT * FROM " + tableName);

                while (results.next()) {
                    if (i > 0) {
                        allStock.append('\n');
                    }
                    allStock.append(results.getString("ID") + " " + results.getString("Type")+ " " +results.getString("Legs")
                            + " " +results.getString("Top")+" " + results.getString("Drawer")+" " + results.getString("Price")
                            +" " + results.getString("ManuID"));
                    i++;
                }

                myStmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        else if (tableName.equals("lamp")){
            try {
                Statement myStmt = dbConnect.createStatement();
                results = myStmt.executeQuery("SELECT * FROM " + tableName);

                while (results.next()) {
                    if (i > 0) {
                        allStock.append('\n');
                    }
                    allStock.append(results.getString("ID") + " " + results.getString("Type").replace(" ","_")+ " " +results.getString("Base")
                            + " " +results.getString("Bulb")+" " + results.getString("Price")
                            +" " + results.getString("ManuID"));
                    i++;
                }

                myStmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        else if (tableName.equals("filing")){
            try {
                Statement myStmt = dbConnect.createStatement();
                results = myStmt.executeQuery("SELECT * FROM " + tableName);

                while (results.next()) {
                    if (i > 0) {
                        allStock.append('\n');
                    }
                    allStock.append(results.getString("ID") + " " + results.getString("Type")+ " " +results.getString("Rails")
                            + " " +results.getString("Drawers")+ " " +results.getString("Cabinet")
                            +" " + results.getString("Price")+" " + results.getString("ManuID"));
                    i++;
                }

                myStmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        else if (tableName.equals("Manufacturer")){
            try {
                Statement myStmt = dbConnect.createStatement();
                results = myStmt.executeQuery("SELECT * FROM " + tableName);

                while (results.next()) {
                    if (i > 0) {
                        allStock.append('\n');
                    }
                    allStock.append(results.getString("ManuID") + " " + results.getString("Name")+ " " +results.getString("Phone")
                            + " " +results.getString("Province"));
                    i++;
                }

                myStmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else {                                  // if table name provided is above listed tables, system exit
            System.exit(1);
        }

        return allStock.toString();
    }

    /**
     * Method returns manufacturers from the database.
     * @param tableName Name of table to get manufacturer information from.
     * @return String listing manufacturers with their names, phone and province.
     */

   public String manufacturers(String tableName){
        String temp =stockInventory(tableName);
        ArrayList <String> allManufacturerID= new ArrayList<>();
        String allManufacturer= "";
        String manufacturerDetails;

        String [] data = temp.split("\n");
        int wordInLine= temp.split("\n")[0].split(" ").length;

        for (int i= 0; i< data.length; i++ ){
            allManufacturerID.add( data[i].split(" ")[wordInLine-1]);
        }

        Set<String> set = new HashSet<>(allManufacturerID);           // Removing duplicate manufacturer IDs using set
        allManufacturerID.clear();
        allManufacturerID.addAll(set);

        manufacturerDetails= stockInventory("Manufacturer");
        String [] temp2 = manufacturerDetails.split("\n");

        for(int j=0; j< allManufacturerID.size(); j++){
            for(int k=0; k<temp2[0].split(" ").length; k++){
                if(allManufacturerID.get(j).equals(temp2[k].split(" ")[0])){
                    allManufacturer= allManufacturer+temp2[k].substring(4);
                    allManufacturer= allManufacturer+'\n';
                }
            }
        }
        allManufacturer=allManufacturer.trim();
        return allManufacturer;
    }

    /**
     * Method deletes an ID from database.
     * @param table Name of the table to delete the ID from
     * @param ID ID to delete
     */
    public void deleteID(String table, String ID){
        try {
            String query = "DELETE FROM "+table+ " WHERE ID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, ID);
            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method adds ID to filing table.
     * @param ID Info for the table's ID column
     * @param type Info for Type column
     * @param rails Info for Rails column
     * @param drawers Info for Drawers column
     * @param cabinet Info for Cabinet column
     * @param price Info for Price column
     * @param manuID Info for ManuID column
     */
    public void addIDtoFiling (String ID, String type, String rails, String drawers, String cabinet, int price, String manuID){

        try {
            String setQuery = "INSERT INTO filing (ID, Type, Rails, Drawers, Cabinet, Price, ManuID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(setQuery);

            myStmt.setString(1, ID);
            myStmt.setString(2, type);
            myStmt.setString(3, rails);
            myStmt.setString(4, drawers);
            myStmt.setString(5, cabinet);
            myStmt.setInt(6, price);
            myStmt.setString(7, manuID);

            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
