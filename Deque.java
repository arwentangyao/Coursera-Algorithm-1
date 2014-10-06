import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int sizeOfDeque;
	
	public Deque(){
		first = new Node();
		last = new Node();
		first.next = last;
		last.previous = first;
		sizeOfDeque = 0;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public int size(){
		return sizeOfDeque;
	}
	
	public void addFirst(Item item){
		if (item == null) throw new java.lang.NullPointerException();
		Node temp = new Node();
		temp.item = item;
		if (first.next == last){
			temp.next = last;
			temp.previous = first;
			first.next = temp;
			last.previous = temp;
			sizeOfDeque++;
		}
		else{
			temp.next = first.next;
			temp.previous = first;
			first.next.previous = temp;
			first.next = temp;
			sizeOfDeque++;
		}
	}
	
	public void addLast(Item item){
		if (item == null) throw new java.lang.NullPointerException();
		Node temp = new Node();
		temp.item = item;
		if (last.previous == first){
			temp.next = last;
			temp.previous = first;
			first.next = temp;
			last.previous = temp;
			sizeOfDeque++;
		}
		else{
			temp.previous = last.previous;
			temp.next = last;
			last.previous.next = temp;
			last.previous = temp;
			sizeOfDeque++;
		}
	}
	
	public Item removeFirst(){
		if (isEmpty()) throw new java.util.NoSuchElementException();
		Node result = first.next;
		first.next = result.next;
		first.next.previous = first;
		result.next = null;
		result.previous = null;
		sizeOfDeque--;
		return result.item;
	}
	
	public Item removeLast(){
		if (isEmpty()) throw new java.util.NoSuchElementException();
		Node result = last.previous;
		last.previous = result.previous;
		result.previous.next = last;
		result.next = null;
		result.previous = null;
		sizeOfDeque--;
		return result.item;
	}
	
	public Iterator<Item> iterator(){
		return new DequeIterator(); 
	}
	
	private class Node{
		private Item item;
		private Node next;
		private Node previous;
	}
	
	private class DequeIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext(){
			return current.next != last;
		}
		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next(){
			if( !hasNext()) throw new java.util.NoSuchElementException();
			current = current.next;
			return current.item;
		}

	}
	
}
