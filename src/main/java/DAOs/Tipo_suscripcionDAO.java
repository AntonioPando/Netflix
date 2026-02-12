package DAOs;

import Models.Tipo_suscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tipo_suscripcionDAO extends DAO<Tipo_suscripcion> {

    public Tipo_suscripcionDAO(Connection conn) {
        super.conn = conn;
    }

    private static final String INSERT
            = "INSERT INTO tipo_suscripcion (nombre, precio, duracion_meses) VALUES (?,?,?)";

    private static final String DELETE
            = "DELETE FROM tipo_suscripcion WHERE tipo_id=?";

    private static final String UPDATE
            = "UPDATE tipo_suscripcion SET nombre=?, precio=?, duracion_meses=? WHERE tipo_id=?";

    private static final String LISTALL
            = "SELECT * FROM tipo_suscripcion";

    private static final String LISTONE
            = "SELECT * FROM tipo_suscripcion WHERE tipo_id=?";

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
                System.out.println("No se encontró el tipo de suscripción con ID: " + id);
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
    public void insertar(Tipo_suscripcion tipo) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(INSERT);

            cargarDatos(stmt, tipo);

            stmt.executeUpdate();
            conn.commit();

            System.out.println("Tipo de suscripción añadido correctamente");

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error insertando tipo de suscripción", e);
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
                System.out.println("Tipo de suscripción eliminado correctamente");
            } else {
                System.out.println("No se encontró el tipo de suscripción");
            }

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error eliminando tipo de suscripción", e);
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void actualizar(Tipo_suscripcion tipo) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

            cargarDatos(stmt, tipo);
            stmt.setInt(4, tipo.getTipo_id());

            int filas = stmt.executeUpdate();
            conn.commit();

            if (filas > 0) {
                System.out.println("Tipo de suscripción actualizado correctamente");
            } else {
                System.out.println("No se encontró el tipo de suscripción para actualizar");
            }

        } catch (SQLException e) {
            hacerRollback(conn);
            throw new SQLException("Error actualizando tipo de suscripción", e);
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void cargarDatos(PreparedStatement stmt, Tipo_suscripcion dato) throws SQLException {
        stmt.setString(1, dato.getNombre());
        stmt.setDouble(2, dato.getPrecio());
        stmt.setInt(3, dato.getDuracion_meses());
    }

    // Función convertir
    public Tipo_suscripcion convertir(ResultSet rs) throws SQLException {
        try {
            return new Tipo_suscripcion(
                    rs.getInt("tipo_id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("duracion_meses")
            );
        } catch (SQLException e) {
            throw new SQLException("Error al convertir Tipo_suscripcion", e);
        }
    }
}
