Afin de pouvoir mettre en place cette nouvelle feature des "types", nous avons suivi la marche suivante:
- Création d'une nouvelle donnée membre "type", dans la classe Carte afin de pouvoir choisir le type de la carte.
- Ce type est choisi grâce à la création d'une nouvelle méthode genererTypeAleatoire() dans la classe Deck afin de pouvoir mettre en place une génération aléatoire du type de la carte.
- Cette méthode est appelé dans la création du deck, dans la méthode generationDeck().