package articy.content;

import java.util.ArrayList;

public class A_Dialogue extends ArticyDraftObjectClass {

	public String text;
	public String color;
	public ArrayList<String> references;
	public ArrayList<String> pins;

	
	public A_Dialogue() {
		references = new ArrayList<String>();
		pins = new ArrayList<String>();
	}
}
