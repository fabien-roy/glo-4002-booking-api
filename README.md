# Projet 2019 - Équipe 5 : Random Pogo

## MEP 2.0

Cette application à été développée par Random Pogo. Elle permet aux festivaliers de réserver leur place pour GLOW-4002. Après la réservation de billet(s), les transport et les bombonne d'oxygène nécessaire pour chaque festivalier sont gérer par l'application. Les organisateurs peuvent également accéder à une liste d'artistes qu'ils peuvent réserver pour faire partie de la programmation. Ils peuvent également planifier des activités, et recevront l'oxygène en conséquence. Les organisateurs ont aussi accès aux statistiques financières du festival.

## Known technical debts

See `/docs/KnownTechnicalDebts.md` to know what is not perfect in this app.

## Appels possibles

Voici la liste de tous les appels possible dans l'application. Les appels sont classés en fonction des demandes du client. L'addresse actuelle de l'application est "http://localhost:8080"
 
### POST /orders


#### Requête

Permet de créer une nouvelle commande. Exemple : 

```
{
  "orderDate": "2050-05-21T15:23:20.142Z",
  "vendorCode": "TEAM",
  "passes": {
              "passCategory":"supergiant",
              "passOption": "package"
            }
}
```
Permet de passer une commande au serveur pour un nombre illimité de passe quotidienne ou un seul package.
*Prendre note que si l'option "package" est fournie, il ne faut pas fournir "eventDates"*

#### Réponse

HTTP 201 CREATED

Une réponse contenant toutes les informations sur la commande effectuée est retournée par le serveur. Dans la dernière version du programme, l'orderNumber est maintenant préfixé du vendorCode. Exemple de réponse :

```
{
   "orderNumber": TEAM-1,
   "orderPrice": 500000.00,
   "orderDate": "2050-05-21T15:23:20.142Z",
   "vendorCode": "TEAM",
   "passes": [
       {
           "passNumber": 2,
           "passCategory": "supergiant",
           "passOption": "package",
           "eventDate": null
       }
   ]
 }
```

HTTP 404 NOT FOUND

Si la value d’achat est en dehors de la période d’achat (1er janvier 2050 au 16 juillet 2050 inclusivement).
```
   {
     "error": "ORDER_NOT_FOUND",
     "description" : "order value should be between January 1 2050 and July 16 2050"
   } 
```

HTTP 400 Bad Request

Si une value (dans eventDates) pour laquelle on veut acheter une passe est en dehors de la durée du festival (17 juillet 2050 au 24 juillet 2050 inclusivement).

   ```
   {
     "error": "INVALID_PASS_DATE",
     "description" : "event value should be between July 17 2050 and July 24 2050"
   }  
```
HTTP 400 Bad request

Pour les autres erreurs (ex. passCategory qui est invalide, si le champ eventDates est présent alors qu’un package est acheté, etc.)
```
{
  "error": "INVALID_FORMAT",
  "description": "invalid format"
} 
```
### GET /orders/{order-id}

Retourne un résumé de la commande portant l'id indiqué. Exemple : 

#### Réponses
```
{
    "orderPrice": 500000.00,
    "passes": [
                  {
                    "passNumber": 345678912,
                    "passCategory": "supergiant",
                    "passOption": "package",
                  }
              ]
}
```

HTTP 404 Not found

Si la commande n’existe pas
```
{
  "error": "ORDER NOT FOUND",
  "description": "order with number XX not found":
} 
```

### GET /shuttle-manifests?date="value"

Retourne les départs et arrivées, ainsi que leur nom de navette et les passagers présents. Si une date est inscrite en paramètre, retourne les voyages de la date. Sinon, retourne tous les voyages pour la durée du festival. Exemple : 

(value est optionnel)
	
```
{
 "departures": [  
                    {
                       "value": "2050-07-19",
                       "shuttleName": "ET Spaceship",
                       "passengers": [123456789]
                    }, 
                    {
                       "value": "2050-07-19",
                       "shuttleName": "SpaceX",
                       "passengers": [234567891]
                    }
                ],
 "arrivals": [
                   {
                     "value": "2050-07-19",
                     "shuttleName": "ET Spaceship",
                     "passengers": [123456789]
                   }, 
                   {
                     "value": "2050-07-19",
                     "shuttleName": "SpaceX",
                     "passengers": [234567891]
                   }
              ]
}
```

