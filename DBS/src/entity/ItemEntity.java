package entity;

import javax.persistence.*;

/**
 * Created by marcelforgac on 3.5.15.
 */
@Entity
@Table(name = "items", schema = "", catalog = "ikvb6ju5")
public class ItemEntity {
	private int itemsId;
	private Integer receptId;
	private Integer pocetKusov;
	private int orderId;
	private int drugId;
	private OrderEntity item_order;
	private DrugEntity item_drug;
	private ReceptEntity item_recept;

	public ItemEntity(int orderId, int drugId, Integer pocetKusov) {
		this.orderId = orderId;
		this.pocetKusov = pocetKusov;
		this.drugId = drugId;
		this.receptId = null;
	}

	public ItemEntity() {
	}

	@Id
	@Column(name = "items_id")
	public int getItemsId() {
		return itemsId;
	}

	public void setItemsId(int itemsId) {
		this.itemsId = itemsId;
	}

	@Basic
	@Column(name = "recept_id")
	public Integer getReceptId() {
		return receptId;
	}

	public void setReceptId(Integer receptId) {
		this.receptId = receptId;
	}

	@Basic
	@Column(name = "pocet_kusov")
	public Integer getPocetKusov() {
		return pocetKusov;
	}

	public void setPocetKusov(Integer pocetKusov) {
		this.pocetKusov = pocetKusov;
	}

	@Basic
	@Column(name = "order_id")
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Basic
	@Column(name = "drug_id")
	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ItemEntity that = (ItemEntity) o;

		if (drugId != that.drugId) return false;
		if (itemsId != that.itemsId) return false;
		if (orderId != that.orderId) return false;
		if (pocetKusov != null ? !pocetKusov.equals(that.pocetKusov) : that.pocetKusov != null) return false;
		if (receptId != null ? !receptId.equals(that.receptId) : that.receptId != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = itemsId;
		result = 31 * result + (receptId != null ? receptId.hashCode() : 0);
		result = 31 * result + (pocetKusov != null ? pocetKusov.hashCode() : 0);
		result = 31 * result + orderId;
		result = 31 * result + drugId;
		return result;
	}

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
	public OrderEntity getItem_order() {
		return item_order;
	}

	public void setItem_order(OrderEntity item_order) {
		this.item_order = item_order;
	}

	@ManyToOne
	@JoinColumn(name = "drug_id", referencedColumnName = "drug_id", nullable = false, insertable = false, updatable = false)
	public DrugEntity getItem_drug() {
		return item_drug;
	}

	public void setItem_drug(DrugEntity item_drug) {
		this.item_drug = item_drug;
	}

	@ManyToOne
	@JoinColumn(name = "recept_id", referencedColumnName = "recept_id", nullable = false, insertable = false, updatable = false)
	public ReceptEntity getItem_recept() {
		return item_recept;
	}

	public void setItem_recept(ReceptEntity item_recept) {
		this.item_recept = item_recept;
	}
}
