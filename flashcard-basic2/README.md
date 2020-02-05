# Webframeworks - Servlet

## Übung  1: BasicServlet erweitern

**Ausgabe: 16.9.19**

**Besprechung: 23.9.19**

Die URL ```http://localhost:80/flashcard-basic/app/questionnaires/1```
wird von der Applikation noch nicht korrekt behandelt. Sie führt immer zur Startseite - anstatt das Detail des entsprechenden Fragebogens (hier mit der ```ID=1```) anzuzeigen.
Ergänzen sie das BasicServlet so, dass ein Request auf ```http://localhost:80/flashcard- basic/app/questionnaires/{id}``` korrekt behandelt wird und z.B. für ```id=1``` zur Seite führt.

***Build & Run***

Web-Archive bauen: ```gradle war```

Tomcat mit Web-App starten: ```docker-compose up```