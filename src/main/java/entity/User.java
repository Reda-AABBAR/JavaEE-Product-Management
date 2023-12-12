package entity;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Creation creation;
    // Constructors
    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String password, Creation creation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creation = creation;
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toJson() {
        return "{" +
                "\"id\": " + "\"" + id + "\"" +
                ", \"firstName\": " +"\""+ firstName +"\""+
                ", \"lastName\": " + "\""+ lastName +"\""+
                ", \"email\": " +"\""+ email + "\""+
                ", \"password\": " +"\""+ password +"\""+
                "}";
    }

	public Creation getCreation() {
		return creation;
	}

	public void setCreation(Creation creation) {
		this.creation = creation;
	}
}

