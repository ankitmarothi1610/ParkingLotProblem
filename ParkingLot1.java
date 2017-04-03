import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ParkingLot1 {
	int size;
	private List<Slot> slots;
	boolean initialized;
	Map <Integer, Vehicle> occupied_slots;
	HashSet<String> regs=new HashSet<String>();

	public ParkingLot1() {
		size = 0;
		initialized = false;
	}

	public void createParkingLot1(int size) {
		if(size < 1) {
			System.out.println("Invalid Parameter");
			return;
		}
		this.size = size;
		slots = new ArrayList<>(size);
		initialized = true;
		for (int i = 0; i < size; i++) {
			slots.add(new Slot(i + 1));
		}
		occupied_slots = new HashMap<>();
		System.out.println("Created a parking lot with " + size + " slots");
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
		if(v.car_colour == null || v.car_colour.equals("") || v.reg_no == null || v.reg_no == "") {
			System.out.println("Invalid Command");
			printHelp();
			return;
		}
		if (occupied_slots.size() == size) {
			System.out.println("Sorry, parking lot is full");
			return;
		}
		if (regs.contains(v.reg_no)) {
			System.out.println("Vehicle already present in parking lot");
			return;
		}
		Slot empty_slot = getFirstFreeSlot();
		empty_slot.park(v);
		occupied_slots.put(empty_slot.slot_no, v);
		regs.add(v.reg_no);
		System.out.println("Allocated slot number: " + empty_slot.slot_no);
	}

	public void leave(int slot_no) {
		if(slot_no < 1 || slot_no > size) {
			System.out.println("Invalid Parameter");
			printHelp();
			return;
		}
		if(occupied_slots.containsKey(slot_no)) {
			occupied_slots.remove(slot_no);
		}
		Iterator<Slot> l = slots.iterator();
		boolean found = false;
		while (l.hasNext()) {
			Slot s = l.next();
			if(s.slot_no == slot_no) {
				if(s.isOccupied()) {
					s.unPark();
					found = true;
					System.out.println("Slot number " + s.slot_no + " is free");
				} else {
					System.out.println("Slot number " + s.slot_no + "is already free");
				}
			}
		}
		if (found == false) {
			System.out.println("Invalid slot");
		}
	}

	public void registrationNumbersForCarsWithColour(String colour) {
		boolean first = true;
		boolean found = false;
		if(colour == null || colour.equals("")) {
			System.out.println("Invalid Parameter");
			printHelp();
			return;
		}
		for(Map.Entry<Integer, Vehicle> entry: occupied_slots.entrySet()) {
			int slot_no = entry.getKey();
			Vehicle v = entry.getValue();
			if (colour.equalsIgnoreCase(v.getCarColour())) {
				found = true;
				if (first) {
					System.out.print( 
							v.getRegistrationNumber()  
							);
					first = false;
				} else {
					System.out.print( 
							", " + v.getRegistrationNumber()  
							);
				}
			}
		}
		if (!found) 
			System.out.println("Not Found");
		System.out.println();
	}
	public void slotNumbersForCarsWithColour(String colour) {
		boolean first = true;
		boolean found = false;
		if(colour == null || colour.equals("")) {
			System.out.println("Invalid Parameter");
			printHelp();
			return;
		}
		for(Map.Entry<Integer, Vehicle> entry: occupied_slots.entrySet()) {
			int slot_no = entry.getKey();
			Vehicle v = entry.getValue();
			if (colour.equalsIgnoreCase(v.getCarColour())) {
				found = true;
				if (first) {
					System.out.print( 
							slot_no
							);
					first = false;
				} else {
					System.out.print( 
							", " + slot_no
							);
				}
			}
		}
		if (!found) 
			System.out.println("Not Found");
		System.out.println();
	}
	public void slotNumberForRegistrationNumber(String reg_no) {
		boolean first = true;
		boolean found = false;
		if(reg_no == null || reg_no.equals("")) {
			System.out.println("Invalid Parameter");
			printHelp();
			return;
		}
		for(Map.Entry<Integer, Vehicle> entry: occupied_slots.entrySet()) {
			int slot_no = entry.getKey();
			Vehicle v = entry.getValue();
			if (reg_no.equalsIgnoreCase(v.getRegistrationNumber())) {
				found = true;
				if (first) {
					System.out.print( 
							slot_no
							);
					first = false;
				} else {
					System.out.print(
							", " +
									slot_no
							);
				}
			}
		} if (!found) {
			System.out.println("Not found");
		}
		System.out.println();
	}
	public void status() {
		//print the occupied hashmap
		System.out.println("No\t Registration Slot No. \t Colour");
		for(Map.Entry<Integer, Vehicle> entry: occupied_slots.entrySet()) {
			int slot_no = entry.getKey();
			Vehicle v = entry.getValue();
			System.out.println(slot_no 
					+ "\t" 
					+ v.getRegistrationNumber()
					+ "\t"
					+ v.getCarColour()
					);
		}
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

	public void runCommand(String line) {
		StringTokenizer st = new StringTokenizer(line, " ");
		if (st.countTokens() < 1 || st.countTokens() > 3) {
			System.out.println("Output: \n Invalid Input");
		}
		while (st.hasMoreTokens()) {
			String command = ((st.hasMoreElements()) ? st.nextToken() : null);
			String input1 = ((st.hasMoreElements()) ? st.nextToken() : null);
			String input2 = ((st.hasMoreElements()) ? st.nextToken() : null);
			System.out.println("Output: ");
			switch(command) {
			case "park":
				if (initialized == false) {
					System.out.println("Parking Lot not initialized");
					break;
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
				createParkingLot1(size);
				break;
			case "leave":
				if (initialized == false) {
					System.out.println("Parking Lot not initialized");
					break;
				}
				int slot_no = -1;
				try {
					slot_no = Integer.parseInt(input1);
				} catch (NumberFormatException ne) {
					System.out.println("Invalid parameter");
					printHelp();
					continue;
				}
				leave(slot_no);
				break;
			case "registration_numbers_for_cars_with_colour":
				if (initialized == false) {
					System.out.println("Parking Lot not initialized");
					break;
				}
				registrationNumbersForCarsWithColour(input1);
				break;
			case "slot_number_for_registration_number":
				if (initialized == false) {
					System.out.println("Parking Lot not initialized");
					break;
				}
				slotNumberForRegistrationNumber(input1);
				break;
			case "slot_numbers_for_cars_with_colour":
				if (initialized == false) {
					System.out.println("Parking Lot not initialized");
					break;
				}
				slotNumbersForCarsWithColour(input1);
				break;
			case "status":
			case "Status":
				System.out.println("\rOutput: (Tab delimited output)");
				if (initialized == false) {
					System.out.println("Parking Lot not initialized");
					break;
				}
				status();
				break;
			case "exit":
				System.exit(0);
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
	}

	public static void main(String[] args) {
		ParkingLot1 p = new ParkingLot1();
		Scanner scanner = null;
		if (args.length > 0 && args[0] != null && args[0] != "") {
			File file = new File(args[0]);
			if (!file.exists()) {
				System.out.println("Invalid File Name" + args[0]);
				System.exit(0);
			}
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			}
			while (scanner.hasNext())
			{
				String line = scanner.nextLine();
				if(line != null && line != "" & line != "\r\n" && line != "\r" && line != "\n")
					p.runCommand(line);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			scanner.close();
		} else {
			scanner = new Scanner(System.in);

			do {
				System.out.println("Input: ");
				String line = scanner.nextLine();
				p.runCommand(line);
				System.out.println();
			} while(true);
		}
	}
}
