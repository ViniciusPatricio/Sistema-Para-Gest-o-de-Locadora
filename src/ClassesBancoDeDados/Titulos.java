package ClassesBancoDeDados;

public class Titulos {

    private int id;
    private String tipo;
    private int numeroExemplares;
    private int periodoMaximoEmprestimo;
    private String descricao;
    private int numeroExemplaresAlugados;
    public Titulos(String tipo,int numeroExemplares,int periodoMaximoEmprestimo,String descricao){
        this.tipo = tipo;
        this.numeroExemplares = numeroExemplares;
        this.periodoMaximoEmprestimo = periodoMaximoEmprestimo;
        this.descricao = descricao;
        this.numeroExemplaresAlugados = 0;
    }
    public Titulos(int id, String tipo,int numeroExemplares,int periodoMaximoEmprestimo,String descricao,int numeroExemplaresAlugados){
        this.id = id;
        this.tipo = tipo;
        this.numeroExemplares = numeroExemplares;
        this.periodoMaximoEmprestimo = periodoMaximoEmprestimo;
        this.descricao = descricao;
        this.numeroExemplaresAlugados = numeroExemplaresAlugados;
    }
    public int getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public int getNumeroExemplares(){
        return  numeroExemplares;
    }
    public void setNumeroExemplares(int numeroExemplares){
        this.numeroExemplares = numeroExemplares;
    }

    public int getPeriodoMaximoEmprestimo(){
        return periodoMaximoEmprestimo;
    }
    public void setPeriodoMaximoEmprestimo(int periodoMaximoEmprestimo){
        this.periodoMaximoEmprestimo = periodoMaximoEmprestimo;
    }

    public String getDescricao(){
        return  descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public int getNumeroExemplaresAlugados(){return  numeroExemplaresAlugados;}

    public void setNumeroExemplaresAlugados(int numeroExemplaresAlugados){
        this.numeroExemplaresAlugados = numeroExemplaresAlugados;
    }

}
