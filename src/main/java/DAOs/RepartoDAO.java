package DAOs;

import Models.Reparto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepartoDAO extends DAO<Reparto> {

    public RepartoDAO(Connection conn) {
        super.conn = conn;
    }

    private static final String INSERT
            = "INSERT INTO reparto (id_filmografia, nombre_actor, papel) VALUES (?,?,?)";

    private static final String DELETE
            = "DELETE FROM reparto WHERE id_reparto=?";

    private static final String UPDATE
            = "UPDATE reparto SET id_filmografia=?, nombre_actor=?, papel=? WHERE id_reparto=?";

    private static final String LISTALL
            = "SELECT * FROM reparto";

    private static final String LISTONE
            = "SELECT * FROM reparto WHERE id_reparto=?";

    @Override
    public void listUno(int id) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(LISTONE);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                 System.out.println(convertir(rs));

            } else {
                System.out.println("No se encontró el reparto con ID: " + id);
            }

        } finally {
            cerrarEstados(stmt, rs);
        }
    }

    @Override
    public void listarTodo() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(LISTALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
               System.out.println(convertir(rs));

            }

        } finally {
            cerrarEstados(stmt, rs);
        }
    }

    @Override
    public void insertar(Reparto rep) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(INSERT);
            cargarDatos(stmt, rep);
            stmt.executeUpdate();
            conn.commit();

            System.out.println("Reparto añadido correctamente");

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error insertando reparto", e);
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();
            conn.commit();

            if (filas > 0) {
                System.out.println("Reparto eliminado correctamente");
            } else {
                System.out.println("No se encontró el reparto");
            }

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error eliminando reparto", e);
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void actualizar(Reparto rep) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

            cargarDatos(stmt, rep);
            stmt.setInt(4, rep.getId_reparto());

            int filas = stmt.executeUpdate();
            conn.commit();

            if (filas > 0) {
                System.out.println("Reparto actualizado correctamente");
            } else {
                System.out.println("No se encontró el reparto para actualizar");
            }

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error actualizando reparto", e);
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void cargarDatos(PreparedStatement stmt, Reparto dato) throws SQLException {
        stmt.setInt(1, dato.getId_filmografia());
        stmt.setString(2, dato.getNombre_actor());
        stmt.setString(3, dato.getPapel());
    }

    //Funcion convertir
    public Reparto convertir(ResultSet rs) throws SQLException {

        try { 
            return new Reparto(
             
                        
                        rs.getInt("id_reparto"),
                        rs.getInt("id_filmografia"),
                        rs.getString("nombre_actor"),
                        rs.getString("papel")
                );
               
           
        } catch (SQLException e) {
            throw new SQLException("Error al convertir", e);

        }
    }


}