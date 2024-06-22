package cg_app.repository;

import java.io.BufferedReader;

import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import cg_app.config.DBHelper;
import cg_app.config.PathHelper;
import cg_app.model.SeviceMasterModel;
import cg_app.model.SubServiceModel;

public class ServiceMasterRepo extends DBHelper {

	Scanner sc = new Scanner(System.in);
	private LinkedHashMap<String, ArrayList<SubServiceModel>> map;

	public boolean isAddService(SeviceMasterModel smodel) {
		try {
			pstmt = conn.prepareStatement(p.getProperty("ServiceMasterRepo.isAddService_q1"));
			pstmt.setString(1, smodel.getS_name());
			int value = pstmt.executeUpdate();

			return value > 0 ? true : false;

		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return false;
		}

	}

	public boolean isAddBulkSubService() {

		try {
			FileReader fr = new FileReader(PathHelper.path.concat("SubServices.csv"));
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			int value = 0;
			while ((line = br.readLine()) != null) {
				String data[] = line.split(",");
				pstmt = conn.prepareStatement(p.getProperty("ServiceMasterRepo.isAddBulkSubService.q1"));
				pstmt.setString(1, data[1]);
				pstmt.setString(2, data[2]);
				value = pstmt.executeUpdate();
			}
			br.close();
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is :- " + ex);
			return false;
		}
	}

	public boolean isAddSubService(String ssname, float ssprice) {

		try {

			pstmt = conn.prepareStatement(p.getProperty("ServiceMasterRepo.isAddSubService.q1"));
			pstmt.setString(1, ssname);
			pstmt.setFloat(2, ssprice);
			int value = pstmt.executeUpdate();

			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is :- " + ex);
			return false;
		}

	}

	public ArrayList<SubServiceModel> showSubService() {

		try {
			ArrayList<SubServiceModel> al = new ArrayList<SubServiceModel>();
			pstmt = conn.prepareStatement(p.getProperty("ServiceMasterRepo.showSubService.q1"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SubServiceModel ssmodel = new SubServiceModel();
				ssmodel.setSs_id(rs.getInt(1));
				ssmodel.setSs_name(rs.getString(2));
				ssmodel.setSs_price(rs.getFloat(3));

				al.add(ssmodel);
			}
			return al.size() > 0 ? al : null;
		} catch (Exception ex) {
			System.out.println("Error is:- " + ex);
			return null;
		}

	}

	public LinkedHashMap<String, ArrayList<SubServiceModel>> showMainService() {

		try {
			this.map = new LinkedHashMap<String, ArrayList<SubServiceModel>>();
			pstmt = conn.prepareStatement(p.getProperty("ServiceMasterRepo.showMainService.q1"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int s_id = rs.getInt(1);
				String s_name = rs.getString(2);
				PreparedStatement pstmt1 = conn.prepareStatement(p.getProperty("ServiceMasterRepo.showMainService.q2"));
				pstmt1.setInt(1, s_id);
				ResultSet rs1 = pstmt1.executeQuery();
				ArrayList<SubServiceModel> al = new ArrayList<SubServiceModel>();
				while (rs1.next()) {
					SubServiceModel ssmodel = new SubServiceModel();
					ssmodel.setS_name(rs1.getString(1));
					ssmodel.setSs_price(rs1.getFloat(2));
					al.add(ssmodel);

				}
				this.map.put(s_name, al);
			}
			return this.map;
		} catch (Exception ex) {
			System.out.println("Error is:- " + ex);
			return null;
		}
	}

	public boolean isUpdatePrice(String ss_name) {
		try {
			pstmt = conn.prepareStatement(p.getProperty("ServiceMasterRepo.isUpdatePrice.q1"));
			pstmt.setString(1, ss_name);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				System.out.println("Enter new price for Particular Sub Service:- ");
				float ss_price = sc.nextFloat();
				pstmt = conn.prepareStatement(p.getProperty("ServiceMasterRepo.isUpdatePrice.q2"));
				pstmt.setFloat(1, ss_price);
				pstmt.setString(2, ss_name);
				int value = pstmt.executeUpdate();
				return value > 0 ? true : false;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("Error is:- " + ex);
			return false;
		}

	}

}
