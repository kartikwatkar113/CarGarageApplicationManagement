package cg_app.repository;

import java.util.ArrayList;


import cg_app.config.DBHelper;
import cg_app.model.TechnicianMasterModel;

public class TechnicianMasterRepo extends DBHelper{

	public boolean isAddTechnician(TechnicianMasterModel tmodel) {
		
		try {
			pstmt=conn.prepareStatement(p.getProperty("TechnicianMasterRepo.isAddTechnician_q1"));
			pstmt.setString(1, tmodel.getT_name());
			pstmt.setLong(2, tmodel.getT_contact());
			int value=pstmt.executeUpdate();
			return value>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}	
	}
	
	public boolean isDeleteTechnician(String name, int id) {
		
		try {
			pstmt=conn.prepareStatement(p.getProperty("TechnicianMasterRepo.isDeleteTechnician_q1"));
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pstmt=conn.prepareStatement(p.getProperty("TechnicianMasterRepo.isDeleteTechnician_q2"));
				pstmt.setInt(1,id);
				int value1=pstmt.executeUpdate();
				
				return value1>0?true:false;
			}
			else {
				System.out.println("Technician Id and Name must not be Matched.. :(");
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}
	}
	
	public ArrayList<TechnicianMasterModel> showTechnician(){
		
		try {
			ArrayList<TechnicianMasterModel> t_al=new ArrayList<TechnicianMasterModel>();
			pstmt=conn.prepareStatement(p.getProperty("TechnicianMasterRepo.showTechnician_q1"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				TechnicianMasterModel tmodel=new TechnicianMasterModel();
				tmodel.setT_id(rs.getInt(1));
				tmodel.setT_name(rs.getString(2));
				tmodel.setT_contact(rs.getLong(3));
				t_al.add(tmodel);
			}
			return t_al.size()>0?t_al:null;
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return null;
		}

	}
}
