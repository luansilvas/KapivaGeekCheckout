/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.model;

/**
 *
 * @author luans
 */
public class ProductOrder {
 private int product_purchaseorder_id;
 private String purchaseorder_purchaseorder_id;
 private int product_product_id;
 private int quantity;
 private double amount;

    public int getProduct_purchaseorder_id() {
        return product_purchaseorder_id;
    }

    public void setProduct_purchaseorder_id(int product_purchaseorder_id) {
        this.product_purchaseorder_id = product_purchaseorder_id;
    }

    @Override
    public String toString() {
        return "ProductOrder{" + "product_purchaseorder_id=" + product_purchaseorder_id + ", purchaseorder_purchaseorder_id=" + purchaseorder_purchaseorder_id + ", product_product_id=" + product_product_id + ", quantity=" + quantity + ", amount=" + amount + '}';
    }

    public ProductOrder(String purchaseorder_purchaseorder_id, int product_product_id, int quantity, double amount) {
        this.purchaseorder_purchaseorder_id = purchaseorder_purchaseorder_id;
        this.product_product_id = product_product_id;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getPurchaseorder_purchaseorder_id() {
        return purchaseorder_purchaseorder_id;
    }

    public void setPurchaseorder_purchaseorder_id(String purchaseorder_purchaseorder_id) {
        this.purchaseorder_purchaseorder_id = purchaseorder_purchaseorder_id;
    }

    public int getProduct_product_id() {
        return product_product_id;
    }

    public void setProduct_product_id(int product_product_id) {
        this.product_product_id = product_product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
 
         
    
}
