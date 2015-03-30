package com.product_rate.model.product;

import java.io.Serializable;

public class ProductDTO implements Serializable{

	private String product_id;
	private String product_name;
	private String rate;
	private String comment;
	private String mtd;
	private String ytd;

	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMtd() {
		return mtd;
	}
	public void setMtd(String mtd) {
		this.mtd = mtd;
	}
	public String getYtd() {
		return ytd;
	}
	public void setYtd(String ytd) {
		this.ytd = ytd;
	}
}
