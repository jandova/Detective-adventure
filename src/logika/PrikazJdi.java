package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param hra instance třídy hry, která obsahuje i herní plán, ve kterém pak hráč chodí.
     */
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param parametry - jako parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // Pokud chybí druhé slovo (sousední prostor), vrací se chybová zpráva
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // Pokus přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            // Pokud zadaný směr nevede do sousedního prostoru, vrací se chybová zpráva
            return "Tam se odsud jít nedá.";
        } else {
            // Pokud směr vede do sousedního prostoru, provádí se logika, která sleduje, zda je prostorem výherní prostor
            if (parametry[0].equals(plan.getVyherniProstor().getNazev())) {
                // Pokud hráč zadal název výherního prostoru
                boolean jeProzkoumano = plan.isJeProzkoumano();
                boolean jeVyfoceno = plan.isJeVyfoceno();
                boolean jeOhledano = plan.isJeOhledano();
                boolean jeDopisVKufru = plan.isJeDopisVKufru();
                boolean jeOtiskVKufru = plan.isJeOtiskVKufru();
                boolean jeKrevniStopaVKufru = plan.isJeKrevniStopaVKufru();

                if (jeProzkoumano && jeVyfoceno && jeOhledano && jeDopisVKufru && jeOtiskVKufru && jeKrevniStopaVKufru) {
                    // Pokud jsou splněny všechny podmínky pro výhru
                    // konec hry
                    hra.setKonecHry(true);
                    hra.setEpilog("Tady máme hotovo. Zdá se, že šlo o vraždu ze žárlivosti.\n"
                            + "Poté, co manžel zjistil, že ho žena podvádí ji konfrontoval a hádka se zvrhnula.\n"
                            + "K potyčce došlo nejspíš v kuchyni, kde po eskalaci konfliktu,\n"
                            + "vzal útočník nůž ze stojanu.\n"
                            + "Oběť před útočníkem utíkala do obývacího pokoje, kde ji však dohnal a ubodal k smrti.");
                    // Ukončení hry
                    hra.setKonecHry(true);
                } else {
                    // Pokud některá podmínka pro výhru chybí, dojde k  vypsání, všech které chybí
                    String chybejiciPodminky = "Chybí následující podmínky pro zpřístunění prostoru 'stanice':\n";
                    if (!jeProzkoumano) {
                        chybejiciPodminky += "- Prozkoumej důkaz týkající se vražedné zbraně.\n";
                    }
                    if (!jeVyfoceno) {
                        chybejiciPodminky += "- Vyfoťte hlavní místo činu.\n";
                    }
                    if (!jeOhledano) {
                        chybejiciPodminky += "- Ohledejte tělo.\n";
                    }
                    if (!jeDopisVKufru) {
                        chybejiciPodminky += "- Najděte motiv vraždy.\n";
                    }
                    if (!jeOtiskVKufru) {
                        chybejiciPodminky += "- Najděte na místě činu otisky.\n";
                    }
                    if (!jeKrevniStopaVKufru) {
                        chybejiciPodminky += "- Najděte na místě činu krevní stopu.\n";
                    }
                    return chybejiciPodminky; // Vrátíme informaci o chybějících podmínkách
                }
            } else {
                // Pokud hráč nezadává směr výherního prostoru, přesune se do nového prostoru
                plan.setAktualniProstor(sousedniProstor);
                // Vrátí dlouhý popis nového prostoru
                return sousedniProstor.dlouhyPopis();
            }
        }
        return ""; // V případě jiného výsledku vracíme prázdný řetězec
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
