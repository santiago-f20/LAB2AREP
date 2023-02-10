# LAB 2 AREP

Segundo taller de la asignatura, en este se aplicaron conocimientos de HTTP, Sockets, HTML, JS, MAVEN, GIT.

### Prerrequisitos

Para ejecutar el laboratorio se debe tener instalado:

```
- Java
- Maven
- Git
```

### Instalación

Debe usarse el comando git clone para descargar el repositorio

```
git clone https://github.com/santiago-f20/LAB2AREP
```
Una vez descargado, en la carpeta del proyecto se debe ejecutar el comando siguiente para compilar el proyecto

```
mvn package
```

Para ejecutar el proyecto se debe usar el comando

```
java -cp target/classes;target/dependency/* edu.eci.arep.HttpServer
```
Una vez iniciado el servidor, se puede acceder a la pagina web en el siguiente link

```
http://localhost:35000
```

## Documentación

Para generar la documentación del proyecto se debe ejecutar el siguiente comando

```
mvn javadoc:javadoc
```
Para ver la documentación se debe abrir el archivo index.html que se encuentra en la carpeta target/site/apidocs

### Estructura del proyecto

```
.
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───edu
│   │   │       └───eci
│   │   │           └───arep
│   │   │                   HttpServer.java
│   │   │
│   │   └───resources
│   │       └───static
│   │           │   404.html
│   │           │   busqueda.html
│   │           │   index.html
│   │           │
│   │           ├───files
│   │           │       eci.png
│   │           │       sample.css
│   │           │       sample.html
│   │           │       sample.js
│   │           │
│   │           └───js
│   │                   search.js
│   │
│   └───test
│       └───java
│           └───edu
│               └───eci
│                   └───arep
│                           AppTest.java
```

## Construido con

* [Maven](https://maven.apache.org/) - Dependency Management
* [JAVA](https://www.java.com/es/download/) - Lenguaje de programación

## Autor

* **Santiago Fetecua** - [santiago-f20](https://github.com/santiago-f20)
