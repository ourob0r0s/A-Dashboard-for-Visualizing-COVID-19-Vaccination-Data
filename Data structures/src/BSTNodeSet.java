public class BSTNodeSet<K> {
	public K data;
	public BSTNodeSet<K> left, right;

	public BSTNodeSet(K k) {
		data = k;
		left = right = null;
	}

	public BSTNodeSet(K k, BSTNodeSet<K> l, BSTNodeSet<K> r) {
		data = k;
		left = l;
		right = r;

	}

	public K retrive() {

		return data;

	}
}