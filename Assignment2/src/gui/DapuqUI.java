package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class DapuqUI extends JFrame {

	private JPanel contentPane;


	public TextArea textArea = new TextArea();
	
	public DapuqUI() {
		setTitle("Dapuq Mekdi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 486);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textArea.setBounds(36, 129, 547, 278);
		contentPane.add(textArea);
		
		Label label = new Label("Kitchen");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("JUSTICE LEAGUE", Font.BOLD, 20));
		label.setBounds(284, 33, 226, 43);
		contentPane.add(label);
		
		Label label_1 = new Label("Order Details");
		label_1.setForeground(Color.ORANGE);
		label_1.setFont(new Font("JUSTICE LEAGUE", Font.BOLD, 17));
		label_1.setBounds(36, 80, 162, 43);
		contentPane.add(label_1);
		
		
		Label label_2 = new Label(getCurrentDate());
		label_2.setFont(new Font("Dialog", Font.BOLD, 13));
		label_2.setForeground(Color.ORANGE);
		label_2.setBounds(465, 95, 114, 21);
		contentPane.add(label_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(DapuqUI.class.getResource("/Img/McDonalds-golden-arches_800x800 (1).png")));
		lblNewLabel.setBounds(204, 29, 84, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("www.mekdi.com.my");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(245, 420, 114, 13);
		contentPane.add(lblNewLabel_1);
		
		
		
	}
	
	public void displayInfo(String text)
	{
		Calendar cal = new GregorianCalendar();
		int second = cal.get(Calendar.SECOND);
		int minute = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR);
		textArea.setEditable(true);
		String nextText = Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second)+":\n"+text;
		textArea.setText(nextText+textArea.getText());
		textArea.setEditable(false);
		
	}


	public void displayOrderMode(String text)
	{
		
		textArea.setEditable(true);
		String nextText = text;
		textArea.setText(nextText+textArea.getText());
		textArea.setEditable(false);
		
	}


	
	public String getCurrentDate() {
			
			String currentDate = new Date().toString();
			
			return currentDate;
			
		}
		
	}
	

