
import java.util.ArrayList;
import java.util.Arrays;

public class Furniture {
    String tableName;
    String type;
    int numberOfUnit;
    Inventory stockDetails;

    /**
     * This constructor takes 3 parameter.
     *
     * @param tableName     Name of the database table
     * @param type          Type of item to ordering.
     * @param stock         To access database for available inventory
     * @param numberOfUnit  Number of units ordered.
     */
    public Furniture(String tableName, String type, Inventory stock, int numberOfUnit){
        this.stockDetails= stock;
        this.tableName= tableName;
        this.type= type;
        this.numberOfUnit=numberOfUnit;
    }

    /**
     * Method uses Inventory variable to get the inventory of ordered type from a database table.
     * and find all possible combinations for ordered type of item possible.
     */
    public void RequestedType(){
        ArrayList<String> Items = new ArrayList<>();
        String temp = stockDetails.stockInventory(tableName);
        String [] eachLine= temp.split("\n");                   //split the string output by new line of stockInventory method accessed from Inventory class.

        for (int i=0; i< eachLine.length; i++){
            String [] temp3= eachLine[i].split(" ");            //split every array element by spaces to check stock type.
            if(temp3[1].equals(this.type)){                           //check for the type of inventory, only type requested is added to ArrayList.
                Items.add(eachLine[i]);
            }
        }

        String [] typeOrdered = Items.toArray(new String[0]);        // ArrayList is converted to String array to pass as an argument to combinations method.

        ArrayList<String> temp10 = new ArrayList<>();               // String array is passed to combinations method to all possible combinations,
        for (int i=numberOfUnit; i<= typeOrdered.length; i++) {     // methods return all possible combinations ranging to min order quantity to max possible based on inventory.
            combinations(typeOrdered, i, 0, new String[i], temp10); //Example if 2 units of chair task are requested, output is all combination of 2 task ID's then combinations of 3 task ID's so on.
        }                                                                 // C3405 and C0914, ....., C3405 and C0914 and C1148

       System.out.println();

        if (temp10.isEmpty()){                                      // if no combination are possible based on ordered quantity output message.
            System.out.println("Order not possible");
           return;
        }
        else{
        fullUnits(temp10, numberOfUnit);}                           // if combinations are possible pass ArrayList of combinations to full units function to check if full units can be possibly
                                                                    // built from them.

    }

    /**
     * Methods check how many full units can be built based on ArrayList of combinations
     * Methods based on units ordered find all combinations that results in that many full units.
     */
    public void fullUnits(ArrayList<String> AllInventoryType, int numberOfUnits) {
        String returnString;
        StringBuilder order = new StringBuilder();
        int fullUnits=10000000;
        int temp = 0;
        int totalPrice=0;
        int size2;
        String [] AllItems = AllInventoryType.get(0).split(",");      //Splits ArrayList to String array
        int size1 = AllItems[0].split(" ").length;                    //Splitting String array elements gives size of each line, 5 in case of Lamp table as table has 5 columns, ID, type, Base, Bulb, Price, ManuID
        int loopiterations= size1-2;                                        //Price and ManuID is not needed to find if full units can be built from combination of ordered type item.
                                                                            // Loop iterates upto Bulb column in case of Lamp table

        for (int i = 0; i < AllInventoryType.size(); i++) {                 //loop goes through ArrayList to check combinations

            String[] elements = AllInventoryType.get(i).split(" ");   // All combinations are split into String Array individual elements.
            size2 = elements.length;                                        //size of this String gives information whether its combination of 1 Desk lamp, 2 Desk lamps or more IDs.

            for (int j=2; j<loopiterations; j++) {                          //Checking elements from 2 and onwards, in case of lamp, only Base and Bulb columns are checked for combinations of item to
                                                                            //get how many full units can be possibly built.
                for (int k = j; k < size2; ) {                              //Checks each piece of item to check if its Y or N,
                    if (elements[k].equals("Y")) {                          //if its Y means that piece can be used in building a full units
                        temp = temp + 1;
                    }
                    k = k + size1;                                          // jumps so that it checks same piece from different ID's. example L132 lamp for base, then loop checks L342 for base
                }                                                           // checks for bulb in L132 and then bulb in L342, If both has that piece 2 units possible
                if(fullUnits>temp){                                         // Checks pieces available to find full units that can be built. If L132 and L342 has base, full units will be 2 but if
                    fullUnits=temp;                                         // L132 doesn't have bulb then then full units that can be build is 1.
                }
                temp=0;                                                     // resetting temp variable to keep checking other pieces.
            }


            if(fullUnits==numberOfUnits) {
                order.append(fullUnits);                                    // all combinations that results in full units equal to order quantity are added to StringBuilder variable
                order.append(" ");
                                                                            // other details, total price, unit ID's are added as well.
                for (int l = size1 - 2; l < size2; ) {
                    totalPrice = totalPrice + Integer.valueOf(elements[l]);
                    l = l + size1;
                }
                order.append(totalPrice);
                order.append(" ");

                for (int l = 0; l < size2; ) {
                    order.append(elements[l]);
                    order.append(" ");
                    l = l + size1;
                }
                order.append('\n');
            }

            fullUnits=10000000;                                             // resetting variable to check next combination
            totalPrice=0;
        }

        if(order.length()==0){                                              // After checking if order variable is empty, then a message is output
            System.out.println("Not enough stock");
            return;
        }
       else {                                                               // If order variable is not empty then is passed to cheapestOption methods to check for cheapest option.
            returnString = cheapestOption(order.toString());
            System.out.println("Return string: " + returnString);

        }
    }


