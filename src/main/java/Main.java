
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
        Pais pais = new Pais(6, "España");

      
        //paisDAO.Delete();
        //paisDAO.listarTodo();
        //paisDAO.insertar(pais);
        //paisDAO.ListOne();
        
        
        RepartoDAO repartoDAO = new RepartoDAO(manager.getConexion());
        Reparto rep = new Reparto(0, 10, "Leonardo DiCaprio", "Protagonista");

        
        //repartoDAO.Delete();
        //repartoDAO.listarTodo();
        //repartoDAO.insertar(rep);
        //repartoDAO.ListOne();
        
                SuscripcionDAO suscripcionDAO = new SuscripcionDAO(manager.getConexion());
                Suscripcion sus = new Suscripcion(1, 10, 2, Date.valueOf("2026-02-01"), Date.valueOf("2026-03-01") );


        //suscripcionDAO.Delete();
        //suscripcionDAO.listarTodo();
        //suscripcionDAO.insertar(sus);
        //suscripcionDAO.ListOne();

        
        manager.desconectar();
        
        


    }

}
