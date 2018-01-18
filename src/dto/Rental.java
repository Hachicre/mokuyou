package dto;

public class Rental {

	private int id;
	private int setlementId;
	private int prroductId;
	private int planId;

	public Rental(
		int id,
		int setlementId,
		int prroductId,
		int planId
	) {
		this.id          = id;
		this.setlementId = setlementId;
		this.prroductId  = prroductId;
		this.planId      = planId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSetlementId() {
		return setlementId;
	}

	public void setSetlementId(int setlementId) {
		this.setlementId = setlementId;
	}

	public int getPrroductId() {
		return prroductId;
	}

	public void setPrroductId(int prroductId) {
		this.prroductId = prroductId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}
}
