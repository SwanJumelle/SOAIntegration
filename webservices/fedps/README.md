# FedPS

## Order - Document

### Effectuer un devis

#### Requête

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ord="http://informatique.polytech.unice.fr/soa1/salle/services/order">
       <soapenv:Header/>
       <soapenv:Body>
          <ord:submit>
             <request xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ord:quote">
               <fromZipCode>59300</fromZipCode>
               <fromCountryCode>US</fromCountryCode>
               <toZipCode>86901</toZipCode>
               <toCountryCode>US</toCountryCode>
               <pickup>1417302000000</pickup>
               <shipping>STANDARD</shipping>
               <currency>USD</currency>
               <width>20</width>
               <height>10</height>
               <depth>5</depth>
               <unitSize>INCH</unitSize>
               <weight>7.5</weight>
               <unitWeight>LBS</unitWeight>
             </request>
          </ord:submit>
       </soapenv:Body>
    </soapenv:Envelope>

Les valeurs acceptables sont :

* `pickup` : date au format timestamp, postérieure à la date courante
* `shipping` : STANDARD, EXPRESS
* `currency` : USD, EUR, GBP
* `unitSize` : CM, INCH
* `unitWeight` : KG, LBS 

#### Réponse : succès

    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:Body>
          <ns2:submitResponse xmlns:ns2="http://informatique.polytech.unice.fr/soa1/salle/services/order">
             <result xsi:type="ns2:quoteOutput" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                <id>0</id>
                <eta>1417734000000</eta>
                <cost>8.5</cost>
             </result>
          </ns2:submitResponse>
       </soap:Body>
    </soap:Envelope>

* `id` : id du devis, nécessaire pour passer une commande à partir d'un devis
* `eta` : date estimée d'arrivée au format timestamp
* `cost` : coût dans la devise passée en paramètre de la requête

### Effectuer une commande à partir d'un devis

#### Requête

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ord="http://informatique.polytech.unice.fr/soa1/salle/services/order">
       <soapenv:Header/>
       <soapenv:Body>
          <ord:submit>
             <request xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ord:parcel_from_quote">
               <quoteId>0</quoteId>
               <cardNumber>carNumber</cardNumber>
               <sender>Jean</sender>
               <senderEmail>jean@mail.com</senderEmail>
               <receiver>Jacques</receiver>
               <pickup>1420000000000</pickup>
               <fromStreetNb>20A</fromStreetNb>
               <fromStreetName>Lombard Street</fromStreetName>
               <toStreetNb>10</toStreetNb>
               <toStreetName>Abc Avenue</toStreetName>
             </request>
          </ord:submit>
       </soapenv:Body>
    </soapenv:Envelope>

#### Réponse : succès

    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:Body>
          <ns2:submitResponse xmlns:ns2="http://informatique.polytech.unice.fr/soa1/salle/services/order">
             <result xsi:type="ns2:orderOutput" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                <id>0</id>
                <eta>1420604800000</eta>
             </result>
          </ns2:submitResponse>
       </soap:Body>
    </soap:Envelope>

* `id` : id du colis, nécessaire pour le suivi
* `eta` : date estimée d'arrivée au format timestamp

### Effectuer directement une commande

#### Requête

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ord="http://informatique.polytech.unice.fr/soa1/salle/services/order">
       <soapenv:Header/>
       <soapenv:Body>
          <ord:submit>
             <request xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ord:parcel">
               <cardNumber>cardNumber</cardNumber>
               <sender>Jean</sender>
               <senderEmail>jean@mail.com</senderEmail>
               <receiver>Jacques</receiver>
               <pickup>1420000000000</pickup>
               <from>
                 <streetNb>20A</streetNb>
                 <streetName>Lombard Street</streetName>
                 <zipCode>08986</zipCode>
                 <countryCode>US</countryCode>
               </from>
               <to>
                 <streetNb>10</streetNb>
                 <streetName>Abc Avenue</streetName>
                 <zipCode>8756</zipCode>
                 <countryCode>UK</countryCode>
               </to>
               <shipping>STANDARD</shipping>
               <currency>USD</currency>
               <width>20</width>
               <height>10</height>
               <depth>5</depth>
               <unitSize>INCH</unitSize>
               <weight>7.5</weight>
               <unitWeight>LBS</unitWeight>
             </request>
          </ord:submit>
       </soapenv:Body>
    </soapenv:Envelope>

Les valeurs acceptables sont :

* `pickup` : date au format timestamp, postérieure à la date courante
* `shipping` : STANDARD, EXPRESS
* `currency` : USD, EUR, GBP
* `unitSize` : CM, INCH
* `unitWeight` : KG, LBS

