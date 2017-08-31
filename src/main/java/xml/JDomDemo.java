package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.alibaba.fastjson.JSON;

public class JDomDemo {

	private static List<Book> bookliststorage = new ArrayList<Book>();
	
	
	public static List<Book> getBookliststorage() {
		return bookliststorage;
	}

	public void xmlParser() {
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			Document document = saxBuilder.build(new File("src/xml/books.xml"));
			Element rootEle = document.getRootElement();
			List<Element> list = rootEle.getChildren();
			System.out.println("根节点下的节点数（即book节点）："+list.size());
			for(Element book : list){
				//存储book
				Book bookEntity = new Book();
				//已知属性为id
				System.out.println("已知属性名，获取属性值："+book.getAttribute("id").getValue());
				//不知道有哪些属性
				List<Attribute> attrList = book.getAttributes();
				for(Attribute attribute : attrList){
					System.out.println("遍历所得属性名："+attribute.getName());
					System.out.println("遍历所得属性值"+attribute.getValue());
					
					if ("id".equals(attribute.getName())) {
						bookEntity.setId(attribute.getValue());
					}
				}
				
				List<Element> children = book.getChildren();
				for (Element element : children) {
					System.out.println("节点名："+element.getName());
					System.out.println("节点值："+element.getValue());
					
					if ("name".equals(element.getName())) {
						bookEntity.setName(element.getValue());
					} else if ("author".equals(element.getName())) {
						bookEntity.setAuthor(element.getValue());
					} else if ("publish".equals(element.getName())) {
						bookEntity.setPublish(element.getValue());
					} else if ("price".equals(element.getName())) {
						bookEntity.setPrice(element.getValue());
					} else if ("category".equals(element.getName())) {
						bookEntity.setCategory(element.getValue());
					}
				}
				
				bookliststorage.add(bookEntity);
				bookEntity = null;
				
			}
			System.out.println(JSON.toJSONString(bookliststorage));
			
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createXml() {
		Element rss = new Element("rss");
		rss.setAttribute("version", "2.0");
		Document document = new Document(rss);
		Element channel = new Element("channel");
		rss.addContent(channel);
		Element title = new Element("title");
		title.setText("国内最新新闻");
		channel.addContent(title);
		
		Element escape = new Element("title");
		CDATA content = new CDATA("d$#@<>dE");
		escape.addContent(content);
		channel.addContent(escape);
		
		Format format = Format.getPrettyFormat();
		File file = new File("src/xml/rssnewswritenbyjdom.xml");
		XMLOutputter outputter = new XMLOutputter(format);
		try {
			outputter.output(document, new FileOutputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		JDomDemo demo = new JDomDemo();
//		demo.xmlParser();
		demo.createXml();
	}
}
