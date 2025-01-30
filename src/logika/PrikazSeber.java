package logika;

import java.util.Objects;

/**
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 * Tato třída je součástí jednoduché textové hry.
 * Příkaz umožňuje hráči sebrat předmět v aktuálním prostoru a vložit ho do inventáře.
 *  * @author Olga Jandová
 *  * @version pro školní rok 2023/2024
 */
public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";

    private final HerniPlan plan;

    /**
     * Konstruktor třídy PrikazSeber.
     *
     * @param plan herní plán, ve kterém se hra odehrává
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provedPrikaz provádí příkaz "seber".
     *
     * @param parametry - jako parametr obsahuje název předmětu, který chcete sebrat
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // Pokud hráč nezadá žádný parametr, vrátí se zpráva, že je potřeba specifikovat, jaký předmět chcete sebrat.
            return "K sebrání předmětu je potřeba specifikovat, jaký předmět chcete sebrat.";
        }
        if (parametry.length > 1) {
            // Pokud hráč zadá více než jeden parametr, vrátí se zpráva, že lze sebrat pouze jeden předmět naráz.
            return "Naráz lze sebrat pouze jeden předmět.";
        }
        String nazevVeci = parametry[0];
        if (plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = plan.getAktualniProstor().vyberVec(nazevVeci);
            if (pozadovanaVec == null) {
                // Pokud se nepodaří sebrat předmět, vrátí se zpráva, že nelze sebrat.
                return nazevVeci + " nelze sebrat.";
            }

            boolean povedloSeVLozit = plan.getKufrik().vlozitDoKufriku(pozadovanaVec);
            if (povedloSeVLozit) {
                // Pokud se podaří vložit předmět do inventáře
                // jde-li o předmět otisky, splní se jedna z výherních podmínek (jeOtiskVKufru)
                // jde-li o předmět dopis_milenci splní se jedna z výherních podmínek (jeDopisVKufru)
                // jde-li o předmět Krevni_stopa splní se jedna z výherních podmínek (jeKrevniStopaVKufru)

                if (Objects.equals(nazevVeci, "dopis_milenci")) {
                    plan.setJeDopisVKufru(true);
                }
                if (Objects.equals(nazevVeci, "otisky")) {
                    plan.setJeOtiskVKufru(true);
                }
                if (Objects.equals(nazevVeci, "krevni_stopa")) {
                    plan.setJeKrevniStopaVKufru(true);
                }
                // Vrátí se zpráva o úspěšném sebrání předmětu.
                return "Sebral jsi " + nazevVeci + "." + "\n" + plan.getAktualniProstor().dlouhyPopis();
            }

            // Pokud se nepodari vlozit predmet do inventare, zkontroluje se, zda jde o predmet "mysteriozni_hmota".
            // Pokud ano, tak se hraci vlozi tento predmet do inventare, I POKUD ma inventar jiz plny.

            if (Objects.equals(nazevVeci, "mysteriozni_hmota")) {
                plan.getKufrik().vlozitDoKufriku(pozadovanaVec);
                // Vrátí se zpráva o úspěšném sebrání předmětu.
                return "Sebral jsi " + nazevVeci + "." + "\n" + plan.getAktualniProstor().dlouhyPopis();
            }

            // Pokud se nepodaří vložit předmět do inventáře, vrátí se zpráva o překročení kapacity kufříku, tedy inventáře.
            plan.getAktualniProstor().vlozVec(pozadovanaVec);
            return nazevVeci + " se do kufříku již nevejde.";
        }
        // Pokud předmět není v aktuálním prostoru, vrátí se zpráva, že se nenachází v tomto prostoru.
        return nazevVeci + " se nenachází v tomto prostoru.";
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro provedení příkazu).
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
