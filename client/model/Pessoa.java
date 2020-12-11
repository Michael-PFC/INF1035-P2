/**
* @author  Thomas Jennings
* @since   2020-03-25
*/
package sample.hyperledger.blockchain.model;
public class Pessoa {

  private String cpf;
  private String rg;
  private String nrProfissional ;
  private String nome;
  private String dtNascimento;
  private String enderecoCompleto;
  private String fotoBlob;
  
  public Pessoa() {}

  public Pessoa(String cpf, String rg, String nrProfissional, String nome, String dtNascimento, String enderecoCompleto,
      String fotoBlob) {
    this.cpf = cpf;
    this.rg = rg;
    this.nrProfissional = nrProfissional;
    this.nome = nome;
    this.dtNascimento = dtNascimento;
    this.enderecoCompleto = enderecoCompleto;
    this.fotoBlob = fotoBlob;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getRg() {
    return rg;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }

  public String getNrProfissional() {
    return nrProfissional;
  }

  public void setNrProfissional(String nrProfissional) {
    this.nrProfissional = nrProfissional;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDtNascimento() {
    return dtNascimento;
  }

  public void setDtNascimento(String dtNascimento) {
    this.dtNascimento = dtNascimento;
  }

  public String getEnderecoCompleto() {
    return enderecoCompleto;
  }

  public void setEnderecoCompleto(String enderecoCompleto) {
    this.enderecoCompleto = enderecoCompleto;
  }

  public String getFotoBlob() {
    return fotoBlob;
  }

  public void setFotoBlob(String fotoBlob) {
    this.fotoBlob = fotoBlob;
  }
  
}
