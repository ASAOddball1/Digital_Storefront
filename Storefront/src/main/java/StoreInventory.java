
import java.util.ArrayList;

public class StoreInventory {

    public ArrayList<BuyableClothing> clothesForSale = new ArrayList<BuyableClothing>();
    public ArrayList<BuyableFood> foodForSale = new ArrayList<BuyableFood>();
    public ArrayList<BuyableGame> gamesForSale = new ArrayList<BuyableGame>();
    public ArrayList<BuyableDevices> deviceForSale = new ArrayList<BuyableDevices>();

    public StoreInventory() {
        populateClothesInventory();
        populateFoodInventory();
        populateGamesInventory();
        populateDeviceInventory();
    }

    // Getters and setters for inventory lists

    public ArrayList<BuyableClothing> getClothesInventory() {
        return clothesForSale;
    }

    public ArrayList<BuyableFood> getFoodInventory() {
        return foodForSale;
    }

    public ArrayList<BuyableGame> getGamesInventory() {
        return gamesForSale;
    }

    public ArrayList<BuyableDevices> getDeviceInventory() {
        return deviceForSale;
    }


    // Returns a master list of all inventory items at once
    public ArrayList<Buyable> getFullInventoryList() {
        ArrayList<Buyable> fullInventory = new ArrayList<Buyable>();
        fullInventory.addAll(clothesForSale);
        fullInventory.addAll(foodForSale);
        fullInventory.addAll(gamesForSale);
        fullInventory.addAll(deviceForSale);
        return fullInventory;
    }


    public void removeItemFromInventory(Buyable item) {
        if (item instanceof BuyableClothing) {
            clothesForSale.remove((BuyableClothing) item);
        } else if (item instanceof BuyableFood) {
            foodForSale.remove((BuyableFood) item);
        } else if (item instanceof BuyableGame) {
            foodForSale.remove((BuyableGame) item);
        } else if (item instanceof BuyableDevices) {
            deviceForSale.remove((BuyableDevices) item);
        }
    }

    public void restockItemToInventory(Buyable item) {
        if (item instanceof BuyableClothing) {
            clothesForSale.add((BuyableClothing) item);
        } else if (item instanceof BuyableFood) {
            foodForSale.add((BuyableFood) item);
        } else if (item instanceof BuyableGame) {
            foodForSale.remove((BuyableGame) item);
        } else if (item instanceof BuyableDevices) {
            deviceForSale.add((BuyableDevices) item);
        }
    }

    // Methods to populate the inventory
    private void populateClothesInventory() {
        // Master list of all clothes held in the store on opening

        // Hoodies
        BuyableClothing smallHoodie = new BuyableClothing(59.99, "Hoodie", "small");
        clothesForSale.add(smallHoodie);
        BuyableClothing mediumHoodie = new BuyableClothing(59.99, "Hoodie", "medium");
        clothesForSale.add(mediumHoodie);
        BuyableClothing largeHoodie = new BuyableClothing(59.99, "Hoodie", "lage");
        clothesForSale.add(largeHoodie);

        // Shoes
        BuyableClothing dressShoes = new BuyableClothing(99.99, "Dress Shoes", "8");
        clothesForSale.add(dressShoes);
        BuyableClothing sandals = new BuyableClothing(9.99, "Sandals", "5");
        clothesForSale.add(sandals);

        // Gloves
        BuyableClothing gloves = new BuyableClothing(13.49, "Gloves", "Medium");
        addMultiple(gloves, 3);
    }

    private void populateFoodInventory() {
        // Master list of all food held in the store on opening

        // Perishables
        BuyableFood pizza = new BuyableFood(12.99, "Pizza", 400);
        foodForSale.add(pizza);
        BuyableFood lasagna = new BuyableFood(24.00, "Lasagna", 1000);
        foodForSale.add(lasagna);
        BuyableFood spinach = new BuyableFood(3.99, "Spinach", 250);
        foodForSale.add(spinach);

        // Non-perishables
        BuyableFood beans = new BuyableFood(1.49, "Beans", 300);
        foodForSale.add(beans);
        BuyableFood noodles = new BuyableFood(0.99, "Noodles", 125);
        foodForSale.add(noodles);
        BuyableFood rice = new BuyableFood(7.99, "Rice", 2000);
        addMultiple(rice, 5);

    }

    private void populateGamesInventory() {
        // Master list of all games held in the store on opening

        //Board Games

        BuyableGame monopoly = new BuyableGame(19.99, "Monopoly", 4, "Board Game");
        gamesForSale.add(monopoly);
        BuyableGame scrabble = new BuyableGame(24.99, "Scrabble", 2, "Board Game");
        gamesForSale.add(scrabble);

        //Computer Games

        BuyableGame breathOfTheWild = new BuyableGame(79.99, "Breath of the Wild", 1, "Video Game");
        gamesForSale.add(breathOfTheWild);
        BuyableGame forza = new BuyableGame(59.99, "Forza", 2, "Video Game");
        gamesForSale.add(forza);
    }

    // Helper method to add multiple copies of the same item to the inventory at once
    private void addMultiple(Buyable item, int numberToAdd) {
        if (item instanceof BuyableClothing) {
            for (int i = 0; i < numberToAdd; i++) {

                clothesForSale.add((BuyableClothing) item);
            }
        } else if (item instanceof BuyableFood) {
            for (int i = 0; i < numberToAdd; i++) {
                foodForSale.add((BuyableFood) item);
            }
        } else if (item instanceof BuyableGame) {
            for (int i = 0; i < numberToAdd; i++) {
                gamesForSale.add((BuyableGame) item);


            }


        }
    }

    public void addItemToStoreInventory(Buyable item) {
        if (item instanceof BuyableClothing) {
            clothesForSale.add((BuyableClothing) item);
        } else if (item instanceof BuyableFood) {
            foodForSale.add((BuyableFood) item);
        } else if (item instanceof BuyableGame) {
            gamesForSale.add((BuyableGame) item);
        } else if (item instanceof BuyableDevices) {
            deviceForSale.add((BuyableDevices) item);
        }
    }

    private void populateDeviceInventory() {
        BuyableDevices IPhone13ProMax = new BuyableDevices(599.99, "IPhone 13 Pro Max", "Apple", "Phone");
        deviceForSale.add(IPhone13ProMax);
        BuyableDevices HPVictusGamingLaptop = new BuyableDevices(749.99, "HP Victus Gaming Laptop", "HP", "Laptop");
        deviceForSale.add(HPVictusGamingLaptop);
        BuyableDevices ROGAlly = new BuyableDevices(899.99, "ROG Ally", "ROG", "Gaming Handheld");
        deviceForSale.add(ROGAlly);
        BuyableDevices LogitechG29 = new BuyableDevices(349.99, "Logitech G29 Racing Wheel", "Logitech", "Controller");
        deviceForSale.add(LogitechG29);
        BuyableDevices ROGDesktop = new BuyableDevices(899.99, "ROG-G10DK Gaming Desktop", "ROG", "DesktopPC");
        deviceForSale.add(ROGDesktop);
    }
}
