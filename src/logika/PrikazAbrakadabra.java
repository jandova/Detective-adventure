package logika;

/**
 * Třída PrikazAbrakadabra implementuje pro hru příkaz abrakadabra.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */
public class PrikazAbrakadabra implements IPrikaz {
    private static final String NAZEV = "abrakadabra";
    private HerniPlan plan;
    private Hra hra;

    private Prostor puvodniProstor;

    /**
     * Konstruktor třídy
     *
     * @param hra instance třídy hry, která obsahuje i herní plán, ve kterém pak hráč chodí.
     */
    public PrikazAbrakadabra(Hra hra) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
    }

    /**
     * Provádí příkaz "abrakadabra". Teleportuje hráče do kouzelneho prostoru,
     * pokud není uveden parametr, teleportuje do výchozího kouzelneho prostoru "narnie".
     *
     * @param parametry - jako parametr může obsahovat jméno prostoru (východu),
     *                  do kterého se má teleportovat.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            if (plan.getAktualniProstor().equals(plan.getKouzelnyProstor())) {
                // Pokud je hrac v kouzelnem prostoru vrati se do puvodniho prostoru
                plan.setAktualniProstor(puvodniProstor);
                return "Carymaryfuk! Vrátil jsi se do původního prostoru: " + puvodniProstor.getNazev() + "\n" + plan.getAktualniProstor().dlouhyPopis();
            } else {
                // Pokud neni hrac v kouzelnem prostoru bude do nej prenesen
                puvodniProstor = plan.getAktualniProstor();
                plan.setAktualniProstor(plan.getKouzelnyProstor());
                return "Abrakadabra! Byl jsi teleportován do prostoru: " + plan.getKouzelnyProstor().getNazev() + "\n" + plan.getAktualniProstor().dlouhyPopis();
            }
        }
        return "Do 'narnie' se můžeš dostat jen pomocí kouzelného slova (abrakadabra)";
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
