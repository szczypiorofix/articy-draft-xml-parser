package xmlparser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import articy.content.A_Connection;
import articy.content.A_Dialogue;
import articy.content.A_DialogueFragment;
import articy.content.A_Entity;
import articy.content.A_FlowFragment;




public class XMLParser {

	public XMLParser() throws Exception {
		
		
		List<A_Entity> entityList = new ArrayList<A_Entity>();
		List<A_Dialogue> dialogueList = new ArrayList<A_Dialogue>();
		List<A_DialogueFragment> dialogueFragmentList = new ArrayList<A_DialogueFragment>();
		List<A_FlowFragment> flowFragmentList = new ArrayList<A_FlowFragment>();
		List<A_Connection> connectionList = new ArrayList<A_Connection>();
		
		
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();

	    Document document = builder.parse(ClassLoader.getSystemResourceAsStream("SimpleProject.xml"));

	    NodeList nodeList = document.getDocumentElement().getChildNodes();

	    for (int i = 0; i < nodeList.getLength(); i++) {

	        Node node = nodeList.item(i);
	        if (node instanceof Element) {
	        	
	        	NodeList childNodes = node.getChildNodes();
	        	
	        	for (int j = 0; j < childNodes.getLength(); j++) {
	        		Node cNode = childNodes.item(j);

	        		if (cNode instanceof Element) {	        			
	        			
	        			///// ##### ENTITY
	        			if (cNode.getNodeName().equals("Entity")) {
	        				
	        				A_Entity entity = new A_Entity();
	        				NodeList entityChildNodes = cNode.getChildNodes();
	        				
	        				for (int a = 0; a < entityChildNodes.getLength(); a++) {
	        					Node eNode = entityChildNodes.item(a);
	        					if (eNode instanceof Element) {
	        						
	        						switch (eNode.getNodeName()) {
	        						case "DisplayName":
										entity.displayName = eNode.getTextContent().trim();
										break;
	        						}
	        						
	        						if (eNode.getNodeName().equals("Features")) {
	        						}
	        					}	        					
	        				}
	        				entity.id = cNode.getAttributes().getNamedItem("Id").getNodeValue();
	        				entityList.add(entity);
	        			}
	        			
	        			
	        			///// ##### DIALOGUE
	        			if (cNode.getNodeName().equals("Dialogue")) {
	        				A_Dialogue dialog = new A_Dialogue();
	        				NodeList entityChildNodes = cNode.getChildNodes();
	        				
	        				for (int a = 0; a < entityChildNodes.getLength(); a++) {
	        					Node eNode = entityChildNodes.item(a);
	        					if (eNode instanceof Element) {
	        						switch (eNode.getNodeName()) {
	        						case "DisplayName":
	        							dialog.displayName = eNode.getTextContent().trim();
										break;
	        						case "Text":
	        							dialog.text = eNode.getTextContent().trim();
										break;
	        						}
	        						// REFERENCES
	        						if (eNode.getNodeName().equals("References")) {	        							
	        							NodeList refNodeList = eNode.getChildNodes();
	        							
	        							dialog.references = new ArrayList<String>();
	        							
	        							for (int b = 0; b < refNodeList.getLength(); b++) {
	        								Node rNode = refNodeList.item(b);
	        								if (rNode instanceof Element) {
		        								dialog.references.add(rNode.getAttributes().getNamedItem("IdRef").getNodeValue());
	        								}
	        							}
	        						}
	        						// PINS
	        						if (eNode.getNodeName().equals("Pins")) {	        							
	        							NodeList pinNodeList = eNode.getChildNodes();
	        							
	        							dialog.pins = new ArrayList<String>();
	        							
	        							for (int b = 0; b < pinNodeList.getLength(); b++) {
	        								Node pNode = pinNodeList.item(b);
	        								if (pNode instanceof Element) {
		        								dialog.pins.add(pNode.getAttributes().getNamedItem("Id").getNodeValue());
	        								}
	        							}
	        						}
	        					}	        					
	        				}
	        				dialog.id = cNode.getAttributes().getNamedItem("Id").getNodeValue();
	        				dialogueList.add(dialog);
	        			}
	        			
	        			
	        			
	        			
	        			///// ##### DIALOGUE FRAGMENT
	        			if (cNode.getNodeName().equals("DialogueFragment")) {	        				
	        				A_DialogueFragment dialogFragment = new A_DialogueFragment();
	        				NodeList entityChildNodes = cNode.getChildNodes();
	        				
	        				for (int a = 0; a < entityChildNodes.getLength(); a++) {
	        					Node eNode = entityChildNodes.item(a);
	        					if (eNode instanceof Element) {	        						
	        						switch (eNode.getNodeName()) {
	        						case "DisplayName":
	        							dialogFragment.displayName = eNode.getTextContent().trim();
										break;
	        						case "Text":
	        							dialogFragment.text = eNode.getTextContent().trim();
										break;
	        						case "MenuText":
	        							dialogFragment.menuText = eNode.getTextContent().trim();
										break;
	        						case "Speaker":
	        							dialogFragment.speakerId = eNode.getAttributes().getNamedItem("IdRef").getNodeValue();
										break;
	        						}
	        						
	        					}	        					
	        				}
	        				dialogFragment.id = cNode.getAttributes().getNamedItem("Id").toString();
	        				dialogueFragmentList.add(dialogFragment);
	        			}

	        			
	        			///// ##### FLOW FRAGMENT
	        			if (cNode.getNodeName().equals("FlowFragment")) {	        				
	        				A_FlowFragment flowFragment = new A_FlowFragment();
	        				NodeList entityChildNodes = cNode.getChildNodes();
	        				
	        				for (int a = 0; a < entityChildNodes.getLength(); a++) {
	        					Node eNode = entityChildNodes.item(a);
	        					if (eNode instanceof Element) {	        						
	        						switch (eNode.getNodeName()) {
	        						case "DisplayName":
	        							flowFragment.displayName = eNode.getTextContent().trim();
										break;
	        						case "Text":
	        							flowFragment.text = eNode.getTextContent().trim();
										break;
	        						}
	        						
	        					}	        					
	        				}
	        				
	        				flowFragment.id = cNode.getAttributes().getNamedItem("Id").getNodeValue();
	        				flowFragmentList.add(flowFragment);
	        			}
	        			
	        			
	        			
	        			///// ##### CONNECTION
	        			if (cNode.getNodeName().equals("Connection")) {	        				
	        				A_Connection connection = new A_Connection();
	        				NodeList entityChildNodes = cNode.getChildNodes();
	        				
	        				for (int a = 0; a < entityChildNodes.getLength(); a++) {
	        					Node eNode = entityChildNodes.item(a);
	        					if (eNode instanceof Element) {	        						
	        						switch (eNode.getNodeName()) {
	        						case "Source":
	        							connection.sourceIdRef = eNode.getAttributes().getNamedItem("IdRef").getNodeValue();
	        							connection.sourcePinRef = eNode.getAttributes().getNamedItem("PinRef").getNodeValue();
										break;
	        						case "Target":
	        							connection.targetIdRef = eNode.getAttributes().getNamedItem("IdRef").getNodeValue();
	        							connection.targetPinRef = eNode.getAttributes().getNamedItem("PinRef").getNodeValue();
										break;
	        						}
	        						
	        					}	        					
	        				}
	        				connection.id = cNode.getAttributes().getNamedItem("Id").getNodeValue();
	        				connectionList.add(connection);
	        			}	        			
	        			
	        			
	        		}
	        	}
	        }
	    }
	    
	    
	    System.out.println("SPIS OBIEKTÓW ENTITY: ");
	    for (int i = 0; i < entityList.size(); i++) {
	    	System.out.println(entityList.get(i).displayName +", " +entityList.get(i).id);
	    }
	    
	    System.out.println();
	    System.out.println("SPIS OBIEKTÓW DIALOGUE: ");
	    for (int i = 0; i < dialogueList.size(); i++) {
	    	System.out.println(dialogueList.get(i).displayName +", " +dialogueList.get(i).id);
	    	System.out.println("Text: " +dialogueList.get(i).text);
	    	System.out.println("References:");
	    	for (int j = 0; j < dialogueList.get(i).references.size(); j++) {
	    		System.out.println("Ref: " +dialogueList.get(i).references.get(j));
	    	}
	    	System.out.println("Pins:");
	    	for (int j = 0; j < dialogueList.get(i).pins.size(); j++) {
	    		System.out.println("Pin: " +dialogueList.get(i).pins.get(j));
	    	}
	    }
	    
	    System.out.println();
	    System.out.println("SPIS OBIEKTÓW DIALOGUE FRAGMENT: ");
	    for (int i = 0; i < dialogueFragmentList.size(); i++) {
	    	System.out.println(dialogueFragmentList.get(i).displayName +", " +dialogueFragmentList.get(i).id);
	    	System.out.println("Text: " +dialogueFragmentList.get(i).text);
	    	System.out.println("MenuText: " +dialogueFragmentList.get(i).menuText);
	    	System.out.println("Speaker ID: " +dialogueFragmentList.get(i).speakerId);
	    }
	    
	    System.out.println();
	    System.out.println("SPIS OBIEKTÓW FLOW FRAGMENT: ");
	    for (int i = 0; i < flowFragmentList.size(); i++) {
	    	System.out.println(flowFragmentList.get(i).displayName +", " +flowFragmentList.get(i).id);
	    	System.out.println("Text: " +flowFragmentList.get(i).text);
	    }
	    
	    System.out.println();
	    System.out.println("SPIS OBIEKTÓW CONNECTION: ");
	    for (int i = 0; i < connectionList.size(); i++) {
	    	System.out.println("ID: "+connectionList.get(i).id);
	    	System.out.println("Source: " +connectionList.get(i).sourceIdRef +" -> " +connectionList.get(i).sourcePinRef);
	    	System.out.println("Target: " +connectionList.get(i).targetIdRef +" -> " +connectionList.get(i).targetPinRef);
	    }
	    
	    System.out.println();
	    System.out.println();
	    System.out.println("Łączenie: ");
	    
	    for (int i = 0; i < connectionList.size(); i++) {
	    	
	    	if (connectionList.get(i).sourceIdRef.equals("0x0100000000000117")) {
	    		for (int x = 0; x < flowFragmentList.size(); x++) {
	    			if (flowFragmentList.get(x).id.equals(connectionList.get(i).sourceIdRef)) {
	    				System.out.println("POCZĄTEK!: " +flowFragmentList.get(x).displayName);
	    			}
	    		}
	    	}
	    	
	    }
    }
	
	public static void main(String[] args) throws Exception {
		new XMLParser();
	  }
}
