package adt;
/**
 *
 * @author Min
 * 
 */
public interface SecurityListInterface<T extends Comparable<T>> {
    
    public boolean add(T newEntry);
    public T getEntry(int givenPosition);
    public T remove(int givenPosition);
    public void clear();
    public int getLength();
    public boolean isEmpty();
    public boolean isFullEquipment(int limit);
}