package model;

public class Food extends GardenItem {

	private String type;

	public Food(String name, int x, int y) {

		super(x, y);
		this.type = name;

	}

	public String getType() {

		return type;
	}

	public void setName(String type) {

		this.type = type;
	}

	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return "Food [type=" + type + ", x=" + positionX + ", y=" + positionY + "]";
	}

}
