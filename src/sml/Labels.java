package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * a class for storing labels and their associated addresses as key-value pairs.
 * labels can be added to the map with their associated addresses.
 * the address associated with a label can be retrieved from the map.
 * duplicate labels are not allowed.
 * the class provides methods to check for equality with other instances, and to reset the labels.
 */

/**
 * @author yusuf963
 */
public final class Labels {
    private final Map<String, Integer> labels = new HashMap<>();

    /**
     * Adds a label with the associated address to the map.
     * throw run time exception if label is already exists, otherwise it accepts the label
     *
     * @param label   the label
     * @param address the address the label refers to
     */
    public void addLabel(String label, int address) {
        Objects.requireNonNull(label);
        if (label.contains(label)) {
            throw new RuntimeException("the label can not be added, as its already exist!");
        } else {
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
        /**
         * we store an address incorrectly against a label, it can result in an error. More specifically,
         * this issue arises when we store an address for an index that exceeds the size of the program ArrayList of Instructions in the Machine class.
         *  If we attempt to jump to the index of this label,and it is outside the size of program, the program will throw a NullPointerException.
         */
        if (!labels.containsKey(label)) {
            throw new RuntimeException("Label of value " + label + "doesn't exist");
        } else {
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
        return labels.entrySet()
                .stream()
                .map(e -> e.getKey() + " -> " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Labels labels1 = (Labels) obj;
        return Objects.equals(labels, labels1.labels);
    }

    /**
     * The hash code is generated using the labels map.
     *
     * @return the hash label for the object
     */
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
