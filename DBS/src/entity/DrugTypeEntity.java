package entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by marcelforgac on 3.5.15.
 */
@Entity
@Table(name = "drug_type", schema = "", catalog = "DBS")
public class DrugTypeEntity {
	private int drugTypeId;
	private String typLieku;
	private Set<DrugEntity> drugType;

	@Id
	@Column(name = "drug_type_id")
	public int getDrugTypeId() {
		return drugTypeId;
	}

	public void setDrugTypeId(int drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	@Basic
	@Column(name = "typ_lieku")
	public String getTypLieku() {
		return typLieku;
	}

	public void setTypLieku(String typLieku) {
		this.typLieku = typLieku;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DrugTypeEntity that = (DrugTypeEntity) o;

		if (drugTypeId != that.drugTypeId) return false;
		if (typLieku != null ? !typLieku.equals(that.typLieku) : that.typLieku != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = drugTypeId;
		result = 31 * result + (typLieku != null ? typLieku.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "drugType")
	public Set<DrugEntity> getDrugType() {
		return drugType;
	}

	public void setDrugType(Set<DrugEntity> drugType) {
		this.drugType = drugType;
	}
}
