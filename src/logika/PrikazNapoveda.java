package logika;

/**
 * Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */
public class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;

    /**
     * Konstruktor třídy
     *
     * @param platnePrikazy seznam příkazů,
     *                      které je možné ve hře použít,
     *                      aby je nápověda mohla zobrazit uživateli.
     */
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     * vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     * @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Vaším úkolem je zajistit důkazy, prozkoumat podezřelé jevy,\n"
                + "vyfotografovat hlavní misto činu, tedy tělo oběti,\nkteré je také třeba ohledat, a zjistit motiv útočníka.\n"
                + "\n"
                + "Můžeš zadat tyto příkazy: "
                + platnePrikazy.vratNazvyPrikazu();
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
