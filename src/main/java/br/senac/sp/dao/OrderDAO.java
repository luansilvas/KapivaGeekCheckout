/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.bd.ConexaoDB;
import br.senac.sp.model.Order;
import br.senac.sp.model.Product;
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
public class OrderDAO {

    public static boolean addOrder(Order o){

        boolean retorno = false;
        Connection conexao;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into purchaseorder(purchaseorder_id,purchaseorder_amount,customer_customer_id,payment_payment_id,purchaseorder_status,address_address_id,diaPedido) values(?,?,?,?,?,?,now());", Statement.RETURN_GENERATED_KEYS);

            instrucaoSQL.setString(1, o.getPurchaseorder_id());
            instrucaoSQL.setDouble(2, o.getPurchaseorder_amount());
            instrucaoSQL.setInt(3, o.getCustomer_customer_id());
            instrucaoSQL.setInt(4, o.getPayment_payment_id());
            instrucaoSQL.setString(5, o.getPurchaseorder_status());
            instrucaoSQL.setInt(6, o.getAddress_address_id());

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;

                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (!generatedKeys.next()) {
                    throw new SQLException("Falha ao obter o código do Funcionário.");
                }
            }

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
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
    
    public static List<Order> getOrders(int customerId){
        String sql = "select * from purchaseorder where customer_customer_id="+customerId + ";";
        System.out.println(sql);
        ArrayList<Order> prodBd = new ArrayList<>();

        try (Connection conn = ConexaoDB.abrirConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {// enquanto tiver empresas adiciona no array

                Order pedido = new Order();
                pedido.setPurchaseorder_id(rs.getString("purchaseorder_id"));
                pedido.setPurchaseorder_amount(rs.getDouble("purchaseorder_amount"));
                pedido.setCustomer_customer_id(rs.getInt("customer_customer_id"));
                pedido.setPayment_payment_id(rs.getInt("payment_payment_id"));
                pedido.setPurchaseorder_status(rs.getString("purchaseorder_status"));
                pedido.setAddress_address_id(rs.getInt("address_address_id"));
                pedido.setPurchaseorder_status(rs.getString("purchaseorder_status"));
                pedido.setDiaPedido(rs.getString("diaPedido"));
                System.out.println("ESSE É O ID DO PEDIDO " + pedido.getPurchaseorder_id());
                prodBd.add(pedido);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return prodBd;
    }
    
    public static List<Product> getProdPedido(String id){
        String sql = "select p.name_prod,p.price,ppo.quantity from product_purchaseorder as ppo INNER JOIN purchaseorder as po on po.purchaseorder_id = ppo.purchaseorder_purchaseorder_id INNER JOIN products as p on ppo.product_product_id = p.prod_id where po.purchaseorder_id='" + id + "'";
        
        System.out.println(sql);
        ArrayList<Product> prodBd = new ArrayList<>();

        try (Connection conn = ConexaoDB.abrirConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {// enquanto tiver empresas adiciona no array

                Product pedido = new Product();
                pedido.setProductName(rs.getString("name_prod"));
                pedido.setPrice(rs.getDouble("price"));
                pedido.setQuantity(rs.getInt("quantity"));
                prodBd.add(pedido);
                System.out.println(prodBd.toString());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return prodBd;
    }
}
