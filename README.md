# ExamenMovil
Repositorio publico para la resolucion del examen movil
Jaime Trujillo Ruiz
A01276139




Instrucciones


Contexto

En la última década, los juegos de lógica y rompecabezas han experimentado un resurgimiento significativo, con el Sudoku posicionándose como uno de los más populares a nivel mundial. Estudios neurológicos recientes han demostrado que resolver rompecabezas como el Sudoku estimula la actividad cerebral, mejora la concentración y puede incluso ayudar a prevenir el deterioro cognitivo.

En el entorno educativo actual, existe un creciente interés por incorporar elementos de "gamificación" y pensamiento lógico en el aprendizaje, especialmente en áreas STEM (Ciencia, Tecnología, Ingeniería y Matemáticas). Instituciones educativas y desarrolladores de software están buscando formas innovadoras de utilizar juegos de lógica para mejorar habilidades de resolución de problemas en estudiantes de todas las edades.

Necesidad

La Fundación para el Desarrollo Cognitivo (FDC) está implementando un programa nacional para promover el razonamiento lógico y matemático en jóvenes estudiantes. Te han contratado como Ingeniero de Software para desarrollar una aplicación móvil centrada en el Sudoku que servirá como herramienta educativa interactiva.

La aplicación debe permitir a estudiantes y educadores generar puzzles de Sudoku de diferentes niveles de dificultad, resolverlos interactivamente, y verificar sus soluciones. La FDC planea utilizar esta aplicación en escuelas secundarias y preparatorias como complemento a las clases de matemáticas, con el objetivo de mejorar las habilidades analíticas de los estudiantes mientras disfrutan del proceso de aprendizaje.

La FDC necesita una interfaz intuitiva que atraiga a los jóvenes, ofrezca desafíos apropiados para diferentes niveles de experiencia, y proporcione una experiencia educativa valiosa. Tu aplicación será una pieza fundamental en su iniciativa para promover el pensamiento lógico a través del juego.

Requerimientos Funcionales
La aplicación debe permitir generar puzzles de Sudoku de diferentes tamaños (4x4, 9x9) y niveles de dificultad (fácil, medio, difícil) utilizando la API de Sudoku de API Ninjas.
Debe incluir una interfaz interactiva donde los usuarios puedan resolver el puzzle ingresando números en las celdas vacías.
La aplicación debe proporcionar la funcionalidad de verificar si la solución ingresada por el usuario es correcta o incorrecta.
La aplicación debe permitir al usuario reiniciar el puzzle actual o solicitar uno nuevo sin perder el progreso en el puzzle actual.
La aplicación debe tener la capacidad de guardar y cargar partidas en progreso.
La interfaz debe mostrar claramente cuándo un puzzle ha sido completado correctamente.
Requerimientos No Funcionales
Se requiere implementar la arquitectura MVVM (Model-View-ViewModel) dentro de la aplicación.
Se deberá incorporar la meta-arquitectura Clean Architecture, organizando claramente las capas de datos, dominio y presentación.
La interfaz gráfica deberá ser desarrollada utilizando Jetpack Compose.
Se debe implementar manejo de estados de carga (loading), error y éxito en la interfaz de usuario.
Se evaluará positivamente el uso de inyección de dependencias mediante Hilt.
Deberás crear un repositorio en GitHub público e individual para subir tu solución, que debe incluir un archivo README.md detallando:
Tu nombre completo
Tu matrícula o identificación estudiantil
En caso de no incluir el README.md completo, se restarán 5 puntos de la calificación final.
La aplicación debe ser capaz de funcionar en modo offline una vez generado un puzzle.
El diseño debe ser responsive y adaptarse adecuadamente a diferentes tamaños de pantalla.
