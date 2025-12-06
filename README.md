# Tabla Hash y Simulador de Tabla de Ruteo  
Implementación en Java – Estructura de Datos 2025
Autor: Aby Piña Bernal

Este proyecto contiene la implementación completa de una Tabla Hash personalizada (Parte 1) y un Simulador de Router con Tabla de Ruteo (Parte 2), utilizando dicha estructura.  
El código fue desarrollado en NetBeans, utilizando Java en un proyecto tipo Ant.

----------------------------------------------------------------------------------------

## Parte 1: Implementación de TablaHash

La Parte 1 consiste en la implementación de una estructura de datos tipo HashMap utilizando encadenamiento separado.

Archivos incluidos:
- Diccionario.java  
- TablaHash.java  
- TestTablaHash.java  

Características principales:
- Manejo de colisiones mediante listas enlazadas.
- Factor de carga máximo de 0.75.
- Redimensionamiento automático (rehashing).
- Implementación de métodos fundamentales:
  - put(K, V)
  - get(K)
  - remove(K)
  - containsKey(K)
  - size()
  - keys() (añadido para permitir iteración en Parte 2)

--------------------------------------------------------------------------------

## Parte 2: Tabla de Ruteo de Red y Simulador

La Parte 2 aplica la estructura TablaHash para simular una tabla de ruteo real como las empleadas en routers.

Archivos incluidos:
- Ruta.java  
- TablaRuteo.java  
- SimuladorRouter.java  
- Main.java  

### Descripción general

La tabla de ruteo almacena rutas con información como:
- Red destino (CIDR)
- Máscara de subred
- Next hop
- Interfaz de salida
- Métrica
- Protocolo asociado

### Métodos principales

- agregarRuta(Ruta ruta):  
  Inserta o actualiza una ruta aplicando la lógica de mejor métrica.

- buscarRuta(String ipDestino):  
  Realiza la búsqueda utilizando un algoritmo simplificado de longest prefix match.

- eliminarRuta(String redDestino):  
  Elimina una ruta si existe en la tabla.

### Simulación

El archivo SimuladorRouter.java:
- Inicializa rutas típicas (rutas conectadas, OSPF, estáticas y default).
- Procesa un conjunto de paquetes.
- Muestra la ruta seleccionada y la acción correspondiente (FORWARD o DROP).
- Imprime estadísticas de búsqueda.

----------------------------------------------------------------------------

## Ejecución

Para ejecutar el proyecto:
1. Abrir el proyecto en NetBeans.
2. Asegurar que Main.java sea la clase principal.
3. Ejecutar mediante Run Project.

Ejemplo de salida esperada:
```
[ADD] Nueva ruta: 0.0.0.0/0
[ADD] Nueva ruta: 192.168.1.0/24
[ADD] Nueva ruta: 10.0.0.0/8
[ADD] Nueva ruta: 172.16.0.0/16

Paquete -> 192.168.1.50
Ruta encontrada: 192.168.1.0/24
ACCION: FORWARD

Paquete -> 10.5.3.2
Ruta encontrada: 10.0.0.0/8
ACCION: FORWARD
```

## Estructura del proyecto
```
HashMap/
├── nbproject/
├── src/
│ └── hashmap/
│ Diccionario.java
│ TablaHash.java
│ Ruta.java
│ TablaRuteo.java
│ SimuladorRouter.java
│ Main.java
├── build.xml
└── manifest.mf
```
