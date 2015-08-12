package ParkingCode;

import java.util.HashMap;

import java.util.Map;



public class Parking {

	final int capacityOfParking=1;
	
	
	ParkingLotOwner owner;
	private Map<CarToken, Car> car= new HashMap<CarToken, Car>();
	
	public Parking(ParkingLotOwner owner){
		this.owner=owner;
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
	
	public void notifyOwner(){
		if(car.size()==capacityOfParking)
		owner.parkingIsFull();
		
	}
	
	
	
	
	public CarToken parked(String carNumber)throws ParkingException{
		
		CarToken carToken= new CarToken();
		if(isParkingAvailable() && !car.containsValue(new  Car(carNumber))){
			car.put(carToken, new Car(carNumber));
			notifyOwner();
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
