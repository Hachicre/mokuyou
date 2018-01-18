package dto;

public class Settlement implements java.io.Serializable {

	private int     id;
	private int     userId;
	private int     rentalStartAt;
	private int     price;
	private Boolean beReturned;
	
	public Settlement(
		int     id,
		int     userId,
		int     rentalStartAt,
		int     price,
		Boolean beReturned
	) {
		this.id            = id;
		this.userId        = userId;
		this.rentalStartAt = rentalStartAt;
		this.price         = price;
		this.beReturned    = beReturned;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRentalStartAt() {
		return rentalStartAt;
	}

	public void setRentalStartAt(int rentalStartAt) {
		this.rentalStartAt = rentalStartAt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Boolean getBeReturned() {
		return beReturned;
	}

	public void setBeReturned(Boolean beReturned) {
		this.beReturned = beReturned;
	}

}
