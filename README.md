# enterprise-project

Challenge demo project


# Enterprise Web Service Project

Este proyecto es una API REST desarrollada con **Spring Boot** utilizando **arquitectura hexagonal**, **H2** como base de datos en memoria y **Flyway** para la gestión de migraciones. Se incluyen excepciones personalizadas y pruebas unitarias.

## Endpoints Disponibles

### Empresas

#### 1. Obtener empresas adheridas en el último mes

**Endpoint:**`GET /empresas/adheridas-ultimo-mes`

**Descripción:** Retorna una lista de empresas que se adhirieron en el último mes.

**Respuesta:**

```json
{
  "items": [
    {
      "createdDate": "2025-03-01T03:00:00.000+00:00",
      "modifiedDate": "2025-03-02T03:00:00.000+00:00",
      "createdBy": "SYSTEM",
      "modifiedBy": "SYSTEM",
      "id": 2,
      "cuit": "30-22222222-2",
      "razonSocial": "Empresa B",
      "fechaAdhesion": "2025-03-01T03:00:00.000+00:00",
      "active": true
    }
  ]
}
```

#### 2. Adherir una empresa

**Endpoint:**`POST /empresas/adherir`

**Descripción:** Permite registrar una nueva empresa. (cuit: no debe existir)

**Body:**

```json
{
  "cuit": "30-22222222-2",
  "razonSocial": "Empresa B",
  "fechaAdhesion": "2025-03-10"
}
```

**Errores:**

* `409 Conflict` si el CUIT ya existe.
* `500 InternaServerError` si existe un error inesperado

### Transferencias

#### 3. Obtener transferencias del último mes

**Endpoint:**`GET /transferencias/ultimo-mes`

**Descripción:** Retorna las transferencias realizadas en el último mes.

**Respuesta:**

```json
[
  {
    "createdDate": "2025-03-01T03:00:00.000+00:00",
    "modifiedDate": "2025-03-02T03:00:00.000+00:00",
    "createdBy": "SYSTEM",
    "modifiedBy": "SYSTEM",
    "id": 2,
    "importe": 2000,
    "cuentaDebito": "234-567",
    "cuentaCredito": "890-123",
    "empresaCuit": "30-22222222-2",
    "active": true
  }
]
```

## Arquitectura

El proyecto sigue el patrón de **arquitectura hexagonal** con las siguientes capas:

* **Dominio:** Modelos de datos (`Empresa`, `Transferencia`).
* **Repositorio:** Interfaces para acceso a base de datos (`EmpresaRepository`, `TransferenciaRepository`).
* **Servicios:** Implementaciones de lógica de negocio (`EmpresaServiceImpl`, `TransferenciaServiceImpl`).
* **Controladores:** Exponen los endpoints REST (`EmpresaController`, `TransferenciaController`).
* **Excepciones:** Manejo de errores (`GlobalExceptionHandler`).
* Seguridad: Configuraciones básicas de seguridad.

## Configuración de Base de Datos

La base de datos H2 se configura en `application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
  flyway:
    locations: classpath:db/migration
```

## Pruebas

El proyecto incluye pruebas unitarias para los servicios y controladores, utilizando **JUnit** y **Mockito**.

Ejecutar pruebas con:

```shell
mvn test
```

## Herramientas Usadas

* **Spring Boot** (API REST)
* **H2** (Base de datos en memoria)
* **Flyway** (Migraciones de base de datos)
* **Lombok** (Reducción de código boilerplate)
* **JUnit / Mockito** (Pruebas unitarias)
* **Swagger** (Documentación de API)

## Cómo Ejecutar

```shell
mvn spring-boot:run
```

Acceder a **Swagger UI** en:

```
http://localhost:8080/swagger-ui.html
```
