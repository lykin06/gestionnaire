miniprojetmd
============

Loïc GAILLARD
Mathieu BOUTELIER


Mini Projet de Mathématiques Discrètes

Polytech Nice Sophia SI3

Ce projet est à réaliser en binôme pendant les semaines 50,51 (2013) et 2 (2014).

Les binômes doivent être constitués de 2 étudiants (!) appartenant au même groupe de TD. Vous ne devez pas refaire un binôme déjà utilisé pour d’autres travaux ou projets réalisés cette année en binôme.

Les noms des fichiers qui vous sont donnés doivent être impérativement respectés.

Pour certaines questions, vous aurez besoin de notions abordées en MD dans les semaines qui précèdent ou qui viennent.

Rendu

·       les binômes doivent être constitués au plus tard mardi 10 décembre (minuit)

·       ce projet doit être rendu au plus tard jeudi 9 janvier 2014 (minuit) sur http://moodle.i3s.unice.fr

·       Rendre les fichiers .java et la javadoc  pour toutes les questions  de programmation et les réponses aux autres questions dans un seul fichier .pdf

·       utilisation du web et échanges entre binômes : il n’est pas interdit d’utiliser le web ni de discuter entre vous de vos solutions, au contraire (et en plus nous n’avons aucun moyen de vous empêcher de le faire J), mais « pomper » un code ou une réponse (surtout auquel on ne comprend rien) est d’une part idiot et surtout est susceptible de conduire à des poursuites devant l’instance disciplinaire de l’université.


Remarques

·       la programmation est à faire en langage Java

·        vous serez notés sur vos réponses aux questions posées, et non sur d’éventuelles sophistications graphiques ou autres (qui par ailleurs peuvent être intéressantes à réaliser).

·        quelle que soit la manière dont le travail a été réparti entre les deux membres du binôme, vous devez pouvoir répondre à toutes les questions posées sur le projet rendu.

Partie 1 : des tours de Hanoï

