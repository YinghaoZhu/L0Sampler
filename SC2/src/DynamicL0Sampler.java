
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class DynamicL0Sampler implements Sampler<Integer> {
    // you may need to add additional class attributes

    // size of universe
    private int n;

    private int sparsity;

    // struct of sparse rec. structures
    private SSparseRec [] recovery;

    // selected from k-wise independent family of hash functions with k>=s/2
    private Hash hash;
    private int subset;
    private long range;
    public DynamicL0Sampler(int n) {
    	
        this.n = n;
        //here we want to get a success of 1 - 1/1024
        sparsity = (int)( 4 * (Math.log(1024)/Math.log(2))); 
        //here we divide n into logn/log2 subset
        subset = (int) Math.ceil(Math.log(n)/Math.log(2));
        
        //there is n^3/2^0 subset
        recovery  = new SSparseRec[subset + 1];
        
        for (int i=0; i<= subset; i++) {
        	recovery[i] = new SSparseRec(16, sparsity);
        	
        }
        // initialize a hash function map n to n^2
        range = (long)Math.pow(n, 2);
        hash = new Hash(16, range);
        
    }
    
    //put items that are form stream into subsets
    public void add(Integer index, int value) {
         for (int i = 0; i <= subset; i++) {
        	 //put item into nested subsets
        	 if (hash.hash(index) <= (int)(range/(long)Math.pow(2, i))) {
        		 recovery[i].add(index, value);
        	 }
        	 else 
        		 break;
         }
    }

    public Integer output() {
        for (int i=0; i<=subset; i++) {
        	if(recovery[i].isSSparse()) {
        		//recover the subset which has the largest-size 
        		//as well as satisfy at most s-sparse
        		HashMap <Integer, Integer> map = recovery[i].recover();
        		long temp = range;
        		int mark = 0;
        		//select the item has the minimum hash value
        		for (Integer key : map.keySet() ) {
        			if ((long)hash.hash(key) < temp) {
        				temp = (long)hash.hash(key);
        				mark = key;
        			}	
        		}
        		return mark;
        	}
        }
        return null;
    }

    // more difficult: return a vector index and its count-value
    public Pair<Integer,Integer> outputVector() {
        // you do not have to fill in

        return null;
    }

    // this may help
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<recovery.length; i++) {
            sb.append(recovery[i].toString());
        }
        return sb.toString();
    }

}
