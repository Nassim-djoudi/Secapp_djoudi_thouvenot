1- Importer l'archive du projet sur Eclipe
2- Exécuter run-hsqldb et show-hsqldb avec URL : jdbc:hsqldb:hsql://localhost/
3- Excécuter le serveur backend sous jaxRs avec Eclipse (exécuter la classe RestServer)
4- Se placer dans le repertoire ../frontend et exécuter Angular avec la commande : ng serve
5- Se connecter au lien http://localhost:4200/
6- Aller sur http://localhost:4200/add-form pour ajouter un formulaire
7- Une fois le formulaire rempli et envoyé, un lien est donné. Le lien a pour forme forme 
http://localhost:4200/choice/%7Bid%7D où id est l'id du sondage. Utiliser ce lien pour ajouter 
des utilisateurs et leurs préferences sur ce sondage.
8- Aller sur http://localhost:4200/logging pour consulter les paramètres du sondage. Cette partie
est destinée uniquement à l'organisateur et est protègée par un mot de passe.
Deux modes sont disponibles pour recupérer les informations, une sécurisée qui protège
des injections SQL et une non protègée pouvant être attaquée à l'aide du champ "p' or '1'='1" 
(sans les "") mis dans le champ mot de passe