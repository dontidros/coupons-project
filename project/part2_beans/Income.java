package project.part2_beans;

import java.time.LocalDate;


/**
 * 
 * an income bean for part III of this project
 *
 */
public class Income {
	private int id;
	private String clientType;
	private int clientId;
	private String name;
	private LocalDate date;
	private IncomeType description;
	private double amount;
	
	public Income(int id, String clientType, int clientId, String name, 
				  LocalDate date, IncomeType description, double amount) {
		setId(id);
		setClientType(clientType);
		setClientId(clientId);
		setName(name);
		setDate(date);
		setDescription(description);
		setAmount(amount);
	}
	
	
	public Income(String clientType, int clientId, String name, LocalDate date, IncomeType description, double amount) {
		setClientType(clientType);
		setClientId(clientId);
		setName(name);
		setDate(date);
		setDescription(description);
		setAmount(amount);
	}
	public Income() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public IncomeType getDescription() {
		return description;
	}
	public void setDescription(IncomeType description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void display() {
		System.out.println("id: " + id);
		System.out.println("client type: " + clientType);
		System.out.println("client id: " + clientId);
		System.out.println("name: " + name);
		System.out.println("date: " + date.toString());
		System.out.println("description: " + description.name());
		System.out.println("amount: " + amount);
	}

}