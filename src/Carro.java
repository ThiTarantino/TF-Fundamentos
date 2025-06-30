
public class Carro
{
    private String modelo;
    private String placa;
    private int ano;
    
    //Construtor
    public Carro(){
        this.modelo = "Indefinido";
        this.placa = "Indefinido";
    }
    //Construtor sobrecarregado
    public Carro(String modelo, String placa, int ano){
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
    }
    
    //Setters
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setPlaca(String placa){
        this.placa = placa;
    }
    public void setAno(int ano){
        this.ano = ano;
    }
    
    //Getters
    public String getModelo(){
        return modelo;
    }
    public String getPlaca(){
        return placa;
    }
    public int getAno(){
        return ano;
    }
}
