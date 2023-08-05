# Usar una imagen base de Java 11
FROM openjdk:11-jdk-slim

# Copiar el archivo de fuentes personalizado al contenedor
COPY sources.list /etc/apt/sources.list


# Establecer el directorio de trabajo en el contenedor

WORKDIR /app


# Compilar el proyecto con Maven
RUN apt-get update -y
RUN apt-get install -y maven
RUN mvn package

# Copiar los archivos del proyecto al contenedor
COPY pom.xml /app/pom.xml
COPY src /app/src

# Especificar el comando para ejecutar la aplicación
CMD ["java", "-cp", "target/classes", "sd.grupo1.server.App"]
