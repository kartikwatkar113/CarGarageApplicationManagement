package cg_app.model;


public class SubServiceModel extends SeviceMasterModel{

	int ss_id;
	String ss_name;
	float ss_price;
	
	public int getSs_id() {
		return ss_id;
	}
	public void setSs_id(int ss_id) {
		this.ss_id = ss_id;
	}
	public String getSs_name() {
		return ss_name;
	}
	public void setSs_name(String ss_name) {
		this.ss_name = ss_name;
	}
	public float getSs_price() {
		return ss_price;
	}
	public void setSs_price(float ss_price) {
		this.ss_price = ss_price;
	}
	
	
}
