

import java.util.HashMap;
import java.util.Scanner;
import java.io.*;


public class Test {

	private final static String[] files = {
			"oneSparseOne.txt",
            "oneSparseMore.txt",
            "oneSparseZero.txt",
            "sSparseRecover.txt" ,
            "sSparseMore.txt",
            "sSparseZero.txt",
            "dinner.txt",
            "dinner2.txt"};

	public static void main(String[] args) {

		OneSparseRec oner = null;
		SSparseRec sparse = null;
		InsertL0Sampler isamp = null;
		DynamicL0Sampler dsamp;
		Scanner scan;
		String[] line;

		int index, value;

		for(String file: files)
		   /*if(file.matches("one(.*)")) {

                try {
                    scan = new Scanner(new File("input/" + file));
                    scan.nextLine();
                    oner = new OneSparseRec();

                    while(scan.hasNext()) {
                        line = scan.nextLine().split(" ");
                        index = Integer.parseInt(line[0]);
                        value = Integer.parseInt(line[1]);
           
                        oner.add(index, value);
                    }
                    System.out.println(oner.oneSparseTest());

                    scan.close();
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }


            }else*/ if(file.matches("sS(.*)")) {

                try {
                    scan = new Scanner(new File("input/" + file));
                    int sparsity = Integer.parseInt(scan.nextLine());
                    sparse = new SSparseRec(16, sparsity);
                    
                    while(scan.hasNext()) {
                        line = scan.nextLine().split(" ");
                        index = Integer.parseInt(line[0]);
                        value = Integer.parseInt(line[1]);
                        //System.out.println("*" + index + "*" + value + "*");
                        sparse.add(index, value);
                    }
                    System.out.println(sparse.sparseRecTest());
                   
                    scan.close();
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }

            }/*else if(file.matches("dinner2.txt")) {
            	
            	   double data[] = new double[41];
            
            		int i = 0;
	            	while (i < 10000) {
	            		//System.out.println("*" + i + "*");
	            		try {
	            			scan = new Scanner(new File("input/" + file));
	            			int numOfItems = Integer.parseInt(scan.nextLine());
	            			//System.out.println("" + numOfItems);
	            			if (numOfItems > 0) {
	            				
	        					dsamp = new DynamicL0Sampler(numOfItems);          				
	            				 while(scan.hasNext()) {
	                                 line = scan.nextLine().split("\t");
	                                 index = Integer.parseInt(line[0]);
	                                 value = Integer.parseInt(line[1]);
	                                 //System.out.println("*" + index + "*" + value + "*");
	                                
	                                 dsamp.add(index, value);
	                             }
	            				 
	            				 int temp = dsamp.output();
	            				 if (dsamp.output() == null)
	                                 data[0] += 1; 
	            				 else 
	            					 data[temp] += 1;
	            				 i++;
	            				 scan.close();
	            			}
	            		} catch (Exception e) {
	            			System.out.println(e.getMessage());
	            		}
	            	}
	            	for (int j=0; j < 41; j++)
	            		data[j] = data[j]/10000;
	            	for (int k = 0; k < 41; k++)
	            	   System.out.println(data[k]+"");
	            	StdStats.plotBars(data);
	            		
            }*/
		
		//System.out.println(isamp.output());
	}

	public static void isUniform(Sampler samp, String file) {
        // use to help test your samplers
        // teaching staff will use their own code
		Scanner scan = null;        
    }


}

