# sistema teste zagwork

Para executar o projeto, clonar com:

```
$ git clone https://github.com/edmilson1968/zagwork.git
```
## requisitos
* java8
* Angular7
* node8

O sistema é composto de dois módulos: frontend Angular e backend Springboot. Utiliza o banco H2 para maior simplicidade. 
Os dados do banco são gravados no arquivo /backend/risco_db.mv.db e pode ser alterado no arquivo application.properties do backend.

### backend

#### compilando & executando

Abrir uma janela de Terminal e executar os passos abaixo:

```
$ cd backend
$ mvn clean install
$ mvn spring-boot:run
```

### frontend

#### montando & executando

Em outra janela de Terminal, executar os passos abaixo:

```
$ cd frontend
$ npm install
$ ng serve
```
