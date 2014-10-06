import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] s;
	private int N;
	
	public RandomizedQueue(){
		s = (Item[]) new Object[2];
	}
	
    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }
    
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = s[i];
        }
        s = temp;
    }
	
    public void enqueue(Item item) {
    	if (item == null) throw new java.lang.NullPointerException();
    	if (N == s.length) resize(2*s.length);
    	s[N++] = item;    	
    }
    
    public Item dequeue(){
    	 if (isEmpty()) throw new java.util.NoSuchElementException();
    	 int index = StdRandom.uniform(N);
    	 Item temp = s[index];
    	 exch(s, index, N-1);
    	 s[N-1] = null;
    	 N--;
    	 if (N < s.length/4) resize(s.length/2);
    	 return temp;
    }
    
    public Item sample(){
    	 if (isEmpty()) throw new java.util.NoSuchElementException();
    	 int index = StdRandom.uniform(N);
    	 return s[index];
    }
    
	public Iterator<Item> iterator(){
		return new  RandomizedQueueIterator(); 
	}
	
	private void exch(Item[] a, int i, int j){
		Item swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private class RandomizedQueueIterator implements Iterator<Item>{
		private int i = N;
		private int[] idx;
		public RandomizedQueueIterator(){
			idx = new int[N];
			for(int i = 0; i < N; i++){
				idx[i] = i;
			}
			StdRandom.shuffle(idx);
		}
		public boolean hasNext(){
			return i > 0;
		}
		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next(){
			if( !hasNext()) throw new java.util.NoSuchElementException();
			return s[idx[--i]];
		}
	}
	


}
