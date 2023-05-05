package ClassesBancoDeDados;
import java.sql.*;
import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *  A classe {@code BancoDeDados} permite acessar os banco de dados que estao criados no programa my SQL
 */

public class BancoDeDados {

    private static String dataBase = "locadora";
    private static String user = "root";
    private static String pass = "123A";
    protected static Connection conexao = null;

    /**
     * cria a coneccao com banco de dados do programa my SQL
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public BancoDeDados() throws ClassNotFoundException, SQLException {
        if (conexao == null) conecta();
    }

    /**
     * O metodo realiza a coneccao com banco de dados que está no programa my SQL
     * @return um boolean que indica se a conecção com o programa my SQL funcionou
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static boolean conecta() throws ClassNotFoundException, SQLException{
        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUser(user);
            ds.setPassword(pass);
            ds.setServerName("localhost");
            ds.setPort(3306);
            ds.setServerTimezone("UTC");
            ds.setDatabaseName(dataBase);
            conexao = ds.getConnection();

            return true;
        } catch (SQLException e) { System.out.println(e);return false; }
    }

    /**
     * Desconecta a ligacao criada com o programa e o banco de dados
     * @return um booleano para verificar se foi desconecto com sucesso
     */
    public static boolean desconecta() {
        try {
            conexao.close();
            return true;
        } catch (SQLException e) { return false; }
    }
}

