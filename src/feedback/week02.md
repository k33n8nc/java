# Denkwijze
MenuItems is een ArrayList. ArrayList heeft een standard method add.
Ik gebruik add items in Menu class zodat de verantwoordelijkheid daar blijft.
Encapsulation door Menu controle te geven over eigen "internal" state.

# Aanpassingen Factory en immutable menu
Classes zouden standaard immutable moeten zijn tenzij er een reden is om het niet te doen.
In dit geval wil je enkel dat de Waitress een Menu kan presenteren en van daar een order kan opmaken.

# Hoe verder?
- [x] Unit en IT afmaken
- Applicatie opdelen in zodat hij volledig voldoet aan SRP.
- Waitress krijgt 2 services, TakeOrderService, ServeOrderService 
- (of waitress verdwijnt geheel omdat we een virtueel restaurant zijn)

# Vragen
- De Factory class is een utility? Hoe noemen we dit? Wat gebeurt hier precies?
- We kunnen hiermee de applicatie configurabel maken, wat als we o.b.v. tijd nu verschillende menu's willen aanbieden.
- De Factory zou verantwoordelijk kunnen zijn 

# Opmerkingen naar mezelf
Ik merk dat ik vaker zeg dat het later altijd indaalt, maar volgens mij
kan ik me niet genoeg concentreren wanneer ik in een meeting zit. Teveel afleiding.
- realiseer je dit en handel indien nodig
- neem momenten na meetings voor korte analyse (5/10 min)

# Feedback
- [x] Installeer Maven pit-test 
- [x] Waitress hoeft niks te weten van de factory, enkel van het menu.
- [] Zorg ervoor dat het menu vanuit een echte config komt, bijvoorbeeld vanuit een yaml of config.
- [] Zorg voor 96% coverage
- [x] Unit Tests nalopen - menu wordt meermaals aangemaakt, kan ook makkelijker.
- [] Integration Tests nalopen
