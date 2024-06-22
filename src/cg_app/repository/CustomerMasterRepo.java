package cg_app.repository;

import java.util.*;

import cg_app.config.DBHelper;
import cg_app.customException.InvalidContactNumberException;
import cg_app.customException.InvalidCustomerIdException;
import cg_app.customException.InvalidCustomerNameException;
import cg_app.model.CustomerMasterModel;

public class CustomerMasterRepo extends DBHelper{

	Scanner sc=new Scanner(System.in);
	public boolean isAddCustomer(CustomerMasterModel model) {
		
		try{
			
			pstmt=conn.prepareStatement(p.getProperty("CustomerMasterRepo.isAddCustomer_q1"));
			pstmt.setString(1, model.getCust_name());
			pstmt.setString(2, model.getCust_contact());
			pstmt.setString(3, model.getCust_address());
			int value=pstmt.executeUpdate();
			return value>0?true:false;	
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}	
	}
	
	public boolean isUpdateCustomer(String name,int id) {
		
		try {
			pstmt=conn.prepareStatement(p.getProperty("CustomerMasterRepo.isUpdateCustomer_q1"));
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("Enter Customer's updated Name...");
				String cust_name=sc.nextLine();
				validateCustomerName(cust_name);
				System.out.println("Enter Customer's updated Contact...");
				String cust_contact=sc.next();
				validateContactNumber(cust_contact);
				System.out.println("Enter Customer's updated Address...");
				sc.nextLine();
				String cust_address=sc.nextLine();
				
				pstmt=conn.prepareStatement(p.getProperty("CustomerMasterRepo.isUpdateCustomer_q2"));
				pstmt.setString(1, cust_name);
				pstmt.setString(2, cust_contact);
				pstmt.setString(3, cust_address);
				pstmt.setInt(4, id);
				
				int value1=pstmt.executeUpdate();
				
				return value1>0?true:false;
			}
			else {
				System.out.println("Customer's Id and Name must not be Matched.. :(");
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}	
	}
	
	
	public boolean isDeleteCustomer(String name, int id) {
		
		try {
			pstmt=conn.prepareStatement(p.getProperty("CustomerMasterRepo.isDeleteCustomer_q1"));
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pstmt=conn.prepareStatement(p.getProperty("CustomerMasterRepo.isDeleteCustomer_q2"));
				pstmt.setInt(1,id);
				int value1=pstmt.executeUpdate();
				
				return value1>0?true:false;
			}
			else {
				System.out.println("Customer's Id and Name must not be Matched.. :(");
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return false;
		}
	}
	
	public ArrayList<CustomerMasterModel> showCustomer(){
		
		try {
			ArrayList<CustomerMasterModel> al=new ArrayList<CustomerMasterModel>();
			pstmt=conn.prepareStatement(p.getProperty("CustomerMasterRepo.showCustomer_q1"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CustomerMasterModel model=new CustomerMasterModel();
				model.setCust_id(rs.getInt(1));
				model.setCust_name(rs.getString(2));
				model.setCust_contact(rs.getString(3));
				model.setCust_address(rs.getString(4));
				al.add(model);
			}
			return al.size()>0?al:null;
		}
		catch(Exception ex) {
			System.out.println("Error is:- "+ex);
			return null;
		}

	}
	
	private void validateCustomerName(String name) throws InvalidCustomerNameException {
		 if (!name.matches("[a-zA-Z ]+")) {
	         throw new InvalidCustomerNameException("Customer name should contain only alphabets and spaces.");
	     }
	}
	
	private void validateContactNumber(String contact) throws InvalidContactNumberException {
       if (!contact.matches("\\d{10}")) {
           throw new InvalidContactNumberException("Contact number should be 10 digits long and contain only numbers.");
       }
   }
	
	private void validateCustomerId(int id) throws InvalidCustomerIdException {
       if (id <= 0) {
           throw new InvalidCustomerIdException("Customer ID should be a positive number.");
       }
   }
	
}
