# Nightcode 2020

Ce repository se propose de présenter une solution à une partie des problèmes posés pendant la Nightcode 2020.

## Qu'est-ce que la Nightcode ?

C'est une "Nuit de l'Info" à la mode Iteracode.

Depuis 4 ans, nous organisons cet événement avec l'IUT Informatique d'Amiens. On enferme les étudiants (volontaires) pendant une nuit (de 18h à 6h) et on les fait travailler.

Le travail est proposé sous une forme itérative. Si les derniers exercices proposent quelques défis algorithmiques, les premiers servent à poser les bases.

Ainsi, on vérifie que les équipes savent répondre, gérer le code statut, manipuler du JSON.

## Pourquoi ce repo ?

L'objectif n'est pas de fournir les solutions des problèmes de cette année, mais de creuser un point qui semble parfois problématique.

On pourrait diviser un projet Nightcode en 4 domaines :

- Front
- Ops
- API (création de routes, communication JSON)
- Algorithmie

La partie API est vraiment ce qui bloque le plus d'équipes. Trop d'implémentations "from scratch", trop de .htaccess à debugger,... Nous avons donc eu l'idée de fournir des exemples dans différents langages et frameworks.

Pour démontrer les possibilités des technos choisies, nous nous contenterons de résoudre les trois premiers exercices proposés aux étudiants.

N.B. Le choix des technologies se fait uniquement au bon vouloir des développeurs d'Iteracode et ne doit pas être perçu comme un conseil technique pour les prochaines Nightcode.

## De quoi parle-t-on exactement ?

- Une route "/" qui répond 200 et "Nightcode" dans son body (sans restriction sur son format).
- Une route "/api/greetings" qui appelée en GET sans paramètre retourne un body JSON `{ "message" : "hello, world" }`
- Appelée avec un paramètre `name=toto` retournera `{ "message" : "hello, toto" }`
- Appelée en POST avec un body `{ "name" : "toto" }` retournera `{ "message" : "hello, toto" }`
- Appelée en GET avec un paramètre name vide, elle retournera une 400.
- Appelée en POST avec un body (ou un attribut `name`) vide, elle retournera une 400.

## Les implémentations

L'implémentation de trois routes et de deux validations ne sauraient représenter un véritable test d'une librairie. Mais cela ne nous empêche pas de vous donner quelques retours d'expériences sur chacune d'elle.

### Node JS - Hapi.js

L'intégration de JOI pour la validation du body et de la query string est intéressante, en terme de lisibilité et réutilisation.

Pour le reste, cette implémentation reste assez classique pour du NodeJS.

### JVM - Groovy - Micronaut

Une implémentation à base d'annotation, assez classique dans le monde Java. Groovy permet quelques raccourcis par rapport à une implémentation Java.

### JVM - Groovy - Ratpack

La plus grosse déception de l'exercice.

L'exemple simple que nous avions trouvé ne fonctionne plus à cause d'une dépendance transitive problématique dans la dernière version.

Passer l'implémentation de la route "/", le reste c'est montré assez douloureux, avec peu de documentation disponible.

A noter que la duplication de code pour la validation est peut-être dûe à un manque de connaissance du langage.

### JVM - Kotlin - HTTP4K

La bonne surprise.

A la relecture, ce n'est pas l'implémentation la plus concise. Pourtant, la notion de Lenses et l'aspect "fonction" de tous les éléments ont été très agréables à découvrir et à utiliser.

## Vous voulez tester en local ?

Nous vous proposons de tester vous-même ces quelques défis de la Nightcode.

Vous allez pouvoir les lancer en local sur votre machine.

### Exécution

```cmd
java -jar dist/nightcode-demo.jar http://localhost:8080
```

(Si vous ne précisez pas d'url, nous utiliserons http://localhost:3000.)

### Affichage

Les résultats apparaissent dans la console, avec les détails de vos erreurs.

(Les tests sont relancés toutes les minutes.)

### Arrêt

`Ctrl+C` dans votre console
