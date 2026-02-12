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
        "INSERT INTO suscripcion (id, cuenta_id, tipo_id, fecha_contratacion, fecha_fin) VALUES (?,?,?,?,?)";

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
                Suscripcion sus = new Suscripcion(
                        rs.getInt("id"),
                        rs.getInt("cuenta_id"),
                        rs.getInt("tipo_id"),
                        rs.getDate("fecha_contratacion"),
                        rs.getDate("fecha_fin")
                );
                System.out.println(sus.toString());
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
                Suscripcion sus = new Suscripcion(
                        rs.getInt("id"),
                        rs.getInt("cuenta_id"),
                        rs.getInt("tipo_id"),
                        rs.getDate("fecha_contratacion"),
                        rs.getDate("fecha_fin")
                );
                System.out.println(sus.toString());
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

            stmt.setInt(1, sus.getId());
            stmt.setInt(2, sus.getCuenta_id());
            stmt.setInt(3, sus.getTipo_id());
            stmt.setDate(4, sus.getFecha_contratacion());
            stmt.setDate(5, sus.getFecha_fin());

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

            stmt.setInt(1, sus.getCuenta_id());
            stmt.setInt(2, sus.getTipo_id());
            stmt.setDate(3, sus.getFecha_contratacion());
            stmt.setDate(4, sus.getFecha_fin());
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
    public void cargarDatos(PreparedStatement stmt, Suscripcion dato) {
        throw new UnsupportedOperationException("No se usa este método.");
    }
}
