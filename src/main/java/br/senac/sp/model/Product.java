package br.senac.sp.model;

import lombok.Getter;
import lombok.Setter;
import br.senac.sp.dao.*;

/**
 *
 * @author luans
 */
@Getter
@Setter
public class Product {

    private int productId;
    private String productName;
    private String productFullName;
    private double price;
    private int quantity;
    private int stars;
    private String status;
    private String category;
    private String path_MainImg;
    private double totalPrice;
    private String size;

    public Product(int productId, String productName, String productFullName, double price, int quantity, int stars, String status, String category) {
        try {
            this.productId = productId;
            this.productName = productName;
            this.productFullName = productFullName;
            this.price = price;
            this.quantity = quantity;
            this.stars = stars;
            this.status = status;
            this.category = category;
            
        } catch (Exception e) {
            System.out.println("There was an error building the product object");
            System.out.println("Full error message: {" + e.getLocalizedMessage() + "}");
        }
    }

    public Product() {
    }
    
    public boolean getValueStatus(String status){
        if(status.equals("Ativo"))
            return true;
        else
            return false;      
}
    public boolean verifyLastId(int id){
       ProductDAO dao = new ProductDAO();
       if(dao.LastItemId() == id)
           return true;
       
       
       return false;
       
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", productFullName=" + productFullName + ", price=" + price + ", quantity=" + quantity + ", stars=" + stars + ", status=" + status + ", category=" + category + ", path_MainImg=" + path_MainImg + ", totalPrice=" + totalPrice + '}';
    }



   

   
    
    
    

}
