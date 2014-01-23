gestionnaire
============

Projet Java de fin de semestre - Objetif : améliorer un logiciel de gestion d'emprunt


Liens
======
- sujet mini-projet : http://moodle.i3s.unice.fr/mod/assign/view.php?id=975
- sujet projet : http://moodle.i3s.unice.fr/mod/assign/view.php?id=1252
- rapport : https://www.writelatex.com/665920vsfddt#/1405529/


TODO
====
-  Gestion des statistiques
-  gerer : Les étudiant peuvent emprunter le matériel sur une période courte.
-  gerer (pourcentage sur la durée max) : Les durées d'emprunt peuvent varier selon le type de matériel et le nombre d'exemplaire disponible.
-  autorisation d'emprunt : Un emprunt est acceptable si le matériel est disponible pendant la durée proposée et si il respecte les durées fixées.
-  changer la valeur du boolean dans la classe Reservation : Tout emprunt doit être accepté par un gestionnaire pour devenir effectif.
-  Les gestionnaires du	stock	décident de	l'achat	de nouveau matériel, de	l'envoi	en réparation	de certains	matériels, de	l'autorisation	de	l'emprunt	du matériel.
-  Afficher les reservations en cours après l'affichage du matériel
-  Revoir le code actuel : getters / setters / refactorisation / etc...


DONE
====
-   Les etudients ne peuvent pas faire de réservation plus d'une semaine à l'avance.
  


Man GitHub
==========

initialisation :
-  ouvrez le terminal
-  (si le logiciel n'est pas installé) tapez la commande "gitg" et installez le logiciel requis.
-  déplcez-vous dans votre workspace Java
-  tapez la ligne suivante : git clone https://github.com/Lykin06/gestionnaire.git
-  sous eclipse créez un nouveau projet et donnez-lui le nom "gestionnaire"


commit :
-  sauvegardez votre travail dans un dossier temporaire en cas de problèmes
-  vérifiez qu'ancun nouveau commit n'a été fait entre temps
-  si c'est le cas : contactez celui qui a fait des changement avant de poursuivre
-  tapez la commande "gitg" et vérifiez votre commit dans l'onglet "commit"
-  tapez la commande "git push" et entrez vos identifiants.
-  vérifiez sur le site si le commit a marché


