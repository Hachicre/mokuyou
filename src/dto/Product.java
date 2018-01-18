package dto;

public class Product implements java.io.Serializable {

	private int    id;
	private String name;
	private int    stock;
	private int    price;
	private String createdAt;
	private String updatedAt;
	
	public Product(
		int    id,
		String name,
		int    stock,
		int    price,
		String createdAt,
		String updatedAt
	) {
		this.id        = id;
		this.name      = name;
		this.stock     = stock;
		this.price     = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
