# Usar imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Copiar o jar gerado pelo Maven (ajuste o nome do jar se necessário)
COPY target/UBS-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","/app.jar"]
