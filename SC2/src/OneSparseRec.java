public class OneSparseRec {

    private int phi;
    private int iota;
    private long tau;
    //a large prime number p, Prime p = 1073741789; 
    private static long p = 1073741789;
    //a int number, which is randomly chose from{1, p} 
    private long q = 0;
    
    public OneSparseRec() {
       // fill in
       phi = 0;
       iota = 0;
       tau = 0;
       q = (long)StdRandom.uniform((int)p + 1);
    }
    
    
    
    public void add(int index, int value) {
        // fill in
    	//System.out.println(index + "* " + value);
    	int i = 0;
    	long tempTau = ((long)value * q) % p;
    	while (i <= index) {
    		tempTau = (tempTau * q) % p;
    		i++;
    	}
    	
    	tau = (tau + tempTau) % p;
    	phi += value; // phi = X_1 + X_2+.....+X_n
		iota += index * value;
        //System.out.println(tau + " " + phi + " " + iota);
    }

    //to check the cell is a one sparse or not 
    public boolean isOneSparse() {
        
    	if (tau != 0) {
    		if (phi == 0) {
    			return false;
    		}
    		else {
    			
	    		int temp = iota % phi;
	    		
	    		//if it is 1-sparse, iota%phi must be zero
	    		if (temp != 0)
	    			return false;
	    		
	    		// ther may be one no-zero item or more 
	    		else {
	    			int iotaDPhi = iota / phi;
	    			long result = (long)phi * q % p;
	    			for (int i = 0; i <= iotaDPhi; i++)
	    			{
	    				result = result * q % p;
	    			}
	    			
	    			if (result == tau)
	    				return true;
	    			else 
	    				return false;
	    		}
    	    }
    			
    	} 
    	//no non-zero items 
    	else 
    		return false;
    }
  //to check the cell is a one sparse or not 
    public boolean isMoreSparse() {
        
    	if (tau != 0) {
    		if (phi == 0) {
    			return false;
    		}
    		else {
    			
	    		int temp = iota % phi;
	    		
	    		//if it is 1-sparse, iota%phi must be zero
	    		if (temp != 0)
	    			return false;
	    		
	    		// there may be one no-zero item or more 
	    		else {
	    			int iotaDPhi = iota / phi;
	    			long result = (long)phi * q % p;
	    			for (int i = 0; i <= iotaDPhi; i++)
	    			{
	    				result = result * q % p;
	    			}
	    			
	    			if (result == tau)
	    				return true;
	    			else 
	    				return false;
	    		}
    	    }
    			
    	} 
    	//no non-zero items 
    	else 
    		return false;
    }

    public String oneSparseTest() {
        // fill in
       return this.toString();
    }

    // getters
    public int getPhi() {
        return this.phi;
    }

    public int getIota() {
        return this.iota;
    }

    // this might help
    @Override public boolean equals(Object otherObj) {
        if (!(otherObj instanceof OneSparseRec)) return false;
        else {
            OneSparseRec oner = (OneSparseRec) otherObj;
            return this.getIota() == oner.getIota()
                    && this.getPhi() == oner.getPhi();
        }
    }

    @Override public String toString() {
        if(this.isOneSparse()) {
            return  iota/phi + " " + phi;
        } 
        //zero
        else if(tau==0 && phi==0 && iota==0) {
            return "zero";
        } 
        //more
        else 
        	return "more";
    }


}
