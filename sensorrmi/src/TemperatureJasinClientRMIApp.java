//MUHAMMAD AZRI BIN ABDULLAH ZAWAWI
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ftmk.rmi.sensor.TemperatureSensor;

public class TemperatureJasinClientRMIApp {

	public static void main(String[] args) {
		
		
		try {
			
			//syntax to get registry
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			//lookup for the remote object
			TemperatureSensor remoteSensorJasin = (TemperatureSensor) rmiRegistry.lookup("SensorJasin");
			
			// Invoke method from the remote object
			int currentTemperature = remoteSensorJasin.getTemperature();
						
			System.out.println("Current temperature in Jasin: " + currentTemperature + " Celcius");
			

		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
