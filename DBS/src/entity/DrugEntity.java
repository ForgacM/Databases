package entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by marcelforgac on 3.5.15.
 */
@Entity
@Table(name = "drugs", schema = "", catalog = "DBS")
public class DrugEntity {
	private int drugId;
	private String name;
	private Integer count;
	private int drugTypeId;
	private boolean recept;
	private boolean state;
	private DrugTypeEntity drugType;
	private Set<ItemEntity> item_drug;

	public DrugEntity() {
	}

	public DrugEntity(String name, Integer count, int drugTypeId, boolean recept, boolean state) {
		this.name = name;
		this.count = count;
		this.drugTypeId = drugTypeId;
		this.recept = recept;
		this.state = state;
	}

	@Id
	@Column(name = "drug_id")
	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "count")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Basic
	@Column(name = "drug_type_id")
	public int getDrugTypeId() {
		return drugTypeId;
	}

	public void setDrugTypeId(int drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	@Basic
	@Column(name = "recept")
	public boolean getRecept() {
		return recept;
	}

	public void setRecept(boolean recept) {
		this.recept = recept;
	}

	@Basic
	@Column(name = "state")
	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DrugEntity that = (DrugEntity) o;

		if (drugId != that.drugId) return false;
		if (drugTypeId != that.drugTypeId) return false;
		if (count != null ? !count.equals(that.count) : that.count != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = drugId;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (count != null ? count.hashCode() : 0);
		result = 31 * result + drugTypeId;
		return result;
	}

	@ManyToOne
	@JoinColumn(name = "drug_type_id", referencedColumnName = "drug_type_id", nullable = false, insertable = false, updatable = false)
	public DrugTypeEntity getDrugType() {
		return drugType;
	}

	public void setDrugType(DrugTypeEntity drugType) {
		this.drugType = drugType;
	}

	@OneToMany(mappedBy = "item_drug")
	public Set<ItemEntity> getItem_drug() {
		return item_drug;
	}

	public void setItem_drug(Set<ItemEntity> item_drug) {
		this.item_drug = item_drug;
	}
}
