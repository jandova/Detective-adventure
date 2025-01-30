package logika;

import java.util.ArrayList;
import java.util.List;
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
public class PrikazOdemkniTruhlu implements IPrikaz {
    private static final String NAZEV = "odemkniTruhlu";
    private final HerniPlan plan;

    private List<Vec> truhla;

    /**
     * Konstruktor třídy PrikazOdemkni.
     *
     * @param plan instance třídy HerniPlan, která uchovává herní stav
     */
    public PrikazOdemkniTruhlu(HerniPlan plan) {
        this.plan = plan;
        this.truhla = new ArrayList<>();
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
                    if ("truhla".equals(nazevVeci)) {
                        // Pokud hráč chce odemknout trezor, zkontroluje se, zda má klíček.
                        boolean obsahujeKlic = false;
                        for (Vec vec : plan.getKufrik().getObsahKufriku()) {
                            if (vec.getNazev().equals("klic")) {
                                obsahujeKlic = true;
                                break;
                            }
                        }
                        if (obsahujeKlic) {
                            // Pokud má hráč klíček, trezor se úspěšně odemkne.
                            // Z trezoru se do aktuálního prostoru přidaji predemety z truhly.

                            plan.getAktualniProstor().vlozVec(new Vec("kralik", true));
                            plan.getAktualniProstor().vlozVec(new Vec("fazolka", true));
                            plan.getAktualniProstor().vlozVec(new Vec("strevic", true));
                            plan.getAktualniProstor().vlozVec(new Vec("klobouk", true));
                            plan.getAktualniProstor().vlozVec(new Vec("koruna", true));
                            plan.getAktualniProstor().vlozVec(new Vec("mysteriozni_hmota", true));
                            plan.getKufrik().vyndejZKufriku("klic");
                            return "Odemknul jsi truhlu.\nvem si co potrebujes\n"
                                    + plan.getAktualniProstor().dlouhyPopis();

                        } else {
                            // Pokud hráč nemá klíček, trezor nelze odemknout.
                            return "K otevření truhly je potřeba klic.\n";
                        }
                    } else {
                        // Pokud hráč chce odemknout něco jiného než trezor, nelze to provést.
                        return "Tuto věc nelze pomoci klice odemknout. \n";
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
