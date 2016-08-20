package com.soap.parser;

import org.dom4j.Element;
import org.dom4j.Node;

public class SOAPPARSER {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String str="";
		}

	public static void printRecursive(Element element) {
	    for(int i = 0, size = element.nodeCount(); i < size; i++) {
	        Node node = element.node(i);
	        if(node instanceof Element) {
	            Element currentNode = (Element) node;
	            if(currentNode.isTextOnly()) {
	                System.out.println(currentNode.getText());
	            }
	            printRecursive(currentNode);
	        }
	    }
	}
}