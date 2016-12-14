package api;

import java.util.HashMap;
import java.util.Map;

public class AddStore {

	public void addStoreIdByStoreName(String path, int sheetNum, int storeNameCol, int storeIdCol) {
		String sql = "";
		OpExcel op = new OpExcel();
		CommonUtil util = new CommonUtil();
		Map<String, String> mapOut = new HashMap<>();
		try {
			Map<String, String> map = op.readExcel(path, sheetNum, storeNameCol);
			System.out.println("***读取客户名称：***");
			for (int i = 1; i <= map.size(); i++) {
				sql = "SELECT s.id from T_STORE s where s.DEPARTMENT_ID in (SELECT d.ID from T_DEPARTMENT d WHERE d.PARENT_ID = 109) AND s.STORE_NAME='"
						+ map.get("data_" + i) + "' AND s.DELETE_STATUS=1;";

				String id = util.getEntityId(sql);
				mapOut.put("data_" + i, id);
			}
			System.out.println("----write start----");
			op.writeExcel(sheetNum, storeIdCol, path, mapOut);
			System.out.println("----write over-----");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDistrictIdByDistrictFullName(String path, int sheetNum, int districtNameCol, int districtIdCol) {
		String sql = "";
		OpExcel op = new OpExcel();
		CommonUtil util = new CommonUtil();
		Map<String, String> mapOut = new HashMap<>();
		try {
			Map<String, String> map = op.readExcel(path, sheetNum, districtNameCol);
			System.out.println("***读取地区名称：***");
			for (int i = 1; i <= map.size(); i++) {
				sql = "select id from T_D_DISTRICT where all_name='" + map.get("data_" + i) + "';";
				String id = util.getEntityId(sql);
				mapOut.put("data_" + i, id);
			}
			System.out.println("----write start----");
			op.writeExcel(sheetNum, districtIdCol, path, mapOut);
			System.out.println("----write over-----");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addUserIdByUserName(String path, int sheetNum, int userNameCol, int userIdCol) {
		String sql = "";
		OpExcel op = new OpExcel();
		CommonUtil util = new CommonUtil();
		Map<String, String> mapOut = new HashMap<>();
		try {
			Map<String, String> map = op.readExcel(path, sheetNum, userNameCol);
			System.out.println("***读取用户名称：***");
			for (int i = 1; i <= map.size(); i++) {
				sql = "select id from T_USER where employee_name='" + map.get("data_" + i) + "';";
				String id = util.getEntityId(sql);
				mapOut.put("data_" + i, id);
			}
			System.out.println("----write start----");
			op.writeExcel(sheetNum, userIdCol, path, mapOut);
			System.out.println("----write over-----");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkUsername(String path, int sheetNum, int userNameCol, int employeeNameCol) {
		String sql = "";
		OpExcel op = new OpExcel();
		CommonUtil util = new CommonUtil();
		Map<String, String> mapOut = new HashMap<>();
		try {
			Map<String, String> map = op.readExcel(path, sheetNum, userNameCol);
			System.out.println("***读取用户登录名：***");
			for (int i = 1; i <= map.size(); i++) {
				sql = "select employee_name from T_USER where username='" + map.get("data_" + i) + "';";
				String id = util.getEntityId(sql);
				mapOut.put("data_" + i, id);
			}
			System.out.println("----write start----");
			op.writeExcel(sheetNum, employeeNameCol, path, mapOut);
			System.out.println("----write over-----");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchLinkmanByStoreName(String path, int sheetNum, int storeNameCol, int linkmanIdCol) {
		String sql = "";
		OpExcel op = new OpExcel();
		CommonUtil util = new CommonUtil();
		Map<String, String> mapOut = new HashMap<>();
		try {
			Map<String, String> map = op.readExcel(path, sheetNum, storeNameCol);
			System.out.println("***读取客户名称***");
			for (int i = 1; i <= map.size(); i++) {
				sql = "select LINKMAN_ID from T_STORE where STORE_NAME='" + map.get("data_" + i) + "';";
				String id = util.getEntityId(sql);
				mapOut.put("data_" + i, id);
			}
			System.out.println("***写入联系人id***");
			op.writeExcel(sheetNum, linkmanIdCol, path, mapOut);
			System.out.println("----write over-----");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找营销体系码
	 * 
	 * @param path
	 * @param sheetNum
	 * @param storeIdCol
	 * @param CHR1004Col
	 */
	public void searchCHR1004ByStoreId(String path, int sheetNum, int storeIdCol, int CHR1004Col) {
		String sql = "";
		OpExcel op = new OpExcel();
		CommonUtil util = new CommonUtil();
		Map<String, String> mapOut = new HashMap<>();
		try {
			Map<String, String> map = op.readExcel(path, sheetNum, storeIdCol);
			System.out.println("***读取客户id称***");
			for (int i = 1; i <= map.size(); i++) {
				sql = "select CHR1004 from T_STORE_EXT where STORE_ID='" + map.get("data_" + i) + "';";
				String id = util.getEntityId(sql);
				mapOut.put("data_" + i, id);
			}
			System.out.println("***写入营销体系码***");
			op.writeExcel(sheetNum, CHR1004Col, path, mapOut);
			System.out.println("----write over-----");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkStoreId() throws Exception {
		OpExcel op = new OpExcel();
		Map<String, String> map = new HashMap<>();
		Map<String, String> mapId = new HashMap<>();
		CommonUtil common = new CommonUtil();
		OpExcel opExcel = new OpExcel();
		String hql = "";
		String docAddress = "C:/Users/luco/Desktop/外勤系统/数据调整/合作医疗主数据20161124.xlsx";

		// 查找客户ID
		// map = op.readExcel(docAddress, 1, 16);
		// System.out.println("***读取客户名称：***");
		// System.out.println(JSON.toJSONString(map));
		// for (int i = 1; i <= map.size(); i++) {
		// hql = "select ID from T_STORE where STORE_NAME='" + map.get("data_" +
		// i) + "' and delete_status=1 ";
		// String id = common.getEntityId(hql);
		// mapId.put("data_" + i, id);
		// }
		// opExcel.writeExcel(1, 17, docAddress, mapId);

		// 医疗合作团队
		/*
		 * map = op.readExcel(docAddress, 1, 16);
		 * System.out.println("***读取客户名称：***");
		 * System.out.println(JSON.toJSONString(map)); for (int i = 1; i <=
		 * map.size(); i++) { hql =
		 * "SELECT s.* from T_STORE s where s.DEPARTMENT_ID in (SELECT d.ID from T_DEPARTMENT d WHERE d.PARENT_ID = 109) AND s.STORE_NAME='"
		 * + map.get("data_" + i) + "' AND s.DELETE_STATUS=1;";
		 * 
		 * String id = common.getEntityId(hql); mapId.put("data_" + i, id); }
		 * opExcel.writeExcel(1, 18, docAddress, mapId);
		 */

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

		// 查找省份
		// (String docAddress,int sheetID,int colNum)
		map = op.readExcel(docAddress, 1, 24);
		// System.out.println(JSON.toJSONString(map));
		for (int i = 1; i <= map.size(); i++) {
			hql = "select id from T_D_DISTRICT where all_name='" + map.get("data_" + i) + "'"; // 省份ID
			// hql="select ID from T_STORE where
			// STORE_NAME='"+map.get("data_"+i)+"'
			// and delete_status=1 "; // 客户ID
			// System.out.println(hql);
			String name = common.getEntityName(hql);
			mapId.put("data_" + i, name);
		}
		opExcel.writeExcel(1, 25, docAddress, mapId);
		// System.out.println(JSON.toJSONString(mapId));
		// 查找客户联系人id
		// //(String docAddress,int sheetID,int colNum)
		// map= op.readExcel(docAddress,2,15);
		// for (int i = 1; i <=map.size(); i++) {
		// hql="select id from T_LINKMAN where
		// STORE_ID="+Integer.parseInt(map.get("data_"+i).toString());
		// System.out.println("----"+hql);
		// String id= common.getEntityId(hql);
		// mapId.put("data_"+i, id);
		// // System.out.println("---------"+id);
		// }
		// //(int sheetNum,int colNum,String docAddress,Map map)
		// opExcel.writeExcel(2, 16, docAddress,mapId);

		// 查找团队
		// (String docAddress,int sheetID,int colNum)
		// map= op.readExcel(docAddress,4,14);
		// for (int i = 1; i <=map.size(); i++) {
		// hql="select PARENT_ID from T_DEPARTMENT where id=(select
		// department_id from T_STORE where ID
		// ="+Integer.parseInt(map.get("data_"+i).toString())+")";
		// String id= common.getEntityId(hql);
		// mapId.put("data_"+i, id);
		// // System.out.println("---------"+id);
		// }
		// //(int sheetNum,int colNum,String docAddress,Map map)
		// opExcel.writeExcel(4, 16, docAddress,mapId);

		// common
		// (String docAddress,int sheetID,int colNum)
		// map= op.readExcel(docAddress,2,16);
		// for (int i = 1; i <=map.size(); i++) {
		// hql="select ADDRESS from T_STORE where
		// STORE_NAME='"+map.get("data_"+i).toString()+"' and delete_status=0
		// ";//地址
		// // hql="select CUSTOMER_ID from T_STORE_EXT where
		// STORE_ID="+Integer.parseInt(map.get("data_"+i).toString()); //上级公司id
		// // hql="select LONGITUDE from T_STORE where
		// id="+Integer.parseInt(map.get("data_"+i).toString()); //经度
		// // hql="select LATITUDE from T_STORE where
		// id="+Integer.parseInt(map.get("data_"+i).toString()); //纬度
		// // hql="select DISTRICT_ID from T_STORE where ID
		// ="+Integer.parseInt(map.get("data_"+i).toString()) ; // 省份id
		// // hql="select name from T_D_STORE_LEVEL where id =(select
		// STORE_LEVEL_ID from T_STORE where
		// id="+Integer.parseInt(map.get("data_"+i).toString())+")";
		// // hql="select id from T_D_DISTRICT where
		// all_name='"+map.get("data_"+i).toString()+"'";
		// String id= common.getEntityId(hql);
		// mapId.put("data_"+i, id);
		// // System.out.println("---------"+id);
		// }
		// //(int sheetNum,int colNum,String docAddress,Map map)
		// opExcel.writeExcel(2, 21, docAddress,mapId);

		System.out.println("---checkStoreId method end---");
	}

	public static void main(String[] args) throws Exception {
		AddStore opStore = new AddStore();
		String path = "C:/Users/luco/Desktop/外勤系统/数据调整/合作医疗主数据20161124.xlsx";
		// String userpath = "C:/Users/luco/Desktop/外勤系统/数据调整/合作医疗-User.xlsx";
//		 String storePath = "C:/Users/luco/Desktop/外勤系统/数据调整/合作医疗-Store.xlsx";
		opStore.addStoreIdByStoreName(path, 1, 16, 18);

	}
}
