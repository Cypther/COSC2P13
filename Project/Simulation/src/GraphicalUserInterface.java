/**
 * 
 * This class makes the GUI
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Extends JFrame so it can create frames

public class GraphicalUserInterface extends JFrame{

	private static final long serialVersionUID = -4633951193510114451L;
	/**
	 * 
	 */

	List<RandomJobRequestGenerator> dataArrayList  = new ArrayList<RandomJobRequestGenerator>();// array for the jobs
	//List<RandomJobRequestGenerator> dataArrayList;// array for the jobs
	
	//JPanel thePanel = new JPanel();
	JPanel thePanel;
	
	int storedContentCounter;// how many panels in the frame
	int numbersOfJobs;
	
	JButton FIFOButton;
	JButton SPJFButton;
	JButton PAPQButton;
	JButton LBAQButton;
	JButton ResetButton;
	
	JTextField textFieldTime;
	JTextField textFieldJobs;
	
	JTextField textFieldA;
	JTextField textFieldB;
	
	JTextArea textArea1;
	int buttonClicked;
	
	public GraphicalUserInterface(){
		
		prepareGUI();		
		
	}
	
	// Implements ActionListener so it can react to events on components
	
	private class ListenForButton implements ActionListener{
	
	// This method is called when an event occurs
	
	public void actionPerformed(ActionEvent e){
		
		// Check if the source of the event was the button
		
		if(e.getSource() == FIFOButton){
			
			/*Checking if it's a number */
			int jobs = 0;
			   try {
			        jobs = Integer.parseInt(textFieldJobs.getText());
			        textFieldJobs.setText(String.valueOf(jobs));

			    } catch (NumberFormatException notNumber) {
			        JOptionPane.showConfirmDialog(null, "Please enter numbers only", "Numbers of Jobs", JOptionPane.CANCEL_OPTION);
			        return;
			    }
			
			Component[] storedContentNew = thePanel.getComponents();
			
			if(storedContentCounter < storedContentNew.length){
				thePanel.remove(storedContentNew.length-1);
				thePanel.revalidate();
				thePanel.repaint();
				
			}
		
			if(dataArrayList.isEmpty()){
			RandomJobRequestGenerator data;
	    	for(int i = 1; i < 10; i++){
	    		data = new RandomJobRequestGenerator(jobs, i);//generating data
	    		dataArrayList.add(data);
	    	}
			}
			
			//FIFOGraphingData graphData = new FIFOGraphingData(jobs);
			FIFOGraphingData graphData = new FIFOGraphingData(dataArrayList);
			graphData.setBounds(100, 10, 550, 440); //the graphing data
			//graphData.setVisible(false);
			//thePanel.remove(0);
			thePanel.repaint(); //Refreshing the screen
			thePanel.add(graphData);
			//thePanel.revalidate(); 
			thePanel.repaint(); //Refreshing the screen
				
		}
		
		
		if(e.getSource() == SPJFButton){
			
			/*Checking if it's a number */
			int jobs = 0;
			   try {
			        jobs = Integer.parseInt(textFieldJobs.getText());
			        textFieldJobs.setText(String.valueOf(jobs));

			    } catch (NumberFormatException notNumber) {
			        JOptionPane.showConfirmDialog(null, "Please enter numbers only", "Numbers of Jobs", JOptionPane.CANCEL_OPTION);
			        return;
			    }
			
			Component[] storedContentNew = thePanel.getComponents();
			
			if(storedContentCounter < storedContentNew.length){
				thePanel.remove(storedContentNew.length-1);
				thePanel.revalidate();
				thePanel.repaint();
				
			}
			
			if(dataArrayList.isEmpty()){
				RandomJobRequestGenerator data;
		    	for(int i = 1; i < 10; i++){
		    		data = new RandomJobRequestGenerator(jobs, i);//generating data
		    		dataArrayList.add(data);
		    	}
				}
			
			//int jobs = Integer.parseInt(textFieldJobs.getText());
			
			//SPJFGraphingData graphData = new SPJFGraphingData(jobs);
			SPJFGraphingData graphData = new SPJFGraphingData(dataArrayList);
			graphData.setBounds(100, 10, 550, 440); //the graphing data
			//graphData.setVisible(false);
			//thePanel.remove(0);
			thePanel.repaint(); //Refreshing the screen
			thePanel.add(graphData);
			//thePanel.revalidate(); 
			thePanel.repaint(); //Refreshing the screen
				
		}
		
		
		if(e.getSource() == PAPQButton){
			
			/*Checking if it's a number */
			int jobs = 0;
			int A = 0;
			int B = 0;
			   try {
			        jobs = Integer.parseInt(textFieldJobs.getText());
			        A = Integer.parseInt(textFieldA.getText());
			        B = Integer.parseInt(textFieldB.getText());
			        textFieldA.setText(String.valueOf(A));
			        textFieldB.setText(String.valueOf(B));
			        textFieldJobs.setText(String.valueOf(jobs));

			    } catch (NumberFormatException notNumber) {
			        JOptionPane.showConfirmDialog(null, "Please enter numbers of Jobs, A and B priority", "Numbers only!", JOptionPane.CANCEL_OPTION);
			        return;
			    }
			
			Component[] storedContentNew = thePanel.getComponents();
			
			if(storedContentCounter < storedContentNew.length){
				thePanel.remove(storedContentNew.length-1);
				thePanel.revalidate();
				thePanel.repaint();
				
			}
			
			if(dataArrayList.isEmpty()){
				
				
				
				//int jobs = Integer.parseInt(textFieldJobs.getText());
				RandomJobRequestGenerator data;
		    	for(int i = 1; i < 10; i++){
		    		data = new RandomJobRequestGenerator(jobs, i);//generating data
		    		dataArrayList.add(data);
		    	}
				}
			
			//int A = Integer.parseInt(textFieldA.getText());
			//int B = Integer.parseInt(textFieldB.getText());
			
			//PAPQGraphingData graphData = new PAPQGraphingData(jobs);
			PAPQGraphingData graphData = new PAPQGraphingData(dataArrayList, A, B);
			graphData.setBounds(100, 10, 550, 440); //the graphing data
			//graphData.setVisible(false);
			//thePanel.remove(0);
			thePanel.repaint(); //Refreshing the screen
			thePanel.add(graphData);
			//thePanel.revalidate(); 
			thePanel.repaint(); //Refreshing the screen
				
		}
		
		
		if(e.getSource() == LBAQButton){
			
			
			/*Checking if it's a number */
			int jobs = 0;
			int A = 0;
			int B = 0;
			   try {
			        jobs = Integer.parseInt(textFieldJobs.getText());
			        A = Integer.parseInt(textFieldA.getText());
			        B = Integer.parseInt(textFieldB.getText());
			        textFieldA.setText(String.valueOf(A));
			        textFieldB.setText(String.valueOf(B));
			        textFieldJobs.setText(String.valueOf(jobs));

			    } catch (NumberFormatException notNumber) {
			        JOptionPane.showConfirmDialog(null, "Please enter numbers of Jobs, A and B priority", "Numbers only!", JOptionPane.CANCEL_OPTION);
			        return;
			    }
			
			Component[] storedContentNew = thePanel.getComponents();
			
			if(storedContentCounter < storedContentNew.length){
				thePanel.remove(storedContentNew.length-1);
				thePanel.revalidate();
				thePanel.repaint();
				
			}
			
			if(dataArrayList.isEmpty()){
				//int jobs = Integer.parseInt(textFieldJobs.getText());
				RandomJobRequestGenerator data;
		    	for(int i = 1; i < 10; i++){
		    		data = new RandomJobRequestGenerator(jobs, i);//generating data
		    		dataArrayList.add(data);
		    	}
				}
			
			//int A = Integer.parseInt(textFieldA.getText());
			//int B = Integer.parseInt(textFieldB.getText());
			
			//int jobs = Integer.parseInt(textFieldJobs.getText());
			
			//PAPQGraphingData graphData = new PAPQGraphingData(jobs);
			LBAQGraphingData graphData = new LBAQGraphingData(dataArrayList, A, B);
			graphData.setBounds(100, 10, 550, 440); //the graphing data
			//graphData.setVisible(false);
			//thePanel.remove(0);
			thePanel.repaint(); //Refreshing the screen
			thePanel.add(graphData);
			//thePanel.revalidate(); 
			thePanel.repaint(); //Refreshing the screen
				
		}
		
		if(e.getSource() == ResetButton){
			
			/*Component[] storedContentNew = thePanel.getComponents();
			
			if(storedContentCounter < storedContentNew.length){
				thePanel.remove(storedContentNew.length-1);
				thePanel.revalidate();
				thePanel.repaint();
				
			}*/
			
			/*Component[] storedContent = thePanel.getComponents();
			
			if(storedContent.length != 0){
				thePanel.remove(storedContent.length-1);
				thePanel.revalidate();
				thePanel.repaint();
				
			}*/
			
			//graphData.setVisible(false);
			//graphData.removeAll();
			//graphData.repaint();
			//thePanel.add(graphData);
			thePanel.removeAll();
			thePanel.revalidate();
			thePanel.setVisible(false);
			prepareGUI();
			thePanel.repaint(); //Refreshing the screen
			System.out.print("Clearing");
				
		}
		
	}
	
	}
	
	private void prepareGUI(){
		
		//if the array list is not empty, clear the list
		if(!dataArrayList.isEmpty()){
			dataArrayList.clear();
		}
		
		//this.dataArrayList.clear();
		
		thePanel = new JPanel();
		// Define the size of the frame
		this.setSize(800, 510);
		
		// Toolkit is the super class for the Abstract Window Toolkit
		// It allows us to ask questions of the OS
				
		Toolkit tk = Toolkit.getDefaultToolkit();
				
		// A Dimension can hold the width and height of a component
		// getScreenSize returns the size of the screen
				
		Dimension dim = tk.getScreenSize();
				
		// dim.width returns the width of the screen
		// this.getWidth returns the width of the frame you are making
				
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
				 
		// You could also define the x, y position of the frame
				 
		this.setLocation(xPos, yPos);
		
		// Define how the frame exits (Click the Close Button)
		// Without this Java will eventually close the app
				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		// Define the title for the frame
		
		this.setTitle("COSC 2P13: SPOOLer Simulation Project");
				
		// The JPanel contains all of the components for your frame
		//thePanel.setBackground(Color.WHITE); // Setting the back ground colour white
		thePanel.setLayout(null);
		
		// Create a button with Click Here on it
		FIFOButton = new JButton("FIFO");
		FIFOButton.setBounds(190,450,60,30);
		thePanel.add(FIFOButton);
		
		// Create a button with Click Here on it
		SPJFButton = new JButton("SPJF");
		SPJFButton.setBounds(250,450,60,30);
		thePanel.add(SPJFButton);
		
		// Create a button with Click Here on it
		PAPQButton = new JButton("PAPQ");
		PAPQButton.setBounds(310,450,60,30);
		thePanel.add(PAPQButton);
		
		// Create a button with Click Here on it
		LBAQButton = new JButton("LBAQ");
		LBAQButton.setBounds(370,450,60,30);
		thePanel.add(LBAQButton);
		
		ResetButton = new JButton("Reset");
		ResetButton.setBounds(430,450,60,30);
		thePanel.add(ResetButton);
		
		/*textFieldTime = new JTextField("Time", 15);
		textFieldTime.setBounds(420,450,120,30);
		thePanel.add(textFieldTime);*/
		
		textFieldJobs = new JTextField("Numbers of Jobs", 15);
		textFieldJobs.setBounds(490,450,120,30);
		thePanel.add(textFieldJobs);
		
		textFieldA = new JTextField("A", 15);
		textFieldA.setBounds(610,450,30,30);
		thePanel.add(textFieldA);
		
		textFieldB = new JTextField("B", 15);
		textFieldB.setBounds(640,450,30,30);
		thePanel.add(textFieldB);
		
		//Title of for simulation
		JTextArea label = new JTextArea();
		label.setBounds(340, 10, 120, 30); //location and size
		Font font = new Font("Simulation", Font.BOLD, 20);
		label.setFont(font);
		label.setBackground(null);
		//label.setForeground(Color.BLUE);
		label.setText("Simulation");
		
	    //adding it ot the panel
		thePanel.add(label);
		
		JLabel labelTurnOverTime = new JLabel("Turn Over Time");
		labelTurnOverTime.setBounds(0, 200, 120, 100); //location and size
		labelTurnOverTime.setUI(new VerticalLabelUI());
		thePanel.add(labelTurnOverTime);
		
		JLabel labelArrivalTime = new JLabel("Arrival Time");
		labelArrivalTime.setBounds(350, 390, 120, 100); //location and size
		thePanel.add(labelArrivalTime);
		
		// Create an instance of ListenForEvents to handle events
		
				ListenForButton PrintQueueButtonListen = new ListenForButton();
				
				// Tell Java that you want to be alerted when an event
				// occurs on the button
				
				FIFOButton.addActionListener(PrintQueueButtonListen);
				thePanel.add(FIFOButton);
				
				SPJFButton.addActionListener(PrintQueueButtonListen);
				thePanel.add(SPJFButton);
				
				PAPQButton.addActionListener(PrintQueueButtonListen);
				thePanel.add(PAPQButton);
				
				LBAQButton.addActionListener(PrintQueueButtonListen);
				thePanel.add(LBAQButton);
				
				ResetButton.addActionListener(PrintQueueButtonListen);
				thePanel.add(ResetButton);
	
		 getContentPane().add(thePanel);
		 setDefaultCloseOperation(3);
	     setVisible(true);
	     
	     Component[] storedContent = thePanel.getComponents();//calculating the how many content in the frame
	      storedContentCounter = storedContent.length;
		
	}
	
}