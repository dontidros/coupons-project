package project.part8_services;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CouponProxy {
	private int id;
	private int companyId;
	private String category;
	private String title;
	private String description;
	private String startDate;
	private String endDate;
	private int amount;
	private double price;
	private String image;

	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	@XmlElement
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public String getCategory() {
		return category;
	}
	@XmlElement
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStartDate() {
		return startDate;
	}
	@XmlElement
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	@XmlElement
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getAmount() {
		return amount;
	}
	@XmlElement
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getPrice() {
		return price;
	}
	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImage() {
		return image;
	}
	@XmlElement
	public void setImage(String image) {
		this.image = image;
		
	}


}
