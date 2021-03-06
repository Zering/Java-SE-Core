package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.alibaba.fastjson.JSON;

public class Dom4JDemo {

	private static List<Book> bookListStorage = new ArrayList<Book>();
	
	
	public static List<Book> getBookListStorage() {
		return bookListStorage;
	}

	public void xmlParser() {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File("src/xml/books.xml"));
			Element bookstore = document.getRootElement();
			List<Element> list = bookstore.elements();
			Iterator<Element> iterator = bookstore.elementIterator();
			while (iterator.hasNext()) {
				Element book = iterator.next();
				Book bookEntity = new Book();
				List<Attribute> attributes = book.attributes();
				for (Attribute attribute : attributes) {
					System.out.println("属性名："+attribute.getName()+"---属性值："+attribute.getValue());
					if ("id".equals(attribute.getName())) {
						bookEntity.setId(attribute.getValue());
					}
				}
				
				Iterator<Element> iterator2 = book.elementIterator();
				while (iterator2.hasNext()) {
					Element element = iterator2.next();
					System.out.println("节点名："+element.getName()+"---节点值："+element.getStringValue());
					
					if ("name".equals(element.getName())) {
						bookEntity.setName(element.getStringValue());
					} else if ("author".equals(element.getName())) {
						bookEntity.setAuthor(element.getStringValue());
					} else if ("publish".equals(element.getName())) {
						bookEntity.setPublish(element.getStringValue());
					} else if ("price".equals(element.getName())) {
						bookEntity.setPrice(element.getStringValue());
					} else if ("category".equals(element.getName())) {
						bookEntity.setCategory(element.getStringValue());
					}
					
				}
				
				bookListStorage.add(bookEntity);
				bookEntity = null;
			}
			
			System.out.println(JSON.toJSONString(bookListStorage));
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void createXml() {
		Document document = DocumentHelper.createDocument();
		Element rss = document.addElement("rss");
		rss.addAttribute("version", "2.0");
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("国内最新新闻");
		//有换行、缩进的xml格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		
		File file = new File("src/xml/rssnewswritenbydom4j.xml");
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileOutputStream(file),format);
			//设置不转义特殊字符
			writer.setEscapeText(false);
			writer.write(document);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Dom4JDemo demo = new Dom4JDemo();
//		demo.xmlParser();
		demo.createXml();
	}
}
