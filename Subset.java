
public class Subset {
   public static void main(String[] args){
		//int k = Integer.parseInt(args[0]);
	    int k = 3;
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()){
			q.enqueue(StdIn.readString());
		}
		if(k <= q.size() && k>0){
			for (int i = 0; i < k; i++){
				StdOut.println(q.dequeue());
			}
		}

   }
}
