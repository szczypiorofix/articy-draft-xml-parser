package articy.content;

import java.util.ArrayList;
import java.util.List;

public abstract class ArticyDraftObject {

	public String id;
	public String displayName;
	public List<String>pinsInput = new ArrayList<String>();
	public List<String>pinsOutput = new ArrayList<String>();
}
