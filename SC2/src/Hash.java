
// Hash.java
// Hash class
// awirth for COMP90056
// Aug 2017,8 -- version 2

public class Hash{
	private int p = 1073741789; //smaller than 2^30
	private long parameters[];	
	private long range;
	
	//k-wise hash function
	private int  wise;
	/*
	 * sparse is the number k for k-sparse
	 */
	public Hash(int sparse, long range){
		
        wise = sparse/2 + 1;
	    parameters = new long[wise];
		
		parameters[wise-1] = (long)StdRandom.uniform(p-1)+1;
		for (int i = 0; i < wise - 1; i++)
			parameters[i] = StdRandom.uniform(p+1);

		this.range = range;
		//System.out.format("a %16d b %12d p %12d %n", a,b,p);*/
	}
	
	public int hash(int key) {
		//the wise of hash function	
		long r = parameters[wise -1];
		for (int i = wise-1; i > 0; i-- ) {
			r =  (r * key + parameters[i-1])%p;
		}
		
		return (int) (r%range);
			 
	}

	public long getN() { return this.range; }

}
