# Serveur de stockage de fichiers

## Introduction

Ce serveur permet de stocker des fichiers.

## Lancement
Dans le répertoire du projet, lancer:
```
mvn spring-boot:run
```

Le web-service écoute sur le port 9191.

## Exemples de requêtes

### Envoi d'un fichier

```
POST /documents/fc71a181 HTTP/1.1
Host: localhost:9191
Cache-Control: no-cache

----WebKitFormBoundaryE19zNvXGzXaLvS5C
Content-Disposition: form-data; name="file"; filename="pom.xml"
Content-Type: text/xml

----WebKitFormBoundaryE19zNvXGzXaLvS5C
```

```
200 OK
Content-Length → 0
Date → Mon, 30 Nov 2015 10:49:15 GMT
Server → Apache-Coyote/1.1
```

### Téléchargement d'un fichier
```
GET /documents/fc71a181 HTTP/1.1
Host: localhost:9191
Cache-Control: no-cache

```

```
200 OK
Content-Disposition → attachment;filename=fc71a181
Content-Length → 2412
Content-Type → application/json;charset=UTF-8
Date → Mon, 30 Nov 2015 10:50:46 GMT
Server → Apache-Coyote/1.1
```

### Suppression d'un fichier
```
DELETE /documents/fc71a181 HTTP/1.1
Host: localhost:9191
Cache-Control: no-cache
```

```
200 OK
Content-Length → 0
Date → Mon, 30 Nov 2015 10:51:54 GMT
Server → Apache-Coyote/1.1
```