package seGui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class Node {
	private double x;
	private double y;
	
	
	private Text text = new Text();
	private Circle circle;
	private StackPane stack;
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public Text getText() {
		return text;
	}
	public Circle getCircle() {
		return circle;
	}
	public StackPane getStack() {
		return stack;
	}
	public Node(double x, double y, int text, StackPane stack) {
		super();
		this.x = x;
		this.y = y;
		updateText(text);
		
		this.stack = stack;
		
		
		this.circle = new Circle(15);
	    
		circle.setStroke(Color.FORESTGREEN);

		circle.setStrokeType(StrokeType.INSIDE);
		circle.setFill(Color.AZURE);
		
		this.text.setBoundsType(TextBoundsType.VISUAL); 
		
		stack.relocate(x, y);
        stack.getChildren().add( circle);
        stack.getChildren().add( this.text);
        
        
	}
	public void updateText(int text) {
		this.text.setText(Integer.toString(text));
	}
	public String index() {
		return text.getText();
		
	}
	
}
