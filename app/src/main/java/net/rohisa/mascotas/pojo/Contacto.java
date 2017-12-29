package net.rohisa.mascotas.pojo;

/**
 * Created by frojash on 12/22/17.
 */

public class Contacto {

    private String nombre;
    private String email;
    private String mensaje;

    public Contacto(String nombre, String mensaje, String email) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.email = email;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

}