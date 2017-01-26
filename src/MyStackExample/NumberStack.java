package MyStackExample;

import java.util.ArrayList;

public class NumberStack {

	private int capacity;		//the stack maximum capacity
	private int count = 0;		//the number of elements put on the stack
	private int[] values;

	public NumberStack(int n){
		capacity = n;
		values = new int[n];
	}

	public int getCount() {

		return count;
	}

	public void push(int i) {

		if(count==capacity){
			throw new IllegalStateException();
		}
		values[count++] = i;
	}

	public int pop() {

		if (isEmpty()){ //refaktoryzacja
			throw new IllegalStateException();
		}

		return values[--count];
	}

	public boolean isEmpty(){
		
		return getCount()==0;  //tak jest lepiej niz napisac count==0 bo count juz jest przetestowane
		
		}

	public void clear() {
		
		while(getCount()>0){
			this.pop();
		}
		//mozna zrobic po prostu count = 0; nie trzeba robic pop w petli
		
	}


	public int getCapacity() {
		return this.capacity;
	}
}
