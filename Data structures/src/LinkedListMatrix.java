
public class LinkedListMatrix<T> {
	private MNode<T> headC;
	private MNode<T> headR;
	private MNode<T> current;
	private int nBC ;
	private int nBR  ;

	public int getnBC() {
		return nBC;
	}

	public int getnBR() {
		return nBR;
	}

	public boolean full() {
		return false;
	}

	public boolean findC(String name) {
		findFirstC();
		while (current != null) {
			if (((String) current.getData()).equalsIgnoreCase(name)) {
				
				return true;
			}
				
			current = current.right;
		}
		current = headC;
		return false;
	}

	public void findLast() {
		findFirstC();
		while (current.right != null)
			current = current.right;

	}

	public void findFirstC() {
		current = headC;

	}

	public void findFirstR() {
		if (nBR == 0)
			current = headC;
		else
			current = headR;
	}

	public void findRight() {
		if (current.right != null)
			current = current.right;
		else
			current = headC;

	}

	public void findDown() {
		if (current.down != null)
			current = current.down;
		else
			current = headC;

	}

	public T retrieve() {
		return current.data;

	}

	public void setData(T e) {
		current.data = e;

	}

	public void insertC(T x) {
		findFirstC();
		MNode<T> tmp = new MNode<T>(x);
		if (headC == null) {
			current = headC = tmp;
			nBC++;
		} else {
			findLast();
			current.right = tmp;
			current = current.right;
			nBC++;
		}
	}

	public void insertC2(T x) {
		findFirstC();
		MNode<T> tmp = new MNode<T>(x);
		if (headC == null) {
			current = headC = tmp;
			nBC++;
		} else {
			findLast();
			current.right = tmp;
			nBC++;
		}

		while (current.down != null) {
			tmp.down = new MNode<T>();
			tmp = tmp.down;
			current = current.down;
			current.right = tmp;

		}

	}

	public void Clear() {
		if (headC == null)
			return;
		findFirstC();
		while (current.right != null) {
			current.down = null;
			findRight();
		}
		nBR = 0;
	}

	public void insertR() {
		if (nBC == 0)
			return;

		MNode<T> temp;
		if (headR == null)
			findFirstC();

		else
			findFirstR();

		temp = new MNode<T>();
		headR = temp;
		current.down = temp;
		current = current.right;
		for (int i = 1 ; i < nBC; i++) {
			temp.right = new MNode<T>();
			temp = temp.right;
			current.down = temp;
			current = current.right;
		}
		nBR++;
	}

	public double getMean(String colName) {
		findC(colName);
		Double mean = 0.0;
	
		while (current.down != null) {
			current = current.down;
			if(current.data instanceof Integer)
			mean += (Double) ( (double) (((Integer) current.data).intValue()) );
			else if(current.data instanceof Double)
				mean += (Double) current.data;
		}
		if (!(nBR == 0))
			mean = mean / nBR;
		return mean;
	}

	

}
