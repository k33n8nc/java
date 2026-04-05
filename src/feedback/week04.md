# Feedback
- [x] Kijk naar Optional.ofNullable, Waitress heeft menu nodig dus dit gaat niet werken (zie commit 24 jan);
- dit was in menu config die we niet meer gebruiken.
- [x] Configureer pitest -> Geen IT's en kijken welke echt nodig zijn.
- [] Zorg voor 96% coverage
- [x] Zorg ervoor dat het menu vanuit een echte config komt, bijvoorbeeld vanuit een yaml of config.
- [x] Zorg voor een infinite loop in applicatie (zodat je meerdere orders kunt plaatsen)

- [ ] 3 losse processen (opnemen, bereiden, uitserveren van order)
- [ ] weekend uitzoeken (plan maken, nog niet concreet uitwerken, maandag presenteren)
- Doel hier is om te scheiden zodat processen tegelijk kunnen lopen.
- We willen dus los van elkaar meerdere orders kunnen opnemen, bereiden en uitserveren.
- Momenteel verwerkt de applicatie 1 order tegelijk, dus als er een order binnenkomt, wordt deze meteen verwerkt.

- hoe weet ik nu of **IT ook echt uitgesloten is? Kan ik dat ergens zien?
- Zijn er boilerplates voor Unit en IT? Het zou handig zijn als je zo iets als sout kan doen
