package cg_app.service;
import java.util.*;

import cg_app.model.SubServiceModel;
import cg_app.repository.CarServicingRepo;

public class CarServiceMasterService {
	Scanner sc=new Scanner(System.in);
	SerMasterService smser=new SerMasterService();
	CarServicingRepo cRepo=new CarServicingRepo();
	static String v_nplate;
	
	public boolean checkVehicle(){
		System.out.println("Enter Vehicle Registered Number:- ");
		String v_nplate=sc.next();
		this.v_nplate=v_nplate;
		if(cRepo.checkVehicle(v_nplate)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String getV_nplate() {
		return v_nplate;
	}

	public void ServiceForCar() {
		
		smser.showMainService();
		
		System.out.println("Enter the Service id name ");
		int service_id=sc.nextInt();
		
		
		if(cRepo.isServiceAdded(service_id)) {
			
			System.out.println("Allocating Services to Vehicles Successfully... :)");
		}
		else {
			System.out.println("There's Some Problem while Allocating Service to Vehicle... :(");
		}
		
	}
	
	public void otherServices() {
		smser.showSubService();
		int opt;
		
		do {
			System.out.println("------------------------");
			System.out.println("Enter Sub Service Id :-");
			int ss_id=sc.nextInt();
			if(cRepo.isOtherServiceAdd(ss_id)) {
				System.out.println("Sub Services Added Successfully... :)");
			}
			else {
				System.out.println("There's Some Problem while Allocating Sub Service to Vehicle... :(");
			}		
			System.out.println("Do you Want to Add more Sub Services then Enter ["+1+"]");
			System.out.println("If no then Enter ["+0+"]");			
			System.out.println("------------------------");
			opt=sc.nextInt();
		}while(opt==1);
		
	}
	
//	public void showServices() {
//
//			
//			try {
//				LinkedHashMap<String, ArrayList<SubServiceModel>> map = cRepo.showServices(v_nplate);
//				Set<Map.Entry<String, ArrayList<SubServiceModel>>> s=map.entrySet();
//				int num=1;
//				for(Map.Entry<String, ArrayList<SubServiceModel>> m:s) {
//					System.out.println("Main Service Name:- "+(num++)+"\t"+m.getKey()+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//					ArrayList<SubServiceModel> al=m.getValue();
//					System.out.println("Sub Services:-");
//					System.out.println("====================================================================");
//					System.out.printf("%-55s %-10s\n", "Sub Service Name", "Prices");
//					System.out.println("====================================================================");
//					for(SubServiceModel ssmodel:al) {
//						System.out.printf("%-55s %-10.2f\n", ssmodel.getS_name(), ssmodel.getSs_price());
//					}
//					System.out.println("====================================================================");
//				}
//			}
//			catch(Exception ex) {
//				
//				ArrayList<SubServiceModel> al1=cRepo.showSubServices(v_nplate);
//				System.out.println("Registered Vehicle Number:-   "+v_nplate+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//				System.out.println("Sub Services:-");
//				System.out.println("====================================================================");
//				System.out.printf("%-55s %-10s\n", "Sub Service Name", "Prices");
//				System.out.println("====================================================================");
//				for(SubServiceModel ssmodel:al1) {
//					System.out.printf("%-55s %-10.2f\n", ssmodel.getS_name(), ssmodel.getSs_price());
//					System.out.println("--------------------------------------------------------------------");
//				}
//				System.out.println("====================================================================");
//				
//				
//			}
//	}
	
	public void showServices() {
		cRepo.showServices(v_nplate);
	}
}
