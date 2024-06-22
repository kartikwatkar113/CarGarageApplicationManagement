package cg_app.repository;

import java.sql.PreparedStatement;
import java.util.*;
import java.sql.ResultSet;

import cg_app.config.DBHelper;


public class BillingRepo extends DBHelper {
	Scanner sc = new Scanner(System.in);
	
	public boolean showBill(String v_nplate) {
		float ssAmount = 0;
		float spAmount = 0;
		float sssAmount = 0;
		int cust_id = 0;
		String cust_name = "";
		int visit_id = 0;
		try {
			pstmt = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q1"));
			pstmt.setString(1, v_nplate);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cust_id = rs.getInt(1);
				cust_name = rs.getString(2);
				visit_id = rs.getInt(5);
			}
				PreparedStatement pstmt5 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q7"));
				pstmt5.setString(1, cust_name);
				ResultSet rs5=pstmt5.executeQuery();
				int count=0;
				if(rs5.next()) {
					count=rs5.getInt(2);
					
				}
				if(count>3) {
					
					System.out.println("=======================================================================================================");
					System.out.printf("%-20s %-20s %-30s %-20s %-10s\n", "Customer ID", "Customer Name",
							"Registered Vehicle Number", "Vehicle Model", "Visit ID");
					System.out.println("=======================================================================================================");
					System.out.printf("%-20d %-20s %-30s %-20s %-10d\n", cust_id, cust_name, rs.getString(3),
							rs.getString(4), visit_id);
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");

					PreparedStatement pstmt1 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q2"));
					pstmt1.setInt(1, visit_id);
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						System.out.println("Main Services:-");
						System.out.println("=======================================================================================================");
						System.out.printf("%s\n", rs1.getString(1));
						System.out.println(
								"-------------------------------------------------------------------------------------------------------");

						PreparedStatement pstmt2 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q3"));
						pstmt2.setString(1, rs1.getString(1));
						ResultSet rs2 = pstmt2.executeQuery();
						System.out.println("Sub Services In Main Services:-");
						System.out.println("=======================================================================================================");
						System.out.printf("%-80s %-10s\n", "Sub Service Name", "Prices");
						System.out.println("=======================================================================================================");
						while (rs2.next()) {
							System.out.printf("%-80s %-10.2f\n", rs2.getString(1), rs2.getFloat(2));
							ssAmount += rs2.getFloat(2);
						}
					}

					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println("Spare Parts List:-");
					System.out.println("=======================================================================================================");
					System.out.printf("%-80s %-10s\n", "Spare Parts Name", "Prices");
					System.out.println("=======================================================================================================");
					PreparedStatement pstmt3 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q4"));
					pstmt3.setInt(1, visit_id);
					ResultSet rs3 = pstmt3.executeQuery();

					while (rs3.next()) {
						System.out.printf("%-80s %-10.2f\n", rs3.getString(1), rs3.getFloat(2));
						spAmount += rs3.getFloat(2);
					}

					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println("Sub Services (if Any)");
					System.out.println("=======================================================================================================");
					System.out.printf("%-80s %-10s\n", "Sub Service Name", "Prices");
					System.out.println("=======================================================================================================");
					PreparedStatement pstmt4 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q5"));
					pstmt4.setInt(1, visit_id);
					ResultSet rs4 = pstmt4.executeQuery();
					while (rs4.next()) {
						System.out.printf("%-80s %-10.2f\n", rs4.getString(1), rs4.getFloat(2));
						sssAmount += rs4.getFloat(2);
					}
//				}

				System.out.println("=======================================================================================================");
				System.out.printf("Total Bill Amount: \n%.2f RS\n", (ssAmount + spAmount + sssAmount));
				System.out.printf("\nTotal Bill Amount After 10% Discount: \n%.2f RS\n", ((ssAmount + spAmount + sssAmount)-((ssAmount + spAmount + sssAmount)*10/100)));
				System.out.println("=======================================================================================================");

					
				}else {
					
					System.out.println("=======================================================================================================");
					System.out.printf("%-20s %-20s %-30s %-20s %-10s\n", "Customer ID", "Customer Name",
							"Registered Vehicle Number", "Vehicle Model", "Visit ID");
					System.out.println("=======================================================================================================");
					System.out.printf("%-20d %-20s %-30s %-20s %-10d\n", cust_id, cust_name, rs.getString(3),
							rs.getString(4), visit_id);
					System.out.println(
							"-------------------------------------------------------------------------------------------------------");

					PreparedStatement pstmt1 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q2"));
					pstmt1.setInt(1, visit_id);
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						System.out.println("Main Services:-");
						System.out.println("=======================================================================================================");
						System.out.printf("%s\n", rs1.getString(1));
						System.out.println(
								"-------------------------------------------------------------------------------------------------------");

						PreparedStatement pstmt2 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q3"));
						pstmt2.setString(1, rs1.getString(1));
						ResultSet rs2 = pstmt2.executeQuery();
						System.out.println("Sub Services In Main Services:-");
						System.out.println("=======================================================================================================");
						System.out.printf("%-80s %-10s\n", "Sub Service Name", "Prices");
						System.out.println("=======================================================================================================");
						while (rs2.next()) {
							System.out.printf("%-80s %-10.2f\n", rs2.getString(1), rs2.getFloat(2));
							ssAmount += rs2.getFloat(2);
						}
					}

					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println("Spare Parts List:-");
					System.out.println("=======================================================================================================");
					System.out.printf("%-80s %-10s\n", "Spare Parts Name", "Prices");
					System.out.println("=======================================================================================================");
					PreparedStatement pstmt3 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q4"));
					pstmt3.setInt(1, visit_id);
					ResultSet rs3 = pstmt3.executeQuery();

