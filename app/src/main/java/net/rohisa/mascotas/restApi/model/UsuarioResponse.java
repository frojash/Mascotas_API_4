package net.rohisa.mascotas.restApi.model;

import net.rohisa.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by frojash on 2/1/18.
 * ArrayList<Mascota> mascotas;

 public ArrayList<Mascota> getMascotas(){
 return  mascotas;
 }

 public  void setMascotas (ArrayList<Mascota> mascotas){
 this.mascotas = mascotas;

 }
 */

public class UsuarioResponse {

    private String id;
    private String token;
    private String usuario;
    private  String foto;

    public UsuarioResponse(String id, String token, String usuario, String foto){
        this.id = id;
        this.token  = token;
        this.usuario = usuario;
        this.foto = foto;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
