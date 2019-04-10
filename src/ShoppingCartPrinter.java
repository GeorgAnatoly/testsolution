// USES JAVA 12

import java.util.ArrayList;
import java.util.Scanner;

// just wanted to point out I understood the purpose
// of the assignment as the ItemToPurchase
// class as the data persistence object, the model,
// and the ShoppingCartPrinter the controller/view
// portion - with the 'view', the user not having
// many options regarding normal, relatively unlimited
// CRUD options being presented to the user having the
// ItemToPurchase have a meaningful existence in a
// is not really possible - it's role is rather limited

// I've followed a standard MVC construction with
// this class - going to include implicit data
// transfer objects - that's why the duplicate fields
// makes code modular and easy to unit test
// really enjoy how the main() shows the resulting
// code structure obtained via TDD versus more
// coupled code without testing in mind
final class ShoppingCartPrinter {
    private ShoppingCartPrinterTest tests =
            new ShoppingCartPrinterTest();
    private int itemNumber = 0;

    public static void main(String[] args) {
        var cart = new ShoppingCartPrinter();

        if(cart.tests.runTests() == -1) {
            cart.tests.printErrors();

            return;
        }

        var item = cart.promptUserForItem();

        var items = new ArrayList<ItemToPurchase>();

        items.add(item);

        System.out.println();

        item = cart.promptUserForItem();

        items.add(item);

        System.out.println("\nTOTAL COST");

        cart.displayItemCost(items);
    }

    private void displayItemCost(ArrayList<ItemToPurchase> items) {
        var itemPrice = 0;
        var itemQuantity = 0;
        var itemCost = 0;
        var totalPrice = 0;

        for(ItemToPurchase item: items) {
            itemPrice = item.getPrice();
            itemQuantity = item.getQuantity();
            itemCost = itemPrice * itemQuantity;
            totalPrice += itemCost;

            System.out.println(
                item.getName() + " " +
                        itemQuantity +
                        " @ $" +
                        itemPrice
                        + " = $" +
                    itemCost
             );
        }

        System.out.println("\nTotal: $" + totalPrice);
    }

    private ItemToPurchase promptUserForItem() {
        incrementItemNumber();
        displayItemNumber();

        promptRequestItemName();
        var itemName = getFromUserItemName(
                new Scanner(System.in)
        );

        promptRequestItemPrice();
        var itemPrice = getFromUserItemPrice(
                new Scanner(System.in)
        );

        promptRequestItemQuantity();
        var itemQuantity = getFromUserItemQuantity(
                new Scanner(System.in)
        );


        return ItemToPurchase.getInstance(
                itemName, itemPrice, itemQuantity
        );
    }

    private int getFromUserItemQuantity(Scanner scanner) {
        return scanner.nextInt();
    }

    private void promptRequestItemQuantity() {
        System.out.println("Enter the item quantity: ");
    }

    private int getFromUserItemPrice(Scanner scanner) {
        return scanner.nextInt();
    }

    private void promptRequestItemPrice() {
        System.out.println("Enter the item price: ");
    }

    private void promptRequestItemName() {
        System.out.println("Enter the item name: ");
    }

    private String getFromUserItemName(Scanner scanner) {
        return scanner.nextLine();
    }

    private void incrementItemNumber() {
        ++itemNumber;

    }

    private void displayItemNumber() {
        System.out.println("Item " + itemNumber);
    }

    private class ShoppingCartPrinterTest {
        private int failedTests;
        private ArrayList<String> failedMessages =
                new ArrayList<>();

        ShoppingCartPrinterTest() {
            failedTests = 0;
        }

        int runTests() {
            promptUserForItemFirstItemIs1();
            promptUserForItems9TimesIs10();
            getFromUserItemNameIsStringFoo();
            getFromUserItemPriceIsInt3();
            getFromUserItemQuantityIsInt10();

            return failedTests > 0? -1: 0;
        }

        void printErrors() {
            for(String message: failedMessages)
                System.out.println(message);
        }

        <T> void createNewError(String methodName, T expected,
                            T result) {
            ++failedTests;

            failedMessages.add(
                    "FAILED: " + methodName +
                            "\nExpected " + expected +
                            " - Got " +
                            result
            );
        }

        // pretend these are using Assertions.assert...()
        // methods
        void promptUserForItemFirstItemIs1() {
            var cart = new ShoppingCartPrinter();

            cart.incrementItemNumber();

            if(cart.itemNumber != 1) {
                createNewError(
                        "incrementItemNumber",
                        1,
                        cart.itemNumber
                );
            }
        }

        void promptUserForItems9TimesIs10() {
            var cart = new ShoppingCartPrinter();

            for(int i = 0; i < 10; ++i)
                cart.incrementItemNumber();

            if(cart.itemNumber != 10) {
                createNewError(
                        "incrementItemNumber",
                        10,
                        cart.itemNumber
                );
            }
        }

        void getFromUserItemNameIsStringFoo() {
            var cart = new ShoppingCartPrinter();

            var response = cart.getFromUserItemName(
                    new Scanner("Foo")
            );

            if(!"Foo".equals(response)) {
                createNewError(
                        "getFromUserItemName",
                        "Foo",
                        response
                );
            }
        }

        void getFromUserItemPriceIsInt3() {
            var cart = new ShoppingCartPrinter();

            var response = cart.getFromUserItemPrice(
                    new Scanner("3")
            );

            if(response != 3) {
                createNewError(
                        "getFromUserItemPrice",
                        3,
                        response
                );
            }
        }

        void getFromUserItemQuantityIsInt10() {
            var cart = new ShoppingCartPrinter();

            var response = cart.getFromUserItemQuantity(
                    new Scanner("10")
            );

            if(response != 10) {
                createNewError(
                        "getFromUserItemQuantity",
                        10,
                        response
                );
            }
        }
    }
}
