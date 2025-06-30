
public class Cliente
{
    private String nome;
    private String cpf;
    private int idade;
    
    //Construtor
    public Cliente(){
        this.nome = "Indefinido";
        this.cpf = "Indefinido";
    }
    //Construtor sobrecarregado
    public Cliente(String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }
    
    //Setters
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }
    
    //Getters
    public String getNome(){
        return this.nome;
    }
    public String getCpf(){
        return this.cpf;
    }
    public int getIdade(){
        return this.idade;
    }
}
