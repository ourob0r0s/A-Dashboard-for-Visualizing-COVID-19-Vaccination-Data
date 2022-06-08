public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private Node<T> current;
	

	@Override
	public boolean empty() {
		return head == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public int size() {
		int count = 0;
		while (current.next != null) {
			current = current.next;
			count++;
		}
		return count;
	}

	public boolean find(String name) {
		current = head;
		while (current.next != null) {
			if(current.getData().equals(name)) {
				current = head;
				return true;
			
			}
			current = current.next;
		}
			return false;
	}

	@Override
	public void findFirst() {
		current = head;

	}

	@Override
	public void findNext() {
		if(current.next != null)
		current = current.next;
		else
			current = head;

	}

	@Override
	public boolean last() {
		return current.next == null;

	}

	@Override
	public T retrieve() {
		return current.data;

	}

	@Override
	public void update(T e) {
		current.data = e;

	}

	@Override
	public void insert(T x) {
		Node<T> tmp = new Node<T>(x);
		if (empty()) {
			current = head = tmp;
		} else {
			current.next = tmp;
			current = current.next;
		}
	}
	
	public void unsert(T x) {
		Node<T> tmp = new Node<T>(x);
		if (empty()) {
			current = head = tmp;
		} else {
			current.next = tmp;
			current = current.next;
		}
	}

	@Override
	public void remove() {
		if (current == head) {
			head = head.next;
		} else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;

	}

}
