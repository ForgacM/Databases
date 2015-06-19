package model;

/**
 * Created by marcelforgac on 11.4.15.
 */
public class Items {

	private int itemId;
	private String name;
	private int count;
	private String drug_type;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDrug_type() {
		return drug_type;
	}

	public void setDrug_type(String drug_type) {
		this.drug_type = drug_type;
	}
}
