package reg;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Register implements Serializable {
    public List<Employee> employeeList = new ArrayList<>();
    public int employeeCounter = 0;
    public final List<String> peselList = new ArrayList<>();

    public Register() {
    }

    /* weryfikacja pesel

        while (true) {
            boolean weryfikacja = nowy.setPesel(pesel);
            boolean uniq = sprawdzUnikalnoscPesel(pesel);
            if (!weryfikacja || !uniq) {
                continue;
            } else
                nowy.setPesel(pesel);
                break;
            }
     */

    public Employee addDirector(String pesel, String name, String surname, BigDecimal salary, String phoneNum, BigDecimal bonus, String card , BigDecimal limit) {
        Dyrektor newEmp = new Dyrektor();
        newEmp.setHeadship();
        newEmp.setName(name);
        newEmp.setSurname(surname);
        newEmp.setSalary(salary);
        newEmp.setPhoneNumber(phoneNum);
        newEmp.setBonus(bonus);
        newEmp.setBusinessCard(card);
        newEmp.setCostLimit(limit);
        newEmp.setPesel(pesel);
        peselList.add(pesel);
        employeeCounter++;
        employeeList.add(newEmp);
        return newEmp;
    }

    public Employee addTrader(String pesel, String name, String surname, BigDecimal salary, String phoneNum, BigDecimal commission, BigDecimal limit) {
        Handlowiec newEmpl = new Handlowiec();
        newEmpl.setName(name);
        newEmpl.setSurname(surname);
        newEmpl.setSalary(salary);
        newEmpl.setCommision(commission);
        newEmpl.setPhoneNumber(phoneNum);
        newEmpl.setCostLimit(limit);
        newEmpl.setPesel(pesel);
        peselList.add(pesel);
        employeeCounter++;
        employeeList.add(newEmpl);
        return newEmpl;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        employeeCounter++;
        peselList.add(employee.getPesel());
    }

    public void removeEmployee(Employee toDelete) {
        removePesel(toDelete.getPesel());
        employeeList.remove(toDelete);
        employeeCounter--;
    }

    public boolean checkUniquenessPesel(String newPesel) {
        if (peselList.size() > 0) {
            for (String item : peselList) {
                if (item.equals(newPesel)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean peselValidation(String pesel){
        //pesel ma 11 cyfr
        if(pesel.length() == 11) {
            char month1 = pesel.charAt(2);
            char month2 = pesel.charAt(3);
            int m1 = month1 -48;
            int m2 = month2 -48;
            char day1 = pesel.charAt(4);
            char day2 = pesel.charAt(5);
            int d1 = day1 -48;
            int d2 = day2 -48;
            //pola miesiaca urodzenia musza byc od 01 do 12
            if((m1 == 0 && month2 != 0) || (m1 == 1 && m2 < 3)) {
                //pola dnia miesiaca musza byc od 01 do 31
                return (d1 == 0 && d2 != 0) || (d1 == 1) || (d1 == 2) || (d1 == 3 && d2 < 2);
            }
        }
        return false;
    }

    public void removePesel(String oldPesel) {
        if (peselList.size() > 0) {
            for (String item : peselList) {
                if (item.equals(oldPesel)) {
                    peselList.remove(item);
                    break;
                }
            }
        }
    }

    public boolean isRegistryEmpty() {
        return employeeList.size() != 0;
    }

    public void saveEmployee(List<String> filepaths, Employee employee) throws IOException {
        String filepath = "C:\\Users\\mrmaj\\OneDrive - Zachodniopomorski Uniwersytet Technologiczny w Szczecinie\\Dokumenty\\Intellij\\Java_lab\\lab12\\";
        try {
            String fileToZip = filepath + employee.getSurname();
            FileOutputStream file = new FileOutputStream(fileToZip);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(employee);
            out.close();
            file.close();
            filepaths.add(fileToZip);
        } catch (IOException e) {
            System.out.print("IOExp");
            e.printStackTrace();
        }
    }

    public Register saveRegistryBackup(Register register, String filename, String gOrZ) {
        if (employeeCounter != 0) {
            String filepath = "C:\\Users\\mrmaj\\OneDrive - Zachodniopomorski Uniwersytet Technologiczny w Szczecinie\\Dokumenty\\Intellij\\Java_lab\\lab12\\";
            List<String> filepaths = new ArrayList<>();
            try {
                //zapis plikow pracownikow
                saveEmpleyeesFiles(filepaths);
                //kompresja
                if (gOrZ.equalsIgnoreCase("Z")) {
                    zipCompression(filepath, filename, filepaths);
                } else if (gOrZ.equalsIgnoreCase("G")) {
                    gzipCompression(filepath, filename, filepaths);
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
        } else if (!register.isRegistryEmpty()) {
            System.out.print("\t\tLista zatrudnionych jest pusta.");
        }
        return register;
    }

    private void saveEmpleyeesFiles(List<String> filepaths) throws InterruptedException, ExecutionException {
        List<CompletableFuture<Void>> cfList = new ArrayList<>(employeeCounter);
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (Employee prac : employeeList) {
            CompletableFuture<Void> pracownik = CompletableFuture.runAsync(() -> {
                try {
                    saveEmployee(filepaths, prac);
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
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]));
        allFutures.get();
        executorService.shutdown();
    }

    private void gzipCompression(String filepath, String filename, List<String> filepaths) throws IOException {
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
    }

    private void zipCompression(String filepath, String filename, List<String> filepaths) throws IOException {
        filename += ".zip";
        FileOutputStream fos = new FileOutputStream(filepath + filename);
        ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
        for (String filePath : filepaths) {
            File input = new File(filePath);
            FileInputStream fis = new FileInputStream(input);
            ZipEntry ze = new ZipEntry(input.getName());
            zipOut.putNextEntry(ze);
            byte[] tmp = new byte[4 * 1024];
            int size;
            while ((size = fis.read(tmp)) != -1) {
                zipOut.write(tmp, 0, size);
            }
            zipOut.flush();
            fis.close();
            Files.deleteIfExists(Paths.get(filePath));
        }
        zipOut.close();
    }

    public Employee readEmployee(String dest) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(dest);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Employee input;
        input = (Employee) (in.readObject());
        in.close();
        fileIn.close();
        return input;
    }

    public Register readRegistryBackup(String filename) throws ExecutionException, IOException, InterruptedException {
        String filepath = null;
        String extension = filename.substring(filename.length() - 2);
        Register newEwid = new Register();
        byte[] buffer = new byte[1024];
        String dest = filename.substring(0, filename.indexOf("."));
        File file = new File(filepath + dest);
        file.mkdir();
        dest = filepath + dest;
        InputStream fi = Files.newInputStream(Paths.get(filepath + filename));
        BufferedInputStream bi = new BufferedInputStream(fi);

        if (extension.equalsIgnoreCase("ip")) {
            unZip(dest, bi);
        } else if (extension.equalsIgnoreCase("gz")) {
            unGzip(dest, bi);
        }

        List<CompletableFuture<Employee>> cfList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        File folder = new File(dest);
        File[] listOfFiles = folder.listFiles();
        final Employee[] thisFileOut = {null};
        assert listOfFiles != null;
        for (File pracownik : listOfFiles) {
            CompletableFuture<Employee> thisPrac = CompletableFuture.supplyAsync(() -> {
                try {
                    thisFileOut[0] = readEmployee(pracownik.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    System.out.print("FileNotFoundExp");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.print("IOExp");
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    System.out.print("ClassNotFoundExp");
                    e.printStackTrace();
                }
                return thisFileOut[0];
            }, executorService);
            cfList.add(thisPrac);
            thisPrac.get();
        }

        for (CompletableFuture<Employee> item : cfList) {
            newEwid.addEmployee(item.get());
        }
        executorService.shutdown();

        Files.walk(Paths.get(dest))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);


        return newEwid;
    }

    private void unGzip(String dest, BufferedInputStream bi) throws IOException {
        GzipCompressorInputStream gzi = new GzipCompressorInputStream(bi);
        TarArchiveInputStream ti = new TarArchiveInputStream(gzi);
        ArchiveEntry entry;
        while ((entry = ti.getNextEntry()) != null) {
            Path destPath = Paths.get(dest).resolve(entry.getName());
            Files.copy(ti, destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private void unZip(String dest, BufferedInputStream bi) throws IOException {
        ZipInputStream gzi = new ZipInputStream(bi);
        ZipEntry entry;
        while ((entry = gzi.getNextEntry()) != null) {
            Path destPath = Paths.get(dest).resolve(entry.getName());
            Files.copy(gzi, destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
