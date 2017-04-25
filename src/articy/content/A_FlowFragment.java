package articy.content;

import java.util.ArrayList;

public class A_FlowFragment extends ArticyDraftObjectClass {

	public String text;
	public String color;
	public ArrayList<String> references;
	public ArrayList<String> sources;
	public ArrayList<String> targets;
	
	public A_FlowFragment() {
		sources = new ArrayList<String>();
		targets = new ArrayList<String>();
		references = new ArrayList<String>();
	}
}
