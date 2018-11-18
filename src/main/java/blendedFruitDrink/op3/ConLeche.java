package blendedFruitDrink.op3;

public class ConLeche implements Cafe {

	private Cafe cafe;

	public ConLeche(Cafe cafe) {
		this.cafe = cafe;
	}

	@Override
	public String descripcion() {
		return cafe.descripcion() + " con Leche";
	}

	@Override
	public int precio() {
		return cafe.precio() + 20;
	}

}
