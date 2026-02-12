package DAOs;

import Models.Cuenta;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class CuentaDao extends DAO{

    private static final String INSERT = "INSERT INTO Cuenta (id_cuenta, tipo_cuenta, nombre, password_hash) VALUES (?,?,?,?)";
    private static final String DELETE = "DELETE FROM Cuenta WHERE id_cuenta=?";
    private static final String UPDATE = "UPDATE Cuenta SET  tipo_cuenta=?, nombre=?, password_hash=? WHERE id_cuenta=?";
    private static final String LISTALL = "SELECT * FROM Cuenta";
    private static final String LISTONE = "SELECT * FROM Cuenta WHERE id_cuenta=?";

    public void cargarDatos (PreparedStatement stmt, Cuenta c) {
        try{
            stmt.setInt(1, c.getId_cuenta());
            stmt.setString(2, c.getTipo_cuenta());
            stmt.setString(3, c.getNombre());
            stmt.setString(4, c.getPassword_hash());
        } catch (Exception e) {
            System.out.println("error al cargar datos = " + e.getMessage());
        }


    }

    //Mettodo para insertar
    public void insertar (Cuenta c) throws SQLException {
        PreparedStatement stmt = null;
        try{
            stmt = conn.prepareStatement(INSERT);
            cargarDatos(stmt, c);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error al insertar = " + e.getMessage());
        }finally {
            cerrarEstados(stmt, null);
        }
    }

}
