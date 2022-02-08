package conexaoeinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MercadoDbManager {


    public static Connection obterConexao() {
        Connection conexao = null;
        try {
            // Define o driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Interface JDBC cuja implementação DriveManager abre uma conexão com a URL para acesso ao banco
             conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "fiap", "fiap");

            // Tratamento de erro da conexão
        } catch (SQLException e) {
            System.err.println("Não foi possível conectar no ORACLE FIAP");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("O driver JDBC não foi encontrado!");
            e.printStackTrace();
        }
        return conexao;
    }
}
