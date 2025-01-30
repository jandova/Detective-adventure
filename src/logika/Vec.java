package logika;

/**
 * Třída Vec představuje objekt, který může být součástí herního světa.
 * Jedná se například o předměty, které je možné v herním prostoru najít a použít.
 */
public class Vec {
    private String nazev; // název předmětu
    private boolean prenositelnost; // určuje, zda je předmět přenositelný

    /**
     * Konstruktor pro vytvoření instance třídy Vec.
     * @param nazev název předmětu
     * @param prenositelnost true, pokud je předmět přenositelný, jinak false
     */
    public Vec(String nazev, boolean prenositelnost) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
    }

    /**
     * Metoda pro získání názvu předmětu.
     * @return název předmětu
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Metoda pro zjištění, zda je předmět přenositelný.
     * @return true, pokud je předmět přenositelný, jinak false
     */
    public boolean jePrenositelna() {
        return prenositelnost;
    }
}