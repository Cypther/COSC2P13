/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */


public class Queue<E> {
	
	
	private Node<E> first;
	private Node<E> last;
	E newOne;
	private int count;
	
	
	public Queue () {
	   last = new Node<E>();
	   first = new Node<E>(null,last);
	   count=0;
	}

	public String toString() {
		String ret = "";
		Node<E> r = first.getNext();
	    for (; r!=last; r=r.getNext())
		    ret += r.getData() + " ";
		return "first " + ret + "last";
	}

	public E dequeue() {
		E ret;
		if (count==0){
			return null;
		}
		ret = first.getNext().getData();
		first = first.getNext();
		count--;
		return ret;
	}

	public void enqueue(E newData) {
		last.setData(newData);
		last.setNext(new Node<E>());
		last = last.getNext();
		count++;
	}
	public boolean empty(){
		
		if (count == 0){
			return true;
		}else{
			return false;
		}
		}

	public int length(){
		return count; 
		}
	
	/**
	 * Method for looking for data on top of the  stack
	 * checking if the stack is not empty
	 * */
	public E peek(){
		E ret;
		if(!this.empty()){ //if the front is not empty!
			ret = first.getNext().getData();
		return  ret;
		}else{
			return null; 
		}
	}
	
	public void clear() {
		while(!empty()){
		first = first.getNext();
		count--;
		}

	}

}
