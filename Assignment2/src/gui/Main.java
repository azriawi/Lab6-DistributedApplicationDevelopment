package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;

import kioskapp.itemproduct.ItemProduct;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;
import kioskapp.order.Order;
import manager.ItemProductManager;
import manager.OrderedItemManager;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.TextArea;
import java.awt.Panel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Main extends JFrame {

	private String transMode = "";
	private JPanel contentPane;
	public Waiter waiter = new Waiter();
	public ItemProductManager prodManager = new ItemProductManager();
	public OrderedItem orderedItem = new OrderedItem();
	public ItemProduct itemProduct = new ItemProduct();
	public OrderedItemManager orderedItemManager = new OrderedItemManager();
	public Order order = new Order();
	private int[] quantity = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	private boolean[] choosedItem = {false,false,false,false,false,false,false,false,false,false,false,false,false};
	public OrderTransaction orderTransaction = new OrderTransaction();
	
	
	private int choose = 0;
	private double total = 0;
	
	double[] subtotal = {8.10, 11.90, 30.20, 11.90, 9.40, 9.45, 10.40, 8.45, 13.20, 13.20, 13.00 , 4.15, 4.15};
	
	double[] baseprice = {8.10, 11.90, 30.20, 11.90, 9.40, 9.45, 10.40, 8.45, 13.20, 13.20, 13.00 , 4.15, 4.15};
	
	ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
	
	
	JTextField textField = new JTextField();
	
	private JTextField textField_1;
	TextArea textArea = new TextArea();
	JLabel lblTotal_1_2 = new JLabel("0.00");
	private JLabel lblStatusValue = new JLabel("-");
	//lblStatusValue = new JLabel("-");
	private JLabel lblServerDate = new JLabel("-");
	
	
	
	//public OrderTransaction ot = new OrderTransaction();

	public Main() 
	{
		setBackground(Color.BLACK);
		setTitle("Mekdi Kiosk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1339, 873);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(102, 110, 702, 371);
		
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mekdi Kulim");
		lblNewLabel.setBounds(543, 131, 230, 57);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 37));
		
		JLabel lblMcdonaldKwelcome = new JLabel("Welcome");
		lblMcdonaldKwelcome.setBounds(561, 92, 251, 35);
		panel_1.add(lblMcdonaldKwelcome);
		lblMcdonaldKwelcome.setForeground(Color.WHITE);
		lblMcdonaldKwelcome.setFont(new Font("Tahoma", Font.PLAIN, 37));
		
		JButton btnNewButton_1 = new JButton("Take-Away");
		btnNewButton_1.setBounds(574, 490, 123, 45);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(waiter);
	
		btnNewButton_1.setFont(new Font("Keep Calm Med", Font.PLAIN, 10));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		
		JButton btnNewButton_2 = new JButton("Eat-In");
		btnNewButton_2.setBounds(574, 438, 123, 42);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(waiter);
		
		btnNewButton_2.setFont(new Font("Keep Calm Med", Font.PLAIN, 10));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Choose");
		lblNewLabel_2.setBounds(615, 415, 45, 13);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.WHITE);
		
		JLabel mekdilogo = new JLabel("");
		mekdilogo.setBounds(0, 0, 289, 138);
		panel_1.add(mekdilogo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setEnabled(false);
		tabbedPane.setBounds(10, 10, 1306, 680);
		contentPane.add(tabbedPane);
		
		
		tabbedPane.addTab("Welcome", null, panel_1, null);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transMode = "Eat-In";
				orderTransaction.setOrderMode(transMode);
				btnNewButton_2.addActionListener(waiter);
				tabbedPane.setSelectedIndex(1);
				System.out.println(orderTransaction.getOrderMode());
				
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				transMode = "Take-Away";
				orderTransaction.setOrderMode(transMode);
				btnNewButton_1.addActionListener(waiter);
				tabbedPane.setSelectedIndex(1);
				System.out.println(orderTransaction.getOrderMode());
			}
		});
		
		JLabel lblNewLabel_25 = new JLabel("New label");
		lblNewLabel_25.setIcon(new ImageIcon(Main.class.getResource("/Img/mcd latest.png")));
		lblNewLabel_25.setBounds(450, 213, 316, 159);
		panel_1.add(lblNewLabel_25);
		
		//SecondInterface------------------------------------------------------------------------------------------------------
		
		 JPanel panelSecond = new JPanel();
		 panelSecond.setBackground(Color.BLACK);
	        panelSecond.setBounds(19, 1, 1341, 700);
	        panelSecond.setLayout(null);
			//Image logo = new ImageIcon(this.getClass().getResource("/Mcchicken.png")).getImage();
	        
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(0, 50, 1191, 505);
	        panelSecond.add(scrollPane);
	        
	        JPanel panel_13 = new JPanel();
	        panel_13.setBackground(Color.BLACK);
	        panel_13.setPreferredSize(new Dimension (1500,1000));
	        scrollPane.setViewportView(panel_13);
	        panel_13.setLayout(null);
	        
	        JPanel panel = new JPanel();
	        panel.setBounds(0, 0, 228, 219);
	        panel_13.add(panel);
	        panel.setBackground(Color.BLACK);
	        panel.setForeground(Color.WHITE);
	        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "McChicken", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
	        panel.setLayout(null);
	        
	        JLabel mekdilogo1 = new JLabel("");
	        mekdilogo1.setBounds(6, 15, 174, 135);
	        panel.add(mekdilogo1);
	        mekdilogo1.setIcon(new ImageIcon(Main.class.getResource("/Img/Mcchicken.png")));
			
			JLabel lblNewLabel1 = new JLabel("RM8.10");
			lblNewLabel1.setBounds(83, 160, 89, 13);
			panel.add(lblNewLabel1);
			lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel1.setForeground(Color.WHITE);
			
			JButton buttonMcChicken = new JButton("Add To Cart");
			buttonMcChicken.setBounds(62, 183, 108, 21);
			panel.add(buttonMcChicken);
			buttonMcChicken.setBackground(Color.RED);
			buttonMcChicken.setForeground(Color.WHITE);
			
			JPanel panel_11 = new JPanel();
			panel_11.setBounds(264, 0, 253, 219);
			panel_13.add(panel_11);
			panel_11.setBackground(Color.BLACK);
			panel_11.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ayam Goreng Spicy (2pcs)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_11.setLayout(null);
			
			JLabel mchicken = new JLabel("");
			mchicken.setBounds(-15, 0, 241, 154);
			panel_11.add(mchicken);
			mchicken.setIcon(new ImageIcon(Main.class.getResource("/Img/Ayam-Goreng-McD\u2122-Spicy-Malaysia.png")));
			
			JLabel lblNewLabel_1 = new JLabel("RM11.90");
			lblNewLabel_1.setBounds(90, 164, 89, 13);
			panel_11.add(lblNewLabel_1);
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JButton buttonAyamMekdi2pcs = new JButton("Add To Cart");
			buttonAyamMekdi2pcs.setBounds(73, 187, 108, 21);
			panel_11.add(buttonAyamMekdi2pcs);
			buttonAyamMekdi2pcs.setForeground(Color.WHITE);
			buttonAyamMekdi2pcs.setBackground(Color.RED);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(552, 0, 317, 219);
			panel_13.add(panel_2);
			panel_2.setBackground(Color.BLACK);
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ayam Goreng Mekdi Spicy (5pcs)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_21 = new JLabel("");
			lblNewLabel_21.setBounds(43, 0, 224, 165);
			panel_2.add(lblNewLabel_21);
			lblNewLabel_21.setIcon(new ImageIcon(Main.class.getResource("/Img/AyamGoreng5pcs.png")));
			
			JLabel lblAyamGoreng_1 = new JLabel("RM30.20");
			lblAyamGoreng_1.setBounds(140, 160, 71, 23);
			panel_2.add(lblAyamGoreng_1);
			lblAyamGoreng_1.setForeground(Color.WHITE);
			lblAyamGoreng_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JButton buttonAyamMekdi5pcs = new JButton("Add To Cart");
			buttonAyamMekdi5pcs.setBounds(117, 188, 108, 21);
			panel_2.add(buttonAyamMekdi5pcs);
			buttonAyamMekdi5pcs.setForeground(Color.WHITE);
			buttonAyamMekdi5pcs.setBackground(Color.RED);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(897, 3, 259, 212);
			panel_13.add(panel_3);
			panel_3.setBackground(Color.BLACK);
			panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Spicy Chicken McDeluxe", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_3.setLayout(null);
			
			JLabel deluxechicken = new JLabel("");
			deluxechicken.setBounds(49, 20, 188, 122);
			panel_3.add(deluxechicken);
			deluxechicken.setIcon(new ImageIcon(Main.class.getResource("/Img/mekciken deluxe.png")));
			
			JLabel lblAyamGoreng_1_1 = new JLabel(" RM11.90");
			lblAyamGoreng_1_1.setBounds(96, 152, 73, 23);
			panel_3.add(lblAyamGoreng_1_1);
			lblAyamGoreng_1_1.setForeground(Color.WHITE);
			lblAyamGoreng_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JButton buttonChickenMcDeluxe = new JButton("Add To Cart");
			buttonChickenMcDeluxe.setBounds(71, 181, 108, 21);
			panel_3.add(buttonChickenMcDeluxe);
			buttonChickenMcDeluxe.setForeground(Color.WHITE);
			buttonChickenMcDeluxe.setBackground(Color.RED);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBounds(0, 247, 228, 167);
			panel_13.add(panel_5);
			panel_5.setBackground(Color.BLACK);
			panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Double CheeseBurger", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_5.setLayout(null);
			
			JLabel cheeseburger = new JLabel("New label");
			cheeseburger.setBounds(36, 15, 129, 85);
			panel_5.add(cheeseburger);
			cheeseburger.setIcon(new ImageIcon(Main.class.getResource("/Img/DoubleCheeseBurger.png")));
			
			JButton doublecheeseburger = new JButton("Add To Cart");
			
			doublecheeseburger.setBounds(55, 140, 108, 21);
			panel_5.add(doublecheeseburger);
			doublecheeseburger.setForeground(Color.WHITE);
			doublecheeseburger.setBackground(Color.RED);
			
			JLabel lblAyamGoreng_1_1_1 = new JLabel("RM 9.45");
			lblAyamGoreng_1_1_1.setBounds(72, 107, 66, 23);
			panel_5.add(lblAyamGoreng_1_1_1);
			lblAyamGoreng_1_1_1.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JPanel panel_6 = new JPanel();
			panel_6.setBounds(264, 247, 253, 167);
			panel_13.add(panel_6);
			panel_6.setBackground(Color.BLACK);
			panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "BigMac", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_6.setLayout(null);
			
			JLabel bigmac = new JLabel("");
			bigmac.setBounds(50, 0, 151, 118);
			panel_6.add(bigmac);
			bigmac.setIcon(new ImageIcon(Main.class.getResource("/Img/bigmac.png")));
			
			JButton bigmacburger = new JButton("Add To Cart");
			
			bigmacburger.setBounds(70, 136, 108, 21);
			panel_6.add(bigmacburger);
			bigmacburger.setForeground(Color.WHITE);
			bigmacburger.setBackground(Color.RED);
			
			JLabel lblAyamGoreng_1_1_1_1 = new JLabel("RM 10.40");
			lblAyamGoreng_1_1_1_1.setBounds(84, 115, 81, 23);
			panel_6.add(lblAyamGoreng_1_1_1_1);
			lblAyamGoreng_1_1_1_1.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JPanel panel_7 = new JPanel();
			panel_7.setBounds(552, 247, 317, 167);
			panel_13.add(panel_7);
			panel_7.setBackground(Color.BLACK);
			panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Fillet-O-Fish", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_7.setLayout(null);
			
			JLabel filetofish = new JLabel("New label");
			filetofish.setBounds(84, -38, 151, 154);
			panel_7.add(filetofish);
			filetofish.setIcon(new ImageIcon(Main.class.getResource("/Img/filetofish.png")));
			
			JButton filetofishbttn = new JButton("Add To Cart");
			
			filetofishbttn.setBounds(104, 136, 108, 21);
			panel_7.add(filetofishbttn);
			filetofishbttn.setForeground(Color.WHITE);
			filetofishbttn.setBackground(Color.RED);
			
			JLabel lblAyamGoreng_1_1_1_1_1 = new JLabel(" RM 8.45");
			lblAyamGoreng_1_1_1_1_1.setBounds(121, 115, 76, 23);
			panel_7.add(lblAyamGoreng_1_1_1_1_1);
			lblAyamGoreng_1_1_1_1_1.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JPanel panel_8 = new JPanel();
			panel_8.setBounds(897, 247, 259, 167);
			panel_13.add(panel_8);
			panel_8.setBackground(Color.BLACK);
			panel_8.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chicken McNuggets (6pcs)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_8.setLayout(null);
			
			JLabel nugget = new JLabel("");
			nugget.setBounds(10, -24, 224, 122);
			panel_8.add(nugget);
			nugget.setIcon(new ImageIcon(Main.class.getResource("/Img/ChickenMcNugggets6pcs.png")));
			
			JLabel lblAyamGoreng_1_1_1_1_1_1 = new JLabel("RM 9.40");
			lblAyamGoreng_1_1_1_1_1_1.setBounds(104, 108, 86, 23);
			panel_8.add(lblAyamGoreng_1_1_1_1_1_1);
			lblAyamGoreng_1_1_1_1_1_1.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JButton buttonChickenMcNugget = new JButton("Add To Cart");
			buttonChickenMcNugget.setBounds(82, 136, 108, 21);
			panel_8.add(buttonChickenMcNugget);
			buttonChickenMcNugget.setForeground(Color.WHITE);
			buttonChickenMcNugget.setBackground(Color.RED);
			
			JPanel panel_12 = new JPanel();
			panel_12.setBounds(0, 445, 228, 274);
			panel_13.add(panel_12);
			panel_12.setBackground(Color.BLACK);
			panel_12.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "McChicken Meal (Medium)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_12.setLayout(null);
			
			JLabel mchickenMeal = new JLabel("");
			mchickenMeal.setBounds(-13, 10, 241, 190);
			panel_12.add(mchickenMeal);
			mchickenMeal.setIcon(new ImageIcon(Main.class.getResource("/Img/McChickenMeal.png")));
			
			JButton btnmekcikenMeal = new JButton("Add To Cart");
			
			btnmekcikenMeal.setBounds(56, 243, 108, 21);
			panel_12.add(btnmekcikenMeal);
			btnmekcikenMeal.setForeground(Color.WHITE);
			btnmekcikenMeal.setBackground(Color.RED);
			
			JLabel lblAyamGoreng_1_1_1_4 = new JLabel("RM13.20");
			lblAyamGoreng_1_1_1_4.setBounds(77, 210, 75, 23);
			panel_12.add(lblAyamGoreng_1_1_1_4);
			lblAyamGoreng_1_1_1_4.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JPanel panel_111 = new JPanel();
			panel_111.setBounds(269, 445, 246, 274);
			panel_13.add(panel_111);
			panel_111.setBackground(Color.BLACK);
			panel_111.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chicken McNuggets 6pcs Meal (Medium) ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_111.setLayout(null);
			
			JLabel chickenMcnuggetMeal = new JLabel("");
			chickenMcnuggetMeal.setBounds(0, 15, 296, 172);
			panel_111.add(chickenMcnuggetMeal);
			chickenMcnuggetMeal.setIcon(new ImageIcon(Main.class.getResource("/Img/ChickenMcNuggets6pcsMeal.png")));
			
			JButton btnchickenmcnuggetmeal = new JButton("Add To Cart");
			
			btnchickenmcnuggetmeal.setBounds(67, 243, 108, 21);
			panel_111.add(btnchickenmcnuggetmeal);
			btnchickenmcnuggetmeal.setForeground(Color.WHITE);
			btnchickenmcnuggetmeal.setBackground(Color.RED);
			
			JLabel lblAyamGoreng_1_1_1_3_1 = new JLabel("RM13.20");
			lblAyamGoreng_1_1_1_3_1.setBounds(92, 210, 305, 23);
			panel_111.add(lblAyamGoreng_1_1_1_3_1);
			lblAyamGoreng_1_1_1_3_1.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JPanel panel_10 = new JPanel();
			panel_10.setBounds(552, 445, 317, 274);
			panel_13.add(panel_10);
			panel_10.setBackground(Color.BLACK);
			panel_10.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Fillet-O-Fish Meal (Medium)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_10.setLayout(null);
			
			JLabel filetOFishMediumMeal = new JLabel("");
			filetOFishMediumMeal.setBounds(36, 10, 247, 180);
			panel_10.add(filetOFishMediumMeal);
			filetOFishMediumMeal.setIcon(new ImageIcon(Main.class.getResource("/Img/Filet-O-FishMeal.png")));
			
			JLabel lblAyamGoreng_1_1_1_2 = new JLabel("RM13.00");
			lblAyamGoreng_1_1_1_2.setBounds(128, 210, 67, 23);
			panel_10.add(lblAyamGoreng_1_1_1_2);
			lblAyamGoreng_1_1_1_2.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JButton buttonFiletOFishMeal = new JButton("Add To Cart");
			
			buttonFiletOFishMeal.setBounds(104, 243, 108, 21);
			panel_10.add(buttonFiletOFishMeal);
			buttonFiletOFishMeal.setForeground(Color.WHITE);
			buttonFiletOFishMeal.setBackground(Color.RED);
			
			JPanel panel_9 = new JPanel();
			panel_9.setBounds(897, 445, 269, 274);
			panel_13.add(panel_9);
			panel_9.setBackground(Color.BLACK);
			panel_9.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Strawberry Sundae", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_9.setLayout(null);
			
			JLabel SundaeStrawberry = new JLabel("");
			SundaeStrawberry.setBounds(6, 15, 188, 165);
			panel_9.add(SundaeStrawberry);
			SundaeStrawberry.setIcon(new ImageIcon(Main.class.getResource("/Img/StrawberrySundae.png")));
			
			JLabel lblAyamGoreng_1_1_1_5 = new JLabel("RM 4.15");
			lblAyamGoreng_1_1_1_5.setBounds(107, 215, 73, 23);
			panel_9.add(lblAyamGoreng_1_1_1_5);
			lblAyamGoreng_1_1_1_5.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JButton butonStarwberySundae = new JButton("Add To Cart");
			
			butonStarwberySundae.setBounds(86, 243, 108, 21);
			panel_9.add(butonStarwberySundae);
			butonStarwberySundae.setForeground(Color.WHITE);
			butonStarwberySundae.setBackground(Color.RED);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBounds(1194, 0, 286, 212);
			panel_13.add(panel_4);
			panel_4.setBackground(Color.BLACK);
			panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chocolate Sundae", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			panel_4.setLayout(null);
			
			JLabel ChocolateSundae = new JLabel("");
			ChocolateSundae.setBounds(29, 0, 241, 167);
			panel_4.add(ChocolateSundae);
			ChocolateSundae.setIcon(new ImageIcon(Main.class.getResource("/Img/ChocolateSundae.png")));
			
			JButton btnChocalateSundae = new JButton("Add To Cart");
			
			btnChocalateSundae.setBounds(99, 181, 108, 21);
			panel_4.add(btnChocalateSundae);
			btnChocalateSundae.setForeground(Color.WHITE);
			btnChocalateSundae.setBackground(Color.RED);
			
			JLabel lblAyamGoreng_1_1_1_5_1 = new JLabel("RM 4.15");
			lblAyamGoreng_1_1_1_5_1.setBounds(119, 158, 70, 23);
			panel_4.add(lblAyamGoreng_1_1_1_5_1);
			lblAyamGoreng_1_1_1_5_1.setForeground(Color.WHITE);
			lblAyamGoreng_1_1_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			//Image chicken = new ImageIcon(this.getClass().getResource("/Ayam-Goreng-McD�-Spicy-Malaysia.png")).getImage();
			//Image ayam = new ImageIcon(this.getClass().getResource("/AyamGoreng5pcs.png")).getImage();
			
			JLabel lblNewLabel_3 = new JLabel("MENU");
			lblNewLabel_3.setBounds(51, 0, 162, 60);
			panelSecond.add(lblNewLabel_3);
			lblNewLabel_3.setFont(new Font("Keep Calm Med", Font.PLAIN, 30));
			lblNewLabel_3.setForeground(Color.WHITE);
			
			JButton btnNewButton_3 = new JButton("");
			btnNewButton_3.setBounds(571, 565, 100, 60);
			panelSecond.add(btnNewButton_3);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					choose = 1;
