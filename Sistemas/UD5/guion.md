## Masterclass Auditoría de Red



### INTRODUCCIÓN — 3 min

> *"Hoy vamos a ver una auditoría de red completa: qué herramientas se usan, para qué sirve cada una y cómo encajan entre sí. Todo lo que voy a mostrar lo he hecho en un entorno de laboratorio controlado — una máquina virtual con Kali Linux y un servidor Apache. Nada de esto se aplica fuera de entornos con autorización, porque hacerlo sería ilegal."*

> *"Una auditoría de red tiene básicamente cuatro fases: reconocimiento, escaneo, explotación y remediación. Hoy vamos a recorrer todas."*

---

### BLOQUE 1 — Comandos de diagnóstico

#### ¿Qué es el ping y por qué existe?

> *"El ping es el comando más básico de diagnóstico de red. Envía paquetes ICMP — que son paquetes de control, no de datos — a un host para ver si responde. Es como llamar a alguien a la puerta: si abre, está vivo; si no, o no está o tiene las persianas bajadas."*

> *"Nos da tres datos útiles: si el host está activo, la latencia — cuánto tarda en responder — y el TTL, que es el Time To Live."*

> *"El TTL es el número de saltos que puede dar un paquete antes de que los routers lo descarten, para evitar que circule indefinidamente. Pero también nos sirve para algo muy interesante en seguridad: cada sistema operativo tiene un TTL de origen distinto. Linux parte de 64, Windows de 128, los routers Cisco de 255. Si recibimos un TTL de 118, sabemos que partió de 128 — es Windows — y ha dado 10 saltos hasta llegar a nosotros. Esto se llama fingerprinting pasivo: identificar el sistema operativo sin enviar nada sospechoso, solo analizando la respuesta."*

**[Mostrar captura de ping a 8.8.8.8]** — señalar el TTL=118 y la latencia media.

---

#### ¿Qué es Traceroute?

> *"Traceroute lleva el concepto del ping un paso más allá. En vez de preguntar '¿estás ahí?', pregunta '¿por dónde voy hasta llegar a ti?'. Muestra cada router intermedio — cada salto — que atraviesa el paquete hasta el destino, junto con la latencia en cada punto."*

> *"¿Cómo funciona? Envía paquetes con TTL progresivo: primero con TTL=1, que caduca en el primer router y ese router responde con un mensaje de error ICMP diciéndonos quién es. Luego TTL=2, que llega al segundo router. Y así hasta el destino."*

> *"Esto sirve para detectar cuellos de botella — si un salto tiene latencia muy alta — y también para ver dónde hay firewalls."*

**[Mostrar captura del traceroute con los asteriscos]**

> *"Los asteriscos significan que ese router tiene bloqueadas las respuestas ICMP. No significa que el paquete no pase — el paquete sí pasa — pero ese router descarta silenciosamente los mensajes de control. Es una práctica de seguridad habitual en ISPs y empresas para no revelar su infraestructura interna."*

> *"Existen tres variantes: la estándar usa UDP, la versión con -I usa ICMP, y la versión con -T usa TCP por el puerto 80. Esta última es la más efectiva contra firewalls porque bloquear el puerto 80 sería demasiado agresivo — casi ningún firewall lo hace."*

---

#### ¿Qué es el DNS y para qué sirve NSLOOKUP?

> *"DNS, Domain Name System, es la agenda telefónica de internet. Traduce nombres de dominio legibles — como google.com — a direcciones IP que entienden las máquinas. Sin DNS tendríamos que memorizar IPs para todo."*

> *"NSLOOKUP es la herramienta para consultar esos registros DNS manualmente. Existen varios tipos de registros: el registro A apunta un dominio a una IP, el registro MX indica el servidor de correo de una organización, el registro TXT contiene políticas de seguridad como SPF y DKIM, y el registro CNAME crea alias entre dominios."*

> *"En ciberseguridad, el DNS revela infraestructura sin necesidad de tocar ningún sistema. Con solo consultas DNS podemos saber qué proveedor de correo usa una empresa, dónde está su servidor web o qué políticas de seguridad de email tienen configuradas. Si el registro MX apunta a google.com, la empresa usa Google Workspace; si apunta a outlook.com, usa Microsoft 365. Eso permite a un atacante imitar exactamente el aspecto del correo legítimo en un ataque de phishing dirigido."*

**[Mostrar captura del MX de gmail.com]** — señalar los servidores smtp.google.com.

---

