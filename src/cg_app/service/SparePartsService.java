package cg_app.service;
import java.util.*;

import cg_app.model.SparePartsModel;
import cg_app.repository.SparePartsRepo;
import cg_appInputMismatched.InputMismatchedClass;

public class SparePartsService {

	static boolean exit1;
	Scanner sc=new Scanner(System.in);
	SparePartsRepo spRepo=new SparePartsRepo();
	CarServiceMasterService csService=new CarServiceMasterService();
	InputMismatchedClass ims=new InputMismatchedClass();

	
	public void isAddSpareParts() {
	
		System.out.println("Enter Spare Part Name:- ");
		String spname=sc.nextLine();
		
		System.out.println("Enter Spare Part Price:- ");
		float spprice=sc.nextFloat();
		
		if(spRepo.isAddSpareParts(spname,spprice)) {
			System.out.println("Spare Part Data Added Successfully... :)");
		}
		else {
			System.out.println("There's Some Problem while Adding Spare Part... :(");
		}
		
	}
	
	public void updateSparePart() {
		
		System.out.println("Enter Spare part Name for Updation...");
		String spname=sc.nextLine();
		sc.nextLine();
		boolean b=spRepo.isUpdateSparePart(spname);
		if(b) {
			System.out.println("Spare Part's price Updated Successfully... :)");
		}
		else {
			System.out.println("There's Some Problem while Updating Spare Part's Details... :(");
		}
	}
	
	public void showSparePart() {
        ArrayList<SparePartsModel> al = spRepo.showSparePart();

        if (al != null && !al.isEmpty()) {
            System.out.println("================================================================");
            System.out.println("SparePart ID\tSparePart Name\t\t\tSparePart Price");
            System.out.println("================================================================");
            al.forEach((m) -> {
                System.out.println(String.format("%-15d%-35s%-10.2f",
                        m.getSp_id(), m.getSp_name(), m.getSp_price()));
            });
            System.out.println("================================================================");
        } else {
            System.out.println("There's no Customer Data Found in DATABASE... :(");
        }
    }
	
	public void assignSparePart() {
	    boolean exit = false;
	    do {
	        if (csService.checkVehicle()) {
	            this.showSparePart();

	            System.out.println("Enter the Spare Part id or type '0' to exit: ");
	            int sp_id = sc.nextInt();

	            // Exit the loop if the user inputs '0'
	            if (sp_id == 0) {
	                exit = true;
	                System.out.println("Exiting spare part assignment...");
	            } else {
	                if (spRepo.isSparePartAssign(sp_id)) {
	                    System.out.println("Allocating Spare part to Vehicles Successfully... :)");
	                } else {
	                    System.out.println("There's Some Problem while Allocating Spare part to Vehicle... :(");
	                }
	            }
	            break;
	        } else {
	            System.out.println("Please Enter Correct Vehicle Registered Number...");
	        }
	    } while (!exit);
	}

	
	public void showAssignSparePartVehicle(){
		boolean exit=false;

		do {
			System.out.println("------------------------");
			System.out.println("For Show Enter 1 ...");
			System.out.println("For Exit Enter 0 ...");
			System.out.println("------------------------");

            
			int opt=ims.checkInput();
            switch(opt) {
            
            	case 1:{

            		do {
            			exit1=csService.checkVehicle();
            			if(exit1) {
            				
            				LinkedHashMap<String,ArrayList<SparePartsModel>> map=spRepo.showAssignSparePartVehicle();
            				Set<Map.Entry<String, ArrayList<SparePartsModel>>> s=map.entrySet();
            				int num=1;
            				for(Map.Entry<String, ArrayList<SparePartsModel>> m:s) {
            					System.out.println("Registered Vehicle Number:- "+(num++)+". "+m.getKey()+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            					ArrayList<SparePartsModel> sp_Name=m.getValue();
            					for(SparePartsModel spmodel:sp_Name) {
            						System.out.println(spmodel.getSp_name()+"\t"+spmodel.getSp_price());
            					}
            					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            				}
            				
            			}
            			else {
            				System.out.println("Please Enter Correct Vehicle Registered Number...");
            			}
            		}while(!exit1);
            	}
            	break;
            	
            	case 0:{
            		exit=true;
            	}
            	break;
            }
			
		}while(!exit);
		
		
	}
}
