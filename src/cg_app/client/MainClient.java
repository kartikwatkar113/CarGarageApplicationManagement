package cg_app.client;

import java.util.*;

import cg_app.customException.InvalidContactNumberException;
import cg_app.customException.InvalidCustomerIdException;
import cg_app.customException.InvalidCustomerNameException;
import cg_app.service.BillingService;
import cg_app.service.CarServiceMasterService;
import cg_app.service.CustomerMasterService;
import cg_app.service.SerMasterService;
import cg_app.service.SparePartsService;
import cg_app.service.TechnicianMasterService;
import cg_app.service.UserLoginService;
import cg_app.service.VehicleMasterService;
import cg_appInputMismatched.InputMismatchedClass;

public class MainClient {

	static boolean check;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		CustomerMasterService cmservice=new CustomerMasterService();
		TechnicianMasterService tservice=new TechnicianMasterService();
		VehicleMasterService vservice=new VehicleMasterService();
		SerMasterService sservice=new SerMasterService();
		UserLoginService uservice=new UserLoginService();
		CarServiceMasterService cService=new CarServiceMasterService();
		SparePartsService spService=new SparePartsService();
		BillingService bService=new BillingService();
		InputMismatchedClass ims=new InputMismatchedClass();
		
		do {
			System.out.println("=====================");
			System.out.println("1.Login...");
			System.out.println("2.Forgot Password...");
			System.out.println("3.Exit...");
			System.out.println("=====================");
		
			int choice1=ims.checkInput();
			switch(choice1) {
				case 1:{
					boolean b=uservice.isUserLogin();
					if(b) {
						
						boolean exit1=false;
						do {
							
							System.out.println("==========Welcome==========");
							System.out.println("1. Customer Section...");
							System.out.println("2. Customer's Vehicle Section...");
							System.out.println("3. Technician's Section...");
							System.out.println("4. Services Section...");
							System.out.println("5. Service's Allocation to the Car...");
							System.out.println("6. Spare Part's Section and Allocation to the Car...");
							System.out.println("7. Billing Section...");
							System.out.println("8. Logout...");
							System.out.println("===========================");
							
							int choice=ims.checkInput();
							switch(choice){
								
								case 1:{

									boolean exit = false;
									do {
										System.out.println("------------------------");
										System.out.println("1. Add Customer Details...");
										System.out.println("2. Update Customer Details...");
										System.out.println("3. Delete Customer Details...");
										System.out.println("4. Show Customer Details...");
										System.out.println("5. Exit");
										System.out.println("------------------------");

										
										int opt=ims.checkInput();
										switch(opt) {
										case 1:{
											
											try {
												cmservice.addCustomer();
											} catch (InvalidCustomerNameException  | InvalidContactNumberException e) {
												System.out.println("Error is:- "+e.getMessage());
											}
//								
										}
										break;
										
										case 2:{
											try {
												cmservice.updateCustomer();
											} catch (InvalidCustomerIdException  | InvalidCustomerNameException e) {
												System.out.println("Error is:- "+e.getMessage());
											}
										}
										break;
										
										case 3:{
											try {
												cmservice.deleteCustomer();
											} catch (InvalidCustomerIdException | InvalidCustomerNameException e) {
												System.out.println("Error is:- "+e.getMessage());
											}
										}
										break;
										
										case 4:{
											cmservice.showCustomer();
										}
										break;
										
										case 5:
											exit=true;
										break;
										default:
											System.out.println("Wrong Choice... :(");
										
										}
									}while(!exit);
									
								}
								break;
								
								case 2:{
									boolean exit = false;
									do {
										System.out.println("------------------------");
										System.out.println("1. Add Customer's Vehicle Details...");
										System.out.println("2. Update Customer's Vehicle...");
										System.out.println("3. Delete Customer's Vehicle...");
										System.out.println("4. Show Customer's Vehicle...");
										System.out.println("5. Search Customer's Vehicle...");
										System.out.println("6. Exit");
										System.out.println("------------------------");
										
										int opt=ims.checkInput();
										switch(opt) {
										case 1:{
											
											vservice.addVehicles();
										}
										break;
										
										case 2:{
											vservice.updateVehicles();
										}
										break;
										
										case 3:{
											vservice.deleteVehicle();
										}
										break;
										
										case 4:{
											vservice.showVehicle();
										}
										break;
										
										case 5:{
											vservice.searchVehicle();
										}
										break;
										
										case 6:
											exit=true;
										break;
										default:
											System.out.println("Wrong Choice.. :(");
										
										}
									}while(!exit);
								}
								break;
								
								
								case 3:{
									boolean exit = false;
									do {
										System.out.println("------------------------");
										System.out.println("1. Add Technician's Details...");
										System.out.println("2. Delete Technician's Details...");
										System.out.println("3. Show Technician's...");
										System.out.println("4. Exit");
										System.out.println("------------------------");
						
										int opt=ims.checkInput();
										switch(opt) {
										case 1:{
											tservice.addTechnician();
										}
										break;
										
										case 2:{
											tservice.deleteTechnician();
										}
										break;
										
										case 3:{
											tservice.showTechnician();
										}
										break;
										
										case 4:
											exit=true;
										break;
										
										
										default:
											System.out.println("Wrong Choice.. :(");
										
										}
									}while(!exit);
								}
								break;
								
								case 4:{
									boolean exit = false;
									do {
										System.out.println("------------------------");
										System.out.println("1. Add New Services in Garage...");
										System.out.println("2. Add Sub Services in Garage...");
										System.out.println("3. Show Sub Services in Garage...");
										System.out.println("4. Show Main Services and its sub types...");
										System.out.println("5. Price Updation in Sub Services...");
										System.out.println("6. Exit");
										System.out.println("------------------------");
										 
										int opt=ims.checkInput();
										switch(opt) {
										case 1:{
											sservice.addServiceName();
										}
										break;
										
										case 2:{
											sservice.addSubService();
										}
										break;
										
										case 3:{
											sservice.showSubService();
										}
										break;
										
										case 4:{
											sservice.showMainService();
										}
										break;
										
										case 5:{
											sservice.priceUpdation();
										}
										break;
										
										case 6:{
											exit=true;
										}
											break;
											
											default:
												System.out.println("Wrong choice....");
										}
										
										
									}while(!exit);
									
								}
									break;
									
							
								case 5:{
								    do {
								        check = cService.checkVehicle();
								        if (check) {
								            boolean exit = false;
								            do {
								                System.out.println("------------------------");
								                System.out.println("1. Do you Want to Add Main Services...");
								                System.out.println("2. Do you Want to Add Other Services...");
								                System.out.println("3. Show Allocated Services to Registered Vehicle Number...");
								                System.out.println("4. Exit");
								                System.out.println("------------------------");

								                int opt=ims.checkInput();
								                switch (opt) {
								                    case 1: {
								                        cService.ServiceForCar();
								                    }
								                    break;

								                    case 2: {
								                        cService.otherServices();
								                    }
								                    break;

			
								                    case 3:{
								                    	cService.showServices();
								                    }
								                    break;
								                    
								                    case 4: {
								                        exit = true;
								                    }
								                    break;
								                }
								            } while (!exit);
								        } else {
								            System.out.println("Please Enter Correct Vehicle Registered Number...");
								        }
								    } while (!check); // Repeat until check is true
								}
								break;
	
								
								case 6:{
									boolean exit=false;
									do {
										System.out.println("------------------------");
										System.out.println("1. Add Spare Part's in Garage...");
										System.out.println("2. Update Spare Part's in Garage...");
										System.out.println("3. Show Spare Part's in Garage...");
										System.out.println("4. Assign Spare Part's to the Vehicle...");
										System.out.println("5. Show Allocated Spare Part's to Vehicle...");
										System.out.println("6. Exit...");
										System.out.println("------------------------");
						                
										int opt=ims.checkInput();
						                switch(opt) {
						                	case 1:{
						                		spService.isAddSpareParts();
						                	}
						                	break;
						                	
						                	case 2:{
						                		spService.updateSparePart();
						                	}
						                	break;
						                	
						                	case 3:{
						                		spService.showSparePart();
						                	}
						                	break;
						                	
						                	case 4:{
						                		spService.assignSparePart();
						                	}
						                	break;
						                	
						                	case 5:{
						                		spService.showAssignSparePartVehicle();
						                	}
						                	break;
						                	
						                	case 6:{
						                		exit=true;
						                	}
						                	break;
						                }
										
									}while(!exit);
									
								}
								break;
								
								case 7:{
									
									boolean exit=false;
									do {
										System.out.println("------------------------");
										System.out.println("1. Show Bill...");
										System.out.println("2. Show the All Bill DateWise In Garage...");
										System.out.println("3. Last 4 Month Bill...");
										System.out.println("4. Number of Customer's Regarding Year and Month Wise...");
										System.out.println("5. Customer List Regarding Year and Month Wise...");
										System.out.println("6. Customer List Regarding Year Wise...");
										System.out.println("7. Apply 10% Discount by visiting Customer more than 3 time's...");
										System.out.println("8. Exit...");
										System.out.println("------------------------");
						                
										int opt=ims.checkInput();
						                switch(opt) {
						                	case 1:{
						                		bService.showBill();
						                	}
						                	break;
						                	
						                	case 2:{
						                		bService.showTotalBillDatewise();
						                	}
						                	break;
						                	
						                	case 3:{
						                		bService.showLastFourMonthBill();
						                	}
						                	break;
						                	
						                	case 4:{
						                		bService.countMonthWise();
						                	}
						                	break;
						                	
						                	case 5:{
						                		bService.custListYearMonthWise();
						                	}
						                	break;
						                	
						                	case 6:{
						                		bService.yearWiseCustList();
						                	}
						                	break;
						                	
						                	case 7:{
						                		bService.discountOnBill();
						                	}
						                	break;
						                	
						                	case 8:{
						                		exit=true;
						                	}
						                	break;
						                }
										
									}while(!exit);
								}
								break;
								
								case 8:{
									exit1=true;
								}
									
								break;
								
								default:
									System.out.println("Wrong Choice... :(");
								
							}
							
						}while(!exit1);
					}
					else {
						System.out.println("Wrong User Name and Password... :(");
					}
				}
				break;
				
				case 2:{
					uservice.isUpdatePass();
				}
				break;
				
				case 3:{
					System.out.println("Thank You... :)");
					System.exit(choice1);
				}
			
				default:{
					System.out.println("Wrong choice.... :(");
				}
			}
			
			sc.close();
		}while(true);
		
		

	}
	

}
