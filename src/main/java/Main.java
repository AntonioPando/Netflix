/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import DAOs.FacturaDAO;
import DAOs.FilmografiaDAO;
import DbManager.DbManager;
import Models.Factura;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
/**
 *
 * @author bigpa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        DbManager manager = new DbManager();
        manager.conectar();
        manager.getConexion().setAutoCommit(false);
        
        
        //FilmografiaDAO filmografiaDAO = new FilmografiaDAO(manager.getConexion());
        FacturaDAO facturaDAO = new FacturaDAO(manager.getConexion());
        
        Factura fact = new Factura(6, 1, 1, 17, "efectivo", Date.valueOf("2026-02-09"));
        
        //filmografiaDAO.Insert();
        //filmografiaDAO.Delete();
        facturaDAO.listarTodo();
        //facturaDAO.insertar(fact);
        //filmografiaDAO.ListOne();
        
        manager.desconectar();
    }
    
}
