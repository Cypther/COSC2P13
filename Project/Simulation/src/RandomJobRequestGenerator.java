/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class RandomJobRequestGenerator {
	
	
	int numberOfRequest;
	private List<PrintJob> randomPrintRequestQueue;
	
	
	/**
	 * Generating Random Data for the Job Request
	 * 
	 * */
	public RandomJobRequestGenerator(){
		
		this.randomPrintRequestQueue = RandomJobRequestQueueData(1, 1);
		
	}
	
	public RandomJobRequestGenerator(int numberOfRequest, int runTime){
		
		this.numberOfRequest = numberOfRequest;
		this.randomPrintRequestQueue = RandomJobRequestQueueData(numberOfRequest, runTime);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PrintJob> RandomJobRequestQueueData(int numberOfRequest, int runTime){
		
		PrintJob job;
	    List<PrintJob> arryJobs = new ArrayList<PrintJob>();
	    
  		for(int i = 0; i < numberOfRequest; i++){	
  			int numberPage = randPages();
  			//int randTime = randArrivaTime(runTime);
  			double randTime = randArrivaTime(runTime);
	 	    job = new PrintJob(randTime, numberPage); 
	 	    arryJobs.add(job);
  		}
	    
	    
	    
  		
  		//sort my arrival time
  		 Collections.sort(arryJobs, new PrintJobChainedComparator(
		  new PrintJobArrivalTimeComparator()));	

  			
	    return arryJobs;
	 }
	
	/**
	 * Generating Random pages base on Poisson distribution of 4
	 * Check if the pages is not zero
	 * 
	 * */
	
	//private int randArrivaTime(int runTime){
		private double randArrivaTime(int mean){
		
		//setting the random arrival time to the jobs in seconds
		 //int randTime = (int)Math.floor(Math.random() * runTime * 60);
		  //double randTime = Math.floor(Math.random() * runTime * 60);
	      //System.out.println(randTime);
		  
		  
		  
			//double randTime = Math.floor(Math.random() * runTime * 60);
			
			/* Random r = new Random();
			    double L = Math.exp(-mean);
			    int k = 0;
			    double p = 1.00;
			    do {
			        p = p * r.nextDouble();
			        k++;
			    } while (p > L);
			    return k - 1;*/
			
			//double randTime  = (Math.log(1.0-Math.random())/-mean;
			
		    //1 is really spread out and 9 is really close together
			return 60*(Math.log(1.0-Math.random())/-mean);
		 //return randTime;
	}
	
	/**
	 * Generating Random pages base on Poisson distribution of 4
	 * Check if the pages is not zero
	 * 
	 * */
	
	private int randPages(){
		
		int numberPage = 0;
		
		do{
		//random number of pages
	 	Random randPages = new Random();
	 	PoissonGenerator numberOfPages = null;
	 	numberOfPages = new PoissonGenerator(4, randPages);
	 	numberPage = numberOfPages.nextValue();

		}while(numberPage == 0);
		
		return numberPage;
	}
	
	public  Queue <PrintJob> getData(){
		
		Queue <PrintJob> printRequestQueue  = new Queue <PrintJob>();
		//queuing up the sorted data
  		for(int i = 0; i < this.numberOfRequest; i++){
  			this.randomPrintRequestQueue.get(i).waitTime = 0;//resetting the the wait time to zero
  			printRequestQueue.enqueue(this.randomPrintRequestQueue.get(i));
  		}
		return printRequestQueue;
		}
	
	public  List<PrintJob> getDataList(){
		
		return this.randomPrintRequestQueue;
		}
	

}