    public String cheapestOption(String AvailableChoices){
        String [] temp= AvailableChoices.split("\n");
        int cheapest =1000000;
        String returnValue = null;
        System.out.println();

        for(int i=0; i<temp.length; i++){                                   // Loop checks for total price of each full unit combination and returns cheapest option
            String []temp2 = temp[i].split(" ");
            if(cheapest > Integer.valueOf(temp2[1]) ){
                returnValue= temp[i];
                cheapest= Integer.valueOf(temp2[1]);
            }
        }

        return returnValue.replace("[", "");
    }


    /**
     * Methods using recursion makes all possible combinations of array of items
     * @param Items all items to build combinations of.
     * @param len length of combinations whether combinations of 2 elements or 3 elements.
     * @param begin index of array to begin making combinations
     * @param result to store combinations, String array is same size as len,
     * @param returnList to add result to ArrayList to be used in other methods.
     *
     */

    public void combinations(String [] Items, int len, int begin, String [] result, ArrayList<String> returnList){
        if (len == 0){                                                              // Base case
            //System.out.println(Arrays.toString(result));
            returnList.add(Arrays.toString(result));
            return;
        }
        for (int i = begin; i <= Items.length-len; i++){                            // iterating over all array elements
            result[result.length - len] = Items[i];                                 // adding elements to result
            combinations(Items, len-1, i+1, result, returnList);          // recursive call progressing towards base case, adding elements to result array
        }
    }

}

class Chair extends Furniture{
    private String SuggestedManufacturers;

    public Chair(String tableName, String type, Inventory stock, int numberOfUnit) {
        super(tableName, type, stock, numberOfUnit);
    }

    public String chairManufacturer(){
        return super.stockDetails.manufacturers("chair");
    }

    public String getSuggestedManufacturers() {
        return SuggestedManufacturers;
    }

    public void setSuggestedManufacturers(String suggestedManufacturers) {
        SuggestedManufacturers = suggestedManufacturers;
    }
}

class Desk extends Furniture{
    private String SuggestedManufacturers;

    public Desk(String tableName, String type, Inventory stock, int numberOfUnit) {
        super(tableName, type, stock, numberOfUnit);
    }

    public String DeskManufacturer(){
        return super.stockDetails.manufacturers("desk");
    }

    public String getSuggestedManufacturers() {
        return SuggestedManufacturers;
    }

    public void setSuggestedManufacturers(String suggestedManufacturers) {
        SuggestedManufacturers = suggestedManufacturers;
    }
}

class Lamp extends Furniture{
    private String SuggestedManufacturers;

    public Lamp(String tableName, String type, Inventory stock, int numberOfUnit) {
        super(tableName, type, stock, numberOfUnit);
    }

    public String DeskManufacturer(){
        return super.stockDetails.manufacturers("lamp");
    }

    public String getSuggestedManufacturers() {
        return SuggestedManufacturers;
    }

    public void setSuggestedManufacturers(String suggestedManufacturers) {
        SuggestedManufacturers = suggestedManufacturers;
    }
}


class filling extends Furniture{
    private String SuggestedManufacturers;

    public filling(String tableName, String type, Inventory stock, int numberOfUnit) {
        super(tableName, type, stock, numberOfUnit);
    }

    public String DeskManufacturer(){
        return super.stockDetails.manufacturers("filling");
    }

    public String getSuggestedManufacturers() {
        return SuggestedManufacturers;
    }

    public void setSuggestedManufacturers(String suggestedManufacturers) {
        SuggestedManufacturers = suggestedManufacturers;
    }
}


