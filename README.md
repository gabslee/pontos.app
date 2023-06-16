
# Pontos app - Farm

Backend para aplicação de marcação de pontos centrada na experiência do usuario

Este componente segue uma estrutura de pacotes divididos em funcionalidades



## Informações Gerais

- Linguagem: JDK 17
- Servidor Tomcat 9
- Frameworks:
  - Spring Boot 2.7.5
  - Spring Starter Web
  - Spring Data JPA
  - Spring Doc OpenAPI 1.6.13
- Gerenciador de dependências: Maven





## URLs Importantes

- Documentação da API: /swagger-ui/index.html



## Ambiente

- Local: http://localhost:8081

## Compilando

## Compilando localmente(JDK instalada na maquina)
- ###Build e Testes
```bash
  mvn clean package
```
- ###  Build (skip dos testes)

```bash
  mvn clean package -DskipTests=true
```
- ### Testes

```bash
  mvn test
```
- ### Coverage dos Testes

```bash
  mvn clean prepare
```
- ### Gerando Classes Swagger

```bash
  mvn generate-resources
```


## Compilando com Docker
Obs.: para esse modelo de compilação não é necessário ter nenhuma ferramenta Java, apenas o Docker.


```bash
  docker-compose up --build -d
```

## Documentação

[Documentação da Aplicação em PDF](https://drive.google.com/u/0/uc?id=1q5HJEcR-AYpXkAjCnfYd9CyIbdV5rTF5&export=download)

