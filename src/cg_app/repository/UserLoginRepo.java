package cg_app.repository;
import java.util.*;
import cg_app.config.DBHelper;
import cg_app.config.EncryptDecrypt;

public class UserLoginRepo extends DBHelper{
	Scanner sc=new Scanner(System.in);
	
	public boolean isCorrect(String user_n,String pass) {
		
		try {
			pstmt=conn.prepareStatement("select *from login_info where user_name=? and user_pass=?");
			pstmt.setString(1, user_n);
			
			pstmt.setString(2, EncryptDecrypt.encrypt(pass));
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ ex);
			return false;
		}	
		
	}
	
	public boolean isUpdatePass(String user_n) {
		
		try {
			pstmt=conn.prepareStatement("select *from login_info where user_name=?");
			pstmt.setString(1, user_n);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("Enter New Password:- ");
				String pass=sc.next();
				pstmt=conn.prepareStatement("update login_info set user_pass=? where user_name=?");
				pstmt.setString(1, pass);
				pstmt.setString(2, user_n);
				int value=pstmt.executeUpdate();
				if(value>0) {
					return true;
				}
				else {
					return false;
				}
				
			}else {
				return false;
			}
			
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ ex);
			return false;
		}

		
	}
}
