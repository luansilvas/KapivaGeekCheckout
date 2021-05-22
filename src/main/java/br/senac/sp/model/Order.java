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
public class Order {
   private String  purchaseorder_id;
   private double purchaseorder_amount;
   private int customer_customer_id;
   private int payment_payment_id;
   private String purchaseorder_status;
   private int address_address_id;
   private String diaPedido;

    public String getDiaPedido() {
        return diaPedido;
    }

    public Order() {
    }

    public Order(String purchaseorder_id, double purchaseorder_amount, int customer_customer_id, int payment_payment_id, String purchaseorder_status, int address_address_id, String diaPedido) {
        this.purchaseorder_id = purchaseorder_id;
        this.purchaseorder_amount = purchaseorder_amount;
        this.customer_customer_id = customer_customer_id;
        this.payment_payment_id = payment_payment_id;
        this.purchaseorder_status = purchaseorder_status;
        this.address_address_id = address_address_id;
        this.diaPedido = diaPedido;
    }

    public void setDiaPedido(String diaPedido) {
        this.diaPedido = diaPedido;
    }


    public double getPurchaseorder_amount() {
        return purchaseorder_amount;
    }

    public String getPurchaseorder_id() {
        return purchaseorder_id;
    }

    public void setPurchaseorder_id(String purchaseorder_id) {
        this.purchaseorder_id = purchaseorder_id;
    }

    public Order(String purchaseorder_id, double purchaseorder_amount, int customer_customer_id, int payment_payment_id, String purchaseorder_status, int address_address_id) {
        this.purchaseorder_id = purchaseorder_id;
        this.purchaseorder_amount = purchaseorder_amount;
        this.customer_customer_id = customer_customer_id;
        this.payment_payment_id = payment_payment_id;
        this.purchaseorder_status = purchaseorder_status;
        this.address_address_id = address_address_id;
    }

    public void setPurchaseorder_amount(double purchaseorder_amount) {
        this.purchaseorder_amount = purchaseorder_amount;
    }

    public int getCustomer_customer_id() {
        return customer_customer_id;
    }

    public void setCustomer_customer_id(int customer_customer_id) {
        this.customer_customer_id = customer_customer_id;
    }

    public int getPayment_payment_id() {
        return payment_payment_id;
    }

    public void setPayment_payment_id(int payment_payment_id) {
        this.payment_payment_id = payment_payment_id;
    }

    public String getPurchaseorder_status() {
        return purchaseorder_status;
    }

    public void setPurchaseorder_status(String purchaseorder_status) {
        this.purchaseorder_status = purchaseorder_status;
    }

    public int getAddress_address_id() {
        return address_address_id;
    }

    public void setAddress_address_id(int address_address_id) {
        this.address_address_id = address_address_id;
    }

    @Override
    public String toString() {
        return "Order{" + "purchaseorder_id=" + purchaseorder_id + ", purchaseorder_amount=" + purchaseorder_amount + ", customer_customer_id=" + customer_customer_id + ", payment_payment_id=" + payment_payment_id + ", purchaseorder_status=" + purchaseorder_status + ", address_address_id=" + address_address_id + '}';
    }
   
           
}
