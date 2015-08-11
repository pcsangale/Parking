package ParkingCode;

public class Car {
	
	private String carNumber;
	
	
	
	public Car(String carNumber){
		this.carNumber=carNumber;
	}



	public String getCarNumber() {
		return carNumber;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carNumber == null) ? 0 : carNumber.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carNumber == null) {
			if (other.carNumber != null)
				return false;
		} else if (!carNumber.equals(other.carNumber))
			return false;
		return true;
	}
	
}