#### Réponse : succès

    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:Body>
          <ns2:submitResponse xmlns:ns2="http://informatique.polytech.unice.fr/soa1/salle/services/order">
             <result xsi:type="ns2:orderOutput" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                <id>0</id>
                <eta>1420604800000</eta>
             </result>
          </ns2:submitResponse>
       </soap:Body>
    </soap:Envelope>

* `id` : id du colis, nécessaire pour le suivi
* `eta` : date estimée d'arrivée au format timestamp

## Follow - REST

### Récupérer tous les colis

#### Requête
  
    GET /follow/parcels

#### Réponse

Code de retour : `200`.

On récupère une liste de pointeurs vers les colis.

    {
        "parcels": [
            "http://localhost:8080/follow/parcels/722466204",
            "http://localhost:8080/follow/parcels/753818080",
            "http://localhost:8080/follow/parcels/147730462"
        ]
    }

### Récupérer les informations d'un colis

#### Requête
  
    GET /follow/parcels/{id du colis}

#### Réponse

##### Succès

Code de retour : `200`.

    {
        "id"          : "356511910",
        "status"      : "CUSTOMS",
        "sender"      : "Alexis Mayo",
        "senderEmail" : "alexis_mayo@mail.com",
        "receiver"    : "Hermione Heath",
        "from"        : {
            "id"          : "218",
            "streetNb"    : "P.O. Box 278, 5739",
            "streetName"  : "Mauris Rd.",
            "zipCode"     : "13580",
            "cityName"    : "Eisden",
            "countryCode" : "US"
        },
        "to"          : {
            "id"          : "219",
            "streetNb"    : "495-1276",
            "streetName"  : "Neque. Road",
            "zipCode"     : "24017",
            "cityName"    : "Sète",
            "countryCode" : "US"
        },
        "shipping"    : "EXPRESS",
        "pickup"      : 1409649300000,
        "eta"         : 1410181200000,
        "cost"        : 84.86,
        "currency"    : "EUR",
        "width"       : 38,
        "height"      : 100,
        "depth"       : 22,
        "unitSize"    : "CM",
        "weight"      : 7,
        "unitWeight"  : "LBS"
    }

##### Erreur : colis non trouvé

Code de retour : `404`.

    {
        "type" : "parcel",
        "key"  : "{id du colis}"
    }

### Récupérer les colis en retard

#### Requête
  
    GET /follow/parcels/delayed

#### Réponse

Code de retour : `200`.

On récupère une liste de pointeurs vers les colis.

    {
        "parcels": [
            "http://localhost:8080/follow/parcels/722466204",
            "http://localhost:8080/follow/parcels/753818080",
            "http://localhost:8080/follow/parcels/147730462"
        ]
    }

### Récupérer le nombre de colis en fonction de leur état

#### Requête
  
    GET /follow/stats

#### Réponse

Code de retour : `200`.

    {
        "PICKED_UP": 18,
        "IN_TRANSIT": 16,
        "STORAGE": 16,
        "DELIVERED": 0,
        "ARRIVAL_SCAN": 17,
        "CUSTOMS": 17,
        "AWAITING_PICK_UP": 16
    }

### Récupérer les colis en fonction de leur état

#### Requête
  
    GET /follow/stats/{état du colis}

Le paramètre "état du colis" peut prendre les valeurs suivantes :

* PICKED_UP
* IN_TRANSIT
* STORAGE
* DELIVERED
* ARRIVAL_SCAN
* CUSTOMS
* AWAITING_PICK_UP

#### Réponse

Code de retour : `200`.

On récupère une liste de pointeurs vers les colis.

    {
        "parcels": [
            "http://localhost:8080/follow/parcels/722466204",
            "http://localhost:8080/follow/parcels/753818080",
            "http://localhost:8080/follow/parcels/147730462"
        ]
    }

## Round - REST

### Récupérer la prochaine adresse

#### Requête
  
    GET /round/{id du livreur}/next_address

#### Réponse

##### Succès

Code de retour : `200`.

    {
        "parcelId": "657047758",
        "kind": "DELIVERY",
        "address": {
            "id": "23",
            "streetNb": "980-352",
            "streetName": "Placerat. Ave",
            "zipCode": "80630",
            "cityName": "Jandrain-Jandrenouille",
            "countryCode": "US"
        }
    }

##### Erreur : aucun colis à livrer

Code de retour : `404`.

### Mettre à jour l'état d'un colis

#### Requête
  
    PUT /round/parcels/{id du colis}/{nouvel état}

Le paramètre "état du colis" peut prendre les valeurs suivantes :

* PICKED_UP
* IN_TRANSIT
* STORAGE
* DELIVERED
* ARRIVAL_SCAN
* CUSTOMS
* AWAITING_PICK_UP

#### Réponse

##### Succès

Code de retour : `200`.

##### Erreur : colis non trouvé

Code de retour : `404`.

    {
        "type" : "parcel",
        "key"  : "{id du colis}"
    }