/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.bd.ConexaoDB;
import br.senac.sp.model.Address;
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
public class AddressDAO {

    public static boolean addAddress(Address address) throws SQLException, ClassNotFoundException {
        boolean retorno = false;
        Connection conexao;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into customer_address(customer_customer_id,address_street,address_code,address_state_abbreviation,address_number,address_neighborhood,address_complement,address_type,address_title,isActive) values(?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            instrucaoSQL.setInt(1, address.getCustomer_customer_id());
            instrucaoSQL.setString(2, address.getAddress_street());
            instrucaoSQL.setString(3, address.getAddress_code());
            instrucaoSQL.setString(4, address.getAddress_state_abbreviation());
            instrucaoSQL.setString(5, address.getAddress_number());
            instrucaoSQL.setString(6, address.getAddress_neighborhood());
            instrucaoSQL.setString(7, address.getAddress_complement());
            instrucaoSQL.setString(8, address.getAddress_type());
            instrucaoSQL.setString(9, address.getAddress_title());
            instrucaoSQL.setString(10, "Ativo");
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;

                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                    address.setAddress_id(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o código de endereço.");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    ConexaoDB.fecharConexao();
                }
            } catch (SQLException ex) {
                System.out.println("Houve erro ao encerrar sua conexão. Tente novamente.");
            }
        }
        return retorno;
    }
    public static int addAddressReturnId(Address address){
        boolean retorno = false;
        Connection conexao;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into customer_address(customer_customer_id,address_street,address_code,address_state_abbreviation,address_number,address_neighborhood,address_complement,address_type,address_title,isActive) values(?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            instrucaoSQL.setInt(1, address.getCustomer_customer_id());
            instrucaoSQL.setString(2, address.getAddress_street());
            instrucaoSQL.setString(3, address.getAddress_code());
            instrucaoSQL.setString(4, address.getAddress_state_abbreviation());
            instrucaoSQL.setString(5, address.getAddress_number());
            instrucaoSQL.setString(6, address.getAddress_neighborhood());
            instrucaoSQL.setString(7, address.getAddress_complement());
            instrucaoSQL.setString(8, address.getAddress_type());
            instrucaoSQL.setString(9, address.getAddress_title());
            instrucaoSQL.setString(10, "Ativo");
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;

                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                    address.setAddress_id(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o código de endereço.");
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } 
        
        finally {
            try {
                if (instrucaoSQL != null) {
                    ConexaoDB.fecharConexao();
                }
            } catch (SQLException ex) {
                System.out.println("Houve erro ao encerrar sua conexão. Tente novamente.");
            }
        }
        return address.getAddress_id();
    }

    public static Address getAddress(int addressId) {
        Address address = null;
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoDB.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from customer_address where address_id=? and isActive='Ativo'");
            instrucaoSQL.setInt(1, addressId);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                int addressCode = rs.getInt("address_id");
                int customer_id = rs.getInt("customer_customer_id");
                String address_title = rs.getString("address_title");
                String address_street = rs.getString("address_street");
                String address_code = rs.getString("address_code");
                String address_state_abbreviation = rs.getString("address_state_abbreviation");
                String address_number = rs.getString("address_number");
                String address_neighborhood = rs.getString("address_neighborhood");
                String address_complement = rs.getString("address_complement");
                String address_type = rs.getString("address_type");
                String isActive = rs.getString("isActive");

                address = new Address(addressCode,customer_id, address_title,address_street, address_code, address_state_abbreviation, address_number, address_neighborhood, address_complement, address_type, isActive);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Não conseguimos encontrar um cliente com o ID passado.");
        }

        return address;
    }

    public static Address getAddressWithList(int addressId) {

        List<Address> AddressList = new ArrayList();
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Customer customer = null;
        try {
            conexao = ConexaoDB.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from address where address_id=? and isActive='Ativo'");
            instrucaoSQL.setInt(1, addressId);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {

                int addressCode = rs.getInt("address_id");
                int customer_id = rs.getInt("customer_customer_id");
                String address_title = rs.getString("address_title");
                String address_street = rs.getString("address_street");
                String address_code = rs.getString("address_code");
                String address_state_abbreviation = rs.getString("address_state_abbreviation");
                String address_number = rs.getString("address_number");
                String address_neighborhood = rs.getString("address_neighborhood");
                String address_complement = rs.getString("address_complement");
                String address_type = rs.getString("address_type");
                String isActive = rs.getString("isActive");

                Address address = new Address(addressCode, customer_id, address_title,address_street, address_code, address_state_abbreviation, address_number, address_neighborhood, address_complement, address_type, isActive);

                AddressList.add(address);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return AddressList.get(0);
    }

    public static boolean updateAddress(Address address) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("update customer_address set address_street=?,address_code=?,address_state_abbreviation=?,address_number=?,address_neighborhood=?,address_complement=?,address_title=? where address_id=?");

            instrucaoSQL.setString(1, address.getAddress_street());
            instrucaoSQL.setString(2, address.getAddress_code());
            instrucaoSQL.setString(3, address.getAddress_state_abbreviation());
            instrucaoSQL.setString(4, address.getAddress_number());
            instrucaoSQL.setString(5, address.getAddress_neighborhood());
            instrucaoSQL.setString(6, address.getAddress_complement());
            instrucaoSQL.setString(7, address.getAddress_title());
            instrucaoSQL.setInt(8, address.getAddress_id());
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
    

    public static Customer getCustomer(String userName) throws ClassNotFoundException, SQLException {
        Customer customer = new Customer();
        String sql = "select * from customer where customer ='" + userName + "'";

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

    public static List<Address> getCustomerAddresses(int customerId) {

        List<Address> AddressList = new ArrayList();
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Customer customer = null;
        try {
            conexao = ConexaoDB.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from customer_address where customer_customer_id=? and isActive = 'Ativo' and address_type='Entrega';");
            instrucaoSQL.setInt(1, customerId);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {

                int addressCode = rs.getInt("address_id");
                int customer_id = rs.getInt("customer_customer_id");
                String address_title = rs.getString("address_title");
                String address_street = rs.getString("address_street");
                String address_code = rs.getString("address_code");
                String address_state_abbreviation = rs.getString("address_state_abbreviation");
                String address_number = rs.getString("address_number");
                String address_neighborhood = rs.getString("address_neighborhood");
                String address_complement = rs.getString("address_complement");
                String address_type = rs.getString("address_type");
                String isActive = rs.getString("isActive");

                Address address = new Address(addressCode, customer_id, address_title,address_street, address_code, address_state_abbreviation, address_number, address_neighborhood, address_complement, address_type, isActive);

                AddressList.add(address);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return AddressList;
    }
        public static Address getCustomerIncomeAddresses(int customerId) {

        List<Address> AddressList = new ArrayList();
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Customer customer = null;
        try {
            conexao = ConexaoDB.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from customer_address where customer_customer_id=? and isActive = 'Ativo' and address_type='Faturamento';");
            instrucaoSQL.setInt(1, customerId);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {

                int addressCode = rs.getInt("address_id");
                int customer_id = rs.getInt("customer_customer_id");
                String address_title = rs.getString("address_title");
                String address_street = rs.getString("address_street");
                String address_code = rs.getString("address_code");
                String address_state_abbreviation = rs.getString("address_state_abbreviation");
                String address_number = rs.getString("address_number");
                String address_neighborhood = rs.getString("address_neighborhood");
                String address_complement = rs.getString("address_complement");
                String address_type = rs.getString("address_type");
                String isActive = rs.getString("isActive");

                Address address = new Address(addressCode, customer_id, address_title,address_street, address_code, address_state_abbreviation, address_number, address_neighborhood, address_complement, address_type, isActive);

                AddressList.add(address);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return AddressList.get(0);
    }
    
    public static void inativate (int address_id){
 
        String sql = "update customer_address set isActive = 'Inativo' where address_id = '"+address_id+"'";
        try{
    try (Connection conn = ConexaoDB.abrirConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
           
        }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }

}
