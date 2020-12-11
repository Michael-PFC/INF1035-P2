/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class Pessoa {

    private final static Genson genson = new Genson();

    @Property()
    private String cpf;

    @Property()
    private String rg;

    @Property()
    private String nrProfissional ;

    @Property()
    private String nome;

    @Property()
    private String dtNascimento;

    @Property()
    private String enderecoCompleto;

    @Property()
    private String fotoBlob;


    public Pessoa(){
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

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Pessoa fromJSONString(String json) {
        Pessoa asset = genson.deserialize(json, Pessoa.class);
        return asset;
    }
}