#### ¿Qué es Netstat y SS?

> *"Netstat, que viene de Network Statistics, muestra el estado de la red desde dentro de la máquina: qué puertos están escuchando, qué conexiones hay activas, la tabla de enrutamiento y estadísticas de interfaces."*

> *"Un puerto es como una puerta numerada en una casa. La IP es la dirección de la casa; el puerto indica a qué habitación llamar. El puerto 80 es HTTP, el 443 HTTPS, el 22 SSH, el 3306 MySQL. Saber qué puertos tiene abiertos una máquina es el primer paso de cualquier ataque."*

> *"SS — Socket Statistics — es el sucesor moderno de Netstat. Hace lo mismo pero consulta directamente al kernel a través de netlink, mientras Netstat lee el archivo /proc/net/tcp. En sistemas con miles de conexiones, SS es significativamente más rápido. En muchas distribuciones modernas, Netstat ya ni siquiera viene instalado por defecto."*

**[Mostrar captura de netstat -tlnp]** — señalar la línea del puerto 80 con apache2.

> *"Aquí vemos exactamente lo que un atacante buscaría: puerto 80, protocolo TCP, proceso apache2, escuchando en 0.0.0.0 — es decir, en todas las interfaces de red. Cualquier IP que llegue al sistema puede intentar conectarse."*

---

### BLOQUE 2 — Nmap

#### ¿Qué es Nmap?

> *"Nmap, Network Mapper, es la herramienta de reconocimiento más utilizada en ciberseguridad. Nació en 1997 y sigue siendo el estándar porque es increíblemente versátil: descubre hosts activos en una red, identifica puertos abiertos, detecta versiones de servicios, intenta adivinar el sistema operativo y puede ejecutar scripts para buscar vulnerabilidades conocidas."*

> *"Funciona enviando paquetes especialmente construidos a los puertos del objetivo y analizando las respuestas. Un puerto puede estar abierto — hay un servicio escuchando — cerrado — no hay servicio pero el host responde — o filtrado — un firewall está bloqueando las respuestas."*

#### Tipos de escaneo

> *"El escaneo más común es el SYN scan, también llamado escaneo sigiloso. En vez de completar el handshake TCP completo — que es la negociación inicial entre cliente y servidor — envía solo el primer paquete SYN y analiza la respuesta. Si recibe SYN-ACK, el puerto está abierto; si recibe RST, está cerrado. Al no completar la conexión, muchos sistemas de logs no registran el intento."*

> *"Con el flag -sV, Nmap intenta identificar la versión exacta del servicio enviando peticiones específicas y analizando los banners de respuesta — que son los mensajes de presentación que envían los servidores."*

**[Mostrar las tres capturas: básico, -sV y -A]**

> *"Fijaos en la progresión. El básico nos dice que el puerto 80 está abierto. Con -sV obtenemos Apache httpd 2.4.66. Con -A obtenemos además el sistema operativo estimado. Cada nivel de información permite al atacante afinar más el ataque."*

> *"¿Por qué es tan crítico conocer la versión exacta? Porque puedo ir a bases de datos públicas de vulnerabilidades como CVE — Common Vulnerabilities and Exposures — y buscar qué fallos de seguridad conocidos tiene exactamente esa versión. Paso de 'hay un servidor web' a 'hay un servidor web con estas vulnerabilidades concretas, con sus exploits documentados públicamente'."*

#### Script de vulnerabilidades

> *"Nmap incluye un motor de scripts llamado NSE — Nmap Scripting Engine. El script 'vuln' ejecuta automáticamente comprobaciones de vulnerabilidades conocidas contra los servicios detectados."*

**[Mostrar captura del resultado vuln con /server-status]**

> *"/server-status es una ruta de Apache que muestra estadísticas internas del servidor. Por defecto está accesible para cualquiera. Cuando la confirmamos con curl, devuelve versión exacta, carga de CPU, workers activos, tráfico total y las últimas URLs solicitadas con sus IPs de origen. Es información de inteligencia muy valiosa."*

---

### BLOQUE 3 — Wireshark

#### ¿Qué es Wireshark y qué es un sniffer?

> *"Wireshark es un analizador de protocolos de red, también llamado sniffer o capturador de paquetes. Pone la interfaz de red en modo promiscuo — que significa que captura todos los paquetes que circulan por la red, no solo los dirigidos a esa máquina — y los muestra desglosados campo a campo."*