### GET /report/o2

Retourne un rapport lié à l'inventaire et l'utilisation de l'oxygène. Exemple : 


```
{ 
  "inventory": [
                    {
                        "gradeTankOxygen": "B",
                        "quantity": 3
                    }, 
                    {
                        "gradeTankOxygen": "A",
                        "quantity": 5
                    }
               ],
  "oxygenHistory" : [
                 {
                     "value": "2050-03-10",
                     "qtyOxygenTankBought": 0,
                     "qtyWaterUsed": 0,
                     "qtyCandlesUsed": 15,
                     "qtyOxygenTankMade": 0
                 },
                 {
                     "value": "2050-03-30",
                     "qtyOxygenTankBought": 0,
                     "qtyWaterUsed": 0,
                     "qtyCandlesUsed": 0,
                     "qtyOxygenTankMade": 5
                 },
                 {
                     "value": "2050-04-25",
                     "qtyOxygenTankBought": 0,
                     "qtyWaterUsed": 8,
                     "qtyCandlesUsed": 0,
                     "qtyOxygenTankMade": 0
                 },
                 {
                     "value": "2050-05-05",
                     "qtyOxygenTankBought": 0,
                     "qtyWaterUsed": 0,
                     "qtyCandlesUsed": 0,
                     "qtyOxygenTankMade": 3
                 },
               ]
} 
```

### GET /program/artists?orderBy={ordering}

Ordering peut être soit "lowCosts" pour classer en ordre croissant de coût, "mostPopular" pour class par côte de popularité (décroissant) ou rien, si le champ est vide, ils seront retournés selon l'ordre qu'ils ont été ajoutés. Exemple de réponse : 

```
{
        "artists": [
            "Cyndi Dauppler",
            "Lady Gamma",
            "Sun 41",
            "Bruno Mars",
            "Suns N’ Roses",
            "XRay Charles",
            "Megadearth",
            "30 Seconds to Mars",
            "Mumford and Suns",
            "Black Earth Peas",
            "Simple Planet",
            "Kelvin Harris",
            "Freddie Mercury",
            "Eclipse Presley",
            "Kid Rocket",
            "David Glowie",
            "Novana",
            "Rolling Stars",
            "Coldray"
        ]
    }
```


### POST /program

Permet de créer la programmation du festival. Exemple : 

```
{
   "program": [
            {
               "eventDate": "2050-07-17",
               "am": "yoga",
               "pm": "Kid Rocket"
            },
            {
               "eventDate": "2050-07-18",
               "am": "yoga",
               "pm": "Freddie Mercury"
            },
            {
               "eventDate": "2050-07-19",
               "am": "cardio",
               "pm": "Kelvin Harris"
            },
            {
               "eventDate": "2050-07-20",
               "am": "cardio",
               "pm": "Lady Gamma"
            },
            {
               "eventDate": "2050-07-21",
               "am": "yoga",
               "pm": "30 Seconds to Mars"
            },
            {
               "eventDate": "2050-07-22",
               "am": "yoga",
               "pm": "Coldray"
            },
            {
               "eventDate": "2050-07-23",
               "am": "cardio",
               "pm": "Suns N’ Roses"
            },
            {
               "eventDate": "2050-07-24",
               "am": "yoga",
               "pm": "Eclipse Presley"
            }
       ]
}
```


Un program valide ne doit pas contenir deux fois la même date, ne peut pas avoir un artiste en am ou une activité en pm et ne doit pas avoir deux fois le même artiste. En cas de program invalide, l'organisateur recevra l'erreur suivante : 

```
{
  "error": "INVALID_PROGRAM",
  "description" : "the program is invalid"
} 
```

### GET /report/profits

Retourne les profits / pertes du festival. Exemple : 

```
{
  "in": 100.0
  "out": 50.0
  "profit": 50.0, => profit
}
```

## Équipe

- Brian Cloutier
- Fabien Roy
- Guillaume Bégin
- Marie-Claude Gauthier
