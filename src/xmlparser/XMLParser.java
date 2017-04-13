package xmlparser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import articy.content.A_Entity;




public class XMLParser {

	public XMLParser() throws Exception {
		
		
		List<A_Entity> entityList = new ArrayList<A_Entity>();
		
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();

	    Document document = builder.parse(ClassLoader.getSystemResourceAsStream("SimpleProject.xml"));

	    NodeList nodeList = document.getDocumentElement().getChildNodes();

	    for (int i = 0; i < nodeList.getLength(); i++) {

	        Node node = nodeList.item(i);
	        if (node instanceof Element) {
	          //Employee emp = new Employee();
	          //emp.id = node.getAttributes().getNamedItem("id").getNodeValue();
	          //System.out.print("NODE: " +node.getNodeName() +" : ");
	        	
	        	NodeList childNodes = node.getChildNodes();
	          
	        	
	        	for (int j = 0; j < childNodes.getLength(); j++) {
	        		Node cNode = childNodes.item(j);

	        		//Identifying the child tag of employee encountered. 
	        		if (cNode instanceof Element) {
	        			//System.out.print(cNode.getNodeName() +", ");
	        			
	        			if (cNode.getNodeName().equals("Entity")) {
	        				//System.out.println(cNode.getNodeName() +" id: "+cNode.getAttributes().getNamedItem("Id").getNodeValue());
	        				
	        				A_Entity entity = new A_Entity();
	        				NodeList entityChildNodes = cNode.getChildNodes();
	        				
	        				for (int a = 0; a < cNode.getChildNodes().getLength(); a++) {
	        					Node eNode = cNode.getChildNodes().item(a);
	        					if (eNode instanceof Element) {
	        						//System.out.println(entityChildNodes.item(a).getNodeName());	
	        						if (eNode.getNodeName().equals("ShortId")) {
	        							
	        							
	        							NodeList shortIdList = eNode.getChildNodes();
	        							for (int b = 0; b < eNode.getChildNodes().getLength(); b++) {
	        								Node siNode = eNode.getChildNodes().item(b);
	        								if (siNode instanceof Element) {
	        									System.out.println(shortIdList.item(b).getNodeName());
	        								}
	        							}
	        							
	        						}
	        							
	        					}
	        					
	        				}
	        				entity.id = cNode.getAttributes().getNamedItem("Id").toString();
	        				
	        				entityList.add(entity);
	        			}
	        		}
	        	}
	        	
	        	//System.out.println();
	        }
	    }
	    
	    
	    System.out.println("SPIS OBIEKTÓW ENTTY: ");
	    for (int i = 0; i < entityList.size(); i++) {
	    	System.out.println(entityList.get(i).id + " ShortId: " +entityList.get(i).shortId);
	    }
    }
	
	public static void main(String[] args) throws Exception {
		new XMLParser();
	  }
}
