//MUHAMMAD AZRI BIN ABDULLAH ZAWAWI
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import ftmk.rmi.sensor.TemperatureSensor;
import ftmk.rmi.sensor.manager.TemperatureSensorManager;
public class TemperatureServerRMIApp {

	public static void main(String[] args) {
		
		try {
			//syntax utk create interface object
			TemperatureSensor sensorJasin = new TemperatureSensorManager();
			
			//get registry
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			//register interface object as remote object
			rmiRegistry.rebind("SensorJasin", sensorJasin);
			
			System.out.println("Sensor Jasin Is Succesfully Registered!!");
			
			//syntax utk create interface object
			TemperatureSensor sensorAyerKeroh = new TemperatureSensorManager();
			
			//register interface sensorAyerKeroh object as remote object
			rmiRegistry.rebind("SensorAyerKeroh", sensorAyerKeroh);
			
			System.out.println("Sensor Ayer Keroh Is Succesfully Registered!!");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
