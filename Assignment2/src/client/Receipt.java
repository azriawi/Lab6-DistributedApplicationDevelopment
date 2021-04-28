package client;

import kioskapp.ordereditem.OrderedItem;
import kioskapp.order.Order;
import kioskapp.ordertransaction.OrderTransaction;

public class Receipt {

	public String printReceipt(Order order, OrderTransaction orderTransaction) {
		String transStatus = "Failed";
		if(orderTransaction.isTransactionStatus()) {
			transStatus = "Succeed";
		}
		
		String receipt = "-------------------------------------------\n"
						+"		        Mekdi Kulim                  \n"
						+" 			   TEL# 04-4646229				 \n"			
						+"-------------------------------------------\n"
						+"			   Sales Receipt		     	 \n"		
						+"-------------------------------------------\n"
						+"Quantity Item                Price         \n";
						
		for (OrderedItem orderedItem : order.getOrderedItems()){
			
			receipt += orderedItem.getQuantity()+ "        " + orderedItem.getItemProduct().getName() + "    "
			+ String.format("%4.2f", orderedItem.getSubTotalAmount()) + "\n";
			
		}
		
			receipt += "-------------------------------------------\n"
					   +"Order Mode: " + orderTransaction.getOrderMode() + "\n"
					   +"Total Price: RM" + order.getTotalAmount() + "\n"
					   +"-------------------------------------------\n"
					   +"Paid With: **** **** **** " + orderTransaction.getLast4Digits() + "\n"
					   +"-------------------------------------------\n"
					   +"Transaction Status: " + transStatus + "\n"
					   +"-------------------------------------------\n"
					   +"-------------------------------------------\n"
					   +"Transaction ID: " + orderTransaction.getOrderTransactionId() +"\n"
					   +"Date: " + orderTransaction.getTransactioDate() +"\n"
			 		   +"-------------------------------------------\n"
			 		   +"         THANK YOU! Please Come Again      \n"
			 		   +"	  Rate your experience with us today    \n"
			 		   +"		  Visit: www.mekdi.com.my   	    \n"
					   +"-------------------------------------------\n"	;
			 		   
			return receipt;
	}


	
}
