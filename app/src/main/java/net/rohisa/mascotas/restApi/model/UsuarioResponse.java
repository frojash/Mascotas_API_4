package net.rohisa.mascotas.restApi.model;

/**
 * Created by frojash on 2/1/18.
 */

public class UsuarioResponse {

    private String id;
    private String token;
    private String usuario;

    public UsuarioResponse(String id, String token, String usuario){
        this.id = id;
        this.token  = token;
        this.usuario = usuario;
    }

    public UsuarioResponse(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
