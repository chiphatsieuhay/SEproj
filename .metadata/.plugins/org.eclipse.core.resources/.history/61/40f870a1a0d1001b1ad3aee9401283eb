package ABC;

import java.util.Random;

public class FoodSource {
	private  int[] element;
	private int[] preelement;
	private int size_length;
	public FoodSource() {
		
	}
	public int[] getPreelement() {
		return preelement;
	}

	public void setPreelement(int[] preelement) {
		this.preelement = preelement;
	}

	public FoodSource(int[] element) {
		super();
		this.element = element;
		this.preelement = element;
		this.size_length = element.length;
	}

	public int[] getElement() {
		return element;
	}

	public void setElement(int[] element) {
		this.element = element;
	}
	public void LanCan() { 
		  	Random rd = new Random();
		    int number1 = 1+rd.nextInt(this.size_length-2);
		    int number2 ;//= -4 + rd.nextInt(4);  
		    do{
		    	number2 = 1+rd.nextInt(this.size_length-2);
		    }while(number1 == number2);
		    int temp = this.element[number1];
	        this.element[number1] = this.element[number2];
	        this.element[number2] = temp;
	        
	}
	public void PreReset() {
		this.element = this.preelement;
	}
	public void Reset() {
		this.preelement = this.element;
	}
}
