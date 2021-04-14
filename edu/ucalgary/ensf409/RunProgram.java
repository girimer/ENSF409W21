/**
 * @author Zach Welsh <a href="mailto:zachary.welsh@ucalgary.ca">zachary.welsh@ucalgary.ca</a>
 * @author Girimer Singh <a href="mailto:girimer.singh@ucalgary.ca">girimer.singh@ucalgary.ca</a>
 * @version 1.2
 * @since 1.0
 */

import java.io.IOException;
import java.util.Scanner;


public class RunProgram {

        public static void main(String[] args) throws IOException {
                Scanner input = new Scanner(System.in);
                String databaseURL;
                String userName;
                String password;
                System.out.println("Please enter database URL: ");
                databaseURL= input.next();
                System.out.println("Please enter username: ");
                userName= input.next();
                System.out.println("Please enter password: ");
                password=input.next();

              
                Inventory myTable = new Inventory(databaseURL,userName, password);
                myTable.initializeConnection();

                int type;
                int quantity;
                boolean exit= false;

                do {
                        System.out.println("Please select item to order: ");
                        System.out.println("1. Chair");
                        System.out.println("2. Desk");
                        System.out.println("3. Lamp");
                        System.out.println("4. Filing");

                        int table = input.nextInt();

                switch (table) {
                        case 1:
                                System.out.println("Please select the type of Chair: ");
                                System.out.println("1. Kneeling");
                                System.out.println("2. Task");
                                System.out.println("3. Mesh");
                                System.out.println("4. Executive");
                                System.out.println("5. Ergonomics");
                                type = input.nextInt();
                                System.out.println("Please enter the order quantity: ");
                                quantity = input.nextInt();
                                if (type == 1) {
                                        Chair newOrder = new Chair("chair", "Kneeling", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 2) {
                                        Chair newOrder = new Chair("chair", "Task", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 3) {
                                        Chair newOrder = new Chair("chair", "Mesh", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 4) {
                                        Chair newOrder = new Chair("chair", "Executive", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 5) {
                                        Chair newOrder = new Chair("chair", "Ergonomics", myTable, quantity);
                                        newOrder.RequestedType();
                                }
                                break;

                        case 2:
                                System.out.println("Please select the type of Desk: ");
                                System.out.println("1. Standing");
                                System.out.println("2. Adjustable");
                                System.out.println("3. Traditional");
                                type = input.nextInt();
                                System.out.println("Please enter the order quantity: ");
                                quantity = input.nextInt();
                                if (type == 1) {
                                        Chair newOrder = new Chair("desk", "Standing", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 2) {
                                        Chair newOrder = new Chair("desk", "Adjustable", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 3) {
                                        Chair newOrder = new Chair("desk", "Traditional", myTable, quantity);
                                        newOrder.RequestedType();
                                }
                                break;

                        case 3:
                                System.out.println("Please select the type of Lamp: ");
                                System.out.println("1. Desk");
                                System.out.println("2. Study");
                                System.out.println("3. Swing Arm");
                                type = input.nextInt();
                                System.out.println("Please enter the order quantity: ");
                                quantity = input.nextInt();
                                if (type == 1) {
                                        Chair newOrder = new Chair("lamp", "Desk", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 2) {
                                        Chair newOrder = new Chair("lamp", "Study", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 3) {
                                        Chair newOrder = new Chair("lamp", "Swing_Arm", myTable, quantity);
                                        newOrder.RequestedType();
                                }
                                break;

                        case 4:
                                System.out.println("Please select the type of Filing: ");
                                System.out.println("1. Small");
                                System.out.println("2. Medium");
                                System.out.println("3. Large");
                                type = input.nextInt();
                                System.out.println("Please enter the order quantity: ");
                                quantity = input.nextInt();
                                if (type == 1) {
                                        Chair newOrder = new Chair("filing", "Small", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 2) {
                                        Chair newOrder = new Chair("filing", "Medium", myTable, quantity);
                                        newOrder.RequestedType();
                                } else if (type == 3) {
                                        Chair newOrder = new Chair("filing", "Large", myTable, quantity);
                                        newOrder.RequestedType();
                                }
                                break;

                        default:
                                System.out.println("Invalid input");
                                break;
                }
                        System.out.println("Would you like to continue, Y/N");
                        if (input.next().equalsIgnoreCase("N")){
                                exit = true;
                        }
                } while (!exit);
        }
}
