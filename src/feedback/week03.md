# Vragen
Zie WaitressTest.java
Kan dit makkelijker? Kan ik een Mock maken van het Menu (dus gevuld door MenuFacotry)?

- [] Zorg voor 96% coverage
- [] Zorg ervoor dat het menu vanuit een echte config komt, bijvoorbeeld vanuit een yaml of config.
- [] Zorg voor een infinite loop in applicatie (zodat je meerdere orders kunt plaatsen)
- Kan ik while restaurant = open(); gebruiken of aanmaken? Dat lijkt me een goed oplossing...
- Zo lang het restaurant open is blijven we orders opnemen totdat het restaurant sluit.

# Opmerkingen
- Als je code faalt op het moment dat het moet falen is dat prima. (je hoeft dat niet in te bouwen en te testen == null ?)
- als iets lastig te testen moet dat een signaal zijn dat de code anders moet
- line coverage ook, als je er lastig bij komt kan dat ook een signaal zijn
- wees kritisch op je code en bekijk en begrijp je feedback goed
- leesbaarheid, testbaarheid en onderhoudbaarheid

# Bevindingen
Ik denk dat imperative vs. declarative ook meer gaat spelen in het kader van leesbaarheid.
Zie commit MenuConfiguration van 24 jan. Juist doordat de classes zo klein mogelijk blijven 
profiteer je van de leesbaarheid.