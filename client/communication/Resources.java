/**
* @author  Thomas Jennings
* @since   2020-03-25
*/
package sample.hyperledger.blockchain.communication;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;

import sample.hyperledger.blockchain.model.*;

@javax.ws.rs.Path("Resources")
@ApplicationScoped
public class Resources {
	
	//set this for the location of the wallet directory and the connection json file
	static String pathRoot = "../../../../../../Users/Shared/Connection/";

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}
	
	@Timed(name = "addPessoaProcessingTime",
	         tags = {"method=post"},
	         absolute = true,
	         description = "Time needed to add pessoa to the inventory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@javax.ws.rs.Path("Pessoa")
	@Operation(
			summary = "Add a pessoa to the ledger",
			description = "Requires a unique cpf to be successful")
	public Pessoa addPessoa(
			Pessoa aPessoa
			)
	{
		try {
			Path walletPath = Paths.get(pathRoot + "wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);
			
			//load a CCP
			//expecting the connect profile json file; export the Connection Profile from the
			//fabric gateway and add to the default server location 
			Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");
			
			Gateway.Builder builder = Gateway.createBuilder();
			
			//expecting wallet directory within the default server location
			//wallet exported from Fabric wallets Org 1
			builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
			try (Gateway gateway = builder.connect()) {
				
				//get the network and contract
				Network network = gateway.getNetwork("mychannel");
				Contract contract = network.getContract("pessoa");
				contract.submitTransaction(
					"createPessoa",
					aPessoa.getCpf(),
					aPessoa.getRg(),
					aPessoa.getNrProfissional(),
					aPessoa.getNome(),
					aPessoa.getDtNascimento(),
					aPessoa.getEnderecoCompleto(),
					aPessoa.getFotoBlob()
					);
				return new Pessoa(
					aPessoa.getCpf(),
					aPessoa.getRg(),
					aPessoa.getNrProfissional(),
					aPessoa.getNome(),
					aPessoa.getDtNascimento(),
					aPessoa.getEnderecoCompleto(),
					aPessoa.getFotoBlob()
				);
			}
			catch (Exception e){
				System.out.println("Unable to get network/contract and execute query"); 
				throw new javax.ws.rs.ServiceUnavailableException();
			}
		} 
		catch (Exception e2) 
		{
			String current;
			try {
				current = new java.io.File( "." ).getCanonicalPath();
				System.out.println("Current working dir: "+current);
			} catch (IOException e) {
				throw new javax.ws.rs.ServiceUnavailableException();
			}
			System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}	
	}

	@Timed(name = "addDiplomaProcessingTime",
	         tags = {"method=post"},
	         absolute = true,
	         description = "Time needed to add diploma to the inventory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@javax.ws.rs.Path("Diploma")
	@Operation(
			summary = "Add a diploma to the ledger",
			description = "Requires a unique id to be successful")
	public Diploma addDiploma(
			Diploma oDiploma
			)
	{
		try {
			Path walletPath = Paths.get(pathRoot + "wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);
			
			//load a CCP
			//expecting the connect profile json file; export the Connection Profile from the
			//fabric gateway and add to the default server location 
			Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");
			
			Gateway.Builder builder = Gateway.createBuilder();
			
			//expecting wallet directory within the default server location
			//wallet exported from Fabric wallets Org 1
			builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
			try (Gateway gateway = builder.connect()) {
				
				//get the network and contract
				Network network = gateway.getNetwork("mychannel");
				Contract contract = network.getContract("diploma");
				contract.submitTransaction(
					"createDiploma",
					oDiploma.getId(),
					oDiploma.getIdPessoa(),
					oDiploma.getIdInstituicao(),
					oDiploma.getCurso(),
					oDiploma.getEnfase(),
					oDiploma.getDtEmissao(),
					oDiploma.getDtConclusaoCurso(),
					oDiploma.getDtAutenticacao(),
					oDiploma.getCodigoMEC(),
					oDiploma.getTemplate(),
					oDiploma.getResponsavel(),
					oDiploma.getFotoBlob(),
					oDiploma.getInvalido()
					);

				return new Diploma(
					oDiploma.getId(),
					oDiploma.getIdPessoa(),
					oDiploma.getIdInstituicao(),
					oDiploma.getCurso(),
					oDiploma.getEnfase(),
					oDiploma.getDtEmissao(),
					oDiploma.getDtConclusaoCurso(),
					oDiploma.getDtAutenticacao(),
					oDiploma.getCodigoMEC(),
					oDiploma.getTemplate(),
					oDiploma.getResponsavel(),
					oDiploma.getFotoBlob(),
					oDiploma.getInvalido()
				);
			}
			catch (Exception e){
				System.out.println("Unable to get network/contract and execute query"); 
				throw new javax.ws.rs.ServiceUnavailableException();
			}
		} 
		catch (Exception e2) 
		{
			String current;
			try {
				current = new java.io.File( "." ).getCanonicalPath();
				System.out.println("Current working dir: "+current);
			} catch (IOException e) {
				throw new javax.ws.rs.ServiceUnavailableException();
			}
			System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}	
	}
	
	@Timed(name = "UpdatePessoaProcessingTime",
	         tags = {"method=put"},
	         absolute = true,
	         description = "Time needed to update pessoa in the inventory")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@javax.ws.rs.Path("Pessoa")
	@Operation(
			summary = "Update a pessoa in the ledger",
			description = "Requires the cpf to the pessoa and a pessoa info")
	public Pessoa updatePessoa(
			Pessoa aPessoa
			)
	{
		try {
			Path walletPath = Paths.get(pathRoot + "wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);
			
			//load a CCP
			//expecting the connect profile json file; export the Connection Profile from the
			//fabric gateway and add to the default server location 
			Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");
			
			Gateway.Builder builder = Gateway.createBuilder();
			
			//expecting wallet directory within the default server location
			//wallet exported from Fabric wallets Org 1
			builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
			try (Gateway gateway = builder.connect()) {
				
				//get the network and contract
				Network network = gateway.getNetwork("mychannel");
				Contract contract = network.getContract("pessoa");
				contract.submitTransaction(
					"updatePessoa",
					aPessoa.getCpf(),
					aPessoa.getRg(),
					aPessoa.getNrProfissional(),
					aPessoa.getNome(),
					aPessoa.getDtNascimento(),
					aPessoa.getEnderecoCompleto(),
					aPessoa.getFotoBlob()
				);
				//TODO: Retorna os dados atualizados, mas os que estao na ledger, pode ser que algum campo 
				// seja null
				return new Pessoa(
					aPessoa.getCpf(),
					aPessoa.getRg(),
					aPessoa.getNrProfissional(),
					aPessoa.getNome(),
					aPessoa.getDtNascimento(),
					aPessoa.getEnderecoCompleto(),
					aPessoa.getFotoBlob()
				);
			}
			catch (Exception e){
				System.out.println("Unable to get network/contract and execute query"); 
				throw new javax.ws.rs.ServiceUnavailableException();
			}
		} 
		catch (Exception e2) 
		{
			String current;
			try {
				current = new java.io.File( "." ).getCanonicalPath();
				System.out.println("Current working dir: "+current);
			} catch (IOException e) {
				throw new javax.ws.rs.ServiceUnavailableException();
			}
			System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}	
	}

	@Timed(name = "UpdateDiplomaProcessingTime",
	tags = {"method=put"},
	absolute = true,
	description = "Time needed to update pessoa in the inventory")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@javax.ws.rs.Path("Diploma")
	@Operation(
	summary = "Update a diploma in the ledger",
	description = "Requires the id to the pessoa and a pessoa info")
		public String updateDiploma(
			@QueryParam("id")String id
		)
	{
	try {
		Path walletPath = Paths.get(pathRoot + "wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);
		
		//load a CCP
		//expecting the connect profile json file; export the Connection Profile from the
		//fabric gateway and add to the default server location 
		Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");
		
		Gateway.Builder builder = Gateway.createBuilder();
		
		//expecting wallet directory within the default server location
		//wallet exported from Fabric wallets Org 1
		builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
		try (Gateway gateway = builder.connect()) {
		
		//get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("diploma");
		contract.submitTransaction("invalidarDiploma",id);
		//TODO: Retorna os dados atualizados, mas os que estao na ledger, pode ser que algum campo 
		// seja null
		byte[] result = contract.evaluateTransaction("readDiplomaById", id);
		String outputString = new String(result);
		return outputString;
	}
	catch (Exception e){
		System.out.println("Unable to get network/contract and execute query"); 
		throw new javax.ws.rs.ServiceUnavailableException();
	}
	} 
	catch (Exception e2) 
	{
	String current;
	try {
		current = new java.io.File( "." ).getCanonicalPath();
		System.out.println("Current working dir: "+current);
	} catch (IOException e) {
		throw new javax.ws.rs.ServiceUnavailableException();
	}
	System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
	throw new javax.ws.rs.ServiceUnavailableException();
	}	
	}

	@Timed(name = "QueryAPessoaProcessingTime",
	         tags = {"method=GET"},
	         absolute = true,
	         description = "Time needed to query a pessoa")
	@GET
	@javax.ws.rs.Path("Pessoa")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Returns an individual pessoa by cpf",
			description = "Requires the cpf to be provided")
	public String QueryPessoaBycpf(@QueryParam("cpf")String cpf
			)
	{
		byte[] result = null;
		String outputString = "";
		String passedOutput = "";
		
		try {
			Path walletPath = Paths.get(pathRoot + "wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);
			
			//load a CCP
			//expecting the connect profile json file; export the Connection Profile from the
			//fabric gateway and add to the default server location 
			Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");
			
			Gateway.Builder builder = Gateway.createBuilder();
			
			//expecting wallet directory within the default server location
			//wallet exported from Fabric wallets Org 1
			builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
			try (Gateway gateway = builder.connect()) {
				
				//get the network and contract
				Network network = gateway.getNetwork("mychannel");
				Contract contract = network.getContract("pessoa");
				result = contract.evaluateTransaction("readPessoaByCpf", cpf);
				outputString = new String(result);
				passedOutput = "Queried pessoa Successfully. \nCPF = " + cpf + "\nDetails = " + outputString;
				return passedOutput;
			}
			catch (Exception e){
				System.out.println("Unable to get network/contract and execute query"); 
				throw new javax.ws.rs.ServiceUnavailableException();
			}
		} 
		catch (Exception e2) 
		{
			String current;
			try {
				current = new java.io.File( "." ).getCanonicalPath();
				System.out.println("Current working dir: "+current);
			} catch (IOException e) {
				throw new javax.ws.rs.ServiceUnavailableException();
			}
			System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}
	}

	@Timed(name = "QueryODiplomaProcessingTime",
	         tags = {"method=GET"},
	         absolute = true,
	         description = "Time needed to query a diploma")
	@GET
	@javax.ws.rs.Path("Diploma")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Returns an individual diploma by id",
			description = "Requires the id to be provided")
	public String QueryDiplomaById(@QueryParam("id")String id
			)
	{
		byte[] result = null;
		String outputString = "";
		String passedOutput = "";
		
		try {
			Path walletPath = Paths.get(pathRoot + "wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);
			
			//load a CCP
			//expecting the connect profile json file; export the Connection Profile from the
			//fabric gateway and add to the default server location 
			Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");
			
			Gateway.Builder builder = Gateway.createBuilder();
			
			//expecting wallet directory within the default server location
			//wallet exported from Fabric wallets Org 1
			builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
			try (Gateway gateway = builder.connect()) {
				
				//get the network and contract
				Network network = gateway.getNetwork("mychannel");
				Contract contract = network.getContract("diploma");
				result = contract.evaluateTransaction("readDiplomaById", id);
				outputString = new String(result);
				passedOutput = "Queried diploma Successfully. \nid = " + id + "\nDetails = " + outputString;
				return passedOutput;
			}
			catch (Exception e){
				System.out.println("Unable to get network/contract and execute query"); 
				throw new javax.ws.rs.ServiceUnavailableException();
			}
		} 
		catch (Exception e2) 
		{
			String current;
			try {
				current = new java.io.File( "." ).getCanonicalPath();
				System.out.println("Current working dir: "+current);
			} catch (IOException e) {
				throw new javax.ws.rs.ServiceUnavailableException();
			}
			System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}
	}

	@Timed(name = "DeletePessoaProcessingTime",
	tags = {"method=PATCH"},
	absolute = true,
	description = "Time needed to delete a pessoa")
	@PATCH
	@javax.ws.rs.Path("Pessoa")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
	summary = "Delete an individual pessoa by cpf",
	description = "Requires the cpf to be provided")
	public String DeletePessoa(@QueryParam("cpf")String cpf){
		byte[] result = null;
		try {
			Path walletPath = Paths.get(pathRoot + "wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);
			
			//load a CCP
			//expecting the connect profile json file; export the Connection Profile from the
			//fabric gateway and add to the default server location 
			Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");
			
			Gateway.Builder builder = Gateway.createBuilder();
			
			//expecting wallet directory within the default server location
			//wallet exported from Fabric wallets Org 1
			builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
			
			try (Gateway gateway = builder.connect()) {
				//get the network and contract
				Network network = gateway.getNetwork("mychannel");
				Contract contract = network.getContract("pessoa");

				result = contract.submitTransaction("deletePessoa", cpf);
				return new String(result);

			} catch (Exception e){
				System.out.println("Unable to get network/contract and execute query"); 
				throw new javax.ws.rs.ServiceUnavailableException();
			}
		} catch (Exception e2) 
		{
			String current;
			try {
				current = new java.io.File( "." ).getCanonicalPath();
				System.out.println("Current working dir: "+current);
			} catch (IOException e) {
				throw new javax.ws.rs.ServiceUnavailableException();
			}
			System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}
	}

	@Timed(name = "addInstituicaoProcessingTime",
	tags = {"method=post"},
	absolute = true,
	description = "Time needed to add Instituicao to the inventory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@javax.ws.rs.Path("Instituicao")
	@Operation(
	summary = "Add a Instituicao to the ledger",
	description = "Requires a unique CNPJ to be successful")
		public Instituicao addInstituicao(
			Instituicao oInstituicao
	)
	{
		
	try {
		Path walletPath = Paths.get(pathRoot + "wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);
		//load a CCP
		//expecting the connect profile json file; export the Connection Profile from the
		//fabric gateway and add to the default server location 
		Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");
		Gateway.Builder builder = Gateway.createBuilder();
		//expecting wallet directory within the default server location
		//wallet exported from Fabric wallets ONome 1
		builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
		try (Gateway gateway = builder.connect()) {
		//get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("instituicao");
		contract.submitTransaction(
			"createInstituicao",
			oInstituicao.getId(),
			oInstituicao.getNome(),
			oInstituicao.getCnpj(),
			oInstituicao.getEnderecoCompleto()
		);
		return new Instituicao(
			oInstituicao.getId(),
			oInstituicao.getNome(),
			oInstituicao.getCnpj(),
			oInstituicao.getEnderecoCompleto()
		);
		}
		catch (Exception e){
			System.out.println("Unable to get network/contract and execute query"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}
		} 
			catch (Exception e2) 
		{
			String current;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
			System.out.println("Current working dir: "+current);
		} catch (IOException e) {
			throw new javax.ws.rs.ServiceUnavailableException();
		}
			System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}	
	}

	@Timed(name = "UpdateInstituicaoProcessingTime",
		tags = {"method=put"},
		absolute = true,
		description = "Time needed to update Instituicao in the inventory")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@javax.ws.rs.Path("Instituicao")
	@Operation(
	summary = "Update a Instituicao in the ledger",
	description = "Requires the Id to the Instituicao and a Instituicao info")
	public Instituicao updateInstituicao(
		Instituicao oInstituicao
	)
	{
	try {
		Path walletPath = Paths.get(pathRoot + "wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		//load a CCP
		//expecting the connect profile json file; export the Connection Profile from the
		//fabric gateway and add to the default server location 
		Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");

		Gateway.Builder builder = Gateway.createBuilder();

		//expecting wallet directory within the default server location
		//wallet exported from Fabric wallets ONome 1
		builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
		try (Gateway gateway = builder.connect()) {

		//get the network and contract
		
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("instituicao");
		contract.submitTransaction(
			"updateInstituicao",
			oInstituicao.getId(),
			oInstituicao.getNome(),
			oInstituicao.getCnpj(),
			oInstituicao.getEnderecoCompleto()
		);
		//TODO: Retorna os dados atualizados, mas os que estao na ledger, pode ser que algum campo 
		// seja null
		return new Instituicao(
			oInstituicao.getId(),
			oInstituicao.getNome(),
			oInstituicao.getCnpj(),
			oInstituicao.getEnderecoCompleto()
			);
		}
		catch (Exception e){
			System.out.println("Unable to get network/contract and execute query"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}
		} 
		catch (Exception e2) 
		{
			String current;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
			System.out.println("Current working dir: "+current);
		} catch (IOException e) {
			throw new javax.ws.rs.ServiceUnavailableException();
		}
			System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
			throw new javax.ws.rs.ServiceUnavailableException();
		}	
	}

	@Timed(name = "QueryoInstituicaoProcessingTime",
		tags = {"method=GET"},
		absolute = true,
		description = "Time needed to query a Instituicao")
	@GET
	@javax.ws.rs.Path("Instituicao")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
	summary = "Returns an individual Instituicao by Id",
	description = "Requires the Id to be provided")
	public String QueryInstituicao(@QueryParam("Id")String Id
	)
	{
		byte[] result = null;
		String outputString = "";
		String passedOutput = "";

		try {
			Path walletPath = Paths.get(pathRoot + "wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);

			//load a CCP
			//expecting the connect profile json file; export the Connection Profile from the
			//fabric gateway and add to the default server location 
			Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");

			Gateway.Builder builder = Gateway.createBuilder();

			//expecting wallet directory within the default server location
			//wallet exported from Fabric wallets ONome 1
			builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);
		try (Gateway gateway = builder.connect()) {

			//get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("instituicao");
			result = contract.evaluateTransaction("readInstituicao", Id);
			outputString = new String(result);
			passedOutput = "Queried Instituicao Successfully. \nId = " + Id + "\nDetails = " + outputString;
			return passedOutput;
	}
	catch (Exception e){
		System.out.println("Unable to get network/contract and execute query"); 
		throw new javax.ws.rs.ServiceUnavailableException();
	}
	} 
		catch (Exception e2) 
	{
		String current;
	try {
		current = new java.io.File( "." ).getCanonicalPath();
		System.out.println("Current working dir: "+current);
	} catch (IOException e) {
		throw new javax.ws.rs.ServiceUnavailableException();
	}
		System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
		throw new javax.ws.rs.ServiceUnavailableException();
	}
	}

	@Timed(name = "DeleteInstituicaoProcessingTime",
	tags = {"method=PATCH"},
	absolute = true,
	description = "Time needed to delete a Instituicao")
	@PATCH
	@javax.ws.rs.Path("Instituicao")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
	summary = "Delete an individual Instituicao by Id",
	description = "Requires the Id to be provided")
	public String DeleteInstituicao(@QueryParam("sId")String sId){

	byte[] result = null;

	try {
	Path walletPath = Paths.get(pathRoot + "wallet");
	Wallet wallet = Wallet.createFileSystemWallet(walletPath);

	//load a CCP
	//expecting the connect profile json file; export the Connection Profile from the
	//fabric gateway and add to the default server location 
	Path networkConfigPath = Paths.get(pathRoot + "1-Org-Local-Fabric-Org1_connection.json");

	Gateway.Builder builder = Gateway.createBuilder();

	//expecting wallet directory within the default server location
	//wallet exported from Fabric wallets ONome 1
	builder.identity(wallet, "org1Admin").networkConfig(networkConfigPath).discovery(true);

	try (Gateway gateway = builder.connect()) {
	//get the network and contract
	Network network = gateway.getNetwork("mychannel");
	Contract contract = network.getContract("instituicao");

	result = contract.submitTransaction("deleteInstituicao", sId);
	return new String(result);

	} catch (Exception e){
	System.out.println("Unable to get network/contract and execute query"); 
	throw new javax.ws.rs.ServiceUnavailableException();
	}
	} catch (Exception e2) 
	{
	String current;
	try {
	current = new java.io.File( "." ).getCanonicalPath();
	System.out.println("Current working dir: "+current);
	} catch (IOException e) {
	throw new javax.ws.rs.ServiceUnavailableException();
	}
	System.out.println("Unable to find config or wallet - please check the wallet directory and connection json"); 
	throw new javax.ws.rs.ServiceUnavailableException();
	}
	}
}