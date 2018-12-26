
import java.util.HashMap;

public class SSparseRec {

    private int r, s;

    // selected from pairwise independent family of hash functions
    private Hash[] hashFamily;
    private OneSparseRec[][] net;
    
    int maxNumberOfOneSparse = 0;
    //markRow indicate which row has the most 1-sparse
    int markRow = 0;
    public SSparseRec(int r, int sparsity){
        this.r = r;
        this.s = sparsity;
        
        initialise();
        //System.out.println("********");
    }

    private void initialise() {
    	
        // initialise count-sketch, for every row the width is 2 * s
    	net = new OneSparseRec[r][2 * s];
    	for (int i = 0; i < r; i++) {
        	for (int j = 0; j < 2 * s; j++) {
        		net[i][j] = new OneSparseRec();
        	}
        }
    	
    	//initialize hash family for count-sketch
    	hashFamily = new Hash[r];
        for (int i = 0; i < r; i++) {
    		hashFamily[i] = new Hash(2, 2*s);
    	}
    }

    public void add(int index, int value) {
       // fill in
    	for (int i = 0; i < r; i++) {
    		net[i][hashFamily[i].hash(index)].add(index, value);
    	}
    }

    public boolean isSSparse() {
    	int temp = 0;
        for (int i = 0; i < r; i++) {
        	temp = 0;
        	for (int j = 0; j < 2 * s; j++) {
        		if (net[i][j].toString().equals("more"))
        			temp += 2;// collision so at least add 2
        		else if (!net[i][j].toString().equals("zero"))
        			temp++;
        	}
        	
        	if (maxNumberOfOneSparse < temp) {
        		maxNumberOfOneSparse = temp;
        		markRow = i;
        	}
        		
        }
        if ( maxNumberOfOneSparse != 0 && maxNumberOfOneSparse <= this.s)
        	return true;
        return false;
    }

    public String sparseRecTest() {
        // fill in
    	String str = toString();
    	//recover();
        return str;
    }

    public HashMap<Integer,Integer> recover() {
       // itemStr is all items info  we get if the mixmum number of 1-sparse in this 
       // count-sketch is at most s
       HashMap<Integer, Integer> dataStream = new HashMap<Integer, Integer>();
       String itemsStr = toString();
       String[] items = itemsStr.split("\n");
       //recovery data to hashmap
       for (String item : items) {
    	   String[] temp = item.split(" ");
    	   dataStream.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
       }
       //System.out.println(dataStream.toString());
       return  dataStream;
    }

    // this might help
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        int numCols = 2*s;
        //return the items in the row which has the most 1-sparse
        if (isSSparse()) {
        	 //System.out.println("This is a " + maxNumberOfOneSparse + "sparse.");
        	 for (int j=0; j<numCols; j++) {
        		 //output of  1-sparse vector which has the most 1-sparse
        		 if(net[markRow][j].isOneSparse()) {
        			 sb.append(net[markRow][j].toString());
            	     sb.append("\n");
        		 }
        		 
             }
        	 return sb.toString();
        } 
        else if (maxNumberOfOneSparse == 0)
        	return "zero";
        else
        	return "more";   
   }




}
