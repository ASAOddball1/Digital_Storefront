public class BuyableDevices extends Buyable {
    private String Brand;
    private String Type;

    public BuyableDevices(double price, String name, String Brand, String Type) {
        super(price, name, "Device");
        this.Brand = Brand;
        this.Type = Type;
    }

    public String getBrand() {
        return Brand;
    }

    public String getType() {
        return Type;
    }
}
