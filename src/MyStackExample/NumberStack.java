package MyStackExample;

public class NumberStack {

	private int count = 0;
	private int[] values = new int[2];

	public int getCount() {

		return count;
	}

	public void push(int i) {

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


	public static void main(String [] args){
		NumberStack stackEx = new NumberStack();
		stackEx.push(1);
		stackEx.getCount();
	}
}
