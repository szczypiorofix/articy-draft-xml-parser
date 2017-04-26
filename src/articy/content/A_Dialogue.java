package articy.content;

import java.util.ArrayList;

public class A_Dialogue extends ArticyDraftObject {

	public String text;
	public String color;
	public ArrayList<String> references;

	
	public A_Dialogue() {
		references = new ArrayList<String>();
	}
}
