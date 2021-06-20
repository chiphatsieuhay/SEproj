package seGui;







import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ABC.ABC;
import Tabu.TabuSearch;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;



public class SeGuiController {
	
	int[][] m1;
	Matrix matrix;
	
	//variable for runTask
	int runOnBest = 0;
	public ArrayList<Integer> bestSolutions = new ArrayList<Integer>();
	public ArrayList<Integer> costForSolutions = new ArrayList<Integer>();
	public int[][] solutions;
	public int numberOfSolution;
	//end variable
	
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
	
	Color colorCur = Color.GREEN;
	Color colorBest = Color.RED;
	

	
	enum Connect {
	    Dragging,
	    notDragging
	  }
	Connect connect = Connect.notDragging;
	
	Edges edges = new Edges();
	
	Node S;
	Node E;
	TabuSearch tabuSearch;
	Boolean clickOnNode(double X,double Y,double eX,double eY) {
		
		if ((Math.abs(X-eX)<=17) && (Math.abs(Y-eY)<=17)){
			
			return true;
		}
		return false;
	}
	
	@FXML
    private Pane graphArea;
	@FXML
	private TextField currCostTf;
	@FXML
	private TextField bestCostTf;
	//edge radio btn
	@FXML
    private RadioButton addCityRadioBtn;
	@FXML
    private RadioButton removeCityRadioBtn;
	@FXML
    private RadioButton addEdgeRadioBtn;
	@FXML
    private RadioButton removeEdgeRadioBtn;
	//algorithm btn
	@FXML
    private RadioButton tabuSeachBtn;
	@FXML
    private RadioButton sABtn;
	@FXML
    private RadioButton aBCBtn;
	//runbtn
	@FXML
    private Button runBtn;
	@FXML
    private Button generateBtn;
	@FXML
    private Button newBtn;

	
	@FXML
	void didPressTabuSearch(){
		tabuSeachBtn.setSelected(true);
		sABtn.setSelected(false);
		aBCBtn.setSelected(false);
	}
	@FXML
	void didPressSABtn(){
		tabuSeachBtn.setSelected(false);
		sABtn.setSelected(true);
		aBCBtn.setSelected(false);
	}
	@FXML
	void didPressABCBtn(){
		tabuSeachBtn.setSelected(false);
		sABtn.setSelected(false);
		aBCBtn.setSelected(true);
	}
	@FXML
    void AddCity() {
		removeCityRadioBtn.setSelected(false);
		addEdgeRadioBtn.setSelected(false);
		removeEdgeRadioBtn.setSelected(false);
		addCityRadioBtn.setSelected(true);
    }
	
	@FXML
    void RemoveCity() {
		addCityRadioBtn.setSelected(false);
		addEdgeRadioBtn.setSelected(false);
		removeEdgeRadioBtn.setSelected(false);
		removeCityRadioBtn.setSelected(true);
    }
	
	@FXML
    void AddEdge() {
		addEdgeRadioBtn.setSelected(true);
		addCityRadioBtn.setSelected(false);
		removeCityRadioBtn.setSelected(false);
		removeEdgeRadioBtn.setSelected(false);
	
    }
	@FXML
	void RemoveEdge() {
		addCityRadioBtn.setSelected(false);
		removeCityRadioBtn.setSelected(false);
		addEdgeRadioBtn.setSelected(false);
		removeEdgeRadioBtn.setSelected(true);
    }
	
	@FXML
	void didPressGenerateBtn(){
		matrix = new Matrix(list.size());
		matrix.printMatrix();
		drawEdges();
	}
	@FXML
	void didPressNewBtn(){
		for (int i=edges.getSequence().size()-1;i>=0;i--) {	
//				System.out.println("remove the edges"+edges.getSequence().get(i).getIndexStart()+" "+edges.getSequence().get(i).getIndexEnd());
                graphArea.getChildren().remove(edges.getSequence().get(i).getLine());
                edges.getSequence().remove(i);
             
			
		}
//		System.out.println("this is list size"+list.size());
		int listSize = list.size();
		for (int i = listSize-1; i >= 0; i--) {		
//			System.out.println("remove city i"+i +list.get(i).indexByInt());
                graphArea.getChildren().remove(list.get(i).getStack());
                list.remove(i);
        }
	}
	
