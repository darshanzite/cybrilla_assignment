package com.product_rate.model.result;

public class ResultDTO {
	private int result_id;
	private String product_name;
	private double avg_mtd_rate;
	private double avg_ytd_rate;
	
	public int getResult_id() {
		return result_id;
	}
	public void setResult_id(int result_id) {
		this.result_id = result_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getAvg_mtd_rate() {
		return avg_mtd_rate;
	}
	public void setAvg_mtd_rate(double avg_mtd_rate) {
		this.avg_mtd_rate = avg_mtd_rate;
	}
	public double getAvg_ytd_rate() {
		return avg_ytd_rate;
	}
	public void setAvg_ytd_rate(double avg_ytd_rate) {
		this.avg_ytd_rate = avg_ytd_rate;
	}	
}
