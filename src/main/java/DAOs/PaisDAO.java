package DAOs;

import Models.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisDAO extends DAO<Pais> {

    public PaisDAO(Connection conn) {
        super.conn = conn;
    }

    private static final String INSERT
            = "INSERT INTO Pais (nombre) VALUES (?)";

    private static final String DELETE
            = "DELETE FROM Pais WHERE id = ?";

    private static final String UPDATE
            = "UPDATE Pais SET nombre = ? WHERE id = ?";

    private static final String LISTALL
            = "SELECT * FROM Pais";

    private static final String LISTONE
            = "SELECT * FROM Pais WHERE id = ?";

    @Override
    public void listUno(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(LISTONE);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                System.out.println(pais.toString());
            } else {
                System.out.println("No se encontró el país con id: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } finally {
            cerrarEstados(stmt, rs);
        }
    }

    @Override
    public void listarTodo() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(LISTALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                System.out.println(pais.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } finally {
            cerrarEstados(stmt, rs);
        }
    }

    @Override
    public void insertar(Pais pais) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(INSERT);
            cargarDatosInsert(stmt, pais);
            stmt.executeUpdate();
            conn.commit();

            System.out.println("País añadido correctamente");

        } catch (SQLException e) {
            hacerRollback(conn);
            System.out.println("Error insertando país: " + e.getMessage());
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void eliminar(int id) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();
            conn.commit();

            if (filas > 0) {
                System.out.println("País eliminado correctamente");
            } else {
                System.out.println("No se encontró ningún país con ese ID");
            }

        } catch (SQLException e) {
            hacerRollback(conn);
            System.out.println("Error eliminando país: " + e.getMessage());
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void actualizar(Pais pais) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            cargarDatosUpdate(stmt, pais);

            int filas = stmt.executeUpdate();
            conn.commit();

            if (filas > 0) {
                System.out.println("País actualizado correctamente");
            } else {
                System.out.println("No se encontró el país para actualizar");
            }

        } catch (SQLException e) {
            hacerRollback(conn);
            System.out.println("Error actualizando país: " + e.getMessage());
        } finally {
            cerrarEstados(stmt, null);
        }
    }

    @Override
    public void cargarDatos(PreparedStatement stmt, Pais dato) {
        throw new UnsupportedOperationException("No se usa este método.");
    }

    public void cargarDatosInsert(PreparedStatement stmt, Pais pais) throws SQLException {
        stmt.setString(1, pais.getNombre());
    }

    public void cargarDatosUpdate(PreparedStatement stmt, Pais pais) throws SQLException {
        stmt.setString(1, pais.getNombre());
        stmt.setInt(2, pais.getId());
    }
}
