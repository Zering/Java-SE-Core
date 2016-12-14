package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JDBC {

	ResultSet rs = null;
	Connection conn = null;

	private Connection GetConn() {

		 String URL = "jdbc:mysql://172.16.10.154:3306/moss";
		 String UNAME = "root";
		 String PWD = "root";

		// 测试
//		String URL = "jdbc:mysql://192.168.3.75:3306/moss";
//		String UNAME = "root";
//		String PWD = "123456";
		try {
			// 1.加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获得数据库的连接
			conn = DriverManager.getConnection(URL, UNAME, PWD);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return conn;
	}

	public ResultSet QueryInfo(String sql) {
		try {
			rs = this.GetConn().prepareStatement(sql).executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void excute(String sql) {
		try {
			this.GetConn().prepareStatement(sql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeCon() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加合作医疗客户信息
	 * 表格格式标准：A:地址|B:经度|C:纬度|D:客户名称|E:大区id|F:客户编号|G:地区id（省份、城市）|H:客户级别id
	 * 
	 * @param path
	 *            文件路径
	 * @param sheetNum
	 *            sheet位置
	 */
	public void insertStoreInfor(String path, int sheetNum) {
		try {
			String hql = "insert into T_STORE(address,DELETE_status,longitude,latitude,store_name,department_id,store_status_id,store_type_id,store_no,district_id,store_cate_id,create_user_id,create_time,store_level_id,company_id,is_located)"
					+ " values(?,1,?,?,?,?,1,4922,?,?,4922,90,'2016-12-13 19:00',?,49,2);";
			PreparedStatement ps = this.GetConn().prepareStatement(hql);
			OpExcel opExcel = new OpExcel();
			Map<String, Map<String, String>> rowMap = opExcel.readManyExcel(path, sheetNum, 0, 7);

			for (int i = 1; i <= rowMap.size(); i++) {
				Map<String, String> colMap = (rowMap.get("row_" + i));
				ps.setString(1, colMap.get("col_0"));
				ps.setFloat(2, Float.parseFloat(colMap.get("col_1")));
				ps.setFloat(3, Float.parseFloat(colMap.get("col_2")));
				ps.setString(4, colMap.get("col_3"));
				ps.setInt(5, Integer.parseInt(colMap.get("col_4")));
				ps.setString(6, colMap.get("col_5"));
				ps.setInt(7, Integer.parseInt(colMap.get("col_6")));
				ps.setInt(8, Integer.parseInt(colMap.get("col_7")));
				System.out.println(ps.toString());
				 ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加客户扩展信息 表格格式标准：I:客户id|J:上级公司id|K:上级公司名称|L:营销体系码
	 * 
	 * @param path
	 * @param sheetNum
	 */
	public void insertStoreExt(String path, int sheetNum) {
		try {
			String hql = "insert into T_STORE_EXT(store_id,CHR1004)VALUES(?,?);"; // 客户扩展信息
			PreparedStatement ps = this.GetConn().prepareStatement(hql);
			OpExcel opExcel = new OpExcel();
			Map<String, Map<String, String>> rowMap = opExcel.readManyExcel(path, sheetNum, 8, 11);
			for (int i = 1; i <= rowMap.size(); i++) {
				Map<String, String> colMap = rowMap.get("row_" + i);
				ps.setInt(1, Integer.parseInt(colMap.get("col_8")));
				ps.setString(2, colMap.get("col_11"));
				System.out.println(ps.toString());
//				 ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---the method end---");
	}

	/**
	 * 添加未知客户联系人 表格格式标准：I:客户id
	 * 
	 * @param path
	 * @param sheetNum
	 */
	public void insertStoreLinkman(String path, int sheetNum) {
		try {
			String sql = "insert into T_LINKMAN(delete_status,linkman_name,telephone,store_id,create_user_id,create_time)values(1,'未知','未知',?,90,'2016-12-13 19:00');";
			PreparedStatement ps = this.GetConn().prepareStatement(sql);
			OpExcel opExcel = new OpExcel();
			Map<String, String> map = opExcel.readExcel(path, sheetNum, 8);
			for (int i = 1; i <= map.size(); i++) {
				ps.setInt(1, Integer.parseInt(map.get("data_" + i)));
				System.out.println(ps.toString());
//				 ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---insertStoreLinkman method end---");
	}

	/**
	 * 添加未知客户联系人 表格格式标准：O:客户id|P:联系人姓名|Q:联系人电话
	 * 
	 * @param path
	 * @param sheetNum
	 */
	public void insertStoreLinkmanHas(String path, int sheetNum) {
		try {
			String sql = "insert into T_LINKMAN(delete_status,linkman_name,telephone,store_id,create_user_id,create_time)values(1,?,?,?,90,'2016-12-13 19:00');";
			PreparedStatement ps = this.GetConn().prepareStatement(sql);
			OpExcel opExcel = new OpExcel();
			Map<String, Map<String, String>> rowMap = opExcel.readManyExcel(path, sheetNum, 14, 16);
			for (int i = 1; i <= rowMap.size(); i++) {
				Map<String, String> colMap = rowMap.get("row_" + i);
				ps.setString(1, colMap.get("col_15"));
				ps.setString(2, colMap.get("col_16"));
				ps.setInt(3, Integer.parseInt(colMap.get("col_14")));
				System.out.println(ps.toString());
//				 ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---insertStoreLinkmanHas method end---");
	}

	/**
	 * 客户-业务员人关系 T_STORE_USER 表格格式标准 : M:业务员id|O:客户id
	 * 
	 * @param path
	 * @param sheetNum
	 */
	public void insertStoreUser(String path, int sheetNum) {
		try {
			// 客户业务员
			String sql = "insert into T_STORE_USER(store_id,user_id) values(?,?);";
			PreparedStatement ps = this.GetConn().prepareStatement(sql);
			OpExcel opExcel = new OpExcel();
			Map<String, Map<String, String>> rowMap = opExcel.readManyExcel(path, sheetNum, 12, 14);
			for (int i = 1; i <= rowMap.size(); i++) {
				Map<String, String> colMap = (rowMap.get("row_" + i));
				ps.setInt(1, Integer.parseInt(colMap.get("col_14")));
				ps.setInt(2, Integer.parseInt(colMap.get("col_12")));
				System.out.println(ps.toString());
//				 ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---insertStoreUser method end---");
	}

	/**
	 * 更新客户-联系:T_STORE 表格格式标准：N:联系人id|O:客户id
	 * 
	 * @param path
	 * @param sheetNum
	 */
	public void updateStoreLinkman(String path, int sheetNum) {
		try {
			String sql = "update T_STORE set LINKMAN_ID=? where ID=?;";
			PreparedStatement ps = this.GetConn().prepareStatement(sql);
			OpExcel opExcel = new OpExcel();
			Map<String, Map<String, String>> rowMap = opExcel.readManyExcel(path, sheetNum, 13, 14);
			for (int i = 1; i <= rowMap.size(); i++) {
				Map<String, String> colMap = (rowMap.get("row_" + i));
				ps.setInt(1, Integer.parseInt(colMap.get("col_13")));
				ps.setInt(2, Integer.parseInt(colMap.get("col_14")));
				System.out.println(ps.toString());
//				 ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---updateStoreLinkman method end---");
	}

	/**
	 * 删除多余客户
	 * 
	 * @param path
	 * @param sheetNum
	 * @param storenameCol
	 */
	public void deleteStore(String path, int sheetNum,int storenameCol) {
		try {
			String sql = "update T_STORE set DELETE_STATUS=0 where store_name = ?;";
			PreparedStatement ps = this.GetConn().prepareStatement(sql);
			OpExcel opExcel = new OpExcel();
			Map<String, String> map = opExcel.readExcel(path, sheetNum, storenameCol);
			System.out.println();
			for (int i = 1; i <= map.size(); i++) {
				ps.setString(1, map.get("data_"+i));
				System.out.println(ps.toString());
//				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增用户 表格顺序标准格式：A:中文名|B:电话|C:登录名|D:大区id|E:工号|F:职务id|G:上级id
	 * 
	 * @param path
	 *            文件路径
	 * @param sheetNum
	 *            sheet位置（从0开始算）
	 */
	public void insertUserInfor(String path, int sheetNum) {
		try {
			String sql = "insert into T_USER(delete_status,employee_name,password,telephone,username,department_id,salt,jobcode,status_id,is_bind_phone,is_deparment_leader,post_id,company_id,PARENT_USER_ID)"
					+ "values(1,?,'cd7c47fc69723d57b59be3d3f972c71b902fbd78',?,?,?,'ba6ac237560c4022',?,1,2,2,?,49,?);";
			PreparedStatement ps = this.GetConn().prepareStatement(sql);
			OpExcel opExcel = new OpExcel();
			Map<String, Map<String, String>> rowMap = opExcel.readManyExcel(path, sheetNum, 0, 6);
			for (int i = 1; i <= rowMap.size(); i++) {
				Map<String, String> colMap = rowMap.get("row_" + i);
				ps.setString(1, colMap.get("col_0"));// 中文名
				ps.setString(2, colMap.get("col_1"));// 电话
				ps.setString(3, colMap.get("col_2"));// 登录名
				ps.setInt(4, Integer.parseInt(colMap.get("col_3")));// 大区
				ps.setString(5, colMap.get("col_4"));// 工号
				ps.setInt(6, Integer.parseInt(colMap.get("col_5")));// 职务 代表
				ps.setInt(7, Integer.parseInt(colMap.get("col_6")));// 上级领导
				System.out.println(ps.toString());
				// ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---insertUserInfor method end---");
	}

	/**
	 * 添加用户角色 表格格式标准：H:UserId|G:RoleId
	 * 
	 * @param path
	 *            文件路径
	 * @param sheetNum
	 *            sheet位置（从0开始算）
	 */
	public void insertUserRole(String path, int sheetNum) {
		try {
			String sql = " insert into T_USER_ROLE (user_id,role_id)values(?,?);";
			PreparedStatement ps = this.GetConn().prepareStatement(sql);
			OpExcel opExcel = new OpExcel();
			Map<String, Map<String, String>> rowMap = opExcel.readManyExcel(path, sheetNum, 7, 8);
			for (int i = 1; i <= rowMap.size(); i++) {
				Map<String, String> colMap = rowMap.get("row_" + i);
				ps.setInt(1, Integer.parseInt(colMap.get("col_7")));// userID
				ps.setInt(2, Integer.parseInt(colMap.get("col_8")));// roleID
				System.out.println(ps.toString());
				// ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---insertUserRole method end---");
	}

	/**
	 * 根据客户名称和业务员名字查询客户id（确认客户的业务员有没有调整）
	 * 
	 * @param path
	 * @param sheetNum
	 */
	public void getStoreIdByNames(String path, int sheetNum) {
		try {
			String sql = "select s.id from T_STORE s  left join T_STORE_USER su on su.STORE_ID=s.ID   left join T_USER u on u.ID=su.USER_ID "
					+ " where s.STORE_NAME=? and u.EMPLOYEE_NAME=? and  s.delete_status=1;";
			PreparedStatement ps = this.GetConn().prepareStatement(sql);
			OpExcel opExcel = new OpExcel();
			Map<String, Map<String, String>> rowMap = opExcel.readManyExcel(path, sheetNum, 11, 16);

			Map<String, String> mapId = new HashMap<>();
			for (int i = 1; i <= rowMap.size(); i++) {
				Map<String, String> colMap = rowMap.get("row_" + i);
				ps.setString(1, colMap.get("col_16"));// 客户名称
				ps.setString(2, colMap.get("col_11"));// 业务员名称
				rs = ps.executeQuery();
				String id = "";
				while (rs.next()) {
					id += "***" + rs.getString(1);
					// System.out.println("****"+id);
				}
				mapId.put("data_" + i, id);
			}
			opExcel.writeExcel(sheetNum, 18, path, mapId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---getStoreIdByNames method end---");
	}

	public static void main(String[] args) throws Exception {
		JDBC JDBC = new JDBC();
		String path = "C:/Users/luco/Desktop/外勤系统/数据调整/合作医疗-Store.xlsx";
		//Step 1 添加客户基本信息
		JDBC.insertStoreInfor(path, 7);
		//Step 2 获取到客户id更新到表格
		//Step 3添加客户扩展信息
//		JDBC.insertStoreExt(path, 7);
		//Step 4添加客户联系人
//		JDBC.insertStoreLinkmanHas(path, 6);
//		JDBC.insertStoreLinkman(path, 7);
		//Step 5获取linkmanid到表格
		//Step 6添加业务员
//		JDBC.insertStoreUser(path, 7);
		//Step 7更新联系人id
//		JDBC.updateStoreLinkman(path, 7);
		
		//删除多余
//		JDBC.deleteStore(path, 8, 8);
		
		//调整客户代表
		//Step 1删除已有
//		JDBC.deleteStore(path, 9, 3);
		//添加store
//		JDBC.insertStoreInfor(path, 9);
		//添加扩展信息
//		JDBC.insertStoreExt(path, 9);
		//添加联系人
//		JDBC.insertStoreLinkmanHas(path, 9);
		//更新linkman--先更新表格
//		JDBC.updateStoreLinkman(path, 9);
		//添加业务员
//		JDBC.insertStoreUser(path, 9);
		
		
	}

}
