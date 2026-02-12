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
                System.out.println("No se encontró el país con id: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
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

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } finally {
            cerrarEstados(stmt, rs);
        }
    }

    @Override
    public void insertar(Pais pais) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(INSERT);
            cargarDatos(stmt, pais);
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
    public void eliminar(int id) throws SQLException {
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
    public void actualizar(Pais pais) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            cargarDatos(stmt, pais);
            stmt.setInt(2, pais.getId());

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
    public void cargarDatos(PreparedStatement stmt, Pais dato) throws SQLException {
       stmt.setString(1, dato.getNombre());
    }
    
    
    //Funcion convertir
    public Pais convertir(ResultSet rs) throws SQLException {

        try { 
            return new Pais(
             
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
           
        } catch (SQLException e) {
            throw new SQLException("Error al convertir", e);

        }
    }


}
