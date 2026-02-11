/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

/**
 *
 * @author bigpa
 */
import Models.Factura;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaDAO extends DAO<Factura> {
    
    public FacturaDAO (Connection conn){
        super.conn = conn;
    }

    private static final String INSERT = "INSERT INTO factura(num_factura,"
            + " suscripcion_id, cuenta_id, importe_pvp, metodo_pago,"
            + " fecha_factura) VALUES (?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM factura"
            + " WHERE num_factura=?";
    private static final String UPDATE = "UPDATE factura SET suscripcion_id=?,"
            + " cuenta_id=?, importe_pvp=?, metodo_pago=?, fecha_factura=?"
            + " WHERE num_factura=?";
    private static final String LISTALL = "SELECT * FROM factura";
    private static final String LISTONE = "SELECT * FROM factura"
            + " WHERE num_factura=?";

    @Override
    public void listUno(int id) {
        
    }

    @Override
    public void listarTodo() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(LISTALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Factura fact= new Factura(rs.getInt("num_factura"),
                        rs.getInt("suscripcion_id"), rs.getInt("cuenta_id"),
                        rs.getDouble("importe_pvp"), rs.getString("metodo_pago"),
                        rs.getDate("fecha_factura"));
                System.out.println(fact.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e);
        } finally {
            cerrarEstados(stmt, rs);
        }
    }

    @Override
    public void insertar(Factura fact) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement(INSERT);
            cargarDatosInsert(stmt, fact);
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

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actualizar(Factura dato_actualizar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cargarDatos(PreparedStatement stmt, Factura dato) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void cargarDatosInsert(PreparedStatement stmt, Factura fact) {

        try {
            stmt.setInt(1, fact.getNum_factura());
            stmt.setInt(2, fact.getSuscripcion_id());
            stmt.setInt(3, fact.getCuenta_id());
            stmt.setDouble(4, fact.getImporte_pvp());
            stmt.setString(5, fact.getMetodo_pago());
            stmt.setDate(6, fact.getFecha_factura());
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

}
