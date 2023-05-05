package ClassesBancoDeDados;

public class Funcionarios {
    private int id;
    private String primeiroNome;
    private String segundoNome;
    private String CPF;
    private int numeroLocacoesTratadas;
    private int numeroDevolucoesTratadas;

    public Funcionarios(String primeiroNome,String segundoNome,String CPF){
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.CPF = CPF;
        this.numeroDevolucoesTratadas = 0;
        this.numeroLocacoesTratadas = 0;
    }
    public  Funcionarios(int id, String primeiroNome, String segundoNome, String CPF, int numeroLocacoesTratadas, int numeroDevolucoesTratadas){
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.CPF = CPF;
        this.numeroLocacoesTratadas = numeroLocacoesTratadas;
        this.numeroDevolucoesTratadas = numeroDevolucoesTratadas;
    }
    public int getID(){
        return id;
    }
    public String getPrimeiroNome(){
        return primeiroNome;
    }
    public String getSegundoNome(){
        return segundoNome;
    }
    public String getCPF(){
        return CPF;
    }
    public int getNumeroLocacoesTratadas(){
        return numeroLocacoesTratadas;
    }
    public int getNumeroDevolucoesTratadas(){
        return numeroDevolucoesTratadas;
    }

}
