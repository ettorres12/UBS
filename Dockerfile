# Etapa 1: Build com Maven
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

# Copia arquivos de configuração e código
COPY pom.xml .
COPY src ./src

# Build do projeto (gera o JAR)
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com JDK
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copia o JAR da etapa de build
COPY --from=build /app/target/UBS-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","/app/app.jar"]
