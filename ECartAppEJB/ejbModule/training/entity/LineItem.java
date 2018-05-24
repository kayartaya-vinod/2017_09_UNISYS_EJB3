package training.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "line_items")
public class LineItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order = new Order(); // this field is refered in the Order.java @OneToMany(mappedBy="order")
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product = new Product();
	private Integer quantity;
	@Column(name = "unit_price")
	private Double unitPrice; // price on the day of purchase

	public LineItem() {
	}

	public LineItem(Integer orderId, Integer productId) {
		this.order.setId(orderId);
		this.product.setId(productId);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
