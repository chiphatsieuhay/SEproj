package ABC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import seGui.Matrix;

public class ABC implements Algorithm{
	private Matrix matrix;
	private int N;
	private int D;
	private int L;
	private int I;
	private int[] XX = new int[1000];
	//private int[] BestSolution;
	public ArrayList<Integer> bestSolution = new ArrayList<Integer>();
	private int[][] Solution;
	private OngTho[] ongtho = new OngTho[1000];
	private OngQuanSat[] ongquansat = new OngQuanSat[1000];
	private int[] Temp1 = new int [100000];
	private float[] fit = new float[1000];
	private float sumfit;
	private int NumberOfSolution;
	private ArrayList<Integer> CostOfSolution = new ArrayList<Integer>();
	private float[] Proba = new float[1000];
	private int[] cost = new int[1000];
	private int[] precost = new int[1000];
	public ABC() {
	}
	public ABC(Matrix abc) {
		super();
		this.matrix = abc;
		N = abc.getSize()*2;
		I = N;
		D = abc.getSize() + 1;
		L = N*(D-1)/2;
		Solution = new int[N*I/2][D];
		NumberOfSolution = 0;
	}

	

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getD() {
		return D;
	}

	public void setD(int d) {
		D = d;
	}

	public int getL() {
		return L;
	}

	public void setL(int l) {
		L = l;
	}

	public int getI() {
		return I;
	}

	public void setI(int i) {
		I = i;
	}

	public OngTho[] getOngtho() {
		return ongtho;
	}

	public void setOngtho(OngTho[] ongtho) {
		this.ongtho = ongtho;
	}
	public void shuffleArray()
	{
	    for(int j = 0 ; j <= this.N/2 -1 ;j++) {
	    
		    this.ongtho[j].getFoodSource().generateSolution();
	    	//this.ongtho[j].getFoodSource().Reset();
	    	
	    		System.out.print("(("+j+"))"+" -> ");
	    		System.out.println(Arrays.toString(this.ongtho[j].getFoodSource().getElement()));
			}
	}
	public OngTho infoOfBee() {
			OngTho O = null;
			int arr[] = new int[D];
			arr[D-1] = 0;
			for(int i = 1 ; i <= this.D - 2; i++) {
				arr[i] = i;}
			FoodSource init = new FoodSource(arr);
			O = new OngTho(init);
		return O;
	}
	public OngQuanSat infoOfOBee() {
		OngQuanSat O= null;
		int arr[] = new int[D];
		arr[D-1] = 0;
		for(int i = 1 ; i <= this.D - 2; i++) {
			arr[i] = i;}
		O = new OngQuanSat(arr);
		return O;
	}
	public void init() {
		
		for(int i = 0 ; i<=this.N/2-1; i++ ) {
			this.ongtho[i] = infoOfBee();
			this.ongquansat[i] = infoOfOBee();
			
		}
		shuffleArray();
		
	}
	public void EqualArray(int[] A,int[] B) {
		for(int i = 0 ; i<=D-1 ; i++) {
			A[i] = B[i];
		}
	}
	
