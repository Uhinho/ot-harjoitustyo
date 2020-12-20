# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne on kolmikerroksinen: 

![](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/kerros.png)

## Käyttöliittymä
Tekstikäyttöliittymä, jossa käyttäjä voi 
1. Tehdä haun uuden kaupungin asuntoilmoituksista
2. Katsoa hakuhistorian
3. Tyhjentää tietokannan
0. Poistua näkymästä

## Sovelluslogiikka
Sovelluksen datamallin muodostavat luokat Database ja Apartment, jotka kuvaavat tietokantaa ja yksittäisen asuntoilmoituksen yksityiskohtia.

![](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/luokkakaavio.png)





## Päätoiminnallisuudet

### Ilmoitusten haku ja lisääminen tietokantaan

Kun käyttäjä tekee haun ja antaa syötteenä kaupungin nimen, Scraper-luokka tarkistaa ensin onko kyseinen kaupunki jo tietokannassa.
Mikäli kaupunkia ei löydy jo tietokannasta, scraper käy silmukassa läpi Etuovi.comin kyseisen kaupungin listaukset ja lisää tiedot "apartments"-nimiseen SQL pöytään.

![](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/datanlisaaminen.png)

### Keskimääräisen neliöhinnan palautus

Jokaisen haun päätteeksi käyttöliittymä tulostaa keskimääräisen neliöhinnan. UiLogic-luokka pyytää Database-luokan palauttamaan listan Apartment-luokan olioita.
UiLogic-luokan getAvgPrice-metodin sisällä kädään lista silmukkana läpi ja palautetaan neliöhintakeskiarvo.
Kun keskiarvo on laskettu, lisätään tietokannan "results"-pöytään kyseisen haun kaupunki ja neliöhinta hakuhistorian ylläpitämiseksi.

![](https://github.com/Uhinho/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/keskiarvo.png)
