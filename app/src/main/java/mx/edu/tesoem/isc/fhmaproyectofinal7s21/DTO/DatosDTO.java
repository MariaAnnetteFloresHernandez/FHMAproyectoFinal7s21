package mx.edu.tesoem.isc.fhmaproyectofinal7s21.DTO;

public class DatosDTO {
    int id, edad;
    String nombre, correo;

    public DatosDTO() {
    }

    public DatosDTO(int id, int edad, String nombre, String correo) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
