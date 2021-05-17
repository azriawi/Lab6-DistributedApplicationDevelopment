//MUHAMMAD AZRI BIN ABDULLAH ZAWAWI
package ftmk.rmi.sensor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TemperatureSensor extends Remote {
	
	//method ni utk get current temperature
	//pastu akan return temperature
	public int getTemperature() throws RemoteException;

	//method to accept temperature based on specific day
	//return current temperature based on that day
	public int getTemperatureBasedOnDay(String day) throws RemoteException;
	
	//calculate average temperature for ayer keroh
	public float getAvgTemperature() throws RemoteException;
}
