package BruchrechnerView;

public class Calculator {

    // Methode zur Addition von Brüchen
    public int[] addFractions(int z1, int n1, int z2, int n2) {
        int ergebnisZaehler = z1 * n2 + z2 * n1;
        int ergebnisNenner = n1 * n2;
        return kuerzeBruch(ergebnisZaehler, ergebnisNenner);
    }

    // Methode zur Subtraktion von Brüchen
    public int[] subtractFractions(int z1, int n1, int z2, int n2) {
        int ergebnisZaehler = z1 * n2 - z2 * n1;
        int ergebnisNenner = n1 * n2;
        return kuerzeBruch(ergebnisZaehler, ergebnisNenner);
    }

    // Methode zur Multiplikation von Brüchen
    public int[] multiplyFractions(int z1, int n1, int z2, int n2) {
        int ergebnisZaehler = z1 * z2;
        int ergebnisNenner = n1 * n2;
        return kuerzeBruch(ergebnisZaehler, ergebnisNenner);
    }

    // Methode zur Division von Brüchen
    public int[] divideFractions(int z1, int n1, int z2, int n2) {
        if (z2 == 0) {
            throw new ArithmeticException("Division durch Null ist nicht erlaubt.");
        }
        int ergebnisZaehler = z1 * n2;
        int ergebnisNenner = n1 * z2;
        return kuerzeBruch(ergebnisZaehler, ergebnisNenner);
    }

    // Methode zur Kürzung des Bruchs (GGT verwenden)
    private int[] kuerzeBruch(int zaehler, int nenner) {
        int gcd = ggt(zaehler, nenner);
        zaehler /= gcd;
        nenner /= gcd;

        // Sicherstellen, dass der Nenner positiv ist
        if (nenner < 0) {
            zaehler *= -1;
            nenner *= -1;
        }

        return new int[]{zaehler, nenner};
    }

    // Methode zur Berechnung des größten gemeinsamen Teilers (GGT)
    private int ggt(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return ggt(b, a % b);
    }
}
