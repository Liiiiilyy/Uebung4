// Importieren der VK_*-Tastenkonstanten
import static java.awt.event.KeyEvent.*;

/**
 * Dies ist die Hauptklasse eines Spiels. Eine Spielfigur kann mit der Tastatur
 * gesteuert werden. Sie bleibt dabei auf dem Gitter des Mühle-artigen
 * Spielfeldes.
 *
 * Die Legalität von Spielzügen wird von einer Instanz der Klasse {@link Rules}
 * geprüft.
 *
 * @author Thomas Röfer
 */
abstract class OneMansMorris extends Game
{
    /** Das Spiel beginnt durch Aufruf dieser Methode. */
    public static void main(String[] args)
    {
        new GameObject.Canvas(5, 5, 96, 96);
        new GameObject(0, 0, 0, "path-l");
        new GameObject(1, 0, 0, "path-i");
        new GameObject(2, 0, 1, "path-t");
        new GameObject(3, 0, 0, "path-i");
        new GameObject(4, 0, 1, "path-l");

        new GameObject(0, 1, 1, "path-i");
        new GameObject(1, 1, 0, "path-l");
        new GameObject(2, 1, 3, "path-t");
        new GameObject(3, 1, 1, "path-l");
        new GameObject(4, 1, 1, "path-i");

        new GameObject(0, 2, 0, "path-t");
        new GameObject(1, 2, 2, "path-t");
        new GameObject(2, 2, 3, "grass");
        new GameObject(3, 2, 0, "path-t");
        new GameObject(4, 2, 2, "path-t");

        new GameObject(0, 3, 1, "path-i");
        new GameObject(1, 3, 3, "path-l");
        new GameObject(2, 3, 1, "path-t");
        new GameObject(3, 3, 2, "path-l");
        new GameObject(4, 3, 1, "path-i");

        new GameObject(0, 4, 3, "path-l");
        new GameObject(1, 4, 0, "path-i");
        new GameObject(2, 4, 3, "path-t");
        new GameObject(3, 4, 0, "path-i");
        new GameObject(4, 4, 2, "path-l");

        final GameObject player = new GameObject(1, 1, 0, "claudius");
        final Rules rules = new Rules(2, 2);

        while (true) {
            final int key = getNextKey();
            if (key == VK_RIGHT && rules.isLegal(player, 1, 0)) {
                player.setRotation(0);
                player.setLocation(player.getX() + 1, player.getY());
            }
            else if (key == VK_DOWN && rules.isLegal(player, 0, 1)) {
                player.setRotation(1);
                player.setLocation(player.getX(), player.getY() + 1);
            }
            else if (key == VK_LEFT && rules.isLegal(player, -1, 0)) {
                player.setRotation(2);
                player.setLocation(player.getX() - 1, player.getY());
            }
            else if (key == VK_UP && rules.isLegal(player, 0, -1)) {
                player.setRotation(3);
                player.setLocation(player.getX(), player.getY() - 1);
            }
            else {
                playSound("error");
                continue;
            }

            playSound("step");
        }
    }
}
