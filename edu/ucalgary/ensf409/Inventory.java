/**
 * @author Zach Welsh <a href="mailto:zachary.welsh@ucalgary.ca">zachary.welsh@ucalgary.ca</a>
 * @author Girimer Singh <a href="mailto:girimer.singh@ucalgary.ca">girimer.singh@ucalgary.ca</a>
 * @version 1.5
 * @since 1.0
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Inventory{
    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Connection dbConnect;
    private ResultSet results;



    /**
     * This constructor takes 3 parameter.
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
     * This is default constructor.
     *
     */

    public Inventory() {
        this.DBURL = null;
        this.PASSWORD = null;
        this.USERNAME=null;
    }

    /**
     * Getter and setter methods
     */
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
     * Method initialize connection to provided database with credential.
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
     * Method returns stock details from database
     * @param tableName name of the table to get data from.
     * @return returns String containing all stock details.
     */
    public String stockInventory (String tableName) {
        StringBuffer AllStock = new StringBuffer();
        int i = 0;
        if (tableName.equals("chair")) {
            try {
                Statement myStmt = dbConnect.createStatement();
                results = myStmt.executeQuery("SELECT * FROM " + tableName);

                while (results.next()) {
                    if (i > 0) {
                        AllStock.append('\n');
                    }
                    AllStock.append(results.getString("ID") + " " + results.getString("Type").replace(" ", "_")+ " " +results.getString("Legs")
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
                        AllStock.append('\n');
                    }
                    AllStock.append(results.getString("ID") + " " + results.getString("Type")+ " " +results.getString("Legs")
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
                        AllStock.append('\n');
                    }
                    AllStock.append(results.getString("ID") + " " + results.getString("Type").replace(" ","_")+ " " +results.getString("Base")
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
                        AllStock.append('\n');
                    }
                    AllStock.append(results.getString("ID") + " " + results.getString("Type")+ " " +results.getString("Rails")
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
                        AllStock.append('\n');
                    }
                    AllStock.append(results.getString("ManuID") + " " + results.getString("Name")+ " " +results.getString("Phone")
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

        return AllStock.toString();
    }

    /**
     * Method returns manufacturers from the database
     * @param tableName name of table to get manufacturer information
     * @return String with manufacturer with their names, phone and province.
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
     * @param table Name of the table to delete ID from
     * @param ID id to delete
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
     * Method add Id to filling table.
     * @param ID, Type, Rails, Drawers, Cabinet, Price, ManuId: all table columns information
     */
    public void addIDtoFiling (String ID, String Type, String Rails, String Drawers, String Cabinet, int Price, String ManuID){

        try {
            String setQuery = "INSERT INTO filing (ID, Type, Rails, Drawers, Cabinet, Price, ManuID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(setQuery);

            myStmt.setString(1, ID);
            myStmt.setString(2, Type);
            myStmt.setString(3, Rails);
            myStmt.setString(4, Drawers);
            myStmt.setString(5, Cabinet);
            myStmt.setInt(6, Price);
            myStmt.setString(7, ManuID);

            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}