(source par exemple : http://fr.wikipedia.org/wiki/Tours_de_Hano%C3%AF)
Le jeu des tours de Hanoï est constitué de 3 piquets et d’un certain nombre de disques, qui ont des diamètres 2 à 2 différents. Au départ du jeu, tous les disques sont empilés sur un piquet « de départ » et il s’agit de déplacer tous les disques sur un autre piquet, dit piquet « d’arrivée » et ceci en respectant les règles suivantes :

    on ne peut déplacer qu'un disque à la fois
    on ne peut placer un disque que sur un disque plus grand ou sur le socle des 3 piquets.

Le but est de trouver une (ou la) méthode pour y arriver et ce le plus vite possible, c’est-à-dire en minimisant le nombre de déplacements de disque.

    Programmer l’algorithme récursif optimal, c’est-à-dire tel que tout autre algorithme (récursif ou pas) fait plus de déplacements de disque.

Le résultat de l’exécution la commande java JeuHanoi 3 doit donner un affichage de ce type :

Vous pouvez faire une solution avec interface graphique plus ou moins sophistiquée, il en existe d’ailleurs de nombreuses accessibles sur le web, mais d’une part ça ne doit venir qu’en plus de ce qui est demandé (et pas à la place) et d’autre part si vous utilisez tout ou partie de code trouvé sur le web, vous devez citer vos sources.

    Calculer le nombre un de déplacements de disque fait par cet algorithme où n est le nombre de disques.
    Démontrer que cet algorithme est optimal.
    Démontrer que cet algorithme est l’unique algorithme optimal.
    Montrer que dans la solution optimale, un déplacement sur deux est le déplacement du plus petit disque.
    Montrer que dans la solution optimale, le plus petit disque se déplace toujours dans le même sens (c’est-à-dire que si on appelle A, B et C les 3 piquets, le petit disque fait :

·     soit toujours l’un des 3 déplacements suivants : (A vers B), (B vers C) ou (C vers A)

·     soit toujours l’un des 3 déplacements suivants : (A vers C), (C vers B) ou (B vers A)

    En déduire et programmer un algorithme itératif (très simple) donnant la solution optimale.
    On note les n disques, D(1), D(2), …, D(n), du plus grand disque au plus petit. Pour chacun de ces disques calculer le nombre de déplacements faits dans l’algorithme optimal et retrouver ainsi le nombre total de déplacements de disque.

On ajoute la contrainte suivante pour les déplacements de disque : tout déplacement de disque est l’un des 3 déplacements suivants : (A vers B), (B vers C) ou (C vers A). Autrement dit, les piquets sont considérés non plus comme étant alignés, mais comme étant sur un cercle, et il y a un sens de rotation imposé sur ce cercle pour les déplacements de disque.

    Programmer l’algorithme récursif optimal.
    Calculer le nombre vn de déplacements de disque fait par cet algorithme où n est le nombre de disques.
    Démontrer que cet algorithme est optimal et qu’il est unique

 

 

 

 

Partie 2 : des courbes récursives

 Le but est d'étudier et de dessiner certaines figures récursives. Ces familles sont définies par

    une figure initiale F1
    une règle de transformation qui permet de définit Fk+1 en fonction de Fk

Remarques :

·        les méthodes de dessins de la classe java.awt.Graphics  exigent des coordonnées entières, mais tous les calculs intermédiaires pourront être faits sur les réels. Eventuellement faites une version avec des calculs sur les entiers et une version avec des calculs sur les réels et regardez la différence au niveau du dessin.

·        le point de coordonnée (0,0) est celui qui est en haut et à gauche, et non pas en bas à gauche comme on en a l'habitude.

 

Première famille : des cercles

Figure initiale : F1: un cercle

Règle de transformation : on obtient Fk+1 à partir de Fk en ajoutant à chaque plus petit cercle c de Fk, 2 nouveaux cercles

·       tangents à c

·       de diamètre la moitié du diamètre de c

·       et tels que le centre de c et d’un des nouveau cercle  sont  sur  une verticale ou une horizontale.

Par exemple pour k = 4, on obtient une figure de ce type :

 

1.     Ecrire une méthode récursive pour dessiner  Fk.. Pour cela, créer une classe FkSolution qui étend la classe (abstraite) AbstractSolution , en implémentant la méthode drawSolutionk.

Ecrire une classe de test qui permette de saisir la profondeur k de récursivité et d’afficher le dessin correspondant.

2.     Quelle est la taille maximale de la figure obtenue, de manière plus précise, quelle est la taille du plus petit carré contenant la figure Fk. pour tout entier k , on supposera que le diamètre du disque initial est 1?

3.     Modifier votre code pour que l’intérieur des cercles soit coloré en rose, puis calculer en fonction de k, la taille de la surface colorée .

 

Deuxième famille : encore plus de cercles

    On veut maintenant obtenir des dessins F2k.du type suivant pour k = 4 :

5.     Ecrire une méthode récursive pour dessiner  F2k.. Pour cela, créer une classe F2kSolution qui étend la classe (abstraite) AbstractSolution  en implémentant la méthode drawSolutionk.

Ecrire une classe de test qui permette de saisir la profondeur k de récursivité et d’afficher le dessin correspondant.

Si on colorie l’intérieur des cercles en vert fluo, quelle sera, en fonction de k, la taille de la surface colorée.

Troisième famille : et des carrés

    On veut maintenant obtenir des dessins F3k du type suivant pour k = 3 :

1.     Ecrire une méthode récursive pour dessiner  F3k.. Pour cela, créer une classe F3kSolution qui étend la classe (abstraite) AbstractSolution  en implémentant la méthode drawSolutionk.

Ecrire une classe de test qui permette de saisir la profondeur k de récursivité et d’afficher le dessin correspondant.

 

Quatrième famille : et puis quoi encore

 

2.     Ecrire une méthode récursive pour dessiner  F4k. une famille de dessins construits sur le même « modèle » que les précédents que vous aurez définie vous-même. Par exemple, vous pouvez jouer sur les formes, sur les couleurs du dessin, du coloriage, faire des rotations, dessiner à l’intérieur des dessins déjà faits, etc. Soyez créatifs sans vous limiter à faire du google ….

(Cette question sera notée comme le patinage artistique avec une note technique pour l’intérêt/la difficulté de la méthode récursive utilisée et note artistique pour l’esthétique de la figure.)

 

 

 

 

 

