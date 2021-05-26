package seGui;



import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.scene.text.Text;


public class SeGuiController {
	
	ObservableList<Node> list = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Edge> weigthTbl;
	
	@FXML
	private TableColumn<Edge, String> StartCol;
	
	@FXML
	private TableColumn<Edge, String> EndCol;
	
	@FXML
	private TableColumn<Edge, Float> CostCol;
	
	
	@FXML
	private void initialize() {
		CostCol.setCellValueFactory(
			    new PropertyValueFactory<Edge,Float>("cost"));
		EndCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getIndexEnd()));
		StartCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getIndexStart()));
//		EndCol.setCellValueFactory(
//			    new PropertyValueFactory<Edge,String>( cellData -> new ReadOnlyStringWrapper(cellData.getValue().getdate())));
//			   
//		
//		StartCol.setCellValueFactory(
//			    new PropertyValueFactory<Edge,String>("text"));
		
		weigthTbl.setItems(this.edges.getSequence());
		
	}
	
	Color color = new Color(0, 0, 0, 0) ;
	
	
	
	
	
	
	enum Connect {
	    Dragging,
	    notDragging
	  }
	Connect connect = Connect.notDragging;
	Edges edges = new Edges();
	Node S;
	Node E;
	
	Boolean clickOnNode(double X,double Y,double eX,double eY) {
		
		if ((Math.abs(X-eX)<=17) && (Math.abs(Y-eY)<=17)){
			
			return true;
		}
		return false;
	}
	
	@FXML
    private Pane graphArea;
	
	@FXML
    private RadioButton addCityRadioBtn;
	@FXML
    private RadioButton removeCityRadioBtn;
	@FXML
    private RadioButton addEdgeRadioBtn;
	@FXML
    private RadioButton removeEdgeRadioBtn;
	
	@FXML
    void AddCity() {
		removeCityRadioBtn.setSelected(false);
		addEdgeRadioBtn.setSelected(false);
		removeEdgeRadioBtn.setSelected(false);
	
    }
	
	@FXML
    void RemoveCity() {
		addCityRadioBtn.setSelected(false);
		addEdgeRadioBtn.setSelected(false);
		removeEdgeRadioBtn.setSelected(false);
	
    }
	
	@FXML
    void AddEdge() {
		addCityRadioBtn.setSelected(false);
		removeCityRadioBtn.setSelected(false);
		removeEdgeRadioBtn.setSelected(false);
	
    }
	@FXML
	void RemoveEdge() {
		addCityRadioBtn.setSelected(false);
		removeCityRadioBtn.setSelected(false);
		addEdgeRadioBtn.setSelected(false);
	
    }
	
	@FXML
    void graphAreaClicked(MouseEvent event) {
		
		if (addCityRadioBtn.isSelected()) {
			StackPane stack = new StackPane();
//			Text text = new Text();
			Node node= new Node(event.getX()-15, event.getY()-15, list.size(), stack);;
			list.add(node);
	
//			text.setText(Integer.toString(list.indexOf(node)));
			
			
//			text.textProperty().bindBidirectional(list.indexOf(node));
			
	        graphArea.getChildren().add(node.getStack());

	        
		}else if (removeCityRadioBtn.isSelected()) {
			int a = 0;
			for (int i = 0; i < list.size(); i++) {
				 
	                if (clickOnNode(list.get(i).getX(),list.get(i).getY(),event.getX()-15,event.getY()-15)&&a==0) {
	                	 
	                    graphArea.getChildren().remove(list.get(i).getStack());
	                    list.remove(i);
	                   a =1 ;
	                    
	                }if(a ==1) {
	                	list.get(i).updateText(i);
	                }
	            }
		}
		
    }
	@FXML
	void graphAreaStart(MouseEvent event) {
		
		if (addEdgeRadioBtn.isSelected()) {
			
			connect = Connect.Dragging;
			for (int i = 0; i < list.size(); i++) {
				 
                if (clickOnNode(list.get(i).getX(),list.get(i).getY(),event.getX()-15,event.getY()-15)) {
                	 
                    S = list.get(i);
                    break;
                }
            }
		}else if (removeEdgeRadioBtn.isSelected()) {
			
			connect = Connect.Dragging;
			for (int i = 0; i < list.size(); i++) {
				 
                if (clickOnNode(list.get(i).getX(),list.get(i).getY(),event.getX()-15,event.getY()-15)) {
                	 
                    S = list.get(i);
                    break;
                }
            }
		}
    }
	
	
	
	@FXML
	void graphAreaFinish(MouseEvent event) {

		if (addEdgeRadioBtn.isSelected()&& connect == Connect.Dragging) {
			
			connect = Connect.notDragging;
			for (int i = 0; i < list.size(); i++) {
				 
                if (clickOnNode(list.get(i).getX(),list.get(i).getY(),event.getX()-15,event.getY()-15)) {
                	 E = list.get(i);
                    
                    break;
                }
            }
			
			Edge e1 = new Edge(this.S, this.E, 0);
			graphArea.getChildren().add(e1.getLine());
			edges.addEdge(e1);
			edges.showEdges();
			int c = createDialog();
			e1.setCost(c);			
			
		}else if (removeEdgeRadioBtn.isSelected()&& connect == Connect.Dragging) {
			
			connect = Connect.notDragging;
			for (int i = 0; i < list.size(); i++) {
				 
                if (clickOnNode(list.get(i).getX(),list.get(i).getY(),event.getX()-15,event.getY()-15)) {
                	 E = list.get(i);
                    
                    break;
                }
            }
			for (int i=0;i<edges.getSequence().size();i++) {
				if (edges.getSequence().get(i).existedEdge(S, E)) {
					 
                    graphArea.getChildren().remove(edges.getSequence().get(i).getLine());
                    edges.getSequence().remove(i);
                    break;
				}
			}
		}
    }
	
	
	int createDialog() {
		// create a text input dialog
        TextInputDialog td = new TextInputDialog("enter number");
  
        // setHeaderText
        td.setHeaderText("enter cost between them");
        
        td.showAndWait();
        try {
            int num = Integer.parseInt(td.getResult());
            System.out.println(num);
            return num;
        } catch (NumberFormatException e) {
        	System.out.println(td.getResult());
            return createDialog();
        }
//        if (td.getResult().)
//        if (td.getResult().getClass())
//        td.showAndWait();
//        try {
//        	return td.getResult()
//        }catch(Exception e) {
//        	return createDialog();
//        }
	}
	
}
