
package Entidades;

public class vCliente extends vPersona {
    private String codigo_cliente;

    public vCliente() {
    }

    public vCliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }
    
    
    
}
