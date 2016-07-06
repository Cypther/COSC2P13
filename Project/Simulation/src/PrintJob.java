/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

public class PrintJob{

	double priority; 
	double arrivalTime; 
	double waitTime;
	int pages; 
	
	double processTime; //time of arrival + pages to complete
	double turnAroundTIme; //Time of the complete time minus the arrival time
	
	public PrintJob()
	{
		this.arrivalTime = 0; 
		this.waitTime = 0;
		this.pages = 0;
		
	}
	public PrintJob(double theTime, int numberOfPages){
		this.arrivalTime = theTime;
		this.pages = numberOfPages;
		this.waitTime = 0;
	}

	public void setWaitTIme(double currentTime){
		
		if(currentTime > this.arrivalTime){
			this.waitTime = currentTime - arrivalTime;
			
		}else{
			
			this.waitTime = 0;
			
		}
		
		//this.waitTime =  Math.abs(currentTime - arrivalTime);
		 //return waitTime;
	}
	
	
public double getWaitTIme(){
		 return this.waitTime;
	}

public double getArrivalTime(){
	 return this.arrivalTime;
}

public int getPages(){
	 return this.pages;
}

public double getPriority(){
	 return this.priority;
}

public void setPriority(double priority){
	 this.priority = priority;
}

public void resetData(){
	 this.priority = 0;
	 this.waitTime = 0;
	 this.processTime = 0;
	 this.turnAroundTIme = 0;
}



public String toString() {
    return String.format("%d\t\t\t%d\t\t\t%.2f",  arrivalTime, pages, waitTime);
}
}