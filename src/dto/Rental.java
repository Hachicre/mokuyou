package dto;

public class Rental implements java.io.Serializable {

	private int id;
	private int settlementId;
	private int productId;
	private int planId;

	public Rental(
		int id,
		int settlementId,
		int productId,
		int planId
	) {
		this.id          = id;
		this.settlementId = settlementId;
		this.productId  = productId;
		this.planId      = planId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(int settlementId) {
		this.settlementId = settlementId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}
}
