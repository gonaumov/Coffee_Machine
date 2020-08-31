package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int water = 400;
        int milk = 540;
        int coffee = 120;
        int cups = 9;
        int money = 550;
        CofeeMaker maker = new CofeeMaker(water, milk, coffee, cups, money);
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(maker.getStatusMessage());
        } while (maker.processUserInput(scanner.nextLine()));
    }
}