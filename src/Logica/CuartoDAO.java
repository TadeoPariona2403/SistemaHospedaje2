package Logica;

import Entidades.vCuarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CuartoDAO {
    private conexion mysql = new conexion();
    private Connection cn = mysql.getConnection();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Numero", "Piso", "Definicion", "Caracteristicas", "Precio", "Estado", "Tipo Cuarto"};
        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "SELECT * FROM cuarto WHERE piso LIKE ? OR numero LIKE ? OR tipo_cuarto LIKE ? ORDER BY id_cuarto";

        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setString(1, "%" + buscar + "%");
            pst.setString(2, "%" + buscar + "%");
            pst.setString(3, "%" + buscar + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                registro[0] = rs.getString("id_cuarto");
                registro[1] = rs.getString("numero");
                registro[2] = rs.getString("piso");
                registro[3] = rs.getString("definicion");
                registro[4] = rs.getString("caracteristicas");
                registro[5] = String.valueOf(rs.getDouble("precio_diario"));
                registro[6] = rs.getString("estado");
                registro[7] = rs.getString("tipo_cuarto");
                totalregistros++;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar(vCuarto dts) {
        sSQL = "INSERT INTO cuarto(numero, piso, definicion, caracteristicas, precio_diario, estado, tipo_cuarto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDefinicion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_cuarto());
            
            return pst.executeUpdate() != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean modificar(vCuarto dts) {
        sSQL = "UPDATE cuarto SET numero=?, piso=?, definicion=?, caracteristicas=?, precio_diario=?, estado=?, tipo_cuarto=? WHERE id_cuarto=?";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDefinicion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_cuarto());
            pst.setInt(8, dts.getIdCuarto());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean eliminar(vCuarto dts) {
        sSQL = "DELETE FROM cuarto WHERE id_cuarto=?";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setInt(1, dts.getIdCuarto());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}

