package main;

import java.io.IOException;

public class Main {
	public static void main(String[] arg) throws IOException {
		DenseMatrix M1 = new DenseMatrix("Matrix1.txt");
		SparseMatrix M2 = new SparseMatrix("Matrix2.txt");
		M1.out();
		M2.out();
		M1.mul(M2).out();;
	}
}
