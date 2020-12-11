// /*
//  * SPDX-License-Identifier: Apache License 2.0
//  */

// package org.example;
// import static java.nio.charset.StandardCharsets.UTF_8;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.nio.charset.StandardCharsets;

// import org.hyperledger.fabric.contract.Context;
// import org.hyperledger.fabric.shim.ChaincodeStub;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;


// public final class InstituicaoContractTest {

//     @Nested
//     class AssetExists {
//         @Test
//         public void noProperAsset() {

//             InstituicaoContract contract = new  InstituicaoContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10001")).thenReturn(new byte[] {});
//             boolean result = contract.instituicaoExists(ctx,"10001");

//             assertFalse(result);
//         }

//         @Test
//         public void assetExists() {

//             InstituicaoContract contract = new  InstituicaoContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10001")).thenReturn(new byte[] {42});
//             boolean result = contract.instituicaoExists(ctx,"10001");

//             assertTrue(result);

//         }

//         @Test
//         public void noKey() {
//             InstituicaoContract contract = new  InstituicaoContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10002")).thenReturn(null);
//             boolean result = contract.instituicaoExists(ctx,"10002");

//             assertFalse(result);

//         }

//     }

//     @Nested
//     class AssetCreates {

//         @Test
//         public void newAssetCreate() {
//             InstituicaoContract contract = new  InstituicaoContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             String json = "{\"value\":\"TheInstituicao\"}";

//             contract.createInstituicao(ctx, "10001", "TheInstituicao");

//             verify(stub).putState("10001", json.getBytes(UTF_8));
//         }

//         @Test
//         public void alreadyExists() {
//             InstituicaoContract contract = new  InstituicaoContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10002")).thenReturn(new byte[] { 42 });

//             Exception thrown = assertThrows(RuntimeException.class, () -> {
//                 contract.createInstituicao(ctx, "10002", "TheInstituicao");
//             });

//             assertEquals(thrown.getMessage(), "The asset 10002 already exists");

//         }

//     }

//     @Test
//     public void assetRead() {
//         InstituicaoContract contract = new  InstituicaoContract();
//         Context ctx = mock(Context.class);
//         ChaincodeStub stub = mock(ChaincodeStub.class);
//         when(ctx.getStub()).thenReturn(stub);

//         Instituicao asset = new  Instituicao();
//         asset.setValue("Valuable");

//         String json = asset.toJSONString();
//         when(stub.getState("10001")).thenReturn(json.getBytes(StandardCharsets.UTF_8));

//         Instituicao returnedAsset = contract.readInstituicao(ctx, "10001");
//         assertEquals(returnedAsset.getValue(), asset.getValue());
//     }

//     @Nested
//     class AssetUpdates {
//         @Test
//         public void updateExisting() {
//             InstituicaoContract contract = new  InstituicaoContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);
//             when(stub.getState("10001")).thenReturn(new byte[] { 42 });

//             contract.updateInstituicao(ctx, "10001", "updates");

//             String json = "{\"value\":\"updates\"}";
//             verify(stub).putState("10001", json.getBytes(UTF_8));
//         }

//         @Test
//         public void updateMissing() {
//             InstituicaoContract contract = new  InstituicaoContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10001")).thenReturn(null);

//             Exception thrown = assertThrows(RuntimeException.class, () -> {
//                 contract.updateInstituicao(ctx, "10001", "TheInstituicao");
//             });

//             assertEquals(thrown.getMessage(), "The asset 10001 does not exist");
//         }

//     }

//     @Test
//     public void assetDelete() {
//         InstituicaoContract contract = new  InstituicaoContract();
//         Context ctx = mock(Context.class);
//         ChaincodeStub stub = mock(ChaincodeStub.class);
//         when(ctx.getStub()).thenReturn(stub);
//         when(stub.getState("10001")).thenReturn(null);

//         Exception thrown = assertThrows(RuntimeException.class, () -> {
//             contract.deleteInstituicao(ctx, "10001");
//         });

//         assertEquals(thrown.getMessage(), "The asset 10001 does not exist");
//     }

// }
