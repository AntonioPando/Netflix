/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DbManager.Logger;

/**
 *
 * @author bigpa
 */
public abstract class DAO <T>{
    
    protected static Connection conn;
    protected static Logger logger = new Logger();
    
    public abstract void listUno(int id) throws SQLException;
    public abstract void listarTodo() throws SQLException;
    public abstract void insertar(T dato_insertar) throws SQLException;
    public abstract void eliminar(int id) throws SQLException;
    public abstract void actualizar(T dato_actualizar) throws SQLException;
    
    public abstract void cargarDatos(PreparedStatement stmt, T dato);
    
    public void hacerRollback(Connection conn) throws SQLException{
        try {
            conn.rollback();
        } catch (SQLException e){
            logger.info("Mensaje de error");
            throw new SQLException("Error al hacer rollback" + e.getMessage());
        }
    }
    
    public void cerrarEstados(PreparedStatement stmt, ResultSet rs){
        if (stmt != null) try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar Statement" + e);
        }

        if (rs != null) try {
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar Result Set" + e);
        }
    }
    
    
    
    
    
    
    
    
}
