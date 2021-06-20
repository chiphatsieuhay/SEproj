package ABC;

import java.util.Arrays;
import java.util.Random;

public class FoodSource {
	private  int[] element;
	private int size_length;
	public FoodSource() {
		
	}

	public FoodSource(int[] element) {
		super();
		this.element = element;
		this.size_length = element.length;
	}

	public int[] getElement() {
		return element;
	}

	public void setElement(int[] element) {
		this.element = element;
	}
	public int[] LanCan() { 
		  	Random rd = new Random();
		    int number1 = 1+rd.nextInt(this.size_length-2);
		    int number2 ;//= -4 + rd.nextInt(4);  
		    do{
		    	number2 = 1+rd.nextInt(this.size_length-2);
		    }while(number1 == number2);
		    int temp = element[number1];
	        element[number1] = element[number2];
	        element[number2] = temp;
	        return element;
	}
	public int[] generateSolution()
	{	
	    int index;
	    Random random = new Random();
	    
	    	for (int i = 1; i < this.size_length-2; i++) {

				int rd = 1 + random.nextInt(this.size_length-2);

				int temp = element[rd];

				element[rd]=element[i];

				element[i]= temp;
	    	}
			return this.element;
	}
}

