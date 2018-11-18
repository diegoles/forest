package blendedFruitDrink.op3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import blendedFruitDrink.op2.Drink;
import blendedFruitDrink.op2.Ingredient;

public class DrinkMachine {

	private Map<Ingredient, Integer> stock = new HashMap<>();

	public static void main(String[] args) {

		DrinkMachine machine = new DrinkMachine();
		machine.restock();
		machine.display();
		machine.startIO();
		ConLeche cafeConLeche = new ConLeche(new CafeCorto());
		System.out.println(cafeConLeche.descripcion() + " " + cafeConLeche.precio() + "");
	}

	public void restock() {

		for (Ingredient ingredient : Ingredient.values()) {
			if (this.getStock(ingredient) < 1000) {
				this.stock.put(ingredient, 1000);
			}
		}
	}

	public int getStock(Ingredient ingredient) {
		return this.stock.containsKey(ingredient) ? this.stock.get(ingredient) : 0;
	}

	public void display() {
		System.out.println("***********  Inventory:  ************\n");
		this.stock.forEach((k, v) -> System.out.println("Item : " + k + " = " + v));

		System.out.println("\n\n***********  Menu:  ************\n");
		int i = 0;
		for (Drink drink : Drink.values()) {
			String result = ++i + "".concat(": ").concat(drink.toString());

			if (this.canMake(drink)) {
				result = result.concat(", $").concat(drink.getPrice().toString());
			} else {
				result = result.concat(" (out of stock)");
			}

			System.out.println(result);
		}
	}

	public boolean canMake(Drink drink) {
		
		for (Map.Entry<Ingredient, Integer> quantStuff : drink.getRecipe().entrySet()) {
			if (this.getStock(quantStuff.getKey()) < quantStuff.getValue()) {
				return false;
			}
		}
		return true;
	}

	public void startIO() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";

		while (true) {
			try {

				input = reader.readLine().toLowerCase();

				if (input.equals("")) {
					continue;
				} else if (input.equals("q")) {
					System.exit(0);
				} else if (input.equals("r")) {
					restock();
				} else if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= Drink.values().length) {
					int selection = Integer.parseInt(input);
					Drink order = Drink.values()[selection - 1];
					this.make(order);
					System.out.println("Dispensing: " + order);
				} else {
					throw new IOException();
				}
			} catch (OutOfStockException e) {
				System.out.println("Restock!!!! ");
			} catch (Exception e) {
				System.out.println("Invalid selection: " + input);
			}
		}
	}

	public void make(Drink drink) throws OutOfStockException {

		if (this.canMake(drink)) {
			drink.getRecipe().forEach((k, v) -> this.stock.put(k, this.getStock(k) - v));
		} else {
			System.out.println("Out of stock:: " + drink.toString());
			restock();
			throw new OutOfStockException(drink);
		}
	}

	@SuppressWarnings("serial")
	public static class OutOfStockException extends RuntimeException {
		public OutOfStockException(Drink drink) {
			super(drink.toString());
		}
	}
}
