package cg_app.repository;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.*;
import cg_app.config.DBHelper;
import cg_app.model.VehicleMasterModel;
import cg_app.service.CustomerMasterService;
import cg_app.service.TechnicianMasterService;

public class VehicleMasterRepo extends DBHelper{
	
	TechnicianMasterService tservice=new TechnicianMasterService();
	CustomerMasterService cmservice=new CustomerMasterService();
	Scanner sc=new Scanner(System.in);
	static String v_nplate;
	
	
	
	
	public boolean isVehicleRepeat(String v_nplate) {
			this.v_nplate=v_nplate;
		try{
			
			pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q1"));
			pstmt.setString(1, v_nplate);
			
			rs=pstmt.executeQuery();

			if(rs.next()) {
				int cust_id=rs.getInt(1);
				int v_id=rs.getInt(2);
				System.out.println("Your Car Details present Already...");
				System.out.println("Enter Arrival Date for Repeated Vehicle:- ");
				
				String visit_date=sc.next();
				
				sc.nextLine();
				System.out.println("Enter Technician Name for Repeated Vehicle:- ");
				tservice.showTechnician();
				String t_name=sc.nextLine();
				
				PreparedStatement pstmt1=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q2"));
				pstmt1.setString(1, t_name);
				ResultSet rs1=pstmt1.executeQuery();
				int value=0;
				while(rs1.next()) {

					PreparedStatement pstmt2=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q3"));
					pstmt2.setString(1, visit_date);
					pstmt2.setInt(2, v_id);
					pstmt2.setInt(3, rs1.getInt(1));
					value=pstmt2.executeUpdate();

				}
				int value2=0;
				PreparedStatement pstmt3=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q4"));
				pstmt3.setString(1, v_nplate);
				ResultSet rs2=pstmt3.executeQuery();
				if(rs2.next()) {
					PreparedStatement pstmt4=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q5"));
					pstmt4.setInt(1, cust_id);
					pstmt4.setInt(2, rs2.getInt(1));
					value2=pstmt4.executeUpdate();
				}
				return value2>0?true:false;
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
	
	public boolean isAddVehicle(VehicleMasterModel vmodel,String t_name,int cust_id) {
		
		try{
			
			pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q2"));
			pstmt.setString(1, t_name);
			
			rs=pstmt.executeQuery();
			boolean flag=false;
			
			while(rs.next()) {
				int t_id=rs.getInt(1);
				pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isAddVehicle_q1"));
				pstmt.setString(1, vmodel.getV_model());
				pstmt.setString(2, vmodel.getV_number_plate());
				flag=true;
				pstmt.executeUpdate();
				
			//----	
				pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isAddVehicle_q2"));
				pstmt.setString(1, v_nplate);
				
				rs=pstmt.executeQuery();

				if(rs.next()) {
					int v_id=rs.getInt(1);
					pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q3"));
					pstmt.setString(1, vmodel.getV_arrival_date());
					pstmt.setInt(2, v_id);
					pstmt.setInt(3, t_id);
					int value1=pstmt.executeUpdate();
					int value2=0;
					PreparedStatement pstmt2=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isAddVehicle_q3"));
					pstmt2.setString(1, v_nplate);
					ResultSet rs1=pstmt2.executeQuery();
					if(rs1.next()) {
						PreparedStatement pstmt3=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q5"));
						pstmt3.setInt(1, cust_id);
						pstmt3.setInt(2, rs1.getInt(1));
						value2=pstmt3.executeUpdate();
					}
					else {
						System.out.println("Vehicle Not Found... :(");
					}
					
					return value2>0?true:false;
				}
				else {
					return false;
				}
				
				
			//---
				
			}
			if(!flag) {
				System.out.println("Technician not found in DATABASE... :(");
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
	
	
	public boolean isUpdateVehicles (String nplate) {
		
		try {
			pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q4"));
			pstmt.setString(1, nplate);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int visit_id=rs.getInt(1);
				System.out.println("Enter Customer's updated Vehicle Model...");
				String v_model=sc.nextLine();
				System.out.println("Enter Customer's updated Vehicle Number Plate...");
				String v_nplate=sc.nextLine();
				System.out.println("Enter new Vehicle's assign Technician...");
				String t_name=sc.nextLine();
				
				pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q2"));
				pstmt.setString(1, t_name);
				rs=pstmt.executeQuery();
				boolean flag1=false;
				while(rs.next()) {
					pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isUpdateVehicles_q1"));
					pstmt.setString(1, v_model);
					pstmt.setString(2, v_nplate);
					pstmt.setInt(3, rs.getInt(1));
					pstmt.setInt(4, visit_id);
					
					
					flag1=true;
					int value=pstmt.executeUpdate();
					return value>0?true:false;
				}
				if(!flag1) {
					System.out.println("Technician not found in DATABASE... :(");
					return false;
				}
				else {
					return true;
				}
		
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
	
	
	public boolean isDeleteVehicle(String v_nplate) {
		
		try {
			pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.isVehicleRepeat_q4"));
			pstmt.setString(1, v_nplate);
			rs=pstmt.executeQuery();
			boolean flag2=false;
			while(rs.next()) {
				pstmt=conn.prepareStatement("delete from visit_vehicle where visit_id=?");
				pstmt.setInt(1,rs.getInt(1));
				flag2=true;
				
				int value=pstmt.executeUpdate();
				
				return value>0?true:false;
			}
			if(!flag2) {
				System.out.println("Vehicle Registered Number must not be Matched.. :(");
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
	
	
	
	public ArrayList<VehicleMasterModel> showVehicle(){
		
		try {
			ArrayList<VehicleMasterModel> v_al=new ArrayList<VehicleMasterModel>();
			pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.showVehicle_q1"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				VehicleMasterModel vmodel=new VehicleMasterModel();
				vmodel.setV_id(rs.getInt(1));
				vmodel.setV_model(rs.getString(2));
				vmodel.setV_number_plate(rs.getString(3));
				vmodel.setV_arrival_date(rs.getString(4));
				vmodel.setT_name(rs.getString(5));
				v_al.add(vmodel);
			}
			return v_al.size()>0?v_al:null;
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return null;
		}

	}
	
public ArrayList<VehicleMasterModel> searchVehicle(String n_plate){
		
		try {
			ArrayList<VehicleMasterModel> v_al=new ArrayList<VehicleMasterModel>();
			pstmt=conn.prepareStatement(p.getProperty("VehicleMasterRepo.searchVehicle_q1"));
			pstmt.setString(1, n_plate);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				VehicleMasterModel vmodel=new VehicleMasterModel();
				vmodel.setV_id(rs.getInt(1));
				vmodel.setV_model(rs.getString(2));
				vmodel.setV_number_plate(rs.getString(3));
				vmodel.setV_arrival_date(rs.getString(4));
				vmodel.setT_name(rs.getString(5));
				v_al.add(vmodel);
			}
			return v_al.size()>0?v_al:null;
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return null;
		}

	}
	
	
}
