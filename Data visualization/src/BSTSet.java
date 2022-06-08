public class BSTSet<K extends Comparable<K>> implements Set<K> {
	BSTNodeSet<K> root, current;
	int size = 0;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void clear() {
		root = current = null;
		size = 0;
	}

	@Override
	public boolean find(K k) {
		BSTNodeSet<K> p = root, q = root;
		if (root == null)
			return false;

		while (p != null) {
			q = p;
			if (p.data.equals(k)) {
				
				current = p;
				return true;
			} else if (k.compareTo(p.data) == -1) {
				p = p.left;
				
			} else {
				p = p.right;
				
			}

		}

		current = q;
		return false;
	}

	@Override
	public int nbKeyComp(K k) {
		BSTNodeSet<K> p = root, q = root;
		int c = 0;
		if (root == null)
			return c;

		while (p != null) {
			q = p;
			c++;
			if (p.data.equals(k)) {
				
				current = p;
				return c;
			} else if (k.compareTo(p.data) == -1) {
				p = p.left;
				
			} else {
				p = p.right;
				
			}

		}

		current = q;
		return c;
	}

	@Override
	public boolean insert(K k) {
		BSTNodeSet<K> p, q = current;

		if (find(k)) {
			current = q; 
			return false; 
		}

		p = new BSTNodeSet<K>(k);
		if (root == null) {
			root = current = p;
			size++;
			return true;
		} else {
			if (k.compareTo(current.data) == -1)
				current.left = p;
			else
				current.right = p;
			current = p;
			size++;
			return true;
		}
	}
	@Override
	public boolean remove(K k) {

		if (find(k)) {
			remove(root, k);
			size--;
			return true;
		}
		return false;

	}
	
	
	public BSTNodeSet<K> remove(BSTNodeSet<K> node, K k) {

		if (root == null)
			return null;

		if (k.compareTo(node.data) < 0) {
			node.left = remove(node.left, k);

		} else if (k.compareTo(node.data) > 0) {
			node.right = remove(node.right, k);

		} else {

			if (node.left == null) {

				BSTNodeSet<K> rightC = node.right;

				node.data = null;
				node = null;

				return rightC;

			} else if (node.right == null) {

				BSTNodeSet<K> leftC = node.left;

				node.data = null;
				node = null;

				return leftC;

			} else {

				BSTNodeSet<K> tmp = findMin(node.right);

				node.data = (K) tmp.data;

				node.right = remove(node.right, (K) tmp.data);

			}
		}

		return node;
	}

	private BSTNodeSet<K> findMin(BSTNodeSet<K> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}
	List<K> temp = new LinkedList<>();
	public List<K> getKeys() {
		temp = new LinkedList<>();
		inOrder(root);
		return temp;
		  
		  }
	
	public  void inOrder(BSTNodeSet<K> node){
	    if(node != null){
	      inOrder(node.left);
	      temp.insert((K) node.retrive());
	      inOrder(node.right);
	    }
	    
	  }

	

}
