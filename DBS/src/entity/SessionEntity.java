package entity;

import javax.persistence.*;

/**
 * Created by marcelforgac on 3.5.15.
 */
@Entity
@Table(name = "session", schema = "", catalog = "DBS")
public class SessionEntity {
	private Integer patientId;
	private int sessId;
	private PatientEntity sessP;

	public SessionEntity(Integer patientId) {
		this.patientId = patientId;
	}

	public SessionEntity() {
	}

	@Basic
	@Column(name = "patient_id")
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@Id
	@Column(name = "sess_id")
	public int getSessId() {
		return sessId;
	}

	public void setSessId(int sessId) {
		this.sessId = sessId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SessionEntity that = (SessionEntity) o;

		if (sessId != that.sessId) return false;
		if (patientId != null ? !patientId.equals(that.patientId) : that.patientId != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = patientId != null ? patientId.hashCode() : 0;
		result = 31 * result + sessId;
		return result;
	}

	@OneToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false, insertable = false, updatable = false)
	public PatientEntity getSessP() {
		return sessP;
	}

	public void setSessP(PatientEntity sessP) {
		this.sessP = sessP;
	}
}
