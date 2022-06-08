public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {

	BSTNode<K, T> root, current;
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

	public boolean findkey(K k) {
		BSTNode<K, T> p = root, q = root;
		if (root == null)
			return false;
		
		while (p != null) {

			q = p;
			if (p.key.equals(k)) {

				current = p;
				return true;
			} else if (k.compareTo(p.key) == -1) {
				p = p.left;

			} else {
				p = p.right;

			}

		}

		current = q;
		return false;
	}

	@Override
	public boolean update(K k, T e) {
		if (!findkey(k))
			return false;
		current.data = e;
		return true;
	}

	@Override
	public Pair<Boolean, T> retrieve(K k) {
		Pair<Boolean, T> temp;
		if (!findkey(k))
			return temp = new Pair<Boolean, T>(false, null);
		else
			return temp = new Pair<Boolean, T>(true, current.data);
	}

	@Override
	public int nbKeyComp(K k) {
		BSTNode<K, T> p = root, q = root;
		int c = 0;
		if (root == null)
			return c;
		while (p != null) {
			c++;
			q = p;
			if (p.key.equals(k)) {

				current = p;
				return c;
			} else if (k.compareTo(p.key) == -1) {
				p = p.left;

			} else {
				p = p.right;

			}

		}

		current = q;
		return c;
	}

	@Override
	public boolean insert(K k, T e) {
		BSTNode<K, T> p, q = current;

		if (findkey(k)) {
			current = q;
			return false;
		}

		p = new BSTNode<K, T>(k, e);
		if (root == null) {
			root = current = p;
			size++;
			return true;
		} else {

			if (k.compareTo(current.key) == -1)
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

		if (findkey(k)) {
			remove(root, k, current.data);
			size--;
			return true;
		}
		return false;

	}

	public BSTNode<K, T> remove(BSTNode<K, T> node, K k, T t) {

		if (root == null)
			return null;

		if (k.compareTo(node.key) < 0) {
			node.left = remove(node.left, k, t);

		} else if (k.compareTo(node.key) > 0) {
			node.right = remove(node.right, k, t);

		} else {

			if (node.left == null) {

				BSTNode<K, T> rightC = node.right;

				node.key = null;
				node.data = null;
				node = null;

				return rightC;

			} else if (node.right == null) {

				BSTNode<K, T> leftC = node.left;

				node.key = null;
				node.data = null;
				node = null;

				return leftC;

			} else {

				BSTNode<K, T> tmp = findMin(node.right);

				node.data = (T) tmp.data;
				node.key = (K) tmp.key;

				node.right = remove(node.right, (K) tmp.key, (T) tmp.data);

			}
		}

		return node;
	}

	private BSTNode<K, T> findMin(BSTNode<K, T> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	List<K> temp = new LinkedList<>();

	@Override
	public List<K> getKeys() {
		temp = new LinkedList<>();
		inOrder(root);
		return temp;

	}

	private void inOrder(BSTNode<K, T> node) {
		if (node != null) {
			inOrder(node.left);
			temp.insert(node.key);
			inOrder(node.right);
		}

	}

}
