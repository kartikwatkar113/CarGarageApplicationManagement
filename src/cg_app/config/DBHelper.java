package cg_app.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBHelper {
	protected DBConfig db=DBConfig.getDBInstance();
	protected Connection conn=DBConfig.getConnection();
	protected PreparedStatement pstmt=DBConfig.getPreparedStatement();
	protected ResultSet rs=DBConfig.getResultSet();
	protected Properties p=DBConfig.getProperties();
	
}
