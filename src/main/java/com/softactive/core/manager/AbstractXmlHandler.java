package com.softactive.core.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.LocalDate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.softactive.grwa.manager.AbstractHandler;
import com.softactive.grwa.manager.MyException;
import com.softactive.grwa.object.MyError;
import com.softactive.grwa.object.Pullable;

public abstract class AbstractXmlHandler<P extends Pullable>
extends AbstractHandler<String, P, Document, List<Element>, Element> {

	private static final long serialVersionUID = 1702240267667438881L;

	protected abstract LocalDate resolveDateFromCustomFormat(String dateString) throws MyException;
	
	protected String getValue(Element e, String tag) throws MyException {
		Node n = e.getElementsByTagName(tag).item(0);
		if(n==null) {
			throw new MyException("Cannot find a node with name: " + tag +
					"in element: " + e);
		}
		return n.getTextContent();
	}
	
	protected TreeMap<String, String> getAsMap(Element e){
		TreeMap<String, String> values = new TreeMap<String, String>();
		NodeList list = e.getChildNodes();
		for(int i=0; i < list.getLength(); i++) {
			Node n = list.item(i);
			String value = null;
			try {
				String pseudoValue = n.getTextContent();
				value = resolveValidString(pseudoValue, null);
			} 
			catch (MyException e2) {
				continue;
			}
			values.put(n.getNodeName(), value);
		}
		return values;
	}

	@Override
	protected Document onFormatResponse(String rowInput) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			FileInputStream stream= new FileInputStream(rowInput);
			Document doc = dBuilder.parse(stream);
			doc.normalize();
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected abstract String[] getArrayTags(); 

	@Override
	protected List<Element> getArray(Document r, Map<String, Object> sharedParams) {
		List<Element> list = new ArrayList<>();
		for(String tag:getArrayTags()) {
			NodeList nodeList = r.getElementsByTagName(tag);
			for(int i = 0; i < nodeList.getLength(); i++) {
				list.add((Element)nodeList.item(i));
			}
		}
		return list;
	}
	
	protected List<Element> resolveAllNodes(Document r) {
		List<Element> list = new ArrayList<>();
		NodeList children = r.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			try {
				list = resolveElementsFromNode(children.item(i), list);
			} catch (MyException e) {
				System.out.println(e);
				continue;
			}
		}
		return list;
	}
	
	private List<Element> resolveElementsFromNode(Node n, List<Element> list) throws MyException {
		NodeList children = n.getChildNodes();
		if(children == null || children.getLength()==0) {
			throw new MyException("no children nodes found in given node\nwill check next node if exists");
		}
		boolean alreadyAdded = false;
		for(int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			String content = child.getTextContent();
			String name = child.getNodeName();
			if(name.startsWith("#")) {
				continue;
			}
			if(!alreadyAdded) {
				
				try {
					resolveValidDouble(content);
//					System.out.println("Double value is found\nnode name: " + name +
//							"\nnode content: " + tmpVal + "\nparent node: " +
//							n.getNodeName() + "\ngrand parent node: " + n.getParentNode().getNodeName());
					list.add((Element)n);
					alreadyAdded = true;
					continue;
				} catch (MyException e) {
					try {
						list = resolveElementsFromNode(child, list);
					} catch (MyException e1) {
						continue;
					}
				}
			} else {
				try {
					list = resolveElementsFromNode(child, list);
				} catch (MyException e1) {
					continue;
				}
			}
		}
		return list;
	}
	
	@Override
	protected List<P> getList(List<Element> array, Map<String, Object> sharedParams) {
		List<P> list = new ArrayList<>();
		for(Element e:array) {
			try {
				list.add(getObject(e, sharedParams));
			} catch (MyException e1) {
				MyError er = new MyError(ERROR_PARSING_OBJECT_FROM_XML, e1.getMsg());
				sharedParams.put(PARAM_ERROR, er);
				onError(sharedParams);
			}
		}
		return list;
	}
}
