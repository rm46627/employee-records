package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.*;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;


class Ewidencja implements Serializable{
    protected List<Pracownik> lista = new ArrayList<>();
    private int liczbaPracownikow = 0;
    private final List<String> listaPesel = new ArrayList<>();

    public Ewidencja(){}

    public void addDyrektor(){
        Dyrektor nowy = new Dyrektor();
        Scanner dane = new Scanner(System.in);
        Scanner danee = new Scanner(System.in);
        String pesel = null;
        System.out.println("\t\t---------------------------------");
        boolean loopPESEL = true;
        while (loopPESEL) {
            System.out.print("\t\tIdentyfikator PESEL\t\t\t:\t");
            pesel = dane.nextLine();
            boolean weryfikacja = nowy.setPesel(pesel);
            boolean uniq = sprawdzUnikalnoscPesel(pesel);
            if (!weryfikacja || !uniq) {
                System.out.println("Wprowadzono bledny pesel - sprobuj ponownie.");
                continue;
            } else
                nowy.setPesel(pesel);
            loopPESEL = false;
        }

        nowy.setDyrektor();

        System.out.print("\t\tImie\t\t\t\t\t\t:\t");
        String imie = dane.nextLine();
        nowy.setName(imie);

        System.out.print("\t\tNazwisko\t\t\t\t\t:\t");
        String nazwisko = dane.nextLine();
        nowy.setSurname(nazwisko);

        System.out.print("\t\tWynagrodzenie (zl)\t\t\t:\t");
        BigDecimal wynagrodzenie = dane.nextBigDecimal();
        nowy.setSalary(wynagrodzenie);

        System.out.print("\t\tTelefon sluzbowy numer\t\t:\t");
        String num = danee.nextLine();
        nowy.setPhoneNumber(num);

        System.out.print("\t\tDodatek sluzbowy (zl)\t\t:\t");
        BigDecimal dodatek = dane.nextBigDecimal();
        nowy.setDodatekSluzbowy(dodatek);

        System.out.print("\t\tKarta sluzbowa numer\t\t:\t");
        String karta = danee.nextLine();
        nowy.setBusinessCard(karta);

        System.out.print("\t\tLimit kosztow/miesiac (zl)\t:\t");
        BigDecimal limit = dane.nextBigDecimal();
        nowy.setCostLimit(limit);
        System.out.println("\t\t---------------------------------");
        System.out.println("\t[Enter] - zapisz");
        System.out.println("\t[Q] - porzuc");
        String exit = danee.nextLine();
        if (exit.equals("")) {
            listaPesel.add(pesel);
            liczbaPracownikow++;
            lista.add(nowy);
        } else if (exit.equalsIgnoreCase("q"))
            System.out.println("Porzucono wprowadzone dane.");
    }
    public void addHandlowiec(){
        Handlowiec nowy = new Handlowiec();
        Scanner dane = new Scanner(System.in);
        Scanner danee = new Scanner(System.in);
        String pesel = null;
        System.out.println("\t\t---------------------------------");
        boolean loopPESEL = true;
        while(loopPESEL) {
            System.out.print("\t\tIdentyfikator PESEL\t\t\t:\t");
            pesel = dane.nextLine();
            boolean weryfikacja = nowy.setPesel(pesel);
            boolean uniq = sprawdzUnikalnoscPesel(pesel);
            if (!weryfikacja || !uniq) {
                System.out.println("Wprowadzono bledny pesel - sprobuj ponownie.");
                continue;
            }
            else
                nowy.setPesel(pesel);
                loopPESEL = false;
        }
        System.out.print("\t\tImie\t\t\t\t\t\t:\t");
        String imie = dane.nextLine();
        nowy.setName(imie);

        System.out.print("\t\tNazwisko\t\t\t\t\t:\t");
        String nazwisko = dane.nextLine();
        nowy.setSurname(nazwisko);

        System.out.print("\t\tWynagrodzenie (zl)\t\t\t:\t");
        BigDecimal wynagrodzenie = dane.nextBigDecimal();
        nowy.setSalary(wynagrodzenie);

        System.out.print("\t\tProwizja (%)\t\t\t\t:\t");
        BigDecimal prowizja = dane.nextBigDecimal();
        nowy.setStawkaProwizji(prowizja);

        System.out.print("\t\tLimit kosztow/miesiac (zl)\t:\t");
        BigDecimal limit = dane.nextBigDecimal();
        nowy.setCostLimit(limit);
        System.out.println("\t\t---------------------------------");
        System.out.println("\t[Enter] - zapisz");
        System.out.println("\t[Q] - porzuc");
        String exit = danee.nextLine();
        if(exit.equals("")) {
            listaPesel.add(pesel);
            liczbaPracownikow++;
            lista.add(nowy);
        }
        else if(exit.equalsIgnoreCase("q"))
            System.out.println("Porzucono wprowadzone dane.");
    }
    public void addPracownik(Pracownik pracownik){
        lista.add(pracownik);
        liczbaPracownikow++;
        listaPesel.add(pracownik.getPesel());
    }
    public void usunPracownika(){
        int i = 0;
        for(Pracownik item : lista){
            i++;
            System.out.println("\n\t\tIdentyfikator PESEL\t\t\t:\t" + item.getPesel());
            System.out.println("\t\t---------------------------------");
            System.out.println("\t\tImie\t\t\t\t\t\t:\t" + item.getName());
            System.out.println("\t\tNazwisko\t\t\t\t\t:\t" + item.getSurname());

            if(item.isDyrektor()) {
                System.out.println("\t\tStanowisko\t\t\t\t\t:\tDyrektor");
            }else{
                System.out.println("\t\tStanowisko\t\t\t\t\t:\tHandlowiec");
            }

            System.out.println("\t\tWynagrodzenie (zl)\t\t\t:\t" + item.getSalary());

            if(item.isDyrektor())
                System.out.println("\t\tTelefon sluzbowy numer\t\t:\t" + item.getPhoneNumber());
            else
                System.out.println("\t\tTelefon sluzbowy numer\t\t:\t- brak -");

            if(item.isDyrektor())
                System.out.println("\t\tDodatek sluzbowy (zl)\t\t:\t" + item.getDodatekSluzbowy());
            else
                System.out.println("\t\tProwizja (%)\t\t\t\t:\t" + item.getStawkaProwizji());

            if(item.isDyrektor())
                System.out.println("\t\tKarta sluzbowa numer\t\t:\t" + item.getBusinessCard());

            System.out.println("\t\tLimit kosztow/miesiac (zl)\t:\t" + item.getCostLimit());
            System.out.println("\t\t---------------------------------");
            System.out.println("\n\t\t\t\t\t\t\t\t\t[Pozycja: " + i + "/" + liczbaPracownikow + "]");
            System.out.println("\t[Enter] - nastepny\n\t[Q] - porzuc\n\t[U] - usun pracownika");
            Scanner menu = new Scanner(System.in);
            String exit = menu.nextLine();
            if(exit.equalsIgnoreCase("q"))
                break;
            if(exit.equalsIgnoreCase("u")){
                usunPesel(item.getPesel());
                lista.remove(item);
                liczbaPracownikow--;
                break;
            }
            if(i == lista.size()){
                break;
            }
        }
    }
    public void listaPracownikow(){
        int i = 0;
        for(Pracownik item : lista){
            i++;
            System.out.println("\t\tIdentyfikator PESEL\t\t\t:\t" + item.getPesel());
            System.out.println("\t\tImie\t\t\t\t\t\t:\t" + item.getName());
            System.out.println("\t\tNazwisko\t\t\t\t\t:\t" + item.getSurname());

            if(item.isDyrektor()) {
                System.out.println("\t\tStanowisko\t\t\t\t\t:\tDyrektor");
            }else{
                System.out.println("\t\tStanowisko\t\t\t\t\t:\tHandlowiec");
            }

            System.out.println("\t\tWynagrodzenie (zl)\t\t\t:\t" + item.getSalary());

            if(item.isDyrektor())
                System.out.println("\t\tTelefon sluzbowy numer\t\t:\t" + item.getPhoneNumber());
            else
                System.out.println("\t\tTelefon sluzbowy numer\t\t:\t- brak -");

            if(item.isDyrektor())
                System.out.println("\t\tDodatek sluzbowy (zl)\t\t:\t" + item.getDodatekSluzbowy());
            else
                System.out.println("\t\tProwizja (%)\t\t\t\t:\t" + item.getStawkaProwizji());

            if(item.isDyrektor())
            System.out.println("\t\tKarta sluzbowa numer\t\t:\t" + item.getBusinessCard());

            System.out.println("\t\tLimit kosztow/miesiac (zl)\t:\t" + item.getCostLimit());

            System.out.println("\n\t\t\t\t\t\t\t\t\t[Pozycja: " + i + "/" + liczbaPracownikow + "]");
            System.out.println("\t[Enter] - nastepny\n\t[Q] - powrot");
            Scanner menu = new Scanner(System.in);
            String exit = menu.nextLine();
            if(exit.equalsIgnoreCase("q"))
                break;
            if(i == lista.size()){
                break;
            }
        }
    }
    public boolean sprawdzUnikalnoscPesel(String newPesel){
        if(listaPesel.size() > 0) {
            for (String item : listaPesel) {
                if (item.equals(newPesel)) {
                    return false;
                }
            }
        }
        return true;
    }
    public void usunPesel(String oldPesel){
        if(listaPesel.size() > 0) {
            for (String item : listaPesel) {
                if (item.equals(oldPesel)) {
                    listaPesel.remove(item);
                    break;
                }
            }
        }
    }
    public boolean czyKtosJestZatrudniony(){
        return lista.size() != 0;
    }

