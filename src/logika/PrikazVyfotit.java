package logika;

import java.util.Objects;

/**
 * Třída PrikazVyfotit implementuje pro hru příkaz vyfotit.
 * Tato třída je součástí jednoduché textové hry.
 * Příkaz umožňuje hráči vyfotit předmět v aktuálním prostoru.
 *  * @author Olga Jandová
 *  * @version pro školní rok 2023/2024
 */
public class PrikazVyfotit implements IPrikaz {
    private static final String NAZEV = "vyfotit";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy PrikazVyfotit.
     *
     * @param plan herní plán, ve kterém se hra odehrává
     */
    public PrikazVyfotit(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provedPrikaz provádí příkaz "vyfotit".
     *
     * @param parametry - jako parametr obsahuje název předmětu, který chcete vyfotit
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        switch (parametry.length) {
            case 0:
                // Pokud hráč nezadá žádný parametr, vrátí se zpráva, že je třeba specifikovat, jaký předmět chcete fotit.
                return "K focení je třeba specifikovat, jaký předmět chcete fotit.\n" + plan.getAktualniProstor().dlouhyPopis();
            case 1:
                String nazevVeci = parametry[0];
                if (plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
                    if (!nazevVeci.equals(("telo"))) {
                        // Pokud předmět není třeba vyfotit, vrátí se zpráva.
                        return nazevVeci + " není třeba vyfotit. \n" + plan.getAktualniProstor().dlouhyPopis();
                    } else {
                        // Pokud se jedná o správný předmět k vyfocení, aktualizuje se příznak ve hře a vrátí se zpráva o úspěšném vyfocení.
                        plan.setJeVyfoceno(true);
                        return "Vyfotil jsi hlavní místo činu." + "\n" + plan.getAktualniProstor().dlouhyPopis();
                    }
                } else {
                    // Pokud předmět není v aktuálním prostoru, vrátí se zpráva.
                    return nazevVeci + " se nenachází v tomto prostoru.\n" + plan.getAktualniProstor().dlouhyPopis();
                }
            default:
                // Pokud hráč zadá více než jeden parametr, vrátí se zpráva.
                return "Do fotografie musí obsahovat pouze jeden důkazní předmět naráz.\n" + plan.getAktualniProstor().dlouhyPopis();
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
