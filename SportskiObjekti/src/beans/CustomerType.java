package beans;

import data.utils.CustomerTypeEnum;

public class CustomerType {

	private CustomerTypeEnum name;
	private int requiredPoints;
	private int salePercent;

	public CustomerType(CustomerTypeEnum name, int requiredPoints, int salePercent) {
		super();
		this.name = name;
		this.requiredPoints = requiredPoints;
		this.salePercent = salePercent;
	}

	public CustomerTypeEnum getName() {
		return name;
	}

	public void setName(CustomerTypeEnum name) {
		this.name = name;
	}

	public int getRequiredPoints() {
		return requiredPoints;
	}

	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}

	public int getSalePercent() {
		return salePercent;
	}

	public void setSalePercent(int salePercent) {
		this.salePercent = salePercent;
	}
	
	
	
}
