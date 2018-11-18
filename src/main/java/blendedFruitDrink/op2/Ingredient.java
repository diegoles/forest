package blendedFruitDrink.op2;

import java.math.BigDecimal;

public enum Ingredient {
	STRAWBERRY_G("1.25"),
    BANANA_G("1.75"),
    MANGO_G("1.10"),
	CONDENSED_MILK_ML("0.90"),
    ICE_ML("0.75"),
    SUGAR_G("0.25");

    private final String name;
    private final BigDecimal cost;

    Ingredient(String cost) {
        this.name = this.name().replace("_", " ").toLowerCase();
        this.cost = new BigDecimal(cost);
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public String toString() {
        return this.name;
    }
}