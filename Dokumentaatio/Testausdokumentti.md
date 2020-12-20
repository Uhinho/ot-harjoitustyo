# Testausdokumentti

Ohjelmaa on testattu JUnit-automaatiotestein ja manuaalisesti.

## Yksikkö- ja integraatiotestaus

Automatisoidut testit muodostuvat sovelluslogiikkaa eli pakkauksen logic-luokkia testaavien [ApartmentTest](https://github.com/Uhinho/ot-harjoitustyo/blob/master/aTracker/src/test/java/logicTests/ApartmentTest.java) ja [LogicTestin](https://github.com/Uhinho/ot-harjoitustyo/blob/master/aTracker/src/test/java/logicTests/LogicTest.java) lisäksi tietokantatoimintoja testaavasta [DatabaseTestistä](https://github.com/Uhinho/ot-harjoitustyo/blob/master/aTracker/src/test/java/databaseTests/DatabaseTest.java).

Integraatiotesteissä pysyväistallennukseen käytetään sovelluksen omaa tietokantaa, josta testausdata pyyhitään testien päätteeksi. 

Apartment-luokan konstruktoria ja toString-metodia testataan yksikkötestillä [ApartmentTest](https://github.com/Uhinho/ot-harjoitustyo/blob/master/aTracker/src/test/java/logicTests/ApartmentTest.java).

## Testikattavuus

Kun käyttöliittymän rakentava luokka on jätetty pois testauksesta, automatisoitujen testien rivikattavuus on 87% ja haaraumakattavuus 78%.

![](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/jacoco.png)


## Järjestelmätestaus

aTracker-sovelluksen järjestelmätestaus on suoritettu manuaalisesti.
Sovellusta on mm. yritetty kaataa epäloogisilla syötteillä. 


