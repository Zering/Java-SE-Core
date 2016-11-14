package xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomDemo {

	/**
	 * xml解析
	 */
	public void xmlParser() {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse("src/xml/books.xml");
			NodeList booklists = document.getElementsByTagName("book");
			for(int i = 0; i < booklists.getLength();i++){
				//显示节点属性值有两个方式
				//方式一：不知道book有哪些属性
				NamedNodeMap maps = booklists.item(i).getAttributes();
				for (int j = 0; j < maps.getLength(); j++) {
					System.out.print("属性："+maps.item(j).getNodeName());
					System.out.println("-------"+maps.item(j).getFirstChild().getNodeValue());
				}
				//方式二：知道book节点有且只有一个id属性
				Element element = (Element) booklists.item(i);
				System.out.println("属性名id,属性值："+element.getAttribute("id"));
				
				NodeList children = booklists.item(i).getChildNodes();
				//换行符会被默认为一个text节点，表示为#text
				System.out.println(children.getLength());
				for (int j = 0; j < children.getLength(); j++) {
//					System.out.println(children.item(j).getNodeName());
					//不需要输出text节点（即换行符）,只获取ELEMENT_NODE的节点
					if (children.item(j).getNodeType() == Node.ELEMENT_NODE) {
						System.out.print("节点名："+children.item(j).getNodeName());
						//获取节点值得两种方式
						//1.节点内的内容会被默认为是一个节点，可以精确的获取子节点的内容
						//如第二本书，就只显示 算法
						System.out.println("----节点值："+children.item(j).getFirstChild().getNodeValue());
						//2.直接输出节点下的内容，即使还有子节点也会忽略子节点标签，直接显示所有的内容
						//如第二本书，显示 算法第四版
						System.out.println("----节点值："+children.item(j).getTextContent());
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public static void main(String[] args) {
		DomDemo domDemo = new DomDemo();
		domDemo.xmlParser();
	}
}
