//MUHAMMAD AZRI BIN ABDULLAH ZAWAWI
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ftmk.rmi.sensor.TemperatureSensor;

public class TemperatureAyerKerohClientRMIApp {

	public static void main(String[] args) {
		
		
		try {
			
			//syntax to get registry
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			//lookup for the remote object
			TemperatureSensor remoteSensorAyerKeroh = (TemperatureSensor) rmiRegistry.lookup("SensorAyerKeroh");
			
			// Invoke method from the remote object
			int currentTemperature = remoteSensorAyerKeroh.getTemperature();
						
			System.out.println("Current temperature in Ayer Keroh: " + currentTemperature + " Celcius");
			
			//invoke method from the remote object to display the temperature based on specific day
			String day = "Thursday";
			int AyerKerohtemperature  = remoteSensorAyerKeroh.getTemperatureBasedOnDay(day);
			
			//display
			System.out.println("Current temperature in Ayer Keroh, " +"Days: " +day +", " +AyerKerohtemperature  +" Celcius");
			
			// display average temperature
			float averageTemperature = remoteSensorAyerKeroh.getAvgTemperature();
			System.out.println("Average temperature in Ayer Keroh: " +String.format("%.1f", averageTemperature) +" Celcius");
			

		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}