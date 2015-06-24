package entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by marcelforgac on 3.5.15.
 */
@Entity
@Table(name = "orders", schema = "", catalog = "DBS")
public class OrderEntity {
	private int orderId;
	private String date;
	private Integer serialNumber;
	private int patientId;
	private Set<ItemEntity> item_order;
	private PatientEntity patientOrder;

	public OrderEntity(String date, Integer serialNumber, int patientId) {
		this.date = date;
		this.serialNumber = serialNumber;
		this.patientId = patientId;
	}

	public OrderEntity() {
	}

	@Id
	@Column(name = "order_id")
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	@Column(name = "serial_number")
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Basic
	@Column(name = "patient_id")
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderEntity that = (OrderEntity) o;

		if (orderId != that.orderId) return false;
		if (patientId != that.patientId) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		if (serialNumber != null ? !serialNumber.equals(that.serialNumber) : that.serialNumber != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = orderId;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
		result = 31 * result + patientId;
		return result;
	}

	@OneToMany(mappedBy = "item_order")
	public Set<ItemEntity> getItem_order() {
		return item_order;
	}

	public void setItem_order(Set<ItemEntity> item_order) {
		this.item_order = item_order;
	}

	@ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false, insertable = false, updatable = false)
	public PatientEntity getPatientOrder() {
		return patientOrder;
	}

	public void setPatientOrder(PatientEntity patientOrder) {
		this.patientOrder = patientOrder;
	}
}
