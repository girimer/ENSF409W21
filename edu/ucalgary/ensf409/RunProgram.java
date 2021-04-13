mport java.util.Scanner;


public class RunProgram {

        public static void main(String[] args){
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

                //Inventory myTable = new Inventory(databaseURL,userName, password);
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
                                System.out.println("Please select the type of chair: ");
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
                        default:
                                System.out.println("Invalid input");
                                break;
                }
                        System.out.println("Would you like to continue, Y/N");
                        if (input.next().equals("N")){
                                exit = true;
                        }
                } while (!exit);


               /* String temp =myTable.stockInventory("lamp");
                System.out.println(temp);
                System.out.println();
                Chair MyOrder= new Chair("lamp", "Swing_Arm", myTable, 3);
                MyOrder.RequestedType();
                System.out.println(MyOrder.chairManufacturer());*/
        }

}
