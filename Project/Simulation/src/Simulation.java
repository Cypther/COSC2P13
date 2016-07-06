/**
 * The simulation class, has the 
 * method for FIFO, SPJF, LBAQ and PAPQ
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Simulation {
	
	double averageTotalRunTime; 
	double maxTurnAroundTIme; 
	
	//static double totalRunTime; //total running time
	private List<PrintJob> processedPrintRequest;
	
	double turnAroundTimeMaster; 
	
	
	public Simulation(){
		  
		this.processedPrintRequest = new ArrayList<PrintJob>();//new instance of ArrayList Print job
  	
	 }
	
	
	/**
	 *Method for resetting the data of the simulation
	 * 
	 * */
	
	public void resetData(){
		this.averageTotalRunTime = 0;
		this.maxTurnAroundTIme = 0;
		this.turnAroundTimeMaster = 0;
	}
	
	/**
	 * Processing the job Request time
	 * 
	 * */
	
	public double jobProcessCompleteTime(PrintJob currentJobRequest){

		double processtime = 0;
		processtime = (currentJobRequest.arrivalTime + currentJobRequest.waitTime + (((40.0)/(60.0))*currentJobRequest.pages));
		currentJobRequest.processTime = processtime;
		return processtime;
		
	}
	
	public double jobProcessTime(PrintJob currentJobRequest){

		double processtime = 0;
		processtime = (currentJobRequest.arrivalTime + (((40.0)/(60.0))*currentJobRequest.pages));
		return processtime;
		
	}
	
