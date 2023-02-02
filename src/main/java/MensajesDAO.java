import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MensajesDAO {
    public static void crearMensajeDB (Mensajes mensajes){
        Connections db_conect = new Connections();
        try (Connection conexion = db_conect.get_connection()){
            PreparedStatement ps = null;
            try {
                    String query = "INSERT INTO mensajes (mensaje, autor_mensaje ) VALUES (?,?)";
                    ps = conexion.prepareStatement(query);
                    ps.setString(1, mensajes.getMensaje());
                    ps.setString(2,mensajes.getAutor_mensaje());
                    ps.executeUpdate();
                System.out.println("mensaje creado");
            }catch (SQLException ex){
                System.out.println(ex);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public static void leerMensajesDB (){
        Connections db_conect = new Connections();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection conexion = db_conect.get_connection()) {

            String query ="SELECT * FROM mensajes";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println("ID: "+ rs.getInt("id_mensaje"));
                System.out.println("Mensaje: "+ rs.getString("mensaje"));
                System.out.println("Autor: "+ rs.getString("autor_mensaje"));
                System.out.println("Fecha: "+ rs.getString("fecha_mensaje"));
                System.out.println("");
            }

        }catch (SQLException e){
            System.out.println("No se pudieron obtener los mensajes");
            System.out.println(e);
        }
    }
    public static void borrarMensajeDB (int id_mensaje){
        Connections db_conect = new Connections();

        try (Connection conexion = db_conect.get_connection()) {
            PreparedStatement ps = null;

            try {
                String query = "DELETE FROM mensajes WHERE id_mensaje = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id_mensaje);
                ps.executeUpdate();
                System.out.println("El mensaje ah sido borrado");
            }catch (SQLException ex){
                System.out.println(ex);
                System.out.println("No se pudo borrar el mensaje ");
            }


        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public static void actualizarMensajeDB (Mensajes mensajes){
        Connections db_conect = new Connections();

        try (Connection conexion = db_conect.get_connection()) {
            PreparedStatement ps = null;

            try {
                String query = "UPDATE mensajes SET mensaje = ? WHERE id_mensaje = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1,mensajes.getMensaje());
                ps.setInt(2,mensajes.getId_mensaje());
                ps.executeUpdate();
                System.out.println("El mensaje ah sido actualizado");
            }catch (SQLException ex){
                System.out.println(ex);
                System.out.println("No se pudo actualizar el mensaje ");
            }


        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
