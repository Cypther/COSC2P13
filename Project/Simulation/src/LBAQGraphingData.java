/**
 * LBAQ plotting data
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

import javax.swing.*;
 
public class LBAQGraphingData extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 442876559006815460L;

	List<Simulation> LBAQdata;
	
    final int PAD = 35; //padding on the out side
    
    public LBAQGraphingData(List<RandomJobRequestGenerator> dataArrayList, int A, int B){
    	
    	
    	/**
    	 * LBAQ
    	 * 
    	 * */
    	
    	Queue <PrintJob> LBAQRequestQueue = new Queue <PrintJob>();// queue for wait time
    	List<PrintJob> LBAQprintRequestQueueArrayList  = new ArrayList<PrintJob>();// array for the jobs
    	
     	List<Simulation> LBAQSimulation= new ArrayList<Simulation>();// array for all the simulation run
    	
     	Simulation simulationLBAQ = null;
    	
     	System.out.println("LBAQ: ");
     	System.out.println("A: "+ A + " B: " + B);
     	
    	//RandomJobRequestGenerator data;
    	for(int i = 0; i < 9; i++){
    		LBAQprintRequestQueueArrayList = dataArrayList.get(i).getDataList(); //getting the random data
    		
    		//queuing up the jobs
    		for (PrintJob jobs : LBAQprintRequestQueueArrayList) {
    			jobs.resetData();
    			LBAQRequestQueue.enqueue(jobs);
            }
    		
    		simulationLBAQ = new Simulation();
    		simulationLBAQ.LBAQ(LBAQRequestQueue, A, B); //need a for loop
    		
    		DecimalFormat f = new DecimalFormat("#0.00");
    		
				System.out.println("Speed is: " + i 
  						+ " Average:" 
  						+ f.format(simulationLBAQ.averageTotalRunTime)
  						+ " Max: "
  						+ f.format(simulationLBAQ.maxTurnAroundTIme));
    		LBAQSimulation.add(simulationLBAQ);
    		//sim.resetData();
    	}
    	System.out.println("");
    	this.LBAQdata = LBAQSimulation;
    	
    }
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
       // ((Graphics2D) g).setBackground(new Color(255, 255, 255, 255));
    	// g.clearRect(0, 0, getWidth(), getHeight());
    	 
     
        Graphics2D g2 = (Graphics2D)g;
        Graphics2D g3 = (Graphics2D)g; //added
        
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
        	
        
        /**
         * 
         * Y axis for time 
         * 
         * */
        	
            float sw = (float) (w*(0.09));
            float sx = (PAD - sw)/2;
            for(int i = 0; i < 10; i += 1) {
            	 number = String.valueOf(i);
                 sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
                 //float sw = (float)font.getStringBounds(number, frc).getWidth();
                 g2.drawString(number, sx, sy);
                 sx += sw;
            	
            }
        
        
        
        // Draw lines.
        //double xInc = (double)(w - 2*PAD)/(data.length-1);
        double xInc = (double)(w - 2*PAD)/(LBAQdata.size()-1);
        double scale = (double)(h - 2*PAD)/getMax();
        
        
        //Drawing the lines
        //g2.setPaint(Color.green.darker());
        g2.setPaint(Color.RED);
        //for(int i = 0; i < data.length-1; i++) {
        for(int i = 0; i < LBAQdata.size()-1; i++) {
            double x1 = PAD + i*xInc;
            //double y1 = h - PAD - scale*data[i];
           // double y1 = h - PAD - scale*FIFOdata.get(i).arrivalTime; //not sure starting poststion
            double y1 = h - PAD - scale*LBAQdata.get(i).averageTotalRunTime; //not sure starting poststion
            double x2 = PAD + (i+1)*xInc;
            //double y2 = h - PAD - scale*data[i+1];
           // double y2 = h - PAD - scale*FIFOdata.get(i+1).arrivalTime; //the height
            double y2 = h - PAD - scale*LBAQdata.get(i+1).averageTotalRunTime; //the height
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        
        // Mark data points.
        g2.setPaint(Color.red);
        //for(int i = 0; i < data.length; i++) {
        for(int i = 0; i < LBAQdata.size()-1; i++) {
            double x = PAD + i*xInc;
            //double y = h - PAD - scale*data[i];
            //double y = h - PAD - scale*FIFOdata.get(i).arrivalTime; //not sure
            double y = h - PAD - scale*LBAQdata.get(i).averageTotalRunTime; //not sure
            //g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        
        //the second line
        g3.setPaint(Color.blue);
        //for(int i = 0; i < data.length; i++) {
        for(int i = 0; i < LBAQdata.size()-1; i++) {
            double x = PAD + i*xInc;
            //double y = h - PAD - scale*data[i];
           // double y = h - PAD - scale*FIFOdata.get(i).turnAroundTIme; //not sure
            double y = h - PAD - scale*LBAQdata.get(i).maxTurnAroundTIme; //not sure
            //g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            g3.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        
        
        //Drawing the lines
        //g2.setPaint(Color.green.darker());
        g2.setPaint(Color.BLUE);
        //for(int i = 0; i < data.length-1; i++) {
        for(int i = 0; i < LBAQdata.size()-1; i++) {
            double x1 = PAD + i*xInc;
            //double y1 = h - PAD - scale*data[i];
            //double y1 = h - PAD - scale*FIFOdata.get(i).turnAroundTIme; //not sure
            double y1 = h - PAD - scale*LBAQdata.get(i).maxTurnAroundTIme; //not sure
            double x2 = PAD + (i+1)*xInc;
            //double y2 = h - PAD - scale*data[i+1];
            //double y2 = h - PAD - scale*FIFOdata.get(i+1).turnAroundTIme;
            double y2 = h - PAD - scale*LBAQdata.get(i+1).maxTurnAroundTIme;
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        
       
        
        
    }

    
    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < LBAQdata.size(); i++) {
        	if(LBAQdata.get(i).maxTurnAroundTIme > max)
                //max = data[i];
        		max = (int) LBAQdata.get(i).maxTurnAroundTIme;
        }
        return max;
    }
 
}