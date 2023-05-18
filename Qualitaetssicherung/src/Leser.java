
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rathfab28
 */
public class Leser {

    private String maschinenNummer;
    private static String speicherChar;
    private static String[] speicherString;
    private ArrayList<Pakete> listAllePackete = new ArrayList<>();
    private ArrayList<Pakete> list250g = new ArrayList<>();
    private ArrayList<Pakete> list500g = new ArrayList<>();
    private ArrayList<Pakete> list1000g = new ArrayList<>();
    private Pakete pakete;

    Leser(String maschinenNummer) {
        this.maschinenNummer = maschinenNummer;
        if (!maschinenNummer.equals("0")) {
            if (Integer.parseInt(maschinenNummer) < 10) {
                maschinenNummer = "0" + maschinenNummer;
            }
            String dateiName = "maschine" + maschinenNummer + ".csv";
            such(dateiName);
        }
    }

    void such(String dateiName) {
        try {
            FileReader liesHilf = new FileReader(dateiName);
            BufferedReader lies = new BufferedReader(liesHilf);
            while (lies.ready()) {
                speicherChar = lies.readLine();
                speicherString = speicherChar.split(",");
                pakete = new Pakete(speicherString[0], Integer.parseInt(speicherString[1]), Double.parseDouble(speicherString[2]));
                listAllePackete.add(pakete);
            }
            liesHilf.close();
        } catch (Exception ex) {
            Logger.getLogger(Leser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int[] anzahlPakete() {
        for (int x = 0; listAllePackete.size() > x; x++) {
            if (listAllePackete.get(x).getSollGewicht() == 250) {
                list250g.add(listAllePackete.get(x));
            } else if (listAllePackete.get(x).getSollGewicht() == 500) {
                list500g.add(listAllePackete.get(x));
            } else if (listAllePackete.get(x).getSollGewicht() == 1000) {
                list1000g.add(listAllePackete.get(x));
            }
        }
        int paketeGroessen[] = {list250g.size(), list500g.size(), list1000g.size()};
        return paketeGroessen;
    }

    double getAverage(int indexSollGewicht) {
        double avarage = 0.00;
        if (indexSollGewicht == 1) {
            for (int x = 0; list250g.size() > x; x++) {
                avarage = avarage + list250g.get(x).getGewicht();
            }
            return Math.round(avarage / list250g.size() * Math.pow(10, 2)) / Math.pow(10, 2);
        } else if (indexSollGewicht == 2) {
            for (int x = 0; list500g.size() > x; x++) {
                avarage = avarage + list500g.get(x).getGewicht();
            }
            return Math.round(avarage / list500g.size() * Math.pow(10, 2)) / Math.pow(10, 2);
        } else if (indexSollGewicht == 3) {
            for (int x = 0; list1000g.size() > x; x++) {
                avarage = avarage + list1000g.get(x).getGewicht();
            }

            return Math.round(avarage / list1000g.size() * Math.pow(10, 2)) / Math.pow(10, 2);
        }
        return avarage;
    }

    //Zählen fehlt
    int getpacketeProzent(double prozent, int indexSollGewicht) {
        ArrayList<Double> listProzentPacket = new ArrayList<>();
        int anzahl = 0;
        double abweichungProzent = 0;
        if (indexSollGewicht == 1) {
            for (int x = 0; list250g.size() > x; x++) {
                abweichungProzent = (250.0 - list250g.get(x).getGewicht()) / 250.0 * 100.0;
                abweichungProzent = Math.round(abweichungProzent * Math.pow(10, 1)) / Math.pow(10, 1);
                listProzentPacket.add(abweichungProzent);
            }
        } else if (indexSollGewicht == 2) {
            for (int x = 0; list500g.size() > x; x++) {
                abweichungProzent = (500.0 - list500g.get(x).getGewicht()) / 500.0 * 100.0;
                abweichungProzent = Math.round(abweichungProzent * Math.pow(10, 1)) / Math.pow(10, 1);
                listProzentPacket.add(abweichungProzent);
            }
        } else if (indexSollGewicht == 3) {
            for (int x = 0; list1000g.size() > x; x++) {
                abweichungProzent = (1000.0 - list1000g.get(x).getGewicht()) / 1000.0 * 100.0;
                abweichungProzent = Math.round(abweichungProzent * Math.pow(10, 1)) / Math.pow(10, 1);
                listProzentPacket.add(abweichungProzent);
            }
        }
        for (int x = 0; listProzentPacket.size() > x; x++) {
            if (listProzentPacket.get(x) >= prozent && listProzentPacket.get(x) >= 0) {
                anzahl++;
            } else if (listProzentPacket.get(x) * (-1) >= prozent&& listProzentPacket.get(x) <= 0) {
                anzahl++;
            }
        }
        return anzahl;
    }
}
