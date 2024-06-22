package cg_app.service;

import cg_app.customException.InvalidContactNumberException;
import cg_app.customException.InvalidCustomerIdException;
import cg_app.customException.InvalidCustomerNameException;
import cg_app.model.CustomerMasterModel;
import cg_app.repository.CustomerMasterRepo;
import java.util.*;
public class CustomerMasterService {
	Scanner sc=new Scanner(System.in);
	CustomerMasterRepo cmrepo=new CustomerMasterRepo();
	
	public void addCustomer() throws InvalidCustomerNameException,InvalidContactNumberException{
		System.out.println("Enter Customer Name:- ");
		String name=sc.nextLine();
		validateCustomerName(name);
		System.out.println("Enter Customer Contact");
		String contact=sc.next();
		validateContactNumber(contact);
		sc.nextLine();
		System.out.println("Enter Customer Address");
		String address=sc.nextLine();
		
		CustomerMasterModel cmmodel=new CustomerMasterModel(name,contact,address);
		
		boolean b=cmrepo.isAddCustomer(cmmodel);
		
		if(b) {
			System.out.println("Customer Details Added Successfully... :");
		}
		else {
			System.out.println("There's Some Problem while adding Customer Details... :(");
		}
	
	}
	
	public void updateCustomer() throws InvalidCustomerIdException, InvalidCustomerNameException{
		
		System.out.println("Enter Id and Name for Updation...");
		int id=sc.nextInt();
		validateCustomerId(id);
		sc.nextLine();
		String name=sc.nextLine();
		validateCustomerName(name);
		
		boolean b=cmrepo.isUpdateCustomer(name,id);
		if(b) {
			System.out.println("Customer Updated Successfully... :)");
		}
		else {
			System.out.println("There's Some Problem while Updating Customer Details... :(");
		}
	}
	
	public void deleteCustomer() throws InvalidCustomerIdException, InvalidCustomerNameException {
		
		System.out.println("Enter Id and Name for Deletion...");
		int id=sc.nextInt();
		validateCustomerId(id);
		sc.nextLine();
		String name=sc.nextLine();
		validateCustomerName(name);
		boolean b=cmrepo.isDeleteCustomer(name,id);
		if(b) {
			System.out.println("Customer Deleted Successfully... :)");
		}
		else {
			System.out.println("There's Some Problem while Deleting Customer Details... :(");
		}
	}
	
	public void showCustomer() {

	    ArrayList<CustomerMasterModel> al = cmrepo.showCustomer();

	    if (al != null && !al.isEmpty()) {
	        System.out.println("=====================================================================================");
	        System.out.printf("%-15s %-25s %-25s %-20s\n", "Customer ID", "Customer Name", "Customer Contact", "Customer Address");
	        System.out.println("=====================================================================================");

	        for (CustomerMasterModel m : al) {
	            System.out.printf("%-15d %-25s %-25s %-20s\n", m.getCust_id(), m.getCust_name(), m.getCust_contact(), m.getCust_address());
	        }

	        System.out.println("=====================================================================================");

	    } else {
	        System.out.println("There's no Customer Data Found in DATABASE... :(");
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
