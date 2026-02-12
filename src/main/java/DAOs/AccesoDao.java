package DAOs;

import Models.Acceso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AccesoDao extends DAO<Acceso> {

    private static final String INSERT = "INSERT INTO Acceso (id_acceso, id_filmografia, id_cuenta, fecha_acceso, tipo_suscripcion_id) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM Acceso WHERE id_acceso=?";
    private static final String UPDATE = "UPDATE Acceso SET id_filmografia=?, id_cuenta=?, fecha_acceso=?, tipo_suscripcion_id=? WHERE id_acceso=?";
    private static final String LISTALL = "SELECT * FROM Acceso";
    private static final String LISTONE = "SELECT * FROM Acceso WHERE id_acceso=?";

    // méttodo para cargar datos:

    public void cargarDatos (PreparedStatement stmt, Acceso a) {
        try {
            stmt.setInt(1, a.getId_acceso());
            stmt.setInt(2, a.getId_filmografia());
            stmt.setInt(3, a.getId_cuenta());
            stmt.setDate(4, a.getFecha_acceso());
            stmt.setInt(5, a.getTipo_suscripcion_id());
        } catch (SQLException e) {
            System.out.println("Error al cargar datos " + e.getMessage());

        }
    }

    //Méttodo para insertar

    public void insertar (Acceso a) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(INSERT);
            cargarDatos(stmt, a);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("error al insertar = " + e);
        }finally {
            cerrarEstados(stmt, null);
        }
    }

    // Méttodo para borrar

    public void borrar (int id_cuenta) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id_cuenta);
            stmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("error al eliminar = " + e);
        }finally {
            cerrarEstados(stmt, null);
        }
    }

    //Méttodo para actualizar

    public void actualizar (Acceso a) throws SQLException {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

            stmt.setInt(2, a.getId_filmografia());
            stmt.setInt(3, a.getId_cuenta());
            stmt.setDate(4, a.getFecha_acceso());
            stmt.setInt(5, a.getTipo_suscripcion_id());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al actualizar = " + e);
        }finally {
            cerrarEstados(stmt, null);
        }
    }

    //Méttodo para listar todos

    public void listarTodo () throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = stmt.executeQuery(LISTALL);
        try {
            while (rs.next()) {
                Acceso a = new Acceso(
                        rs.getInt("id_acceso"),
                        rs.getInt("id_filmografia"),
                        rs.getInt("id_cuenta"),
                        rs.getDate("fecha_acceso"),
                        rs.getInt("tipo_suscripcion_id")
                );
                System.out.println("Acceso ID: " + a.getId_acceso());
            }
        } catch (SQLException e) {
            System.out.println("Error al listar todo: " + e.getMessage());
        } finally {
            cerrarEstados(stmt, rs);
        }
    }
    // Mettodo para listar uno

    public void listarUno(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        rs = stmt.executeQuery(LISTONE);

        try {
            Acceso a = new Acceso(
                    rs.getInt("id_acceso"),
                    rs.getInt("id_filmografia"),
                    rs.getInt("id_cuenta"),
                    rs.getDate("fecha_acceso"),
                    rs.getInt("tipo_suscripcion_id")
            );
            System.out.println("Acceso encontrado: " + a.getId_acceso());


        }catch (SQLException e){
            System.out.println("error al listar uno = " + e.getMessage());
        }finally {
            cerrarEstados(stmt, rs);
        }


    }
}