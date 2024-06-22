package cg_appInputMismatched;
import java.util.*;

public class InputMismatchedClass {

	Scanner sc=new Scanner(System.in);
	
	public int checkInput() {
		int choice1;
		boolean flag=true;
		do {
			
			try {
				System.out.println("Enter Choice:- ");
				choice1=sc.nextInt();
				flag=false;
				
			}
			catch(Exception ex) {
				System.out.println("Please Enter Proper Input... :(");
				sc.next();
				flag=true;
				return 0;
			}
		}while(flag);
		
		return choice1;
	}
	
}
