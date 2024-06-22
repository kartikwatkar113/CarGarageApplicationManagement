package cg_app.service;
import java.util.*;

import cg_app.model.TechnicianMasterModel;
import cg_app.model.VehicleMasterModel;
import cg_app.repository.VehicleMasterRepo;

public class VehicleMasterService {
	Scanner sc=new Scanner(System.in);
	VehicleMasterRepo vrepo=new VehicleMasterRepo();
	TechnicianMasterModel tmodel=new TechnicianMasterModel();
	CustomerMasterService cmservice=new CustomerMasterService();
	TechnicianMasterService tservice=new TechnicianMasterService();
	
	public void addVehicles() {
		
		
		System.out.println("Enter Vehicle Number Plate:- ");
		String v_nplate=sc.nextLine();
		
		if(vrepo.isVehicleRepeat(v_nplate)) {
			System.out.println("Repeated Vehicle Details with Date Added successfully... :)");
		}
		else {
			
			System.out.println("Enter Customer Id to join Vehicle:- ");
			cmservice.showCustomer();
			int cust_id=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Vehicle Model:- ");
			String v_model=sc.nextLine();
			
			System.out.println("Enter Arrival Date:- ");
			String v_adate=sc.nextLine();
			
			tservice.showTechnician();
			System.out.println("Enter Technician Name assign for Servicing:- ");
			String t_name=sc.nextLine();
			VehicleMasterModel vmodel=new VehicleMasterModel(v_model,v_nplate,v_adate);
			
			boolean b=vrepo.isAddVehicle(vmodel,t_name,cust_id);
			
			if(b) {
				System.out.println("Vehicle's Details Added Successfully... :");
			}
			else {
				System.out.println("There's Some Problem while adding Vehicle's Details... :(");
			}
		}
		
		
	}
	
	public void updateVehicles() {
		
		System.out.println("Enter Vehicle Registered Number for Updation...");
		String v_nplate=sc.next();
		
		boolean b=vrepo.isUpdateVehicles(v_nplate);
		if(b) {
			System.out.println("Vehicle's Detail's Updated Successfully... :)");
		}
		else {
			System.out.println("There's Some Problem while Updating Vehicle's Details... :(");
		}
	}
	
	
	public void deleteVehicle() {
		
		System.out.println("Enter Vehicle Registered number for Deletion...");
		
		String v_nplate=sc.next();
		
		boolean b=vrepo.isDeleteVehicle(v_nplate);
		if(b) {
			System.out.println("Vehicle Detail's Deleted Successfully... :)");
		}
		else {
			System.out.println("There's Some Problem while Deleting Vehicle Details... :(");
		}
	}
	
	public void showVehicle() {
	    ArrayList<VehicleMasterModel> al = vrepo.showVehicle();

	    if (al != null && !al.isEmpty()) {
	        System.out.println("================================================================================================");
	        System.out.printf("%-10s%-20s%-25s%-25s%-20s%n", "Visit ID", "Vehicle Model", "Vehicle NumberPlate", "Vehicle ArrivalDate", "Technician Name");
	        System.out.println("================================================================================================");
	        for (VehicleMasterModel m : al) {
	            System.out.printf("%-10d%-20s%-25s%-25s%-20s%n", m.getV_id(), m.getV_model(), m.getV_number_plate(), m.getV_arrival_date(), m.getT_name());
	        }
	        System.out.println("================================================================================================");
	    } else {
	        System.out.println("There's no Vehicle Data Found in DATABASE... :(");
	    }
	}

	
	public void searchVehicle() {
		
		System.out.println("Enter Vehicle Registered Number");
		String n_plate=sc.nextLine();
		
		ArrayList<VehicleMasterModel> al=vrepo.searchVehicle(n_plate);
		
		if(al!=null) {
			System.out.println("================================================================================================");
	        System.out.printf("%-10s%-20s%-25s%-25s%-20s%n", "Visit ID", "Vehicle Model", "Vehicle NumberPlate", "Vehicle ArrivalDate", "Technician Name");
	        System.out.println("================================================================================================");
			al.forEach((m)-> System.out.printf("%-10d%-20s%-25s%-25s%-20s%n", m.getV_id(), m.getV_model(), m.getV_number_plate(), m.getV_arrival_date(), m.getT_name()));
			System.out.println("================================================================================================");
		}
		else {
			System.out.println("There's no Vehicle Data Found in DATABASE... :(");
		}
	}
}
