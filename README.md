# pixelProject
### Projekt z zadania rekrutacyjnego

W tym projekcie zrealizowano podane wymagania dotyczące:
- uzyskania cyfry kontrolnej dla podanego ciągu cyfr wg. algorytmu Luhna
- weryfikacji czy podany ciąg cyfr jest poprawny wg. algorytmu Luhna
- uzyskanie drogi oraz jej długości wg. algorytmu najbliższego sąsiada, gdzie użytkownik podaje współrzędne geograficzne oraz nazwy punktów

### Korzystanie z projektu

Aby uzyskać odpowiedź od API w celu otrzymania cyfry kontrolnej, należy wysłać request GET na poniższy adres, podając jednocześnie ciąg cyfr, np.:
```
http://localhost:8080/algorithms/LuhnControl/92480
```

Aby uzyskać odpowiedź od API w celu weryfikacji ciągu cyfr, należy wysłać request GET na poniższy adres, podając jednocześnie ciąg cyfr, np.:
```
http://localhost:8080/algorithms/LuhnCheck/924803
```

Aby uzyskać odpowiedź od API zawierającą trasę obliczoną metodą najbliższego sąsiada oraz jej długość, należy wysłać request POST na poniższy adres:
```
http://localhost:8080/algorithms/nearestNeighbour
```
Jako body requestu należy przesłać listę punktów zawierających współrzędne geograficzne oraz nazwy punktów, np.:
```
{
  "geoPoints": [
    {
      "name": "punkt A",
      "latitude": 10.1,
      "longitude": 15
    },
    {
      "name": "punkt B",
      "latitude": -5.25,
      "longitude": 19.477419
    },
    {
      "name": "punkt C",
      "latitude": 51.1,
      "longitude": -19.33
    }
  ]
}
```
Współrzędne można podawać w formie liczbowej lub tekstowej
```
"-14.7892"
```
Należy jednak pamiętać o użyciu kropki jako separatora dziesiętnego, w przeciwnym wypadku API zwróci błąd.