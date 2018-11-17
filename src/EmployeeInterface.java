public static class EmployeeInterface
{
    public static void updateItem(){
        //TODO
        // updateInv(item);
    }

    public static void restockItem(){
        //TODO
        // updateInv(item);
    }

    private static void updateInv(Item newItem){
        Database database = new Database();
        List<Item> inventory = database.getInventoryList();
        int i = 0;
        boolean found = false;
        while(!found && i < inventory.size()){
            found = inventory.get(i).name.toUpperCase().equals(newItem.name.toUpperCase());
            i++;
        }
        if(found) {
            inventory.set(i, newItem);
        }
        else {
            inventory.add(newItem);    
        }
        database.setInventoryList(inventory);
    }
}
