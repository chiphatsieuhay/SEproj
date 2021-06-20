package ABC;

import java.util.Arrays;
import java.util.Random;

import seGui.Matrix;

public class ABC implements Algorithm{
	private Matrix matrix;
	private int N;
	private int D;
	private int L;
	private int I;
	private int[] XX = new int[1000];
	private int[] BestSolution;
	private OngTho[] ongtho = new OngTho[1000];
	private OngQuanSat[] ongquansat = new OngQuanSat[1000];
	private float[] fit = new float[1000];
	private float sumfit;
	private float[] Proba = new float[1000];
	private int[] cost = new int[1000];
	private int[] precost = new int[1000];
	public ABC() {
	}
	public ABC(Matrix abc, int n, int i) {
		super();
		this.matrix = abc;
		N = n;
		I = i;
		D = abc.getSize() + 1;
		L = N*(D-1)/2;
		
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
	    int index;
	    Random random = new Random();
	    for(int j = 0 ; j <= this.N/2 -1 ;j++) {
	    
	    	for (int i = 1; i < D-2; i++) {

				int rd = 1 + random.nextInt(D-2);

				int temp = this.ongtho[j].getFoodSource().getElement()[rd];

				this.ongtho[j].getFoodSource().getElement()[rd] = this.ongtho[j].getFoodSource().getElement()[i];

				this.ongtho[j].getFoodSource().getElement()[i]= temp;

			}
	    	this.ongtho[j].getFoodSource().Reset();
	    	
	    		System.out.print("(("+j+"))"+" -> ");
	    		System.out.println(Arrays.toString(this.ongtho[j].getFoodSource().getElement()));
			}
	    for (int i = 0; i < D-1; i++) {
	    	System.out.print("(("+i+"))"+" -> ");
	    	System.out.println(Arrays.toString(this.ongtho[i].getFoodSource().getElement()));
	    }
	    	}
	
	public void init() {
		int arr[] = new int[D];
		arr[0] = 0;
		arr[D-1] = 0;
		for(int i = 1 ; i <= this.D - 2; i++) {
			arr[i] = i;}
		FoodSource init = new FoodSource(arr);
		for(int i = 0 ; i<=this.N/2-1; i++ ) {
			this.ongtho[i] = new OngTho(init);
			this.ongquansat[i] = new OngQuanSat(arr);
			
		}
		shuffleArray();
		
	}
	public void update() {
		for(int i = 0; i <= N/2 -1 ; i++) {
			this.precost[i] = matrix.calculateDistance(ongtho[i].getFoodSource().getPreelement());
			System.out.print("--"+i+"OLD--");
			System.out.println(Arrays.toString(this.ongtho[i].getFoodSource().getElement()));
			this.ongtho[i].getFoodSource().LanCan();
			System.out.print("--"+i+"NEW--");
			System.out.println(Arrays.toString(this.ongtho[0].getFoodSource().getPreelement()));
			this.cost[i] = matrix.calculateDistance(ongtho[i].getFoodSource().getElement());
			if(this.cost[i] <= this.precost[i]) {
				this.ongtho[i].getFoodSource().Reset();
				//this.precost[i] = this.cost[i];
				this.fit[i] = 1/this.cost[i];
				System.out.print("=>(("+ i +"))  ");
				/*for(int q = 0; q <= D-1; q++) {
					System.out.print(this.ongtho[i].getFoodSource().getElement()[q] + " -> ");
					//System.out.print(this.ongtho[i].getFoodSource().getPreelement()[q] + " -> ");
				
			}	
			System.out.println(this.precost[i] + "->>>>" + this.cost[i] );
				//this.precost[i] = this.cost[i];
				System.out.print("\n");*/
				System.out.println(Arrays.toString(this.ongtho[0].getFoodSource().getElement()));
				System.out.println(this.precost[i] + "->>>>" + this.cost[i] );
			}
			else {
				this.ongtho[i].getFoodSource().PreReset();
				//this.cost[i] = this.precost[i];
				this.fit[i]=1/this.precost[i];
				System.out.print("=>(("+ i +"))  ");
				/*for(int q = 0; q <= D-1; q++) {
					System.out.print(this.ongtho[i].getFoodSource().getElement()[q] + " -> ");
					System.out.print(this.ongtho[i].getFoodSource().getPreelement()[q] + " -> ");
					
				
			}	
				
				//this.cost[i] = this.precost[i];
				
				System.out.print("\n");*/
				System.out.println(Arrays.toString(this.ongtho[0].getFoodSource().getElement()));
				System.out.println(this.precost[i] + "->>>>" + this.cost[i] );
			}
			
		}
		for(int i=0;i<=N/2-1;i++) {
			this.sumfit = this.fit[i];
		}
		for(int i=0;i<=N/2-1;i++) {
			this.Proba[i] = this.fit[i]/this.sumfit;
		}
	}
	public void ChooseOngTho() {
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
	public int[] getSolution() {
		return BestSolution;
	}
	public void setSolution(int[] solution) {
		BestSolution = solution;
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
		init();
		for(int i = 0 ; i <= this.I - 1; i++) {
		update();
		ChooseOngTho();
		}
	}
}