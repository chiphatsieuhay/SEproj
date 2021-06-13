package seGui;







import java.util.Timer;
import java.util.TimerTask;

import Tabu.TabuSearch;
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



public class SeGuiController {
	
	int[][] m1;
	Matrix matrix;
	
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
	void runBtnPressed()  {
		matrix = new Matrix(list.size());
		matrix.printMatrix();
		drawEdges();
		runTabuSearch();
		
		
	}
	void runTabuSearch() {
		TabuSearch tabuSearch = new TabuSearch(matrix);  
        tabuSearch.invoke();   
		
	}
	
	void drawEdges() {
		for (int i = 0; i< list.size();i++) {
			for (int j = 0 ; j< i ; j++) {
				int tim = 0;
				for (int k = 0;k<list.size();k++) {
					if (list.get(k).IsThisNode(i)||list.get(k).IsThisNode(j)) {
						tim++;
						if (tim == 1) {
							this.S = list.get(k);
						}else if (tim==2) {
							this.E = list.get(k);
							break;
						}
						
						
					}
				}
				Edge e1 = new Edge(this.S, this.E, matrix.getWeight(i, j));
				graphArea.getChildren().add(0, e1.getLine());
//				graphArea.getChildren().add(e1.getLine());
				edges.addEdge(e1);
//				edges.showEdges();
			}
		}
	}

	int iGlobal = 0;
	Timer timer;
	
	void runSolution(int[] solution) {
		int[] so= {0,2,1,0};
		iGlobal=0;
		
		TimerTask task = new TimerTask() {
	        public void run() {
//	        	System.out.println("start point and end point is:"+start+" "+end);
	        	
	    		edges.searchEdgeWithInt(so[iGlobal], so[iGlobal+1]).getLine().setVisible(true);
	    		iGlobal++;
	    		if (iGlobal==so.length-1) timer.cancel();
	        }
	    };
	     timer = new Timer("Timer");
	    
	    timer.schedule(task,0,1000);
	    
	    
	    System.out.println("Hello istherew");
	    

	}
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
			if (this.S!=this.E) {
				int c = createDialog();
				if (c!=-1) {
					int tontai = 0;
					for (int j=0;j<edges.getSequence().size();j++) {
						if (edges.getSequence().get(j).existedEdge(S, E)) {
							System.out.println("ton tai");
							tontai =1; 
							break;
						}else {
							
						}
					}
					if (tontai == 0 ) {
						Edge e1 = new Edge(this.S, this.E, c);
						graphArea.getChildren().add(0, e1.getLine());
//						graphArea.getChildren().add(e1.getLine());
						edges.addEdge(e1);
//						edges.showEdges();
					}else {
						
					}
					
				}else {
					// cancel button pressed
				}
				
			}
			
			
					
			
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
        TextInputDialog td = new TextInputDialog();
  
        // setHeaderText
        td.setHeaderText("enter cost between them");

        if (td.showAndWait().isPresent()) {
        	 try {
                 int num = Integer.parseInt(td.getResult());
//                 System.out.println(num);
                 return num;
             } catch (NumberFormatException e) {
//             	System.out.println(td.getResult());
                 return createDialog();
             }
        }else return -1;
       
	}
	void makeMatrix() {
		m1 = new int[list.size()][list.size()];
		for (int i =0;i< edges.getSequence().size();i++) {
			m1[edges.getSequence().get(i).getIndexStartInInt()][edges.getSequence().get(i).getIndexEndInInt()] = edges.getSequence().get(i).getCost();
			m1[edges.getSequence().get(i).getIndexEndInInt()][edges.getSequence().get(i).getIndexStartInInt()] = edges.getSequence().get(i).getCost();
		}
//		for (int i =0;i<m1.length;i++) {
//			for (int j =0;j<m1.length;j++) {
//				System.out.print(m1[i][j]+"\t");
//			}
//			System.out.println();
//		}
	}
	void invisibleEdge() {
		for (int i =0;i< edges.getSequence().size();i++) {
			edges.getSequence().get(i).getLine().setVisible(false);
		}
	}
	
	
	
}
