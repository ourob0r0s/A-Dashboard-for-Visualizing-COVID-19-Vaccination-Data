
public class MNode<T> {
	public T data;
	public MNode<T> right;
	public MNode<T> down;
	

	public MNode () {
		data = null;
		right = null;
		down = null;
	}

	public MNode (T val) {
		data = val;
		right = null;
		down = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public MNode<T> getRight() {
		return right;
	}

	public void setRight(Node<T> next) {
		this.right = right;
	}

	public MNode<T> getDown() {
		return down;
	}

	public void setDown(MNode<T> down) {
		this.down = down;
	}
	

	
}
