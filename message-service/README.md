# Service de message

## Introduction

Ce service permet de gérer les messages.

## Lancement
Dans le répertoire du projet, lancer:
```
mvn spring-boot:run
```

Le web-service écoute sur le port 9393.

## Exemples de requêtes

### Enregistrer un message
```
POST /messages/message HTTP/1.1
Host: localhost:9393
Content-type : application/json
{"content" : "c'est un projet", "auteur" : "salsabile"}
```

```
Content-Length → 0
Date → Sun, 29 Nov 2015 16:41:41 GMT
Server → Apache-Coyote/1.1
[
    {
        "login": "cbellart",
        "email": "cbellart@gmail.com",
        "facebookid": "facebookClement",
        "twitterid" : "twitterClement",
        "competence" : JEE",
        "projet" : "Reseau social"
    }
]
```

### Récupérer tous les messages
```
GET /messages HTTP/1.1
Host: localhost:9393
```

```
Content-Length → 0
Date → Sun, 29 Nov 2015 16:42:41 GMT
Server → Apache-Coyote/1.1
[
    {
        "content": "Que la #force soit avec toi !",
        "login": "Yoda",
    },
    {
        "content": "Je suis un futur seigneur Sith..",
        "login": "DarkVador",
    }
]

```

### Récupérer tous les messages d'un utilisateur
```
GET /messages/Yoda HTTP/1.1
Host: localhost:9393
```

```
Content-Length → 0
Date → Sun, 29 Nov 2015 16:42:41 GMT
Server → Apache-Coyote/1.1
[
    {
        "content": "Que la #force soit avec toi !",
        "login": "Yoda",
    }
]

```
### Listing des messages

content | login
---------|--------
Que la #force soit avec toi !|Yoda
Je suis un futur seigneur Sith..|DarkVador
