package ori.beans;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Incomes")
public class Income {
	private int id;
	private String clientType;
	private int clientId;
	private String name;
	private String date;
	private String description;
	private double amount;
	
	public Income(int id, String clientType, int clientId, String name, String date, String description, double amount) {
		setId(id);
		setClientType(clientType);
		setClientId(clientId);
		setName(name);
		setDate(date);
		setDescription(description);
		setAmount(amount);
	}
	
	public Income(int clientId, String clientType, String name, String date, String description, double amount) {
		setClientType(clientType);
		setClientId(clientId);
		setName(name);
		setDate(date);
		setDescription(description);
		setAmount(amount);
	}
	
	public Income() {
		
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(nullable=false)
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	@Column(nullable=false)
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false)
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Column(nullable=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(nullable=false)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void display() {
		System.out.println("id: " + id);
		System.out.println("client type: " + clientType);
		System.out.println("clientId: " + clientId);
		System.out.println("name: " + name);
		System.out.println("date: " + date.toString());
		System.out.println("description: " + description);
		System.out.println("amaount: " + amount);
	}
}