	@FXML
	void runBtnPressed()  {
		disableBtn(true);
		if (tabuSeachBtn.isSelected()==true) {
			runTabuSearch();
		}
		else if (aBCBtn.isSelected()==true) {
			runABC();
		}else {
			
		}
		
		
		
	}	
	void runTabuSearch() {
		tabuSearch = new TabuSearch(matrix);  
        tabuSearch.invoke();   
        edges.inVisibleEdgeAll();
        bestSolutions = tabuSearch.bestSolutions;
        solutions = tabuSearch.solutions;
        numberOfSolution = tabuSearch.numberOfSolution;
        costForSolutions = tabuSearch.costForSolutions;
//        for(int i=0;i<=numberOfSolution;i++) {
//        	System.out.print(i+":");
//        	for(int j =0;j <matrix.getSize();j++) {
//        		System.out.print(solutions[i][j] + "  ");
//        	}
//        	System.out.println("\t" + costForSolutions.get(i));
//        }
//        for (int i = 0; i< bestSolutions.size();i++)System.out.print(bestSolutions.get(i)+" ");
//        System.out.println("number of solutions"+numberOfSolution);
		runSolution();
	}
	void runABC() {
		ABC abc  = new ABC(matrix);
		abc.invoke();
		edges.inVisibleEdgeAll();
		this.solutions = abc.getSolution();
		this.bestSolutions = abc.getBestSolution();
		numberOfSolution = abc.getNumberOfSolution()-1;
        costForSolutions = abc.getCostOfSolution();
//        for (int i =0; i<= numberOfSolution;i++) {
//        	System.out.println("\t" + costForSolutions.get(i));
//        }
//        for(int i=0;i<=numberOfSolution;i++) {
//        	System.out.print(i+":");
//        	for(int j =0;j < abc.getD();j++) {
//        		System.out.print(solutions[i][j] + "  ");
//        	}
//        	System.out.println("\t" + costForSolutions.get(i));
//        }
//        for (int i = 0; i< bestSolutions.size() ; i++) {
//        	System.out.print("\t" + bestSolutions.get(i));
//        }
        runSolution();
        }
	void disableBtn(boolean bool) {
		tabuSeachBtn.setDisable(bool);
		sABtn.setDisable(bool);
		aBCBtn.setDisable(bool);
		
		addEdgeRadioBtn.setDisable(bool);
		addCityRadioBtn.setDisable(bool);
		removeCityRadioBtn.setDisable(bool);
		removeEdgeRadioBtn.setDisable(bool);
		
		runBtn.setDisable(bool);
		generateBtn.setDisable(bool);
		newBtn.setDisable(bool);
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
	
	void runSolution() {
		runOnBest=0;
		iGlobal=0;
		 MyTask myTask = new MyTask();
		 timer = new Timer();
		 timer.schedule(myTask, 0, 100);
	    

	}

	@FXML
    void graphAreaClicked(MouseEvent event) {
		
		if (addCityRadioBtn.isSelected()) {
			StackPane stack = new StackPane();
			Node node= new Node(event.getX()-15, event.getY()-15, list.size(), stack);;
			list.add(node);
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

	}
	
	public class MyTask extends TimerTask {
		  @Override
		  public void run() {		    
		  edges.inVisibleEdgeAll();
		  	
		  	if (iGlobal == bestSolutions.get(runOnBest)&& runOnBest<bestSolutions.size()) 
		  	{
		  		edges.visibleEdges(solutions[iGlobal], colorBest);  		
		  		bestCostTf.setText(String.valueOf(costForSolutions.get(iGlobal)));  		
		  		currCostTf.setText(String.valueOf(costForSolutions.get(iGlobal)));
		  		if (runOnBest==bestSolutions.size()-1) {}
		  		else runOnBest++;
		  	}
		  	else {
		  		edges.visibleEdges(solutions[iGlobal], colorCur);	  		
		  		currCostTf.setText(String.valueOf(costForSolutions.get(iGlobal)));
		  	}	
		    if (iGlobal==numberOfSolution) {
		    	disableBtn(false);
		    	timer.cancel();
		    } 
		    iGlobal++;
		  }
		}		
}
