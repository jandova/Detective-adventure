package logika;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * <p>
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Olga Jandová
 * @version pro školní rok 2023/2024
 */
public class HerniPlan {
    private Prostor aktualniProstor;
    private Prostor vyherniProstor;

    private Prostor puvodniProstor;

    private Prostor kouzelnyProstor;
    private Kufrik kufrik;
    private static int OMEZENI_KUFRIKU = 4;

    // Stav různých událostí ve hře
    private boolean jeProzkoumano = false; // Zda je něco prozkoumáno
    private boolean jeVyfoceno = false; // Zda je něco vyfoceno
    private boolean jeOhledano = false; // Zda je něco ohledáno
    private boolean jeDopisVKufru = false; // Zda je dopis v kufru
    private boolean jeOtiskVKufru = false; // Zda je otisk v kufru
    private boolean jeKrevniStopaVKufru = false; // Zda je krevní stopa v kufru

    /**
     * Gettery a Settery pro stav různých událostí ve hře
     */
    public boolean isJeProzkoumano() {
        return jeProzkoumano;
    }

    public boolean isJeVyfoceno() {
        return jeVyfoceno;
    }

    public boolean isJeOhledano() {
        return jeOhledano;
    }

    public boolean isJeDopisVKufru() {
        return jeDopisVKufru;
    }

    public boolean isJeOtiskVKufru() {
        return jeOtiskVKufru;
    }

    public boolean isJeKrevniStopaVKufru() {
        return jeKrevniStopaVKufru;
    }

    public void setJeProzkoumano(boolean jeProzkoumano) {
        this.jeProzkoumano = jeProzkoumano;
    }

    public void setJeVyfoceno(boolean jeVyfoceno) {
        this.jeVyfoceno = jeVyfoceno;
    }

    public void setJeOhledano(boolean jeOhledano) {
        this.jeOhledano = jeOhledano;
    }

    public void setJeDopisVKufru(boolean jeDopisVKufru) {
        this.jeDopisVKufru = jeDopisVKufru;
    }

    public void setJeOtiskVKufru(boolean jeOtiskVKufru) {
        this.jeOtiskVKufru = jeOtiskVKufru;
    }

    public void setJeKrevniStopaVKufru(boolean jeKrevniStopaVKufru) {
        this.jeKrevniStopaVKufru = jeKrevniStopaVKufru;
    }

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
    }

    /**
     * Vytvoření jednotlivých prostor a jejich propojení pomocí východů.
     * Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // Vytvoření jednotlivých prostor
        Prostor stanice = new Prostor("stanice", "vyhra");
        Prostor chodba = new Prostor("chodba", "chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky");
        Prostor predsin = new Prostor("predsin", "předsíň.\nNa věšáku visí dámské kabáty a pánské koženky.\nOběť tedy žila s mužem");
        Prostor kuchyne = new Prostor("kuchyne", "kuchyně, která působí poházeně.\nVypadá to, že se zde odehrálo něco chaotického");
        Prostor obyvak = new Prostor("obyvak", "obývák, který naplňuje puch.\nU pohovky leží tělo oběti a vypadá to,\nže při zápasu byly z konferenčního stolku shozeny všechny věci");
        Prostor loznice = new Prostor("loznice", "ložnice, která vypadá nedotčeně.\nJe tu jenom menší převrácený trezor");
        Prostor pracovna = new Prostor("pracovna", "pracovna.\nVypadá to, že se někdo prohrabával v šuplících pracovního stolu.\nAsi tu oběť nebo útočník něco hledal");
        Prostor narnie = new Prostor("narnie", "tohle je kouzelna mistnost");

        // Propojení prostorů pomocí východů
        chodba.setVychod(predsin);

//        chodba.setVychod(narnie);

        chodba.setVychod(stanice);
        predsin.setVychod(chodba);
        predsin.setVychod(kuchyne);
        predsin.setVychod(obyvak);
        predsin.setVychod(loznice);
        kuchyne.setVychod(predsin);
        obyvak.setVychod(predsin);
        obyvak.setVychod(pracovna);
        pracovna.setVychod(obyvak);
        loznice.setVychod(predsin);

        // Vložení předmětů do jednotlivých prostorů
        chodba.vlozVec(new Vec("otisky", true));
        predsin.vlozVec(new Vec("krevni_stopa", true));
        obyvak.vlozVec(new Vec("telo", false));
        obyvak.vlozVec(new Vec("blok", true));
        obyvak.vlozVec(new Vec("svicka", true));
        obyvak.vlozVec(new Vec("tuzka", true));
        kuchyne.vlozVec(new Vec("stojan_na_noze", false));
        pracovna.vlozVec(new Vec("klicek", true));
        loznice.vlozVec(new Vec("trezor", false));

        narnie.vlozVec(new Vec("klic", true));
        narnie.vlozVec(new Vec("truhla", false));

        // Nastavení výchozího prostoru po startu hry
        aktualniProstor = chodba;
        // Nastavení výherního prostoru, po jehož dosažení hráč vyhraje hru
        vyherniProstor = stanice;
        // Vytvoření inventáře Kufřík
        kufrik = new Kufrik(OMEZENI_KUFRIKU);

        kouzelnyProstor = narnie;

    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     *
     * @return aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, nejčastěji se používá při přechodu mezi prostory.
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {

        aktualniProstor = prostor;
        puvodniProstor = aktualniProstor;
    }

    /**
     * Metoda pro získání výherního prostoru.
     *
     * @return výherní prostor
     */
    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }
    public Prostor getKouzelnyProstor() {
        return kouzelnyProstor;
    }



    /**
     * Metoda pro získání inventáře hráče (Kufříku).
     *
     * @return inventář hráče
     */
    public Kufrik getKufrik() {
        return kufrik;
    }


}
