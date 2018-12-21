package datastructures;


public interface IQueue<Item> {

	public boolean isEmpty();

	public int size();

	public void enqueue(Item item);

	public Item dequeue();

}
