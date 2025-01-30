package logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída Kufrik představuje inventář hráče, který může obsahovat různé předměty.
 *  * @author Olga Jandová
 *  * @version pro školní rok 2023/2024
 */
public class Kufrik {
    private final int omezeniKufriku; // Maximální kapacita kufříku
    private List<Vec> obsahKufriku; // Obsah kufříku, reprezentovaný množinou předmětů

    /**
     * Konstruktor třídy Kufrik inicializuje maximální kapacitu kufříku a vytváří prázdný inventář.
     *
     * @param omezeniInventare určuje maximální kapacitu kufříku
     */
    public Kufrik(int omezeniInventare) {
        this.omezeniKufriku = omezeniInventare;
        obsahKufriku = new ArrayList<>();
    }

    /**
     * Metoda vrátí obsah kufříku.
     *
     * @return Obsah kufříku
     */
    public List<Vec> getObsahKufriku() {
        return obsahKufriku;
    }

    /**
     * Metoda vyndejZKufriku vyjme předmět z kufříku podle zadaného názvu.
     *
     * @param nazevVeci Název předmětu k vyjmutí z kufříku
     * @return Předmět vyjmutý z kufříku nebo null, pokud se v kufříku nenachází předmět se zadaným názvem
     */
    public Vec vyndejZKufriku(String nazevVeci) {
        for (Vec neco : obsahKufriku) {
            if (neco.getNazev().equals(nazevVeci)) {
                obsahKufriku.remove(neco);
                return neco;
            }
        }
        return null;
    }

    /**
     * Metoda vlozitDoKufriku vloží předmět do kufříku, pokud kufřík není plný a předmět je přenositelný.
     * Pokud je kufřík plný a přidávaný předmět je "mysteriozni_hmota", tak se tento předmět přidá do kufříku
     * a vypíše se v kufříku.
     *
     * @param vec Předmět k vložení do kufříku
     * @return True, pokud se podařilo předmět vložit do kufříku, jinak false
     */
    public boolean vlozitDoKufriku(Vec vec) {
        if (obsahKufriku.size() < omezeniKufriku && vec.jePrenositelna()) {
            obsahKufriku.add(vec);
            return true;
        } else if (vec.getNazev().equals("mysteriozni_hmota")) {
            obsahKufriku.add(vec);
            return true;
        }
        return false;
    }

    /**
     * Metoda dlouhyPopis vrací detailní popis obsahu kufříku.
     *
     * @return Detailní popis obsahu kufříku
     */
    public String dlouhyPopis() {
        String vypis = "\nObsah kufříku: ";

        for (Vec vec : obsahKufriku) {
            vypis += vec.getNazev() + " ";
        }
        return vypis;
    }
}
