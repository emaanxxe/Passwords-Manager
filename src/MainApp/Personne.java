package MainApp;

public class Personne {
	

	    private String nombres;
	    private String apellidos;
	    private String correo;

	    public Personne(String nombres, String apellidos, String correo) {
	        this.nombres = nombres;
	        this.apellidos = apellidos;
	        this.correo = correo;
	    }

	    public String getNombres() {
	        return nombres;
	    }

	    public void setNombres(String nombres) {
	        this.nombres = nombres;
	    }

	    public String getApellidos() {
	        return apellidos;
	    }

	    public void setApellidos(String apellidos) {
	        this.apellidos = apellidos;
	    }

	    public String getCorreo() {
	        return correo;
	    }

	    public void setCorreo(String correo) {
	        this.correo = correo;
	    }
	}


