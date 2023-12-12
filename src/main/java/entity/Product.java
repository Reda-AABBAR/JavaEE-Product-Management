package entity;

import java.io.Serializable;

public class Product implements Serializable {
    private Long id;
    private String name;
	private Creation creation;
    private TypeProduit type;

    // Constructors
    public Product() {
    }

    public Product(String name,TypeProduit type,Creation creation) {
        this.name = name;
        this.setType(type);
        this.creation = creation;
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Creation getCreation() {
		return creation;
	}

	public void setCreation(Creation creation) {
		this.creation = creation;
	}

	public TypeProduit getType() {
		return type;
	}

	public void setType(TypeProduit type) {
		this.type = type;
	}

	public String toJson() {
		return "{"
				+"\"id\": " + "\"" + id +"\""
				+",\"name\": " + "\"" + name +"\""
				+",\"Type\": " + "\"" + type.getTypeName() +"\""
				+ "}";
	}
}

