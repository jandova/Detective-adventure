package logika;

import java.util.Objects;

/**
 * Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 * Tato třída je součástí jednoduché textové hry.
 * Příkaz umožňuje hráči odemknout trezor, pokud má správný klíček.
 * Pokud je trezor úspěšně odemčen, hráč získá určité předměty z trezoru.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */
public class PrikazOdemkni implements IPrikaz {
    private static final String NAZEV = "odemkni";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy PrikazOdemkni.
     *
     * @param plan instance třídy HerniPlan, která uchovává herní stav
     */
    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provedPrikaz provádí příkaz "odemkni".
     *
     * @param parametry názvy předmětů, které hráč chce odemknout
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        switch (parametry.length) {
            case 0:
                // Pokud hráč nezadá žádný parametr, vrátí se zpráva, že je potřeba zadat předmět k odemčení.
                return "K odemčení je potřeba specifikovat předmět, který chcete odemknout.";
            case 1:
                // Pokud hráč zadal právě jeden parametr, zkontroluje se, zda je to trezor.
                String nazevVeci = parametry[0];
                if (plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
                    if ("trezor".equals(nazevVeci)) {
                        // Pokud hráč chce odemknout trezor, zkontroluje se, zda má klíček.
                        boolean obsahujeKlicek = false;
                        for (Vec vec : plan.getKufrik().getObsahKufriku()) {
                            if (vec.getNazev().equals("klicek")) {
                                obsahujeKlicek = true;
                                break;
                            }
                        }
                        if (obsahujeKlicek) {
                            // Pokud má hráč klíček, trezor se úspěšně odemkne.
                            // Z trezoru se do aktuálního prostoru přidá dopis milenci.
                            plan.getAktualniProstor().vlozVec(new Vec("dopis_milenci", true));
                            plan.getKufrik().vyndejZKufriku("klicek");
                            return "Odemknul jsi trezor.\nUvnitř jsou nějaké dokumenty a několik milostných dopisů oběti,\n" +
                                    "ve kterých autor přesvědčuje oběť k opuštění manžela.\n" +
                                    "V dopisu na vrchu oběť souhlasí.\n"
                                    + plan.getAktualniProstor().dlouhyPopis();

                        } else {
                            // Pokud hráč nemá klíček, trezor nelze odemknout.
                            return "K otevření trezoru je potřeba klíček.\n";
                        }
                    } else {
                        // Pokud hráč chce odemknout něco jiného než trezor, nelze to provést.
                        return "Tuto věc nelze odemknout. \n";
                    }
                } else {
                    // Pokud předmět, který hráč chce odemknout, není v aktuálním prostoru, vrátí se chybová zpráva.
                    return nazevVeci + " se nenachází v tomto prostoru.\n" + "\n" + plan.getAktualniProstor().dlouhyPopis();
                }
            default:
                // Pokud hráč zadá více než jeden parametr, vrátí se zpráva, že klíčkem lze odemknout pouze jeden objekt.
                return "Klíčkem lze odemknout pouze jeden objekt.\n";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo, které používá hráč pro jeho vyvolání).
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
