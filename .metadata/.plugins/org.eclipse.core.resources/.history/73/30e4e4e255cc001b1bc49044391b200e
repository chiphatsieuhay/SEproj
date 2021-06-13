package seGui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Edges {
	private ObservableList<Edge> sequence = FXCollections.observableArrayList();
	public ObservableList<Edge> getSequence() {
		return sequence;
	}
	
	public void clearEdges() {
		sequence.clear();
    }
	
	public int existeEdge(Edge edge) {
		
		for (int i = 0;i < sequence.size();i++) {
			
			if (edge.getStart().getX()==sequence.get(i).getStart().getX() &&
					edge.getStart().getY()==sequence.get(i).getStart().getY() &&
					edge.getEnd().getX() == sequence.get(i).getEnd().getX() &&
							edge.getEnd().getY() == sequence.get(i).getEnd().getY()
					) {
//				System.out.println("edge exists");
				return 1;
			}
		}
//		System.out.println("edge did not exist");
		return -1;
	}
	public Boolean addEdge(Edge edge) {
		
			if (existeEdge(edge)!=-1) {
				return false;
			}else {
				sequence.add(edge);
//			System.out.println("The edge has been added to the current sequence!");
			return true;
			}
		}
	
	public Boolean removeEdge(Edge edge) {
		
		if (existeEdge(edge)!=-1) {
			sequence.remove(edge);
			return true;
		}else {
		
//		System.out.println("The edge has been added to the current sequence!");
		return false;
		}
		
	}
	
	public void showEdges() {
		for (int i=0;i<sequence.size();i++) {
			System.out.println(sequence.get(i).getStart().getX()+"\t"+sequence.get(i).getStart().getY());
		}
	}
	public Edge searchEdgeWithInt(int s, int e) {
		for (int i =0 ;i< sequence.size();i++) {
			if ((sequence.get(i).getIndexStartInInt() == e && sequence.get(i).getIndexEndInInt() == s)||(sequence.get(i).getIndexStartInInt() == s && sequence.get(i).getIndexEndInInt() == e)) {
				return sequence.get(i);
			}
		}
		return null;
	}
		
	
}
