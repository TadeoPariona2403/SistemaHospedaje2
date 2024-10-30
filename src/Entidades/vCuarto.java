
package Entidades;
public class vCuarto {
    private int idCuarto;
    private String numero;
    private String piso;
    private String definicion;
    private String caracteristicas;
    private double precio_diario;
    private String estado;
    private String tipo_cuarto;

    public vCuarto(int idCuarto, String numero, String piso, String definicion, String caracteristicas, double precio_diario, String estado, String tipo_cuarto) {
        this.idCuarto = idCuarto;
        this.numero = numero;
        this.piso = piso;
        this.definicion = definicion;
        this.caracteristicas = caracteristicas;
        this.precio_diario = precio_diario;
        this.estado = estado;
        this.tipo_cuarto = tipo_cuarto;
    }

    public vCuarto() {
        
    }

    public int getIdCuarto() {
        return idCuarto;
    }

    public void setIdCuarto(int idCuarto) {
        this.idCuarto = idCuarto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public double getPrecio_diario() {
        return precio_diario;
    }

    public void setPrecio_diario(double precio_diario) {
        this.precio_diario = precio_diario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_cuarto() {
        return tipo_cuarto;
    }

    public void setTipo_cuarto(String tipo_cuarto) {
        this.tipo_cuarto = tipo_cuarto;
    }
    
    
}
