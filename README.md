# Projet 2019 - Équipe 5 : Random Pogo

**README à mettre à jour pour la MEP 2.0**

Cette application à été développer par Random Pogo. Elle permet aux festivalier de réserver leur place pour GLOW-4002. Après la réservation de billet(s), les transport et les bombonne d'oxygène nécessaire pour chaque festivalier sont gérer par l'application.

## Appels possibles

Voici la liste de tout les appels possible dans l'application. Les appels sont classé par user story.
 
### POST /orders

#### Requête

```
{
   "orderDate": "2050-05-21T15:23:20.142Z"::string,
   "vendorCode": "TEAM"::string,
   "passes": {
               "passCategory": "supernova" || "supergiant" || "nebula" ::string,
               "passOption": "package" || "singlePass" ::string,
               "eventDates": []::string[] 
             }
}
```
Permet de passer une commande au serveur pour un nombre illimité de passe quotidienne ou un seul package.
*Prendre note que si l'option "package" est fournie, il ne faut pas fournir "eventDates"*

#### Réponse

HTTP 201 CREATED

Une réponse contenant toutes les informations sur la commande effectuée est retournée par le serveur. Celle-ci prend cette forme par exemple :

```
{
   "orderNumber": 1,
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

Si la date d’achat est en dehors de la période d’achat (1er janvier 2050 au 16 juillet 2050 inclusivement).
```
   {
     "error": "ORDER_NOT_FOUND"::string,
     "description" : "order date should be between January 1 2050 and July 16 2050"::string
   } 
```

HTTP 400 Bad Request

Si une date (dans eventDates) pour laquelle on veut acheter une passe est en dehors de la durée du festival (17 juillet 2050 au 24 juillet 2050 inclusivement).

   ```
   {
     "error": "INVALID_PASS_DATE"::string,
     "description" : "event date should be between July 17 2050 and July 24 2050"::string
   }  
```
HTTP 400 Bad request

Pour les autres erreurs (ex. passCategory qui est invalide, si le champ eventDates est présent alors qu’un package est acheté, etc.)
```
{
  "error": "INVALID_FORMAT"::string,
  "description": "invalid format"::string
} 
```
### GET /orders/{order-id}

#### Réponses
```
{
    "orderPrice": 0.00::float,
    "passes": [
                  {
                    "passNumber": 0::long,
                    "passCategory": "supernova" || "supergiant" || "nebula" ::string,
                    "passOption": "package" || "singlePass" ::string,
                    "eventDate": "2017-07-01" ::string
                  }, ...
              ]
}
```

HTTP 404 Not found

Si la commande n’existe pas
```
{
  "error": "ORDER NOT FOUND"::string,
  "description": "order with number XX not found"::string
} 
```

### GET /shuttle-manifests?date="date"

(date est optionnel)
	
```
{
 "departures": [  
                    {
                       "date": "2050-07-19",
                       "shuttleName": "ET Spaceship",
                       "passengers": [123456789]
                    }, 
                    {
                       "date": "2050-07-19",
                       "shuttleName": "SpaceX",
                       "passengers": [234567891]
                    }
                ],
 "arrivals": [
                   {
                     "date": "2050-07-19",
                     "shuttleName": "ET Spaceship",
                     "passengers": [123456789]
                   }, 
                   {
                     "date": "2050-07-19",
                     "shuttleName": "SpaceX",
                     "passengers": [234567891]
                   }
              ]
}
```

### GET /report/o2

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
  "history" : [
                 {
                     "date": "2050-03-10",
                     "qtyOxygenTankBought": 0,
                     "qtyWaterUsed": 0,
                     "qtyCandlesUsed": 15,
                     "qtyOxygenTankMade": 0
                 },
                 {
                     "date": "2050-03-30",
                     "qtyOxygenTankBought": 0,
                     "qtyWaterUsed": 0,
                     "qtyCandlesUsed": 0,
                     "qtyOxygenTankMade": 5
                 },
                 {
                     "date": "2050-04-25",
                     "qtyOxygenTankBought": 0,
                     "qtyWaterUsed": 8,
                     "qtyCandlesUsed": 0,
                     "qtyOxygenTankMade": 0
                 },
                 {
                     "date": "2050-05-05",
                     "qtyOxygenTankBought": 0,
                     "qtyWaterUsed": 0,
                     "qtyCandlesUsed": 0,
                     "qtyOxygenTankMade": 3
                 },
               ]
} 
```

## Équipe

- Alexandre Boily
- Brian Cloutier
- Fabien Roy
- Guillaume Bégin
- Louis-Philippe Paquet
- Marie-Claude Gauthier
