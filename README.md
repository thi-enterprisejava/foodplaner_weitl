# foodplaner_weitl

Dokumentation FoodPlaner
===================

Der FoodPlaner ermöglicht das Speichern von Rezepten und das Planen eines Essens. Dazu können Rezepte ausgewählt werden, um anschließend eine Einkaufsliste anzusehen.

Der FoodPlaner ist hier verfügbar: 


----------

## Allgemeines ##
Die Anwendung wurde als Java Enterprise Edition Applikation mit JSF entwickelt. Der Zugriff erfolgt über eine Weboberfläche. Zudem wird eine REST und eine SOAP API angeboten.

## Aufbau ##
Die Anwendung ist in drei Teile gegliedert. Dabei wird das weit verbreitete MVC-Konzept verwendet. 

Im Model werden folgende Klassen verwendet:


Die Informationen werden mithilfe einer MySQL Datenbank persistiert. 

----------

Die View wird mithilfe folgender xhtml-Datein umgesetzt:


Zur Visualisierung wird zudem das Front-End-Framework bootstrap verwendet.  

----------

Das Model verwaltet die Logik und setzt auf den jeweiligen Präsentationsdaten auf: 


----------

Die Logik ist zudem in zwei Bestandteile aufgeteilt. Zum einen die Erstellung der Rezepte zum anderen die Erstellung einer Essenplanung und die dazugehöre Einkaufsliste.

