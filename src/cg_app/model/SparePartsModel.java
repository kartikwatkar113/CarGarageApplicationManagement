package cg_app.model;

public class SparePartsModel {

	private int sp_id;
	private String sp_name;
	private float sp_price;
	
	public SparePartsModel() {}
	
	public SparePartsModel(int sp_id, String sp_name, float sp_price) {
		super();
		this.sp_id = sp_id;
		this.sp_name = sp_name;
		this.sp_price = sp_price;
	}

	public int getSp_id() {
		return sp_id;
	}

	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}

	public String getSp_name() {
		return sp_name;
	}

	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}

	public float getSp_price() {
		return sp_price;
	}

	public void setSp_price(float sp_price) {
		this.sp_price = sp_price;
	}
	
}
