package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SparseMatrix implements Matrix
{
	int hight, width;
	
	private class Pair{
		int first, second;
		Pair (int first, int second){
			this.first = first;
			this.second = second;
		}
		
		Pair getPair()
		{
			return this;
		}
		@Override
		public boolean equals(Object o)
		{
			if(!(o instanceof Pair)) return false;
					Pair pair = (Pair)o;
			return (this.first == pair.first) && this.second == pair.second;
		}
	}
	
	
	HashMap< Pair, Integer> value;
	public SparseMatrix(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		this.value = new HashMap<>();
		this.hight = scanner.nextInt();
		this.width = scanner.nextInt();
		Pair p;
		for(int i = 1; i <= this.width; i++)
		{
			for(int j = 1; j <= this.hight; j++)
			{
				int m = scanner.nextInt();
				if( m != 0) {
					p = new Pair(i,j);
					this.value.put(p.getPair(), m);
				}
			}
		}
	}
	
	@Override public void out() {
	System.out.println(this.hight + " " + this.width);
		for(int i = 1; i <= this.hight; i++ ) {
			for(int j = 1;j <= this.width; j++) {
				System.out.print(this.get(i, j) + " "); 
			}
			System.out.print("\n");
		}
	}
	
	@Override public int get(int i, int j) {
		if(i > this.hight || j > this.width) {
			System.out.println("OutOfBounds");
			System.exit(-1);
		}
		Pair p = new Pair(i, j);
		Object  m =  this.value.get(p.getPair());
		if(m == null)
			return 0;
		return (int) m;
	}
	
	public SparseMatrix (int hight, int width) {
		this.hight = hight;
		this.width = width;
		this.value = new HashMap<>();
	}
	
	@Override public Matrix mul(Matrix o)
	{
		
		if(this.width != o.getHight()){
			System.out.println("InvalidSizesOfMultipliedMatrix");
			System.exit(-1);
		}
		SparseMatrix X = new SparseMatrix(this.hight, o.getWidth());
		for(int i = 1; i <= X.hight; i++)
			for(int j = 1; j <= X.width; j++) {
				int sum = 0;
				for(int k = 1; k <= this.width; k++) {
					sum+= this.get(i, k) * o.get(k, j); 
				}
				if(sum != 0)
					X.value.put(new Pair(i, j), sum);
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
		SparseMatrix matrix = (SparseMatrix)o;
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
