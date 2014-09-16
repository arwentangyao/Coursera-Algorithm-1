
public class Percolation {
	private int size, top, bottom;
	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF ufl; // back-wash
	private boolean[][] sites;
	
	private void indexChecker(int i , int j) {
		if ( i < 1 || i > size || j < 1 || j > size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Percolation(int N)                // create N-by-N grid, with all sites blocked
	{
		if(N <= 0){
			throw new java.lang.IllegalArgumentException();
		}
		sites = new boolean[N][N];
		//one virtual top
		uf = new WeightedQuickUnionUF(N*N+2);
		//one virtual bottom
		ufl = new WeightedQuickUnionUF(N*N+1);
		
		//virtual points
		top = N*N;
		bottom = N*N+1;
		size = N;
		
		for( int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				sites[i][j] = false;
			}
		}
		
		for (int i = 0; i < N; i++){
			uf.union(i, top);
			ufl.union(i, top);
			uf.union(N*(N-1)+i, bottom);
		}
		
	}
	   
	public void open(int i, int j)           // open site (row i, column j) if it is not already
	{
		indexChecker(i,j);
		sites[i-1][j-1] = true;
		if ( (i - 2) >= 0 && sites[i-2][j-1] == true){
			uf.union((i-2)*size + j-1, (i-1)*size+j-1);
			ufl.union((i-2)*size + j-1, (i-1)*size+j-1);
		}
		if ( i < size && sites[i][j-1] == true){
			uf.union(i*size + j-1, (i-1)*size+j-1);
			ufl.union(i*size + j-1, (i-1)*size+j-1);
		}
		if ( (j-2) >= 0 && sites[i-1][j-2] == true){
			uf.union((i-1)*size + j-2,(i-1)*size+j-1);			
			ufl.union((i-1)*size + j-2,(i-1)*size+j-1);	
		}
		if( j < size && sites[i-1][j] == true){
			uf.union((i-1)*size+j, (i-1)*size+j-1);
			ufl.union((i-1)*size+j, (i-1)*size+j-1);
		}		
	}
	
	public boolean isOpen(int i, int j)      // is site (row i, column j) open?
	{
		indexChecker(i,j);
		return sites[i-1][j-1];
	}
	  
	public boolean isFull(int i, int j)      // is site (row i, column j) full?
	{
		indexChecker(i,j);
		if ( ufl.connected( (i-1)*size+ j - 1, top) && isOpen(i,j)){
			return true;
		}
		return false;
	}
	 
	public boolean percolates()              // does the system percolate?
	{
		if(size == 1)
			return isOpen(1,1);
		return uf.connected(top, bottom);
	}
	   
}
