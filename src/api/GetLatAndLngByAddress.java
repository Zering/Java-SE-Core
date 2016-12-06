package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 * 根据已有的excel中的地址信息，调用百度地图api获得经纬度信息
 * 
 * @author Zhanghj @ 2016年12月6日
 *
 */
public class GetLatAndLngByAddress {

	private URL getBaiduMapUrl(String addr) {
		String address = "";
		URL url = null;
		try {
			address = URLEncoder.encode(addr, "UTF-8");
			String urlString = String.format(
					"http://api.map.baidu.com/geocoder/v2/?ak=CDcf4e5552a789ad751d69f388e70b20&output=json&address=%s",
					address);
			url = new URL(urlString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return url;
	}

	public Map<String, BigDecimal> getLatAndLngByAddress(String addr) {
		String lat = "";
		String lng = "";
		URL myURL = null;
		URLConnection httpsConn = null;
		Map<String, BigDecimal> map = new HashMap<>();
		try {
			myURL = getBaiduMapUrl(addr);
			System.out.println(myURL);
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					if (data.indexOf("\"lat\":") != -1 && data.indexOf("\"lng\":") != -1) {
						lat = data.substring(data.indexOf("\"lat\":") + ("\"lat\":").length(),
								data.indexOf("},\"precise\""));
						lng = data.substring(data.indexOf("\"lng\":") + ("\"lng\":").length(),
								data.indexOf(",\"lat\""));
					}
				}
				insr.close();
			}
			if (!(lat.isEmpty() || lng.isEmpty())) {
				map.putIfAbsent("lat", new BigDecimal(lat));
				map.putIfAbsent("lng", new BigDecimal(lng));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public List<String> getAddress(File path) {
		List<String> result = new ArrayList<>();
		try {
			InputStream ins = new FileInputStream(path);
			HSSFWorkbook workbook = new HSSFWorkbook(ins);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.rowIterator();
			while (iterator.hasNext()) {
				HSSFRow row = (HSSFRow) iterator.next();
				// System.out.println(row.getCell(3));
				result.add(String.valueOf(row.getCell(3)));
			}
			System.out.println("read over");
			ins.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void writeLatAndLng(File file, List<Address> list) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = (HSSFSheet) wb.createSheet("sheet1");
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i);

			row.createCell(0).setCellValue(list.get(i).getAddress());
			row.createCell(1).setCellValue(list.get(i).getLng());
			row.createCell(2).setCellValue(list.get(i).getLat());
		}

		OutputStream outputStream = new FileOutputStream(file);
		wb.write(outputStream);
		System.out.println("wirte over");
		outputStream.flush();
		outputStream.close();
		wb.close();
	}

	public static void main(String[] args) {

		System.out.println("Start...");
		GetLatAndLngByAddress getLatAndLngByAddress = new GetLatAndLngByAddress();
		File file = new File("C:/Users/luco/Desktop/外勤系统/address1.xls");
		List<String> addresses = getLatAndLngByAddress.getAddress(file);
		List<Address> lists = new ArrayList<>();
		for (int i = 0; i < addresses.size(); i++) {
			String address = addresses.get(i);
			Map<String, BigDecimal> lngAndLat = getLatAndLngByAddress.getLatAndLngByAddress(address);
			String lng = String.valueOf(lngAndLat.get("lng"));
			String lat = String.valueOf(lngAndLat.get("lat"));
			lists.add(new Address(address, lat, lng));
		}

		File fileOut = new File("C:/Users/luco/Desktop/外勤系统/address2.xls");
		if (!fileOut.exists()) {
			try {
				fileOut.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			getLatAndLngByAddress.writeLatAndLng(fileOut, lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End!");

	}

}
