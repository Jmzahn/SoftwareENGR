import java.util.List;
import java.util.Scanner;

public class EmployeeInterface//I added a static Database to DatabaseInterface that you can pull via get method, and push via set
{
    public static void welcome(){
        Scanner sel=new Scanner(System.in);
        System.out.print("Welcome to the Employee Interface!\nSelect 1 if you are a restocker, or 2 if you are a manager : ");
        int wut=sel.nextInt();
        if(wut==1)
            restockItem();
        else if(wut==2)
            updateItem();
        System.out.println("Leaving Employee Interface!");
        sel.close();
    }
    //Database database = DatabaseInterface.getDatabase();
    private static void updateItem(){
        Scanner kb = new Scanner(System.in);
        Database database = DatabaseInterface.getDatabase();
        boolean cont=true;
        while(cont)
        {
            System.out.println("Enter the name of the item:");
            String itemName = kb.nextLine();
            Item item = getItem(itemName, database);
            if(item == null)
            {
                System.out.println("Item not found. Create new item:");

                System.out.println("Enter the item description:");
                String description = kb.nextLine();

                System.out.println("Enter the item price:");
                double price = kb.nextDouble();
                kb.nextLine();

                System.out.println("Enter the item discount (as a number from 0 to 1, e.g. 10% = 0.10):");
                double discount = kb.nextDouble();
                kb.nextLine();

                System.out.println("Enter the item inventory quantity:");
                int quantity = kb.nextInt();
                kb.nextLine();

                System.out.println("Enter whether or not the item is alcohol (true/false)");
                String isAlchStr = kb.nextLine();
                boolean isBooze = Boolean.parseBoolean(isAlchStr);

                System.out.println("Enter the item inventory minimum:");
                int invMininum = kb.nextInt();
                kb.nextLine();

                item = new Item();
                item.name = itemName;
                item.description = description;

                item.price = price;
                item.discount = discount;
                item.quantity = quantity;
                item.isBooze = isBooze;
                item.invMinimum = invMininum;

                updateInv(item, database);
            }
            else{
                System.out.println("Item found. Update item information:");
                System.out.println("Current item status: " + item.toString());

                System.out.println("Would you like to delete the item? (y/n):");
                if(kb.nextLine().toUpperCase().startsWith("Y")){
                    List<Item> list = database.getInventoryList();
                    list.remove(item);
                    break;
                }
                System.out.println("Enter the item description:");

                System.out.println("(Press Enter to leave unchanged)");

                String description = kb.nextLine();
                if(description != null){
                    item.description = description;
                }

                System.out.println("Enter the item price:");
                double price = kb.nextDouble();
                kb.nextLine();

                System.out.println("Enter the item discount (as a number from 0 to 1, e.g. 10% = 0.10):");
                double discount = kb.nextDouble();
                kb.nextLine();

                System.out.println("Enter the item inventory quantity:");
                int quantity = kb.nextInt();
                kb.nextLine();

                System.out.println("Enter whether or not the item is alcohol (true/false)");
                String isAlchStr = kb.nextLine();
                boolean isBooze = Boolean.parseBoolean(isAlchStr);

                System.out.println("Enter the item inventory minimum:");
                int invMininum = kb.nextInt();
                kb.nextLine();

                item.price = price;
                item.discount = discount;
                item.quantity = quantity;
                item.isBooze = isBooze;
                item.invMinimum = invMininum;

                updateInv(item, database);
            }
            System.out.print("Are you done? (y/n) : ");
            if(kb.nextLine().toUpperCase().startsWith("Y")){
                cont=false;
            }
        }
    }

    private static void restockItem(){
        Scanner kb = new Scanner(System.in);
        Database database = DatabaseInterface.getDatabase();
        boolean cont=true;
        while(cont)
        {
            System.out.println("Enter the name of the item:");
            String itemName = kb.nextLine();
            Item item = getItem(itemName, database);

            if(item == null){
                System.out.println("Item not found. Unable to restock.");
                return;
            }

            System.out.println("Enter the new quantity in inventory:");
            int quantity = kb.nextInt();
            kb.nextLine();

            item.quantity = quantity;
            updateInv(item, database);

            System.out.print("Are you done? (y/n) : ");
            if(kb.nextLine().toUpperCase().startsWith("Y")){
                cont=false;
            }
        }

    }

    private static Item getItem(String itemName, Database database){
        List<Item> inventory = database.getInventoryList();
        int i = 0;
        boolean found = false;
        while(!found && i < inventory.size()){
            found = inventory.get(i).name.toUpperCase().equals(itemName.toUpperCase());
            if(!found) i++;
        }
        if(found){
            return inventory.get(i);
        }
        return null;
    }

    private static void updateInv(Item item, Database database){
        List<Item> inventory = database.getInventoryList();
        int i = 0;
        boolean found = false;
        while(!found && i < inventory.size()){
            found = inventory.get(i).name.toUpperCase().equals(item.name.toUpperCase());
            if(!found) i++;
        }
        if(found) {
            inventory.set(i, item);
        }
        else {
            inventory.add(item);    
        }
        database.setInventoryList(inventory);
    }
}
