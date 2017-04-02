
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
		boolean isValid = false;
		return isValid;
	}
	public boolean isValidColour(String colour) {
		boolean isValid = false;
		return isValid;
	}
}
