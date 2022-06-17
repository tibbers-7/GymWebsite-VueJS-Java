package model;

public class CustomerType{
	private String name;
	private float discountPercentage;
	public CustomerType(String name, float discountPercentage, int requiredPoints) {
		super();
		this.name = name;
		this.discountPercentage = discountPercentage;
		this.requiredPoints = requiredPoints;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public int getRequiredPoints() {
		return requiredPoints;
	}
	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}
	private int requiredPoints;
}