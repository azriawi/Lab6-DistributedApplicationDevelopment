package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
//import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import gui.Main;
import kioskapp.itemproduct.ItemProduct;
import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;
import writefile.WriteFile;


public class TCPKioskClientApplication {

    @SuppressWarnings("unchecked")
	public static void main(String[] args) 
            throws UnknownHostException, IOException, InterruptedException, SQLException, ClassNotFoundException {

        // Launch client-side frame
       Main main = new Main();
       main.setVisible(true);
       
       while(true) 
       {

	        //Connect to the server @ localhost, port 4646
	        Socket socket = new Socket(InetAddress.getLocalHost(), 4646);
	        socket.setKeepAlive(true);
	        
	        ItemProduct itemProduct = new ItemProduct();
	        
	        // Update the status of the connection
	       // main.updateConnectionStatus(socket.isConnected());
	        DataOutputStream dataOs = new DataOutputStream(socket.getOutputStream());
			DataInputStream dataIs = new DataInputStream(socket.getInputStream());
	
	        // create object utk baca dgn hantaq
	        ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
	        ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
	        
	       
	        //get credit card and order transaction
	        String creditCardNum = main.getcreditCard();
	        dataOs.writeUTF(creditCardNum);
	        dataOs.flush();
	        
	        
	        ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
	        orderedItems = main.getOrderedItems();
	        
	        
	//        for(OrderedItem o: orderedItems)
	//        {
	//    	   System.out.print("From CLient:"+o.getItemProduct().getName());
	//        }
	        OrderTransaction orderTransaction = new OrderTransaction();
			orderTransaction = main.getOrderTransaction();
			
	        //send to server 
	        objectOS.writeObject(orderedItems);
	        objectOS.flush();
	        objectOS.writeObject(orderTransaction);
	        objectOS.flush();
	
//	        
//	        //get order back from transaction server
	        
	       
			
	        Order order = new Order();
			order = (Order) objectIS.readObject();
			orderTransaction = (OrderTransaction) objectIS.readObject();
//	       
			
//	        // Read from network
//	         BufferedReader bufferedReader = new BufferedReader(
//	         new InputStreamReader(socket.getInputStream()));
//	
//	        // Display the current date
//	         String currentDate = bufferedReader.readLine();
//	        // main.updateServerDate(currentDate);
	        
	        //Order order = new Order();
	        
	     // print receipt
			Receipt receiptTemplate = new Receipt();
			String receipt = receiptTemplate.printReceipt(order, orderTransaction);
			WriteFile data = new WriteFile("Receipt_" + order.getOrderId()+"_"+orderTransaction.getTransactioDate() + ".txt", true);
			data.writeToFile(receipt);
			
	         //Close everything
	        // bufferedReader.close();
	    	objectOS.close();
			objectIS.close();
			dataOs.close();
			dataIs.close();		
	        socket.close();
         
    	}
    	

    }

}