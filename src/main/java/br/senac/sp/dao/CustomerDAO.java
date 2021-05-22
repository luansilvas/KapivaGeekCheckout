/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.bd.ConexaoDB;
import br.senac.sp.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luans
 */
public class CustomerDAO {

    public static int addCustomer(Customer customer){
        boolean retorno = false;
        Connection conexao;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into customer(customer_name,customer_cpf,customer_email,customer_password) values(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

            instrucaoSQL.setString(1, customer.getCustomer_name());
            instrucaoSQL.setString(2, customer.getCustomer_cpf());
            instrucaoSQL.setString(3, customer.getCustomer_email());
            instrucaoSQL.setString(4, customer.codificarSenha(customer.getCustomer_password()));

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;

                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customer.setCustomer_id(generatedKeys.getInt(1));

                } else {
                    throw new SQLException("Falha ao obter o código do Cliente.");
                }
            } else {

            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Houve erro na conexão");
        } finally {
            try {
                if (instrucaoSQL != null) {
                    ConexaoDB.fecharConexao();
                }
            } catch (SQLException ex) {
                System.out.println("Houve erro ao encerrar sua conexão. Tente novamente.");
            }
        }
        return customer.getCustomer_id();
    }

    public static Customer getCustomer(int customerId) {
        Customer customer = null;
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoDB.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from customer where customer_id=?");
            instrucaoSQL.setInt(1, customerId);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {

                int customerCode = rs.getInt("customer_id");
                String customerName = rs.getString("customer_name");
                String customerCPF = rs.getString("customer_cpf");
                String customerEmail = rs.getString("customer_email");
                String customerPassword = "";

                customer = new Customer(customerCode, customerName, customerCPF, customerEmail, customerPassword);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Não conseguimos encontrar um cliente com o ID passado.");
        }

        return customer;
    }

    public static Customer getCustomerWithList(int customerId) {

        List<Customer> CustomerList = new ArrayList();
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Customer customer = null;
        try {
            conexao = ConexaoDB.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from customer where customer_id=?");
            instrucaoSQL.setInt(1, customerId);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {

                int customerCode = rs.getInt("customer_id");
                String customerName = rs.getString("customer_name");
                String customerCPF = rs.getString("customer_cpf");
                String customerEmail = rs.getString("customer_email");
                String customerPassword = "";

                customer = new Customer(customerCode, customerName, customerCPF, customerEmail, customerPassword);

                CustomerList.add(customer);
            }
        } catch (ClassNotFoundException | SQLException ex) {
        }

        return CustomerList.get(0);
    }

    public static boolean updateCustomer(Customer customer) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("update customer set customer_name=? where customer_id=?");

            instrucaoSQL.setString(1, customer.getCustomer_name());
            instrucaoSQL.setInt(2, customer.getCustomer_id());

            instrucaoSQL.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                retorno = true;
                conexao.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return retorno;

        }
    }

    public static boolean updateCustomerPassword(Customer customer) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("update customer set customer_password = ? where customer_id=?");

            instrucaoSQL.setString(1, customer.codificarSenha(customer.getCustomer_password()));
            instrucaoSQL.setInt(2, customer.getCustomer_id());
            instrucaoSQL.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                retorno = true;
                conexao.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return retorno;

        }
    }
public static boolean deleteCustomer(int customerId) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("delete from customer where customer_id=?");

            instrucaoSQL.setInt(1, customerId);


            instrucaoSQL.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                retorno = true;
                conexao.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return retorno;

        }
    }


    public static boolean CPFExists(String CPF) {

        List<Customer> CustomerList = new ArrayList();
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Customer customer = null;
        try {
            conexao = ConexaoDB.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from customer where customer_cpf=?");
            instrucaoSQL.setString(1, CPF);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {

                int customerCode = rs.getInt("customer_id");
                String customerName = rs.getString("customer_name");
                String customerCPF = rs.getString("customer_cpf");
                String customerEmail = rs.getString("customer_email");
                String customerPassword = "";

                customer = new Customer(customerCode, customerName, customerCPF, customerEmail, customerPassword);

                CustomerList.add(customer);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao realizar a busca "+ex.getMessage());
        }

        if (CustomerList.size()>0) return true;
        return false;
    }

        public static boolean EmailExists(String email) {

        List<Customer> CustomerList = new ArrayList();
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Customer customer = null;
        try {
            conexao = ConexaoDB.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from customer where customer_email=?");
            instrucaoSQL.setString(1, email);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {

                int customerCode = rs.getInt("customer_id");
                String customerName = rs.getString("customer_name");
                String customerCPF = rs.getString("customer_cpf");
                String customerEmail = rs.getString("customer_email");
                String customerPassword = "";

                customer = new Customer(customerCode, customerName, customerCPF, customerEmail, customerPassword);

                CustomerList.add(customer);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao realizar a busca "+ex.getMessage());
        }

        if (CustomerList.size()>0) return true;
        return false;
    }
        
     public static Customer validaLogin(String email) throws ClassNotFoundException, SQLException {
        Customer customer = new Customer();
        String sql = "select * from customer where customer_email ='"+email+"'";
        try (Connection conn = ConexaoDB.abrirConexao();
                PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_cpf(rs.getString("customer_cpf"));
                customer.setCustomer_email(rs.getString("customer_email"));
                customer.setCustomer_password(rs.getString("customer_password"));

                return customer;
            }
        }
        return null;
    }   
}
