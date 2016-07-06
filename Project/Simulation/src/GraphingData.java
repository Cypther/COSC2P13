import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.*;

import java.awt.event.*;
 
public class GraphingData extends JPanel {

	
	/*List<PrintJob> FIFOdata;
	List<PrintJob> SPJFdata;
	List<PrintJob> PAPQdata;
	List<PrintJob> LBAQdata;*/
	
	List<Simulation> FIFOdata;
	List<Simulation> SPJFdata;
	List<Simulation> PAPQdata;
	List<Simulation> LBAQdata;
	
	
	
    final int PAD = 35; //padding on the out side
    
    public GraphingData(int runTime, int howManyJobs){
    	
    	
    	/**
    	 * FIFO
    	 * 
    	 * */
    	/////////////////////////////////////////////////////////////////
    	Queue <PrintJob> FIFORequestQueue = new Queue <PrintJob>();// queue for wait time
    	List<PrintJob> FIFOprintRequestQueueArrayList  = new ArrayList<PrintJob>();// array for the jobs
    	
     	List<Simulation> FIFOSimulation= new ArrayList<Simulation>();// array for all the simulation run
    	
     	Simulation simulationFIFO = null;
    	
    	RandomJobRequestGenerator data;
    	for(int i = 1; i < 10; i++){
    		data = new RandomJobRequestGenerator(howManyJobs, i);//generating data
    		FIFOprintRequestQueueArrayList = data.getDataList(); //getting the random data
    		
    		for (PrintJob jobs : FIFOprintRequestQueueArrayList) {
      			FIFORequestQueue.enqueue(jobs);
            }
    		
    		simulationFIFO = new Simulation();
    		simulationFIFO.FIFO(FIFORequestQueue);
    	
    		 FIFOSimulation.add(simulationFIFO);
    	}
    	
    	this.FIFOdata = FIFOSimulation;
    	
    	/////////////////////////////////////////////////////////////////
    	
    	/**
    	 * SPJF
    	 * 
    	 * */
    	/////////////////////////////////////////////////////////////////
    	Queue <PrintJob> SPJFRequestQueue = new Queue <PrintJob>();// queue for wait time
    	List<PrintJob> SPJFprintRequestQueueArrayList  = new ArrayList<PrintJob>();// array for the jobs
    	
     	List<Simulation> SPJFSimulation= new ArrayList<Simulation>();// array for all the simulation run
    	
     	Simulation simulationSPJF = null;
    	
    	//RandomJobRequestGenerator data;
    	for(int i = 1; i < 10; i++){
    		data = new RandomJobRequestGenerator(howManyJobs, i);//generating data
    		SPJFprintRequestQueueArrayList = data.getDataList(); //getting the random data
    		
    		for (PrintJob jobs : FIFOprintRequestQueueArrayList) {
      			SPJFRequestQueue.enqueue(jobs);
            }
    		
    		simulationSPJF = new Simulation();
    		simulationSPJF.SPJF(SPJFRequestQueue);
    	
    		 SPJFSimulation.add(simulationFIFO);
    	}
    	
    	this.SPJFdata = FIFOSimulation;
    	
        //List<PrintJob> FIFOprintRequestQueue  = new ArrayList<PrintJob>();
        //List<PrintJob> SPJFprintRequestQueue  = new ArrayList<PrintJob>();
        //List<PrintJob> PAPQprintRequestQueue  = new ArrayList<PrintJob>();
        //List<PrintJob> LBAQprintRequestQueue  = new ArrayList<PrintJob>();
        
    	/*Scanner userInputTime = new Scanner(System.in);
  		System.out.println("How long to run in minutes : ");
  		int runTime = userInputTime.nextInt();
  		 		
  		Scanner userInputJobs = new Scanner(System.in);
  		System.out.println("How many jobs : ");
  		int howManyJobs = userInputJobs.nextInt();*/
  		
  		//RandomJobRequestGenerator data = new RandomJobRequestGenerator(howManyJobs, runTime);
  		//FIFOprintRequestQueue = data.getDataList();
  		/*
  		Simulation simulationFIFO = new Simulation(runTime, howManyJobs);
  		Simulation simulationSPJF = new Simulation(runTime, howManyJobs);
  		Simulation simulationPAPQ = new Simulation(runTime, howManyJobs);
  		Simulation simulationLBAQ = new Simulation(runTime, howManyJobs);
  		
  		//Queue <PrintJob> FIFORequestQueue = new Queue <PrintJob>();// queue for wait time
  		Queue <PrintJob> SPJFRequestQueue = new Queue <PrintJob>();// queue for wait time
  		Queue <PrintJob> PAPQRequestQueue = new Queue <PrintJob>();// queue for wait time
  		Queue <PrintJob> LBAQRequestQueue = new Queue <PrintJob>();// queue for wait time
  		 //add the sorted pages in the queue
  		for (PrintJob jobs : FIFOprintRequestQueue) {
  			
  			FIFORequestQueue.enqueue(jobs);
  			SPJFRequestQueue.enqueue(jobs);
  			PAPQRequestQueue.enqueue(jobs);
  			LBAQRequestQueue.enqueue(jobs);
        }

  		simulationFIFO.FIFO(FIFORequestQueue);
  		simulationSPJF.SPJF(SPJFRequestQueue);
  		
  		FIFOprintRequestQueue = simulationFIFO.getProcessedDataList();
  		SPJFprintRequestQueue = simulationSPJF.getProcessedDataList();*/
  		
  		//this.FIFOdata = FIFOprintRequestQueue;
  		//this.SPJFdata = SPJFprintRequestQueue;
    	
    	
    }
    
    
	public GraphingData(List<PrintJob> FIFOdata, List<PrintJob> SPJFdata, List<PrintJob> PAPQdata, List<PrintJob> LBAQdata){
		
		/*this.FIFOdata = FIFOdata;
		this.SPJFdata = SPJFdata;
		this.PAPQdata = PAPQdata;
		this.LBAQdata = LBAQdata;*/
		
			
	}
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
       // ((Graphics2D) g).setBackground(new Color(255, 255, 255, 255));
    	// g.clearRect(0, 0, getWidth(), getHeight());
    	 
     
        Graphics2D g2 = (Graphics2D)g;
        Graphics2D g3 = (Graphics2D)g; //added
        Graphics2D g4SPJF = (Graphics2D)g; //added tunr arround time
        Graphics2D g4SPJFarrivalTime = (Graphics2D)g; //added tunr arround time
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        
        //float sh = lm.getAscent() + lm.getDescent();
        float sh = (float) (h*(0.09));
        //System.out.println("What is sh "+ sh);
        
