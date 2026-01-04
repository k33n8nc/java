# Feedback Week 01
- [x] Application is om te starten, maak een Restaurant shell (verantwoordelijk voor run)
- [] Nadenken over hoe we een dynamisch menu kunnen maken
- [] Wat zijn de mogelijkheden met het menu en wat zijn de voor- en nadelen?
- [] Welke optie kies ik en waarom?

Eerste poging om een restaurant shell te maken is gelukt.
In eerste instantie had ik geen @Component gebruikt, waardoor het niet lukte om te starten.
Daarna aan GPT gevraagd en met @Component werkte het wel.

# Optie 1
menu id's geven en de optie voor create/delete:
is uit te breiden door categorieën toe te voegen, zoals voorgerecht, hoofdgerecht, nagerecht.
``` json
{
id: 001,
name: Spaghetti Bolognese,
price: 20.00
}
```

# Optie 2
menu gebonden aan tijd, ochtend/middag/avond:
Met Events zou je bij opvragen menu eerst een time check kunnen doen alvorens het juiste menu uit te serveren.
``` json
{
time: morning,
items: [
    {
        name: Pancakes,
        price: 10.00
    },
    {
        name: Omelette,
        price: 12.00
    }
]
}
```
