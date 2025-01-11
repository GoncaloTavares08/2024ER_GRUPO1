package modelos;

public abstract class Documento {
    protected String titulo;
    protected String editora;
    protected String categoria;

    public Documento(String titulo, String editora, String categoria) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

