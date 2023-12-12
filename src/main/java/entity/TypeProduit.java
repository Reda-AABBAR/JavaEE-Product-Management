package entity;

public class TypeProduit {
    private Long id;
    private String typeName;

    // Constructors
    public TypeProduit() {
    }

    public TypeProduit(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    // toString method for easy debugging
    public String toJson() {
        return "{" +
                "\"id\":" + "\"" + id +"\"" +
                ", \"typeName\":" +"\""+ typeName + "\"" +
                '}';
    }
}
