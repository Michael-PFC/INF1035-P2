/*
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.ArrayList;
import java.util.List;

@Contract(name = "DiplomaContract",
    info = @Info(title = "Diploma contract",
                description = "My Smart Contract",
                version = "0.0.1",
                license =
                        @License(name = "Apache-2.0",
                                url = ""),
                                contact =  @Contact(email = "Diploma@example.com",
                                                name = "Diploma",
                                                url = "http://Diploma.me")))
@Default
public class DiplomaContract implements ContractInterface {
    public  DiplomaContract() {}

    @Transaction()
    public boolean diplomaExists(Context ctx, String id) {
      byte[] buffer = ctx.getStub().getState(id);
      return (buffer != null && buffer.length > 0);
    }

    @Transaction()
    public void createDiploma(
      Context ctx,
      String id,
      String idPessoa,
      String idInstituicao,
      String curso,
      String enfase,
      String dtEmissao,
      String dtConclusaoCurso,
      String dtAutenticacao,
      String codigoMEC,
      String template,
      String responsavel,
      String fotoBlob,
      String invalido
      )
    {
      boolean exists = diplomaExists(ctx,id);
      if (exists) {
          throw new RuntimeException("The asset "+id+" already exists");
      }
      Diploma asset = new Diploma();
      asset.setId(id);
      asset.setIdPessoa(idPessoa);
      asset.setIdInstituicao(idInstituicao);
      asset.setCurso(curso);
      asset.setEnfase(enfase);
      asset.setDtEmissao(dtEmissao);
      asset.setDtConclusaoCurso(dtConclusaoCurso);
      asset.setDtAutenticacao(dtAutenticacao);
      asset.setCodigoMEC(codigoMEC);
      asset.setTemplate(template);
      asset.setResponsavel(responsavel);
      asset.setFotoBlob(fotoBlob);
      asset.setInvalido(invalido);
      ctx.getStub().putState(id, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public Diploma readDiplomaById(Context ctx, String id) {
        boolean exists = diplomaExists(ctx,id);
        if (!exists) {
            throw new RuntimeException("The asset "+id+" does not exist");
        }

        Diploma newAsset = Diploma.fromJSONString(new String(ctx.getStub().getState(id),UTF_8));
        return newAsset;
    }

    @Transaction()
    public List<Diploma> readDiplomaByCpf(Context ctx, String idPessoa) {
        // boolean exists = diplomaExists(ctx,idPessoa);
        // if (!exists) {
        //     throw new RuntimeException("The asset "+idPessoa+" does not exist");
        // }

        // Diploma newAsset = Diploma.fromJSONString(new String(ctx.getStub().getState(idPessoa),UTF_8));
        // return newAsset;
        List<Diploma> diplomas = new ArrayList<Diploma>();
        QueryResultsIterator <KeyValue> results = ctx.getStub().getStateByRange("", "");
        
        for(KeyValue result: results) {
          Diploma dip = Diploma.fromJSONString(result.getStringValue());
          if(dip.getIdPessoa().equals(idPessoa))
            diplomas.add(dip);
        }

        return diplomas;
    }

    @Transaction()
    public List<Diploma> readDiplomaByInsituicao(Context ctx, String idInstituicao) {
        // boolean exists = diplomaExists(ctx,idInstituicao);
        // if (!exists) {
        //     throw new RuntimeException("The asset "+idInstituicao+" does not exist");
        // }
        List<Diploma> diplomas = new ArrayList<Diploma>();
        QueryResultsIterator <KeyValue> results = ctx.getStub().getStateByRange("", "");
        
        for(KeyValue result: results) {
          Diploma dip = Diploma.fromJSONString(result.getStringValue());
          if(dip.getIdInstituicao().equals(idInstituicao))
            diplomas.add(dip);
        }

        // Diploma newAsset = Diploma.fromJSONString(new String(ctx.getStub().getState(idInstituicao),UTF_8));
        return diplomas;
    }

    @Transaction()
    public void invalidarDiploma(
        Context ctx,
        String id
      )
    {
      boolean exists = diplomaExists(ctx,id);
      if (!exists) {
          throw new RuntimeException("The asset "+id+" does not exist");
      }
      Diploma asset = readDiplomaById(ctx, id);

      if(asset.getInvalido().equals("true")) {
        throw new RuntimeException("Ja é invalido.");
      }
      asset.setInvalido("true");

      ctx.getStub().putState(id, asset.toJSONString().getBytes(UTF_8));
    }
}
