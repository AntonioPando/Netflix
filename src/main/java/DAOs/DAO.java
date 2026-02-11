/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bigpa
 */
public abstract class DAO <T>{
    
    protected static Connection conn;
    
    public abstract void listUno(int id);
    public abstract void listarTodo();
    public abstract void insertar(T dato_insertar);
    public abstract void eliminar(int id);
    public abstract void actualizar(T dato_actualizar);
    
    public abstract void cargarDatos(PreparedStatement stmt, T dato);
    
    public void hacerRollback(Connection conn){
        try {
            conn.rollback();
        } catch (SQLException e){
            System.out.println("Error al hacer rollback");
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