    public void zapisPracownika (List <String> filepaths, Pracownik pracownik) throws IOException, FileNotFoundException {
            String filepath = "C:\\Users\\mrmaj\\OneDrive - Zachodniopomorski Uniwersytet Technologiczny w Szczecinie\\Dokumenty\\Intellij\\Java_lab\\lab12\\";
            try {
                String fileToZip = filepath + pracownik.getSurname();
                FileOutputStream file = new FileOutputStream(fileToZip);
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(pracownik);
                out.close();
                file.close();
                filepaths.add(fileToZip);
            } catch (FileNotFoundException e) {
                System.out.print("FileNotFoundExp");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.print("IOExp");
                e.printStackTrace();
            }
        }
    public void zapisKopii(Ewidencja ewidencja) throws IOException, FileNotFoundException{
        if(liczbaPracownikow != 0) {
            String filepath = "C:\\Users\\mrmaj\\OneDrive - Zachodniopomorski Uniwersytet Technologiczny w Szczecinie\\Dokumenty\\Intellij\\Java_lab\\lab12\\";
            System.out.println("\t\t\t---------------------------------");
            System.out.print("\t\t\tKompresja [G]zip/[Z]ip\t:\t");
            Scanner backup = new Scanner(System.in);
            String gOrZ = backup.nextLine();
            System.out.print("\t\t\t---------------------------------");
            System.out.print("\n\t\t\tNazwa pliku\t\t:\t");
            String filename = backup.nextLine();
            List<String> filepaths = new ArrayList<>();
        //zapis plikow pracownikow
            try {
                List<CompletableFuture<Void>> cfList = new ArrayList<CompletableFuture<Void>>(liczbaPracownikow);
                ExecutorService executorService = Executors.newFixedThreadPool(8);
                for (Pracownik prac : lista) {
                    CompletableFuture<Void> pracownik = CompletableFuture.runAsync(() -> {
                        try {
                            Pracownik finalPrac = prac;
                            zapisPracownika(filepaths, finalPrac);
                        } catch (FileNotFoundException e) {
                            System.out.print("FileNotFoundExp");
                            e.printStackTrace();
                        } catch (IOException e) {
                            System.out.print("IOExp");
                            e.printStackTrace();
                        }
                    }, executorService);
                    cfList.add(pracownik);
                }
                CompletableFuture<Void> allFutures = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[cfList.size()]));
                allFutures.get();
                executorService.shutdown();

                //kompresja do zip
                if (gOrZ.equalsIgnoreCase("Z")) {
                    filename += ".zip";
                    FileOutputStream fos = new FileOutputStream(filepath + filename);
                    ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
                    for (String filePath : filepaths) {
                        File input = new File(filePath);
                        FileInputStream fis = new FileInputStream(input);
                        ZipEntry ze = new ZipEntry(input.getName());
                        zipOut.putNextEntry(ze);
                        byte[] tmp = new byte[4 * 1024];
                        int size = 0;
                        while ((size = fis.read(tmp)) != -1) {
                            zipOut.write(tmp, 0, size);
                        }
                        zipOut.flush();
                        fis.close();
                        Files.deleteIfExists(Paths.get(filePath));
                    }
                    zipOut.close();
                //kompresja tar.gz
                } else if (gOrZ.equalsIgnoreCase("G")) {
                    Path output = Paths.get(filepath + filename + ".tar.gz");
                    try (OutputStream fOut = Files.newOutputStream(output);
                         BufferedOutputStream buffOut = new BufferedOutputStream(fOut);
                         GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
                         TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {
                        for (String path : filepaths) {
                            Path ph = Paths.get(path);
                            TarArchiveEntry tarEntry = new TarArchiveEntry(ph.toFile(), ph.getFileName().toString());
                            tOut.putArchiveEntry(tarEntry);
                            Files.copy(ph, tOut);
                            tOut.closeArchiveEntry();
                            Files.deleteIfExists(Paths.get(path));
                        }
                        tOut.finish();
                    }
                } else {
                    System.out.print("\t\tBledny wybor - powrot do menu.");
                }
            } catch (FileNotFoundException e) {
                System.out.print("FileNotFoundExp");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.print("IOExp");
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.print("InterruptedExp");
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.print("ExecutionExp");
                e.printStackTrace();
            }
        }
        else if(!ewidencja.czyKtosJestZatrudniony()){
            System.out.print("\t\tLista zatrudnionych jest pusta.");
        }
    }

    public Pracownik odczytPracownika(String dest) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(dest);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Pracownik input = null;
        input = (Pracownik) (in.readObject());
        in.close();
        fileIn.close();
        return input;
    }
    public Ewidencja odczytDanych() throws IOException, FileNotFoundException, ClassNotFoundException{
        String filepath = "C:\\Users\\mrmaj\\OneDrive - Zachodniopomorski Uniwersytet Technologiczny w Szczecinie\\Dokumenty\\Intellij\\Java_lab\\lab12\\";
            System.out.print("\t\t\t---------------------------------");
            System.out.print("\n\t\t\tNazwa pliku\t\t:\t");
            Scanner backup = new Scanner(System.in);
            String filename = backup.nextLine();
            String extension = filename.substring(filename.length() - 2);
            Ewidencja newEwid = new Ewidencja();
            try {
                byte[] buffer = new byte[1024];
                String dest = filename.substring( 0, filename.indexOf("."));
                File file = new File(filepath + dest);
                file.mkdir();
                dest = filepath + dest;
                InputStream fi = Files.newInputStream(Paths.get(filepath + filename));
                BufferedInputStream bi = new BufferedInputStream(fi);
                if(extension.equalsIgnoreCase("ip")) {
                    ZipInputStream gzi = new ZipInputStream(bi);
                    ZipEntry entry;
                    while ((entry = gzi.getNextEntry()) != null) {
                        Path destPath = Paths.get(dest).resolve(entry.getName());
                        Files.copy(gzi, destPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                }else if(extension.equalsIgnoreCase("gz")) {
                    GzipCompressorInputStream gzi = new GzipCompressorInputStream(bi);
                    TarArchiveInputStream ti = new TarArchiveInputStream(gzi);
                    ArchiveEntry entry;
                    while ((entry = ti.getNextEntry()) != null) {
                        Path destPath = Paths.get(dest).resolve(entry.getName());
                        Files.copy(ti, destPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                else {
                    System.out.println("Podano bledna nazwe pliku lub plik ma bledne rozszerzenie.");
                    return null;
                }

                List<CompletableFuture<Pracownik>> cfList = new ArrayList<CompletableFuture<Pracownik>>();
                ExecutorService executorService = Executors.newFixedThreadPool(8);

                File folder = new File(dest);
                File[] listOfFiles = folder.listFiles();
                final Pracownik[] thisFileOut = {null};
                for (File pracownik : listOfFiles) {
                    CompletableFuture<Pracownik> thisPrac = CompletableFuture.supplyAsync(() -> {
                        try {
                            thisFileOut[0] = odczytPracownika(pracownik.getAbsolutePath());
                        } catch (FileNotFoundException e) {
                            System.out.print("FileNotFoundExp");
                            e.printStackTrace();
                        } catch (IOException e) {
                            System.out.print("IOExp");
                            e.printStackTrace();
                        }catch (ClassNotFoundException e){
                            System.out.print("ClassNotFoundExp");
                            e.printStackTrace();
                        }
                        return thisFileOut[0];
                    }, executorService);
                    cfList.add(thisPrac);
                    thisPrac.get();
                }

                for(CompletableFuture<Pracownik> item : cfList){
                    newEwid.addPracownik(item.get());
                }
                executorService.shutdown();

                Files.walk(Paths.get(dest))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);


            }catch (FileNotFoundException e){
                System.out.print("FileNotFoundExp");
                e.printStackTrace();
            }catch (IOException e){
                System.out.print("IOExp");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        return newEwid;
    }
}

abstract class Pracownik implements Serializable{

    private boolean isDyrek = false;
    private String pesel;
    private String name;
    private String surname;
    private BigDecimal salary;
    private BigDecimal costLimit;
    private String phoneNumber;

    public Pracownik(){}

    protected void setDyrektor(){
        isDyrek = true;
    }

    protected boolean setPesel(String newPesel){
        //pesel ma 11 cyfr
        if(newPesel.length() == 11) {
            char miesiac1 = newPesel.charAt(2);
            char miesiac2 = newPesel.charAt(3);
            int m1 = miesiac1 -48;
            int m2 = miesiac2 -48;
            char dzien1 = newPesel.charAt(4);
            char dzien2 = newPesel.charAt(5);
            int d1 = dzien1 -48;
            int d2 = dzien2 -48;
            //pola miesiaca urodzenia musza byc od 01 do 12
            if((m1 == 0 && miesiac2 != 0) || (m1 == 1 && m2 < 3)) {
                //pola dnia miesiaca musza byc od 01 do 31
                if ((d1 == 0 && d2 != 0) || (d1 == 1) || (d1 == 2) || (d1 == 3 && d2 < 2)) {
                    pesel = newPesel;
                    return true;
                }
            }
        }
        return false;
    }
    protected void setName(String newName){
        name = newName;
    }
    protected void setSurname(String newSurname){
        surname = newSurname;
    }
    protected void setSalary(BigDecimal newSalary){
        salary = newSalary;
    }
    protected void setCostLimit(BigDecimal newCostLimit){
        costLimit = newCostLimit;
    }
    protected void setPhoneNumber(String newNum){ phoneNumber = newNum;}

    protected boolean isDyrektor(){
        return isDyrek;
    }
    protected String getPesel(){
        return pesel;
    }
    protected String getName(){
        return name;
    }
    protected String getSurname(){
        return surname;
    }
    protected BigDecimal getSalary(){
        return salary;
    }
    protected BigDecimal getCostLimit(){
        return costLimit;
    }
    protected String getPhoneNumber(){
        return phoneNumber;
    }
    protected abstract BigDecimal getDodatekSluzbowy();
    protected abstract String getBusinessCard();
    protected abstract BigDecimal getStawkaProwizji();
}

class Dyrektor extends Pracownik implements Serializable{

    private BigDecimal dodatekSluzbowy;
    private String businessCard;

    public Dyrektor(){}

    protected void setDodatekSluzbowy(BigDecimal kwota){
        dodatekSluzbowy = kwota;
    }
    protected void setBusinessCard(String newBusinessCard){
        businessCard = newBusinessCard;
    }

    protected BigDecimal getStawkaProwizji(){ return null;}
    protected BigDecimal getDodatekSluzbowy(){
        return dodatekSluzbowy;
    }
    protected String getBusinessCard(){
        return businessCard;
    }
}

class Handlowiec extends Pracownik implements Serializable {
    private BigDecimal stawkaProwizji;

    public Handlowiec(){}

    protected void setStawkaProwizji(BigDecimal stawkaProcentowa){
        stawkaProwizji = stawkaProcentowa;
    }

    protected BigDecimal getStawkaProwizji(){
        return stawkaProwizji;
    }
    protected BigDecimal getDodatekSluzbowy(){ return null; }
    protected String getBusinessCard(){
        return null;
    }
}

    public class Main {

        public static void main(String[] args) throws IOException, ClassNotFoundException {
            Scanner menu = new Scanner(System.in);
            Ewidencja ewidencja = new Ewidencja();
            boolean loop = true;
            while (loop) {
                System.out.println("\tMenu");
                System.out.println("\t\t1. Lista pracownikow");
                System.out.println("\t\t2. Dodaj pracownika");
                System.out.println("\t\t3. Usun pracownika");
                System.out.println("\t\t4. Kopia zapasowa");
                System.out.println("\t\t5. Zakoncz");
                System.out.print("\tWybor> ");
                int wybor = menu.nextInt();
                switch (wybor) {
                    case 1:
                        if(ewidencja.czyKtosJestZatrudniony()) {
                            System.out.println("\t\t1. Lista pracownikow");
                            ewidencja.listaPracownikow();
                            break;
                        }
                        else{
                            System.out.println("\t\tLista zatrudnionych jest pusta.");
                            break;
                        }
                    case 2:
                        Scanner dane = new Scanner(System.in);
                        System.out.println("\t2. Dodaj pracownika");
                        System.out.print("\t\t[D]yrektor/[H]andlowiec:> ");
                        String pracownik = dane.nextLine();
                        if (pracownik.equalsIgnoreCase("d")){
                            ewidencja.addDyrektor();
                        }
                        if (pracownik.equalsIgnoreCase("h")){
                            ewidencja.addHandlowiec();
                        }
                        break;
                    case 3:
                        if(ewidencja.czyKtosJestZatrudniony()) {
                            System.out.println("\t3. Usun pracownika");
                            ewidencja.usunPracownika();
                            break;
                        }
                        else{
                            System.out.println("\t\tLista zatrudnionych jest pusta.");
                            break;
                        }
                    case 4:
                        System.out.println("\t\t4. Kopia zapasowa");
                        System.out.print("\n\t\t\t[Z]achowaj/[O]dtwórz\t :\t");
                        Scanner backup = new Scanner(System.in);
                        String userInput = backup.nextLine();

                        if(userInput.equalsIgnoreCase("z")) {
                        ewidencja.zapisKopii(ewidencja);
                        }
                        if(userInput.equalsIgnoreCase("o")) {
                            ewidencja = ewidencja.odczytDanych();
                        }
                        break;
                    case 5:
                        loop = false;
                }
            }
        }
    }
