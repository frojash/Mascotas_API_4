package net.rohisa.mascotas.pojo;


public class Mascota implements  java.io.Serializable{

    private int id;
    private String nombre;
    private int likes;
    private int foto;
    private boolean gusta;

    public Mascota( int pfoto, int plikes, String pnombre, boolean pGusta){
        this.nombre = pnombre;
        this.foto = pfoto;
        this.likes = plikes;
        this.gusta = pGusta;
    }

    public Mascota (int pfoto, int plikes){
        this.foto = pfoto;
        this.likes = plikes;
    }

    public Mascota() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public boolean isGusta() {
        return gusta;
    }

    public void setGusta(boolean gusta) {
        this.gusta = gusta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
