package cg_app.service;

import cg_app.repository.BillingRepo;
import cg_app.repository.CarServicingRepo;

import java.util.*;

public class BillingService {

	BillingRepo billRepo=new BillingRepo();
	CarServicingRepo cRepo=new CarServicingRepo();
	Scanner sc=new Scanner(System.in);
	
	public void showBill() { 
		
		System.out.println("Enter Vehicle Registered Number:- ");
		String v_nplate=sc.next();
		if(cRepo.checkVehicle(v_nplate)) {
			billRepo.showBill(v_nplate);
		}
		else {
			System.out.println("Vehicle not found... :(");
		}
		
	}
	
	public void showTotalBillDatewise() {
		billRepo.showTotalBillDatewise();
	}
	
	public void showLastFourMonthBill() {
		billRepo.showLastFourMonthBill();
	}
	public void countMonthWise() {
		billRepo.countMonthWise();
	}
	public void custListYearMonthWise(){
		System.out.println("--------------------------");
		System.out.println("Enter Year....");
		int year=sc.nextInt();
		System.out.println("Enter Month Number...");
		int m_num=sc.nextInt();
		
		System.out.println("--------------------------");
		
		billRepo.custListYearMonthWise(year,m_num);
	}
	
	public void yearWiseCustList(){
		System.out.println("--------------------------");
		System.out.println("Enter Year....");
		int year=sc.nextInt();
		System.out.println("--------------------------");
		billRepo.yearWiseCustList(year);
	}
	
	public void discountOnBill() {
		billRepo.discountOnBill();
	}
}
