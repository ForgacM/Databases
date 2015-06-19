package model;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class Drug {

	private Integer drugId;
	private String name;
	private int count;
	private boolean recept;
	private String drug_type;
	private boolean state;
	private int typeIndex;
	private Integer drugTypeId;

	public int getTypeIndex() {
		return typeIndex;
	}

	public void setTypeIndex(int typeIndex) {
		this.typeIndex = typeIndex;
	}

	public Integer getDrugId() {
		return drugId;
	}

	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
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

	public boolean isRecept() {
		return recept;
	}

	public void setRecept(boolean recept) {
		this.recept = recept;
	}

	public String getDrug_type() {
		return drug_type;
	}

	public void setDrug_type(String drug_type) {
		this.drug_type = drug_type;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Integer getDrugTypeId() {
		return drugTypeId;
	}

	public void setDrugTypeId(Integer drugTypeId) {
		this.drugTypeId = drugTypeId;
	}
}
