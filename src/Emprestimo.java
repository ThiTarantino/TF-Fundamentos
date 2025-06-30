
public class Emprestimo
{
    private String dataEmp;
    private Cliente cliente;
    private Carro carro;
    
    //Construtor
    public Emprestimo(){
        
    }
    //Construtor sobrecarregado
    public Emprestimo(String dataEmp, Cliente cliente, Carro carro){
        this.dataEmp = dataEmp;
        this.cliente = cliente;
        this.carro = carro;
    }
    
    //Setters
    public void setDataemp(String dataEmp){
        this.dataEmp = dataEmp;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setCarro(Carro carro){
        this.carro = carro;
    }
    
    //Getters
    public String getDataemp(){
        return dataEmp;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public Carro getCarro(){
        return carro;
    }
}
