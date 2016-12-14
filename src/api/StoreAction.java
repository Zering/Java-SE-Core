package api;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreAction {
	JDBC jdbc = new JDBC();
	ResultSet rs = null;

	public void getStoreInfor(String storeName) throws SQLException {
		int storeId = 0;
		int districtID = 0; // 省份
		// int userId=0;
		int departId = 0; // 大区
		int daibiaoId = 0;
		String hql = "select ID,DISTRICT_ID,DEPARTMENT_ID  from T_STORE where STORE_NAME='" + storeName + "'";
		rs = jdbc.QueryInfo(hql);
		while (rs.next()) {
			storeId = rs.getInt(1);
			districtID = rs.getInt(2);
			departId = rs.getInt(3);
			String areaHql = "select all_name from T_D_DISTRICT where id=" + districtID; // 省份
			ResultSet areaSet = jdbc.QueryInfo(areaHql);
			while (areaSet.next()) {
				System.out.println("省份---" + areaSet.getString(1));
			}

			String chr004 = "select  CHR1004  from T_STORE_EXT  where STORE_ID=" + storeId;// 营销体系码
			ResultSet chr004Set = jdbc.QueryInfo(chr004);
			while (chr004Set.next()) {
				System.out.println("营销体系码---" + chr004Set.getString(1));
			}

			String daibiaoHql = "select USER_ID from T_STORE_USER  where STORE_ID=" + storeId; // 代表Id
			ResultSet daibiaoHqlSet = jdbc.QueryInfo(daibiaoHql);
			while (daibiaoHqlSet.next()) {
				System.out.println("代表Id---" + daibiaoHqlSet.getInt(1));
				daibiaoId = daibiaoHqlSet.getInt(1);
			}
			String daibiaoInfor = "select EMPLOYEE_NAME,JOBCODE from T_USER where id=" + daibiaoId;
			ResultSet daibiaoInforSet = jdbc.QueryInfo(daibiaoInfor);
			while (daibiaoInforSet.next()) {
				System.out.println("代表姓名---" + daibiaoInforSet.getString(1) + "---工号::" + daibiaoInforSet.getString(2));
			}

			String departInfor = "select NAME from T_DEPARTMENT where id=" + departId; // 大区
			ResultSet departInforSet = jdbc.QueryInfo(departInfor);
			while (departInforSet.next()) {
				System.out.println("大区---" + departInforSet.getString(1));
			}
		}

	}

	public static void main(String[] args) throws SQLException {
		StoreAction biz = new StoreAction();
		biz.getStoreInfor("济南漱玉平民大药房有限公司经四纬七路店");
	}

}
