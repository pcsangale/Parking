package ParkingCode;

import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;
import java.util.Queue;


public class Parking {

	final int capacityOfParking=20;
	
	
	
	private Map<CarToken, Car> car= new HashMap<CarToken, Car>();
	
	public Parking(){
		
	}
	
	
	public boolean isParkingAvailable(){
		
	if(car.size()<capacityOfParking)
	{
		
		return true;
	
	}
		else{
			
			return false;
		}
	}
	
	public CarToken parked(String carNumber)throws ParkingException{
		
		CarToken carToken= new CarToken();
		if(isParkingAvailable() && !car.containsValue(new  Car(carNumber))){
			car.put(carToken, new Car(carNumber));
			
			return carToken;
		}
		else
		{
			throw new ParkingException("Can not park the car");
		}
	}
	public Car unparked(CarToken carToken) throws ParkingException{
		if(car.containsKey(carToken)){
			return car.remove(carToken);
			
			
		}
		throw new ParkingException("Can not unpark from the parking");
	}
	
	
}
