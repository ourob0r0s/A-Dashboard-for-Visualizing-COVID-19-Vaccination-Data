public class DynamicArray<T> implements Array<T> {
	 private T[] arr;
	  private int size = 0; 
	  private int capacity; 
	  
	  public DynamicArray(){
		  arr = (T[]) new Object[0];
	  }
	  
	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public T get(int i) {
		if(i >= size)
			throw new ArrayIndexOutOfBoundsException();
		return arr[i];
	}
	

	@Override
	public void set(int i, T e) {
		if(i >= size)
			throw new ArrayIndexOutOfBoundsException();
		arr[i] = e;;

	}

	@Override
	public void add(T e) {
		  if (size >= capacity) {
		      if (capacity == 0) capacity = 1;
		      else capacity *= 2; 
		      T[] new_arr = (T[]) new Object[capacity];
		      for (int i = 0; i < size; i++) new_arr[i] = arr[i];
		      arr = new_arr;
		    }

		    arr[size++] = e;
	}

}
