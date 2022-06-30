# Solution en Quarkus

[Quarkus](https://quarkus.io/) est un framework Java classique à base d'annotations. En lisant rapidement son site web, ça parle de "Kubernetes native" et GraalVM, je me suis dit que ça méritait d'être creusé.

## Implémentation

Rien de révolutionnaire de ce côté, ce sont les annotations standards JAX-RS soutenues par la librairie [Resteasy](https://resteasy.dev/).
Et la validation se fait avec des annotations Hibernate.

(A noter que l'annotation `@NotEmpty` sur le query param donne bien une 400, mais avec un body HTML au lieu d'un retour JSON décrivant l'erreur.)

Là où Quarkus présente une différence pour le développeur, c'est sur son aspect "Live Reload".

## Live Reload

Avec Quarkus, vous démarrez l'environnement de développement avec : `./mvnw quarkus:dev`. A partir de là, dès que vous faites une modification, celle-ci est disponible sans recompilation du projet ou redémarrage du serveur.

Quand on compare à d'autres solutions qui nécessitent de stopper avec Ctrl+C, puis de relancer la commande "Jetty", c'est assez impressionant.

## Déploiement

Normalement, dans ce repository je ne détaille pas la partie déploiement/ops. Mais jusque là, j'avais eu affaire soit à des choses évidentes (Node.js, Gradle -> jar autosuffisant), soit du bizarre (Racket).
Ici, je vois Kubernetes et GraalVM, je me dis que c'est bien d'avoir un environnement de développement local super efficace, mais si on ne peut pas le déployer, ça ne sert à rien.

Si on veut profiter à fond du framework, il faut passer par de la compilation native avec GraalVM et tout. Pour ça, le site de Quarkus dispose d'articles pour les Cloud Providers classiques.
A priori, on peut même déployer chez les français de [CleverCloud](https://www.clever-cloud.com/blog/features/2019/04/15/how-to-use-quarkus/) !

Si on ne veut pas s'embêter, Quarkus propose de générer un Uberjar Java classique.
```
./mvnw clean package -Dquarkus.package.type=uber-jar 
```