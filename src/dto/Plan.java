package dto;

public class Plan implements java.io.Serializable {
	
	private int    id;
	private double coefficient;
	private int    period;
	
	public Plan(
		int    id,
		double coefficient,
		int    period
	) {
		this.id          = id;
		this.coefficient = coefficient;
		this.period      = period;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

}
