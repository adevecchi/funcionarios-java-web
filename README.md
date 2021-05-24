## Descrição

Aplicativo web de gerenciamento de funcionários com recursos para Criar, Visualizar, Atualizar, Remover, Upload de imagem e utilizando DataTables Server-side processing.
Aplicando Design Patterns Front Controller, Command e Singleton.

## Ferramentas e tecnologias usadas

* Eclipse neon.3
* Tomcat 8.5
* Java 8
* JSP 2.3
* Servlet 3.1
* JSTL 1.2
* JDBC postgresql 42.2.5
* Gson 2.8.6
* Commons IO 2.8.0
* Commons FileUpload 1.4

## Estrutura do Projeto

![Projeto estrutura](https://github.com/adevecchi/funcionarios-java-web/blob/main/WebContent/assets/image/screenshot/project.png)

## Configuração Banco de Dados PostgreSQL

```sql
CREATE DATABASE dvq_employee;

CREATE TABLE employees
(
    id integer NOT NULL,
    fname varchar(80),
    lname varchar(80),
    department varchar(40),
    email varchar(80),
    image varchar(80),
    CONSTRAINT employees_pk PRIMARY KEY (id)
);
CREATE SEQUENCE employees_seq START 1;
GRANT ALL ON employees TO <user_name>;
GRANT ALL ON employees_seq TO <user_name>;
```

- No arquivo ***dev.lab.dao.EmployeeDao.java*** atualizar nome de usuário e senha do Banco de Dados PostgreSQL usados para acesso, conforme mostrado abaixo:

```java
private static final String url = "jdbc:postgresql://localhost/dvq_employee";
private static final String user = <user_name>;
private static final String pass = <user_password>;
```

## Captura de Tela

![Home index](https://github.com/adevecchi/funcionarios-java-web/blob/main/WebContent/assets/image/screenshot/home-index.png)

---

![Funcionarios index](https://github.com/adevecchi/funcionarios-java-web/blob/main/WebContent/assets/image/screenshot/funcionarios-index.png)

---

![Funcionarios novo](https://github.com/adevecchi/funcionarios-java-web/blob/main/WebContent/assets/image/screenshot/funcionarios-novo.png)

---

![Funcionarios editar](https://github.com/adevecchi/funcionarios-java-web/blob/main/WebContent/assets/image/screenshot/funcionarios-editar.png)

---

![Funcionarios detalhe](https://github.com/adevecchi/funcionarios-java-web/blob/main/WebContent/assets/image/screenshot/funcionarios-detalhe.png)

---

![Funcionario excluir](https://github.com/adevecchi/funcionarios-java-web/blob/main/WebContent/assets/image/screenshot/funcionarios-excluir.png)
