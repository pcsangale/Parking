package Testing;

import org.junit.Assert;
import org.junit.Test;

import ParkingCode.Car;
import ParkingCode.CarToken;
import ParkingCode.Parking;
import ParkingCode.ParkingException;


public class CarParkingTest {

	
	
	@Test
	public void souldPark(){
		Parking manager=new Parking();
		try{
		Assert.assertEquals(true, manager.parked("MH-12-1234") instanceof CarToken);
		}catch (ParkingException e) {
			// TODO: handle exception
		}
		}
	
	@Test
	public void shouldUnpark(){
		Parking manager=new Parking();
		
		try{
			CarToken carToken=manager.parked("MH-12-1234");
			Assert.assertEquals(true, manager.unparked(carToken) instanceof Car);
		}
		catch (ParkingException e) {
			
		}
		}
	
	@Test
	public void souldIsParkingDuplicateCar(){
		Parking manager=new Parking();
		try{
		manager.parked("MH-12-1234");
		Assert.assertEquals(false, manager.parked("MH-12-1234") instanceof CarToken);
		}
		catch (ParkingException e) {
			System.out.println(e.getMessage());
		}
		}
	@Test
	public void souldParkUnparkedCar(){
		Parking manager=new Parking();
		try{
		CarToken carToken=manager.parked("MH-12-1234");
		manager.unparked(carToken);
		Assert.assertEquals(true, manager.parked("MH-12-1234") instanceof CarToken);
		}
		catch (ParkingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test
	public void shouldNotUnparkUnparkedCar(){
		Parking manager=new Parking();
		try{
			CarToken carToken = new CarToken();
			
			Assert.assertEquals(false, manager.unparked(carToken) instanceof Car);
			}
			catch (ParkingException e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	
}
