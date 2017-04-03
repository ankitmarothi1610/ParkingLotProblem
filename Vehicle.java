
public class Vehicle {
	String reg_no;
	String car_colour;
	
	public Vehicle(String reg_no, String colour) {
		this.reg_no = reg_no;
		this.car_colour = colour;
	}
	
	public String getCarColour() {
		return this.car_colour;
	}
	public void setCarColour(String colour) {
		this.car_colour = colour;
	}
	public String getRegistrationNumber() {
		return this.reg_no;
	}
	public void setRegistrationNumber(String reg_no) {
		this.reg_no = reg_no;
	}
	
	public boolean isValidRegistrationNumber(String reg_no) {
		// Logic to check the valid registration number goes here
		// Logic not added since, the details about registration number is not given
		boolean isValid = true;
		return isValid;
	}
	public boolean isValidColour(String colour) {
		// Logic to check the valid colour goes here
		// Logic not added since, the details about car colour is not given
		boolean isValid = true;
		return isValid;
	}
	
	public void printVehicle() {
		System.out.println(car_colour + "\t" + reg_no);
	}
}
