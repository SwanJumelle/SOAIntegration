# TeamForce

/teamforce-1.1

### Informations sur le customer

#### Requête

    GET /customer/{id}

#### Réponse :

    Code de retour : `200`.

    {
      "invoiceAddresses": {
        "line1": "3943 Fusce Rd.",
        "line2": "Wisconsin",
        "zipCode": "51748",
        "city": "Kenosha"
      },
      "deliveryAddresses": {
        "line1": "3943 Fusce Rd.",
        "line2": "Wisconsin",
        "zipCode": "51748",
        "city": "Kenosha"
      },
      "orders": [
        {
          "id": "4",
          "goods": [
            {
              "quantity": 5,
              "good": {
                "id": "P7Z5UA8DN34QG6W",
                "priceString": "$2.92",
                "price": 2.92,
                "categorie": "None"
              }
            },
            {
              "quantity": 10,
              "good": {
                "id": "J7W5PI2IQ29CQ6K",
                "priceString": "$91.97",
                "price": 91.97,
                "categorie": "None"
              }
            },
            {
              "quantity": 4,
              "good": {
                "id": "S8K3AH9GH16FQ1B",
                "priceString": "$72.15",
                "price": 72.15,
                "categorie": "None"
              }
            },
            {
              "quantity": 1,
              "good": {
                "id": "X6D4RB3RD94LK7M",
                "priceString": "$99.90",
                "price": 99.9,
                "categorie": "None"
              }
            },
            {
              "quantity": 5,
              "good": {
                "id": "J4Q7PK4NG95BZ3Z",
                "priceString": "$54.88",
                "price": 54.88,
                "categorie": "None"
              }
            }
          ],
          "status": "PREPARATION",
          "Date": "12/11/14",
          "customerId": "1"
        }
      ],
      "id": "1",
      "lastName": "Bolton",
      "firstName": "Lucian",
      "phoneNumber": "0800 155 4601",
      "email": "Sed.nunc.est@euturpis.co.uk"
    }

* `id` : id du customer

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