import static java.lang.Math.abs; // Absolutwert
import static java.lang.Math.max; // Maximum zweier Zahlen

/**
 * Diese Klasse implementiert eine Überprüfung gültiger Züge auf einem Mühle-artigen
 * Feld. Das Feld besteht dabei aus zwei ineinander geschachtelten Quadraten. Eines
 * hat die Seitenlänge 3, das andere die Seitenlänge 5. Auf den beiden Quadraten darf
 * sich die Figur frei bewegen. Zwischen den beiden Quadraten darf aber nur in der
 * Mitte der Quadratkanten gewechselt werden.
 *
 * @author Thomas Röfer
 */
class Rules
{
    /** Die x-Koordinate der Mitte beider Quadrate, also des Spielfelds. */
    private final int centerX;

    /** Die y-Koordinate der Mitte beider Quadrate, also des Spielfelds. */
    private final int centerY;

    /**
     * Erzeugt ein neues Regel-Objekt.
     * @param x Die x-Koordinate der Mitte beider Quadrate, also des Spielfelds.
     * @param y Die y-Koordinate der Mitte beider Quadrate, also des Spielfelds.
     */
    Rules(final int x, final int y)
    {
        centerX = x;
        centerY = y;
    }

    /**
     * Überprüft, ob ein Zug legal ist, also den erlaubten Bewegungen auf dem
     * oben beschriebenen Spielfeld entspricht. Ein Zug, bei dem sich die Figur
     * nicht bewegt, ist nicht erlaubt.
     * @param object Das Spielobjekt, das den Zug ausführen soll.
     * @param dirX Die Schritte in x-Richtung. Dürfen nur -1, 0 oder 1 sein.
     * @param dirY Die Schritte in y-Richtung. Dürfen nur -1, 0 oder 1 sein.
     * @return Darf diese Bewegung ausgeführt werden?
     */
    boolean isLegal(final GameObject object, final int dirX, final int dirY)
    {
        // Es ist nicht erlaubt, sich nicht zu bewegen.
        if (dirX == 0 && dirY == 0) {
            return false;
        }

        // Es darf nur entweder in x- oder in y-Richtung gelaufen werden.
        if (dirX != 0 && dirY != 0) {
            return false;
        }

        // Schritte dürfen nicht größer als 1 sein.
        if (abs(dirX) > 1 || abs(dirY) > 1) {
            return false;
        }

        // Es ist erlaubt, auf demselben Quadrat (Level) zu bleiben.
        final int fromLevel = maxAbs(object.getX(), object.getY());
        final int toLevel = maxAbs(object.getX() + dirX, object.getY() + dirY);
        if (fromLevel == toLevel) {
            return true;
        }

        // Ab hier geht es um den Wechsel zwischen Quadraten (Leveln).
        // Ein Wechsel ist nur in der Mitte einer Kante erlaubt.
        if (object.getX() != centerX && object.getY() != centerY) {
            return false;
        }

        // Das äußere Quadrat muss die Nummer 2 haben, ansonsten ist der Zug
        // illegal. Da die Quadrate benachbart sind, hat das andere dann den
        // Level 1.
        final int outerLevel = max(fromLevel, toLevel);
        return outerLevel == 2;
    }

    /**
     * Die Methode bestimmt, auf welchem Quadrat sich eine Position befindet.
     * @param x Die x-Koordinate der Position.
     * @param y Die y-Koordinate der Position.
     * @return Die Nummer des Quadrats. Dies ist der größere der x- und
     *         y-Abstände von der Mitte.
     */
    private int maxAbs(final int x, final int y)
    {
        return max(abs(x - centerX), abs(y - centerY));
    }
}
