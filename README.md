# Mitt program

Implementasjon av "Asteroids"

Eksempel på en som min versjon er løst basert på: https://freeasteroids.org/


Spillet går ut på at man flyr et romskip, som flyter rundt i rommet. Spilleren kan akselerere framover,
rotere skiper, og skyte med "mellomrom".

Når man ødelegger asteroider, får man poeng. Når man ødelegger store asteroider, dukker to nye asteroider opp.

Hvis spilleren kolliderer med en asteroide, mister spilleren ett liv. Totalt har man 3 liv.

Hvis spilleren går tom for liv, avsluttes spillet, og skoren vises på skjermen.
Deretter har man muligheten til å starte et nytt spill med å klikke "enter".


# Kontroller
* Pil-fram for å akselerere der skipet peker.
* Pil-venstre og høyre for å rotere skipet respektivt mot venste og høyre.
* Mellomrom for å skyte en kule i retningen skipet peker.
* Gå til andre siden av kartet ved å fly til kanten av kartet.


# Implementerte "features"
* Karakterer som "glir" jevnt(siden de er i rommet), skip som kan akselerere, rotere og skyte.
* Karakterer dukker opp på andre siden av kartet når de når kanten av kartet.
* Tilfeldig "spawning" av asteroider, som ikke kan spawne for nærmt spillerens skip.
* Mindre asteroider som "spawner" når en stor asteroide blir ødelagt.


# Kjente problemer:
* Jeg prøvde å implementere "resizing" slik at ting ble konsekvent når man resizer vinduet, vil ting ha ca like stor relativt størrelse.
Og at spillkartet "passer" til rammen til vinduet. Dette fungerer noenlunde, men også ikke helt, spesielt når vinduet blir resizet flere ganger.
Derfor burde vinduet helst ikke "resizes" når man spiller.

* Hvis spilleren skytes for fort, vil ikke skyte lyder spilles av fort nok.

# Kilder
* Lyd hentet fra: http://www.classicgaming.cc/classics/asteroids/sounds

Se [oppgaveteksten](./OPPGAVETEKST.md) til semesteroppgave 2. Denne README -filen kan du endre som en del av dokumentasjonen til programmet, hvor du beskriver for en bruker hvordan programmet skal benyttes.