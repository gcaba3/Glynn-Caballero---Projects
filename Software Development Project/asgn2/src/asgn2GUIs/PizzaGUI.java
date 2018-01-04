package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature – as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	private PizzaRestaurant restaurant;
	
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	
	private JPanel pnlDisplay;
	private JPanel pnlTwo;
	private JPanel pnlThree;
	private JPanel pnlFour;
	private JPanel pnlBtn; 
	
	private JButton btnLoad;
	private JButton btnFind;
	private JButton btnUnload;
	private JButton btnSwitch;
	private JButton btnTotal;
	
	private JTable tableDisplay; // table
	private DefaultTableModel tableModel; //formats table
	
	private String chosenFile;
	private Boolean isCustomerTable; // indicates which table to show
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		super(title);
		this.restaurant = new PizzaRestaurant();
		this.isCustomerTable = true;
		
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		this.pnlDisplay = this.createPanel(Color.WHITE);
		this.pnlTwo = this.createPanel(Color.LIGHT_GRAY);
		this.pnlThree = this.createPanel(Color.LIGHT_GRAY);
		this.pnlFour = this.createPanel(Color.LIGHT_GRAY);
		this.pnlBtn = this.createPanel(Color.LIGHT_GRAY);
		
		this.btnLoad = this.createButton("Load");
		this.btnFind = this.createButton("Find");
		this.btnSwitch = this.createButton("Switch");
		this.btnUnload = this.createButton("Unload");
		this.btnTotal = this.createButton("Total");
		
		this.tableDisplay = this.createDisplay();
		this.pnlDisplay.setLayout(new BorderLayout());
		this.pnlDisplay.add(new JScrollPane(this.tableDisplay));

		
		this.getContentPane().add(this.pnlDisplay, BorderLayout.CENTER);
		this.getContentPane().add(this.pnlTwo, BorderLayout.NORTH);
		this.getContentPane().add(this.pnlThree, BorderLayout.EAST);
		this.getContentPane().add(this.pnlFour, BorderLayout.WEST);
		this.getContentPane().add(this.pnlBtn, BorderLayout.SOUTH);
	}
	
	private JTable createDisplay(){
		final int fontSize = 11;
		this.tableModel = new DefaultTableModel();
		JTable display = new JTable(this.tableModel);
		
		display.setFont(new Font("Arial",Font.BOLD,fontSize));
		display.setBorder(BorderFactory.createEtchedBorder());
		return display;
	}
	
	private JPanel createPanel(Color c) {
		JPanel temp = new JPanel();
		temp.setBackground(c);
		return temp;
		}
	
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlBtn.setLayout(layout);
		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints();
		//Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		
		this.pnlBtn.add(this.btnFind);
		this.pnlBtn.add(this.btnLoad);
		this.pnlBtn.add(this.btnUnload);
		this.pnlBtn.add(this.btnSwitch);
		this.pnlBtn.add(this.btnTotal);
		}
	
	private JButton createButton(String str) {
		JButton temp = new JButton(str);
		temp.addActionListener(this);
		return temp;
		}
	
	//Fills the table with the customer information
	private void showCustomerTable() throws CustomerException{
		this.clearTable();
		
		this.tableModel.addColumn("Customer Name");
		this.tableModel.addColumn("Mobile Number");
		this.tableModel.addColumn("Customer Type");
		this.tableModel.addColumn("Location X");
		this.tableModel.addColumn("Location Y");
		this.tableModel.addColumn("Delivery Distance");
		
		for(int i = 0; i < this.restaurant.getNumCustomerOrders(); i ++){
			String name = this.restaurant.getCustomerByIndex(i).getName();
			String mobileNumber = this.restaurant.getCustomerByIndex(i).getMobileNumber();
			String type = this.restaurant.getCustomerByIndex(i).getCustomerType();
			String x = String.valueOf(this.restaurant.getCustomerByIndex(i).getLocationX()) + " blocks";
			String y = String.valueOf(this.restaurant.getCustomerByIndex(i).getLocationY()) + " blocks";
			String distance = String.format("%.2f", this.restaurant.getCustomerByIndex(i).getDeliveryDistance()) + " blocks";
			Object[] tableRow = new Object[]{name, mobileNumber, type, x, y, distance};
			this.tableModel.addRow(tableRow);
		}
	}
	
	//Fills the table with the pizza information
	private void showPizzaTable() throws PizzaException{
		this.clearTable();
		
		this.tableModel.addColumn("Pizza Type");
		this.tableModel.addColumn("Quantity");
		this.tableModel.addColumn("Order Price");
		this.tableModel.addColumn("Order Cost");
		this.tableModel.addColumn("Order Profit");
		
		for(int i = 0; i < this.restaurant.getNumPizzaOrders();i++){
			String type = this.restaurant.getPizzaByIndex(i).getPizzaType();
			String quantity = String.valueOf(this.restaurant.getPizzaByIndex(i).getQuantity());
			String orderPrice = "$" + String.format("%.2f", this.restaurant.getPizzaByIndex(i).getOrderPrice());
			String orderCost = "$" + String.format("%.2f", this.restaurant.getPizzaByIndex(i).getOrderCost());
			String orderProfit = "$" + String.format("%.2f", this.restaurant.getPizzaByIndex(i).getOrderProfit());
			Object[] tableRow = new Object[]{type,quantity, orderPrice, orderCost, orderProfit};
			this.tableModel.addRow(tableRow);
			
		}
		
	}
	
	//Shows the totals for the specific log file
	private void totalTable() {
		this.clearTable();
		
		this.tableModel.addColumn("Total Distance");
		this.tableModel.addColumn("Total Profits");
		
		String total_distance = String.format("%.2f",this.restaurant.getTotalDeliveryDistance()) + " blocks";
		String total_profits = "$" + String.format("%.2f",this.restaurant.getTotalProfit());
		Object[] tableRow = new Object[]{total_distance, total_profits};
		this.tableModel.addRow(tableRow);
	}
	
	//Clears the table
	private void clearTable(){
		this.tableModel = (DefaultTableModel) this.tableDisplay.getModel();
		this.tableModel.setRowCount(0);
		this.tableModel.setColumnCount(0);
	}
	
	//Reset's all the fields back to their empty/initial states.
	private void reset(){
		this.clearTable();
		this.restaurant.resetDetails();
		this.chosenFile = null;
	}
	
	/**
	 * Shows the GUI
	 */
	@Override
	public void run() {
		this.layoutButtonPanel();
		this.repaint();
		this.setVisible(true);
		
	}
	
	/**
	 * Handles what PAG is done when an button is pressed.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Get event source
		Object src= e.getSource();
		if (src==btnLoad) {
			
			/*
			 * Try to process the log, then show the customer table. 
			 * If there's an exception thrown during the process log, then output a message to the user.
			 */
			try{
				this.restaurant.processLog(this.chosenFile);
				this.showCustomerTable();
			} catch (NullPointerException ne){
				JOptionPane.showMessageDialog(this,"No file chosen.",
						ne.getClass().getCanonicalName(),JOptionPane.ERROR_MESSAGE);
				this.reset();
			} catch (CustomerException ce){
				JOptionPane.showMessageDialog(this,ce.getMessage(),
						ce.getClass().getCanonicalName(),JOptionPane.ERROR_MESSAGE); 
				this.reset();
			} catch (PizzaException pe) {
				JOptionPane.showMessageDialog(this,pe.getMessage(),
						pe.getClass().getCanonicalName(),JOptionPane.ERROR_MESSAGE); 
				this.reset();
			} catch (LogHandlerException le) {
				JOptionPane.showMessageDialog(this,le.getMessage(),
						le.getClass().getCanonicalName(),JOptionPane.ERROR_MESSAGE); 
				this.reset();
			}
	
		} else if (src == btnUnload){
			if (this.chosenFile == null){
				JOptionPane.showMessageDialog(this,"No file chosen.",
						NullPointerException.class.getCanonicalName() ,JOptionPane.ERROR_MESSAGE);
			} else {
				this.reset();
			}
		} else if (src == btnFind){
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory( new java.io.File(".//logs/"));
			fc.setDialogTitle("Choose file:");
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			if(fc.showOpenDialog(btnFind) == JFileChooser.APPROVE_OPTION){
				this.chosenFile = fc.getSelectedFile().getPath();
				JOptionPane.showMessageDialog(this,"Chosen file: " + this.chosenFile,
						"File Chosen",JOptionPane.INFORMATION_MESSAGE); 
			}
		} else if (src == btnSwitch){
			if (this.chosenFile == null){
				JOptionPane.showMessageDialog(this,"No file chosen.",
						NullPointerException.class.getCanonicalName() ,JOptionPane.ERROR_MESSAGE);
			} else {
				if(this.isCustomerTable){
					try {
						this.showPizzaTable();
						this.isCustomerTable = false;
					} catch (PizzaException pe) {
						JOptionPane.showMessageDialog(this,pe.getMessage(),
								String.valueOf(pe.getClass()),JOptionPane.ERROR_MESSAGE); 
					}
				} else {
					try {
						this.showCustomerTable();
						this.isCustomerTable = true;
					}  catch (CustomerException ce) {
						JOptionPane.showMessageDialog(this,ce.getMessage(),
								String.valueOf(ce.getClass()),JOptionPane.ERROR_MESSAGE); 
					}
				}
			}
		} else if (src == this.btnTotal){
			if (this.chosenFile == null){
				JOptionPane.showMessageDialog(this,"No file chosen.",
						NullPointerException.class.getCanonicalName() ,JOptionPane.ERROR_MESSAGE);
			} else {
				try{
					this.totalTable();
				} catch (IndexOutOfBoundsException io){
					JOptionPane.showMessageDialog(this,io.getMessage(),
							String.valueOf(io.getClass()),JOptionPane.ERROR_MESSAGE); 
				}
			}
			
			
		}
		
	}
}
