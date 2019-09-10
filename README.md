# ReadMe à supprimer avant la remise

Ce readme sert à vous expliquer la structure de base de votre projet. Vous devez le remplacer par le vôtre **avant** la remise!

## Structure des modules

Il existe trois [modules maven](https://maven.apache.org/guides/mini/guide-multiple-modules.html) dans le projet : 

* external-service-api : ce projet offre un API codé avec Spring Boot. Son utilité sera révélé au cours des _user stories_ de la sesion.
* booking-api : le projet que vous développerez. Ici, vous pouvez modifier tout ce qui vous plait. Le code présent n'est pas nécessairement bon/bien placé/selon les normes de votes équipe. À vous de voir!
* application : permet de démarrer les 2 API simultanément. Vous pouvez le modifier également.
 
## Notes sur la structure courante
 
Vous pouvez modifier tous les fichiers du projet, autre que ceux dans `external-service-api`. Considérez qu'il est hébergé ailleurs; vous ne pouvez en aucun cas le modifier. Lors de la correction, nous écraserons toutes vos modifications à ce projet.

La structure dans `booking-api` est à titre d'exemple, vous êtes libre de faire ce que vous voulez. Vous pouvez modifier `application` également, mais vous ne devriez pas avoir besoin. Vous ne pouvez cependant pas renommer le module `application` ni `external-service-api`. Vous pouvez renommer `booking-api` si vous le souhaitez.

:warning: `external-service-api` **n'est volontairement pas une bonne référence**. Ce type de projet a une utilité (à voir plus tard dans la session), mais ce n'est pas ce dont vous avez besoin dans votre projet! N'essayez pas de le copier.

## Notes sur les technologies utilisées

* [Jetty](https://www.eclipse.org/jetty/) est un Servlet Container. Il accepte les requêtes du web et sait comment répondre
* [Jersey](https://jersey.github.io/) est un Servlet fait pour le développement d'API REST. Il sait comment faire la correspondance entre un API rest et vos méthodes selon la norme JAX-RS.
* [Jackson](https://www.baeldung.com/jackson) sert à sérialiser/désérialiser des objets JSON en POJO

Si vous n'êtes pas familiers avec ces concepts, il est fortement suggéré d'utiliser google/stackoverflow pour les apprendre rapidement. Ceux-ci peuvent être matière à examen.

:warning: Jetty, jersey et jackson sont utilisés dans le projet de base. Vous pouvez choisir d'autres technologies, mais vous devrez analyser les avantages et inconvéniants d'abord. Si vous voulez le faire, venez nous en parler. Entre autre, spring et spring boot sont reconnus pour causer des maux de têtes vers la fin de la session. L'an passé, certains n'ont même pas pu faire les remises 3 et 4 à cause de ce choix.

## Intégration travis

L'intégration avec travis vous est fourni. Si travis ne fonctionne pas, n'hésitez pas à nous contacter.

## Démarrer le projet

Vous pouvez démarrer soit un des deux serveurs (OrganisationServer ou BookingServer) individuellement ou simultanément.

Il y a trois classes que vous pouvez exécuter dans intelliJ ou Eclipse pour cela : `OrganisationServer`, `BookingServer` ou `ApplicationServer`. Aucun des `main()` ne demande d'argument.

Vous pouvez également rouler le serveur via maven (**c'est ce qui sera utilisé pour corriger votre projet**) : 

```bash
mvn clean install
mvn exec:java -pl application
```

:warning: Ces commandes seront utilisées pour la correction automatique du projet. Elles doivent fonctionner!

Une resource "heartbeat" vous est fournis pour tester que le service démarre bien. Allez à l'URL `http://localhost:8181/heartbeat?token=unique_token` pour le valider. Vous n'êtes pas obligés de garder le heartbeat (mais si vous le supprimez, ne laissez pas de code mort!)

## Prochaines étapes

Nous regarderons le projet rapidement mercredi. La cliente sera également présente pour vous le présenter davantage. D'ici là, assurez-vous de pouvoir exécuter le code sur votre machine.

## Besoin d'aide?

Vous pouvez nous écrire à aide@qualitelogicielle.ca si quelque chose ne fonctionne pas!