package cg_app.repository;

import cg_app.config.DBHelper;

import cg_app.model.SparePartsModel;
import cg_app.service.CarServiceMasterService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class SparePartsRepo extends DBHelper{

	Scanner sc=new Scanner(System.in);
	CarServicingRepo csRepo=new CarServicingRepo();
	CarServiceMasterService csService=new CarServiceMasterService();
	private LinkedHashMap<String, ArrayList<SparePartsModel>> map;
	
	public boolean isAddSpareParts(String spname,float spprice) {
		
		try {

			pstmt = conn.prepareStatement(p.getProperty("SparePartsRepo.isAddSpareParts_q1"));
			pstmt.setString(1, spname);
			pstmt.setFloat(2, spprice);
			int value = pstmt.executeUpdate();

			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is :- " + ex);
			return false;
		}
	}
	
	public boolean isUpdateSparePart(String spname) {
		
		try {
			pstmt=conn.prepareStatement(p.getProperty("SparePartsRepo.isUpdateSparePart_q1"));
			pstmt.setString(1, spname);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("Enter Spare Part updated Price...");
				float sp_price=sc.nextFloat();
			
				pstmt=conn.prepareStatement(p.getProperty("SparePartsRepo.isUpdateSparePart_q2"));
				pstmt.setFloat(1, sp_price);
				pstmt.setString(2, spname);
				int value1=pstmt.executeUpdate();
				
				return value1>0?true:false;
			}
			else {
				System.out.println("Spare Part Name must not be Matched.. :(");
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}	
	}
	
	public ArrayList<SparePartsModel> showSparePart(){
		
		try {
			ArrayList<SparePartsModel> al=new ArrayList<SparePartsModel>();
			pstmt=conn.prepareStatement(p.getProperty("SparePartsRepo.showSparePart.q1"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				SparePartsModel model=new SparePartsModel();
				model.setSp_id(rs.getInt(1));
				model.setSp_name(rs.getString(2));
				model.setSp_price(rs.getLong(3));
				al.add(model);
			}
			return al.size()>0?al:null;
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return null;
		}

	}
	

	//updated
	public boolean isSparePartAssign(int sp_id) {
	    try {
	        // Fetch the vehicle ID based on the vehicle number plate
	        pstmt = conn.prepareStatement(p.getProperty("SparePartsRepo.isSparePartAssign.q1"));
	        pstmt.setString(1, csService.getV_nplate());

	        rs = pstmt.executeQuery();
	        // Check if the vehicle exists in the database
	        if (rs.next()) {
	            int v_id = rs.getInt(1);

	            // ensure the user for confirmation to add the spare part
	            System.out.println("Do you want to assign the spare part to the vehicle? (yes/no): ");
	            String userInput = sc.next();

	            // If the user confirms, add the spare part
	            if (userInput.equalsIgnoreCase("yes")) {
	                pstmt = conn.prepareStatement(p.getProperty("SparePartsRepo.isSparePartAssign.q2"));
	                pstmt.setInt(1, v_id);
	                pstmt.setInt(2, sp_id);
	                int value = pstmt.executeUpdate();
	                return value > 0?true:false;
	            } else {
	                System.out.println("Spare part assignment cancelled.");
	                return false;
	            }
	        } else {
	            System.out.println("Registered number of Vehicle not found in DATABASE... :(");
	            return false;
	        }
	    } catch (Exception ex) {
	        System.out.println("Error is:- " + ex);
	        return false;
	    }
	}

	
	//updated
	public LinkedHashMap<String, ArrayList<SparePartsModel>> showAssignSparePartVehicle() {
	    try {
	        this.map = new LinkedHashMap<String, ArrayList<SparePartsModel>>();
	        
	        pstmt = conn.prepareStatement(p.getProperty("SparePartsRepo.showAssignSparePartVehicle.q1"));
	        pstmt.setString(1, csService.getV_nplate());
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int visit_id = rs.getInt(1);
	            String v_nplate = rs.getString(2);

	            PreparedStatement pstmt1 = conn.prepareStatement(p.getProperty("SparePartsRepo.showAssignSparePartVehicle.q2"));
	            pstmt1.setInt(1, visit_id);
	            ResultSet rs1 = pstmt1.executeQuery();

	            ArrayList<SparePartsModel> al = new ArrayList<SparePartsModel>();
	            while (rs1.next()) {
	                SparePartsModel spmodel = new SparePartsModel();
	                spmodel.setSp_name(rs1.getString(1));
	                spmodel.setSp_price(rs1.getFloat(2));
	                al.add(spmodel);
	            }
	            this.map.put(v_nplate, al);
	        }
	        return this.map;
	    } catch (Exception ex) {
	        System.out.println("Error is:- " + ex);
	        return null;
	    }
	}

}
