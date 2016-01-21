**Dokumentation FoodPlaner**
===================

Der FoodPlaner ermöglicht das Speichern von Rezepten und das Planen eines Essens. Dazu können Rezepte ausgewählt werden, um anschließend eine Einkaufsliste anzusehen.

Der FoodPlaner ist hier verfügbar: 


----------

## **Allgemeines** ##
Die Anwendung wurde als Java Enterprise Edition Applikation mit JSF entwickelt. Der Zugriff erfolgt über eine Weboberfläche. Zudem wird eine REST und eine SOAP API angeboten.


----------


## **Aufbau** ##
Die Anwendung ist in grundsätzlich drei Teile gegliedert. Dabei wird das weit verbreitete MVC-Konzept verwendet. Zudem wurden Services für den Persistieren der Daten erstellt.

![enter image description here](http://www.adobe.com/inspire-archive/october2008/articles/article2/images/fig01.jpg)

http://www.adobe.com/inspire-archive/october2008/articles/article2/images/fig01.jpg

## Model ##
Im Model werden folgende Klassen verwendet:

**Recipe**

Diese Klasse ist das Hauptobjekt der Anwendung sie beinhaltet alle Informationen welche für die Zubereitung einer Speise benötigt werden.
Dazu gehören:

 - Name des Rezepts
 - Kurzbeschreibung
 - Zubereitungsbeschreibung
 - Zutaten-Liste (bestehend aus Food)
 - Bild

**Food**

Dieses Element wird für beide Teile der Anwendung benötigt als Zutat im Rezept und als Element in der Einkaufsliste (FoodList)
Die Inhalte dieser Klasse sind:

 - Name des Nahrungsmittels
 - Menge 
 - Einheit (Unit)

**Unit**

Dieses Element ist als Enum umgesetzt und definiert die verschiedenen Einheiten eines Foods.
Diese sind zum Teil kompatibel und werden für die Erstellung einer FoodList zusammengeführt.
Folgende Einheiten können ausgewählt werden:

 - kg
 - g
 - l
 - ml
 - piece

**FoodList**

Die FoodList speichert in einer internen Liste alle Bestandteile der ausgewählten Rezepte. Daraus wird bei Bedarf die Einkaufsliste generiert. 

**User**

Der User wird zum anmelden und registrieren eines Benutzers benötigt. Dabei werden verschiedene Informationen gespeichert:

 - Nickname
 - Passwort (Hashed)
 - Role

**Role**

Die Rolle ist wieder ein Enum welches die Rollen der Benutzer definiert.
Dabei können zwei Varianten ausgewählt werden:

 - User
 - Admin (noch nicht verwendet)

Die Informationen werden mithilfe einer MySQL Datenbank persistiert. 

## View ##
Zur Visualisierung wird das Front-End-Framework bootstrap verwendet.  
Die View wird mithilfe folgender xhtml-Datein umgesetzt:

**template**

Das Template setzt die Menüleiste um. Hier wird auf die verschiedenen Hauptseiten weitergeleitet.

**home**

Auf dieser Seite wird der Nutzer begrüßt und es wird die Funktionalität der Seite kurz erläutert.

**recipeoverview**

Die Rezept Übersicht ist das Herzstück der Anwendung hier werden alle Rezepte aufgelistet und es kann nach Rezepten gesucht werden. Zudem wir hier auf die Rezent-Erstellen Seite weitergeleitet.

**planing**

Beim der Planung können drei Gänge gewählt werden um daraus eine Einkaufsliste zu generieren. Hier wird anschließend auf die Einkaufsliste (list) weitergeleitet.

**list**

Die List zeigt alle in Planung ausgewählten Rezept-Zutaten als Liste an. Elemente mit dem selben Namen und einer kompatiblen Einheit werden dabei zusammengefasst. Zudem wird eine leserliche Größe der Menge berechnet (z.B.: kg wenn höhere Mengen g wenn geringere Mengen).

**newrecipe**

Diese Seite wird zum Erstellen eines Rezepts verwendet. Hierbei müssen verschiedene Felder ausgefüllt werden, zudem kann auch ein Bild hochgeladen werden.

**recipedetail**

Die Detailseite zeigt alle Informationen zu einem Rezept an. Hier können Beschreibung gelesen werden oder es kann das Bild betrachtet werden. Hier kann ein Rezept auch bearbeitet oder gelöscht werden, wenn ein Nutzer eingeloggt ist.

**login**

Die Login-Seite wird zum Anmelden eines Nutzers angezeigt. Dort wird automatisch weitergeleitet falls ein Benutzer uneingeloggt folgende Seiten aufrufen will:

 - newrecipe
 - planing
 - list

Sind die Anmeldeinformationen nicht korrekt wird auf die login-error Seite weitergeleitet. Zudem kann mit einem Register Link ein neuer Nutzer angelegt werden (newuser).

**login-error**

Diese Seite zeigt an, dass falsche Informationen beim Anmelden eingegeben wurden.

**newuser**

Beim anlegen eines neuen Users können zwei Informationen angegeben werden. Der Nickname und das Passwort, anschließend wir ein neuer Nutzer mit der Rolle "User" angelegt.

## Controller ##
Der Controller verwaltet die Logik und setzt auf den jeweiligen Präsentationsdaten auf: 

**Home**

In dieser Klasse wir nur das ausloggen eines Nutzer umgesetzt.

**ImageReader**

Dieser Controller ist unabhängig von einer View und wird als allgemeiner ImageReader verwendet. Er greift dazu auf die Datenbank zu und liest ansprechen einer ID ein Bild welches anschließend verwendet werden kann.

**ListOverview**

Hier wird die Logik für die Seite list umgesetzt. Dazu gehören verschiedene Filter- und Überprüfungsalgorithmen. Mithilfe dieser wird die Einkaufsliste vereinfacht. 

**NewRecipe**

Dieser Controller verwaltet die Erstellung eines Rezepts und nimmt Aufrufe von der View entgegen um Food zum Rezept hinzuzufügen. Zudem gibt er beim Speichern in der GUI den Aufruf an die Datenhaltung weiter um zu speichern.

**NewUser**

Diese Klasse verwaltet die Erstellung eines neuen Nutzers. Auch hier werden die Daten empfangen und gegebenenfalls wird die Datenhaltung angetriggert die Daten zu persistieren.

**Planing**

Im Planung wird das Empfangen und verwalten der verschiedenen Gänge umgesetzt. Anschließend wird auch hier die Datenhaltung angewiesen die Daten zu speichern.

**RecipeOverview**
In der Übersicht wird der Aufruf zum Lesen der Daten aus der Datenhaltung umgesetzt. Dies wird beim Ausführen einer Suche durchgeführt.

## Datenhaltung ##
Für die Datenhaltung wurden verschiedene Services umgesetzt um die Aufrufe zur Datenbank umzusetzen. 
Dabei wurden zwei Services entwickelt:

**FoodPlanerServiceDatabase**

Diese Klasse verwaltet die Zugriffe auf die Datenbank für alle Funktionen des FoodPlaners. Dazu gehören:

 - Hinzufügen, bearbeiten und löschen von Rezepten
 - Lesen von Rezepten nach Namen oder ID
 - Lesen aller Rezepte
 - Hinzufügen einer FoodList
 - Lesen einer FoodLust nach ID
 - Lesen der aktuellsten FoodList

**UserServiceDatabase**

Der User Service ist für die Erstellung von Nutzern zuständig. Zudem wird hier das Passwort des Nutzers gehasht.
