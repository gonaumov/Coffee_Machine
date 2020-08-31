package machine;

public class CofeeMaker {
    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;
    MachineState state;
    CoffeeTypes selectedType;

    public CofeeMaker(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;
        this.state = MachineState.CHOOSING_AN_ACTION;
    }

    public String getStatusMessage() {
        switch (this.state) {
            case STATUS:
                this.state = MachineState.CHOOSING_AN_ACTION;
                return String.format(MachineState.STATUS.message,
                        this.water,
                        this.milk,
                        this.coffee,
                        this.cups,
                        this.money) + MachineState.CHOOSING_AN_ACTION.message;
            case TAKE:
                this.state = MachineState.CHOOSING_AN_ACTION;
                int money = this.money;
                this.money = 0;
                return String.format(MachineState.TAKE.message, money) +
                        MachineState.CHOOSING_AN_ACTION.message;
            case ERROR_NOT_ENOUGHT_WATER:
            case ERROR_NOT_ENOUGHT_MILK:
            case ERROR_NOT_ENOUGHT_COFEE:
            case ERROR_NOT_ENOUGHT_CUPS:
                String message = this.state.message;
                this.state = MachineState.CHOOSING_AN_ACTION;
                return message + "\n" + MachineState.CHOOSING_AN_ACTION.message;
            case PREPARING:
                this.state = MachineState.CHOOSING_AN_ACTION;
                return MachineState.PREPARING.message +
                        "\n" +
                        MachineState.CHOOSING_AN_ACTION.message;
            case FILL_CUPS:
            default:
                return this.state.message;
        }
    }

    private boolean checkResources() {
        if (this.water < this.selectedType.water) {
            this.state = MachineState.ERROR_NOT_ENOUGHT_WATER;
            return false;
        }
        if (this.milk < this.selectedType.milk) {
            this.state = MachineState.ERROR_NOT_ENOUGHT_MILK;
            return false;
        }
        if (this.coffee < this.selectedType.coffee) {
            this.state = MachineState.ERROR_NOT_ENOUGHT_COFEE;
            return false;
        }
        if (this.cups == 0) {
            this.state = MachineState.ERROR_NOT_ENOUGHT_CUPS;
            return false;
        }
        return true;
    }

    private void updateResources() {
        this.water -= this.selectedType.water;
        this.milk -= this.selectedType.milk;
        this.coffee -= this.selectedType.coffee;
        this.cups--;
        this.money += this.selectedType.price;
    }

    public boolean processUserInput(String input) {
        if (this.state == MachineState.CHOOSING_AN_ACTION) {
            switch (input) {
                case "exit":
                    return false;
                case "remaining":
                    this.state = MachineState.STATUS;
                    return true;
                case "fill":
                    this.state = MachineState.FILL_WATER;
                    return true;
                case "take":
                    this.state = MachineState.TAKE;
                    return true;
                case "buy":
                    this.state = MachineState.CHOOSING_A_VARIANT_OF_COFFEE;
                    return true;
                default:
                    return true;
            }
        } else if (this.state.name().startsWith("FILL")) {
            switch (this.state) {
                case FILL_WATER:
                    this.water += Integer.parseInt(input);
                    this.state = MachineState.FILL_MILK;
                    return true;
                case FILL_MILK:
                    this.milk += Integer.parseInt(input);
                    this.state = MachineState.FILL_COFEE;
                    return true;
                case FILL_COFEE:
                    this.coffee += Integer.parseInt(input);
                    this.state = MachineState.FILL_CUPS;
                    return true;
                case FILL_CUPS:
                    this.cups += Integer.parseInt(input);
                    this.state = MachineState.CHOOSING_AN_ACTION;
                    return true;
            }
            return false;
        } else if (this.state == MachineState.CHOOSING_A_VARIANT_OF_COFFEE) {
            switch (input) {
                case "back":
                    this.state = MachineState.CHOOSING_AN_ACTION;
                    return true;
                case "1":
                    this.selectedType = CoffeeTypes.CHOOSED_ESPRESSO;
                    if (this.checkResources()) {
                        this.updateResources();
                        this.state = MachineState.PREPARING;
                    }
                    return true;
                case "2":
                    this.selectedType = CoffeeTypes.CHOOSED_LATTE;
                    if (this.checkResources()) {
                        this.updateResources();
                        this.state = MachineState.PREPARING;
                    }
                    return true;
                case "3":
                    this.selectedType = CoffeeTypes.CHOOSED_CAPUCHINO;
                    if (this.checkResources()) {
                        this.updateResources();
                        this.state = MachineState.PREPARING;
                    }
                    return true;
            }
        }

        return true;
    }
}
