import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ParkingLot {
	int size;
	private List<Slot> slots;
	boolean initialized = false;
	Map <Integer, Vehicle> occupied_slots;
	public ParkingLot() {
		size = 0;
	}
	
	public ParkingLot createParkingLot(int size) {
		ParkingLot p = new ParkingLot();
		p.size = size;
		p.slots = new ArrayList<>(size);
		p.initialized = true;
		for (int i = 0; i < size; i++) {
			p.slots.add(new Slot(i));
		}
		return p;
	}
	
	public Slot getFirstFreeSlot() {
		Iterator<Slot> l = slots.iterator();
		boolean found_empty_slot = false;
		Slot empty_slot = null;
		while(l.hasNext() && !found_empty_slot) {
			empty_slot = l.next();
			if (!empty_slot.isOccupied()) {
				found_empty_slot = true;
			}
		}
		return empty_slot;
	}
	public static void printHelp() {
		System.out.println("Below Commands are supported");
		
		System.out.println("create_parking_lot: Creates a parking Lot of a given size");
		System.out.println("Use: Creates a parking lot of a given size");
		System.out.println("Syntax: create_parking_lot <Colour>");
		System.out.println("Example");
		System.out.println("create_parking_lot 6");
		
		System.out.print("park");
		System.out.println("Use: Parks the vehicle in the nearest empty slot");
		System.out.println("Syntax: park <Registration Number> <Colour>");
		System.out.println("Example");
		System.out.println("park KA-01-P-333 White");
		
		System.out.println("leave");
		System.out.println("Use: Unparks the vehicle from a given slot");
		System.out.println("Syntax: leave <Slot Number>");
		System.out.println("Example");
		System.out.println("leave 4");
		
		System.out.println("registration_numbers_for_cars_with_colour");
		System.out.println("Use: Checks the registration numbers of car provided a colour");
		System.out.println("Syntax: registration_numbers_for_cars_with_colour <Colour>");
		System.out.println("Example");
		System.out.println("registration_numbers_for_cars_with_colour White");
		
		System.out.println("slot_numbers_for_cars_with_colour");
		System.out.println("Use: Gives the slot numbers of cars provided a colour");
		System.out.println("Syntax: slot_numbers_for_cars_with_colour <Colour>");
		System.out.println("Example");
		System.out.println("slot_numbers_for_cars_with_colour White");
		
		System.out.println("slot_number_for_registration_number");
		System.out.println("Use: Gives the slot number of the car parked with a given registration number");
		System.out.println("Syntax: slot_number_for_registration_number <registration number>");
		System.out.println("Example");
		System.out.println("slot_number_for_registration_number KA-01-HH-3141");
		
		
		System.out.println("Status");
		System.out.println("Syntax: Status");
		System.out.println("Example");
		System.out.println("Status");
	}
	
	
	public void park(Vehicle v) {
		Slot empty_slot = getFirstFreeSlot();
		empty_slot.park(v);
		occupied_slots.put(empty_slot.slot_no, v);
	}
	
	public void leave(int slot_no) {
		
		if(occupied_slots.containsKey(slot_no)) {
			occupied_slots.remove(slot_no);
		}
		Iterator<Slot> l = slots.iterator();
		while (l.hasNext()) {
			Slot s = l.next();
			if(s.slot_no == slot_no) {
				s.unPark();
			}
		}
	}
	
	public void registrationNumbersForCarsWithColour(String colour) {
		
	}
	public void slotNumbersForCarsWithColour(String colour) {
		
	}
	public void slotNumberForRegistrationNumber(String reg_no) {
		
	}
	public void status() {
		
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ParkingLot p = new ParkingLot();
		System.out.println("Input: ");
		String line = scanner.nextLine();
		StringTokenizer st = new StringTokenizer(line, "");
		if (st.countTokens() < 2 || st.countTokens() > 3) {
			System.out.println("Output: \n Invalid Input");
		}
		while (st.hasMoreTokens()) {
			String command = ((st.hasMoreElements()) ? st.nextToken() : null);
			String input1 = ((st.hasMoreElements()) ? st.nextToken() : null);
			String input2 = ((st.hasMoreElements()) ? st.nextToken() : null);
			switch(command) {
				case "park":
					if (p.initialized == false) {
						System.out.println("Parking Lot not initialized");
					}
					String reg_no = input1;
					String colour = input2;
					Vehicle v = new Vehicle(reg_no, colour);
					if(v.isValidRegistrationNumber(reg_no) 
						&& 
					   v.isValidColour(colour)) {
						park(v);
					} else {
						System.out.println("Invalid registration Number or Colour");
					}
					break;
				case "create_parking_lot":
					int size = Integer.parseInt(input1);
					p.createParkingLot(size);
					break;
				case "leave":
					if (p.initialized == false) {
						System.out.println("Parking Lot not initialized");
					}
					int slot_no = Integer.parseInt(input1);
					p.leave(slot_no);
					break;
				case "registration_numbers_for_cars_with_colour":
					if (p.initialized == false) {
						System.out.println("Parking Lot not initialized");
					}
					p.registrationNumbersForCarsWithColour(input1);
					break;
				case "slot_number_for_registration_number":
					if (p.initialized == false) {
						System.out.println("Parking Lot not initialized");
					}
					p.slotNumberForRegistrationNumber(input1);
					break;
				case "Status":
					if (p.initialized == false) {
						System.out.println("Parking Lot not initialized");
					}
					break;
				case "printhelp":
					printHelp();
					break;
				default: 
					System.out.println("Invalid Input");
					printHelp();
					break;
			}
		}
		System.out.println("The name of the user is :" + line);
	}
}
