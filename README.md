gestionnaire
============

Projet Java de fin de semestre - Objetif : ameliorer un logiciel de gestion d'emprunt


Liens
======
- sujet mini-projet : http://moodle.i3s.unice.fr/mod/assign/view.php?id=975
- sujet projet : http://moodle.i3s.unice.fr/mod/assign/view.php?id=1252
- rapport : https://www.writelatex.com/665920vsfddt#/1405529/


TODO
====
-  Gestion des statistiques (Khady)
-  gerer (pourcentage sur la duree max) : Les durees d'emprunt peuvent varier selon le type de materiel et le nombre d'exemplaire disponible.
-  autorisation d'emprunt : Un emprunt est acceptable si le materiel est disponible pendant la duree proposee et si il respecte les durees fixees. (Aurelien)
-  changer la valeur du boolean dans la classe Reservation : Tout emprunt doit etre accepte par un gestionnaire pour devenir effectif. (Aurelien)
-  Les gestionnaires du	stock decident de	l'achat	de nouveau materiel, de	l'envoi	en reparation de certains materiels, de l'autorisation de l'emprunt du materiel.
-  Afficher les reservations en cours apres l'affichage du materiel. (Aurelien)
-  Revoir le code actuel : getters / setters / refactorisation / etc... (Tout le monde)


DONE
====
-   Les etudients ne peuvent pas faire de reservation plus d'une semaine a l'avance. (Mathieu)
-   gerer : Les etudiant peuvent emprunter le materiel sur une periode courte (divisee par 2). (Mathieu)
-	Les emprunts sont acceptable par le gestionnaire. (Aurelien)
-	Afficher les reservations en cours apres l'affichage du materiel. (Aurelien)


Man GitHub
==========

initialisation :
-  ouvrez le terminal
-  (si le logiciel n'est pas installe) tapez la commande "gitg" et installez le logiciel requis.
-  deplcez-vous dans votre workspace Java
-  tapez la ligne suivante : git clone https://github.com/Lykin06/gestionnaire.git
-  sous eclipse créez un nouveau projet et donnez-lui le nom "gestionnaire"


commit :
-  sauvegardez votre travail dans un dossier temporaire en cas de problèmes
-  verifiez qu'ancun nouveau commit n'a ete fait entre temps
-  si c'est le cas : contactez celui qui a fait des changement avant de poursuivre
-  tapez la commande "gitg" et verifiez votre commit dans l'onglet "commit"
-  tapez la commande "git push" et entrez vos identifiants.
-  verifiez sur le site si le commit a marche


