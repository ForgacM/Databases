package entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by marcelforgac on 3.5.15.
 */
@Entity
@Table(name = "patient", schema = "", catalog = "DBS")
public class PatientEntity {
	private int patientId;
	private String name;
	private String poistovna;
	private String lastName;
	private Set<OrderEntity> patientOrder;
	private Set<ReceptEntity> patientRecept;
	private SessionEntity sessP;

	public PatientEntity(String name, String lastName, String poistovna) {
		this.name = name;
		this.poistovna = poistovna;
		this.lastName = lastName;
	}

	public PatientEntity() {
	}

	@Id
	@Column(name = "patient_id")
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
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
	@Column(name = "poistovna")
	public String getPoistovna() {
		return poistovna;
	}

	public void setPoistovna(String poistovna) {
		this.poistovna = poistovna;
	}

	@Basic
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PatientEntity that = (PatientEntity) o;

		if (patientId != that.patientId) return false;
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (poistovna != null ? !poistovna.equals(that.poistovna) : that.poistovna != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = patientId;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (poistovna != null ? poistovna.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "patientOrder")
	public Set<OrderEntity> getPatientOrder() {
		return patientOrder;
	}

	public void setPatientOrder(Set<OrderEntity> patientOrder) {
		this.patientOrder = patientOrder;
	}

	@OneToMany(mappedBy = "patientRecept")
	public Set<ReceptEntity> getPatientRecept() {
		return patientRecept;
	}

	public void setPatientRecept(Set<ReceptEntity> patientRecept) {
		this.patientRecept = patientRecept;
	}

	@OneToOne(mappedBy = "sessP")
	public SessionEntity getSessP() {
		return sessP;
	}

	public void setSessP(SessionEntity sessP) {
		this.sessP = sessP;
	}
}
