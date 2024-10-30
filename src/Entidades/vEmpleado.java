
package Entidades;

public class vEmpleado extends vPersona {
    Double sueldo;
    String acceso;
    String usuario;
    String contrasena;
    String estado;

    public vEmpleado() {
    }

    public vEmpleado(Double sueldo, String acceso, String usuario, String contrasena, String estado) {
        this.sueldo = sueldo;
        this.acceso = acceso;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
