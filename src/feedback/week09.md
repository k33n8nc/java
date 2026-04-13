- [x] fix test sources root
- [x] weekend uitzoeken (plan maken, nog niet concreet uitwerken, maandag presenteren)
- [x] 3 losse processen (opnemen, bereiden, uitserveren van order) ➡️ zie punt 1
- [ ] pittest command
- [ ] Zorg voor 96% coverage
- [ ] @Async annotatie op @EventListener annotatie, hoe werkt dit? ➡️ zie punt 2
- [ ] code clean up!!

# ➡️ Punt 1
Doel hier is om te scheiden zodat processen tegelijk kunnen lopen.
We willen dus los van elkaar meerdere orders kunnen opnemen, bereiden en uitserveren.
Momenteel verwerkt de applicatie 1 order tegelijk, dus als er een order binnenkomt, wordt deze meteen verwerkt.

# ➡️Punt 2
Uitzoeken hoe dit werkt met thread sleep? Het lijkt (zie console logs) als of de chef onbereikbaar is tijdens sleeps
hierdoor worden event listeners in meerdere bulks uitvoerend.
