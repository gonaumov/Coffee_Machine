package machine;

public enum MachineState {
    CHOOSING_AN_ACTION("Write action (buy, fill, take, remaining, exit):"),
    CHOOSING_A_VARIANT_OF_COFFEE("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"),
    PREPARING("I have enough resources, making you a coffee!"),
    STATUS("The coffee machine has:%n" +
            "%d of water%n" +
            "%d of milk%n" +
            "%d of coffee beans%n" +
            "%d of disposable cups%n" +
            "%d of money%n%n"),
    FILL_WATER("Write how many ml of water do you want to add:"),
    FILL_MILK("Write how many ml of milk do you want to add:"),
    FILL_COFEE("Write how many grams of coffee beans do you want to add:"),
    FILL_CUPS("Write how many disposable cups of coffee do you want to add:"),
    ERROR_NOT_ENOUGHT_WATER("Sorry, not enough water!"),
    ERROR_NOT_ENOUGHT_MILK("Sorry, not enough milk!"),
    ERROR_NOT_ENOUGHT_COFEE("Sorry, not enough coffee beans!"),
    ERROR_NOT_ENOUGHT_CUPS("Sorry, not disposable cups!"),
    TAKE("I gave you $%d%n");
    String message;

    MachineState(String message) {
        this.message = message;
    }
}
