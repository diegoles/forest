package blendedFruitDrink.op2;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class DrinkMachine {
    public static class OutOfStockException extends RuntimeException {
        public OutOfStockException(Drink drink) {
            super(drink.toString());
        }
    }

    private Map<Ingredient, Integer> stock = new HashMap<Ingredient, Integer>();

    public int getStock(Ingredient ingredient) {
        return this.stock.containsKey(ingredient) ?
               this.stock.get(ingredient) : 0;
    }

    public void restock() {
        for (Ingredient ingredient : Ingredient.values()) {
            if (this.getStock(ingredient) < 10) {
                this.stock.put(ingredient, 10);
            }
        }
    }

    public Drink[] menu() {
        return Drink.values();
    }

    public boolean canMake(Drink drink) {
        for (Map.Entry<Ingredient, Integer> quantStuff : drink.getRecipe().entrySet()) {
            if (this.getStock(quantStuff.getKey()) < quantStuff.getValue()) {
                return false;
            }
        }
        return true;
    }

    public void make(Drink drink) throws OutOfStockException {
        if (!this.canMake(drink)) {
            throw new OutOfStockException(drink);
        }
        for (Map.Entry<Ingredient, Integer> quantStuff : drink.getRecipe().entrySet()) {
            int quant = quantStuff.getValue();
            Ingredient stuff = quantStuff.getKey();
            this.stock.put(stuff, this.getStock(stuff) - quant);
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder("Inventory:\n");
        for (Ingredient ingredient : Ingredient.values()) {
            out.append(ingredient).append(": ")
               .append(this.getStock(ingredient)).append('\n');
        }
        out.append("\nMenu:\n");
        int i = 0;
        for (Drink drink : Drink.values()) {
            out.append(++i).append(": ").append(drink);
            if (this.canMake(drink)) {
                out.append(", $").append(drink.getPrice());
            } else {
                out.append(" (out of stock)");
            }
            out.append('\n');
        }
        return out.toString();
    }

    private static String prompt(PrintStream con, String prompt) {
        con.printf("%s", prompt);
        return null;
    }

    public static void main(String[] args) {
        DrinkMachine machine = new DrinkMachine();
        machine.restock();

        PrintStream con = System.out;
        con.printf("%s\n", machine);

//        String origCmd;
//        while ((origCmd = prompt(con, "Select: ")) != null) {
//            String cmd = origCmd.toLowerCase();
//
//            if (cmd.isEmpty()) {
//                continue;
//            } else if ("q".equals(cmd)) {
//                break;
//            } else if ("r".equals(cmd)) {
//                machine.restock();
//            } else try {
//                int selection = Integer.parseInt(cmd);
//                Drink order = machine.menu()[selection - 1];
//                machine.make(order);
//                con.printf("Dispensing: %s\n", order);
//            } catch (ArrayIndexOutOfBoundsException | NumberFormatException invalid) {
//                con.printf("Invalid input: %s\n", origCmd);
//                continue;
//            } catch (OutOfStockException outOfStock) {
//                con.printf("Out of stock: %s\n", outOfStock.getMessage());
//                continue;
//            }
//            con.printf("%s\n", machine);
//        }
    }

}