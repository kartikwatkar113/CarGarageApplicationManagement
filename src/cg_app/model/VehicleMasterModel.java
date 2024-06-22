package cg_app.model;

public class VehicleMasterModel extends TechnicianMasterModel {
	
	int v_id;
	String v_model;
	String v_number_plate;
	String v_arrival_date;
	
	public VehicleMasterModel() {}
	
	public VehicleMasterModel(String v_model, String v_number_plate, String v_arrival_date) {
		super();
		this.v_model = v_model;
		this.v_number_plate = v_number_plate;
		this.v_arrival_date = v_arrival_date;
	}
	
	public int getV_id() {
		return v_id;
	}
	public void setV_id(int v_id) {
		this.v_id = v_id;
	}
	public String getV_model() {
		return v_model;
	}
	public void setV_model(String v_model) {
		this.v_model = v_model;
	}
	public String getV_number_plate() {
		return v_number_plate;
	}
	public void setV_number_plate(String v_number_plate) {
		this.v_number_plate = v_number_plate;
	}
	public String getV_arrival_date() {
		return v_arrival_date;
	}
	public void setV_arrival_date(String v_arrival_date) {
		this.v_arrival_date = v_arrival_date;
	}	
}
