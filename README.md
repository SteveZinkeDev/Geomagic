# Geomagic Übungsaufgabe

Dieses Projekt verarbeitet Linien und fasst zusammenhängende Linien zu Linienzügen (Polylines) zusammen.

## Input

In der Beispiel Input sind nur Ganzzahlen vorhanden, da in der Aufgabenstellung jedoch explizit von Fließkommazahlen die Rede ist, habe ich auch als Datentyp float verwendet. Ich bin mal davon ausgegange, dass einfache Genauigkeit ausreicht.

## Code & Aufbau

Aufgrunddessen habe ich auch für den Rest versucht den Code mehr so zu halten, als wäre es nicht nur eine kleine Beispielaufgabe. Zum Beispiel mehrere Konstruktoren und Methoden für verschiedene Anwendungsfälle.

Da ich anfangs davon ausging, dass die Linien Graphen sind, hatte ich erst angefangen ein Koordinatensystem zu zeichnen (siehe paint_axes()), aber letztendlich entfernt, weil sonst der Stern falsch rum wäre.