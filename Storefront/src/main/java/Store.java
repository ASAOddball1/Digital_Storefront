
import jdk.jfr.Category;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Store {
    Scanner scan = new Scanner(System.in);
    
    // User data variables
    private BankAccount myBankAccount;
    public ArrayList<Buyable> myStuff;
    private ArrayList<Buyable> myShoppingCart;

    public ArrayList<Buyable> purchaseHistory;


    // Store data variables
    private StoreInventory storeInventory;
    
    public Store()
    {
        System.out.println("Welcome to my storefont!");
        setupAccounts();
        setupStore();
        presentShoppingMenu();
    }
    
    private void setupAccounts()
    {
        setupBankAccount();
        myStuff = new ArrayList<Buyable>();
        myShoppingCart = new ArrayList<Buyable>();
        purchaseHistory = new ArrayList<Buyable>();
    }
    
    private void setupStore()
    {
        storeInventory = new StoreInventory();
    }

    private void setupBankAccount()
    {
        System.out.println("To begin, please set up a bank account.");
        System.out.println("How much money should your account contain?");
        try { //Try to deposit defined money value
            int depositAmount = scan.nextInt();
            myBankAccount = new BankAccount(depositAmount);
        } catch (InputMismatchException e) { // Catch Incorrect Value (ex. A string And Not An Int)
            //returns you to deposit menu if found
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine();
            setupBankAccount();
        }
    }
    
    private void presentShoppingMenu()
    {
        boolean stillShopping = true;
        while(stillShopping)
        {
            System.out.println("\n****************************************************** ");
            System.out.println("Please choose from one of the following menu options: ");
            System.out.println("1. View catalog of items to buy");
            System.out.println("2. Buy an item");
            System.out.println("3. View your cart of held items");
            System.out.println("4. Review the items you already own");
            System.out.println("5. View the status of your financials");
            System.out.println("6. Return Items");
            System.out.println("7. Store Purchase History");
            System.out.println("8. Exit program");


            int input = scan.nextInt();
            scan.nextLine(); // buffer clear
            
            switch(input){
                case 1:
                    viewCatalog();
                    break;
                case 2:
                    buyItem();
                    break;
                case 3:
                    reviewMyShoppingCart();
                    break;
                case 4:
                    reviewMyInventory();
                    break;
                case 5:
                    reviewFinancials();
                    break;
                case 6:
                    returnPurchasedItem();
                    break;
                case 7:
                    getPurchaseHistory();
                    break;
                case 8:
                    System.out.println("Thanks for shopping! Now exiting program ... ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect input. Choose again!");
                    break;
            }

        }
    }

    private void getPurchaseHistory(){

        System.out.println("The Items that have ever been bought from the store are. ");
        
        for (Buyable item : purchaseHistory){

            System.out.println(item.getItemName() + " " + item.getPrice());
        }
    }

    private void getClothes() {
        System.out.println("The Clothes We Have For Sale Are");

        for(BuyableClothing item: storeInventory.getClothesInventory()) {
            System.out.println(item.getItemName());
        }

    }

    private void getFood() {
        System.out.println("The Food We Have For Sale Are");

        for(BuyableFood item: storeInventory.getFoodInventory()) {
            System.out.println(item.getItemName());
        }
    }

    private void getGames(){
        System.out.println("The Games We Have For Sale Are");

        for(BuyableGame item: storeInventory.getGamesInventory()) {
            System.out.println(item.getItemName());
        }
    }

    private void getDevice(){
        System.out.println("The Devices We Have For Sale Are");

        for(BuyableDevices item: storeInventory.getDeviceInventory()) {
            System.out.println(item.getItemName());
        }
    }

    private void itemDetails(){

        System.out.println("WIP");

        /* System.out.println("Which item would you like to view the details of");

        String itemName = scan.nextLine();
        Buyable itemToCheck = null;
        for(Buyable item: storeInventory.getFullInventoryList()) {
            {
                if(item.getItemName().toLowerCase().equals(itemName.toLowerCase()))
                {
                    itemToCheck = item;
                    break;
                }

                if(itemToCheck != null)
                {
                    System.out.println(itemToCheck.getItemName());
                }




            }
        }

         */
    }


    private void viewCatalog()
    {
        System.out.println("What Category Would You Like, You have the options of: 1 Clothing, 2 Food, 3 Games, 4 Devices, Or 5 View More Details About An Item");
        {
            int Input = scan.nextInt();


            switch(Input){
                case 1:
                    getClothes();
                    break;
                case 2:
                    getFood();
                    break;
                case 3:
                    getGames();
                    break;
                case 4:
                    getDevice();
                    break;
                case 5:
                    itemDetails();
                default:
                    System.out.println("Incorrect input. Choose again!");
                    break;
            }
        }
    }
    
    private void buyItem()
    {
        System.out.println("Please type in the name of the item you wish to buy!");
        
        // Get user input
        String itemName = scan.nextLine();
        
        // Holding variable for the desired item, if found
        Buyable itemToBuy = null;
        
        // Look through the full inventory to see if the item is present
        // Convert both item name and user input to lower case to prevent case issues!
        for(Buyable item: storeInventory.getFullInventoryList()) 
        {
            if(item.getItemName().toLowerCase().equals(itemName.toLowerCase()))
            {
                itemToBuy = item;
                break;
            }
        }
        
        // If a suitable item was found, give them the option to buy it!
        if(itemToBuy != null)
        {
            System.out.println("We have " + itemToBuy.getItemName() + " in stock!");
            System.out.println("Type 1 to BUY NOW or 2 to PLACE IN YOUR SHOPPING CART.");
            
            int input = scan.nextInt();
            scan.nextLine(); // buffer clear
            
            if(input == 1)
            {
                makePurchaseFromStore(itemToBuy);
            }
            else if(input == 2)
            {
                System.out.println("We'll hold onto this item for you.");
                moveItemToShoppingCart(itemToBuy);
            }
            else
            {
                System.out.println("Incorrect input. Purchase cancelled.");
            }
            
        }
        else // No suitable item found
        {
            System.out.println("The item you are looking for is sold out or does not exist, sorry!");
        }
        
    }

    private void clothesCheck()
    {
        System.out.println("The Clothes you own are,");

        for (Buyable item : myStuff){
            if (item instanceof BuyableClothing){
                System.out.println(item.getItemName());
            }
            else{
                System.out.println("You dont own any clothes yet");
            }
        }

    }

    private void foodCheck()
    {
        System.out.println("The Food Items you own are,");

        for (Buyable item : myStuff){
            if (item instanceof BuyableFood){
                System.out.println(item.getItemName());
            }
            else{
                System.out.println("You don't have any food yet");
            }
        }

    }

    private void gameCheck()
    {
        System.out.println("The Games you own are,");

        for (Buyable item : myStuff){
            if (item instanceof BuyableGame){
                System.out.println(item.getItemName());
            }
            else{
                System.out.println("You don't have any games yet");
            }
        }

    }

    private void deviceCheck()
    {
        System.out.println("The Devices you own are,");

        for (Buyable item : myStuff){
            if (item instanceof BuyableDevices){
                System.out.println(item.getItemName());
            }
            else{
                System.out.println("You don't have a device yet.");
            }
        }

    }





    private void reviewMyInventory()

    {
        System.out.println("Which kind of item would you like to see if is in your inventory, (1. Clothing, 2. Food, 3. Games, 4. Devices)");

        int Input = scan.nextInt();

        switch(Input){
            case 1:
                clothesCheck();
                break;
            case 2:
                foodCheck();
                break;
            case 3:
                gameCheck();
                break;
            case 4:
                deviceCheck();
                break;
            default:
                System.out.println("Incorrect input. Choose again!");
                break;

        }
    }

        /*for (Buyable buyable : myStuff)
        {
            System.out.println("" + buyable.getItemName());
        }*/

    
    private void reviewFinancials()
    {
        myBankAccount.balanceReport();
    }
    
    
    // SHOPPING CART METHODS
    private void reviewMyShoppingCart()
    {
        if(!myShoppingCart.isEmpty())
        {
             System.out.println("Here are all of the items being held in your shopping cart: ");
             for(Buyable item: myShoppingCart)
             {
                 System.out.println("" + item.getItemName());
             }

             System.out.println("Would you like to purchase any held items now? 1 for YES or any other key for NO");

             String userInput = scan.nextLine();

             if(userInput.equals("1"))
             {
                 buyItemInShoppingCart();
             }
             else
             {
                 System.out.println("Leaving shopping cart as is and returning to the storefront ... ");
             } 
        }
        else
        {
            System.out.println("Your shopping cart is empty! Nothing to see here ... ");
        }
        
    }
    
    private void buyItemInShoppingCart()
    {
        System.out.println("Type in the name of the item you want to buy from the shopping cart: ");
        String userChoice = scan.nextLine();
        
        for(Buyable itemInCart: myShoppingCart)
        {
            if(itemInCart.getItemName().toLowerCase().equals(userChoice.toLowerCase()))
            {
                makePurchaseFromShoppingCart(itemInCart);
                break;
            }
            else
            {
                System.out.println("Item could not be found in shopping cart.");
            }
        }
        
    }
    
    private void removeItemFromShoppingCart(Buyable item)
    {
        System.out.println("Which item would you like to remove from your shopping cart?");
        
        String userChoice = scan.nextLine();
        
        for(Buyable cartItem: myShoppingCart)
        {
            if(cartItem.getItemName().toLowerCase().equals(userChoice.toLowerCase()))
            {
                System.out.println("You have removed " + cartItem.getItemName() + " from your shopping cart.");
                moveItemFromShoppingCartToInventory(item);
            }
            else
            {
                System.out.println("Item could not be found in your shopping cart.");
            }
        }
    }
    
    // Move item from inventory to shopping cart
    private void moveItemToShoppingCart(Buyable item)
    {
        myShoppingCart.add(item);
        storeInventory.removeItemFromInventory(item);
    }
    
    private void moveItemFromShoppingCartToInventory(Buyable item)
    {
        storeInventory.restockItemToInventory(item);
        myShoppingCart.remove(item);
    }


    private void makePurchaseFromStore(Buyable item) {

        try {
            if (myBankAccount.canAfford(item.getPrice())) {
                myBankAccount.makePurchase(item.getPrice());
                System.out.println("Purchase complete! You now own " + item.getItemName());
                myStuff.add(item);
                purchaseHistory.add(item);
                storeInventory.removeItemFromInventory(item);
            } else {
                System.out.println("You can't afford that item ... ");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while processing your purchase. Please try again.");
        }
    }
    
    private void makePurchaseFromShoppingCart(Buyable item)
    {
        // If you can afford the item, buy it and remove it from the store
        if(myBankAccount.canAfford(item.getPrice()))
        {
            myBankAccount.makePurchase(item.getPrice());
            System.out.println("Purchase complete! You now own " + item.getItemName());
            myStuff.add(item);
            purchaseHistory.add(item);
            myShoppingCart.remove(item);
        }
        else
        {
            System.out.println("You can't afford that item ... ");
        }        
    }

    public void returnPurchasedItem()
    {
        System.out.println("You Can Return the following items:");
        for (Buyable buyable : myStuff)
        {
            System.out.println(buyable.getItemName());
        }

        System.out.println("Select an item you would like to return:");
        String itemName = scan.nextLine();

        if(isItemInBoughtInventory(itemName))
        {
            int itemToReturn = getItemPositionInArrayList(itemName);
            Buyable item = myStuff.get(itemToReturn);
            double price = item.getPrice();

            myBankAccount.depositMoney(price);
            storeInventory.addItemToStoreInventory(item);
            myStuff.remove(itemToReturn);
        }

        else
        {
            System.out.println("You do not have this item!");
        }
    }

    private int getItemPositionInArrayList(String itemName)
    {
        for(int i = 0; i < myStuff.size(); i++)
        {
            if(myStuff.get(i).getItemName().equalsIgnoreCase(itemName))
            {
                return i;
            }
        }

        return -1;
    }

    private boolean isItemInBoughtInventory(String itemName)
    {
        for(Buyable item : myStuff)
        {
            if(item.getItemName().equalsIgnoreCase(itemName))
            {
                return true;
            }
        }
        return false;
    }
}
