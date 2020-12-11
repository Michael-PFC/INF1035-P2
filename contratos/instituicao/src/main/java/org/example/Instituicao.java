/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class Instituicao {

    private final static Genson genson = new Genson();

    @Property()
    private String sId;

    @Property()
    private String sNome;

    @Property()
    private String sCNPJ;
    
    @Property()
    private String sEnderecoCompleto;

    public Instituicao(){}

    public Instituicao(String sId, String sNome, String sCNPJ, String sEnderecoCompleto) {
        this.sId = sId;
        this.sNome = sNome;
        this.sCNPJ = sCNPJ;
        this.sEnderecoCompleto = sEnderecoCompleto;
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Instituicao fromJSONString(String json) {
        Instituicao asset = genson.deserialize(json, Instituicao.class);
        return asset;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsNome() {
        return sNome;
    }

    public void setsNome(String sNome) {
        this.sNome = sNome;
    }

    public String getsCNPJ() {
        return sCNPJ;
    }

    public void setsCNPJ(String sCNPJ) {
        this.sCNPJ = sCNPJ;
    }

    public String getsEnderecoCompleto() {
        return sEnderecoCompleto;
    }

    public void setsEnderecoCompleto(String sEnderecoCompleto) {
        this.sEnderecoCompleto = sEnderecoCompleto;
    }
}
