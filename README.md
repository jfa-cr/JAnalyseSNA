# JAnalyseSNA
Analyse un fichier snapshot (extension .sna) qui est l'image de la RAM d'un émulateur Amstrad CPC.

Cette analyse liste les variables d'un programme BASIC Locomotive en indiquant le type et la valeur au moment où le dump a été pris.
Cet utilitaire a pu être crée grâce à ces documents :
+ Article de MicroStrad 1986  https://www.cpc-power.com/index.php?page=detail&onglet=notices&num=14104, pour la logique générale
+ Livre "MA la bible du CPC6128", Paragraphe "3.3.3 Le pointeur de variable '@'" pages 300 à 302
  https://www.cpc-power.com/cpcarchives/download/Livres/MA%20-%20le%20langage%20machine%20%5BFR%5D%20%281987%29.zip, pour la logique de la valeur d'un réel

Pour le lancer il faut aller dans la classe principale /JAnalyseSNA/src/org/JAnalyseSNA/JAnalyseSNA.java, soit :
+ Vous testez l'utilitaire avec un des fichiers se trouvant dans /JAnalyseSNA/src/Test/
+ Vous testez l'utilitaire en ligne de commande en mettant en commentaire les lignes 24 à 27 pour que la variable nf soit à null

Prochaines étapes (selon l'ordre de priorité) :
+ Mettre en place des tests unitaires automatisés sur le code existant
+ Ajouter la logique pour lister les tableaux (instruction DIM), leurs définitions et leurs contenus
+ Vérifier dans le header du fichier snapshot le type d'Amastrad CPC émulé (les adresses où l'on va chercher les informations au début changent selon le type)
+ Permettre la comparaison entre deux snapshots
+ Extraire et afficher toutes les informations présentes dans le header 

Je tiens à remercier Kukulclan pour gérer le site https://www.cpc-power.com qui m'a permis de faire revivre ma première passion informatique.
Pour la prise des fichiers snapshot l'émulateur CaPriCe Forver https://www.cpc-power.com/cpcarchives/index.php?page=articles&num=445 a été utilisé

JFA-CR
