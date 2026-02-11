/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package DbManager;
import java.sql.DriverManager; // Maneja las credenciales
import java.sql.Connection; // Guarda la conexion en un objeto para que no se pierda en el aire
import java.sql.SQLException; // Nos indica el error SQL en caso de que falle la conexion
import io.github.cdimascio.dotenv.Dotenv;
/**
 *
 * @author bigpa
 */
public class DbManager {
    
    private static final Dotenv dotenv = Dotenv.load(); // Para ocultar las credenciales
    private static final String USER = dotenv.get("DB_USER"); // Accede a las variables del archivo que tenemos en la raiz
    private static final String PASS = dotenv.get("DB_PASS");
    private static final String URL = dotenv.get("DB_URL");
    private Connection conexion = null;
    
    //Metodos
    
    /*
    Conecta con la base de datos. En caso de que 
    */
    public void conectar(){
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar: " + e.getMessage());
        }
    }
    
    public void desconectar(){
        try{
            if(this.conexion != null && !this.conexion.isClosed()) {
                this.conexion.close();
                System.out.println("Desconexion exitosa");
            }
        } catch(SQLException e){
            System.out.println(e.getMessage()+" ERROR AL CONECTAR ");
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    
}
