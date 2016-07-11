DataConverter-CP
Converte as informações de estrutura de dados, em tabelas e colunas para o banco MySQL: Para isto é necessário um arquivo de estrutura em formato 'cvs' que contenha o nome do campo, descrição, tamanho do campo, tipo do campo e posição inicial e posição final no arquivo de dados.

Converte um arquivo de dados, em informações para a tabelas importadas: Para isto é necessário um arquivo em formato 'txt' que contenha os dados de acordo com a estrutura lida no arquivo 'cvs'.

Observações: O algoritmo lê o arquivo de estrutura, realiza a criação e estruturação da tabela e em seguida lê o arquivo de dados, populando as tabelas de acordo com o modelo lido. Para validar os dados inseridos na tabelas, pode avaliar a quantidade de dados contida no arquivo txt, e comparar aos inseridos no banco de dados.
