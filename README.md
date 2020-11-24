# Asunto Tracker

Kurssin harjoitustyön tarkoitus on helpottaa asuntojen keskimääräisten neliöhintojen tutkimista aluekohtaisesti. 
Käyttäjä voi hakea kaupungin- tai kaupunginosan nimen perusteella alueen asuntojen keskimääräistä neliöhintaa ja tarkastella kilpailukykyisesti hinnoiteltuja myynnissä olevia asuntoja. 

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Vaatimusm%C3%A4%C3%A4rittely.md)

[Tuntikirjanpito](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Tuntikirjanpito.md)

## Komentorivi

### Testaus

Testit suoritetaan komennolla
> mvn test

Testikattavuusraportin luomisessa käytetään jacocoa. Raportti luodaan komennolla
> mvn jacoco:report

Jacoco luo selaimella avattavan html-tiedoston, joka löytyy hakemistosta  *target/site/jacoco/index.html*

Checkstyle raportin voit luoda komennolla
> mvn jxr:jxr checkstyle:checkstyle


## Huomioita
Ensimmäinen versio toimii yksinkertaisella tekstikäyttöliittymällä, joka kysyy kaupungin nimeä, jonka asuntojen
keskimääräisen neliöhinnan käyttäjä haluaa hakea.
Käyttäjän syötettyä kaupungin nimen, ohjelma lisää löydetyt asuntoilmoitukset SQLite-tietokantaan ja palauttaa tietokannasta lasketun keskimääräisen neliöhinnan.


