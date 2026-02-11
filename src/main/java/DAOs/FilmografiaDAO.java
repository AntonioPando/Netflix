/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DbManager.DbManager;
import Models.Filmografia;
import java.sql.PreparedStatement;
import java.sql.Connection; // Guarda la conexion en un objeto para que no se pierda en el aire
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException; // Nos indica el error SQL en caso de que falle la conexion

/**
 *
 * @author bigpa
 */
public class FilmografiaDAO {

    private static final String INSERT = "INSERT INTO filmografia(id,titulo,fecha_estreno,sinopsis,pais_id,clasificacion_id) VALUES (?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM filmografia WHERE id=?";
    private static final String UPDATE = "UPDATE filmografia SET titulo=?, fecha_estreno=?, sinopsis=?, pais_id=?, clasificacion_id=? WHERE id=?";
    private static final String LISTALL = "SELECT * FROM filmografia";
    private static final String LISTONE = "SELECT * FROM filmografia WHERE id=?";
    private Connection conn;

    public FilmografiaDAO(Connection conn) {
        this.conn = conn;
    }

    public void ListAll() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(LISTALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Filmografia filmo = new Filmografia(rs.getInt("id"),
                        rs.getString("titulo"), rs.getDate("fecha_estreno"),
                        rs.getString("sinopsis"), rs.getInt("pais_id"),
                        rs.getInt("clasificacion_id"));
                System.out.println(filmo.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e);
        } finally {
            cerrarEstados(stmt, rs);
        }

    }

    public void ListOne() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(LISTONE);
            stmt.setInt(1, 3);
            rs = stmt.executeQuery();
            rs.next();

            Filmografia filmo = new Filmografia(rs.getInt("id"),
                    rs.getString("titulo"), rs.getDate("fecha_estreno"),
                    rs.getString("sinopsis"), rs.getInt("pais_id"),
                    rs.getInt("clasificacion_id"));
            System.out.println(filmo.toString());
        } catch (SQLException e) {
            System.out.println(e.getMessage() + e);
        } finally {
            cerrarEstados(stmt, rs);
        }

    }

    //"INSERT INTO filmografia(id,titulo,fecha_estreno,sinopsis,pais_id,clasificacion_id) VALUES (?,?,?,?,?,?)";
    public void Insert() {

        PreparedStatement stmt = null;
        Filmografia filmo = new Filmografia(6, "Harry Potter", Date.valueOf("2026-02-09"), "Es un mago guay", 1, 2);

        try {

            stmt = conn.prepareStatement(INSERT);
            cargarDatosInsert(stmt, filmo);
            stmt.executeUpdate();
            conn.commit();
            System.out.println("Pelicula añadida correctamente");

        } catch (SQLException e) {
            hacerRollback(conn);
            System.out.println(e.getMessage());
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    //"DELETE FROM filmografia WHERE id=?";
    public void Delete(int id) throws SQLException{

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            conn.commit();
            
            if (filasAfectadas > 0) {
                System.out.println("Película con  eliminada correctamente.");
            } else {
                System.out.println("No se encontró ninguna película con el ID indicado");
            }
        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error eliminando una filmografia");
        } finally {
            cerrarEstados(stmt, null);
        }

    }

    //"UPDATE filmografia SET titulo=?, fecha_estreno=?, sinopsis=?, pais_id=?, clasificacion_id=? WHERE id=?"
    public void Update(Filmografia filmo) {
    PreparedStatement stmt = null;

    try {
        stmt = conn.prepareStatement(UPDATE);
        cargarDatosUpdate(stmt, filmo);
        int filas = stmt.executeUpdate();
        conn.commit();
        
        if (filas > 0) {
            System.out.println("Registro " + filmo.getId() + " actualizado.");
        } else {
            System.out.println("No se encontró el registro para actualizar.");
        }

    } catch (SQLException e) {
        hacerRollback(conn);
        System.out.println("Error en Update: " + e.getMessage());
    } finally {
        cerrarEstados(stmt, null);
    }
}

    public void cerrarEstados(PreparedStatement stmt, ResultSet rs) {

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

    public void cargarDatosInsert(PreparedStatement stmt, Filmografia filmo) {

        try {
            stmt.setInt(1, filmo.getId());
            stmt.setString(2, filmo.getTitulo());
            stmt.setDate(3, filmo.getFecha_estreno());
            stmt.setString(4, filmo.getSinopsis());
            stmt.setInt(5, filmo.getPais_id());
            stmt.setInt(6, filmo.getClasificacion_id());
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public void cargarDatosUpdate(PreparedStatement stmt, Filmografia filmo) throws SQLException {
        stmt.setString(1, filmo.getTitulo());
        stmt.setDate(2, filmo.getFecha_estreno());
        stmt.setString(3, filmo.getSinopsis());
        stmt.setInt(4, filmo.getPais_id());
        stmt.setInt(5, filmo.getClasificacion_id());
        
        stmt.setInt(6, filmo.getId());
    }
    
    public void hacerRollback(Connection conn){
        try {
            conn.rollback();
        } catch (SQLException e){
            System.out.println("Error al hacer rollback");
        }
    }
}
