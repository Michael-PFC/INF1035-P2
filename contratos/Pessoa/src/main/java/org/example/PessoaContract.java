/*
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import static java.nio.charset.StandardCharsets.UTF_8;

@Contract(name = "PessoaContract",
    info = @Info(title = "Pessoa contract",
                description = "My Smart Contract",
                version = "0.0.1",
                license =
                        @License(name = "Apache-2.0",
                                url = ""),
                                contact =  @Contact(email = "Pessoa@example.com",
                                                name = "Pessoa",
                                                url = "http://Pessoa.me")))
@Default
public class PessoaContract implements ContractInterface {
    public  PessoaContract() {}

    @Transaction()
    public boolean pessoaExists(Context ctx, String cpf) {
      byte[] buffer = ctx.getStub().getState(cpf);
      return (buffer != null && buffer.length > 0);
    }

    @Transaction()
    public void createPessoa(
      Context ctx,
      String cpf,
      String rg,
      String nrProfissional,
      String nome,
      String dtNascimento,
      String enderecoCompleto,
      String fotoBlob
      )
    {
      boolean exists = pessoaExists(ctx,cpf);
      if (exists) {
          throw new RuntimeException("The asset "+cpf+" already exists");
      }
      Pessoa asset = new Pessoa();
      asset.setCpf(cpf);
      asset.setRg(rg);
      asset.setNrProfissional(nrProfissional);
      asset.setNome(nome);
      asset.setDtNascimento(dtNascimento);
      asset.setEnderecoCompleto(enderecoCompleto);
      asset.setFotoBlob(fotoBlob);
      ctx.getStub().putState(cpf, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public Pessoa readPessoaByCpf(Context ctx, String cpf) {
        boolean exists = pessoaExists(ctx,cpf);
        if (!exists) {
            throw new RuntimeException("The asset "+cpf+" does not exist");
        }

        Pessoa newAsset = Pessoa.fromJSONString(new String(ctx.getStub().getState(cpf),UTF_8));
        return newAsset;
    }

    @Transaction()
    public void updatePessoa(
      Context ctx,
      String cpf,
      String rg,
      String nrProfissional,
      String nome,
      String dtNascimento,
      String enderecoCompleto,
      String fotoBlob
      )
    {
      boolean exists = pessoaExists(ctx,cpf);
      if (!exists) {
          throw new RuntimeException("The asset "+cpf+" does not exist");
      }
      Pessoa asset = readPessoaByCpf(ctx, cpf);
      if(!(rg == null || rg.trim().isEmpty())) {
        asset.setRg(rg);
      }
      if(!(nrProfissional == null || nrProfissional.trim().isEmpty())) {
        asset.setNrProfissional(nrProfissional);
      }
      if(!(nome == null || nome.trim().isEmpty())) {
        asset.setNome(nome);
      }
      if(!(dtNascimento == null || dtNascimento.trim().isEmpty())) {
        asset.setDtNascimento(dtNascimento);
      }
      if(!(enderecoCompleto == null || enderecoCompleto.trim().isEmpty())) {
        asset.setEnderecoCompleto(enderecoCompleto);
      }
      if(!(fotoBlob == null || fotoBlob.trim().isEmpty())) {
        asset.setFotoBlob(fotoBlob);
      }
      ctx.getStub().putState(cpf, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public Pessoa deletePessoa(Context ctx, String cpf) {
        boolean exists = pessoaExists(ctx,cpf);
        if (!exists) {
            throw new RuntimeException("The asset "+cpf+" does not exist");
        }
        Pessoa asset = readPessoaByCpf(ctx, cpf);
        ctx.getStub().delState(cpf);
        return asset;
    }
}
