import logika.IHra;
import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HraTest {

    private IHra hra;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
    }

    @AfterEach
    public void tearDown() {
        hra = null;
    }

    @Test
    public void testHry() {
        //uvitani do hry
        assertEquals("Vítejte na místě činu, detektive.\n" +
                "Vaším úkolem je prozkoumat a sesbírat důkazy a vyfotit a ohledat tělo.\n" +
                "Až zajistíte důkazy, opusťte místo činu a přenechejte ho kriminální laboratoři.\n" +
                "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
                "\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.vratUvitani());

        //1 krok seber otisky
        assertEquals("Sebral jsi otisky.\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: \n" +
                "Obsah kufříku: otisky ", hra.zpracujPrikaz("seber otisky"));

        //2 krok jdi predsin
        assertEquals("Jsi v místnosti předsíň.\nNa věšáku visí dámské kabáty a pánské koženky.\nOběť tedy žila s mužem.\n" +
                "Východy: chodba kuchyne loznice obyvak\n" +
                "Předměty v místnosti: krevni_stopa \n" +
                "Obsah kufříku: otisky ", hra.zpracujPrikaz("jdi predsin"));

        //3 krok seber krevni_stopa
        assertEquals("Sebral jsi krevni_stopa.\n" +
                "Jsi v místnosti předsíň.\nNa věšáku visí dámské kabáty a pánské koženky.\nOběť tedy žila s mužem.\n" +
                "Východy: chodba kuchyne loznice obyvak\n" +
                "Předměty v místnosti: \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("seber krevni_stopa"));

        //4 krok jdi kuchyne
        assertEquals("Jsi v místnosti kuchyně, která působí poházeně.\nVypadá to, že se zde odehrálo něco chaotického.\n" +
                "Východy: predsin\n" +
                "Předměty v místnosti: stojan_na_noze \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("jdi kuchyne"));

        //5 krok prozkoumat stojan
        assertEquals("Prozkoumal jsi stojan_na_noze.\n" +
                "Jeden z nožů chybí a v umyvadle také není. Mohl by být vražednou zbraní.\n" +
                "Jsi v místnosti kuchyně, která působí poházeně.\nVypadá to, že se zde odehrálo něco chaotického.\n" +
                "Východy: predsin\n" +
                "Předměty v místnosti: stojan_na_noze \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("prozkoumat stojan_na_noze"));

        //6 krok jdi predsin
        assertEquals("Jsi v místnosti předsíň.\nNa věšáku visí dámské kabáty a pánské koženky.\nOběť tedy žila s mužem.\n" +
                "Východy: chodba kuchyne loznice obyvak\n" +
                "Předměty v místnosti: \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("jdi predsin"));

        //7 krok jdi obyvak
        assertEquals("Jsi v místnosti obývák, který naplňuje puch.\nU pohovky leží tělo oběti a vypadá to,\nže při zápasu byly z konferenčního stolku shozeny všechny věci.\n" +
                "Východy: predsin pracovna\n" +
                "Předměty v místnosti: telo blok svicka tuzka \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("jdi obyvak"));

        //8 krok vyfotit telo
        assertEquals("Vyfotil jsi hlavní místo činu.\n" +
                "Jsi v místnosti obývák, který naplňuje puch.\nU pohovky leží tělo oběti a vypadá to,\nže při zápasu byly z konferenčního stolku shozeny všechny věci.\n" +
                "Východy: predsin pracovna\n" +
                "Předměty v místnosti: telo blok svicka tuzka \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("vyfotit telo"));

        //9 krok ohledat telo
        assertEquals("Ohledal jsi tělo.\n" +
                "Obětí je žena středního věku. Na krku a okolo zápěstí má viditelné kontuze.\nNa hrudníku má četné bodné rány a její ruce vykazují známky zápasu.\n" +
                "Jsi v místnosti obývák, který naplňuje puch.\nU pohovky leží tělo oběti a vypadá to,\nže při zápasu byly z konferenčního stolku shozeny všechny věci.\n" +
                "Východy: predsin pracovna\n" +
                "Předměty v místnosti: telo blok svicka tuzka \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("ohledat telo"));

        //10 krok jdi pracovna
        assertEquals("Jsi v místnosti pracovna.\nVypadá to, že se někdo prohrabával v šuplících pracovního stolu.\nAsi tu oběť nebo útočník něco hledal.\n" +
                "Východy: obyvak\n" +
                "Předměty v místnosti: klicek \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("jdi pracovna"));

        //11 krok seber klicek
        assertEquals("Sebral jsi klicek.\n" +
                "Jsi v místnosti pracovna.\nVypadá to, že se někdo prohrabával v šuplících pracovního stolu.\nAsi tu oběť nebo útočník něco hledal.\n" +
                "Východy: obyvak\n" +
                "Předměty v místnosti: \n" +
                "Obsah kufříku: otisky krevni_stopa klicek ", hra.zpracujPrikaz("seber klicek"));

        //12 krok jdi obyvak
        assertEquals("Jsi v místnosti obývák, který naplňuje puch.\nU pohovky leží tělo oběti a vypadá to,\nže při zápasu byly z konferenčního stolku shozeny všechny věci.\n" +
                "Východy: predsin pracovna\n" +
                "Předměty v místnosti: telo blok svicka tuzka \n" +
                "Obsah kufříku: otisky krevni_stopa klicek ", hra.zpracujPrikaz("jdi obyvak"));

        //13 krok jdi predsin
        assertEquals("Jsi v místnosti předsíň.\nNa věšáku visí dámské kabáty a pánské koženky.\nOběť tedy žila s mužem.\n" +
                "Východy: chodba kuchyne loznice obyvak\n" +
                "Předměty v místnosti: \n" +
                "Obsah kufříku: otisky krevni_stopa klicek ", hra.zpracujPrikaz("jdi predsin"));

        //14 krok jdi loznice
        assertEquals("Jsi v místnosti ložnice, která vypadá nedotčeně.\nJe tu jenom menší převrácený trezor.\n" +
                "Východy: predsin\n" +
                "Předměty v místnosti: trezor \n" +
                "Obsah kufříku: otisky krevni_stopa klicek ", hra.zpracujPrikaz("jdi loznice"));

        //15 krok odemkni trezor
        assertEquals("Odemknul jsi trezor.\nUvnitř jsou nějaké dokumenty a několik milostných dopisů oběti,\n" +
                "ve kterých autor přesvědčuje oběť k opuštění manžela.\n" +
                "V dopisu na vrchu oběť souhlasí.\n" +
                "Jsi v místnosti ložnice, která vypadá nedotčeně.\nJe tu jenom menší převrácený trezor.\n" +
                "Východy: predsin\n" +
                "Předměty v místnosti: trezor dopis_milenci \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("odemkni trezor"));

        //16 krok seber dopis_milenci
        assertEquals("Sebral jsi dopis_milenci.\n" +
                "Jsi v místnosti ložnice, která vypadá nedotčeně.\nJe tu jenom menší převrácený trezor.\n" +
                "Východy: predsin\n" +
                "Předměty v místnosti: trezor \n" +
                "Obsah kufříku: otisky krevni_stopa dopis_milenci ", hra.zpracujPrikaz("seber dopis_milenci"));

        //17 krok jdi predsin
        assertEquals("Jsi v místnosti předsíň.\nNa věšáku visí dámské kabáty a pánské koženky.\nOběť tedy žila s mužem.\n" +
                "Východy: chodba kuchyne loznice obyvak\n" +
                "Předměty v místnosti: \n" +
                "Obsah kufříku: otisky krevni_stopa dopis_milenci ", hra.zpracujPrikaz("jdi predsin"));

        //18 krok jdi chodba
        assertEquals("Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: \n" +
                "Obsah kufříku: otisky krevni_stopa dopis_milenci ", hra.zpracujPrikaz("jdi chodba"));

        //19 krok jdi stanice
       assertEquals("\nObsah kufříku: otisky krevni_stopa dopis_milenci ", hra.zpracujPrikaz("jdi stanice"));

        assertTrue(hra.konecHry());
        assertEquals("Tady máme hotovo. Zdá se, že šlo o vraždu ze žárlivosti.\n"
                + "Poté, co manžel zjistil, že ho žena podvádí ji konfrontoval a hádka se zvrhnula.\n"
                + "K potyčce došlo nejspíš v kuchyni, kde po eskalaci konfliktu,\nvzal útočník nůž ze stojanu.\n"
                + "Oběť před útočníkem utíkala do obývacího pokoje, kde ji však dohnal a ubodal k smrti.", hra.vratEpilog());
    }


    @Test
    public void testUvitani() {
        //uvitani do hry
        assertEquals("Vítejte na místě činu, detektive.\n" +
                "Vaším úkolem je prozkoumat a sesbírat důkazy a vyfotit a ohledat tělo.\n" +
                "Až zajistíte důkazy, opusťte místo činu a přenechejte ho kriminální laboratoři.\n" +
                "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
                "\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.vratUvitani());

    }

    @Test
    public void testVyhry() {
        //vstup do vyherni mistnosti po splneni vyhernich podminek
        hra.zpracujPrikaz("seber otisky");
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("seber krevni_stopa");
        hra.zpracujPrikaz("jdi kuchyne");
        hra.zpracujPrikaz("prozkoumat stojan_na_noze");
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi obyvak");
        hra.zpracujPrikaz("vyfotit telo");
        hra.zpracujPrikaz("ohledat telo");
        hra.zpracujPrikaz("jdi pracovna");
        hra.zpracujPrikaz("seber klicek");
        hra.zpracujPrikaz("jdi obyvak");
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi loznice");
        hra.zpracujPrikaz("odemkni trezor");
        hra.zpracujPrikaz("seber dopis_milenci");
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi chodba");
        assertEquals("\nObsah kufříku: otisky krevni_stopa dopis_milenci ", hra.zpracujPrikaz("jdi stanice"));

        assertTrue(hra.konecHry());
        assertEquals("Tady máme hotovo. Zdá se, že šlo o vraždu ze žárlivosti.\n"
                + "Poté, co manžel zjistil, že ho žena podvádí ji konfrontoval a hádka se zvrhnula.\n"
                + "K potyčce došlo nejspíš v kuchyni, kde po eskalaci konfliktu,\nvzal útočník nůž ze stojanu.\n"
                + "Oběť před útočníkem utíkala do obývacího pokoje, kde ji však dohnal a ubodal k smrti.", hra.vratEpilog());
    }

    @Test
    public void testNapoveda() {
        assertEquals("Vaším úkolem je zajistit důkazy, prozkoumat podezřelé jevy,\n"
                + "vyfotografovat hlavní misto činu, tedy tělo oběti,\nkteré je také třeba ohledat, a zjistit motiv útočníka.\n"
                + "\n"
                + "Můžeš zadat tyto příkazy: napoveda odemkniTruhlu odemkni prozkoumat poloz vyfotit ohledat jdi seber abrakadabra konec \n"
                + "Obsah kufříku: ", hra.zpracujPrikaz("napoveda"));
    }

    @Test
    public void testProzkoumat() {
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi kuchyne");
        assertEquals("Prozkoumal jsi stojan_na_noze.\n" +
                "Jeden z nožů chybí a v umyvadle také není. Mohl by být vražednou zbraní.\n" +
                "Jsi v místnosti kuchyně, která působí poházeně.\nVypadá to, že se zde odehrálo něco chaotického.\n" +
                "Východy: predsin\n" +
                "Předměty v místnosti: stojan_na_noze \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("prozkoumat stojan_na_noze"));

    }

    @Test
    public void testProzkoumatNestojan() {
        assertEquals("otisky není třeba prozkoumat. \n" + "\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("prozkoumat otisky"));
    }

    @Test
    public void testProzkoumatPredmetMimoMistnost() {
        assertEquals("telo se nenachází v tomto prostoru.\n" + "\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("ohledat telo"));
    }

    @Test
    public void testProzkoumatVicePredmetu() {
        assertEquals("K prozkoumání předmětu se soustřeď pouze na jeden objekt.\n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("prozkoumat telo tuzka"));
    }

    @Test
    public void testProzkoumatBezPredmetu() {
        assertEquals("Ke zkoumání je potřeba specifikovat objekt k prozkoumání.\n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("prozkoumat"));
    }

    @Test
    public void testVyfotit() {
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi obyvak");
        assertEquals("Vyfotil jsi hlavní místo činu.\n" +
                "Jsi v místnosti obývák, který naplňuje puch.\nU pohovky leží tělo oběti a vypadá to,\nže při zápasu byly z konferenčního stolku shozeny všechny věci.\n" +
                "Východy: predsin pracovna\n" +
                "Předměty v místnosti: telo blok svicka tuzka \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("vyfotit telo"));
    }

    @Test
    public void testVyfotitNeTelo() {
        assertEquals("otisky není třeba vyfotit. \n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("vyfotit otisky"));
    }

    @Test
    public void testVyfotitPredmetMimoMistnost() {
        assertEquals("telo se nenachází v tomto prostoru.\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("vyfotit telo"));
    }

    @Test
    public void testVyfotitVicePredmetu() {
        assertEquals("Do fotografie musí obsahovat pouze jeden důkazní předmět naráz.\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("vyfotit telo tuzka"));
    }

    @Test
    public void testVyfotitBezPredmetu() {
        assertEquals("K focení je třeba specifikovat, jaký předmět chcete fotit.\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("vyfotit"));
    }

    @Test
    public void testOhledat() {
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi obyvak");
        assertEquals("Ohledal jsi tělo.\n" +
                "Obětí je žena středního věku. Na krku a okolo zápěstí má viditelné kontuze.\nNa hrudníku má četné bodné rány a její ruce vykazují známky zápasu.\n" +
                "Jsi v místnosti obývák, který naplňuje puch.\nU pohovky leží tělo oběti a vypadá to,\nže při zápasu byly z konferenčního stolku shozeny všechny věci.\n" +
                "Východy: predsin pracovna\n" +
                "Předměty v místnosti: telo blok svicka tuzka \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("ohledat telo"));
    }

    @Test
    public void testOhledatNeohledatelnouVec() {
        assertEquals("Takový předmět nelze ohledat. \n" + "\n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("ohledat otisky"));
    }

    @Test
    public void testOhledatPredmetMimoMistnost() {
        assertEquals("telo se nenachází v tomto prostoru.\n" + "\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("ohledat telo"));
    }

    @Test
    public void testOhledatVicePredmetu() {
        assertEquals("Při ohledávání je třeba se soustředit pouze na jeden předmět k ohledání.\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("ohledat telo tuzka"));
    }

    @Test
    public void testOhledatBezPredmetu() {
        assertEquals("K ohledání je potřeba specifikovat, co chcete ohledat.\n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("ohledat"));
    }
    //abc

    @Test
    public void testOdemkni() {
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi obyvak");
        hra.zpracujPrikaz("jdi pracovna");
        hra.zpracujPrikaz("seber klicek");
        hra.zpracujPrikaz("jdi obyvak");
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi loznice");
        assertEquals("Odemknul jsi trezor.\nUvnitř jsou nějaké dokumenty a několik milostných dopisů oběti,\n" +
                "ve kterých autor přesvědčuje oběť k opuštění manžela.\n" +
                "V dopisu na vrchu oběť souhlasí.\n" +
                "Jsi v místnosti ložnice, která vypadá nedotčeně.\n" +
                "Je tu jenom menší převrácený trezor.\n" +
                "Východy: predsin\n" +
                "Předměty v místnosti: trezor dopis_milenci \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("odemkni trezor"));
    }


    @Test
    public void testSeber() {
        hra.zpracujPrikaz("seber otisky");
        hra.zpracujPrikaz("jdi predsin");

        assertEquals("Sebral jsi krevni_stopa.\n" +
                "Jsi v místnosti předsíň.\nNa věšáku visí dámské kabáty a pánské koženky.\nOběť tedy žila s mužem.\n" +
                "Východy: chodba kuchyne loznice obyvak\n" +
                "Předměty v místnosti: \n" +
                "Obsah kufříku: otisky krevni_stopa ", hra.zpracujPrikaz("seber krevni_stopa"));
    }

    @Test
    public void testSeberNeprenositelnouVec() {
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("jdi loznice");
        assertEquals("trezor nelze sebrat.\n" + "Obsah kufříku: ", hra.zpracujPrikaz("seber trezor"));
    }

    @Test
    public void testPoloz() {
        hra.zpracujPrikaz("seber otisky");
        assertEquals("Do místnosti jsi vložil otisky.\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("poloz otisky"));
    }

    //testuje prekroceni kapacity inventare
    @Test
    public void testPlnyInventar() {
        hra.zpracujPrikaz("seber otisky");
        hra.zpracujPrikaz("jdi predsin");
        hra.zpracujPrikaz("seber krevni_stopa");
        hra.zpracujPrikaz("jdi obyvak");
        hra.zpracujPrikaz("seber blok");
        hra.zpracujPrikaz("seber svicka");
//        hra.zpracujPrikaz("seber tuzka");
        hra.zpracujPrikaz("jdi pracovna");
        assertEquals("klicek se do kufříku již nevejde.\n" +
                "Obsah kufříku: otisky krevni_stopa blok svicka ", hra.zpracujPrikaz("seber klicek"));
    }

    @Test
    public void testPrekroceniOmezeniInventareMysterioznimPredmetem() {
        hra.zpracujPrikaz("abrakadabra");
        hra.zpracujPrikaz("seber klic");
        hra.zpracujPrikaz("odemkniTruhlu truhla");
        hra.zpracujPrikaz("seber fazolka");
        hra.zpracujPrikaz("seber strevic");
        hra.zpracujPrikaz("seber klobouk");
        hra.zpracujPrikaz("seber kralik");
        hra.zpracujPrikaz("seber koruna");
        assertEquals("koruna se do kufříku již nevejde.\n" +
                "Obsah kufříku: fazolka strevic klobouk kralik ", hra.zpracujPrikaz("seber koruna"));

        assertEquals("Sebral jsi mysteriozni_hmota.\n" +
                "Jsi v místnosti tohle je kouzelna mistnost.\n" +
                "Východy:\n" +
                "Předměty v místnosti: truhla koruna \n" +
                "Obsah kufříku: fazolka strevic klobouk kralik mysteriozni_hmota ", hra.zpracujPrikaz("seber mysteriozni_hmota"));
    }

    @Test
    public void testPruchoduAbrakadabra() {
        assertEquals("Abrakadabra! Byl jsi teleportován do prostoru: narnie\n" +
                "Jsi v místnosti tohle je kouzelna mistnost.\n" +
                "Východy:\n" +
                "Předměty v místnosti: klic truhla \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("abrakadabra"));
        assertEquals("Carymaryfuk! Vrátil jsi se do původního prostoru: chodba\n" +
                "Jsi v místnosti chodba před bytem oběti.\nDveře do bytu jsou nepoškozené, takže by na klice mohly být otisky.\n" +
                "Východy: stanice predsin\n" +
                "Předměty v místnosti: otisky \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("abrakadabra"));
        hra.zpracujPrikaz("abrakadabra");
        assertEquals("Sebral jsi klic.\n" +
                "Jsi v místnosti tohle je kouzelna mistnost.\n" +
                "Východy:\n" +
                "Předměty v místnosti: truhla \n" +
                "Obsah kufříku: klic ", hra.zpracujPrikaz("seber klic"));
        assertEquals("Odemknul jsi truhlu.\nvem si co potrebujes\n" +
                "Jsi v místnosti tohle je kouzelna mistnost.\n" +
                "Východy:\n" +
                "Předměty v místnosti: truhla kralik fazolka strevic klobouk koruna mysteriozni_hmota \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("odemkniTruhlu truhla"));
        hra.zpracujPrikaz("seber kralik");
        hra.zpracujPrikaz("seber fazolka");
        hra.zpracujPrikaz("seber strevic");
        hra.zpracujPrikaz("seber klobouk");

        assertEquals("koruna se do kufříku již nevejde.\n" +
                "Obsah kufříku: kralik fazolka strevic klobouk ", hra.zpracujPrikaz("seber koruna"));

        assertEquals("Sebral jsi mysteriozni_hmota.\n" +
                "Jsi v místnosti tohle je kouzelna mistnost.\n" +
                "Východy:\n" +
                "Předměty v místnosti: truhla koruna \n" +
                "Obsah kufříku: kralik fazolka strevic klobouk mysteriozni_hmota ", hra.zpracujPrikaz("seber mysteriozni_hmota"));

    }

    @Test
    public void testTruhly() {
        hra.zpracujPrikaz("abrakadabra");
        assertEquals("K otevření truhly je potřeba klic.\n" +
                "\nObsah kufříku: ", hra.zpracujPrikaz("odemkniTruhlu truhla"));
        hra.zpracujPrikaz("seber klic");
        assertEquals("Odemknul jsi truhlu.\n" +
                "vem si co potrebujes\n" +
                "Jsi v místnosti tohle je kouzelna mistnost.\n" +
                "Východy:\n" +
                "Předměty v místnosti: truhla kralik fazolka strevic klobouk koruna mysteriozni_hmota \n" +
                "Obsah kufříku: ", hra.zpracujPrikaz("odemkniTruhlu truhla"));

        assertEquals("Sebral jsi mysteriozni_hmota.\n" +
                "Jsi v místnosti tohle je kouzelna mistnost.\n" +
                "Východy:\n" +
                "Předměty v místnosti: truhla kralik fazolka strevic klobouk koruna \n" +
                "Obsah kufříku: mysteriozni_hmota ", hra.zpracujPrikaz("seber mysteriozni_hmota"));

    }



}
