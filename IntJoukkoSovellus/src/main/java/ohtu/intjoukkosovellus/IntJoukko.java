
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Joukon kapasiteetti ei voi olla negatiivinen.");
        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("Taulukon kasvatuskoko ei voi olla negatiivinen");
        }
        luvut = new int[kapasiteetti];
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        } 
        luvut[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm >= luvut.length) {
            luvut = Arrays.copyOf(luvut, luvut.length + kasvatuskoko);
        }
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i : luvut) {
            if (i == luku) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < luvut.length; i++) {
            if (luvut[i] == luku) {
                int[] arr1 = Arrays.copyOfRange(luvut, 0, i);
                int[] arr2 = Arrays.copyOfRange(luvut, i + 1, luvut.length);
                System.arraycopy(arr1, 0, luvut, 0, arr1.length);
                System.arraycopy(arr2, 0, luvut, i, arr2.length);
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < alkioidenLkm; i++) {
            builder.append(luvut[i]).append(", ");
        }
        if (builder.length() > 1) {
            //viimeinen ", " pois
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("}");
        return builder.toString();
    }

    public int[] toIntArray() {
        return Arrays.copyOf(luvut, alkioidenLkm);
    }


    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        for (int i : a.toIntArray()) {
            yhdisteJoukko.lisaa(i);
        }
        for (int i : b.toIntArray()) {
            yhdisteJoukko.lisaa(i);
        }
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        for (int i : a.toIntArray()) {
            if (b.kuuluu(i)) {
                leikkausJoukko.lisaa(i);
            }
        }
        return leikkausJoukko;
    }

    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        for (int i : a.toIntArray()) {
            if (!b.kuuluu(i)) {
                erotusJoukko.lisaa(i);
            }
        }
        return erotusJoukko;
    }

}
