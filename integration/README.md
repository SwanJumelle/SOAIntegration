# Warning

This file is not up to date!

# MisterDiscount

Adresse : `http://informatique.polytech.unice.fr/soa1/MisterDiscount`

## Passer une commande

Adresse : `/order`

Opération : `ProcessOrder`

###### Requête

Nom              |Type       |Description
-----------------|-----------|-----------------------------------
`customerId`     |String     |Identifiant de l'acheteur
`cardNb`         |Long       |Numéro de carte bleue
`items`          |List\<Item>|Liste des produits
`shippingAddress`|Address    |Adresse de livraison. Facultative : si non fournie, utilisation de l'adresse par défaut de l'utilisateur

Types utilisés :

* **Item**

Nom         |Type  |Description
------------|------|--------------------
`productRef`|String|Référence du produit
`quantity`  |int   |Quantité commandée

* **Address**

Nom          |Type  |Description
-------------|------|-----------------
`streetNb`   |String|Numéro de rue
`streetName` |String|Nom de rue
`zipCode`    |String|Code postal
`city`       |String|Ville
`countryCode`|String|Identifiant pays

###### Réponse

Nom       |Type       |Description
----------|-----------|------------------------------------------
`orderId` |String     |Identifiant de la commande
`eta`     |Long       |Date estimée de livraison (timestamp)
`items`   |List\<Item>|Liste des produits effectivement commandés

###### Exemple

    <soapenv:Envelope xmlns:mis="http://informatique.polytech.unice.fr/soa1/MisterDiscount/order" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
       <soapenv:Header/>
       <soapenv:Body>
          <mis:ProcessOrder>
          
          
             <customerId>UHO76987</customerId>
             <cardNb>3456O678867845</cardNb>
             <shippingAddress>
                <streetNb>10</streetNb>
                <streetName>Abc Avenue</streetName>
                <zipCode>8756</zipCode>
                <city>Bellevue</city>
                <countryCode>US</countryCode>
             </shippingAddress>
             <items>
                <item>
                   <productRef>P7Z5UA8DN34QG6W</productRef>
                   <quantity>2</quantity>
                </item>
                <item>
                   <productRef>V5S4MA8DW44OO5H</productRef>
                   <quantity>1</quantity>
                </item>
             </items>
             
             
          </mis:ProcessOrder>
       </soapenv:Body>
    </soapenv:Envelope>

<p></p>

    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:Body>
          <ns2:ProcessOrderResponse xmlns:ns2="http://informatique.polytech.unice.fr/soa1/MisterDiscount/order">
             <result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ns2:orderOutput">
             
                <orderId>ERZT67I8</orderId>
                <eta>1420604800000</eta>
                <items>
                   <items>
                      <productRef>P7Z5UA8DN34QG6W</productRef>
                      <quantity>2</quantity>
                   </items>
                   <items>
                      <productRef>V5S4MA8DW44OO5H</productRef>
                      <quantity>1</quantity>
                   </items>
                </items>
                
                
             </result>
          </ns2:ProcessOrderResponse>
       </soap:Body>
    </soap:Envelope>

#### Envoi d'email de suivi

<p style="color:red; font-size:20px; font-weight:bold; margin:40px">TODO</p>

(Pas d'appel direct du service, cela doit se faire automatiquement lorsque l'une des choses est validée.)

## Consulter les informations d'une commande

#### Généralités

###### Requête

Adresse : `/orders/{orderId}`

Type : `GET`

Nom      |Type  |Description
---------|------|--------------------------
`orderId`|String|Identifiant de la commande

###### Réponse

Statut : `200` si Ok, `404` si commande non trouvée.

#### Connaître le statut

Adresse : `/status`

###### Réponse

Nom      |Type   |Description
---------|-------|--------------------------------------------------------------------
`status` |String |Statut de la commande (`PREPARED`, `WAITING_FOR_PAYMENT`, `SENT`...)

###### Exemple

    GET http://localhost:8081/orders/ERZT67I8/status

<p></p>

    WAITING_FOR_PAYMENT

#### Récupérer le reçu

Adresse : `/receipt`

###### Réponse

Nom       |Type       |Description
----------|-----------|------------------------------------------
`orderId` |String     |Identifiant de la commande
`eta`     |Long       |Date estimée de livraison (timestamp)
`items`   |List\<Item>|Liste des produits effectivement commandés

###### Exemple

    GET http://localhost:8081/orders/ERZT67I8/receipt

<p></p>

    {
        "orderId": "ERZT67I8",
        "eta": 1420604800000,
        "items": [
            {
                "productRef": "P7Z5UA8DN34QG6W",
                "quantity": 2
            },
            {
                "productRef": "V5S4MA8DW44OO5H",
                "quantity": 1
            }
        ]
    }

#### Récupérer la facture

Adresse : `/invoice`

<p style="color:red; font-size:20px; font-weight:bold; margin:40px">TODO</p>

#### Connaître le numéro de suivi

Adresse : `/transport`

###### Réponse

Nom         |Type  |Description
------------|------|-------------------
`carrier`   |String|Nom du transporteur
`trackingNb`|String|Numéro de suivi
`url`       |String|Adresse de suivi

###### Exemple

    GET http://localhost:8081/orders/ERZT67I8/transport

<p></p>

    {
        "carrier": "FedPS",
        "trackingNb": "53667I7O",
        "url": "http://localhost:8080/fedps/follow/53667I7O"
    }

## S'abonner au catalogue

<p style="color:red; font-size:20px; font-weight:bold; margin:40px">TODO</p>