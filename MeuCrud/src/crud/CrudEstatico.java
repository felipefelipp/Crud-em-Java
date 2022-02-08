package crud;

import java.sql.*;

public class CrudEstatico {
    public static void main(String[] args) {
        try {
            // Define o driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Interface JDBC cuja implementação DriveManager abre uma conexão com a URL para acesso ao banco
            Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "fiap", "fiap");

            System.out.println("Conectou");

            Statement stmt = conexao.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO T_PRODUTO (CD_PRODUTO, NM_PRODUTO,  DT_VALIDADE, VL_PRODUTO)" +
                    "VALUES" +
                            "(SEQ_PRODUTO.NEXTVAL, 'Goiaba',  TO_DATE('17/07/2022', 'DD/MM/YYYY'), 35.78)");

            //String sqlUpdate = "UPDATE T_PRODUTO SET VL_PRODUTO = 13 WHERE CD_PRODUTO = 3";
            //stmt.executeUpdate(sqlUpdate);

            ResultSet result = stmt.executeQuery("SELECT * FROM T_PRODUTO");

            // Percorre todos os registros encontrados
            while(result.next()) {

                //Recupera os valores de cada coluna e imprime no console
                System.out.println( result.getInt(   "cd_produto") + " " +
                                    result.getString("nm_produto") + " " +
                                    result.getDouble("vl_produto") + " " +
                                    result.getDate(  "dt_validade"));
            }

            // Fecha a conexão

            conexao.close();


            // Tratamento de erro da conexão
        } catch (SQLException e) {
            System.err.println("Não foi possível conectar no ORACLE FIAP");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("O driver JDBC não foi encontrado!");
            e.printStackTrace();
        }
    }
}
