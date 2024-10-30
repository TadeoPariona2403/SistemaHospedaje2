
package Logica;

import Entidades.vProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProductoDAO {
    private conexion mysql = new conexion();
    private Connection cn = mysql.getConnection();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Producto", "Descripcion", "Unidad Medida", "Precio Venta"};
        String[] registro = new String[5];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "SELECT * FROM producto WHERE nombre LIKE ? ORDER BY id_producto DESC";

        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
        pst.setString(1, "%" + buscar + "%");
        ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                registro[0] = rs.getString("id_producto");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("descripcion");
                registro[3] = rs.getString("unidad_medida");
                registro[4] = rs.getString("precio_venta");
                totalregistros++;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar(vProducto dts) {
        sSQL = "INSERT INTO producto (nombre, descripcion, unidad_medida, precio_venta)" + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
            
            return pst.executeUpdate() != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean modificar(vProducto dts) {
        sSQL = "UPDATE producto SET nombre=?, descripcion=?, unidad_medida=?, precio_venta=?" + "WHERE id_producto=?";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
            
            pst.setInt(5, dts.getIdproducto());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean eliminar(vProducto dts) {
        sSQL = "DELETE FROM producto WHERE id_producto=?";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setInt(1, dts.getIdproducto());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
