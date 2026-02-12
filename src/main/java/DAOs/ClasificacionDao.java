package DAOs;

import Models.Clasificacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ClasificacionDao extends DAO <Clasificacion> {

    private static final String INSERT = "INSERT INTO Clasificacion (id, nombre) VALUES (?,?)";
    private static final String DELETE = "DELETE FROM Clasificacion WHERE id=?";
    private static final String UPDATE = "UPDATE Clasificacion SET nombre=? WHERE id=?";
    private static final String LISTALL = "SELECT * FROM Clasificacion";
    private static final String LISTONE = "SELECT * FROM Clasificacion WHERE id=?";


    //Mettodo para cargar datos:

    public void cargarDatos (PreparedStatement stmt, Clasificacion c) {
        try{
            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getNombre());
        }catch (SQLException e) {
            System.out.println("error al cargar datos = " + e.getMessage());
        }
    }

    //Mettodo para Insertar

    public void insertar(Clasificacion c) throws SQLException {
        PreparedStatement stmt = null;


        try{
            stmt = conn.prepareStatement(INSERT);
            cargarDatos(stmt, c);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("error al insertar = " + e.getMessage());
        }finally{
            cerrarEstados(stmt, null);
        }


    }

    //Mettodo para borrar

    public void borrar (int id) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error al borrar = " + e.getMessage());
        }finally {
            cerrarEstados(stmt, null);
        }

    }
    //Méttodo para actualizar

    public void actualizar (Clasificacion c) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, c.getNombre());
            stmt.setInt(2, c.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error al actualizar = " + e.getMessage());
        }finally {
            cerrarEstados(stmt, null);
        }
    }

    //Mettodo para listar ttodo

    public void listarTodo () throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = stmt.executeQuery(LISTALL);

        try {
            while (rs.next()){
                Clasificacion c = new Clasificacion(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
                System.out.println("clasificacion = " + c.getId());
            }
        } catch (SQLException e) {
            System.out.println("error al listar todos = " + e.getMessage());
        }finally {
            cerrarEstados(stmt, rs);
        }
    }
    //METTODO para listar uno

    public void listarUno (int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = stmt.executeQuery(LISTONE);

        try{
            while (rs.next()) {
                Clasificacion c = new Clasificacion(
                        stmt.setInt(1, "id");
                        stmt.setString(2, "nombre");
                );
                System.out.println("Clasificacion: " + c.getNombre());
            }
        } catch (SQLException e) {
            System.out.println("error al listar 1 = " + e.getMessage());
        }finally {
            cerrarEstados(stmt, rs);
        }

    }

}
