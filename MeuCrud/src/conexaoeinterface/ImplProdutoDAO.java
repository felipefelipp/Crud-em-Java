package conexaoeinterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ImplProdutoDAO implements ProdutoDAO {

    private Connection conexao;
    PreparedStatement pstmt = null;

    @Override
    public void gravar(Produto produto) {
        try {
             conexao = MercadoDbManager.obterConexao();

            System.out.println("Conectou");


             pstmt = conexao
                    .prepareStatement("INSERT INTO T_PRODUTO (CD_PRODUTO, NM_PRODUTO,  DT_VALIDADE, VL_PRODUTO)" +
                            "VALUES" +
                            "(SEQ_PRODUTO.NEXTVAL, ?,  ?, ?)");
            pstmt.setString(1, produto.getNome());
            pstmt.setDate(2, new java.sql.Date(produto.getDataValidade().getTimeInMillis()));
            pstmt.setDouble(3, produto.getValor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                conexao.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public List<Produto> buscarTodos() {
        // Cria uma lista de produtos
        List<Produto> produtos = new ArrayList<Produto>();
        ResultSet rs = null;
        try {

            conexao = MercadoDbManager.obterConexao();
             pstmt = conexao.prepareStatement("SELECT * FROM T_PRODUTO order by 1");

            rs = pstmt.executeQuery();

            //Percorre todos os registros encontrados
            while (rs.next()) {
                java.sql.Date data = rs.getDate("DT_VALIDADE");
                Calendar dtValidade = Calendar.getInstance();
                dtValidade.setTimeInMillis(data.getTime());
                //Cria um objeto Produto com as informações encontradas
                Produto produto = new Produto(rs.getInt("CD_PRODUTO")
                        ,rs.getString("NM_PRODUTO")
                        , rs.getFloat("VL_PRODUTO")
                        , dtValidade);


                // Adiciona o produto na lista
                produtos.add(produto);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return produtos;
    }

    @Override
    public Produto buscarTodosPorCodigo(int codigo) {
        try {
            conexao = MercadoDbManager.obterConexao();
            pstmt = conexao.prepareStatement("SELECT * FROM PRODUTO WHERE CD_PRODUTO = ?");
            pstmt.setInt(1, codigo);
            ResultSet result = pstmt.executeQuery();

            // Percorre todos os registros encontrados
            while(result.next()) {

                //Recupera os valores de cada coluna e imprime no console
                System.out.println( result.getInt(   "cd_produto") + " " +
                        result.getString("nm_produto") + " " +
                        result.getDouble("vl_produto") + " " +
                        result.getDate(  "dt_validade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      return null;
    }

    @Override
    public void atualizar(Produto produto) {
        try {
            conexao = MercadoDbManager.obterConexao();
            pstmt = conexao.prepareStatement(
                    "UPDATE T_PRODUTO SET NM_PRODUTO = ?, VL_PRODUTO = ?, DT_VALIDADE = ?" +
                            "WHERE CD_PRODUTO = ?");
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getValor());
            pstmt.setDate(3, new java.sql.Date(produto.getDataValidade().getTimeInMillis()));
            pstmt.setInt(4, produto.getCodigo());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remover(int codigo) {
        try {
            conexao = MercadoDbManager.obterConexao();
            pstmt = conexao.prepareStatement("DELETE FROM T_PRODUTO WHERE CD_PRODUTO + ?");
            pstmt.setInt(1, codigo); // Primeiro parâmetro (codigo)
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