        /**
         * Setting up the Y axis values
         * 
         * 
         * */
        // Ordinate label.
        String s = "Numbers of Pages"; //Y axis
        String number;
        //float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        float sy = h - PAD - (h - 2*PAD); //the height when printing out the number
        
        //for(int i = 0; i < s.length(); i++) {
        	for(int i = getMax(); i >= 0; i -=(getMax()*0.1)) { // need to fix this scaler
            //String letter = String.valueOf(s.charAt(i));
            number = String.valueOf(i);
            float sw = (float)font.getStringBounds(number, frc).getWidth();
            float sx = (PAD - sw)/2;
            
            number = String.valueOf(i);
           // g2.drawString(letter, sx, sy);
            g2.drawString(number, sx, sy);
            sy += sh;
        }
        	
        	
        // Abcissa label.
        /*s = "Time";
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);*/
        
        /**
         * 
         * Y axis for time 
         * 
         * */
        	
            float sw = (float) (w*(0.09));
            float sx = (PAD - sw)/2;
            for(int i = 0; i < 9; i += 1) {
            	 number = String.valueOf(i);
                 sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
                 //float sw = (float)font.getStringBounds(number, frc).getWidth();
                 g2.drawString(number, sx, sy);
                 sx += sw;
            	
            }

      /*  float sw = (float) (w*(0.09));
        float sx = (PAD - sw)/2;
        for(int i = 0; i < getMaxArrival(); i +=(getMaxArrival()*0.1)) {
        	 number = String.valueOf(i);
             sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
             //float sw = (float)font.getStringBounds(number, frc).getWidth();
             g2.drawString(number, sx, sy);
             sx += sw;
        	
        }*/
        
        
        