> *"Una red es como una carretera por la que circulan camiones — los paquetes. Wireshark es un observatorio al borde de la carretera que fotografía cada camión y lee lo que lleva dentro. Si el camión va sin cerrar — HTTP — puedes leer la carga directamente. Si va con candado — HTTPS — solo ves el camión pero no el contenido."*

> *"Los filtros son la parte más potente. Puedes filtrar por protocolo, por IP, por puerto, por método HTTP, por flags TCP... Permiten extraer exactamente lo que necesitas de capturas con miles de paquetes."*

**[Mostrar captura del tráfico POST con credenciales visibles]**

> *"Esto es lo más impactante que podéis mostrar en cualquier presentación de seguridad. Un formulario de login enviado por HTTP. Wireshark lo captura y ahí están, en texto plano: usuario 'admin', contraseña 'SuperSecreto123'. Sin descifrar nada, sin romper nada, simplemente leer."*

> *"Con HTTPS, esos datos viajan cifrados con TLS antes de salir de la máquina. Aunque captures todos los paquetes, solo obtienes datos cifrados ilegibles sin la clave privada del servidor."*

#### Anatomía de un paquete HTTP

> *"Un paquete tiene capas, como una cebolla. La capa más externa es Ethernet — con las MACs de origen y destino. Dentro está IP — con las IPs. Dentro está TCP — con los puertos y el control de flujo. Y dentro está HTTP — con el método, la URL, las cabeceras y los datos. Wireshark desglosa cada capa, lo que lo hace ideal para entender cómo funcionan los protocolos."*

---

### BLOQUE 4 — Apache: configuración y seguridad

#### ¿Qué es Apache y qué es un servidor web?

> *"Apache HTTP Server es el servidor web más utilizado del mundo desde 1996. Un servidor web es un programa que escucha peticiones HTTP en un puerto — normalmente el 80 para HTTP y el 443 para HTTPS — y devuelve el contenido solicitado: páginas HTML, imágenes, archivos."*

> *"El hardening de un servidor es el proceso de endurecer su configuración, eliminando servicios innecesarios y corrigiendo configuraciones inseguras por defecto. Apache tiene varias configuraciones por defecto que hay que ajustar."*

> *"La primera es /server-status, que ya vimos. La segunda es ServerTokens: por defecto Apache incluye su versión exacta en la cabecera HTTP Server de cada respuesta. Con `ServerTokens Prod` solo dice 'Apache', sin versión, dificultando el fingerprinting automatizado. La tercera es Options Indexes: si un directorio no tiene index.html, Apache muestra todos sus archivos. Eso puede exponer backups, archivos de configuración o código fuente."*

---

### BLOQUE 5 — Ataque y defensa

#### ARP Spoofing

> *"ARP, Address Resolution Protocol, resuelve IPs a MACs dentro de una red local. Cuando tu ordenador quiere comunicarse con el router, pregunta por broadcast '¿quién tiene esta IP?' y el router responde con su MAC. El problema es que ARP no tiene autenticación — cualquiera puede responder."*

> *"En un ataque ARP Spoofing, el atacante envía respuestas ARP falsas a la víctima diciéndole que la MAC del router es la suya, y al router diciéndole que la MAC de la víctima es la suya. Ambos actualizan sus tablas ARP con información falsa. A partir de ese momento todo el tráfico entre víctima y router pasa por el atacante — eso es un ataque Man in the Middle. Puede leer el tráfico, modificarlo o bloquearlo antes de reenviarlo."*

#### SSL/TLS

> *"SSL — Secure Sockets Layer — y su sucesor TLS — Transport Layer Security — son los protocolos que cifran las comunicaciones entre cliente y servidor. Cuando ves el candado en el navegador, es TLS funcionando."*

> *"El proceso es el siguiente: el cliente y el servidor negocian qué versión de TLS usar y qué algoritmos de cifrado. El servidor envía su certificado digital — que contiene su clave pública y está firmado por una autoridad de certificación de confianza. El cliente verifica el certificado. Intercambian claves de sesión usando criptografía asimétrica. A partir de ahí toda la comunicación va cifrada con criptografía simétrica, que es mucho más rápida."*

> *"Las versiones antiguas tienen vulnerabilidades conocidas con nombres propios: POODLE en SSL 3.0, BEAST en TLS 1.0. TLS 1.3 es la versión actual recomendada — eliminó los algoritmos débiles, prohíbe la renegociación y es más rápida que sus predecesoras."*

**[Mostrar captura de openssl s_client]**

