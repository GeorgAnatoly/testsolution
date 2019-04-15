// USES JAVA 12


// just wanted to point out I understood the purpose
// of the assignment as the ItemToPurchase
// class as the data persistence object, the model,
// and the ShoppingCartPrinter the controller/view
// portion - with the 'view', the user not having
// many options regarding normal, relatively unlimited
// CRUD options being presented to the user having the
// ItemToPurchase have a meaningful existence in a
// is not really possible - it's role is rather limited

import java.util.Scanner;

// I've followed a standard MVC (if antiquated) construction with
// this class - going to include implicit data
// transfer objects - that's why the duplicate fields
// makes code modular and easy to unit test
// really enjoy how the main() shows the resulting
// code structure obtained via TDD versus more
// coupled code without testing in mind
final class ShoppingCartPrinter {
    private Tests tests = new Tests();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var cart = new ShoppingCartPrinter();

        var numTests = cart.tests.runTests();

        if(numTests > 0) {
            System.out.println(numTests);
            return;
        }

        var userPromptMessage = "Enter the item name: \n" +
                "Enter the item price: \n" +
                "Enter the item quantity: \n";

        System.out.println("Item 1");

        var item = getItemInfoFromUser(
                cart,
                cart.scanner,
                userPromptMessage);

        System.out.println();

        System.out.println("Item 2");

        var itemTwo = getItemInfoFromUser(
                cart,
                cart.scanner,
                userPromptMessage);

        System.out.println();

        System.out.println("total cost".toUpperCase());

        var itemTotalCost = displayItemTotalCost(item);

        System.out.println(itemTotalCost);

        var itemTwoTotalCost = displayItemTotalCost(itemTwo);

        System.out.println(itemTwoTotalCost);

        System.out.println();

        var totalCost = (item.getPrice() * item.getQuantity()) +
                (itemTwo.getPrice() * itemTwo.getQuantity());

