/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class Diploma {

    private final static Genson genson = new Genson();

    @Property()
    private String id;
    @Property()
    private String idPessoa;
    @Property()
    private String idInstituicao;
    @Property()
    private String curso;
    @Property()
    private String enfase;
    @Property()
    private String dtEmissao;
    @Property()
    private String dtConclusaoCurso;
    @Property()
    private String dtAutenticacao;
    @Property()
    private String codigoMEC;
    @Property()
    private String template;
    @Property()
    private String responsavel;
    @Property()
    private String fotoBlob;
    @Property()
    private String invalido;

    public Diploma(){
    }

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

  public void setIdPessoa(String idPessoa) {
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

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Diploma fromJSONString(String json) {
        Diploma asset = genson.deserialize(json, Diploma.class);
        return asset;
    }
}
