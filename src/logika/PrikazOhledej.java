package logika;

import java.util.Objects;

/**
 * Třída PrikazOhledej implementuje pro hru příkaz ohledat.
 * Tato třída je součástí jednoduché textové hry.
 * Příkaz umožňuje hráči ohledat určitý předmět v aktuálním prostoru.
 * Pokud se podaří ohledat určitý předmět, hráč získá důležité informace o něm.
 * V této hře může hráč ohledat pouze tělo oběti.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */
public class PrikazOhledej implements IPrikaz {
    private static final String NAZEV = "ohledat";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy PrikazOhledej.
     *
     * @param plan instance třídy HerniPlan, která uchovává herní stav
     */
    public PrikazOhledej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda provedPrikaz provádí příkaz "ohledat".
     *
     * @param parametry názvy předmětů, které hráč chce ohledat
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        switch (parametry.length) {
            case 0:
                // Pokud hráč nezadá žádný parametr, vrátí se zpráva, že je potřeba zadat, co chce ohledat.
                return "K ohledání je potřeba specifikovat, co chcete ohledat.";
            case 1:
                // Pokud hráč zadal právě jeden parametr, zkontroluje se, zda parametrem telo.
                String nazevVeci = parametry[0];
                if (plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
                    if (!Objects.equals(nazevVeci, "telo")) {
                        // Pokud hráč chce ohledat něco jiného než tělo, nelze to provést.
                        return "Takový předmět nelze ohledat. \n";
                    } else {
                        // Pokud hráč ohledá tělo, získá důležité informace o oběti a splní se jedna z výherních podmínek (jeOhledano).
                        plan.setJeOhledano(true);
                        return "Ohledal jsi tělo." + "\n" + "Obětí je žena středního věku. Na krku a okolo zápěstí má viditelné kontuze.\nNa hrudníku má četné bodné rány a její ruce vykazují známky zápasu.\n"
                                +  plan.getAktualniProstor().dlouhyPopis();
                    }
                } else {
                    // Pokud předmět, který hráč chce ohledat, není v aktuálním prostoru, vrátí se chybová zpráva.
                    return nazevVeci + " se nenachází v tomto prostoru.\n" + "\n" + plan.getAktualniProstor().dlouhyPopis();
                }
            default:
                // Pokud hráč zadá více než jeden parametr, vrátí se zpráva, že při ohledávání je potřeba zadat pouze jeden předmět.
                return "Při ohledávání je třeba se soustředit pouze na jeden předmět k ohledání." + "\n" + plan.getAktualniProstor().dlouhyPopis();
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
