package dto;

public class Plan {
	
	private int    id;
	private double coefficient;
	private int    period;
	
	public void product(
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
