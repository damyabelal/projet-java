# l2s4-projet-2025



# Equipe

- Damya BELAL
- Laeticia CHALAH
- Fatima ALMOHAMED ALSADOU
- Stella Rose MILLE

# Sujet

[Le sujet 2025](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2025.pdf)

# Livrables

Les paragraphes concernant les livrables doivent être rempli avant la date de rendu du livrable. A chaque fois on décrira l'état du projet par rapport aux objectifs du livrable. Il est attendu un texte de plusieurs lignes qui explique la modélisation choisie, et/ou les algorithmes choisis et/ou les modifications apportées à la modélisation du livrable précédent.

Un lien vers une image de l'UML doit être fourni (une photo d'un diagramme UML fait à la main est suffisant).

## Livrable 1
On a choisie de faire une classe abstraite Tuile car pour les types de tuiles de type mer et terrestre doivent etre de meme type , c'est a dire de type Tuile. De plus ca n'a pas de sens de instancier un objet de type Tuile.

Ensuite on a crée une classe Terrestre et Sea  qui héritent de Tuile car les tuiles peuvent etre soit de type terrestre soit type mer.Les tuiles de type Terrestre n'ont pas les memes capacités et ainsi pas les meme méthodes et attributs que ceux de type mer.

Les tuiles de type Terrestre peuvent de quatre type différents.Chaque type a un constructeur different et peuvent produire des ressources différentes. 
On a donc choisie de modéliser chaque  type de tuiles par une classe qui lui correspond.Une classe foret , paturage , montagne et champs.Ces quatres classes vont hériter de la classe Terrestre et des ses méthodes et attributs car pour chaque tuiles les méthodes sont les memes a part le constructeur.Cela évite la répétition des méthodes.

On a choisie de faire une classe Position car dans la classe Board , on a besoin de faire appelle a des coordonnées d'une case du plateau régulièrement ce qui  peut encombrer le code.Pour éviter cette gene on a décidé de remplacer les tuples par une instance de la classe position.


On a décidé de modéliser le plateau , toutes ses methodes et tout ce qui le concerne dans une class Board. Pour ce qui est de l'algorithme de placement des tuiles on en as beaucoup discuté, notre première approche était de découpé le nombre total de tuiles terrestre sur le plateau (donc 1/3 des tuiles totales) afin de tout de suite les regroupé entre elle. On voulait formé les îles directement, en sachant par exemple qu'il y aurait 4 îles avec l'une de 2 tuiles, l'autres de 6, ect...
Cependant cette structure qui semblait efficace dans un premier temps est vite devenu compliqué en pensant au pseudo code. C'est pour cela que nous avons optées pour une méthode plus simple: 
on divise le nombre totale de tuile terrestre par deux et on les places de manière aléatoire. Ensuite pour s'assurer qu'elles aient toutes un voisins, on parcourt l'ensemble du plateau: 
si une tuile est seule, on lui ajoute un voisin.
De cette manière on respecte le caractère aléatoire mais aussi la consigne comme quoi **au moins** 2/3 des tuiles sont de types mer. Avec notre modélisation, 1/3 des tuiles totales ne sont pas obligatoirement placé (dans le cas où aléatoirement une tuile as été placé voisine d'une autre) permettant aussi de varier l'aspect du plateau.

On a choisie de modéliser les différents types de ressources dans une enum car les ressources peuvent etre uniquement de quatre types prédifinies( wood , sheep, wealth , ore).

On a choisie de faire une enum Directon pour pouvoir parcourir les 4 directions a partir d'une tuile et ainsi éviter les répétitions.








 ![ l'uml complet du premier livrable](/index/UMLlivrable1.png "UML complet pour le premier livrable")

### Atteinte des objectifs
On a réussi a  générer un plateau de facon aléatoire.

### Difficultés restant à résoudre
Il n'ya pas de difficultés restant a résourdre.

## Livrable 2

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 3

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 4

### Atteinte des objectifs

### Difficultés restant à résoudre

# Journal de bord

Le journal de bord doit être rempli à la fin de chaque séance encadrée, et avant de quitter la salle. 

Pour chaque semaine on y trouvera :
- ce qui a été réalisé, les difficultés rencontrées et comment elles ont été surmontées (on attend du contenu, pas uniquement une phrase du type "tous les objectifs ont été atteints")
- la liste des objectifs à réaliser d'ici à la prochaine séance encadrée

## Semaine 1

### Ce qui a été réalisé

- Début de la réfléxion sur la construction du plateau ainsi que la conception des tuiles
- Discussion autour de la mise en place de l'algorithme posant les tuiles aléatoirement
- Modélisation du diagramme UML pour la classe Plateau 

 ![ l'uml de la classe plateau et tuile](/index/uml_w1.png "UML semaine 1")

### Difficultés rencontrées
- La mise en place des tuiles sur le plateau de facon aléatoire 

### Objectifs pour la semaine

- Finalisation de l'algorithme permettant la mise en place des tuiles aléatoirement
- Modélisation du diagramme UML sur les classes Tuiles et Héritage sur les types (forêt , montagne , paturâge, champ)

## Semaine 2

### Ce qui a été réalisé
- Construction d'une énumeration Direction qui représentent les quatres directions possibles pour vérifier l'encadrement des tuiles.
- Creation d'une énumeration Ressource qui représente les 4 types de ressource
- Rajout de classes et amélioration du diagramme UML
- Réalisation du pseudo-code permettant la mise en place de la construction du plateau

 ![ ajout de enum et amélioration de l'héritage](/index/uml_w2.png "UML semaine 2")

  ![pseudo code construction plateau](/index/pseudoCode1.jpeg "pseudo-code semaine 2")


### Difficultés rencontrées
- Débat sur comment modéliser l'héritage des types de tuiles.

### Objectifs pour la semaine

- Création de la classe abstraite Tuile et la classe Sea et la classe Earth qui héritent de la classe Tuile.
- Création des classes Forest, Montain, Field, Pasture qui héritent de la classe Earth.

## Semaine 3

### Ce qui a été réalisé

- Organisation des packages
- Ecriture de la méthode randomTuile() qui permet de générer une Tuile aléatoirement
- Ecriture de la méthode placeHalfEarthTiles() qui permet de placer 1/6 (la moitie de 1/3) des tuiles terrestres
- Résolution des bugs concernant les commandes git (git stash git merge ect..)
- Discussion sur la répartition des taches pour la semaine pour la finalisation du plateau

### Difficultés rencontrées

- Synchronisation de Git
- Git Merge
- Configuration de vs code


### Objectifs pour la semaine
- Finalisation de la méthode placeNeighboorEarthTiles() qui permet de placer les 1/6 des tuiles terrestres restants en vérifiant le voisinage (Sera fait par : Mille Stella Rose)
- Création de la méthode creaeBoard() qui permet la creation du Plateau (Sera fait par : Fatima ALMOHAMED Alsadou)
- Création de la méthode display() qui permet l'affichage du plateau (Sera fait par : Laeticia Chalah)
- Création du fichier test BoardTest pour le fichier Board.java (Sera fait par : Belal Damya)

## Semaine 4

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 5

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 6

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 7

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 8

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 9

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 10

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 11

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 12

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour finaliser le projet