//					try {
//						release();
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					tabbedPane.setSelectedIndex(2);
					
				}
			});
			btnNewButton_3.setBackground(Color.RED);
			btnNewButton_3.setIcon(new ImageIcon(Main.class.getResource("/Img/AddToCart.png")));
			
			JLabel lblNewLabel_5 = new JLabel("Proceed To Cart");
			lblNewLabel_5.setBounds(432, 582, 129, 28);
			panelSecond.add(lblNewLabel_5);
			lblNewLabel_5.setFont(new Font("Keep Calm Med", Font.PLAIN, 14));
			lblNewLabel_5.setForeground(Color.WHITE);
			
			JButton btnNewButton_4 = new JButton("Cancel");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tabbedPane.setSelectedIndex(0);
					
				}
			});
			btnNewButton_4.setBounds(681, 565, 99, 60);
			panelSecond.add(btnNewButton_4);
			btnNewButton_4.setBackground(Color.RED);
			btnNewButton_4.setForeground(Color.WHITE);
			
			
			
			
			
			tabbedPane.addTab("Menu", null, panelSecond, null);
	
	
	//Panel Third---------------------------------------------------------------------------------------------------------
			JPanel panel1 = new JPanel();
			
			JPanel panel_121 = new JPanel();
			
	
			JPanel panelThird = new JPanel();
			panelThird.setBackground(Color.BLACK);
			panelThird.setBounds(10, 10, 701, 400);
			
			panelThird.setLayout(null);
			
			
			textField.setBounds(534, 286, 167, 19);
			panelThird.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel11 = new JLabel("Total Price:");
			lblNewLabel11.setBounds(534, 267, 96, 13);
			panelThird.add(lblNewLabel11);
			lblNewLabel11.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel11.setForeground(Color.WHITE);
			
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tabbedPane.setSelectedIndex(1);
				}
			});
			btnBack.setBounds(534, 367, 167, 21);
			panelThird.add(btnBack);
			btnBack.setBackground(Color.RED);
			btnBack.setForeground(Color.WHITE);
			
			JScrollPane scrollPane1 = new JScrollPane();
			scrollPane1.setBounds(0, 0, 516, 400);
			panelThird.add(scrollPane1);
			
			
			panel1.setPreferredSize(new Dimension (1000,1800));
			scrollPane1.setViewportView(panel1);
			panel1.setLayout(new GridLayout(13, 0, 0, 0));
			

			

			
			JPanel panelchocolatesundae = new JPanel();
			
			panelchocolatesundae.setLayout(null);
			
			JPanel panel_14 = new JPanel();
			panel_14.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chocolate Sundae", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_14.setBounds(4, 0, 271, 128);
			panelchocolatesundae.add(panel_14);
			panel_14.setLayout(null);
			
			
			JLabel lblNewLabel_23 = new JLabel("");
			lblNewLabel_23.setBounds(10, -30, 203, 158);
			panel_14.add(lblNewLabel_23);
			lblNewLabel_23.setIcon(new ImageIcon(Main.class.getResource("/Img/ChocolateSundae.png")));
			
			JLabel lblNewLabel_24 = new JLabel("RM4.15");
			lblNewLabel_24.setBounds(216, 105, 45, 13);
			panel_14.add(lblNewLabel_24);
			
			JSpinner spinner12 = new JSpinner();
			spinner12.setBounds(365, 57, 30, 20);
			spinner12.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelchocolatesundae.add(spinner12);
			
			//********************************Chicken McNuggets (6pcs)************************************
			Panel chickenNugget6pcs = new Panel();
			chickenNugget6pcs.setLayout(null);
			
			JPanel panel_17 = new JPanel();
			panel_17.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chicken McNuggets (6pcs)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_17.setBounds(10, 10, 269, 124);
			chickenNugget6pcs.add(panel_17);
			panel_17.setLayout(null);
			
			JLabel lblNewLabel_26 = new JLabel("");
			lblNewLabel_26.setBounds(0, -19, 209, 118);
			panel_17.add(lblNewLabel_26);
			lblNewLabel_26.setIcon(new ImageIcon(Main.class.getResource("/Img/ChickenMcNugggets6pcs.png")));
			
			JLabel lblNewLabel_27 = new JLabel("RM9.40");
			lblNewLabel_27.setBounds(218, 101, 45, 13);
			panel_17.add(lblNewLabel_27);
			
			JSpinner spinner4 = new JSpinner();
			spinner4.setBounds(369, 61, 30, 20);
			spinner4.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			chickenNugget6pcs.add(spinner4);
			
			JPanel panelstrwberysundae = new JPanel();
			
			panelstrwberysundae.setLayout(null);
				
			JPanel panel_1111 = new JPanel();
			panel_1111.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Strawberry Sundae", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1111.setBounds(4, 0, 278, 128);
			panelstrwberysundae.add(panel_1111);
			panel_1111.setLayout(null);
			
			JLabel lblNewLabel_211 = new JLabel("");
			lblNewLabel_211.setBounds(0, -32, 200, 160);
			panel_1111.add(lblNewLabel_211);
			lblNewLabel_211.setIcon(new ImageIcon(Main.class.getResource("/Img/StrawberrySundae.png")));
			
			JLabel lblNewLabel_22 = new JLabel("RM4.15");
			lblNewLabel_22.setBounds(220, 69, 45, 13);
			panel_1111.add(lblNewLabel_22);
			
			JSpinner spinner11 = new JSpinner();
			spinner11.setBounds(363, 55, 30, 20);
			spinner11.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelstrwberysundae.add(spinner11);
			
			
			
			JPanel panel_filetofishmeal = new JPanel();
			
			panel_filetofishmeal.setLayout(null);
			
			JPanel panel_31 = new JPanel();
			panel_31.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Fillet-O-Fish Meal (Medium)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_31.setBounds(6, 0, 306, 128);
			panel_filetofishmeal.add(panel_31);
			panel_31.setLayout(null);
			
			
			JLabel lblNewLabel_15 = new JLabel("");
			lblNewLabel_15.setBounds(10, -76, 225, 204);
			panel_31.add(lblNewLabel_15);
			lblNewLabel_15.setIcon(new ImageIcon(Main.class.getResource("/Img/Filet-O-FishMeal.png")));
			
			JLabel lblNewLabel_16 = new JLabel("RM13.00");
			lblNewLabel_16.setBounds(224, 105, 70, 13);
			panel_31.add(lblNewLabel_16);
			
			JSpinner spinner10 = new JSpinner();
			spinner10.setBounds(382, 54, 30, 20);
			spinner10.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panel_filetofishmeal.add(spinner10);
			
			JPanel panelnuget6meal = new JPanel();
			
			panelnuget6meal.setLayout(null);
			
			JPanel panel_131 = new JPanel();
			panel_131.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chicken McNuggets 6pcs Meal (Medium)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_131.setBounds(0, 0, 288, 132);
			panelnuget6meal.add(panel_131);
			panel_131.setLayout(null);
			
			
			JLabel lblNewLabel_19 = new JLabel("");
			lblNewLabel_19.setBounds(10, -69, 221, 191);
			panel_131.add(lblNewLabel_19);
			lblNewLabel_19.setIcon(new ImageIcon(Main.class.getResource("/Img/ChickenMcNuggets6pcsMeal.png")));
			
			JLabel lblNewLabel_20 = new JLabel("RM13.20");
			lblNewLabel_20.setBounds(233, 109, 55, 13);
			panel_131.add(lblNewLabel_20);
			
			JSpinner spinner9 = new JSpinner();
			spinner9.setBounds(381, 52, 30, 20);
			spinner9.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelnuget6meal.add(spinner9);
			
			JPanel panelmcchickenmeal = new JPanel();
			
			panelmcchickenmeal.setLayout(null);
			
			
			panel_121.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "McChicken Meal (Medium)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_121.setBounds(10, 0, 284, 128);
			panelmcchickenmeal.add(panel_121);
			panel_121.setLayout(null);
			
			
			JLabel lblNewLabel_17 = new JLabel("");
			lblNewLabel_17.setBounds(10, -62, 217, 180);
			panel_121.add(lblNewLabel_17);
			lblNewLabel_17.setIcon(new ImageIcon(Main.class.getResource("/Img/McChickenMeal.png")));
			
			JLabel lblNewLabel_18 = new JLabel("RM13.20");
			lblNewLabel_18.setBounds(229, 105, 55, 13);
			panel_121.add(lblNewLabel_18);
			
			JSpinner spinner8 = new JSpinner();
			spinner8.setBounds(390, 53, 30, 20);
			spinner8.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelmcchickenmeal.add(spinner8);
			
			JPanel paneldoublecheeseburger = new JPanel();
			
			paneldoublecheeseburger.setLayout(null);
			
			JPanel panel_doublecheese = new JPanel();
			panel_doublecheese.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Double CheeseBurger", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_doublecheese.setBounds(4, 0, 315, 134);
			paneldoublecheeseburger.add(panel_doublecheese);
			panel_doublecheese.setLayout(null);
			
			
			JLabel lblNewLabel_9 = new JLabel("");
			lblNewLabel_9.setBounds(6, 15, 153, 118);
			panel_doublecheese.add(lblNewLabel_9);
			lblNewLabel_9.setIcon(new ImageIcon(Main.class.getResource("/Img/DoubleCheeseBurger.png")));
			
			JLabel lblNewLabel_10 = new JLabel("RM9.45");
			lblNewLabel_10.setBounds(239, 111, 70, 13);
			panel_doublecheese.add(lblNewLabel_10);
			
			JSpinner spinner5 = new JSpinner();
			spinner5.setBounds(417, 54, 30, 20);
			spinner5.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			paneldoublecheeseburger.add(spinner5);
			
			JPanel panelfiletofish = new JPanel();
			
			panelfiletofish.setLayout(null);
			
			JPanel panel_101 = new JPanel();
			panel_101.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Fillet-O-Fish", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_101.setBounds(4, 0, 293, 134);
			panelfiletofish.add(panel_101);
			panel_101.setLayout(null);
			
			
			JLabel lblNewLabel_13 = new JLabel("");
			lblNewLabel_13.setBounds(10, -24, 174, 148);
			panel_101.add(lblNewLabel_13);
			lblNewLabel_13.setIcon(new ImageIcon(Main.class.getResource("/Img/filetofish.png")));
			
			JLabel lblNewLabel_14 = new JLabel("RM8.45");
			lblNewLabel_14.setBounds(238, 111, 45, 13);
			panel_101.add(lblNewLabel_14);
			
			JSpinner spinner7 = new JSpinner();
			spinner7.setBounds(390, 56, 30, 20);
			spinner7.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelfiletofish.add(spinner7);
			
			
			JPanel panelbigmac = new JPanel();
			panelbigmac.setBorder(null);
			panelbigmac.setBounds(6, 0, 294, 134);
			
			panelbigmac.setLayout(null);
			
			JPanel panel_15 = new JPanel();
			panel_15.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "BigMac", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_15.setBounds(10, 0, 273, 139);
			
			panel_15.setLayout(null);
			panelbigmac.add(panel_15);
			
			JLabel lblNewLabel_11 = new JLabel("");
			lblNewLabel_11.setBounds(49, 15, 145, 118);
			panel_15.add(lblNewLabel_11);
			lblNewLabel_11.setIcon(new ImageIcon(Main.class.getResource("/Img/bigmac.png")));
			
			JLabel lblNewLabel_12 = new JLabel("RM10.40");
			lblNewLabel_12.setBounds(218, 120, 55, 13);
			panel_15.add(lblNewLabel_12);
			
			JSpinner spinner6 = new JSpinner();
			spinner6.setBounds(390, 63, 30, 20);
			spinner6.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelbigmac.add(spinner6);
			
			JPanel panelchickendlx = new JPanel();
			
			panelchickendlx.setLayout(null);
			
			JPanel panel_mcchikendlx = new JPanel();
			panel_mcchikendlx.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Spicy Chicken McDeluxe", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_mcchikendlx.setBounds(4, 0, 299, 134);
			panelchickendlx.add(panel_mcchikendlx);
			panel_mcchikendlx.setLayout(null);
			
			
			JLabel lblNewLabel_7 = new JLabel("");
			lblNewLabel_7.setBounds(46, 0, 152, 128);
			panel_mcchikendlx.add(lblNewLabel_7);
			lblNewLabel_7.setIcon(new ImageIcon(Main.class.getResource("/Img/mekciken deluxe.png")));
			
			JLabel lblNewLabel_8 = new JLabel("RM11.90");
			lblNewLabel_8.setBounds(244, 115, 70, 13);
			panel_mcchikendlx.add(lblNewLabel_8);
			
			JSpinner spinner3 = new JSpinner();
			spinner3.setBounds(395, 60, 30, 20);
			spinner3.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelchickendlx.add(spinner3);
			
			JPanel panelayam2pcs = new JPanel();
			
			panelayam2pcs.setLayout(null);
			
			JPanel panel_ayam2pcs = new JPanel();
			panel_ayam2pcs.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ayam Goreng Mekdi Spicy (2pcs)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_ayam2pcs.setBounds(10, 0, 297, 144);
			panelayam2pcs.add(panel_ayam2pcs);
			panel_ayam2pcs.setLayout(null);
			
			
			JLabel lblNewLabel_31 = new JLabel("");
			lblNewLabel_31.setBounds(-32, -12, 241, 151);
			panel_ayam2pcs.add(lblNewLabel_31);
			lblNewLabel_31.setIcon(new ImageIcon(Main.class.getResource("/Img/Ayam-Goreng-McD\u2122-Spicy-Malaysia.png")));
			
			JLabel lblNewLabel_4 = new JLabel("RM11.90");
			lblNewLabel_4.setBounds(242, 121, 55, 13);
			panel_ayam2pcs.add(lblNewLabel_4);
			
			JSpinner spinner1 = new JSpinner();
			spinner1.setBounds(411, 51, 30, 20);
			spinner1.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelayam2pcs.add(spinner1);
			
			JPanel panel_ayam5pcs = new JPanel();
			
			panel_ayam5pcs.setLayout(null);
			
			JPanel panel_61 = new JPanel();
			panel_61.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ayam Goreng Mekdi SPicy (5pcs)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_61.setBounds(4, 0, 302, 138);
			panel_ayam5pcs.add(panel_61);
			panel_61.setLayout(null);
			
			
			JLabel lblNewLabel_51 = new JLabel("New label");
			lblNewLabel_51.setBounds(0, -14, 235, 180);
			panel_61.add(lblNewLabel_51);
			lblNewLabel_51.setIcon(new ImageIcon(Main.class.getResource("/Img/AyamGoreng5pcs.png")));
			
			JLabel lblNewLabel_6 = new JLabel("RM30.20");
			lblNewLabel_6.setBounds(247, 115, 55, 13);
			panel_61.add(lblNewLabel_6);
			
			JSpinner spinner2 = new JSpinner();
			spinner2.setBounds(437, 50, 30, 20);
			spinner2.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panel_ayam5pcs.add(spinner2);
			
			JPanel panelmcchicken = new JPanel();
			
			panelmcchicken.setLayout(null);
			
			JPanel panel_mcchicken = new JPanel();
			panel_mcchicken.setBorder(new TitledBorder(null, "Mc Chicken", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_mcchicken.setBounds(12, 10, 290, 115);
			panelmcchicken.add(panel_mcchicken);
			panel_mcchicken.setLayout(null);
			
			
			JLabel lblNewLabel_111 = new JLabel("");
			lblNewLabel_111.setBounds(10, -23, 167, 128);
			panel_mcchicken.add(lblNewLabel_111);
			lblNewLabel_111.setIcon(new ImageIcon(Main.class.getResource("/Img/Mcchicken.png")));
			
			JLabel lblNewLabel_2111 = new JLabel("RM8.10");
			lblNewLabel_2111.setBounds(187, 92, 45, 13);
			panel_mcchicken.add(lblNewLabel_2111);
			
			JSpinner spinner0 = new JSpinner();
			spinner0.setBounds(413, 55, 30, 20);
			spinner0.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			panelmcchicken.add(spinner0);
			//int quantity = (Integer)spinner_11.getValue();			
			buttonMcChicken.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(choosedItem[0] == false) {
					choosedItem[0] = true;
					ItemProduct itemProd = prodManager.getProduct(1);		            
					
					int quantity = 1;
					
					orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(quantity);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());

		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(panelmcchicken);
		            buttonMcChicken.setText("Removed");
		            
					}
					else {
						panel1.remove(panelmcchicken);
						buttonMcChicken.setText("Add to cart");
						choosedItem[0] = false;
					}
					UpdateTotal();
		            
				}
				
			});
			
			buttonAyamMekdi2pcs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(choosedItem[1] == false) {
					choosedItem[1] = true;
					ItemProduct itemProd = prodManager.getProduct(2);
		            
					int quantity = Integer.parseInt(spinner1.getValue().toString());	
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(quantity);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());

		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
					
		            panel1.add(panelayam2pcs);
		            buttonAyamMekdi2pcs.setText("Removed");
		            
					}
					else {
						panel1.remove(panelayam2pcs);
						buttonAyamMekdi2pcs.setText("Add to cart");
						choosedItem[1] = false;
					}    
					UpdateTotal();
				}

			});
			
			buttonAyamMekdi5pcs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(choosedItem[2] == false) {
						choosedItem[2] = true;
					ItemProduct itemProd = prodManager.getProduct(3);
		            
					int quantity = Integer.parseInt(spinner2.getValue().toString());	
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());

		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
					
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            panel1.add(panel_ayam5pcs);
		            buttonAyamMekdi5pcs.setText("Removed");
		            
					}
					else {
						panel1.remove(panel_ayam5pcs);
						buttonAyamMekdi5pcs.setText("Add to cart");
						choosedItem[2] = false;
					}
					UpdateTotal();
				}
				
			
			});
			
			buttonChickenMcDeluxe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(choosedItem[3] == false) {
						choosedItem[3] = true;
					ItemProduct itemProd = prodManager.getProduct(4);
		            
					int quantity = Integer.parseInt(spinner3.getValue().toString());	
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
					
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            panel1.add(panelchickendlx); 
		            buttonChickenMcDeluxe.setText("Removed");
		            
					}
					else {
						panel1.remove(panelchickendlx);
						buttonChickenMcDeluxe.setText("Add to cart");
						choosedItem[3] = false;
					}  
					UpdateTotal();

				}
				
			
			});
			
			buttonChickenMcNugget.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(choosedItem[4] == false) {
					choosedItem[4] = true;
					ItemProduct itemProd = prodManager.getProduct(5);
		            
					int quantity = Integer.parseInt(spinner4.getValue().toString());	
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
					
		            panel1.add(chickenNugget6pcs); 
		            buttonChickenMcNugget.setText("Removed");
		            
					}
					else {
						panel1.remove(chickenNugget6pcs);
						buttonChickenMcNugget.setText("Add to cart");
						choosedItem[4] = false;
					} 
					UpdateTotal();
				}
				
			
			});
			
			doublecheeseburger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(choosedItem[5] == false) {
						choosedItem[5] = true;
					ItemProduct itemProd = prodManager.getProduct(6);
		            
					int quantity = Integer.parseInt(spinner5.getValue().toString());
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(paneldoublecheeseburger); 
		            doublecheeseburger.setText("Removed");
					}
					else {
						panel1.remove(paneldoublecheeseburger);
						doublecheeseburger.setText("Add to cart");
						choosedItem[5] = false;
					} 
					UpdateTotal();
				}
			});
			
			bigmacburger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(choosedItem[6] == false) {
						choosedItem[6] = true;
					ItemProduct itemProd = prodManager.getProduct(7);
		            
					int quantity = Integer.parseInt(spinner6.getValue().toString());
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(panelbigmac); 
		            bigmacburger.setText("Removed");
					}
					else {
						panel1.remove(panelbigmac);
						bigmacburger.setText("Add to cart");
						choosedItem[6] = false;
					} 
					UpdateTotal();
				}
			});
			
			filetofishbttn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(choosedItem[7] == false) {
						choosedItem[7] = true;
					ItemProduct itemProd = prodManager.getProduct(8);
		            
					int quantity = Integer.parseInt(spinner7.getValue().toString());
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(panelfiletofish); 
		            filetofishbttn.setText("Removed");
					}
					else {
						panel1.remove(panelfiletofish);
						filetofishbttn.setText("Add to cart");
						choosedItem[7] = false;
					} 
					UpdateTotal();
				}
			});
			
			btnmekcikenMeal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(choosedItem[8] == false) {
						choosedItem[8] = true;
					ItemProduct itemProd = prodManager.getProduct(9);
		            
					int quantity = Integer.parseInt(spinner8.getValue().toString());
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(panelmcchickenmeal);
		            btnmekcikenMeal.setText("Removed");
					}
					else {
						panel1.remove(panelmcchickenmeal);
						btnmekcikenMeal.setText("Add to cart");
						choosedItem[8] = false;
					} 
					UpdateTotal();
				}
			});
			
			btnchickenmcnuggetmeal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(choosedItem[9] == false) {
						choosedItem[9] = true;
					ItemProduct itemProd = prodManager.getProduct(10);
		            
					
					
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            int quantity = Integer.parseInt(spinner9.getValue().toString());
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(panelnuget6meal);
		            btnchickenmcnuggetmeal.setText("Removed");
					}
					else {
						panel1.remove(panelnuget6meal);
						btnchickenmcnuggetmeal.setText("Add to cart");
						choosedItem[9] = false;
					} 
					UpdateTotal();
				}
			});
			
			buttonFiletOFishMeal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(choosedItem[10] == false) {
						choosedItem[10] = true;
					ItemProduct itemProd = prodManager.getProduct(11);
		            
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            int quantity = Integer.parseInt(spinner10.getValue().toString());
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(panel_filetofishmeal);
		            buttonFiletOFishMeal.setText("Removed");
					}
					else {
						panel1.remove(panel_filetofishmeal);
						buttonFiletOFishMeal.setText("Add to cart");
						choosedItem[10] = false;
					} 
					UpdateTotal();
				}
			});
			
			butonStarwberySundae.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(choosedItem[11] == false) {
						choosedItem[11] = true;
					ItemProduct itemProd = prodManager.getProduct(13);
		            
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            int quantity = Integer.parseInt(spinner11.getValue().toString());
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(panelstrwberysundae);
		            butonStarwberySundae.setText("Removed");
					}
					else {
						panel1.remove(panelstrwberysundae);
						butonStarwberySundae.setText("Add to cart");
						choosedItem[11] = false;
					} 
					UpdateTotal();
				}
			});
			
			btnChocalateSundae.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(choosedItem[12] == false) {
						choosedItem[12] = true;
					ItemProduct itemProd = prodManager.getProduct(14);
		            
		            orderedItem.setItemProduct(itemProd);
		            orderedItem.setQuantity(1);
		            orderedItem.setSubTotalAmount(itemProd.getPrice());
		            
		            int quantity = Integer.parseInt(spinner12.getValue().toString());
		            double subtotal = itemProd.getPrice()*quantity;
		            total = total + subtotal;
		            textField.setText(Double.toString(total));
		            
		            System.out.println("Name : "+itemProd.getName());
		            System.out.print(orderedItem.getItemProduct());
		            System.out.println("\nQuantity : "+orderedItem.getQuantity());
		            System.out.println("Subtotal amount : "+orderedItem.getSubTotalAmount());
		            
		            panel1.add(panelchocolatesundae);
		            btnChocalateSundae.setText("Removed");
					}
					else {
						panel1.remove(panelchocolatesundae);
						btnChocalateSundae.setText("Add to cart");
						choosedItem[11] = false;
					} 
					UpdateTotal();
				}
			});
			
			spinner0.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[0] = baseprice[0] * (Integer)spinner0.getValue();
					UpdateTotal();
					quantity[0] = (Integer)spinner0.getValue();
				}
			});
			spinner1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[1] = baseprice[1] * (Integer)spinner1.getValue();
					UpdateTotal();
					quantity[1] = (Integer)spinner1.getValue();
				}
			});
			spinner2.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[2] = baseprice[2] * (Integer)spinner2.getValue();
					UpdateTotal();
					quantity[2] = (Integer)spinner2.getValue();
				}
			});
			spinner3.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[3] = baseprice[3] * (Integer)spinner3.getValue();
					UpdateTotal();
					quantity[3] = (Integer)spinner3.getValue();
				}
			});spinner4.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[4] = baseprice[4] * (Integer)spinner4.getValue();
					UpdateTotal();
					quantity[4] = (Integer)spinner4.getValue();
				}
			});
			spinner5.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[5] = baseprice[5] * (Integer)spinner5.getValue();
					UpdateTotal();
					quantity[5] = (Integer)spinner5.getValue();
				}
			});
			spinner6.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[6] = baseprice[6] * (Integer)spinner6.getValue();
					UpdateTotal();
					quantity[6] = (Integer)spinner6.getValue();
				}
			});
			spinner7.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[7] = baseprice[7] * (Integer)spinner7.getValue();
					UpdateTotal();
					quantity[7] = (Integer)spinner7.getValue();
				}
			});
			spinner8.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[8] = baseprice[8] * (Integer)spinner8.getValue();
					UpdateTotal();
					quantity[8] = (Integer)spinner8.getValue();
				}
			});
			spinner9.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[9] = baseprice[9] * (Integer)spinner9.getValue();
					UpdateTotal();
					quantity[9] = (Integer)spinner9.getValue();
				}
			});
			spinner10.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[10] = baseprice[10] * (Integer)spinner10.getValue();
					UpdateTotal();
					quantity[10] = (Integer)spinner10.getValue();
				}
			});
			spinner11.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[11] = baseprice[11] * (Integer)spinner11.getValue();
					UpdateTotal();
					quantity[11] = (Integer)spinner11.getValue();
				}
			});
			spinner12.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					subtotal[12] = baseprice[12] * (Integer)spinner12.getValue();
					UpdateTotal();
					quantity[12] = (Integer)spinner12.getValue();
				}
			});
			
						
			
			tabbedPane.addTab("Cart List", null, panelThird, null);
	
			//Payment Side---------------------------------------------------------------------------------------
			JPanel panelFourth = new JPanel();
			panelFourth.setBounds(138, 63, 627, 390);
			
			panelFourth.setLayout(null);
			
			JScrollPane scrollPane11 = new JScrollPane();
			scrollPane11.setBounds(0, 0, 1301, 653);
			panelFourth.add(scrollPane11);
			
			JPanel panel11 = new JPanel();
			panel11.setBackground(Color.BLACK);
			panel11.setPreferredSize(new Dimension (1000,1000));
			scrollPane11.setViewportView(panel11);
			panel11.setLayout(null);
			
			JLabel lblNewLabel111 = new JLabel("Order Summary");
			lblNewLabel111.setForeground(Color.WHITE);
			lblNewLabel111.setBounds(61, 464, 127, 22);
			panel11.add(lblNewLabel111);
			lblNewLabel111.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblTotal = new JLabel("");
			lblTotal.setBounds(236, 16, 0, 0);
			panel11.add(lblTotal);
			lblTotal.setBackground(Color.GRAY);
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblTotal_1 = new JLabel("Total:");
			lblTotal_1.setForeground(Color.WHITE);
			lblTotal_1.setBounds(61, 490, 46, 22);
			panel11.add(lblTotal_1);
			lblTotal_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_1 = new JTextField();
			textField_1.setBounds(56, 544, 180, 19);
			panel11.add(textField_1);
			textField_1.setColumns(10);
			
			JButton btnNewButton1 = new JButton("Confirm");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(!(textField_1.getText().isBlank()) && isNumeric(textField_1.getText()) && textField_1.getText().length() >12) {
						orderTransaction.setLast4Digits(Integer.parseInt(textField_1.getText().substring(textField_1.getText().length() - 4)));
						//String creditCardNumber = textField_1.getText();
						
						orderTransaction.setTransactionStatus(true);
						choose = 1;
						
						JOptionPane.showMessageDialog(null, "Success Payment. Thank You! Please Visit Again");
						try {
							setOrderedItem();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						textField_1.getText();
						textArea.getText();
						System.out.println(textField_1.getText());
						
						String creditcard = textField_1.getText();
						String last4digits = "";
						
						 for(int i=creditcard.length()-4; i<creditcard.length();i++) {
							 last4digits +=creditcard.charAt(i);
							  
						 }
						 
						orderTransaction.setLast4Digits(Integer.parseInt(last4digits));
						//System.out.println(last4digits);
							
						orderTransaction.setAmountCharged((float)total);
						//System.out.println(orderTransaction.getAmountCharged());
						
						order.setOrderedItems(orderedItems);
						//System.out.println(order.getOrderedItems());
						
						order.setTotalAmount((float)total);
						 
					}
					else {
						orderTransaction.setTransactionStatus(false);
						JOptionPane.showMessageDialog(null, "Please insert your credit card number correctly.\nMake sure it is integer and valid length.", "ERROR!!!", JOptionPane.INFORMATION_MESSAGE);
					}
						
						
						
						try {
							release();
						}
					 catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
						tabbedPane.setSelectedIndex(0);	
				}
			});
			btnNewButton1.setBounds(56, 573, 100, 21);
			panel11.add(btnNewButton1);
			
			JButton btnBack1 = new JButton("Back");
			btnBack1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tabbedPane.setSelectedIndex(2);
				}
			});
			btnBack1.setBounds(166, 573, 70, 21);
			panel11.add(btnBack1);
			
			JLabel lblTotal_1_1 = new JLabel("Input Credit Card");
			lblTotal_1_1.setForeground(Color.WHITE);
			lblTotal_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTotal_1_1.setBounds(61, 522, 152, 22);
			panel11.add(lblTotal_1_1);
			
			
			lblTotal_1_2.setForeground(Color.WHITE);
			lblTotal_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTotal_1_2.setBounds(115, 490, 184, 22);
			panel11.add(lblTotal_1_2);
			textArea.setEditable(false);
			
			textArea.setBounds(61, 97, 865, 346);
			panel11.add(textArea);
			
			JButton btnPayment = new JButton("Payment");
			btnPayment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tabbedPane.setSelectedIndex(3);
					printOrderedItem();
					
				}
			});
			btnPayment.setBounds(534, 325, 167, 21);
			panelThird.add(btnPayment);
			btnPayment.setBackground(Color.RED);
			btnPayment.setForeground(Color.WHITE);
			
			tabbedPane.addTab("Payment", null, panelFourth, null);
			
			JLabel lblNewLabel_28 = new JLabel("www.mekdi.com.my");
			lblNewLabel_28.setFont(new Font("Keep Calm Med", Font.PLAIN, 17));
			lblNewLabel_28.setForeground(Color.WHITE);
			lblNewLabel_28.setBounds(570, 729, 191, 39);
			contentPane.add(lblNewLabel_28);
			
			JLabel lblNewLabel_28_1 = new JLabel("Copy right 2021");
			lblNewLabel_28_1.setForeground(Color.WHITE);
			lblNewLabel_28_1.setFont(new Font("Keep Calm Med", Font.PLAIN, 13));
			lblNewLabel_28_1.setBounds(597, 758, 191, 39);
			contentPane.add(lblNewLabel_28_1);
	}
	
	public boolean[] getChoosedItem() throws InterruptedException {
		waitForInput();
		return choosedItem;
	}
	
	public void waitForInput() throws InterruptedException {
		synchronized(this) {
			wait();
		}
	}
	
	public void release() throws InterruptedException {
		synchronized(this) {
			notifyAll();
		}
	}
	
	public int getChoose() throws InterruptedException {
		waitForInput();
		return choose;
	}
	
	
	
	public void setTransMode(String transMode) {
		this.transMode = transMode;
	}
	
	
	
	public String getTransMode() {
		waiter.waitFor();
		return transMode;
	}
	
	public void UpdateTotal() {
		total = 0;
		for(int i = 0; i<choosedItem.length ; i++) {
			if (choosedItem[i]) {
				total += subtotal[i];
			}
		}
		textField.setText(String.format("%.2f",(Double)total));
	}
	
	public void printOrderedItem() {
		String text = "";
		for(int i = 0;i<choosedItem.length ; i++) {
			ItemProduct itemProduct = new ItemProduct();
			itemProduct = prodManager.getProduct(i+1);
			
			if(choosedItem[i]) {
				text+=(i+1)+ ". " + itemProduct.getName() +" x " + quantity[i] + " : RM" + subtotal[i] + "\n";
				
				orderedItem.setItemProduct(prodManager.getProduct(i));
			}

		}
		textArea.setText(text);
		lblTotal_1_2.setText("RM"+Double.toString(total));

	}
	
	
	
	public void setcreditCard() throws InterruptedException
    {

		textField_1.getText();

    }
	
	public String getcreditCard() throws InterruptedException
    {
        waitForInput();
        return textField_1.getText();

    }
	
