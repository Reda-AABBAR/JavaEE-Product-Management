package validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidation {
	private String email;
	private String password;
	private Map<String,Object> erreur;
	public LoginValidation(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.erreur = new HashMap<>();
	}
	
	 public Map<String, Object> validate() {
	        validateEmail();
	        validatePassword();

	        return erreur;
	    }

	    private void validateEmail() {
	        if (email == null || email.trim().isEmpty()) {
	            erreur.put("email", "Entrer votre email");
	        } else {
	            // Basic email format validation using regex
	            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	            Pattern pattern = Pattern.compile(emailRegex);
	            Matcher matcher = pattern.matcher(email);

	            if (!matcher.matches()) {
	                erreur.put("email", "Entrer un email valide");
	            }
	        }
	    }

	    private void validatePassword() {
	        if (password == null || password.trim().isEmpty()) {
	            erreur.put("Password", "Entrer le password");
	        } else {
	            // Add more password validation rules if needed
	            if (password.length() < 8) {
	                erreur.put("Password", "le password doit être au moins 8 character");
	            }
	        }
	    }
	
}
