//MUHAMMAD AZRI BIN ABDULLAH ZAWAWI
package ftmk.rmi.sensor.manager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import ftmk.rmi.sensor.TemperatureSensor;
import java.util.HashMap;

public class TemperatureSensorManager extends UnicastRemoteObject implements TemperatureSensor {

	public TemperatureSensorManager() throws RemoteException {
		super();
		
	}

	@Override
	public int getTemperature() throws RemoteException {
		
		return 35;
	}
	
	private HashMap<String, Integer> loadData()
	{
		
		//create a hashmap
		//refer based on --> https://www.programiz.com/java-programming/hashmap
		HashMap <String, Integer> temperature = new HashMap<>();
		
		//add elements to hashmap
		temperature.put("Monday", 32);
		temperature.put("Tuesday", 31);
		temperature.put("Wednesday", 33);
		temperature.put("Thursday", 35);
		temperature.put("Friday", 36);
		temperature.put("Saturday", 33);
		temperature.put("Sunday", 33);
		
		return temperature;
	}
	
	@Override
	public int getTemperatureBasedOnDay(String day) throws RemoteException {
		
		HashMap <String, Integer> suhu = loadData();
		
		int temperature = suhu.get(day);
		
		return temperature;
	}

	@Override
	public float getAvgTemperature() throws RemoteException 
	{
		HashMap <String, Integer> suhu = loadData();
		
		float avgTemperature;
		
		int totalTemperature = 0;
		for(int temperature: suhu.values())
		{
			totalTemperature = totalTemperature + temperature; 
		}
		
		//calculate average temperature for ayer keroh
		avgTemperature = (float)totalTemperature/suhu.size();
		
		return avgTemperature;
		
	}

}