public void updateServerDate (String serverDate) {
		
		this.lblServerDate.setText(serverDate);
		
	}
	
	
	public void updateConnectionStatus (boolean connStatus) {
		

		// Default status. Assuming for the worst case scenario.
		String status = "No connection to server.";
		
		// Validate status of connection
		if (connStatus)
			status = "Connection has established.";
		
				
		// Update the status on frame
		this.lblStatusValue.setText(status);
	}
	
	public OrderTransaction getOrderTransaction() {
		return orderTransaction;
	}
	

	public void setOrderedItem()  throws SQLException {
		String text = "";
		int j = 1;
		for(int i = 0;i<choosedItem.length ; i++) {
			ItemProduct itemProduct = new ItemProduct();
			itemProduct = prodManager.getProduct(i+1);
			
			if(choosedItem[i]) {
				orderedItem = new OrderedItem();
				while(prodManager.getProduct(j) == null)
				{
					j++;
				}
				text+=(i+1)+ ". " + itemProduct.getName() + " : RM" + subtotal[i] + "\n";
				
				orderedItem.setItemProduct(prodManager.getProduct(j));
				orderedItem.setQuantity(quantity[i]);
				orderedItem.getItemProduct().setItemProduct(j);
				orderedItem.setSubTotalAmount((float)subtotal[i]);
				
				orderedItems.add(orderedItem);
				
			}
				j++;
		}
//		textArea.setText(text);
//		lblTotal_1_2.setText("RM"+Double.toString(total));

	}
	
	
	public ArrayList<OrderedItem> getOrderedItems() throws SQLException, InterruptedException {
		
		return orderedItems;
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}