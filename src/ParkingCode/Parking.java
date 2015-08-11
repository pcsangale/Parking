package ParkingCode;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import org.w3c.dom.ls.LSInput;

public class Parking {

	final int capacityOfParking=20;
	
	private List <Car> car= new ArrayList<Car>();
	
	
	
	public boolean isParkingAvailable(){
		
	if(car.size()<capacityOfParking)
	
		return true;
	else
		return false;
	}
	
	public boolean parked(String carNumber){
		
		if(isParkingAvailable() && !car.contains(new Car(carNumber))){
			
			car.add(new Car(carNumber));
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean unparked(String carNumber){
		if(car.contains(new Car(carNumber))){
			car.remove(new Car(carNumber));
			return true;
		}
		return false;
	}
	
	
}
