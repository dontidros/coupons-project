package project.part2_beans;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class Customer implements Serializable{
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private ArrayList<Coupon> coupons = null;
	public Customer() {
		
	}
	public Customer(int id, String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Coupon> getCoupons(){
		return coupons;
	}
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	@SuppressWarnings("unchecked")
	public JSONArray getCouponsJSON() {
		ArrayList<Coupon> coupons = getCoupons();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < coupons.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", coupons.get(i).getId());
			jsonObject.put("companyId", coupons.get(i).getCompanyId());
			jsonObject.put("category", coupons.get(i).getCategory().name());
			jsonObject.put("title", coupons.get(i).getTitle());
			jsonObject.put("description", coupons.get(i).getDescription());
			jsonObject.put("startDate", coupons.get(i).getStartDate());
			jsonObject.put("endDate", coupons.get(i).getEndDate());
			jsonObject.put("amount", coupons.get(i).getAmount());
			jsonObject.put("price", coupons.get(i).getPrice());
			jsonObject.put("image", coupons.get(i).getImage());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	public void display() {
		System.out.println("id: " + id);
		System.out.println("First name: " + firstName);
		System.out.println("Last name: " + lastName);
		System.out.println("email: " + email);
		System.out.println("Password: " + password);
		for(int i = 0; i < coupons.size(); i++) {
			coupons.get(i).display();
			
		}

		
	}
	public String toString() {
		return "Id: " + id + ", first name: " + firstName + ", last name: " + lastName + ", email: " + email + ", password: " +  password;
		
	}

}