					while (rs3.next()) {
						System.out.printf("%-80s %-10.2f\n", rs3.getString(1), rs3.getFloat(2));
						spAmount += rs3.getFloat(2);
					}

					System.out.println(
							"-------------------------------------------------------------------------------------------------------");
					System.out.println("Sub Services (if Any)");
					System.out.println("=======================================================================================================");
					System.out.printf("%-80s %-10s\n", "Sub Service Name", "Prices");
					System.out.println("=======================================================================================================");
					PreparedStatement pstmt4 = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q5"));
					pstmt4.setInt(1, visit_id);
					ResultSet rs4 = pstmt4.executeQuery();
					while (rs4.next()) {
						System.out.printf("%-80s %-10.2f\n", rs4.getString(1), rs4.getFloat(2));
						sssAmount += rs4.getFloat(2);
					}
//				}

				System.out.println("=======================================================================================================");
				System.out.printf("Total Bill Amount: \n%.2f RS\n", (ssAmount + spAmount + sssAmount));
				System.out.println("=======================================================================================================");

					
				}
				
				
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Do you Want To Add Bill In Database Enter yes or no:-");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			String ss = sc.next();
			if (ss.equals("yes")) {
				try {
					System.out.println("Enter the Date For Bill-");
					String date = sc.next();
					pstmt = conn.prepareStatement(p.getProperty("BillingRepo.showBill_q6"));
					pstmt.setInt(1, cust_id);
					pstmt.setString(2, cust_name);
					pstmt.setInt(3, visit_id);
					pstmt.setString(4, date);
					pstmt.setFloat(5, (ssAmount + spAmount + sssAmount));
					int value = pstmt.executeUpdate();
					if (value > 0) {
						System.out.println("Bill Added in Database successfully.........");
					} else {
						System.out.println("Some Problem Adding Bill in Database.... ");
					}
					
					
				} catch (Exception ex) {
					System.out.println("Error is:-" + ex);
				}
			} else {
				System.out.println("Thank you.........");
			}
		} catch (Exception ex) {
			System.out.println("Error is:- " + ex);
		}
		return false;
	}

	public void showTotalBillDatewise() {
		try {
			pstmt = conn.prepareStatement(p.getProperty("BillingRepo.showTotalBillDatewise_q1"));
			rs = pstmt.executeQuery();
			System.out.println("===================================================================================");
			System.out.printf("%-10s %-12s %-20s %-10s %-14s %-11s\n", "Bill ID", "Customer ID", "Customer Name",
					"Visit ID", "Billing Date", "Total Bill");
			System.out.println("===================================================================================");

			while (rs.next()) {
				int B_id = rs.getInt(1);
				int cust_id = rs.getInt(2);
				String B_cust_name = rs.getString(3);
				int visit_id = rs.getInt(4);
				String B_date = rs.getString(5);
				float Total_bill = rs.getFloat(6);
				System.out.printf("%-10d %-12d %-20s %-10d %-14s %-11.2f\n", B_id, cust_id, B_cust_name, visit_id,
						B_date, Total_bill);
				System.out
						.println("-----------------------------------------------------------------------------------");
			}
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
		}
		System.out.println("===================================================================================");
	}

	public void showLastFourMonthBill() {
		float totoalFourMonthBill=0;
		try {
			pstmt = conn.prepareStatement(p.getProperty("BillingRepo.showLastFourMonthBill_q1"));
			rs = pstmt.executeQuery();
			System.out.println("===================================================================================");
			System.out.printf("%-10s %-12s %-20s %-10s %-14s %-11s\n", "Bill ID", "Customer ID", "Customer Name",
					"Visit ID", "Billing Date", "Total Bill");
			System.out.println("===================================================================================");

			while (rs.next()) {
				int B_id = rs.getInt(1);
				int cust_id = rs.getInt(2);
				String B_cust_name = rs.getString(3);
				int visit_id = rs.getInt(4);
				String B_date = rs.getString(5);
				float Total_bill = rs.getFloat(6);
				totoalFourMonthBill+=rs.getFloat(6);
				System.out.printf("%-10d %-12d %-20s %-10d %-14s %-11.2f\n", B_id, cust_id, B_cust_name, visit_id,
						B_date, Total_bill);
				System.out
						.println("-----------------------------------------------------------------------------------");
				
			}
			System.out.println("===================================================================================");
			System.out.printf("Total Bill Amount of Last 4 months: \n%.2f RS\n", totoalFourMonthBill," Rs");
			System.out.println("===================================================================================");
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
		}
	}
	
	public void countMonthWise() {

		try {
		    pstmt = conn.prepareStatement(p.getProperty("BillingRepo.countMonthWise_q1"));
		    rs = pstmt.executeQuery();
		    System.out.println("==================================================================");
		    System.out.printf("%-45s %-25s\n", "Year-Month", "Number of Customer's");
		    System.out.println("==================================================================");

		    while (rs.next()) {
		        String year_month = rs.getString(1);
		        int cust_count = rs.getInt(2);

		        System.out.printf("%-50s %-25d\n", year_month, cust_count);
		        System.out.println("------------------------------------------------------------------");
		    }

		} catch (Exception ex) {
		    System.out.println("Error is" + ex);
		}
		System.out.println("==================================================================");
	}
	
	public void custListYearMonthWise(int year,int m_num) {
		
		try {
			

			pstmt = conn.prepareStatement(p.getProperty("BillingRepo.custListYearMonthWise_q1"));
			pstmt.setInt(1, year);
			pstmt.setInt(2, m_num);
			rs = pstmt.executeQuery();

				System.out.println("===================================================================================");
				System.out.printf("%-10s %-12s %-20s %-10s %-14s %-11s\n", "Bill ID", "Customer ID", "Customer Name",
						"Visit ID", "Billing Date", "Total Bill");
				System.out.println("===================================================================================");
				while (rs.next()) {
					int B_id = rs.getInt(1);
					int cust_id = rs.getInt(2);
					String B_cust_name = rs.getString(3);
					int visit_id = rs.getInt(4);
					String B_date = rs.getString(5);
					float Total_bill = rs.getFloat(6);
					System.out.printf("%-10d %-12d %-20s %-10d %-14s %-11.2f\n", B_id, cust_id, B_cust_name, visit_id,
							B_date, Total_bill);
					System.out
							.println("-----------------------------------------------------------------------------------");
				}
				System.out.println("===================================================================================");	
		
		}
			catch (Exception ex) {
				System.out.println("Error is" + ex);
			}

	}
	
	public void yearWiseCustList(int year) {
		
		try {
			pstmt = conn.prepareStatement(p.getProperty("BillingRepo.custListYearWise_q1"));
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();

				System.out.println("===================================================================================");
				System.out.printf("%-10s %-12s %-20s %-10s %-14s %-11s\n", "Bill ID", "Customer ID", "Customer Name",
						"Visit ID", "Billing Date", "Total Bill");
				System.out.println("===================================================================================");

				while (rs.next()) {
					int B_id = rs.getInt(1);
					int cust_id = rs.getInt(2);
					String B_cust_name = rs.getString(3);
					int visit_id = rs.getInt(4);
					String B_date = rs.getString(5);
					float Total_bill = rs.getFloat(6);
					System.out.printf("%-10d %-12d %-20s %-10d %-14s %-11.2f\n", B_id, cust_id, B_cust_name, visit_id,
							B_date, Total_bill);
					System.out
							.println("-----------------------------------------------------------------------------------");
				}
				System.out.println("===================================================================================");
			 
			
			}
			catch (Exception ex) {
				System.out.println("Error is" + ex);
			}  

	}
	
	public void discountOnBill() {
		
	}

}