        // Draw lines.
        //double xInc = (double)(w - 2*PAD)/(data.length-1);
        double xInc = (double)(w - 2*PAD)/(FIFOdata.size()-1);
        double scale = (double)(h - 2*PAD)/getMax();
        
        
        //Drawing the lines
        //g2.setPaint(Color.green.darker());
        g2.setPaint(Color.RED);
        //for(int i = 0; i < data.length-1; i++) {
        for(int i = 0; i < FIFOdata.size()-1; i++) {
            double x1 = PAD + i*xInc;
            //double y1 = h - PAD - scale*data[i];
           // double y1 = h - PAD - scale*FIFOdata.get(i).arrivalTime; //not sure starting poststion
            double y1 = h - PAD - scale*FIFOdata.get(i).averageTotalRunTime; //not sure starting poststion
            double x2 = PAD + (i+1)*xInc;
            //double y2 = h - PAD - scale*data[i+1];
           // double y2 = h - PAD - scale*FIFOdata.get(i+1).arrivalTime; //the height
            double y2 = h - PAD - scale*FIFOdata.get(i+1).averageTotalRunTime; //the height
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        
        // Mark data points.
        g2.setPaint(Color.red);
        //for(int i = 0; i < data.length; i++) {
        for(int i = 0; i < FIFOdata.size()-1; i++) {
            double x = PAD + i*xInc;
            //double y = h - PAD - scale*data[i];
            //double y = h - PAD - scale*FIFOdata.get(i).arrivalTime; //not sure
            double y = h - PAD - scale*FIFOdata.get(i).averageTotalRunTime; //not sure
            //g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        
        //the second line
        g3.setPaint(Color.blue);
        //for(int i = 0; i < data.length; i++) {
        for(int i = 0; i < FIFOdata.size()-1; i++) {
            double x = PAD + i*xInc;
            //double y = h - PAD - scale*data[i];
           // double y = h - PAD - scale*FIFOdata.get(i).turnAroundTIme; //not sure
            double y = h - PAD - scale*FIFOdata.get(i).maxTurnAroundTIme; //not sure
            //g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            g3.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        
        
        //Drawing the lines
        //g2.setPaint(Color.green.darker());
        g2.setPaint(Color.BLUE);
        //for(int i = 0; i < data.length-1; i++) {
        for(int i = 0; i < FIFOdata.size()-1; i++) {
            double x1 = PAD + i*xInc;
            //double y1 = h - PAD - scale*data[i];
            //double y1 = h - PAD - scale*FIFOdata.get(i).turnAroundTIme; //not sure
            double y1 = h - PAD - scale*FIFOdata.get(i).maxTurnAroundTIme; //not sure
            double x2 = PAD + (i+1)*xInc;
            //double y2 = h - PAD - scale*data[i+1];
            //double y2 = h - PAD - scale*FIFOdata.get(i+1).turnAroundTIme;
            double y2 = h - PAD - scale*FIFOdata.get(i+1).maxTurnAroundTIme;
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        
        /*//the SPJF line turn around time
        g4SPJF.setPaint(Color.green);
        //for(int i = 0; i < data.length; i++) {
        for(int i = 0; i < SPJFdata.size()-1; i++) {
            double x = PAD + i*xInc;
            //double y = h - PAD - scale*data[i];
            double y = h - PAD - scale*SPJFdata.get(i).turnAroundTIme; //not sure
            //g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            g4SPJF.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        
        //Drawing the lines
        //g2.setPaint(Color.green.darker());
        g4SPJF.setPaint(Color.green);
        //for(int i = 0; i < data.length-1; i++) {
        for(int i = 0; i < SPJFdata.size()-1; i++) {
            double x1 = PAD + i*xInc;
            //double y1 = h - PAD - scale*data[i];
            double y1 = h - PAD - scale*SPJFdata.get(i).turnAroundTIme; //not sure
            double x2 = PAD + (i+1)*xInc;
            //double y2 = h - PAD - scale*data[i+1];
            double y2 = h - PAD - scale*SPJFdata.get(i+1).turnAroundTIme;
            g4SPJF.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        
        
        //the SPJF line turn around time
        g4SPJFarrivalTime.setPaint(Color.ORANGE);
        //for(int i = 0; i < data.length; i++) {
        for(int i = 0; i < SPJFdata.size()-1; i++) {
            double x = PAD + i*xInc;
            //double y = h - PAD - scale*data[i];
            double y = h - PAD - scale*SPJFdata.get(i).arrivalTime; //not sure
            //g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            g4SPJFarrivalTime.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        
        //Drawing the lines
        //g2.setPaint(Color.green.darker());
        g4SPJF.setPaint(Color.ORANGE);
        //for(int i = 0; i < data.length-1; i++) {
        for(int i = 0; i < SPJFdata.size()-1; i++) {
            double x1 = PAD + i*xInc;
            //double y1 = h - PAD - scale*data[i];
            double y1 = h - PAD - scale*SPJFdata.get(i).arrivalTime; //not sure
            double x2 = PAD + (i+1)*xInc;
            //double y2 = h - PAD - scale*data[i+1];
            double y2 = h - PAD - scale*SPJFdata.get(i+1).arrivalTime;
            g4SPJF.draw(new Line2D.Double(x1, y1, x2, y2));
        }*/
        
        
    }
 
   /* private int getMax() {
        int max = -Integer.MAX_VALUE;
        //for(int i = 0; i < data.length; i++) {
        for(int i = 0; i < FIFOdata.size(); i++) {
            //if(data[i] > max)
        	if(FIFOdata.get(i).arrivalTime > max)
                //max = data[i];
        		max = (int) FIFOdata.get(i).arrivalTime;
        }
        return max;*/
    
    private int getMax() {
        int max = -Integer.MAX_VALUE;
        //for(int i = 0; i < data.length; i++) {
        //for(int i = 0; i < FIFOdata.size(); i++) {
        for(int i = 0; i < SPJFdata.size(); i++) {
            //if(data[i] > max)
        	//if(FIFOdata.get(i).turnAroundTIme > max)
        	//if(FIFOdata.get(i).maxTurnAroundTIme > max)
        	if(SPJFdata.get(i).maxTurnAroundTIme > max)
                //max = data[i];
        		//max = (int) FIFOdata.get(i).turnAroundTIme;
        		//max = (int) FIFOdata.get(i).maxTurnAroundTIme;
        	max = (int) SPJFdata.get(i).maxTurnAroundTIme;
        }
        return max;
    }
    
    /**
     * Finding the max arrival time
     * 
     * */
    
   /* private int getMaxArrival() {
        int max = -Integer.MAX_VALUE;
        //for(int i = 0; i < FIFOdata.size(); i++) {
        for(int i = 0; i < SPJFdata.size(); i++) {
        	//if(FIFOdata.get(i).arrivalTime > max)
        	//if(FIFOdata.get(i).runTime > max)
        	if(SPJFdata.get(i).runTime > max)
        		//max = (int) FIFOdata.get(i).arrivalTime;
        		//max = (int) FIFOdata.get(i).runTime;
        		max = (int) SPJFdata.get(i).runTime;
        }
        return max;
    }*/
 
    public static void main(String[] args) {
    	
       /* JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //////////////////////////////////////
        List<PrintJob> FIFOprintRequestQueue  = new ArrayList<PrintJob>();
        List<PrintJob> SPJFprintRequestQueue  = new ArrayList<PrintJob>();
        List<PrintJob> PAPQprintRequestQueue  = new ArrayList<PrintJob>();
        List<PrintJob> LBAQprintRequestQueue  = new ArrayList<PrintJob>();
        
    	Scanner userInputTime = new Scanner(System.in);
  		System.out.println("How long to run in minutes : ");
  		int runTime = userInputTime.nextInt();
  		 		
  		Scanner userInputJobs = new Scanner(System.in);
  		System.out.println("How many jobs : ");
  		int howManyJobs = userInputJobs.nextInt();
  		
  		RandomJobRequestGenerator data = new RandomJobRequestGenerator(howManyJobs, runTime);
  		FIFOprintRequestQueue = data.getDataList();
  		
  		Simulation simulationFIFO = new Simulation(runTime, howManyJobs);
  		Simulation simulationSPJF = new Simulation(runTime, howManyJobs);
  		Simulation simulationPAPQ = new Simulation(runTime, howManyJobs);
  		Simulation simulationLBAQ = new Simulation(runTime, howManyJobs);
  		
  		Queue <PrintJob> FIFORequestQueue = new Queue <PrintJob>();// queue for wait time
  		Queue <PrintJob> SPJFRequestQueue = new Queue <PrintJob>();// queue for wait time
  		Queue <PrintJob> PAPQRequestQueue = new Queue <PrintJob>();// queue for wait time
  		Queue <PrintJob> LBAQRequestQueue = new Queue <PrintJob>();// queue for wait time
  		 //add the sorted pages in the queue
  		for (PrintJob jobs : FIFOprintRequestQueue) {
  			
  			FIFORequestQueue.enqueue(jobs);
  			SPJFRequestQueue.enqueue(jobs);
  			PAPQRequestQueue.enqueue(jobs);
  			LBAQRequestQueue.enqueue(jobs);
        }

  		simulationFIFO.FIFO(FIFORequestQueue);
  		simulationSPJF.SPJF(SPJFRequestQueue);
  		
  		FIFOprintRequestQueue = simulationFIFO.getProcessedDataList();
  		SPJFprintRequestQueue = simulationSPJF.getProcessedDataList();
  		
  		f.add(new GraphingData(FIFOprintRequestQueue, SPJFprintRequestQueue, null, null));
        
        //////////////////////////////////////
        
        //f.add(new GraphingData());
        f.setSize(1080,768);
        f.setLocation(200,200);
        f.setVisible(true);*/
        
    }
}