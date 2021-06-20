package ABC;

public class OngTho {
	private FoodSource foodSource = new FoodSource(); 
	private int C;
	public int getC() {
		return C;
	}
	public void setC() {
		C++;
	}
	public FoodSource getFoodSource() {
		return foodSource;
	}
	public void setFoodSource(FoodSource foodSource) {
		this.foodSource = foodSource;
	}
	
	public OngTho(FoodSource foodSource) {
		super();
		this.foodSource = foodSource;
		C = 0;
	}
}