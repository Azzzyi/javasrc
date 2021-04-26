package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class DenseMatrix implements Matrix
{
		
	int hight, width;
	int[][] value;
	public DenseMatrix(String fileName) throws IOException {
		
		Scanner scanner = new Scanner(new File(fileName));
		this.hight = scanner.nextInt();
		this.width = scanner.nextInt();
		this.value = new int[this.hight + 1][this.width + 1];
		for(int i = 1; i <= this.width; i++)
		{
			for(int j = 1; j <= this.hight; j++)
			{
				this.value[i][j] = scanner.nextInt();
			}
		}
	}
	
	@Override public int get(int i, int j) {
		if(i > this.hight || j > this.width) {
			System.out.println("OutOfBounds");
			System.exit(-1);
		}
		return this.value[i][j];
	}

	public DenseMatrix (int hight, int width) {
		this.hight = hight;
		this.width = width;
		this.value = new int[this.hight + 1][this.width + 1];
	}
	
	@Override public void out() {
		System.out.println(this.hight + " " + this.width);
		for(int i = 1; i <= this.hight; i++ ) {
			for(int j = 1; j <= this.width; j++ ) {
				System.out.print(this.value[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	@Override public Matrix mul(Matrix o)
	{
		if(this.width != o.getHight()){
			System.out.println("InvalidSizesOfMultipliedMatrix");
			System.exit(-1);
		}
		DenseMatrix X = new DenseMatrix(this.hight, o.getWidth());
		for(int i = 1; i <= X.hight; i++)
			for(int j = 1; j <= X.width; j++) {
				int sum = 0;
				for(int k = 1; k <= this.width; k++) {
					sum+= this.get(i, k) * o.get(k, j); 
				}
					X.value[i][j] = sum;
			}
		return X;
	}

	@Override public Matrix dmul(Matrix o)
	{
		return null;
	}

	
	@Override public boolean equals(Object o) {
		if(o == null)
			return false;			
		if(o.getClass() != this.getClass())
			return false;
		DenseMatrix matrix = (DenseMatrix)o;
		if(hight != matrix.hight)
			return false;
		if(width != matrix.width)
			return false;
		if(value != matrix.value)
			return false;
		return true;
	}

	@Override
	public int getHight() {
		return hight;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setHight(int hight) {
		this.hight = hight;
		
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

}
