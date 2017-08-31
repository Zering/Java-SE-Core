package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomDemo {
	
	/**
	 * 解析和写入都需要DocumentBuilder,提取为公用方法
	 * @return
	 */
	public DocumentBuilder getDocumentBuilder() {
		DocumentBuilder db= null;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			db = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return db;
	}

	/**
	 * xml解析
	 */
	public void xmlParser() {
		try {
			Document document = getDocumentBuilder().parse("src/xml/books.xml");
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
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 生成xml
	 */
	public void createXml() {
		Document document = getDocumentBuilder().newDocument();
		//去除不必要的属性
		document.setXmlStandalone(true);
		//根节点
		Element bookstore = document.createElement("bookstore");
		//子节点
		Element book = document.createElement("book");
		//设置节点属性
		book.setAttribute("id", "1");
		//添加子节点
		Element name = document.createElement("name");
		//这种方式不能写入值
//		name.setNodeValue("黑客与画家");
		name.setTextContent("黑客与画家");
		Element author = document.createElement("author");
		author.setTextContent("Paul Graham");
		Element publish = document.createElement("publish");
		publish.setTextContent("人民邮电出版社");
		Element price = document.createElement("price");
		price.setTextContent("49");
		book.appendChild(name);
		book.appendChild(author);
		book.appendChild(publish);
		book.appendChild(price);
		//将book节点设置为bookstore的子节点
		bookstore.appendChild(book);
		
		//第二本书
		Element book2 = document.createElement("book");
		book.setAttribute("id", "2");
		Element name2 = document.createElement("name");
		name2.setTextContent("算法");
		Element category = document.createElement("category");
		category.setTextContent("图灵图书系列");
		Element price2 = document.createElement("price");
		price2.setTextContent("99");
		book2.appendChild(name2);
		book2.appendChild(category);
		book2.appendChild(price2);
		bookstore.appendChild(book2);
		
		
		//将bookstore写入到dom树中
		document.appendChild(bookstore);
		//将dom树转换为xml
		
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			Transformer tf = tff.newTransformer();
			//公共标识符，能让根节点换行
			tf.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
			//设置缩进量
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			//设置换行
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			//dom转换xml
			tf.transform(new DOMSource(document), new StreamResult(new File("src/xml/bookswritenbydom.xml")));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		DomDemo domDemo = new DomDemo();
		//文件解析
//		domDemo.xmlParser();
		//文件写入
		domDemo.createXml();
	}
}
