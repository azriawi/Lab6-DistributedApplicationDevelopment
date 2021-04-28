package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.DapuqUI;
import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;

public class TCPKitchenClientApplication
{
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
	{
		
		DapuqUI dapur = new DapuqUI();
		dapur.setVisible(true);	
	
		
		while(true){
			
			Socket socket = new Socket(InetAddress.getLocalHost(), 4646);
			socket.setKeepAlive(true);
			
			//create inputstream
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			
			ObjectInputStream objectIs = new ObjectInputStream(socket.getInputStream());// for object to receive
			
			// if kiosk not click the cancel order
			
				//create object
				Order order = new Order();
				
				//read object dari order server
				order = (Order) objectIs.readObject();
				
				//condition kalau payment tu valid
				if(order != null) {
					
					OrderTransaction orderTransaction = (OrderTransaction) objectIs.readObject();
					String ordermode = orderTransaction.getOrderMode();
					
					//convert the string text to a suitable format for the gui.
					
					String orderList = "Order referrence number: "+order.getOrderReferenceNumber()+"\n";
					dapur.displayOrderMode(ordermode);
					for (OrderedItem O: order.getOrderedItems())
					{
						orderList += O.getItemProduct().getName()+": " +O.getQuantity() +"\n";
						System.out.println(O.getItemProduct().getName());
					}
					
					dapur.displayInfo(orderList);
				
				}
			
		
			socket.close();
	
		}
	}

}
