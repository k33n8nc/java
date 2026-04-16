- [x] fix test sources root
- [x] weekend uitzoeken (plan maken, nog niet concreet uitwerken, maandag presenteren)
- [x] 3 losse processen (opnemen, bereiden, uitserveren van order)
- [ ] pittest command
- [ ] Zorg voor 96% coverage
- [ ] code clean up!!

- order queue: een lijst van orders die nog moeten worden verwerkt
- chef moet in staat zijn orders af te werken em krijg een staat (idle, cooking, etc)
- order book (eventueel) -> bijhouden van alle order ook wat is geweest / afgerond

- Doel hier is om te scheiden zodat processen tegelijk kunnen lopen.
- We willen dus los van elkaar meerdere orders kunnen opnemen, bereiden en uitserveren.
- Momenteel verwerkt de applicatie 1 order tegelijk, dus als er een order binnenkomt, wordt deze meteen verwerkt.
