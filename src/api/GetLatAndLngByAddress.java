package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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

	/**
	 * 从excel获取地址信息
	 * 
	 * @param path
	 *            excel文件路径
	 * @param sheetNum
	 *            读取第几个sheet（从0开始）
	 * @param col
	 *            读取第几列（从0开始）
	 * @return
	 */
	public List<String> getAddress(String path, int sheetNum, int col) {
		File file = new File(path);
		List<String> result = new ArrayList<>();
		try {
			InputStream ins = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(ins);
			Sheet sheet = workbook.getSheetAt(sheetNum);
			Iterator<Row> iterator = sheet.rowIterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				result.add(String.valueOf(row.getCell(col)));
			}
			System.out.println("read over");
			ins.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 指定列写入经纬度
	 * 
	 * @param path
	 *            文件路劲
	 * @param list
	 *            api读取到的地址、经纬度信息
	 * @param sheetNum
	 *            sheet位置
	 * @param addressNum
	 *            对比地址进行确认
	 * @param lngNum
	 *            经度
	 * @param latNum
	 *            纬度
	 * @throws Exception
	 */
	public void writeLatAndLng(String path, List<Address> list, int sheetNum, int addressNum, int lngNum, int latNum)
			throws Exception {
		System.out.println("write start...");
		File file = new File(path);
		InputStream ins = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(ins);
		Sheet sheet = workbook.getSheetAt(sheetNum);
		for (int i = 1; i < list.size(); i++) {
			Row row = sheet.getRow(i);
			if ((row.getCell(addressNum).toString()).equals(list.get(i).getAddress())) {
				row.createCell(lngNum).setCellValue(list.get(i).getLng());
				row.createCell(latNum).setCellValue(list.get(i).getLat());
			}
		}
		
		OutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		System.out.println("wirte over");
		outputStream.flush();
		ins.close();
		outputStream.close();
		workbook.close();
	}

	public static void main(String[] args) {

		System.out.println("Start...");
		GetLatAndLngByAddress getLatAndLngByAddress = new GetLatAndLngByAddress();
		String path = "C:/Users/luco/Desktop/外勤系统/数据调整/合作医疗主数据20161124.xlsx";
		List<String> addresses = getLatAndLngByAddress.getAddress(path, 3, 3);
		List<Address> lists = new ArrayList<>();
		System.out.println("fetch lng and lat...");
		for (int i = 0; i < addresses.size(); i++) {
			String address = addresses.get(i);
			Map<String, BigDecimal> lngAndLat = getLatAndLngByAddress.getLatAndLngByAddress(address);
			String lng = String.valueOf(lngAndLat.get("lng"));
			String lat = String.valueOf(lngAndLat.get("lat"));
			lists.add(new Address(address, lat, lng));
		}

		try {
			getLatAndLngByAddress.writeLatAndLng(path, lists, 3, 3, 1, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End!");

	}

}

class Address {

	private String address;
	private String lat;
	private String lng;

	public Address(String address, String lat, String lng) {
		super();
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

}