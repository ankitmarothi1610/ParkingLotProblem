
public class Slot {
	
	int slot_no;
	Vehicle v;
	boolean is_occupied;

	public Slot(int i) {
		this.slot_no = i;
	}
	public int getSlotNumber() {
		return this.slot_no;
	}
	
	public boolean isOccupied() {
		return this.is_occupied;
	}
	
	public void park(Vehicle v) {
		is_occupied = true;
		this.v = v;
	}
	public void unPark() {
		if (isOccupied()) {
			is_occupied = false;
		}
	}
}
