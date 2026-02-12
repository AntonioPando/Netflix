
import DAOs.FilmografiaDAO;
import DAOs.PaisDAO;
import DbManager.DbManager;
import Models.Factura;
import Models.Pais;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        DbManager manager = new DbManager();
        manager.conectar();
        manager.getConexion().setAutoCommit(false);

        //FilmografiaDAO filmografiaDAO = new FilmografiaDAO(manager.getConexion());
        PaisDAO paisDAO = new PaisDAO(manager.getConexion());

        Pais pais = new Pais(6, "España");

        //paisDAO.Insert();
        //paisDAO.Delete();
        paisDAO.listarTodo();
        //paisDAO.insertar(fact);
        //paisDAO.ListOne();

        manager.desconectar();
    }

}
