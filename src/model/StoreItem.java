package model;

public class StoreItem {

	private String type;
	private int price;
	private String subType;

	public StoreItem(String type, String subType, int Price) {

		this.type = type;
		this.subType = subType;
		this.price = price;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.subType = type;
	}

	public String getsubType() {
		return subType;
	}

	public void setsubType(String subType) {
		this.subType = subType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice() {
		this.price = price;
	}

	public boolean isPlant() {
		return this.type.equals("plant");
	}
	
	public boolean isAnimal() {
		return this.type.equals("plant");
	}
	
	public boolean isFood() {
		return this.type.equals("food");
	}

}
