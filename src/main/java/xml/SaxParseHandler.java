package xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseHandler extends DefaultHandler {
	
	int bookIndex = 0;
	
	String value = null;
	Book book = null;
	private	List<Book> booklist = new ArrayList<Book>();
	
	public List<Book> getBooklist() {
		return booklist;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("-------------Sax解析开始------------------");
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("-------------Sax解析结束-----------------");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		//开始解析book元素的属性
		if ("book".equals(qName)) {
			book = new Book();
			System.out.println("开始解析第"+ ++bookIndex +"本书");
			//已知book元素下属性的名称，根据属性的名称获取属性值
			String value = attributes.getValue("id");
			System.out.println("book的属性值是："+value);
			
			//不知道book元素下属性的名称和个数时
			int num = attributes.getLength();
			for (int i = 0; i < num; i++) {
				System.out.print("属性名："+attributes.getQName(i));
				System.out.println("------属性名："+attributes.getValue(i));
				if ("id".equals(attributes.getQName(i))) {
					book.setId(attributes.getValue(i));
				}
			}
		} else {
			System.out.print("节点名： "+qName);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if ("book".equals(qName)) {
			booklist.add(book);
			book = null;
			System.out.println("结束解析第"+ bookIndex +"本书");
		} else if ("name".equals(qName)) {
			book.setName(value);
		} else if ("author".equals(qName)) {
			book.setAuthor(value);
		} else if ("publish".equals(qName)) {
			book.setPublish(value);
		} else if ("price".equals(qName)) {
			book.setPrice(value);
		} else if ("category".equals(qName)) {
			book.setCategory(value);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		value = new String(ch, start, length);
		if (!value.trim().isEmpty()) {
			System.out.println("-----节点值："+value);
		} 
	}
}
