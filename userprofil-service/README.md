# Service de profil utilisateur

## Introduction

Ce service permet de gérer le profil d'un utilisateur.

## Lancement
Dans le répertoire du projet, lancer:
```
mvn spring-boot:run
```

Le web-service écoute sur le port 9292.

## Exemples de requêtes

#### Récupérer les informations d'un profil
```
GET /userprofil/cbellart HTTP/1.1
Host: localhost:9292
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

#### Modification d'un profil
```
POST /update/jgamba/jgamba@gmail.com/facebook/twitter/linkedin/competence/projet HTTP/1.1
Host: localhost:9292
```

```
Content-Length → 0
Date → Sun, 29 Nov 2015 16:42:41 GMT
Server → Apache-Coyote/1.1
```

```
### Listing des utilisateurs

login | mail | facebook | twitter | linkedin | competence | projet
---------|--------|-----|-------------
a|email@email.fr|facebook|twitter|linkedinid|Aucune|Aucun
jgamba|jgamba@email.fr|jgambaF|jgambaT|jgambaL|JEE|Réseau social
cbellart|cbellart@email.fr|cbellartF|||JEE|Réseau social
shakimi|shakimi@email.fr|shakimiF"|shakimiT||JEE|Réseau social
Yoda|yoda_jedi@force.inter|yodaF|yodaT||Force|Meilleur Jedi
DarkVador|anakin_jedisith@forceobscur.inter|anakinF|anakinTT||Force obscur|Enfant prodige
