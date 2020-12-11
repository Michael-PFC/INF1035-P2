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


// public final class DiplomaContractTest {

//     @Nested
//     class AssetExists {
//         @Test
//         public void noProperAsset() {

//             DiplomaContract contract = new  DiplomaContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10001")).thenReturn(new byte[] {});
//             boolean result = contract.diplomaExists(ctx,"10001");

//             assertFalse(result);
//         }

//         @Test
//         public void assetExists() {

//             DiplomaContract contract = new  DiplomaContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10001")).thenReturn(new byte[] {42});
//             boolean result = contract.diplomaExists(ctx,"10001");

//             assertTrue(result);

//         }

//         @Test
//         public void noKey() {
//             DiplomaContract contract = new  DiplomaContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10002")).thenReturn(null);
//             boolean result = contract.diplomaExists(ctx,"10002");

//             assertFalse(result);

//         }

//     }

//     @Nested
//     class AssetCreates {

//         @Test
//         public void newAssetCreate() {
//             DiplomaContract contract = new  DiplomaContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             String json = "{\"value\":\"TheDiploma\"}";

//             contract.createDiploma(ctx, "10001", "TheDiploma");

//             verify(stub).putState("10001", json.getBytes(UTF_8));
//         }

//         @Test
//         public void alreadyExists() {
//             DiplomaContract contract = new  DiplomaContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10002")).thenReturn(new byte[] { 42 });

//             Exception thrown = assertThrows(RuntimeException.class, () -> {
//                 contract.createDiploma(ctx, "10002", "TheDiploma");
//             });

//             assertEquals(thrown.getMessage(), "The asset 10002 already exists");

//         }

//     }

//     @Test
//     public void assetRead() {
//         DiplomaContract contract = new  DiplomaContract();
//         Context ctx = mock(Context.class);
//         ChaincodeStub stub = mock(ChaincodeStub.class);
//         when(ctx.getStub()).thenReturn(stub);

//         Diploma asset = new  Diploma();
//         asset.setValue("Valuable");

//         String json = asset.toJSONString();
//         when(stub.getState("10001")).thenReturn(json.getBytes(StandardCharsets.UTF_8));

//         Diploma returnedAsset = contract.readDiploma(ctx, "10001");
//         assertEquals(returnedAsset.getValue(), asset.getValue());
//     }

//     @Nested
//     class AssetUpdates {
//         @Test
//         public void updateExisting() {
//             DiplomaContract contract = new  DiplomaContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);
//             when(stub.getState("10001")).thenReturn(new byte[] { 42 });

//             contract.updateDiploma(ctx, "10001", "updates");

//             String json = "{\"value\":\"updates\"}";
//             verify(stub).putState("10001", json.getBytes(UTF_8));
//         }

//         @Test
//         public void updateMissing() {
//             DiplomaContract contract = new  DiplomaContract();
//             Context ctx = mock(Context.class);
//             ChaincodeStub stub = mock(ChaincodeStub.class);
//             when(ctx.getStub()).thenReturn(stub);

//             when(stub.getState("10001")).thenReturn(null);

//             Exception thrown = assertThrows(RuntimeException.class, () -> {
//                 contract.updateDiploma(ctx, "10001", "TheDiploma");
//             });

//             assertEquals(thrown.getMessage(), "The asset 10001 does not exist");
//         }

//     }

//     @Test
//     public void assetDelete() {
//         DiplomaContract contract = new  DiplomaContract();
//         Context ctx = mock(Context.class);
//         ChaincodeStub stub = mock(ChaincodeStub.class);
//         when(ctx.getStub()).thenReturn(stub);
//         when(stub.getState("10001")).thenReturn(null);

//         Exception thrown = assertThrows(RuntimeException.class, () -> {
//             contract.deleteDiploma(ctx, "10001");
//         });

//         assertEquals(thrown.getMessage(), "The asset 10001 does not exist");
//     }

// }
