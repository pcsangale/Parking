package Testing;

import org.junit.Assert;
import org.junit.Test;

import ParkingCode.Parking;


public class CarParkingTest {

	
	
	@Test
	public void souldPark(){
		Parking manager=new Parking();
		Assert.assertEquals(true, manager.parked("MH-12-1234"));
	}
	
	@Test
	public void shouldUnpark(){
		Parking manager=new Parking();
		manager.parked("MH-12-1234");
		Assert.assertEquals(true, manager.unparked("MH-12-1234"));
	}
	
	@Test
	public void souldIsParkingDuplicateCar(){
		Parking manager=new Parking();
		
		Assert.assertEquals(true, manager.parked("MH-12-1234"));
	}
	@Test
	public void souldParkUnparkedCar(){
		Parking manager=new Parking();
		manager.parked("MH-12-1234");
		manager.unparked("MH-12-1234");
		Assert.assertEquals(true,manager.parked("MH-12-1234"));
	}
	
	
}
