/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;





public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> lista;

    public ProdutosDAO() {
        this.lista = new ArrayList<>();
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try {

        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);

        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());

        prep.executeUpdate();

        JOptionPane.showMessageDialog(null,
                "Produto cadastrado no banco!");

    } catch (HeadlessException | SQLException erro) {

        JOptionPane.showMessageDialog(null,
                "Erro ao cadastrar: " + erro.getMessage());
    }
       
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> lista = new ArrayList<>();

    String sql = "SELECT * FROM produtos";

    try {

        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);

        resultset = prep.executeQuery();

        while (resultset.next()) {

            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            lista.add(produto);
        }

    } catch (SQLException erro) {

        JOptionPane.showMessageDialog(null,
                "Erro ao listar produtos: " + erro);

    }
        
        return lista;
    }
    public void venderProduto(int idProduto) {

    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try {

        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);

        prep.setInt(1, idProduto);

        prep.executeUpdate();

        JOptionPane.showMessageDialog(null,
                "Produto vendido com sucesso!");

    } catch (SQLException erro) {

        JOptionPane.showMessageDialog(null,
                "Erro ao vender produto: " + erro.getMessage());
    }
}

    
    
        
}

