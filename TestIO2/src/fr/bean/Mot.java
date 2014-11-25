package fr.bean;

public class Mot {

	private int id;
	
	private String terme;
	
	private char FirstLetter;
	
	private int size;
	
	private boolean endByver;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTerme() {
		return terme;
	}
	public void setTerme(String terme) {
		this.terme = terme;
	}
	public char getFirstLetter() {
		return FirstLetter;
	}
	public void setFirstLetter(char firstLetter) {
		FirstLetter = firstLetter;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	/**
	 * @return the endByver
	 */
	public boolean isEndByver() {
		return endByver;
	}
	/**
	 * @param endByver the endByver to set
	 */
	public void setEndByver(boolean endByver) {
		this.endByver = endByver;
	}
	@Override
	public String toString() {
		return "Mot [id=" + id + ", terme=" + terme + ", FirstLetter="
				+ FirstLetter + ", size=" + size + "]";
	}



}
