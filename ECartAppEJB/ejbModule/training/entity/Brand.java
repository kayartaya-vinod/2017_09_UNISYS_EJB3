package training.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String name;

	// all the products of "this" brand
	@OneToMany // (mappedBy = "brand") // get the mapping info from Product.brand
	@JoinColumn(name = "brand_id") // "brand_id" from "products" table
	private Set<Product> products;

	public Brand() {
	}

	public Brand(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
