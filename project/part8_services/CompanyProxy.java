package project.part8_services;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import project.part2_beans.Coupon;
@XmlRootElement
public class CompanyProxy {
	private int id;
	private String name;
	private String email;
	private String password;
	private ArrayList<Coupon> coupons = null;


	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Coupon> getCoupons(){
		return coupons;
	}
	@XmlElement
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}


}
