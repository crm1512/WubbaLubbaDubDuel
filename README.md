# Wubbaduel Backend

## Instrucciones para levantar el entorno de desarrollo

### 1. Instalar Java JDK 17
Descargar e instalar Java JDK 17 desde la página oficial:  
🔗 [https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
Seleccionar JDK 17 en los SDKs del IDE que se esté utilizando para ejecutar el backend.

---

### 2. Instalar Maven
Descargar Apache Maven desde:  
🔗 [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)  

Agregar la ruta del directorio `bin` de Maven a la variable de entorno `Path` del sistema.

---

### 3. Clonar el repositorio del backend
Ejecutar el siguiente comando en la terminal:

```bash
git clone https://github.com/crm1512/WubbaLubbaDubDuel.git
```

---

### 4. Configurar la conexión a la base de datos
Editar el archivo de configuración:

```
src/main/resources/application.properties
```

Agregar las credenciales de acceso a la base de datos (URL, usuario, contraseña, etc.).

---

### 5. Ejecutar la aplicación
Desde la raíz del proyecto, correr el siguiente comando:

```bash
mvn spring-boot:run
```

---

¡Listo! Ya puedes comenzar a usar la aplicación 
