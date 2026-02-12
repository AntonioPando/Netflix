
import DAOs.PaisDAO;
import DAOs.RepartoDAO;
import DAOs.SuscripcionDAO;
import DbManager.DbManager;
import Models.Pais;
import Models.Reparto;
import Models.Suscripcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        DbManager manager = new DbManager();
        manager.conectar();
        manager.getConexion().setAutoCommit(false);

        PaisDAO paisDAO = new PaisDAO(manager.getConexion());
        SuscripcionDAO suscripcionDAO = new SuscripcionDAO(manager.getConexion());
        RepartoDAO repartoDAO = new RepartoDAO(manager.getConexion());

        Pais pais = new Pais(0, "España");
        Suscripcion sus = new Suscripcion(0, 10, 2, Date.valueOf("2026-02-01"), Date.valueOf("2026-03-01"));
        Reparto rep = new Reparto(0, 10, "Leonardo DiCaprio", "Protagonista");

        //paisDAO.Delete();
       // paisDAO.listarTodo();
        //paisDAO.insertar(pais);
        //paisDAO.ListOne();
        
        //-------------------------//
        
        //suscripcionDAO.Delete();
        //suscripcionDAO.listarTodo();
        //suscripcionDAO.insertar(sus);
        //suscripcionDAO.ListOne();
        
        //-------------------------//
        
        //repartoDAO.Delete();
        //repartoDAO.listarTodo();
        //repartoDAO.insertar(rep);
        //repartoDAO.ListOne();
        
        manager.desconectar();

    }

}
