
import DAOs.PaisDAO;
import DAOs.RepartoDAO;
import DAOs.SuscripcionDAO;
import DAOs.Tipo_suscripcionDAO;
import DbManager.DbManager;
import Models.Pais;
import Models.Reparto;
import Models.Suscripcion;
import Models.Tipo_suscripcion;
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
        Tipo_suscripcionDAO tipo_suscripcionDAO = new Tipo_suscripcionDAO(manager.getConexion());

        Pais pais = new Pais(0, "España");
        Suscripcion sus = new Suscripcion(0, 10, 2, Date.valueOf("2026-02-01"), Date.valueOf("2026-03-01"));
        Reparto rep = new Reparto(0, 10, "Leonardo DiCaprio", "Protagonista");
        Tipo_suscripcion tipo = new Tipo_suscripcion(0, "Premium", 19.99, 12);

        // -------------------------//
        // PAIS
        // -------------------------//
        // paisDAO.eliminar(1);
        // paisDAO.listarTodo();
        // paisDAO.insertar(pais);
        // paisDAO.listUno(1);
        // paisDAO.actualizar(pais);
        // -------------------------//
        // SUSCRIPCION
        // -------------------------//
        // suscripcionDAO.eliminar(1);
        // suscripcionDAO.listarTodo();
        // suscripcionDAO.insertar(sus);
        // suscripcionDAO.listUno(1);
        // suscripcionDAO.actualizar(sus);
        // -------------------------//
        // REPARTO
        // -------------------------//
        // repartoDAO.eliminar(1);
        // repartoDAO.listarTodo();
        // repartoDAO.insertar(rep);
        // repartoDAO.listUno(1);
        // repartoDAO.actualizar(rep);
        // -------------------------//
        // TIPO SUSCRIPCION
        // -------------------------//
        // tipoDAO.eliminar(1);
        // tipoDAO.listarTodo();
        // tipoDAO.insertar(tipo);
        // tipoDAO.listUno(1);
        // tipoDAO.actualizar(tipo);
        
        manager.desconectar();

    }

}
