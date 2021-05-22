/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.bd.ConexaoDB;
import br.senac.sp.model.Order;
import br.senac.sp.model.ProductOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luans
 */
public class CartDAO {
 
        public static boolean addProductOrder(ProductOrder po){

        boolean retorno = false;
        Connection conexao;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = ConexaoDB.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into product_purchaseorder(purchaseorder_purchaseorder_id,product_product_id,quantity,amount) values(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

            instrucaoSQL.setString(1, po.getPurchaseorder_purchaseorder_id());
            instrucaoSQL.setDouble(2, po.getProduct_product_id());
            instrucaoSQL.setInt(3, po.getQuantity());
            instrucaoSQL.setDouble(4, po.getAmount());


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
}
