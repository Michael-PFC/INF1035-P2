/**
* @author  Thomas Jennings
* @since   2020-03-25
*/
package sample.hyperledger.blockchain.model;
public class Instituicao {

  private String id;
  private String nome;
  private String cnpj;
  private String enderecoCompleto;
  
  public Instituicao() {}

  public Instituicao(String id, String nome, String cn, String enderecoCompleto) {
    this.id = id;
    this.nome = nome;
    this.cnpj = cn;
    this.enderecoCompleto = enderecoCompleto;
  }

  public String getId() {
    return id;
  }

  public void setId(String Id) {
    this.id = Id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String Nome) {
    this.nome = Nome;
  }

  public String getEnderecoCompleto() {
    return enderecoCompleto;
  }

  public void setEnderecoCompleto(String EnderecoCompleto) {
    this.enderecoCompleto = EnderecoCompleto;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }
}
