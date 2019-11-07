package project.part2_beans;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Coupon implements Serializable {
	private int id;
	private int companyId;
	private Category category;
	private String title;
	private String description;
	private String startDate;
	private String endDate;
	private int amount;
	private double price;
	private String image;
	public Coupon() {
		
	}
	public Coupon(int id, int companyId, Category category, String title, String description, String startDate, String endDate, int amount, double price, String image) {
		setId(id);
		setCompanyId(companyId);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
		
	}
	public void display() {
		System.out.println("Id: " + id);
		System.out.println("CompanyId: " + companyId);
		System.out.println("Category: " + category.toString());
		System.out.println("Title: " + title);
		System.out.println("Description: " + description);
		System.out.println("Start Date: " + startDate);
		
		System.out.println("End Date: " + endDate);
		
		System.out.println("Amount: " + amount);
		System.out.println("Price: " + price);
		System.out.println("Image: " + image);
		
	}
	public String toString() {
		
		return "Id: " + id + ", company id: " + companyId + ", category: " +  category.toString() + ", title: " + title + ", description: " + description + ", start date: " + startDate + ", end date: " + endDate + ", amount: " + amount + ", price: " + price + ", image: " + image;
		
	}
	

}
