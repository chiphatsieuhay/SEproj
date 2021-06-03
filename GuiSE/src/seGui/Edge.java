package seGui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge {
	private Node start;
	private Node end;
	private int cost;
	private Line line;
	public Edge(Node start, Node end, int cost) {
		super();
		this.start = start;
		this.end = end;
		this.cost = cost;
		line = new Line(this.start.getX()+15,this.start.getY()+15,this.end.getX()+15,this.end.getY()+15);
		line.setStroke(Color.MIDNIGHTBLUE);
        line.setStrokeWidth(3);
       
	}
	public Line getLine() {
		return line;
	}
	
	public Node getStart() {
		return start;
	}
	public Node getEnd() {
		return end;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	Boolean existedEdge(Node s, Node e) {
		return ((start == s&& end ==e)||(start == e&& end ==s));
	}
	public String getIndexStart() {
		return this.start.index();
	}
	public int getIndexStartInInt() {
		return Integer.parseInt(this.start.index());
	}
	public String getIndexEnd() {
		return this.end.index();
	}
	public int getIndexEndInInt() {
		return  Integer.parseInt(this.end.index());
	}
	
}
