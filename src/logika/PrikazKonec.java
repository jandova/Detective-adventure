package logika;

/**
 * Třída PrikazKonec implementuje pro hru příkaz konec.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */

public class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";

    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param hra odkaz na hru, která má být příkazem konec ukončena
     */
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí (volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     *
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            // Pokud hráč zadá více než jedno slovo, vracíme mu zprávu, aby použil pouze slovo "konec"
            return "Chcete-li ukončit hru, zadejte příkaz: konec";
        } else {
            // Pokud hráč zadá pouze slovo "konec", nastavíme konec hry a vrátíme mu zprávu o ukončení hry
            hra.setKonecHry(true);
            return "Hra byla ukončena příkazem konec.";
        }
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
