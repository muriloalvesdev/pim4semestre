package pim.alves.murilo.projetointegradomultidisciplinar.model;

public class Venda {
    private int id;
    private String nomeProduto;
    private String valorVenda;
    private String quantidade;

    public Venda (){}

    public void setQuantidade(String quantidade){
        this.quantidade = quantidade;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNomeProduto(String nomeProduto){
        this.nomeProduto = nomeProduto;
    }

    public String getQuantidade(){
        return quantidade;
    }

    public void setValorVenda(String valorVenda){
        this.valorVenda = valorVenda;
    }

    public int getId(){
        return id;
    }

    public String getNomeProduto(){
        return nomeProduto;
    }

    public String getValorVenda(){
        return valorVenda;
    }
}