> *"Verificamos que Apache negocia TLS 1.3 con el comando openssl s_client. La línea `Protocol: TLSv1.3` lo confirma. También aparece `This TLS version forbids renegotiation` — eso es una protección específica contra ataques de downgrade, donde el atacante intenta forzar al servidor a usar una versión más vulnerable."*

#### DMZ y Firewall

> *"Un firewall es un sistema que controla el tráfico de red según un conjunto de reglas: permite o bloquea paquetes en función de su origen, destino, protocolo y puerto. La política más segura es denegar todo por defecto y solo abrir lo estrictamente necesario."*

> *"Una DMZ — Zona Desmilitarizada — es una subred intermedia entre Internet y la red interna corporativa. Los servidores que deben ser accesibles desde fuera — web, correo, DNS — viven en la DMZ. La red interna — bases de datos, servidores de archivos, PCs de empleados — está detrás de un segundo firewall que bloquea todo el tráfico directo desde Internet."*

> *"La ventaja es el principio de mínimo impacto: si un atacante compromete el servidor web en la DMZ, el firewall interno le impide llegar a la base de datos o a los equipos internos. Contiene el daño."*

> *"Un WAF — Web Application Firewall — opera en la capa 7 del modelo OSI, que es la capa de aplicación. No filtra por IP y puerto como un firewall normal, sino que analiza el contenido HTTP para detectar ataques específicos de aplicaciones web como inyección SQL, XSS o intentos de fuerza bruta. Son complementarios: el firewall de red protege el perímetro, el WAF protege la aplicación."*

---

### EXTRA — Metasploit

#### ¿Qué es Metasploit?

> *"Metasploit es el framework de explotación más usado en pentesting. Contiene módulos para cientos de vulnerabilidades conocidas — exploits, payloads, auxiliares — y permite automatizar la fase de explotación de una auditoría."*

> *"Un exploit es el código que aprovecha una vulnerabilidad concreta. Un payload es lo que se ejecuta en el sistema comprometido una vez que el exploit ha funcionado. Meterpreter es el payload más avanzado de Metasploit — da una sesión interactiva con el sistema comprometido, similar a una shell pero con muchas más capacidades."*

**[Mostrar captura de PostgreSQL — CVE-2012-0868]**

> *"Nmap detectó PostgreSQL 8.3 en el puerto 5432. Buscamos ese servicio y esa versión en la base de datos de Metasploit, encontramos el módulo correspondiente al CVE-2012-0868, configuramos la IP objetivo y ejecutamos. El resultado es una sesión Meterpreter — acceso remoto completo a la máquina. La mitigación es simple: actualizar."*

**[Mostrar captura de Apache Jserv — CVE-2000-1247]**

> *"El protocolo AJP — Apache JServ Protocol — es un protocolo binario que usa Tomcat para comunicarse con el servidor web Apache. El puerto 8009 estaba expuesto públicamente, lo que permitió usar el módulo ghostcat para leer archivos internos del servidor incluyendo credenciales."*

**[Mostrar captura de rpcbind — CVE-2017-8779]**

> *"rpcbind es el servicio que mapea los programas RPC a los puertos en los que escuchan. En el puerto 111 encontramos una versión vulnerable a denegación de servicio — un atacante puede saturarlo y tumbar el servicio. Criticidad 7.5."*

---

### CONCLUSIONES — 3 min

> *"Lo que nos llevamos de hoy. Primero: la información es la primera arma. Antes de explotar nada, el atacante pasa tiempo recolectando datos con herramientas completamente pasivas o poco intrusivas — DNS, ping, traceroute, Nmap. Cuanto más información tiene, más preciso y efectivo es el ataque."*

> *"Segundo: HTTP en claro no tiene ningún tipo de privacidad. Cualquiera con acceso a la red puede leer credenciales, sesiones y datos sensibles con herramientas gratuitas. HTTPS con TLS 1.2 o 1.3 es el mínimo aceptable para cualquier cosa que maneje datos de usuario."*

> *"Tercero: las configuraciones por defecto casi siempre tienen algo que corregir. /server-status expuesto, versiones en cabeceras, directorios listables — ninguna de estas cosas es una vulnerabilidad compleja, pero todas facilitan el trabajo del atacante."*

> *"Y cuarto: el ciclo de auditoría siempre es el mismo — reconocer, confirmar, explotar o analizar, parchear y verificar. Si no verificas que el parche funciona, no sabes si has resuelto el problema."*