        System.out.println("Total: $" + totalCost);
    }

    private static String displayItemTotalCost(ItemToPurchase item) {
        return item.getName() + " " + item.getQuantity() + " @ $" +
                item.getPrice() + " = $" +
                item.getPrice() * item.getQuantity();
    }

    private static ItemToPurchase getItemInfoFromUser(ShoppingCartPrinter cart,
                                            Scanner scanner,
                                                      String userPromptMessage) {
        System.out.print(userPromptMessage);

        var item = new ItemToPurchase();

        cart.setItemName(item, scanner);

        cart.setItemPrice(item, scanner);

        cart.setItemQuantity(item, scanner);

        return item;
    }

    private void setItemName(ItemToPurchase item,
                                    Scanner scanner) {
        var nameOne = scanner.next();
        var nameTwo = scanner.next();
        var nameThree = "";

        if(!scanner.hasNextInt() && scanner.hasNext())
            nameThree = " " + scanner.next();

        item.setName(nameOne + " " + nameTwo +
                 nameThree);
    }

    private void setItemPrice(ItemToPurchase item,
                              Scanner scanner) {
        item.setPrice(scanner.nextInt());
    }

    private void setItemQuantity(ItemToPurchase item,
                                 Scanner scanner) {
        item.setQuantity(scanner.nextInt());
    }

    private class Tests {
        private int numberTestsFailed = 0;

        int runTests() {
            itemSetAndGetName();
            itemSetAndGetPrice();
            itemSetAndGetQuantity();
            cartSetItemNameFromScanner();
            cartSetItemPriceFromScanner();
            integrationTestOnCartSetItemNameAndPrice();
            cartSetItemQuantityFromScanner();
            integrationTestCartSetNamePriceQuantity();
            endToEndForSingleStringTwoObjects();
            displayTotalCostForItemCorrectString();
            cartSetGetNameForThreeStringFromScanner();

            return numberTestsFailed;
        }

        void itemSetAndGetName() {
            var item = new ItemToPurchase();

            item.setName("Chocolate Chips");

            if(!item.getName().equals("Chocolate Chips"))
                ++numberTestsFailed;
        }

        void itemSetAndGetPrice() {
            var item = new ItemToPurchase();

            item.setPrice(3);

            if(item.getPrice() != 3)
                ++numberTestsFailed;
        }

        void itemSetAndGetQuantity() {
            var item = new ItemToPurchase();

            item.setQuantity(4);

            if(item.getQuantity() != 4)
                ++numberTestsFailed;
        }

        void cartSetItemNameFromScanner() {
            var cart = new ShoppingCartPrinter();
            var item = new ItemToPurchase();
            var userInput = "Chocolate Chips";
            var scanner = new Scanner(userInput);

            cart.setItemName(item, scanner);

            if(!item.getName().equals(userInput))
                ++numberTestsFailed;
        }

        void cartSetItemPriceFromScanner() {
            var cart = new ShoppingCartPrinter();
            var item = new ItemToPurchase();
            var userInput = "3 1";
            var scanner = new Scanner(userInput);

            cart.setItemPrice(item, scanner);

            if(item.getPrice() != 3)
                ++numberTestsFailed;
        }

        void integrationTestOnCartSetItemNameAndPrice() {
            var cart = new ShoppingCartPrinter();
            var item = new ItemToPurchase();
            var userInput = "Chocolate Chips 3";
            var scanner = new Scanner(userInput);

            cart.setItemName(item, scanner);
            cart.setItemPrice(item, scanner);

            if(item.getPrice() != 3)
                ++numberTestsFailed;
        }

        void cartSetItemQuantityFromScanner() {
            var cart = new ShoppingCartPrinter();
            var item = new ItemToPurchase();
            var userInput = "1";
            var scanner = new Scanner(userInput);

            cart.setItemQuantity(item, scanner);

            if(item.getQuantity() != 1)
                ++numberTestsFailed;
        }

        void integrationTestCartSetNamePriceQuantity() {
            var cart = new ShoppingCartPrinter();
            var item = new ItemToPurchase();
            var userInput = "Chocolate Chips 3 1";
            var scanner = new Scanner(userInput);

            cart.setItemName(item, scanner);
            cart.setItemPrice(item, scanner);
            cart.setItemQuantity(item, scanner);

            if(item.getQuantity() != 1)
                ++numberTestsFailed;
        }

        void endToEndForSingleStringTwoObjects() {
            var cart = new ShoppingCartPrinter();
            var userInput = "Chocolate Chips 3 1 Bottled Water 1 10";
            var scanner = new Scanner(userInput);

            var item = ShoppingCartPrinter.getItemInfoFromUser(
                    cart,
                    scanner,
                    "");

            var itemTwo = ShoppingCartPrinter.getItemInfoFromUser(
                    cart,
                    scanner,
                    "");

            if(!item.getName().equals("Chocolate Chips") ||
                !itemTwo.getName().equals("Bottled Water"))
                ++numberTestsFailed;

            if(item.getPrice() != 3 ||
                itemTwo.getPrice() != 1)
                ++numberTestsFailed;
        }

        void displayTotalCostForItemCorrectString() {
            var item = new ItemToPurchase();

            item.setName("Chocolate Chips");
            item.setPrice(1);
            item.setQuantity(3);

            var stringOutput = displayItemTotalCost(item);
            var validResponse = "Chocolate Chips " + "3 @ $" +
                    "1 = $3";

            if(!stringOutput.equals(validResponse))
                ++numberTestsFailed;
        }

        void cartSetGetNameForThreeStringFromScanner() {
            var cart = new ShoppingCartPrinter();
            var item = new ItemToPurchase();
            var userInput = "NY Yankees Cap";
            var scanner = new Scanner(userInput);

            cart.setItemName(item, scanner);

            if(!item.getName().equals(userInput))
                ++numberTestsFailed;
        }

    }
}
