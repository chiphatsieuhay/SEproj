package Tabu;

import java.util.ArrayList;

import seGui.Matrix;

public class TabuSearch {
	 	private TabuList tabuList;
	    private final Matrix matrix;
	    public int numberOfSolution = 0;
	    int[] currSolution;
	    public int numberOfIterations;
	    int problemSize;


	    private int[] bestSolution;
	    private int bestCost;

	    public int[][] solutions;
	    public ArrayList<Integer> bestSolutions = new ArrayList<Integer>();
	    public ArrayList<Integer> costForSolutions = new ArrayList<Integer>();

	    public TabuSearch(Matrix matrix) {
	        this.matrix = matrix;
	        problemSize = matrix.getEdgeCount();
	        numberOfIterations = problemSize * 10;

	        tabuList = new TabuList(problemSize);
	        setupCurrentSolution();
	        setupBestSolution();


	    }

	    private void setupBestSolution() {
	        bestSolution = new int[problemSize + 1];
	        System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);
	        bestCost = matrix.calculateDistance(bestSolution);
	        bestSolutions.add(numberOfSolution);
	        costForSolutions.add(bestCost);
	    }

	    private void setupCurrentSolution() {
	        currSolution = new int[problemSize + 1];
	        solutions = new int[10000][problemSize+1];
	        for (int i = 0; i < problemSize; i++) {
	            currSolution[i] = i;
	        	solutions[numberOfSolution][i] = i;}
	        currSolution[problemSize] = 0;
	        
	    }


	    private void printSolution(int[] solution) {
	    	numberOfSolution++;
//	        solutions[numberOfSolution]=  solution;
//	        System.out.print(numberOfSolution+":");
	        costForSolutions.add(matrix.calculateDistance(solution));
	        for (int i = 0; i < solution.length; i++) {
	        	solutions[numberOfSolution][i]= solution[i];
//	            System.out.print(solution[i] + " ");
	            // solutions[x][i]= solution[i];
	        }
//	        System.out.println();
	        
	    }
	    private void printBestSolution(int[] solution) {
//	    	System.out.print(bestSolutions.size()+":");
	    	bestSolutions.add(numberOfSolution);
	    	System.out.println(numberOfSolution+" "+bestCost);
	        for (int i = 0; i < solution.length; i++) {
//	            System.out.print(solution[i] + " ");
	            // solutions[x][i]= solution[i];
	        	
	        }
//	        System.out.println();
	        
	    }


	    public void printSolutions(){
	        for (int i = 0 ;i<=numberOfSolution;i++){
//	        	System.out.print(i+":");
	            for (int j = 0 ;j< currSolution.length;j++)System.out.print(solutions[i][j]+" ");
//	            System.out.println();
	        }
	    }

	    public void invoke() {
	    	outerloop:
	        for (int i = 0; i < numberOfIterations; i++) {
	            int city1 = 0;
	            int city2 = 0;

	            for (int j = 1; j < currSolution.length - 1; j++) {
	                for (int k = 2; k < currSolution.length - 1; k++) {
	                    if (j != k) {
	                        swap(j, k);
	                        printSolution(currSolution);
	                        int currCost = matrix.calculateDistance(currSolution);
	                        if ((currCost < bestCost) && tabuList.tabuList[j][k] == 0) {
	                            city1 = j;
	                            city2 = k;
	                            System.arraycopy(currSolution, 0, bestSolution, 0, bestSolution.length);
//	                            System.out.print("best solution now->\n");
	                            
	                            bestCost = currCost;
	                            printBestSolution(bestSolution);
	                            
	                        }
	                        if (numberOfSolution >= problemSize*100)break outerloop;
	                    }
	                }
	            }


	            if (city1 != 0) {
	                tabuList.decrementTabu();
	                tabuList.tabuMove(city1, city2);
//	                printSolution(currSolution);
	            }
	        }

	        System.out.println("Search done! \nBest Solution cost found = " + bestCost + "\nBest Solution :");
//	        printSolutions();
//	        printSolution(bestSolution);
//	        System.out.print(bestCost+" "+numberOfSolution);
//	        printBestSolution(bestSolution);
//	        for (int i =0 ; i< bestSolutions.size();i++) System.out.print(bestSolutions.get(i)+"\t");
	    }

	    private void swap(int i, int k) {
	        int temp = currSolution[i];
	        currSolution[i] = currSolution[k];
	        currSolution[k] = temp;
	    }
}
