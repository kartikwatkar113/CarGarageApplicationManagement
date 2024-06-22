package cg_app.service;

import java.util.*;
import cg_app.model.SeviceMasterModel;
import cg_app.model.SubServiceModel;
import cg_app.repository.ServiceMasterRepo;
import cg_appInputMismatched.InputMismatchedClass;

public class SerMasterService {
	
	Scanner sc=new Scanner(System.in);
	ServiceMasterRepo sRepo=new ServiceMasterRepo();
	InputMismatchedClass ims=new InputMismatchedClass();
	
	public void addServiceName() {
		System.out.println("Enter the Service name:");
		String s_name=sc.nextLine();
		SeviceMasterModel sermodel=new SeviceMasterModel(s_name);
		boolean b=sRepo.isAddService(sermodel);
		
		if(b) {
			System.out.println("Service Added Successfully.....");
		}else {
			System.out.println("Some problem there adding to Service.....");
		}
		
	}
	
	
	public void addSubService() {
		boolean exit=false;
		do {
			System.out.println("------------------------");
			System.out.println("1. Do you want to Add Bulk Sub Services...");
			System.out.println("2. Add Individual Sub Services...");
			System.out.println("3. Exit...");
			System.out.println("------------------------");
			
			int opt=ims.checkInput();
			switch(opt) {
			
				case 1:{
					if(sRepo.isAddBulkSubService()) {
						System.out.println("Sub Service Bulk Data Added Successfully... :)");
					}
					else {
						System.out.println("There's Some Problem while Adding Sub Services Bulk Data... :(");
					}
					
				}
				break;
				
				case 2:{
					sc.nextLine();
					System.out.println("Enter Sub Service Name:- ");
					String ssname=sc.nextLine();
					
					System.out.println("Enter Sub Service Price:- ");
					float ssprice=sc.nextFloat();
					
					if(sRepo.isAddSubService(ssname,ssprice)) {
						System.out.println("Sub Servic Data Added Successfully... :)");
					}
					else {
						System.out.println("There's Some Problem while Adding Sub Service... :(");
					}
				}
				break;
				
				case 3:{
					exit=true;
				}
				break;
			}
			
		}while(!exit);
		
	}
	
	public void showSubService() {
	    ArrayList<SubServiceModel> al = sRepo.showSubService();
	    
	    if (al != null) {
	        System.out.println("==========================================================================");
	        System.out.printf("%-18s  %-35s  %-15s\n", "SubService ID", "SubService Name", "SubService Price");
	        System.out.println("==========================================================================");

	        al.forEach((m) -> {
	            System.out.printf("%-18d  %-35s  %-15.2f\n", m.getSs_id(), m.getSs_name(), m.getSs_price());
	        });

	        System.out.println("==========================================================================");
	    } else {
	        System.out.println("There's no Customer Data Found in DATABASE... :(");
	    }
	}

	
	public void showMainService() {
		LinkedHashMap<String, ArrayList<SubServiceModel>> map = sRepo.showMainService();
		Set<Map.Entry<String, ArrayList<SubServiceModel>>> s=map.entrySet();
		int num=1;
		for(Map.Entry<String, ArrayList<SubServiceModel>> m:s) {
			System.out.println("Main Service Name:- "+(num++)+"\t"+m.getKey()+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			ArrayList<SubServiceModel> al=m.getValue();
			System.out.println("Sub Services:-");
			System.out.println("====================================================================");
			System.out.printf("%-55s %-10s\n", "Sub Service Name", "Prices");
			System.out.println("====================================================================");
			for(SubServiceModel ssmodel:al) {
				System.out.printf("%-55s %-10.2f\n", ssmodel.getS_name(), ssmodel.getSs_price());
			}
			System.out.println("====================================================================");
		}
		
	}
	
	
	public void priceUpdation() {
		boolean exit=false;
		do{
			System.out.println("------------------------");
			System.out.println("1. Price Updation over Sub Services...");
			System.out.println("2. Exit...");
			System.out.println("------------------------");
		
			int opt=ims.checkInput();
			switch(opt) {
			
				case 1:{
					sc.nextLine();
					System.out.println("Enter Sub Service Name:- ");
					String ss_name=sc.nextLine();
					if(sRepo.isUpdatePrice(ss_name)) {
						System.out.println("Price Updated Successfully in Sub Services... :)");
					}
					else {
						System.out.println("There's Some Problem while Updating Sub Services Price... :(");
					}
					
				}
				break;
			
				case 2:{
					exit=true;
				}
				break;
			}
		
			sc.close();
		}while(!exit);
	}
}
