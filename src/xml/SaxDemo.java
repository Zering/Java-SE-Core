package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.alibaba.fastjson.JSON;

public class SaxDemo {

	/**
	 * xml解析
	 */
	public List<Book> xmlParser() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SaxParseHandler handler = null;
		try {
			SAXParser parser = factory.newSAXParser();
			handler = new SaxParseHandler();
			parser.parse("src/xml/books.xml", handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return handler.getBooklist();
	}

	/**
	 * 生成xml
	 */
	public void createXml() {
		List<Book> list = xmlParser();
		SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

		try {
			TransformerHandler transformerHandler = saxTransformerFactory.newTransformerHandler();
			Transformer transformer = transformerHandler.getTransformer();
			//公共标识符，能让根节点换行
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
			//设置缩进量
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			//设置换行
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			File file = new File("src/xml/bookswritenbysax.xml");
			if (!file.exists()) {
				file.createNewFile();
			}
			Result result = new StreamResult(new FileOutputStream(file));
			transformerHandler.setResult(result);
			
			transformerHandler.startDocument();
			AttributesImpl attr =  new AttributesImpl();
			//前两个参数与命名空间相关
			transformerHandler.startElement("", "", "bookstore", attr);
			
			for (Book book : list) {
				attr.clear();
				attr.addAttribute("", "", "id", "", book.getId());
				transformerHandler.startElement("", "", "book", attr);
				
				attr.clear();
				if (book.getName() != null && !book.getName().trim().isEmpty()) {
					transformerHandler.startElement("", "", "name", attr);
					transformerHandler.characters(book.getName().toCharArray(), 0, book.getName().length());
					transformerHandler.endElement("", "", "name");
				}
				if (book.getAuthor() != null && !book.getAuthor().trim().isEmpty()) {
					transformerHandler.startElement("", "", "author", attr);
					transformerHandler.characters(book.getAuthor().toCharArray(), 0, book.getAuthor().length());
					transformerHandler.endElement("", "", "author");
				}
				if (book.getPublish() != null && !book.getPublish().trim().isEmpty()) {
					transformerHandler.startElement("", "", "publish", attr);
					transformerHandler.characters(book.getPublish().toCharArray(), 0, book.getPublish().length());
					transformerHandler.endElement("", "", "publish");
				}
				if (book.getCategory() != null  &&  !book.getCategory().trim().equals("")) {
					transformerHandler.startElement("", "", "category", attr);
					transformerHandler.characters(book.getCategory().toCharArray(), 0, book.getCategory().length());
					transformerHandler.endElement("", "", "category");
				}
				if (book.getPrice() != null && !book.getPrice().trim().isEmpty()) {
					transformerHandler.startElement("", "", "price", attr);
					transformerHandler.characters(book.getPrice().toCharArray(), 0, book.getPrice().length());
					transformerHandler.endElement("", "", "price");
				}
				
				transformerHandler.endElement("", "", "book");
			}
			
			transformerHandler.endElement("", "", "bookstore");
			transformerHandler.endDocument();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		SaxDemo saxDemo = new SaxDemo();
		System.out.println(JSON.toJSONString(saxDemo.xmlParser()));
		saxDemo.createXml();
	}
}
