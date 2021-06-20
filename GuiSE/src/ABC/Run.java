package ABC;

import seGui.Matrix;

public class Run {
	public static void main(String[] args) {
		
		int [][] m = {{0,132,217,164,158},
					{132,0,290,201,79},
					{217,290,0,113,303},
					{164,201,113,0,196},
					{158,79,303,196,0}};
		Matrix matran = new Matrix(m);
		ABC obj = new ABC(matran);
		obj.invoke();
	}
}

