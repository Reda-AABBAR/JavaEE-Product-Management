package entity;

public class Creation {
    private Long id;
    private User user;
    private Product product;
    private int quantity;
    private Double price;
    
	public Creation(User user, Product product,Double price,int quantity) {
		super();
		this.user = user;
		this.product = product;
		this.quantity = quantity;
		this.setPrice(price);
	}
	public Creation() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public String toJson() {
		
		return "{" +
					"\"id\": " + "\"" + id +"\"" +
					",\"user\": " + user.toJson() +
					",\"product\": " + product.toJson() +
					",\"price\": " + "\"" + price +"\"" +
					",\"quantity\": " + "\"" + quantity +"\"" 
				+ "}";
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
    
    
}
