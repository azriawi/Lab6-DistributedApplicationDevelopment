package transactionserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import creditcardvalidation.CreditCard;
import creditcardvalidation.InvalidCreditCardException;

public class CreditCardAuthorizationServer {

	public static void main (String[] args) throws IOException {
	

		
		int PORT = 4647;
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT); 
		
		CreditCardAuthorizationServerFrame transactionserverframe = new CreditCardAuthorizationServerFrame();
		transactionserverframe.setVisible(true);
		
		while(true) {
			
		Socket socket = serverSocket.accept();// connect to orderServer 
		
		DataInputStream dataIs = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOs = new DataOutputStream(socket.getOutputStream());
	
			boolean status = false;
			
			
			//read from order server
			String creditcardnum = dataIs.readUTF();
			
			//display this message at gui credit card server
			transactionserverframe.addText("Read credit card number: \n"+"-->" +creditcardnum);
			System.out.println("Nak test dapat ka tak " +creditcardnum);
			
		
			// check the validity of the credit card from credit card validation class
			CreditCard creditCardValidation = new CreditCard();
			try {
				Long creditCard = Long.parseLong(creditcardnum);
				
				//transactionserverframe.addText("Checking the validity of the credit card");
				if(!(creditCardValidation.isValid(creditCard))) {
					status = false;
					throw new InvalidCreditCardException();
				}
				else {
					//kalau credit card tu valid, akan return status true, so send status back to order server
					status = true;
					dataOs.writeBoolean(status);
					System.out.println("Success");
				}
				System.out.println("Dapat " +status);
			}catch (InvalidCreditCardException e) {
				System.out.println(e.getMessage());
			}
			
			//send to orderserver
			// send the data
			// update to the server gui that we send it off
			    //transactionserverframe.addText("Sending the status back to requester");
		}
		
	}
}