	public void update() {
			
		for(int i = 0; i <= N/2 -1 ; i++) {
			int[] Temp = new int[D];
			EqualArray(Temp,this.ongtho[i].getFoodSource().getElement());
			this.precost[i] = matrix.calculateDistance(Temp);
		
			this.ongtho[i].getFoodSource().LanCan();
			this.cost[i] = matrix.calculateDistance(ongtho[i].getFoodSource().getElement());
			if(this.cost[i] <= this.precost[i]) {
				
				this.fit[i] = 1/(float)this.cost[i];
				//System.out.println(fit[i]);
				System.out.print("=>(("+ i +"))  ");
				
				System.out.println(Arrays.toString(this.ongtho[i].getFoodSource().getElement()));
				System.out.println(this.precost[i] + "->>>>" + this.cost[i] );
				//System.out.println(matrix.calculateDistance(this.ongtho[i].getFoodSource().getElement()));
			}
			else {
				this.ongtho[i].getFoodSource().setElement(Temp);
				this.fit[i]=1/(float)this.precost[i];
				//System.out.println(fit[i]);
				//System.out.println(this.precost[i] + "->>>>" + this.cost[i] );
				System.out.print("=>(("+ i +"))  ");
				System.out.println(Arrays.toString(this.ongtho[i].getFoodSource().getElement()));
				System.out.println(this.precost[i] + "->>>>" + this.cost[i] );
				//System.out.println(matrix.calculateDistance(this.ongtho[i].getFoodSource().getElement()));
			}
			
		}
		for(int i=0;i<=N/2-1;i++) {
			this.sumfit = this.sumfit + this.fit[i];
			//System.out.println(sumfit);
		}
		for(int i=0;i<=N/2-1;i++) {
			this.Proba[i] = 100*(float)this.fit[i]/this.sumfit;
			
			//System.out.println(Proba[i]);
		}
	}
	/*public void ChooseOngTho() {
		for (int j=0; j<=this.Proba[0]*100-1;j++) {
			this.XX[j] = 0;
		}
		for (int i = 1 ; i <= this.N/2-1;i++) {
			int z = (int) this.Proba[i-1]*100 ;
			for(int j = 0 ; j <= this.Proba[i]*100 -1 ; j++) {
				this.XX[j+z] = i;
			}
			//System.out.print(Arrays.toString(XX));
		}
		for(int i = 0; i <= N/2 - 1 ; i++) {
			Random rd = new Random();   // khai báo 1 đối tượng Random
		    int number1 =rd.nextInt(99);
		    this.ongquansat[i].setFoodSoure(this.ongtho[XX[number1]].getFoodSource().getElement());
		}
		this.setSolution(this.ongquansat[0].getFoodSoure());
	
		for(int i=1 ; i<=this.N/2 - 1 ; i++) {
			int cost = matrix.calculateDistance(this.ongquansat[i].getFoodSoure());
			if(cost < this.matrix.calculateDistance(this.BestSolution) ) {
				this.setSolution(this.ongquansat[i].getFoodSoure());
			}
		}
		System.out.println("ket qua");
		for(int i = 0; i <= this.D-1 ; i++) {
			System.out.print( this.BestSolution[i] + " -> ");
		}
		System.out.println("");
		System.out.println(" The shortest path : " + this.matrix.calculateDistance(this.BestSolution));
		
	}*/
	public Matrix getMatrix() {
		return matrix;
	}
	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}
	public ArrayList<Integer> getBestSolution() {
		return bestSolution;
	}
	public void setBestSolution(ArrayList<Integer> bestSolution) {
		this.bestSolution = bestSolution;
	}
	public int[][] getSolution() {
		return Solution;
	}
	public void setSolution(int[][] solution) {
		Solution = solution;
	}
	public Matrix getAbc() {
		return matrix;
	}
	public void setAbc(Matrix abc) {
		this.matrix = abc;
	}
	public int[] getXX() {
		return XX;
	}
	public void setXX(int[] xX) {
		XX = xX;
	}
	public OngQuanSat[] getOngquansat() {
		return ongquansat;
	}
	public void setOngquansat(OngQuanSat[] ongquansat) {
		this.ongquansat = ongquansat;
	}
	public float[] getFit() {
		return fit;
	}
	public void setFit(float[] fit) {
		this.fit = fit;
	}
	public float getSumfit() {
		return sumfit;
	}
	public void setSumfit(float sumfit) {
		this.sumfit = sumfit;
	}
	public float[] getProba() {
		return Proba;
	}
	public void setProba(float[] proba) {
		Proba = proba;
	}
	public void invoke(){
		int[] temp = new int[N/2];
		init();
		for(int q = 0 ; q <= this.I - 1; q++) {
			sumfit = 0;
		update();
		int z = 0;
		int k = q*N/2;
		for (int j = 0; j<=N/2-1;j++ ) {
			EqualArray(Solution[k+j], ongtho[j].getFoodSource().getElement());
			CostOfSolution.add(this.matrix.calculateDistance(Solution[k+j]));
			NumberOfSolution++;
		}
		for (int j=0; j<=(int)this.Proba[0]-1;j++) {
			this.XX[j] = 0;
		}
		for (int i = 1 ; i <= this.N/2-1;i++) {
			z = z + (int) this.Proba[i-1] ;
			for(int j = 0 ; j <= (int)this.Proba[i] -1 ; j++) {
				this.XX[j+z] = i;
			}
		}
		//System.out.print(Arrays.toString(XX));
		for(int i = 0; i <= N/2-1 ; i++) {
			Random rd = new Random();   // khai báo 1 đối tượng Random
		    int number1 = rd.nextInt(99);
		    EqualArray(this.ongquansat[i].getFoodSoure(), this.ongtho[XX[number1]].getFoodSource().getElement());
		    Temp1[k+i] = XX[number1] + k;
		}
		int A;
		A = matrix.calculateDistance(this.ongquansat[0].getFoodSoure());
		System.out.println(A);
		int temp1;
		for(int i = k;i<=k+N/2-1;i++) {
		
			if(A < matrix.calculateDistance(this.ongquansat[i-k].getFoodSoure())) {
				A = matrix.calculateDistance(this.ongquansat[i-k].getFoodSoure());
				temp1 = Temp1[i];
				bestSolution.add(Temp1[i]);
			}
		
		}
	}
		for(int i = 0 ; i < CostOfSolution.size() ; i++) {
		System.out.print("("+i+") ->");
			for(int a =0 ; a <= D-1 ; a++) {
        		System.out.print(Solution[i][a] + "  ");
        	}
		System.out.println(CostOfSolution.get(i) + " ");
		}
		//System.out.println(NumberOfSolution);
		//System.out.println(bestSolution.size());
		for(int i = 0;i < bestSolution.size();i++) {
			System.out.println(bestSolution.get(i));
		}
	}
	public ArrayList<Integer> getCostOfSolution() {
		return CostOfSolution;
	}
	public void setCostOfSolution(ArrayList<Integer> costOfSolution) {
		CostOfSolution = costOfSolution;
	}
	public int getNumberOfSolution() {
		return NumberOfSolution;
	}
	public void setNumberOfSolution(int numberOfSolution) {
		NumberOfSolution = numberOfSolution;
	}

}
