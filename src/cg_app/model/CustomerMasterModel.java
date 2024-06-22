package cg_app.model;

public class CustomerMasterModel {

	private int cust_id;
	private String cust_name;
	private String cust_contact;
	private String cust_address;
	
	public CustomerMasterModel() {}
	
	public CustomerMasterModel(String cust_name, String cust_contact, String cust_address) {
		super();
		
		this.cust_name = cust_name;
		this.cust_contact = cust_contact;
		this.cust_address = cust_address;
	}

	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_contact() {
		return cust_contact;
	}
	public void setCust_contact(String cust_contact) {
		this.cust_contact = cust_contact;
	}
	public String getCust_address() {
		return cust_address;
	}
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}
	
}
