package Testing;

import java.security.acl.Owner;
import java.sql.Time;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.mockito.InOrder;


import ParkingCode.Car;
import ParkingCode.CarToken;
import ParkingCode.FBIAgent;
import ParkingCode.Parking;
import ParkingCode.ParkingException;
import ParkingCode.ParkingLotOwner;
import ParkingCode.SignBoard;
import ParkingCode.Subscriber;

import org.mockito.internal.verification.api.*;

public class CarParkingTest {

	
	
	@Test
	public void souldPark(){
		ParkingLotOwner owner= new ParkingLotOwner();
		Parking manager=new Parking(owner);
		try{
		Assert.assertEquals(true, manager.parked("MH-12-1234") instanceof CarToken);
		}catch (ParkingException e) {
			// TODO: handle exception
		}
		}
	
	@Test
	public void isParkingFull() throws Exception{
		ParkingLotOwner owner= mock(ParkingLotOwner.class);
		Parking manager=new Parking(owner);
		
		manager.parked("12aasf");
		manager.parked("12a");
		verify(owner).parkingIsFull();
		
	}
	
	@Test
	public void shouldUnpark(){
		ParkingLotOwner owner= new ParkingLotOwner();
		Parking manager=new Parking(owner);
		
		try{
			CarToken carToken=manager.parked("MH-12-1234");
			Assert.assertEquals(true, manager.unparked(carToken) instanceof Car);
		}
		catch (ParkingException e) {
			
		}
		}
	
	@Test
	public void souldIsParkingDuplicateCar(){
		ParkingLotOwner owner= new ParkingLotOwner();
		Parking manager=new Parking(owner);
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
		ParkingLotOwner owner= new ParkingLotOwner();
		Parking manager=new Parking(owner);
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
	public void shouldNotifyOwner(){
		ParkingLotOwner owner= mock(ParkingLotOwner.class);
		Parking manager=new Parking(owner);
		try{
			manager.parked("12-PM-2312");
		manager.parked("12aasf");
		manager.parked("MH-15-2012");
		}
		catch(ParkingException p){
			System.out.println(p.getMessage());
		}
		finally{
		verify(owner).parkingIsFull();
		}
	}
	@Test
	public void shouldNotifyFBIAgent() throws ParkingException{
		ParkingLotOwner owner= mock(ParkingLotOwner.class);
		
		Subscriber fbiAgent = mock(FBIAgent.class);
		
		Parking manager=new Parking(owner);
		manager.addParkingSubscriber(fbiAgent);
		
			manager.parked("12-PM-2312");
			manager.parked("MH-15-2012");
		verify(fbiAgent,times(1)).getParkingFullNotification();
			
		
	}
	
	
	@Test
	public void shouldNotNotifyFBIAgentParkingIsVacant() throws ParkingException{
		ParkingLotOwner owner= mock(ParkingLotOwner.class);
		
		FBIAgent fbiAgent = mock(FBIAgent.class);
		
		Parking manager=new Parking(owner);
		manager.addParkingSubscriber(fbiAgent);
		
			manager.parked("12-PM-2312");
			CarToken token=manager.parked("MH-14");
			manager.unparked(token);
		
				
		verify(fbiAgent,times(1)).getParkingEmptyNotification();
	}
	
	@Test
	public void shouldNotifyEightyPercentFull() throws ParkingException{
		ParkingLotOwner owner= mock(ParkingLotOwner.class);
		
		FBIAgent fbiAgent = mock(FBIAgent.class);
		
		Parking manager=new Parking(owner);
		manager.addParkingSubscriber(fbiAgent);
		
			manager.parked("12-PM-2312");
			manager.parked("MH-14");
			manager.parked("12-AM-2312");
			manager.parked("MH-54");
			
		
		verify(fbiAgent,times(1)).getParkingEightyPercentFullNotification();
	}
	
	@Test
	public void shouldNotifyOwnerOnceSpaceAvailable()throws ParkingException{
		ParkingLotOwner owner= mock(ParkingLotOwner.class);
		Parking manager=new Parking(owner);
		manager.parked("12 MG 1242");
		CarToken token=manager.parked("12-aasf");
		manager.unparked(token);
		verify(owner).parkingIsNotFull();
	}
	@Test
	public void shouldNotNotifyOwner(){
		ParkingLotOwner owner= mock(ParkingLotOwner.class);
		Parking manager=new Parking(owner);
				
		verify(owner,times(0)).parkingIsFull();
	}
	
	@Test
	public void shouldNotUnparkUnparkedCar(){
		ParkingLotOwner owner= new ParkingLotOwner();
		Parking manager=new Parking(owner);
		try{
			CarToken carToken = new CarToken();
			
			Assert.assertEquals(false, manager.unparked(carToken) instanceof Car);
			}
			catch (ParkingException e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	
}
