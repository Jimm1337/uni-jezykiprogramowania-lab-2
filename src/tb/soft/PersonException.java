package tb.soft;

import java.io.Serial;

/**
 * Klasa PersonException jest klasą wyjątków służącą do zgłaszania błędów
 * występujących przy operacjach na obiektach klasy Person.
 */
class PersonException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public PersonException(final String message) {
        super(message);
    }

} // koniec klasy PersonException
