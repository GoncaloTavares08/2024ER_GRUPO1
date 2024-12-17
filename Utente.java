public class Utente {
    private String nif;
    private String nome;
    private char genero;
    private String contacto;

    public Utente(String nif, String nome, char genero, String contacto) {
        this.nif = nif;
        this.nome = nome;
        this.genero = genero;
        this.contacto = contacto;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "[" +
                "NIF: " + getNif() +
                ", Nome: " + getNome() +
                ", Género: " + getGenero() +
                ", Contacto: " + getContacto() +
                ']';
    }

    public static Utente fromString(String dados) {
        dados = dados.replace("[", "").replace("]", "").trim();
        String[] partes = dados.split(", ");
        String nif = partes[0].split(": ")[1];
        String nome = partes[1].split(": ")[1];
        char genero = partes[2].split(": ")[1].charAt(0);
        String contacto = partes[3].split(": ")[1];
        return new Utente(nif, nome, genero, contacto);
    }
}
