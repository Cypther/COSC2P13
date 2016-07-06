/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

import java.util.Comparator;


/**
 * This comparator compares two Print Jobs by their arrival Time.
 *
 */

public class PrintJobArrivalTimeComparator implements Comparator<PrintJob>{
	
	   @Override
	    //public int compare(PrintJob job1, PrintJob job2) {
		   public int compare(PrintJob job1, PrintJob job2) {
	        //return job1.getArrivalTime() - job2.getArrivalTime();
		   //if(Math.abs(job1.getArrivalTime() - job2.getArrivalTime()) < 1e-4){ //compare decimals 
			   if(job1.getArrivalTime() - job2.getArrivalTime() > 0){ //compare decimals 
			   //System.out.println("Is this true!");
			   return 1;
		   }else{
			   return 0;
		   }
		   
		   //return Math.abs(job1.getArrivalTime() - job2.getArrivalTime()) < 1e-4;
	    }

}
