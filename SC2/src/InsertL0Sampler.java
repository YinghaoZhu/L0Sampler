
import java.util.HashMap;
//import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class InsertL0Sampler implements Sampler<Integer> {
    // you will need to add additional class attributes

    
    private long min;
 // the one who has a minmum hash value.
    private int minIndex;
    // selected from k-wise independent family of hash functions with k>=s/2
    private Hash hash;
    private int n;
    
    public InsertL0Sampler(int n) {
        this.n = n;
        this.min = (long)Math.pow((long)this.n, 2);
        this.minIndex = 0;
        
        //create a 4 wise hash function for added stream
        this.hash = new Hash(6,(long)Math.pow((long)this.n, 2));
    }

    public void add(Integer index, int value) {
       
      // find the one with minimum hash value from the stream
      if (hash.hash(index) < min) {
    	  min = (long)hash.hash(index);
    	  this.minIndex = index;
      }
    }
    //return the item which hash the minimum hash value
    public int getMinIndex () {
    	return this.minIndex;
    }
    
    public Integer output() {
       return getMinIndex();
    }

}