public  List<PrintJob> getProcessedDataList(){
		
		return this.processedPrintRequest;
		}
	
	/**
	 * 
	 * Displaying the information of the current job
	 * when it's been processed
	 * 
	 * */
	
	public void displayCurrentJob(PrintJob currentJobRequest){
		
		double processorTime = 0;//pages* (40.0)/(60.0))
		processorTime = (((40.0)/(60.0))*currentJobRequest.pages);
		double turnTime =  jobProcessCompleteTime(currentJobRequest) - currentJobRequest.getArrivalTime();
		currentJobRequest.turnAroundTIme = turnTime;
		turnAroundTimeMaster += turnTime;
		
		//formatting the decimal values
		DecimalFormat f = new DecimalFormat("#0.00");
		
		//print out the current job
		/*System.out.println(f.format(currentJobRequest.arrivalTime) 
				+ "\t\t" + currentJobRequest.pages + "\t\t " 
				+ f.format(currentJobRequest.waitTime)
				+ "\t\tProcessed time: "+ f.format(processorTime)
				+ "\tPriority: " + f.format(currentJobRequest.priority)
				+ "\tCompleted: "+ f.format(jobProcessCompleteTime(currentJobRequest))
				+ " \tTurn around time: "+ f.format(turnTime));*/
		
	}
	
	/**
	 * Simulation for FIFO "First in First Out"
	 * Base on arrival time.
	 * 
	 * */
	
	public double FIFO(Queue <PrintJob> printRequestQueue){
		
		double processTime = 0;//job time to complete
		
		//getting the length of the to check if there's only one element
		int sizeOfQueue = printRequestQueue.length(); 

		PrintJob currentJobRequest;
		PrintJob nextJobRequest = printRequestQueue.dequeue();
		
		//first request case, if only one object in the queue 
		if(sizeOfQueue == 1){
			processTime = jobProcessCompleteTime(nextJobRequest);//getting the process time of the job
			
			//print out the current job
			displayCurrentJob(nextJobRequest);
			processedPrintRequest.add(nextJobRequest);//adding to the list
			//System.out.println("Turn around time FIFO single case " + turnAroundTimeMaster);
			return processTime;
		}

		while(!printRequestQueue.empty()){
			currentJobRequest = nextJobRequest;//the the current job
			nextJobRequest = printRequestQueue.dequeue();//increment to the next queue
			
				//getting the time by the arrival time, plus the wait time and times the numbers of pages
			    processTime = jobProcessCompleteTime(currentJobRequest);//getting the process time of the job
			    
			    nextJobRequest.setWaitTIme(processTime); //setting the wait time of the next job
				
				//print out the current job
			    displayCurrentJob(currentJobRequest);
			    processedPrintRequest.add(currentJobRequest);//adding to the list
				
				//print off the last jobs
				if(printRequestQueue.empty()){
					 processTime = jobProcessCompleteTime(nextJobRequest);//getting the process time of the job
					 
					 //print out the status of the last job
					 displayCurrentJob(nextJobRequest);
					 processedPrintRequest.add(nextJobRequest);//adding to the list
				}
			
		}
		//System.out.println("Turn around time FIFO " + turnAroundTimeMaster);
		getMaxTurnAroundRunTime();
		calculateAverageTurnAroundRunTime();
		return processTime; //returning the FIFO time to complete
		
	}
	
	
	/**
	 * Simulation for SPJF "Shortest print job first"
	 * 
	 * */
	
	public double SPJF(Queue <PrintJob> printRequestQueue){
		
		double processTime = 0;//job time to complete
		double FIFOprocessTime = 0;
		int sizeOfQueue = printRequestQueue.length();
		
		PrintJob currentJobRequest;
		PrintJob nextJobRequest = printRequestQueue.dequeue();
		
		Queue <PrintJob> SPJFRequestQueue = new Queue <PrintJob>();// queue for wait time
		List<PrintJob> SPJFRequestArray = new ArrayList<PrintJob>();
		
		//first request case 
		if(sizeOfQueue == 1){
			processTime = jobProcessCompleteTime(nextJobRequest);//getting the process time of the job
			//print out the current job
			displayCurrentJob(nextJobRequest);
			processedPrintRequest.add(nextJobRequest);//adding to the list
			return processTime; //returning the SPJF time to complete
			
		}
		
		while(!printRequestQueue.empty()){
			
			currentJobRequest = nextJobRequest;
			nextJobRequest = printRequestQueue.dequeue();//increment next queue
			
				//getting the time by the arrival time, plus the wait time and times the numbers of pages
			    processTime = jobProcessCompleteTime(currentJobRequest);//getting the process time of the job
			    
			    //check which time is bigger to set the wait time
				if(FIFOprocessTime >= processTime){
					nextJobRequest.setWaitTIme(FIFOprocessTime);
				}else{
					nextJobRequest.setWaitTIme(processTime);
				}
					
				if(currentJobRequest.waitTime > 0){
					//passing it to be sorted my pages
					//SPJFRequestArray = SPJFpagePriority(SPJFRequestArray, currentJobRequest);
					
					SPJFRequestArray.add(currentJobRequest);//keep adding the current job to the list
					
			  	 //if(nextJobRequest.waitTime == 0 || printRequestQueue.empty()){	
			  		// if(processTime < nextJobRequest.arrivalTime || nextJobRequest.waitTime == 0 || printRequestQueue.empty()){
					     
					if(  SPJFRequestArray.get(0).processTime <= (nextJobRequest.arrivalTime) || nextJobRequest.waitTime == 0|| printRequestQueue.empty()){
						
						SPJFRequestArray = SPJFpagePriority(SPJFRequestArray);//sort the list

			  		//if it's the last one add the last one to it
			  		 if(printRequestQueue.empty()){ 
			  			 //passing it to be sorted my pages and returning it as arraylist
			  			//SPJFRequestArray = SPJFpagePriority(SPJFRequestArray, nextJobRequest);
			  			SPJFRequestArray.add(nextJobRequest);//keep adding the current job to the list
			  		 }
			  		 
			  		 //starting at index one and resetting the wait time
			  		for (int i = 1; i < SPJFRequestArray.size(); i++) {
			  			//SPJFRequestArray.get(i).waitTime = 0;
					}
			  		 
			  		 //add the sorted pages in the queue
			  		for (PrintJob jobs : SPJFRequestArray) {
			  			//currentJobRequest.waitTime = 0;//clearing the wait time
			  			jobs.waitTime = 0;
			            SPJFRequestQueue.enqueue(jobs);
			        }
			  		//System.out.println("Calling FIFO");
			  		FIFOprocessTime = FIFO(SPJFRequestQueue);
			  		 
			  		    //clear the array once it's been processed
			  		    SPJFRequestArray.clear();
			  	 }
			  	//System.out.println(currentJobRequest.arrivalTime + "\t\t" + currentJobRequest.pages + "\t\t " + f.format(currentJobRequest.waitTime) +"\t\t\tTotal time is " + f.format(totalRunTime)  + "\t\tprocessed time "+ f.format(processorTime));
				}else{
					
					//print out the current job
				    displayCurrentJob(currentJobRequest);
				    processedPrintRequest.add(currentJobRequest);//adding to the list
				//print off the last jobs
				if(printRequestQueue.empty()){
					//this.totalRunTime = (nextJobRequest.arrivalTime + nextJobRequest.waitTime + (((40.0)/(60.0))*nextJobRequest.pages));
					processTime = jobProcessCompleteTime(nextJobRequest);//getting the process time of the job
					
					   //check which time is bigger to set complete time whcn returning
					if(FIFOprocessTime >= processTime){
						processTime = FIFOprocessTime;
					}
					
					//print out the current job
					displayCurrentJob(nextJobRequest);
					processedPrintRequest.add(nextJobRequest);//adding to the list
					
				}
				}//end else
		}
		//System.out.println("Turn around time SPJF " + turnAroundTimeMaster);
		getMaxTurnAroundRunTime();
		calculateAverageTurnAroundRunTime();
		return processTime; //returning the SPJF time to complete
				
	}
	
	/**
	 * Sorting by pages, lowest numbers of pages first 
	 * 
	 * */
	
	public List<PrintJob> SPJFpagePriority(List<PrintJob> SPJFRequestArray){
		
        Queue <PrintJob> SPJFRequestQueue = new Queue <PrintJob>();// queue for wait time
		List<PrintJob> SPJFRequestArrayTemp = SPJFRequestArray;
         Collections.sort(SPJFRequestArrayTemp, new PrintJobChainedComparator(new PrintJobPagesComparator()));

		return SPJFRequestArrayTemp;
		
	}
	
	/**
	 * Setting the priority 
	 * 
	 * P calculated as follows: P = A + B*T, where A and B are carefully chosen constants, 
	 * while T is the amount of time a print request spent in the queue 
	 * 
	 * */
	
	
	public void PAPQagedPriority(PrintJob currentJobRequest, int A, int B){
		
		double priority = 0; 
		priority = A + (B*currentJobRequest.waitTime);	
		currentJobRequest.setPriority(priority);
	
	}
	
	/**
	 * 
	 * Priority-aged print queue
	 * 
	 * */
	
	public double PAPQ(Queue <PrintJob> printRequestQueue, int A, int B){
			
		double processTime = 0;//job time to complete
		
		//getting the length of the to check if there's only one element
		int sizeOfQueue = printRequestQueue.length(); 

		PrintJob currentJobRequest;
		PrintJob nextJobRequest = printRequestQueue.dequeue();
		
		//first request case, if only one object in the queue 
		if(sizeOfQueue == 1){
			processTime = jobProcessCompleteTime(nextJobRequest);//getting the process time of the job
			PAPQagedPriority(nextJobRequest, A, B);//Setting the PAPQ Priority
			processedPrintRequest.add(nextJobRequest);//adding to the list
			//print out the current job
			displayCurrentJob(nextJobRequest);
			
			return processTime;
		}

		while(!printRequestQueue.empty()){
			currentJobRequest = nextJobRequest;//the the current job
			nextJobRequest = printRequestQueue.dequeue();//increment to the next queue
			
				//getting the time by the arrival time, plus the wait time and times the numbers of pages
			    processTime = jobProcessCompleteTime(currentJobRequest);//getting the process time of the job
			    
			    nextJobRequest.setWaitTIme(processTime); //setting the wait time of the next job
			    PAPQagedPriority(nextJobRequest, A, B);//Setting the PAPQ Priority
				
				//print out the current job
			    displayCurrentJob(currentJobRequest);
			    processedPrintRequest.add(currentJobRequest);//adding to the list
				
				//print off the last jobs
				if(printRequestQueue.empty()){
					 processTime = jobProcessCompleteTime(nextJobRequest);//getting the process time of the job
					 
					 //print out the status of the last job
					 processedPrintRequest.add(nextJobRequest);//adding to the list
					 displayCurrentJob(nextJobRequest);
				}
			
		}
		
		getMaxTurnAroundRunTime();
		calculateAverageTurnAroundRunTime();
		return processTime; //returning the FIFO time to complete
		
	
	}
	

	/**
	 * 
	 *  Length-based aged queue
	 * 
	 * */
	
	public double LBAQ(Queue <PrintJob> printRequestQueue, int A, int B){
		
		double processTime = 0;//job time to complete
		double FIFOprocessTime = 0;
		int sizeOfQueue = printRequestQueue.length();
		
		PrintJob currentJobRequest;
		PrintJob nextJobRequest = printRequestQueue.dequeue();
		
		Queue <PrintJob> SPJFRequestQueue = new Queue <PrintJob>();// queue for wait time
		List<PrintJob> SPJFRequestArray = new ArrayList<PrintJob>();
		
		//first request case 
		if(sizeOfQueue == 1){
			processTime = jobProcessCompleteTime(nextJobRequest);//getting the process time of the job
			//print out the current job
			displayCurrentJob(nextJobRequest);
			LBAQLengthPriority(nextJobRequest, A, B);//Setting the PAPQ Priority
			processedPrintRequest.add(nextJobRequest);//adding to the list
			return processTime; //returning the SPJF time to complete
			
		}
		
		while(!printRequestQueue.empty()){
			
			currentJobRequest = nextJobRequest;
			nextJobRequest = printRequestQueue.dequeue();//increment next queue
			
				//getting the time by the arrival time, plus the wait time and times the numbers of pages
			    processTime = jobProcessCompleteTime(currentJobRequest);//getting the process time of the job
			    LBAQLengthPriority(currentJobRequest, A, B);//Setting the PAPQ Priority
			    
			    //check which time is bigger to set the wait time
				if(FIFOprocessTime >= processTime){
					nextJobRequest.setWaitTIme(FIFOprocessTime);
					LBAQLengthPriority(nextJobRequest, A, B);//Setting the PAPQ Priority
				}else{
					nextJobRequest.setWaitTIme(processTime);
					LBAQLengthPriority(nextJobRequest, A, B);//Setting the PAPQ Priority
				}
					
				if(currentJobRequest.waitTime > 0){
					//pasting it to be sorted my pages
					//SPJFRequestArray = LBAQPriority(SPJFRequestArray, currentJobRequest);	
					SPJFRequestArray.add(currentJobRequest);//keep adding the current job to the list
					
			  	 //if(nextJobRequest.waitTime == 0 || printRequestQueue.empty()){
					if(  SPJFRequestArray.get(0).processTime <= (nextJobRequest.arrivalTime) || nextJobRequest.waitTime == 0|| printRequestQueue.empty()){
			  		 
						SPJFRequestArray = LBAQPriority(SPJFRequestArray);//sort the list
						
			  		//if it's the last one add the last one to it
			  		 if(printRequestQueue.empty()){ 
			  			 //passing it to be sorted my pages and returning it as arraylist
			  			//SPJFRequestArray = LBAQPriority(SPJFRequestArray, nextJobRequest);
			  			SPJFRequestArray.add(currentJobRequest);//keep adding the current job to the list
			  		 }
			  		 
			  		 //add the sorted pages in the queue
			  		for (PrintJob jobs : SPJFRequestArray) {
			  			jobs.waitTime = 0;
			            SPJFRequestQueue.enqueue(jobs);
			        }
			  		FIFOprocessTime = FIFO(SPJFRequestQueue);
			  		 
			  		    //clear the array once it's been processed
			  		    SPJFRequestArray.clear();
			  	 }
			  	//System.out.println(currentJobRequest.arrivalTime + "\t\t" + currentJobRequest.pages + "\t\t " + f.format(currentJobRequest.waitTime) +"\t\t\tTotal time is " + f.format(totalRunTime)  + "\t\tprocessed time "+ f.format(processorTime));
				}else{
					
					//print out the current job
				    displayCurrentJob(currentJobRequest);
				    processedPrintRequest.add(currentJobRequest);//adding to the list
				
				//print off the last jobs
				if(printRequestQueue.empty()){
					//this.totalRunTime = (nextJobRequest.arrivalTime + nextJobRequest.waitTime + (((40.0)/(60.0))*nextJobRequest.pages));
					processTime = jobProcessCompleteTime(nextJobRequest);//getting the process time of the job
					
					   //check which time is bigger to set complete time whcn returning
					if(FIFOprocessTime >= processTime){
						processTime = FIFOprocessTime;
					}
					
					//print out the current job
					displayCurrentJob(nextJobRequest);
					processedPrintRequest.add(nextJobRequest);//adding to the list
					
				}
				}//end else
		}
		//System.out.println("Turn around time SPJF " + turnAroundTimeMaster);
		getMaxTurnAroundRunTime();
		calculateAverageTurnAroundRunTime();
		return processTime; //returning the PAPQ time to complete
	
	}
	
	public void LBAQLengthPriority(PrintJob currentJobRequest, int A, int B){

		
		double priority = 0;  
		DecimalFormat f = new DecimalFormat("#0.00");
		priority = ((A + (B*currentJobRequest.waitTime))/currentJobRequest.pages);
		
		currentJobRequest.setPriority(priority);
	
	}
	
	/**
	 * LBAQPriority:
	 * Sort by the highest Priority first
	 * 
	 * */
	public List<PrintJob> LBAQPriority(List<PrintJob> SPJFRequestArray){
		
			List<PrintJob> SPJFRequestArrayTemp = SPJFRequestArray;
	         Collections.sort(SPJFRequestArrayTemp, new PrintJobChainedComparator(new PrintJobPriorityLengthComparator()));
	         return SPJFRequestArrayTemp;
		
	}
	
	/**
	 * Calculating the averages turn around time 
	 * of all the jobs
	 * */
	
	public void calculateAverageTurnAroundRunTime(){
		
		double total = 0;
		
		processedPrintRequest.size();
		 //add the sorted pages in the queue
  		for (PrintJob jobs : processedPrintRequest) {
  			total += jobs.turnAroundTIme;
        }
  		this.averageTotalRunTime = (total/processedPrintRequest.size());
	}
	
	/**
	 * Find the max turn around 
	 * time for all the jobs
	 * 
	 * */
	
    private void getMaxTurnAroundRunTime(){
    	//double max = -Integer.MAX_VALUE;
    	double max = 0;
        //for(int i = 0; i < data.length; i++) {
        for(int i = 0; i < processedPrintRequest.size(); i++) {
            //if(data[i] > max)
        	if(processedPrintRequest.get(i).turnAroundTIme > max)
                //max = data[i];
        		max = processedPrintRequest.get(i).turnAroundTIme;
        }
       // System.out.println("Max is " + max);
        this.maxTurnAroundTIme = max;
    }
	
	
	public static void main(String[] args) {
		
		
		//1000 jobs, 100 minutes is the default time. or 25 minutes.
		Queue <PrintJob> printRequestQueue  = new Queue <PrintJob>();  		
  		Simulation sim = new Simulation();
  		
  		
  		/**
  		 * Testing LBAQ method
  		 * 
  		 * 
  		 * */
  		
  		//generating data
  		List<RandomJobRequestGenerator> dataArrayList  = new ArrayList<RandomJobRequestGenerator>();
  		RandomJobRequestGenerator data;
  		for(int i = 1; i< 10;i++){
  		data = new RandomJobRequestGenerator(10, i);//generating data
		dataArrayList.add(data); // my data
  		}
  		
  		int[] A = {0,1,2,4};
  		int[] B = {1,2,4};
  		//LBAQ simulation, running it 12 times with different A, B values
  		System.out.println("");
  		System.out.println("LBAQ");
  		
  		List<PrintJob> RequestQueueArrayList  = new ArrayList<PrintJob>();// array for the jobs
  	
  	  		for(int i = 0; i < 4; i++){ //runs 4 times
  	    			RequestQueueArrayList = dataArrayList.get(i).getDataList(); //getting the random data
  	    		
  	    			for(int j = 0; j < 3; j++){ //runs 3 times
  	    				System.out.println("A["+ A[i] + "] B[" + B[j]+"]");
  	    				
  	    				for(int k = 0; k < dataArrayList.size();k++){
  	    		  		RequestQueueArrayList = dataArrayList.get(k).getDataList(); //getting the random data
  	    		  	    for (PrintJob jobs : RequestQueueArrayList) {
	    	    			jobs.resetData();
	    	    			printRequestQueue.enqueue(jobs);
	    	            }
  	    				sim.LBAQ(printRequestQueue, A[i], B[j]);
  	    				DecimalFormat f = new DecimalFormat("#0.00");
  	    	    		
  	    				System.out.println("Speed is: " + k 
  	    						+ " Average:" 
  	    						+ f.format(sim.averageTotalRunTime)
  	    						+ " Max: "
  	    						+ f.format(sim.maxTurnAroundTIme));
  	    				sim.resetData();
  	    				}
  	    				
  	    			}
  	    			System.out.println("");
  	    			
  	    		}
  	  	   System.out.println("Done Data Set");
  	  	   RequestQueueArrayList.clear();
  			
  		}

}
