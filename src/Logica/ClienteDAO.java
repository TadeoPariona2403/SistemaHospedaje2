package Logica;

import Entidades.vCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteDAO {
    private conexion mysql = new conexion();
    private Connection cn = mysql.getConnection();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Doc", "Numero Documento", "Telefono", "Email", "Código"};
        String[] registro = new String[9];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "SELECT p.id_persona, p.nombre, p.apaterno, p.amaterno, p.tipo_documento, p.numero_documento, "
             + "p.telefono, p.email, c.codigo_cliente FROM persona p "
             + "INNER JOIN cliente c ON p.id_persona=c.id_persona "
             + "WHERE p.numero_documento LIKE '%" + buscar + "%' ORDER BY p.id_persona DESC";

        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                registro[0] = rs.getString("id_persona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apaterno");
                registro[3] = rs.getString("amaterno");
                registro[4] = rs.getString("tipo_documento");
                registro[5] = rs.getString("numero_documento");
                registro[6] = rs.getString("telefono");
                registro[7] = rs.getString("email");
                registro[8] = rs.getString("codigo_cliente");

                totalregistros++;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(vCliente dts) {
        String sSQL = "INSERT INTO persona (nombre, apaterno, amaterno, tipo_documento, numero_documento, telefono, email) " + 
                      "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sSQL2 = "INSERT INTO cliente (id_persona, codigo_cliente) " + 
                       "VALUES ((SELECT id_persona FROM persona ORDER BY id_persona DESC LIMIT 1), ?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getAmaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getTelefono());
            pst.setString(7, dts.getEmail());
            int n = pst.executeUpdate();

            if (n != 0) {
                PreparedStatement pst2 = cn.prepareStatement(sSQL2);
                pst2.setString(1, dts.getCodigo_cliente());
                int n2 = pst2.executeUpdate();
                return n2 != 0;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean modificar(vCliente dts) {
        String sSQL = "UPDATE persona SET nombre=?, apaterno=?, amaterno=?, tipo_documento=?, numero_documento=?, "
                    + "telefono=?, email=? WHERE id_persona=?";
        String sSQL2 = "UPDATE cliente SET codigo_cliente=? WHERE id_persona=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getAmaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getTelefono());
            pst.setString(7, dts.getEmail());
            pst.setInt(8, dts.getIdpersona());
            int n = pst.executeUpdate();

            if (n != 0) {
                PreparedStatement pst2 = cn.prepareStatement(sSQL2);
                pst2.setString(1, dts.getCodigo_cliente());
                pst2.setInt(2, dts.getIdpersona());
                int n2 = pst2.executeUpdate();
                return n2 != 0;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(vCliente dts) {
        sSQL = "DELETE FROM cliente WHERE id_persona=?";
        sSQL2 = "DELETE FROM persona WHERE id_persona=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdpersona());
            int n = pst.executeUpdate();

            if (n != 0) {
                PreparedStatement pst2 = cn.prepareStatement(sSQL2);
                pst2.setInt(1, dts.getIdpersona());
                int n2 = pst2.executeUpdate();
                return n2 != 0;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

}
