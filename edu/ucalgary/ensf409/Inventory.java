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

    public Inventory() {
        this.DBURL = null;
        this.PASSWORD = null;
        this.USERNAME=null;
    }

    //getter methods
    public String getDBURL() {
        return DBURL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getUSERNAME() {
        return USERNAME;
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

        else if (tableName.equals("Filing")){
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

        return AllStock.toString();
    }


    String manufacturers(String tableName){
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
                    for(int l=1; l<temp2[0].split(" ").length-2; l++) {
                        allManufacturer = allManufacturer+((temp2[k].split(" ")[l]));
                        allManufacturer=allManufacturer+ " ";
                    }
                    allManufacturer= allManufacturer+'\n';
                }
            }
        }

        return allManufacturer;
    }


}



