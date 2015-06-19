package entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by marcelforgac on 3.5.15.
 */
@Entity
@Table(name = "recept", schema = "", catalog = "ikvb6ju5")
public class ReceptEntity {
	private int receptId;
	private String date;
	private int patientId;
	private int doctorId;
	private Set<ItemEntity> item_recept;
	private PatientEntity patientRecept;

	@Id
	@Column(name = "recept_id")
	public int getReceptId() {
		return receptId;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}

	@Basic
	@Column(name = "date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Basic
	@Column(name = "patient_id")
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	@Basic
	@Column(name = "doctor_id")
	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReceptEntity that = (ReceptEntity) o;

		if (doctorId != that.doctorId) return false;
		if (patientId != that.patientId) return false;
		if (receptId != that.receptId) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = receptId;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + patientId;
		result = 31 * result + doctorId;
		return result;
	}

	@OneToMany(mappedBy = "item_recept")
	public Set<ItemEntity> getItem_recept() {
		return item_recept;
	}

	public void setItem_recept(Set<ItemEntity> item_recept) {
		this.item_recept = item_recept;
	}

	@ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false, insertable = false, updatable = false)
	public PatientEntity getPatientRecept() {
		return patientRecept;
	}

	public void setPatientRecept(PatientEntity patientRecept) {
		this.patientRecept = patientRecept;
	}
}
