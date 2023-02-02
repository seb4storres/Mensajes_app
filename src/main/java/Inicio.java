import java.sql.Connection;

public class Inicio {
    public static void main(String[] args) {
        Connections conexion = new Connections();

        try (Connection cnx = conexion.get_connection()) {
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
