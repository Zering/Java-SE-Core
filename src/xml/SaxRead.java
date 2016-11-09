package xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;

public class SaxRead {

	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			SaxParseHandler handler = new SaxParseHandler();
			parser.parse("src/xml/books.xml", handler);
			//使用fastJson查看解析得到的书单
			System.out.println(JSON.toJSONString(handler.getBooklist()));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
