package ParkingCode;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import java.util.Map;



public class Parking {

	final double capacityOfParking=5;
	
	
	ParkingLotOwner owner;
	private Map<CarToken, Car> car= new HashMap<CarToken, Car>();
	
	private List Subscribers = new ArrayList();
	
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
		double percentage= (car.size()/capacityOfParking)*100;
		System.out.println(percentage);
		if(car.size()==capacityOfParking)
		{
		owner.parkingIsFull();
		notifyAllSubscriber();
		
		
		}
		else if(percentage==80.00){
			System.out.println();
			notifyEightyPercentFullSubscriber();
		}
	}
	public void notifyOwnerNotFull(){
		if(car.size()==capacityOfParking-1)
		{
			owner.parkingIsNotFull();
			notifyAllVacantParkingSubscriber();
		}
		
	}
	
	public void addParkingSubscriber(Subscriber member){
		Subscribers.add(member);
	}
	
	
	
	
	public void notifyAllSubscriber(){
		ListIterator<Subscriber> it = Subscribers.listIterator();
		while(it.hasNext()){
			it.next().getParkingFullNotification();
			
		}
	}
	
	public void notifyEightyPercentFullSubscriber(){
		ListIterator<Subscriber> it = Subscribers.listIterator();
		while(it.hasNext()){
			it.next().getParkingEightyPercentFullNotification();
		}
	
	}
	
	public void notifyAllVacantParkingSubscriber(){
		ListIterator<Subscriber> it = Subscribers.listIterator();
		while(it.hasNext()){
			it.next().getParkingEmptyNotification();
		}
	
	
	
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
			Car carGet=car.remove(carToken);
				notifyOwnerNotFull();
				return carGet;	
			
		}
		throw new ParkingException("Can not unpark from the parking");
	}
	
	
}
