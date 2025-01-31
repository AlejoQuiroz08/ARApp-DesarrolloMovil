### README.md  

# Aplicación de Realidad Aumentada (ARApp)

Esta aplicación de realidad aumentada (AR) permite a los usuarios interactuar con modelos 3D y contenido multimedia en un entorno realista y dinámico. Está desarrollada utilizando tecnologías modernas como SceneView y ARCore para brindar una experiencia inmersiva y fluida.

### Funcionalidades principales
- **Colocación de modelos 3D**: Los usuarios pueden posicionar modelos 3D en el entorno mediante un botón interactivo.
- **Cambio de modelos**: Navega entre diferentes modelos 3D disponibles con botones dedicados para "Siguiente" y "Anterior".
- **Reproducción de contenido multimedia**: Incluye un nodo de video que se puede anclar al modelo principal para agregar animaciones o información visual adicional.

### Descripción técnica
La aplicación utiliza `ArSceneView` para renderizar los modelos 3D en tiempo real y gestionar las interacciones con el entorno. Los botones están diseñados para ofrecer una navegación intuitiva entre los modelos disponibles, y la integración con `MediaPlayer` permite la reproducción de contenido multimedia dentro del entorno de realidad aumentada.

### Capturas de pantalla
![WhatsApp Image 2025-01-10 at 3 39 28 PM (1)](https://github.com/user-attachments/assets/9744619e-893d-4b3e-8c83-38b9dbf6872c)
![WhatsApp Image 2025-01-10 at 3 39 27 PM](https://github.com/user-attachments/assets/d5f3eaf7-8cd0-424d-82d6-852ba6b3e607)
![WhatsApp Image 2025-01-10 at 3 39 28 PM](https://github.com/user-attachments/assets/765fdccb-25d6-4501-a55e-832fc587f64c)

Integrantes:
-Kevin Coloma, Isaac León, Carlos Ushiña, Cristian Usiña, Alejandro Quiroz, Lenin Masapanta y Sebastián Coronado

Documentación del Proyecto: Aplicación de Realidad Aumentada para la Escuela Politécnica Nacional (EPN)

Planteamiento del Proyecto

Etapa 1: Planificación y Diseño 

1. Definir Alcance:

•	Identificar los puntos de interés en el campus de la EPN que se mostrarán en la app, como facultades, edificios, y otras estructuras relevantes.
•	Determinar la información que se debe mostrar para cada punto de interés, que puede incluir texto (descripciones y datos curiosos), imágenes y modelos 3D.

2. Diseño de la Interfaz de Usuario (UI):
•	Crear wireframes y diseño de la interfaz gráfica utilizando herramientas como Figma o Adobe XD.
•	Definir la estructura de la app: pantallas de inicio, inicio de sesión, cámara en RA, y galería de fotos.

3. Estructura del Proyecto:
•	Planificar la estructura del código en Android Studio para organizar de manera lógica las funcionalidades (RA, cámara, geolocalización, API, etc.).
•	Asignar tareas si el proyecto se desarrolla en equipo, y establecer un cronograma de entregas.

Etapa 2: Desarrollo de Funcionalidades Básicas (1 semana)
1. Configuración del Proyecto en Android Studio:
•	Crear el proyecto en Android Studio configurado con las dependencias necesarias (ARCore, Google Maps, Firebase, etc.).
•	Establecer la estructura de carpetas adecuada y las clases necesarias.

2. Implementación de la Cámara:
•	Utilizar la API de cámara de Android para capturar imágenes y procesarlas en tiempo real.
•	Permitir que los usuarios apunten la cámara hacia puntos de interés para activar la superposición de elementos de RA.

3. Integración de ARCore:
•	Configurar ARCore para detectar planos en el entorno físico y permitir la superposición de modelos 3D o información textual sobre la cámara.
•	Verificar que los dispositivos sean compatibles con ARCore antes de habilitar la funcionalidad de RA.

4. Visualización de Información:
•	Mostrar la información básica sobre puntos de interés como texto en la pantalla o mediante marcadores en RA.
•	Implementar la visualización de modelos 3D sobre la imagen capturada por la cámara.

Etapa 3: Optimización y Funcionalidades Avanzadas (1 semana)
1. Incorporación de Modelos 3D:
•	Integrar modelos 3D de edificios u otros objetos relevantes en el campus, utilizando formatos como .glTF o .obj.
•	Optimizar los modelos para que no impacten negativamente en el rendimiento de la app (reducir la cantidad de polígonos y el tamaño de los archivos).

2. Geolocalización:
•	Implementar geolocalización para que la aplicación pueda identificar la ubicación actual del usuario y mostrar puntos de interés cercanos basados en su posición.
•	Utilizar la API de Google Maps para integrar mapas y ayudar a los usuarios a encontrar los puntos de interés.

3. Interactividad:
•	Permitir que los usuarios interactúen con los modelos 3D en la pantalla táctil, como girarlos, hacer zoom o ver más detalles sobre el punto de interés.
•	Implementar acciones interactivas como reproducir audios o mostrar imágenes adicionales al tocar los puntos de interés.

4. Pruebas en Dispositivos Reales:
•	Realizamos pruebas exhaustivas en dispositivos Android de diferentes gamas para asegurarnos de que la app funciona correctamente, especialmente en términos de rendimiento de RA y carga de modelos 3D.

Etapa 4: Presentación y Entrega 
1. Generar el Archivo APK:
•	Generamos el archivo APK final para distribución e instalación en dispositivos Android.
•	Verificamos que la app funcione correctamente en los dispositivos de destino y que no haya errores o caídas.

2. Preparación de la Presentación del Proyecto:
•	Preparamos una presentación en la que se explique:
o	La funcionalidad de la aplicación.
o	El diseño de la interfaz y las decisiones tomadas durante el proceso de desarrollo.
o	Los desafíos y problemas que surgieron durante el desarrollo, y cómo se resolvieron.
o	Los resultados obtenidos, como la interactividad y el uso de RA.
•	Presentar la app a los profesores o evaluadores, demostrando cómo la app responde a la ubicación del usuario y presenta modelos
