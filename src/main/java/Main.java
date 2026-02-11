/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import DAOs.FilmografiaDAO;
import DbManager.DbManager;
import java.sql.Connection;
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
        
        FilmografiaDAO filmografiaDAO = new FilmografiaDAO(manager.getConexion());
        
        //filmografiaDAO.Insert();
        //filmografiaDAO.Delete();
        filmografiaDAO.ListAll();
        //filmografiaDAO.ListOne();
        
        manager.desconectar();
    }
    
}
