# Asunto Tracker

Kurssin harjoitustyön tarkoitus on helpottaa asuntojen keskimääräisten neliöhintojen tutkimista aluekohtaisesti. 
Käyttäjä voi hakea kaupungin- tai kaupunginosan nimen perusteella alueen asuntojen keskimääräistä neliöhintaa ja tarkastella kilpailukykyisesti hinnoiteltuja myynnissä olevia asuntoja. 

## Käyttöohje

Päävalikossa on tällä hetkellä neljä mahdollista valintaa: 

1. Uusi haku
   -Käyttäjän on mahdollista tarkentaa hakua suodattamalla tietty kaupunginosa
   -Ohjelma kysyy haluaako käyttäjä sisällyttää suodattimen (Y/N)
   -Jos käyttäjä haluaa tarkentaa, ohjelma kysyy sekä kaupungin että kaupunginosan. Muussa tapauksessa vain kaupungin
2. Aiempien hakujen historia
   -Ohjelma listaa aiemmat haetut kaupungit sekä kyseisen kaupungin asuntojen keskimääräisen neliöhinnan
3. Hakuhistorian tyhjennys
   -Tyhjentää tietokannan
0. Sulje ohjelma

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Vaatimusm%C3%A4%C3%A4rittely.md)

[Tuntikirjanpito](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Arkkitehtuuri.md)

[Releaset](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Releaset.md)

## Komentorivi

### Testaus

Testit suoritetaan komennolla
> mvn test

Testikattavuusraportin luomisessa käytetään jacocoa. Raportti luodaan komennolla
> mvn jacoco:report

Jacoco luo selaimella avattavan html-tiedoston, joka löytyy hakemistosta  *target/site/jacoco/index.html*

Checkstyle raportin voit luoda komennolla
> mvn jxr:jxr checkstyle:checkstyle

Ladattavan releasen voi käynnistää komentoriviltä
> java -jar aTracker.jar

Javadoc-dokumentin luominen
> mvn javadoc:javadoc

## Huomioita
Ensimmäinen versio toimii yksinkertaisella tekstikäyttöliittymällä.
Ohjelman käynnistyttyä ohjelma luo juurikansioon SQLite-tietokannan data.db
Käyttäjän syötettyä kaupungin nimen, ohjelma lisää löydetyt asuntoilmoitukset SQLite-tietokantaan ja palauttaa tietokannasta lasketun keskimääräisen neliöhinnan.


