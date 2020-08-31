package machine;

public enum CoffeeTypes {
    CHOOSED_ESPRESSO(250, 0, 16, 4),
    CHOOSED_LATTE(350, 75, 20, 7),
    CHOOSED_CAPUCHINO(200, 100, 12, 6);
    int water;
    int milk;
    int coffee;
    int price;

    CoffeeTypes(int water,
                int milk,
                int coffe,
                int price) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffe;
        this.price = price;
    }
}
