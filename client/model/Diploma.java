package sample.hyperledger.blockchain.model;
public class Diploma {

  private String id;
  private String idPessoa;
  private String idInstituicao;
  private String curso;
  private String enfase;
  private String dtEmissao;
  private String dtConclusaoCurso;
  private String dtAutenticacao;
  private String codigoMEC;
  private String template;
  private String responsavel;
  private String fotoBlob;
  private String invalido;
  
  public Diploma() {}

  public Diploma(String id, String idPessoa, String idInstituicao, String curso, String enfase, String dtEmissao, String dtConclusaoCurso,
      String dtAutenticacao, String codigoMEC, String template, String responsavel, String fotoBlob, String invalido) {
    this.id = id;
    this.idPessoa = idPessoa;
    this.idInstituicao = idInstituicao;
    this.curso = curso;
    this.enfase = enfase;
    this.dtEmissao = dtEmissao;
    this.dtConclusaoCurso = dtConclusaoCurso; 
    this.dtAutenticacao = dtAutenticacao;
    this.codigoMEC = codigoMEC;
    this.template = template;
    this.responsavel = responsavel;
    this.fotoBlob = fotoBlob;
    this.invalido = invalido;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdPessoa() {
    return idPessoa;
  }

  public void setidPessoa(String idPessoa) {
    this.idPessoa = idPessoa;
  }

  public String getIdInstituicao() {
    return idInstituicao;
  }

  public void setIdInstituicao(String idInstituicao) {
    this.idInstituicao = idInstituicao;
  }

  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso =  curso;
  }

  public String getEnfase() {
    return enfase;
  }

  public void setEnfase(String enfase) {
    this.enfase = enfase;
  }

  public String getDtEmissao() {
    return dtEmissao;
  }

  public void setDtEmissao(String dtEmissao) {
    this.dtEmissao = dtEmissao;
  }

  public String getDtAutenticacao() {
    return dtAutenticacao;
  }

  public void setDtAutenticacao(String dtAutenticacao) {
    this.dtAutenticacao = dtAutenticacao;
  }

  public String getCodigoMEC() {
    return codigoMEC;
  }

  public void setCodigoMEC(String codigoMEC) {
    this.codigoMEC = codigoMEC;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public String getResponsavel() {
    return responsavel;
  }

  public void setResponsavel(String responsavel) {
    this.responsavel = responsavel;
  }

  public String getFotoBlob() {
    return fotoBlob;
  }

  public void setFotoBlob(String fotoBlob) {
    this.fotoBlob = fotoBlob;
  }

  public String getInvalido() {
    return invalido;
  }

  public void setInvalido(String invalido) {
    this.invalido = invalido;
  }

  public String getDtConclusaoCurso() {
    return dtConclusaoCurso;
  }

  public void setDtConclusaoCurso(String dtConclusaoCurso) {
    this.dtConclusaoCurso = dtConclusaoCurso;
  }
  
}
