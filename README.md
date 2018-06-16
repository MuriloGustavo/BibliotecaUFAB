# ProjetoWeb

Projeto da disciplina de programação web do professor Thiago Batista

Alunos: Murilo Gustavo dos Santos e Taynar Sousa

Backend com Spring Boot e Resp API
(Ao ser executado roda na porta 8080)

Frontend com Vue.js
(Ao ser executado roda na porta 8090)

Algumas observações:

- MySQL foi deixado em segundo plano e foi utilizado H2 como database
com um import SQL dentro do projeto para popular o banco de dados
(caso queira utilizar o MySQL, remova os comentarios da dependencia
do MySQL e comente a dependecia do H2 no arquivo pom.xml, lembrado que 
também deve remover os comentarios do application.properties e criar
um banco de dados com o nome do definido dentro do mesmo).

- O relatorio se encontra na raiz do projeto.
