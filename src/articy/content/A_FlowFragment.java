package articy.content;

import java.util.ArrayList;

public class A_FlowFragment {

	public String id;
	public String displayName;
	public String text;
	public String color;
	public ArrayList<String> references;
	public ArrayList<String> pins;
	public ArrayList<String> sources;
	public ArrayList<String> targets;
	
	public A_FlowFragment() {
		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
	}
}
