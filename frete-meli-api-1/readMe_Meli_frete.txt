README

1 - Criar o Banco de Dados

	a - Criar um DataBase no MySQL com o nome meli_frete.

	b - Retirar a # da instrucao :

		#spring.jpa.hibernate.ddl-auto=create

	  no arquivo application.properties situado no diretorio src/main/resources da aplicacao.
	  
	c - Iniciar o servidor pivotal no Spring boot, onde estara instalada a aplicacao

	d - Inibir a instrucao de criacao do banco, mencionada no item 'b'.
	
2 - Carregar as tabelas basicas, onde estarao cadastradas as cidades e as ufs, com os comandos abaixo, atentando para as restricoes de integridade entre elas.

	INSERT INTO uf (CODE, NAME) VALUES ('MG', 'Minas Gerais');
	INSERT INTO uf (CODE, NAME) VALUES ('SP', 'São Paulo');
	INSERT INTO uf (CODE, NAME) VALUES ('CE', 'Ceará');


	INSERT INTO city (NAME, id_uf) VALUES ('Uberlândia', 1);
	INSERT INTO city (NAME, id_uf) VALUES ('Belo Horizonte', 1);
	INSERT INTO city (NAME, id_uf) VALUES ('São Paulo', 2);
	INSERT INTO city (NAME, id_uf) VALUES ('Campinas', 2);
	INSERT INTO city (NAME, id_uf) VALUES ('Fortaleza', 3);
	
3 - Para inclusao de registros na tabela merchandise, executar no postman a seguinte instrucao, com a opcao Post selecionada,
	alem de fornecer o corpo da instrucao, exemplo abaixo:

	http://localhost:8080/mercadorias

	Sugestao de operacoes com Post :
	{
		"category": "Frágil",
		"weight": "10kg",
		"volume": "1m³",
		"expected": "25/07/2020",
		"endereco": {
			"street": "Alameda Sorriso",
			"number": "1555",
			"city": "Belo Horizonte",
			"uf": "Minas Gerais"
		},
		"latitudeCurrent": "-23.0895106",
		"longitudeCurrent": "-46.9662692",
		"latitudeDestination": "-11.409874",
		"longitudeDestination": "-41.280857",
		"freight": 50.0
	}
	
4 - Para exclusao de registros na tabela merchandise, executar no postman a seguinte instrucao, com a opcao Delete selecionada,
	exemplo abaixo:

	http://localhost:8080/mercadorias/{numero do registro que se pretende excluir}
	
5 - Para listar os registros na tabela merchandise, executar no postman a seguinte instrucao, com a opcao Get selecionada,
	exemplo abaixo:

	http://localhost:8080/mercadorias

