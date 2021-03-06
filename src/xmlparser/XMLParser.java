package xmlparser;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
import articy.content.ArticyDraftObject;




public class XMLParser {

	private boolean theEnd = false;
	private String output;
	private String lastNode;
	private String startNode, endNode;
	private Scanner keyboard;
	
	private List<A_Entity> entityList = new ArrayList<A_Entity>();
	private List<A_Dialogue> dialogueList = new ArrayList<A_Dialogue>();
	private List<A_DialogueFragment> dialogueFragmentList = new ArrayList<A_DialogueFragment>();
	private List<A_FlowFragment> flowFragmentList = new ArrayList<A_FlowFragment>();
	private List<A_Connection> connectionList = new ArrayList<A_Connection>();
	
	private List<ArticyDraftObject> listOfAllObjects = new ArrayList<ArticyDraftObject>();
	
	
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = factory.newDocumentBuilder();
    
	
	public XMLParser() throws Exception {
		
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
	        								        							
	        							for (int b = 0; b < pinNodeList.getLength(); b++) {
	        								Node pNode = pinNodeList.item(b);
	        								if (pNode instanceof Element) {
	        									if (pNode.getAttributes().getNamedItem("Semantic").getNodeValue().equals("Input")) {
	        										dialog.pinsInput.add(pNode.getAttributes().getNamedItem("Id").getNodeValue());
	        									}
	        									if (pNode.getAttributes().getNamedItem("Semantic").getNodeValue().equals("Output")) {
	        										dialog.pinsOutput.add(pNode.getAttributes().getNamedItem("Id").getNodeValue());
	        									}
	        								}
	        							}
	        						}
	        					}	        					
	        				}
	        				dialog.id = cNode.getAttributes().getNamedItem("Id").getNodeValue();
	        				dialogueList.add(dialog);
	        				listOfAllObjects.add(dialog);
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
	        				dialogFragment.id = cNode.getAttributes().getNamedItem("Id").getNodeValue();
	        				dialogueFragmentList.add(dialogFragment);
	        				listOfAllObjects.add(dialogFragment);
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
		        					case "Pins":
		        						NodeList pinsChildNodes = eNode.getChildNodes();
		        						for (int b = 0; b < pinsChildNodes.getLength(); b++) {
		        							Node pNode = pinsChildNodes.item(b);
		        							if (pNode instanceof Element) {
		        								
		        								//flowFragment.pins.add(pNode.getAttributes().getNamedItem("Id").getNodeValue());
	        									if (pNode.getAttributes().getNamedItem("Semantic").getNodeValue().equals("Input")) {
	        										flowFragment.pinsInput.add(pNode.getAttributes().getNamedItem("Id").getNodeValue());
	        									}
	        									if (pNode.getAttributes().getNamedItem("Semantic").getNodeValue().equals("Output")) {
	        										flowFragment.pinsOutput.add(pNode.getAttributes().getNamedItem("Id").getNodeValue());
	        									}
		        								
		        							}
		        						}
										break;
	        						}
	        					}	        					
	        				}
	        				
	        				flowFragment.id = cNode.getAttributes().getNamedItem("Id").getNodeValue();
	        				flowFragmentList.add(flowFragment);
	        				listOfAllObjects.add(flowFragment);
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
	    	//System.out.println("References:");
	    	//for (int j = 0; j < dialogueList.get(i).references.size(); j++) {
	    	//	System.out.println("Ref: " +dialogueList.get(i).references.get(j));
	    	//}
	    	System.out.println("Pins:");
	    	for (int j = 0; j < dialogueList.get(i).pinsInput.size(); j++) {
	    		System.out.println("INPUT Pin: " +dialogueList.get(i).pinsInput.get(j));
	    	}
	    	for (int j = 0; j < dialogueList.get(i).pinsOutput.size(); j++) {
	    		System.out.println("OUTPUT Pin: " +dialogueList.get(i).pinsOutput.get(j));
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
	    	for (int j = 0; j < flowFragmentList.get(i).pinsInput.size(); j++) {
	    		System.out.println("INPUT PIN: "+flowFragmentList.get(i).pinsInput.get(j));
	    	}
	    	for (int j = 0; j < flowFragmentList.get(i).pinsOutput.size(); j++) {
	    		System.out.println("OUTPUT PIN: "+flowFragmentList.get(i).pinsOutput.get(j));
	    	}
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
	    System.out.println("Dołączanie połączeń do Flow Fragments");
	    for (int i = 0; i < flowFragmentList.size(); i++) {
	    	for (int j = 0; j < connectionList.size(); j++) {
	    		if (flowFragmentList.get(i).id.equals(connectionList.get(j).sourceIdRef)) {
	    			System.out.println("ID: " +j+ " " +flowFragmentList.get(i).id +"  +sourceIdRef: "+connectionList.get(j).targetIdRef);
	    			flowFragmentList.get(i).sources.add(connectionList.get(j).sourceIdRef);
	    			flowFragmentList.get(i).targets.add(connectionList.get(j).targetIdRef);
	    		}
	    	}
	    }
	    
	    System.out.println();
	    System.out.println("Teraz linki każdego z Flow Fialogs:");
	    for (int i = 0; i < flowFragmentList.size(); i++) {
	    	System.out.print("FLOW: "+i +" "+flowFragmentList.get(i).id);
	    	for (int j = 0; j < flowFragmentList.get(i).sources.size(); j++) {
	    		System.out.print(" S: "+flowFragmentList.get(i).sources.get(j));
	    		System.out.print(" T: "+flowFragmentList.get(i).targets.get(j));
	    	}
	    	System.out.println();
	    }
	    
	    System.out.println();
	    System.out.println("Wszystkie obiekty w grze:");
	    for (int i = 0; i < listOfAllObjects.size(); i++) System.out.println(listOfAllObjects.get(i).id);
	    
	    System.out.println();
	    System.out.println();
	    System.out.println("Łączenie przygody 'do kupy': ");
	    
	    startNode = "";
	    endNode = "";
	    for (int i=0; i < flowFragmentList.size(); i++) {
	    	if (flowFragmentList.get(i).displayName.equals("START")) {
	    		
	    	}
	    	if (flowFragmentList.get(i).displayName.equals("KONIEC")) {
	    		endNode=flowFragmentList.get(i).id;
	    	}
	    }
	    startNode="0x010000000000028C";
	    lastNode = startNode;
	    output = "";
	    List<String> options = new ArrayList<String>();
	    List<String> optionsId = new ArrayList<String>();
	    boolean goodChoice = true;
	    
	    do {
	    	for (int i = 0; i < listOfAllObjects.size(); i++) {
	    		for (int j = 0; j < listOfAllObjects.get(i).pinsInput.size(); j++) {
	    			if (listOfAllObjects.get(i).pinsInput.get(j).equals(lastNode)) {
	    				System.out.println("PinIn: " +lastNode);
	    				System.out.println("Flow: " +listOfAllObjects.get(i).displayName +" "+listOfAllObjects.get(i).id);
	    				lastNode = listOfAllObjects.get(i).pinsOutput.get(j);
	    				System.out.println("PinOut: "+lastNode);
	    			}
	    		}
	    	}
	    	
	    	for (int j = 0; j < connectionList.size(); j++) {
	    		if (lastNode.equals(connectionList.get(j).sourcePinRef)) {
	    			lastNode = connectionList.get(j).targetPinRef;
	    			System.out.println("C: "+connectionList.get(j).id);
	    		}
	    	}
	    	try {
	    		Thread.sleep(500);
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    while(!theEnd);
	    System.out.println(output);
    }
	
	public static void main(String[] args) throws Exception {
		new XMLParser();
	  }
}
