/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author luans
 */
@Getter
@Setter
public class Customer {
    private int customer_id;
    private String customer_name;
    private String customer_cpf;
    private String customer_email;
    private String customer_password;
    
    public Customer(int customer_id, String customer_name, String customer_cpf, String customer_email, String customer_password) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_cpf = customer_cpf;
        this.customer_email = customer_email;
        this.customer_password = customer_password;
    }

    public Customer(String customer_name, String customer_cpf, String customer_email, String customer_password) {
        this.customer_name = customer_name;
        this.customer_cpf = customer_cpf;
        this.customer_email = customer_email;
        this.customer_password = customer_password;
    }

    public Customer() {
    }
    
    
    public String codificarSenha(String senha) {
        return BCrypt.withDefaults().hashToString(12, senha.toCharArray());
    }

    public boolean validarSenha(String senha) {
        BCrypt.Result response = BCrypt.verifyer().verify(senha.toCharArray(), this.customer_password);
        return response.verified;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_cpf=" + customer_cpf + ", customer_email=" + customer_email + ", customer_password=" + customer_password + '}';
    }
    
    

}
