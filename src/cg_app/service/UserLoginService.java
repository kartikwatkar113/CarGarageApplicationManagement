package cg_app.service;
import java.util.*;
import cg_app.repository.UserLoginRepo;


public class UserLoginService {
	
	Scanner sc=new Scanner(System.in);
	UserLoginRepo urepo=new UserLoginRepo();
	
	public boolean isUserLogin() {
		
		System.out.println("Enter User Name:- ");
		String user_n=sc.next();
		System.out.println("Enter Password:- ");
		String pass=sc.next();
		sc.nextLine();
		boolean b=urepo.isCorrect(user_n,pass);
		return b;
		
	}
	
	public void isUpdatePass() {
		System.out.println("Enter User Name for Password Updation:- ");
		String user_n=sc.next();
		boolean b=urepo.isUpdatePass(user_n);
		if(b) {
			System.out.println("Password Updated Successfully... :)");
		}else{
			System.out.println("There's Some Problem while Updating User Password... :(");
		
		}
		
	}
	
}
