package orderserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;
import manager.ItemProductManager;
import manager.OrderManager;
import manager.OrderTransactionManager;
import manager.OrderedItemManager;
import kioskapp.order.Order;

/**
 * This class launch the server side application using TCP.
 * The server generates current date.
 * Each connected client will received current date from the server.
 */

public class OrderServerApplication {

	/**
	 * Main entry point to the server side application
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static void main( String [] args) throws IOException, ClassNotFoundException, SQLException {
		
		// Launch the server frame
		OrderServerFrame serverFrame = new OrderServerFrame();
		serverFrame.setVisible(true);
		
		// Binding to a port or any other port no you are fancy of
		int portNo = 4646;
		ServerSocket serverSocket = new ServerSocket(portNo);
		DateGenerator dateGenerator = new DateGenerator();
		

		
		// Counter to keep track the number of requested connection
		int totalRequest = 0;
		
		Socket clientSocket = serverSocket.accept();
		Socket kitchenSocket = serverSocket.accept();
		int orderReferenceNumber = 1;
		
		// Server needs to be alive forever
		while (true) 
		{
			
			
		
			
			// Message to indicate server is alive
			serverFrame.updateServerStatus(false);
			
			// create objectinput and datainput stream fot client 1 which is customer (kiosk)
			DataInputStream dataIs = new DataInputStream(clientSocket.getInputStream());
			ObjectInputStream objectIS = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream objectOs = new ObjectOutputStream(clientSocket.getOutputStream());
			
			
			//read creditcard from client(kiosk)
			String creditCardNum = dataIs.readUTF();
			
			System.out.println("Read from client credit card number: " +creditCardNum);
			serverFrame.addText("Sending Credit Card to Authorization Server: \n"+"-->" +creditCardNum);
			
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			
			//buat port baru untuk connect with credit card authorization server
			int PORT = 4647;
			String ADDRESS = "localhost";
			Socket socket = new Socket(ADDRESS, PORT);
			
			DataInputStream DataIsCredit = new DataInputStream(socket.getInputStream());
			DataOutputStream DataOsCredit = new DataOutputStream(socket.getOutputStream());
			DataOsCredit.writeUTF(creditCardNum);
			
			//read back from credit card status from authorization server 
			boolean receivestatus = DataIsCredit.readBoolean();
			System.out.println("Dapat ke tak From Authorization Server: " +receivestatus);
		
			//close socket untuk server credit card
			socket.close();
			
			//read from kiosk
			ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
			orderedItems = (ArrayList<OrderedItem>) objectIS.readObject();
			
			System.out.println(orderedItems.size());
			
			for(OrderedItem os: orderedItems) 
			{
				System.out.println("Read from client order send to server: " +os.getItemProduct().getName());
				System.out.println("Read from client order send to server: " +os.getQuantity());
				System.out.println("Read from client order send to server: " +os.getSubTotalAmount());
				
				
			}
			
			OrderTransaction orderTransaction = new OrderTransaction();
			orderTransaction = (OrderTransaction) objectIS.readObject();
			orderTransaction.getOrderMode();
			System.out.println("Dapat tak dri client mode order?:" +orderTransaction.getOrderMode());
			
			DataOutputStream dataOsKitchen = new DataOutputStream(kitchenSocket.getOutputStream());
			ObjectOutputStream objectOsKitchen = new ObjectOutputStream(kitchenSocket.getOutputStream());
			
	
			// Generate current date
			String currentDate = dateGenerator.getCurrentDate();
			
			// Create stream to write data on the network
			
			// Send current date back to the client
			//outputStream.writeBytes(currentDate);
			
			//ready nk push semua benda alah ni ke database
			Order order = new Order();
			order.setOrderedItems(orderedItems);
			order.setTotalAmount(orderTransaction.getAmountCharged());
			order.setOrderReferenceNumber(orderReferenceNumber++);
			
			ItemProductManager itemProductManager = new ItemProductManager();
			OrderedItemManager  orderItemManager = new OrderedItemManager();
			OrderTransactionManager orderTransactionManager = new OrderTransactionManager();
			OrderManager orderManager = new OrderManager();
		
			orderTransaction.setOrder(order);
			orderTransaction.setTransactionStatus(receivestatus);
			order.setOrderId(orderManager.getOrderID());
			orderManager.insertOrder(order);
			
			//naktest dapat baca kt console ka tak
			for(OrderedItem O: order.getOrderedItems())
			{
				System.out.println(O.getItemProduct().getName());
				System.out.println(O.getItemProduct().getItemProduct());
			}
			
			orderItemManager.insertOrderedItem(order);
			orderTransactionManager.insertTransaction(orderTransaction);
			
			
			//nak send ke kiosk pulak
			objectOs.writeObject(order);
			objectOs.flush();
			objectOs.writeObject(orderTransaction);
			
			//nak send ke kitchen pulak
			objectOsKitchen.writeObject(order);
			objectOsKitchen.flush();
			objectOsKitchen.writeObject(orderTransaction);
			
			
			
			
			
			// Close the socket
			//clientSocket.close();
			
			
			
			// Update the request status
			serverFrame.updateRequestStatus(
					"Data sent to the client: " + currentDate);
			serverFrame.updateRequestStatus("Accepted connection to from the "
					+ "client. Total request = " + ++totalRequest );
			
		}
		
		

	}

}