package pim.alves.murilo.projetointegradomultidisciplinar.model;

public class Estoque {

    private int id;
    private String nomeProduto;
    private String quantidadeProdutos;


    public Estoque(){}

    public void setId(int id){ this.id = id; }

    public int getId(){
        return id;
    }

    public void setNomeProduto(String nomeProduto){

        this.nomeProduto = nomeProduto;
    }

    public String getNomeProduto(){
        return nomeProduto;
    }

    public void setQuantidadeProdutos(String quantidadeProdutos){
        this.quantidadeProdutos = quantidadeProdutos;
    }

    public String getQuantidadeProdutos(){
        return quantidadeProdutos;
    }

}
