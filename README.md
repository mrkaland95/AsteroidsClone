# Mitt program

Implementasjon av "Asteroids"

Eksempel på en som min versjon er løst basert på: https://freeasteroids.org/


Spillet går ut på at man flyr et romskip, som flyter rundt i rommet.
Der skal man skyte disse asteroidene, samtidig 
Når man ødelegger asteroider, får man poeng.

Man kan akselerere skipet sitt ved å trykke "pil opp", snu skipet til venstre ved å trykke "pil venstre", og til høyre vet å trykke "pil høyre".
For å skyte på asteroider, trykker man mellomrom knappen.

# Kontroller
* Pil-fram for å akselerere der skipet peker.
* Pil-venstre og høyre for å rotere skipet respektivt mot venste og høyre.
* Mellomrom for å skyte en kule i retningen skipet peker.





# Implementerte "features"
* Karakterer som "glir" jevnt(siden de er i rommet), skip som kan akselerere, rotere og skyte.
* Karakterer dukker opp på andre siden av kartet når de når kanten av kartet.
* Tilfeldig "spawning" av asteroider, som ikke kan spawne for nærmt spillerens skip.



# Kjente problemer:
Jeg prøvde å implementere "resizing" slik at ting ble konsekvent når man resizer vinduet, vil ting ha ca like stor relativt størrelse.
Og at spillkarter "passer" til rammen til vinduet. Dette fungerer noenlunde, men også ikke helt, spesielt når vinduet blir resizet flere ganger.
Derfor burde vinduet helst ikke "resizes" når man spiller.


# Kilder
* Lyd hentet fra: http://www.classicgaming.cc/classics/asteroids/sounds

Se [oppgaveteksten](./OPPGAVETEKST.md) til semesteroppgave 2. Denne README -filen kan du endre som en del av dokumentasjonen til programmet, hvor du beskriver for en bruker hvordan programmet skal benyttes.