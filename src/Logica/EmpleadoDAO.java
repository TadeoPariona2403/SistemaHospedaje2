package Logica;

import Entidades.vCliente;
import Entidades.vEmpleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmpleadoDAO {
    private conexion mysql = new conexion();
    private Connection cn = mysql.getConnection();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Doc", "Numero Documento"
                , "Telefono", "Email", "Sueldo", "Acceso", "Usuario", "Contrasena", "Estado"};
        String[] registro = new String[13];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "SELECT p.id_persona, p.nombre, p.apaterno, p.amaterno, p.tipo_documento, p.numero_documento, "
             + "p.telefono, p.email, e.sueldo,e.acceso,e.usuario,e.contrasena,e.estado FROM persona p "
             + "INNER JOIN empleado e ON p.id_persona=e.id_persona "
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
                registro[8] = rs.getString("sueldo");
                registro[9] = rs.getString("acceso");
                registro[10] = rs.getString("usuario");
                registro[11] = rs.getString("contrasena");
                registro[12] = rs.getString("estado");

                totalregistros++;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(vEmpleado dts) {
        String sSQL = "INSERT INTO persona (nombre, apaterno, amaterno, tipo_documento, numero_documento, telefono, email) " + 
                      "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sSQL2 = "INSERT INTO empleado (id_persona, sueldo, acceso, usuario, contrasena, estado) " + 
                       "VALUES ((SELECT id_persona FROM persona ORDER BY id_persona DESC LIMIT 1), ?,?,?,?,?)";

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
                pst2.setDouble(1, dts.getSueldo());
                pst2.setString(2, dts.getAcceso());
                pst2.setString(3, dts.getUsuario());
                pst2.setString(4, dts.getContrasena());
                pst2.setString(5, dts.getEstado());
                
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

    public boolean modificar(vEmpleado dts) {
        String sSQL = "UPDATE persona SET nombre=?, apaterno=?, amaterno=?, tipo_documento=?, numero_documento=?, "
                    + "telefono=?, email=? WHERE id_persona=?";
        String sSQL2 = "UPDATE empleado SET sueldo=?, acceso=?, usuario=?, contrasena=?, estado=? WHERE id_persona=?";

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
                pst2.setDouble(1, dts.getSueldo());
                pst2.setString(2, dts.getAcceso());
                pst2.setString(3, dts.getUsuario());
                pst2.setString(4, dts.getContrasena());
                pst2.setString(5, dts.getEstado());
                pst2.setInt(6, dts.getIdpersona());
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

    public boolean eliminar(vEmpleado dts) {
        sSQL = "DELETE FROM empleado WHERE id_persona=?";
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
    public DefaultTableModel login(String usuario, String contrasena) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Acceso", "Usuario", "Contrasena", "Estado"};
        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "SELECT p.id_persona, p.nombre, p.apaterno, p.amaterno, "
             + "e.acceso, e.usuario, e.contrasena, e.estado "
             + "FROM persona p INNER JOIN empleado e ON p.id_persona = e.id_persona "
             + "WHERE e.usuario = ? AND e.contrasena = ? AND e.estado = 'A'";

        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setString(1, usuario);
            pst.setString(2, contrasena);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                registro[0] = rs.getString("id_persona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apaterno");
                registro[3] = rs.getString("amaterno");
                registro[4] = rs.getString("acceso");
                registro[5] = rs.getString("usuario");
                registro[6] = rs.getString("contrasena");
                registro[7] = rs.getString("estado");

                totalregistros++;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
