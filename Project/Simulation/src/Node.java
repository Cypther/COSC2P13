/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

public class Node<E> {
	
	private E data;
	private Node<E> next;

	public Node(E d, Node<E> n) {
		data=d; 
		next=n; 
		}
	
	public Node(E d){
		data = d;
		next = null; 
		}
	public Node(){
		data = null; 
		next = null; 
		}

	public E getData(){
		return data;
		}
	
	public Node<E> getNext() {
		return next;
		}

	public void setData(E d){
		data = d; 
		}
	
	public void setNext(Node<E> n){
		next = n; 
		}

	public String toString(){
		return "" + data;
	}
}