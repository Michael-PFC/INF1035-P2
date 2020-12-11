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

@Contract(name = "InstituicaoContract",
    info = @Info(title = "Instituicao contract",
                description = "My Smart Contract",
                version = "0.0.1",
                license =
                        @License(name = "Apache-2.0",
                                url = ""),
                                contact =  @Contact(email = "inf1305_blockchain_201210_2038@example.com",
                                                name = "inf1305_blockchain_201210_2038",
                                                url = "http://inf1305_blockchain_201210_2038.me")))
@Default
public class InstituicaoContract implements ContractInterface {
    public  InstituicaoContract() {}

    @Transaction()
    public boolean instituicaoExists(Context ctx, String sId) {
        byte[] buffer = ctx.getStub().getState(sId);
        return (buffer != null && buffer.length > 0);
    }

    @Transaction()
    public void createInstituicao(
        Context ctx,
        String sId,
        String sNome,
        String sCNPJ,
        String sEnderecoCompleto
        ) {
        boolean exists = instituicaoExists(ctx,sId);
        if (exists) {
            throw new RuntimeException("The asset "+sId+" already exists");
        }
        Instituicao asset = new Instituicao();
        asset.setsId(sId);
        asset.setsNome(sNome);
        asset.setsCNPJ(sCNPJ);
        asset.setsEnderecoCompleto(sEnderecoCompleto);
        ctx.getStub().putState(sId, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public String readInstituicao(Context ctx, String sId) {
        boolean exists = instituicaoExists(ctx,sId);
        if (!exists) {
            throw new RuntimeException("The asset "+sId+" does not exist");
        }

        Instituicao newAsset = Instituicao.fromJSONString(new String(ctx.getStub().getState(sId),UTF_8));
        return new String(ctx.getStub().getState(sId),UTF_8);
        // return newAsset;
    }

    @Transaction()
    public void updateInstituicao(Context ctx, String sId, String sNome, String sCNPJ, String sEnderecoCompleto) {
        boolean exists = instituicaoExists(ctx,sId);
        if (!exists) {
            throw new RuntimeException("The asset "+sId+" does not exist");
        }
        Instituicao asset = new Instituicao();
        // asset.setValue(sId, sNome, sCNPJ, sEnderecoCompleto);
        asset.setsId(sId);
        asset.setsNome(sNome);
        asset.setsCNPJ(sCNPJ);
        asset.setsEnderecoCompleto(sEnderecoCompleto);
        ctx.getStub().putState(sId, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public void deleteInstituicao(Context ctx, String instituicaoId) {
        boolean exists = instituicaoExists(ctx,instituicaoId);
        if (!exists) {
            throw new RuntimeException("The asset "+instituicaoId+" does not exist");
        }
        ctx.getStub().delState(instituicaoId);
    }

}
