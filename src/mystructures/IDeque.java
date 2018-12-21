package mystructures;

public interface IDeque<Item> {
	
	public boolean isEmpty();

	public int size();

	public void addFirst(Item item);

	public void addLast(Item item);

	public Item removeFirst();

	public Item removeLast();

}
