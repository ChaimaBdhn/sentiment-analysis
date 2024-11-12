# Rapport de projet  

## Analyse de sentiments sur Twitter  

Ce projet est réalisé par Chaïma BOUDEHANE et Mohamed OUKAS, étudiants en `M1 Cloud Computing and Cybersecurity`.  


## Objectifs  

**Ce PJE consiste à développer une application qui permet de classifier le sentiment général (positif, négatif, neutre) exprimé dans des tweets donnés sur un sujet donné (par exemple, réchauffement climatique). Pour cela des algorithmes d'apprentissage supervisé classiques (Dictionnaire, KNN, Bayes) seront développés et leurs performances analysées.**  


## 1. Nettoyage des tweets  

Nous avons pu implémenter une classe qui prépare nos données. D'après notre fichier `csv` qui contiendra principalement des tweets en français sur différents 
sujets (VLille, équipe de France, élection présidentielle), nous "nettoyons" chaque tweet des symboles suivants : **@**, **RT**, **URL**, **#**, ...
Nous récupérons depuis ce fichier uniquement la dernière colonne qui contient les tweets, puis après récupération et nettoyage, nous produisons un nouveau fichier `csv` en sortie contenant
les tweets dépourvus de symboles et d'urls. 
Sur cette base, nous pouvons ainsi débuter l'implémentation des algorithmes d'apprentissage.  


## 2. Implémentation des algorithmes d'apprentissage  

Nous devons commencer par implémenter 3 algorithmes d'apprentissage : **Dictionnaire**, **KNN** et **Bayes**. Ce sont différentes méthodes pour apprendre et déterminer un résultat après analyse de tweets.  







### Pour l'application : 


Si le fichier data importé est annoté alors :

 - KNN --> Afficher le résulat


Si le fichier data importé n'est pos annoté  on a 2 cas : 


 - Soit l'utilisateur peut annoter les données -> puis utiliser KNN --> afficher le résultat

 - Soit l'utilisateur ne veut pas --> on utilise l'annotation auto (exercice 2) --> puis utiliser KNN --> afficher le résultat



 NOTE pour KNN : implémenter une méthode qui récupère la première et dernière colonne de chaque ligne du fichier csv puis nettoie uniquement le tweet dataManager.cleanAllTweets(csvFile -> [1]); 