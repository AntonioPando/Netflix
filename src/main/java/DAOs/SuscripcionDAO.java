package DAOs;

import Models.Suscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SuscripcionDAO extends DAO<Suscripcion> {

    public SuscripcionDAO(Connection conn) {
        super.conn = conn;
    }

    private static final String INSERT =
        "INSERT INTO suscripcion (cuenta_id, tipo_id, fecha_contratacion, fecha_fin) VALUES (?,?,?,?)";

    private static final String DELETE =
        "DELETE FROM suscripcion WHERE id=?";

    private static final String UPDATE =
        "UPDATE suscripcion SET cuenta_id=?, tipo_id=?, fecha_contratacion=?, fecha_fin=? WHERE id=?";

    private static final String LISTALL =
        "SELECT * FROM suscripcion";

    private static final String LISTONE =
        "SELECT * FROM suscripcion WHERE id=?";

    @Override
    public void listUno(int id) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(LISTONE);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                convertir(rs);
                
            } else {
                System.out.println("No se encontró la suscripción con ID: " + id);
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
                convertir(rs);
               
            }

        } finally {
            cerrarEstados(stmt, rs);
        }
    }

    @Override
    public void insertar(Suscripcion sus) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(INSERT);

        cargarDatos(stmt, sus);

            stmt.executeUpdate();
            conn.commit();

            System.out.println("Suscripción añadida correctamente");

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error insertando suscripción", e);
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
                System.out.println("Suscripción eliminada correctamente");
            } else {
                System.out.println("No se encontró la suscripción");
            }

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error eliminando suscripción", e);
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void actualizar(Suscripcion sus) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

               cargarDatos(stmt, sus);
            stmt.setInt(5, sus.getId());

            int filas = stmt.executeUpdate();
            conn.commit();

            if (filas > 0) {
                System.out.println("Suscripción actualizada correctamente");
            } else {
                System.out.println("No se encontró la suscripción para actualizar");
            }

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error actualizando suscripción", e);
        } finally {
            cerrarEstados(stmt, null);
        }
    }

     @Override
    public void cargarDatos(PreparedStatement stmt, Suscripcion dato) throws SQLException {
        stmt.setInt(1, dato.getCuenta_id());
        stmt.setInt(2, dato.getTipo_id());
        stmt.setDate(3, dato.getFecha_contratacion());
        stmt.setDate(4, dato.getFecha_fin());
    }

    // Función convertir
    public Suscripcion convertir(ResultSet rs) throws SQLException {
        try { 
            return new Suscripcion(
                rs.getInt("id"),
                rs.getInt("cuenta_id"),
                rs.getInt("tipo_id"),
                rs.getDate("fecha_contratacion"),
                rs.getDate("fecha_fin")
            );
        } catch (SQLException e) {
            throw new SQLException("Error al convertir", e);
        }
    }
}