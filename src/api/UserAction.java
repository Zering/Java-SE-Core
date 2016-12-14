package api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserAction {

	/**
	 * 要删除客户表插入所对应的Id
	 * 
	 * @throws SQLException
	 */

	public void checkUserId() throws Exception {
		OpExcel op = new OpExcel();
		Map<String, String> map = new HashMap<>();
		Map<String, String> mapId = new HashMap<>();
		CommonUtil common = new CommonUtil();
		OpExcel opExcel = new OpExcel();
		String hql = "";
		String docAddress = "C:/Users/luco/Desktop/外勤系统/数据调整/合作医疗主数据20161124.xlsx";

		// 查找代表ID
		// (String docAddress,int sheetID,int colNum)
		map = op.readExcel(docAddress, 1, 11);
		for (int i = 1; i <= map.size(); i++) {
			hql = "select id from T_USER where EMPLOYEE_NAME='" + map.get("data_" + i) + "' and DELETE_STATUS=1 ";
			String id = common.getEntityId(hql);
			mapId.put("data_" + i, id);
			// System.out.println("---------"+id);
		}
		// (int sheetNum,int colNum,String docAddress,Map map)
		opExcel.writeExcel(1, 12, docAddress, mapId);

		// 查找姓名
		// //(String docAddress,int sheetID,int colNum)
		// map= op.readExcel(docAddress,3,1);
		// for (int i = 1; i <=map.size(); i++) {
		// hql="select employee_name from T_USER where id =(SELECT user_id from
		// T_STORE_USER where STORE_ID
		// ="+Integer.parseInt(map.get("data_"+i).toString())+")";
		//// System.out.println(hql);
		// String name= common.getEntityName(hql);
		// mapId.put("data_"+i, name);
		// // System.out.println("---------"+mapId.get("data_"+i));
		// }
		// //(int sheetNum,int colNum,String docAddress,Map map)
		// opExcel.writeExcel(3, 3, docAddress,mapId);

		// // 查找等级
		// //(String docAddress,int sheetID,int colNum)
		// map= op.readExcel(docAddress,3,1);
		// for (int i = 1; i <=map.size(); i++) {
		// hql="select name from T_D_STORE_LEVEL where id=(select STORE_LEVEL_ID
		// from T_STORE where id ="+map.get("data_"+i)+")";
		// // System.out.println(hql);
		// String name= common.getEntityName(hql);
		// mapId.put("data_"+i, name);
		// // System.out.println("---------"+mapId.get("data_"+i));
		// }
		// //(int sheetNum,int colNum,String docAddress,Map map)
		// opExcel.writeExcel(3, 5, docAddress,mapId);

		System.out.println("insertStoreId method end" + docAddress);
	}

	public static void main(String[] args) throws Exception {
		UserAction user = new UserAction();
		user.checkUserId();
	}
}
