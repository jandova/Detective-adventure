package logika;

import java.util.Objects;

/**
 * Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumat.
 * Tato třída je součástí jednoduché textové hry.
 * Příkaz umožňuje hráči prozkoumat předmět v aktuálním prostoru.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */
public class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumat";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy PrikazProzkoumej.
     *
     * @param plan herní plán, ve kterém se hra odehrává
     */
    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provedPrikaz provádí příkaz "prozkoumat".
     *
     * @param parametry - jako parametr obsahuje název předmětu, který chcete prozkoumat
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        switch (parametry.length) {
            case 0:
                // Pokud hráč nezadá žádný parametr, vrátí se zpráva, že je potřeba zadat objekt k prozkoumání.
                return "Ke zkoumání je potřeba specifikovat objekt k prozkoumání.";
            case 1:
                String nazevVeci = parametry[0];
                if (plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
                    if (!Objects.equals(nazevVeci, "stojan_na_noze")) {
                        // Pokud předmět není stojan_na_noze, vrátí se zpráva, že ho není třeba prozkoumat.
                        return nazevVeci + " není třeba prozkoumat. \n" + "\n" + plan.getAktualniProstor().dlouhyPopis();
                    } else {
                        // Pokud je předmět stojan_na_noze, dojde ke splnění jedné z výherních podmínek (jeProzkoumano).
                        plan.setJeProzkoumano(true);
                        return "Prozkoumal jsi stojan_na_noze.\n" +
                                "Jeden z nožů chybí a v umyvadle také není. Mohl by být vražednou zbraní.\n" +
                                plan.getAktualniProstor().dlouhyPopis();
                    }
                } else {
                    // Pokud předmět není v aktuálním prostoru, vrátí se zpráva, že se nenachází v tomto prostoru.
                    return nazevVeci + " se nenachází v tomto prostoru.\n" + "\n" + plan.getAktualniProstor().dlouhyPopis();
                }
            default:
                // Pokud hráč zadá více než jeden parametr, vrátí se zpráva, že se lze prozkoumat pouze jeden předmět naráz.
                return "K prozkoumání předmětu se soustřeď pouze na jeden objekt.";
        }
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
