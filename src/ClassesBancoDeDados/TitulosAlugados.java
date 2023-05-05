package ClassesBancoDeDados;

public class TitulosAlugados {
    int id;
    String descricao;
    String tipo;
    int numeroExemplares;
    int periodoMaximoEmprestimo;
    int idFuncionario;
    int idCliente;
    String dataEmprestimo;
    String dataDevolucao;
    public TitulosAlugados(int id, String descricao, String tipo, int numeroExemplares, int perioMaximoEmprestimo, int idFuncionario,int idCliente, String dataEmprestimo, String dataDevolucao){
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.numeroExemplares = numeroExemplares;
        this.periodoMaximoEmprestimo = perioMaximoEmprestimo;
        this.idFuncionario = idFuncionario;
        this.idCliente = idCliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
    public int getId(){
        return id;
    }
    public String getDescricao(){
        return descricao;
    }
    public String getTipo(){
        return tipo;
    }
    public int getNumeroExemplares(){
        return numeroExemplares;
    }
    public int getPeriodoMaximoEmprestimo(){
        return periodoMaximoEmprestimo;
    }
    public int getIdFuncionario(){
        return idFuncionario;
    }
    public int getIdCliente(){
        return idCliente;
    }
    public String getDataEmprestimo(){
        return dataEmprestimo;
    }
    public String getDataDevolucao(){
        return dataDevolucao;
    }
    public void imprimirTituloAlugado(){
        System.out.println("id: "+id+" descricao: "+descricao+" tipo: "+tipo+" nº exmp: "+numeroExemplares+" periodo Max emprest: "+periodoMaximoEmprestimo+" id Funcionario: "+idFuncionario+"  id Cliente: "+idCliente+" data emprest: "+dataEmprestimo+" data devolucao: "+dataDevolucao);
    }
}
