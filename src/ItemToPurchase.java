// typical 'model' (mvc) structured pojo class
// used static factory for extensibility - though
// wasn't necessary
final class ItemToPurchase {

    private String itemName;
    private int itemPrice, itemQuantity;

    private ItemToPurchase() {
        itemName = "none";
        itemPrice = 0;
        itemQuantity = 0;
    }

    static ItemToPurchase getInstance(
            String itemName, int itemPrice,
            int itemQuantity
    ) {
        var item = new ItemToPurchase();
        item.itemName = itemName;
        item.itemPrice = itemPrice;
        item.itemQuantity = itemQuantity;

        return item;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
