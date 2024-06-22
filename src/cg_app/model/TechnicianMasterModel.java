package cg_app.model;

public class TechnicianMasterModel {

	private int t_id;
	private String t_name;
	private long t_contact;
	
	public TechnicianMasterModel() {}
	
	public TechnicianMasterModel(String t_name, long t_contact) {
		super();
		this.t_name = t_name;
		this.t_contact = t_contact;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public long getT_contact() {
		return t_contact;
	}
	public void setT_contact(long t_contact) {
		this.t_contact = t_contact;
	}

}
