package seGui;

public class TabuList {
	 int [][] tabuList ;
	    int tener;
	    public TabuList(int numCities){
	        tabuList = new int[numCities][numCities]; //city 0 is not used here, but left for simplicity
	        this.tener = (int) Math.sqrt(numCities);
	    }

	    public void tabuMove(int city1, int city2){ 
	        tabuList[city1][city2]+= tener;
	        tabuList[city2][city1]+= tener;

	    }

	    public void decrementTabu(){
	        for(int i = 0; i<tabuList.length; i++){
	            for(int j = 0; j<tabuList.length; j++){
	                tabuList[i][j]-=tabuList[i][j]<=0?0:1;
	            }
	        }
	    }
}
