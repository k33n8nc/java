# Leeg
Ik wil nu voor orders een wachtrij die de chef kan gebruiken.
Net als in een echt restaurant waarbij de order bonnetjes aan het rek worden gehangen 
en de chef deze per stuk bekijkt en bereidt.

De chef neemt de eerstvolgende order, begint met koken en pakt direct de volgende order. 
Het koken zou los moeten staan van de orders afwerken. 
Koken kan (wellicht) nog steeds met thread.sleep, maar het moet niet zo zijn dat de chef pas
na het koken pas een volgende order van de "lijst" haalt.

In plaats daarvan zou het van de lijst halen / op het vuur zetten een seconde kunnen duren.
Het koken duurt nu 6 seconden
en na 1 + 6 is de order dan klaar

Hiermee simuleren we een kok die meerdere orders tegelijk kan koken. 
We zouden een limit van 3 orders kunnen hanteren

# Doel voor nu
- order queue: een lijst van orders die nog moeten worden verwerkt
- chef moet in staat zijn orders af te werken en krijgt zelf ook een staat (idle, cooking, etc)

# Extra
- order book (eventueel) -> bijhouden van alle orders ook wat is geweest / afgerond