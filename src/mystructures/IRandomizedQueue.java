package mystructures;

public interface IRandomizedQueue<Item> {
	
	public boolean isEmpty();

	public int size();

	public void enqueue(Item item);

	public Item dequeue();

	public Item sample();

}
