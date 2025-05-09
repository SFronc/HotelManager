Program pozwala na własnoręczne utworzenie pokoi (po uruchomieniu - opcja 1), lub załadowanie z pliku struktury hotelu (opcja 2).
Aplikacja używa dwóch plików:

nazwaPersons.csv
nazwaRooms.csv

gdzie nazwa to ustalona nazwa hotelu.

Aby dodać nową komendę, należy zarejestrować ją metodą .registerCommand(nazwa_wywolawcza, klasa_komendy) obiektu commandManager w metodzie statycznej registerCommands.