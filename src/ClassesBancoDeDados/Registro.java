package ClassesBancoDeDados;

public class Registro {
    int id;
    String tipoRegistro;
    String descricaoTitulo;
    String tipoTitulo;
    int numExemplares;
    int idFuncionario;
    int idCliente;
    String data;

    public Registro(int id,String tipoRegistro, String descricaoTitulo,String tipoTitulo, int numExemplares, int idFuncionario,int idCliente, String data){
        this.id = id;
        this.tipoRegistro = tipoRegistro;
        this.descricaoTitulo = descricaoTitulo;
        this.tipoTitulo = tipoTitulo;
        this.numExemplares = numExemplares;
        this.idFuncionario = idFuncionario;
        this.idCliente = idCliente;
        this.data = data;
    }
    public int getId(){
        return id;
    }
    public String getTipoRegistro(){
        return  tipoRegistro;
    }
    public  String getDescricaoTitulo(){
        return descricaoTitulo;
    }
    public String getTipoTitulo(){
        return tipoTitulo;
    }
    public int getNumExemplares(){
        return numExemplares;
    }
    public int getIdFuncionario(){
        return idFuncionario;
    }
    public int getIdCliente(){
        return idCliente;
    }
    public String getData(){
        return data;
    }
}
