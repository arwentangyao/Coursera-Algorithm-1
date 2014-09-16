
public class PercolationStats {
	private double [] sampleResults;

	public PercolationStats(int N, int T){
		if(N <= 0 || T <= 0){
			throw new java.lang.IllegalArgumentException();
		}
		sampleResults = new double[T];
		for (int i = 0; i < T; i++){
			sampleResults[i] = 0;
			Percolation sample = new Percolation(N);
			while(true){
				int column = StdRandom.uniform(1, N+1);
				int row = StdRandom.uniform(1,N+1);
				if(!sample.isOpen(column, row)){
					sampleResults[i] ++;
					sample.open(column, row);
					if(sample.percolates()){
						break;
					}
				}
			}
			sampleResults[i] = sampleResults[i]/(N*N);
		}		
	}
	
	public double mean(){
		return StdStats.min(sampleResults);
	}
	
	public double stddev(){
		return StdStats.stddev(sampleResults);
	}
	
	public double confidenceLo()             // returns lower bound of the 95% confidence interval
	{
		return mean() - (1.96*stddev()/Math.sqrt(sampleResults.length));
	}	
	
	public double confidenceHi()             // returns upper bound of the 95% confidence interval
	{
		return mean() + (1.96*stddev()/Math.sqrt(sampleResults.length));
	}
	
	public static void main(String[] args) {
		//PercolationStats percolationStats = new PercolationStats(200,100);
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats percolationStats = new PercolationStats(N, T);  
        StdOut.println("mean = " + percolationStats.mean());  
        StdOut.println("stddev = " + percolationStats.stddev());  
        StdOut.println("95% confidence interval " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi()); 
	}

}
