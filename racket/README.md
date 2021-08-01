# Racket


## Utilisation

```sh
racket src/nightcode.rkt
```

## Implémentation

Le point de départ de l'implémentation provient de cet [article](https://lisp.sh/crud-web-api-in-racket/).

En itérant à partir de là, j'ai pu coder sans trop de difficultés, les 2 routes de l'exercice.

### Contracts

Racket dispose d'un système de validation appelé [Contracts](https://docs.racket-lang.org/guide/contracts.html).

Je me suis penché un peu dessus pour générer mes Bad Requests, mais sans creuser le sujet, je ne suis pas parvenu à ce que je voulais. Un simple `if (not-empty-string? name)` étant suffisant pour notre cas, je n'ai pas cherché plus loin.

### REPL Driven Development

Comme en Clojure, j'ai pu brancher un REPL sur mon éditeur de texte et ainsi expérimenter certaines fonctions sans relancer le serveur. Toutefois, contrairement à Clojure, je n'ai pas trouvé comment modifier le serveur sans le relancer en modifiant les fonctions à la volée.

Encore une fois, je n'ai pas consacré beaucoup de temps à cet exercice, cela est probablement faisable. Pour ceux que le sujet intéresse je vous recommande la présentation de Sean Corfield (en Clojure) [ici](https://www.youtube.com/watch?v=gIoadGfm5T8&ab_channel=LondonClojurians) ou [là](https://youtu.be/skEXGSp10Xs).
