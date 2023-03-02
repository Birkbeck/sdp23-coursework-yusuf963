package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 *
 * @author ...
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
		if(label.containsKey(label)){
			throw new RuntimeException("the lable can not be added, as its already exist!")
		}else{
			labels.put(label, address);
		}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
	/**
	 * we store an address incorrectly against a label, it can result in an error. More specifically,
	 * this issue arises when we store an address for an index that exceeds the size of the program ArrayList of Instructions in the Machine class.
	 *  If we attempt to jump to the index of this label and it is outside the size of program, the program will throw a NullPointerException.
	*/
		if(!labels.containsKey(lable)){
			throw new RuntimeException("Lable of value "+ label+ "doesn't exist")
		}else{
			return labels.get(label);
		}
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		return label.enterySet().stream().map(
			e -> e.getKey() + "" + e.getValue()
		).collect(
			collectors.joining(", ", "[", "]")
		)
	}

	// TODO: Implement equals and hashCode (needed in class Machine).
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Labels labels1 = (Labels) obj;
		return Objects.equals(labels, labels1.labels);
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels);
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
