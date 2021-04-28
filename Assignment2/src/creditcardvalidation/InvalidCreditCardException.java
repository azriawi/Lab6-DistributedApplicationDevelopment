package creditcardvalidation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InvalidCreditCardException extends Exception {


	private static final long serialVersionUID = 1L;

	public InvalidCreditCardException() {
		super("Invalid credit card.");
		
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    frame.setAlwaysOnTop(true);
	    frame.setSize(300, 300);
	    frame.setLocation(300, 300);
	    
	    //show error dialog
		JOptionPane.showMessageDialog(frame, "The transaction is invalid...\n Please order again", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
	
	}
	
	public InvalidCreditCardException(String message) {
		super(message);
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    frame.setAlwaysOnTop(true);
	    frame.setSize(300, 300);
	    frame.setLocation(300, 300);
		JOptionPane.showMessageDialog(frame, message, "ERROR!!!", JOptionPane.ERROR_MESSAGE);
		
	}
}