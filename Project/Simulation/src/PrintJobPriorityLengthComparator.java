/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */


import java.util.Comparator;

/**
 * This comparator compares two Print Jobs by their priority.
 *
 */


public class PrintJobPriorityLengthComparator implements Comparator<PrintJob>{
	
	   @Override
	   public int compare(PrintJob job1, PrintJob job2) {
	   		//in ascending order
		   if(job2.getPriority() - job1.getPriority()  > 0){ //compare decimals 
		   return 1;
	   }else{
		   return 0;
	   }
	   
    }

}
