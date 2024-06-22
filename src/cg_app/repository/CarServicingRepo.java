package cg_app.repository;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import cg_app.config.DBHelper;

public class CarServicingRepo extends DBHelper{
	private String v_nplate;
	
	public boolean checkVehicle(String v_nplate) {
		this.v_nplate=v_nplate;
		try{
			
			pstmt=conn.prepareStatement(p.getProperty("CarServicingRepo.checkVehicle_q1"));
			pstmt.setString(1, v_nplate);			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}	
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}		
	}
	

	
	public boolean isServiceAdded(int service_id){
		
		try{
			
			pstmt=conn.prepareStatement(p.getProperty("CarServicingRepo.checkVehicle_q1"));
			pstmt.setString(1, v_nplate);
			
			rs=pstmt.executeQuery();
			boolean flag=false;
			while(rs.next()) {
				pstmt=conn.prepareStatement(p.getProperty("CarServicingRepo.isServiceAdded_q1"));
				pstmt.setInt(1, rs.getInt(1));
				pstmt.setInt(2, service_id);
				flag=true;
				int value=pstmt.executeUpdate();
				return value>0?true:false;
			}
			if(!flag) {
				System.out.println("Registered number of Vehilce not found in DATABASE... :(");
				return false;
			}
			else {
				return true;
			}	
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}
		
	}
	
	public boolean isOtherServiceAdd(int ss_id) {
		
		try{
			
			pstmt=conn.prepareStatement(p.getProperty("CarServicingRepo.checkVehicle_q1"));
			pstmt.setString(1, this.v_nplate);
			rs=pstmt.executeQuery();
			boolean flag=false;
			while(rs.next()) {
				pstmt=conn.prepareStatement(p.getProperty("CarServicingRepo.isOtherServiceAdd_q1"));
				pstmt.setInt(1, rs.getInt(1));
				pstmt.setInt(2, ss_id);
				flag=true;
				int value=pstmt.executeUpdate();
				return value>0?true:false;
			}
			if(!flag) {
				System.out.println("Registered number of Vehilce not found in DATABASE... :(");
				return false;
			}
			else {
				return true;
			}	
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}
		
	}

	public void showServices(String v_nplate){
		try {
			pstmt = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q1"));
			pstmt.setString(1, v_nplate);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int visit_id = rs.getInt(5);

				PreparedStatement pstmt1 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q2"));
				pstmt1.setInt(1, visit_id);
				ResultSet rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					System.out.println("Main Services:-");
					System.out.println("========================================================================================");
					System.out.printf("%s\n", rs1.getString(1));
					System.out.println(
							"----------------------------------------------------------------------------------------");

					PreparedStatement pstmt2 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q3"));
					pstmt2.setString(1, rs1.getString(1));
					ResultSet rs2 = pstmt2.executeQuery();
					System.out.println("Sub Services In Main Services:-");
					System.out.println("========================================================================================");
					System.out.printf("%-80s %-10s\n", "Sub Service Name", "Prices");
					System.out.println("========================================================================================");
					while (rs2.next()) {
						System.out.printf("%-80s %-10.2f\n", rs2.getString(1), rs2.getFloat(2));
						
					}
				}

				System.out.println(
						"----------------------------------------------------------------------------------------");
				System.out.println("Sub Services (if Any)");
				System.out.println("========================================================================================");
				System.out.printf("%-80s %-10s\n", "Sub Service Name", "Prices");
				System.out.println("========================================================================================");
				PreparedStatement pstmt4 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q5"));
				pstmt4.setInt(1, visit_id);
				ResultSet rs4 = pstmt4.executeQuery();
				while (rs4.next()) {
					System.out.printf("%-80s %-10.2f\n", rs4.getString(1), rs4.getFloat(2));
					
				}
			}

				} catch (Exception ex) {
					System.out.println("Error is:-" + ex);
				}
		System.out.println("========================================================================================");
		
	}

}
