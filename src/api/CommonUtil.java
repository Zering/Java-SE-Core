package api;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonUtil {
	JDBC jdbc = new JDBC();
	ResultSet rs = null;

	/**
	 * 通过名称查找Id
	 */
	public String getEntityId(String hql) throws SQLException {
		rs = jdbc.QueryInfo(hql);
		String id = "";
		while (rs.next()) {
			id += "***" + rs.getString(1);
		}
		jdbc.closeCon();
		return id;
	}

	public String getEntityName(String hql) throws SQLException {
		rs = jdbc.QueryInfo(hql);
		String name = "";
		while (rs.next()) {
			name = rs.getString(1);
		}
		jdbc.closeCon();
		return name;
	}

	public static void main(String[] args) throws SQLException {
		CommonUtil common = new CommonUtil();
		String hql = "select  ID,DISTRICT_ID,DEPARTMENT_ID  from T_STORE where STORE_NAME='国药控股甘肃有限公司'";

		System.out.println("---" + common.getEntityId(hql));
	}
}
