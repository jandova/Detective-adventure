package logika;

import java.util.Objects;

/**
 * Třída PrikazPoloz implementuje pro hru příkaz poloz.
 * Tato třída je součástí jednoduché textové hry.
 * Příkaz umožňuje hráči položit předmět z kufříku do aktuálního prostoru.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */
public class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "poloz";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy PrikazPoloz.
     *
     * @param plan herní plán, ve kterém se hra odehrává
     */
    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provedPrikaz provádí příkaz "poloz".
     *
     * @param parametry - jako parametr obsahuje název předmětu, který chcete položit
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // Pokud hráč nezadá žádný parametr, vrátí se zpráva, že musí upřesnit, jaký předmět chce položit.
            return "Musíte upřesnit, jaký předmět chcete položit.";
        }
        if (parametry.length > 1) {
            // Pokud hráč zadá více než jeden parametr, vrátí se zpráva, že může položit pouze jeden předmět naráz.
            return "Položit můžete pouze jeden předmět naráz.";
        }
        String nazevVeci = parametry[0];
        // Z kufříku se vyndá předmět se zadaným názvem.
        Vec pozadovanaVec = plan.getKufrik().vyndejZKufriku(nazevVeci);
        if (pozadovanaVec != null) {
            // Pokud je předmět v kufříku, vloží se do aktuálního prostoru.
            plan.getAktualniProstor().vlozVec(pozadovanaVec);
            if (Objects.equals(nazevVeci, "dopis_milenci")) {
                // Pokud hráč položí dopis milenci, dojde je splnění jedné z výherních podmínek (jeDopisVKufru)
                plan.setJeDopisVKufru(false);
            }
            return "Do místnosti jsi vložil " + nazevVeci + "." + "\n" + plan.getAktualniProstor().dlouhyPopis();
        }
        // Pokud předmět není v kufříku, vrátí se zpráva, že takový předmět nemá hráč v kufříku.
        return "Takový předmět v kufříku nemáš.";
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
