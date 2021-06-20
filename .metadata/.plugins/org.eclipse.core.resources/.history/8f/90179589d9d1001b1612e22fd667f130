package ABC;

import java.util.ArrayList;

import seGui.Matrix;

public class Run {
	public static ArrayList<Integer> bestSolutions = new ArrayList<Integer>();
	public static ArrayList<Integer> costForSolutions = new ArrayList<Integer>();
	public static int[][] solutions;
	public static int numberOfSolution;
	public static void main(String[] args) {
		int runOnBest = 0;
		
		int [][] m = {{0,132,217,164,158},
					{132,0,290,201,79},
					{217,290,0,113,303},
					{164,201,113,0,196},
					{158,79,303,196,0}};
		Matrix matran = new Matrix(m);
		ABC abc = new ABC(matran);
		abc.invoke();
		solutions = abc.getSolution();
		bestSolutions = abc.getBestSolution();
		numberOfSolution = abc.getNumberOfSolution();
        costForSolutions = abc.getCostOfSolution();
//        for (int i =0; i<= numberOfSolution;i++) {
//        	System.out.println("\t" + costForSolutions.get(i));
//        }
        for(int i=0;i<numberOfSolution;i++) {
        	for(int j =0;j < abc.getD();j++) {
        		System.out.print(solutions[i][j] + "  ");
        	}
        	System.out.println("\t" + costForSolutions.get(i));
        }
	}
}