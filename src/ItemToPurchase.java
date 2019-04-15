// typical 'model' (mvc) structured pojo class
// used static factory for extensibility - though
// wasn't necessary
final class ItemToPurchase {

    private String itemName;
    private int itemPrice, itemQuantity;

    ItemToPurchase() {
        itemName = "none";
        itemPrice = 0;
        itemQuantity = 0;
    }

    String getName() {
        return itemName;
    }

    void setName(String itemName) {
        this.itemName = itemName;
    }

    int getPrice() {
        return itemPrice;
    }

    void setPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    int getQuantity() {
        return itemQuantity;
    }

    void setQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
