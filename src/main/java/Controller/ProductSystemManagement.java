package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import convertor.MapToJson;
import entity.Creation;
import entity.Product;
import entity.TypeProduit;
import entity.User;
import repository.CreationRepository;
import repository.ProductRepoditory;
import repository.TypeProductRepositiry;
import repository.UserRepository;
import validation.LoginValidation;

/**
 * Servlet implementation class ProductSystemManagement
 */
@WebServlet("/productSystemManagement")
public class ProductSystemManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductSystemManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ok");
		String page = request.getParameter("page");
		Map<String,Object> data = new HashMap<>();
		if(page.equals("ProductDesplay")) {
			Long idUser = Long.valueOf(request.getParameter("id")) ;
			List<Creation> creations =  CreationRepository.getAllCreationOtherUsers(idUser);
			int i = 1;
			for (Creation creation : creations) {
				data.put("creation " + i++, creation);
			}
		
			String products = MapToJson.convertHashMapToJson(data);
			PrintWriter out = response.getWriter();
	        out.print(products);
		}else if(page.equals("addProduct")) {
			System.out.println("in the test");
			List<TypeProduit> typeProducts = TypeProductRepositiry.getAllTypeProduct();
			int i = 1;
			for (TypeProduit typeProduit : typeProducts) {
				data.put("type " + i++, typeProduit);
			}
			
			String types = MapToJson.convertHashMapToJson(data);
			PrintWriter out = response.getWriter();
			System.out.println(types);
	        out.print(types);
			
		}else if(page.equals("myProduct")) {
			Long idUser = Long.valueOf(request.getParameter("id")) ;
			List<Creation> creations =  CreationRepository.getAllCreationOfUser(idUser);
			int i = 1;
			for (Creation creation : creations) {
				data.put("creation " + i++, creation);
			}
		
			String products = MapToJson.convertHashMapToJson(data);
			PrintWriter out = response.getWriter();
	        out.print(products);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		
		if(page.equals("Login")) {
			User user = null;
			StringBuilder sb = new StringBuilder();
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line);
	            }
	        }
	        
	        String data = sb.toString();
	        String email = data.split("\"email\":")[1].split(",")[0].replaceAll("\"", "");
	        String password = data.split("\"password\":")[1].replaceAll("\"", "");
	        password = password.replace("}", "");
	        
	        LoginValidation loginValidation = new LoginValidation(email, password);
	        
	        Map<String, Object> errors = loginValidation.validate();
	        if(errors.isEmpty())
	        	user = UserRepository.getUserByLogin(email, password);
	        response.setContentType("application/json");
	        
	        StringBuilder jsonData = new StringBuilder();
	        jsonData.append("{");
	        if(user != null)
	        jsonData.append("\"user\":" + user.toJson());
	        else
	        jsonData.append("\"user\":" + null);	
	        jsonData.append(", \"errors\":" + MapToJson.convertHashMapToJson(errors));
	        jsonData.append("}");
	        
	        PrintWriter out = response.getWriter();
	        System.out.println(jsonData.toString());
	        out.print(jsonData.toString());
			
		}else if(page.equals(page)) {
			StringBuilder sb = new StringBuilder();
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line);
	            }
	        }
	        
	        String data = sb.toString();
	        System.out.println(data);
	        Long idUser = Long.valueOf(data.split("\"idUser\":")[1].split(",")[0].replaceAll("\"", ""));
	        String productName = data.split("\"name\":")[1].split(",")[0].replaceAll("\"", "");
	        Double productPrice = Double.valueOf(data.split("\"price\":")[1].split(",")[0].replaceAll("\"", ""));
	        int productQuantity = Integer.valueOf(data.split("\"quantity\":")[1].split(",")[0].replaceAll("\"", ""));
	        Long productType = Long.valueOf(data.split("\"type\":")[1].replaceAll("\"", "").replace("}", ""));
	        Product product = ProductRepoditory.addProduct(new Product(productName,TypeProductRepositiry.getTypeProductById(productType),null));
	        CreationRepository.addCreation(new Creation(UserRepository.getUserById(idUser),product,productPrice,productQuantity));
	        
	        PrintWriter out = response.getWriter();
	        out.print("product added successfully");
		}
        
        
	}

}
