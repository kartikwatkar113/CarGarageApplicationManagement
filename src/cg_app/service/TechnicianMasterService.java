package cg_app.service;
import java.util.*;

import cg_app.model.TechnicianMasterModel;
import cg_app.repository.TechnicianMasterRepo;

public class TechnicianMasterService {
	Scanner sc=new Scanner(System.in);
	TechnicianMasterRepo trepo=new TechnicianMasterRepo();
	
	public void addTechnician() {
		
		System.out.println("Enter Technician Name:- ");
		String t_name=sc.nextLine();
		
		System.out.println("Enter Technician Contact:- ");
		
		long t_contact=sc.nextLong();
		sc.nextLine();
		TechnicianMasterModel tmodel=new TechnicianMasterModel(t_name,t_contact);
		
		boolean b=trepo.isAddTechnician(tmodel);
		if(b) {
			System.out.println("Technician Details Added Successfully... :");
		}
		else {
			System.out.println("There's Some Problem while adding Technician Details... :(");
		}
	}
	
	public void deleteTechnician() {
		
		System.out.println("Enter Technician Id and Name for Deletion...");
		int id=sc.nextInt();
		sc.nextLine();
		String name=sc.nextLine();
		
		boolean b=trepo.isDeleteTechnician(name,id);
		if(b) {
			System.out.println("Technician Deleted Successfully... :)");
		}
		else {
			System.out.println("There's Some Problem while Deleting Technician Details... :(");
		}
	}
	

	public void showTechnician() {
	    ArrayList<TechnicianMasterModel> t_al = trepo.showTechnician();

	    if (t_al != null && !t_al.isEmpty()) {
	        System.out.println("==================================================================");
	        System.out.printf("%-20s %-25s %-20s\n", "Technician ID", "Technician Name", "Technician Contact");
	        System.out.println("==================================================================");

	        for (TechnicianMasterModel m : t_al) {
	            System.out.printf("%-20d %-25s %-20s\n", m.getT_id(), m.getT_name(), m.getT_contact());
	        }

	        System.out.println("==================================================================");

	    } else {
	        System.out.println("There's no Technician Data Found in DATABASE... :(");
	    }
	}

}
