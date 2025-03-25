/**
 * Klasa Books zarządza operacjami związanymi z książkami.
 * W tej wersji zawiera metodę do wyświetlania książek z bazy danych.
 */
public class Books {

    // Obiekt do nawiązania połączenia z bazą danych
    ConnectToBase connectToBase = new ConnectToBase();

    /**
     * Wyświetla książki, łącząc się z bazą danych.
     *
     * @return Zwraca null, ponieważ metoda nie jest jeszcze zaimplementowana do zwracania wyników
     */
    public static String displayBooks() {
        // Wywołanie statycznej metody connect() z klasy ConnectToBase
        System.out.println(ConnectToBase.connect());

        // Zwrócenie null, ponieważ metoda jest wstępna i nie zawiera pełnej logik
        return null;
    }
}

