package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexaobd {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "maycon";
    private static final String URL = "jdbc:mysql://localhost:3306/crudrpg";

    public static Connection createConnectionToMySQL() throws Exception {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection con = createConnectionToMySQL();

        if (con != null) {
            System.out.println("Conexao feita com sucesso");
            con.close();
        }
    }
}