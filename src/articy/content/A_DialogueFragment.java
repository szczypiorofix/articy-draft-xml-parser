package articy.content;

import java.util.ArrayList;

public class A_DialogueFragment extends ArticyDraftObjectClass {
	
	public String text;
	public String color;
	public String speakerId;
	public String menuText;
	public ArrayList<String> pins;
	
	public A_DialogueFragment() {
		pins = new ArrayList<String>();
	}
}
