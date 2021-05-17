package test;

import com.company.Ewidencja;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Collection;

public class TestEwidencja {

    @BeforeAll
    static void setUpBeforeClass(){
        System.out.println( "The tests have started." );
    }
    @AfterAll
    static void tearDownAfterClass(){
        System.out.println( "The tests are complete." );
    }

    //1.1
    @Test
    public void addHandlowiec_pustyKontener(){

        //given
        String pesel = "98083006138";
        String imie = "Tadeusz";
        String nazwisko = "Drozda";
        BigDecimal wynagrodzenie = new BigDecimal(3400);
        String num = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();

        //when
        Ewidencja.Pracownik result = ewidencja.addHandlowiec(pesel, imie, nazwisko, wynagrodzenie,num, prowizja, limit);

        //then
        Assertions.assertEquals(result, ewidencja.lista.get(0));
        Assertions.assertEquals(1, ewidencja.liczbaPracownikow);
        Assertions.assertFalse(ewidencja.lista.get(0).czyJestDyrektorem());
        Assertions.assertEquals(pesel, ewidencja.listaPesel.get(0));
    }
    //1.2
    @Test
    public void addDyrektor_pustyKontener(){

        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        Ewidencja ewidencja = new Ewidencja();

        //when
        Ewidencja.Pracownik result = ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie,num, dodatek, karta , limit);

        //then
        Assertions.assertEquals(result, ewidencja.lista.get(0));
        Assertions.assertEquals(1, ewidencja.liczbaPracownikow);
        Assertions.assertTrue(ewidencja.lista.get(0).czyJestDyrektorem());
        Assertions.assertEquals(pesel, ewidencja.listaPesel.get(0));
    }
    //1.3
    @Test
    public void addHandlowiec_kontenerZawieraPracownikow(){

        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String imie2 = "Tadeusz";
        String nazwisko2 = "Drozda";
        BigDecimal wynagrodzenie2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();

        //when
        ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie,num, dodatek, karta , limit);
        Ewidencja.Pracownik result = ewidencja.addHandlowiec(pesel2, imie2, nazwisko2, wynagrodzenie2, num2, prowizja, limit2);

        //then
        Assertions.assertEquals(result, ewidencja.lista.get(1));
        Assertions.assertFalse(ewidencja.lista.get(1).czyJestDyrektorem());
        Assertions.assertEquals(2, ewidencja.liczbaPracownikow);
        Assertions.assertEquals(pesel2, ewidencja.listaPesel.get(1));
    }
    //1.4
    @Test
    public void addDyrektor_kontenerZawieraPracownikow() {
        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String imie2 = "Tadeusz";
        String nazwisko2 = "Drozda";
        BigDecimal wynagrodzenie2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();

        //when
        ewidencja.addHandlowiec(pesel2, imie2, nazwisko2, wynagrodzenie2, num2, prowizja, limit2);
        Ewidencja.Pracownik result = ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie, num, dodatek, karta, limit);

        //then
        Assertions.assertEquals(result, ewidencja.lista.get(1));
        Assertions.assertTrue(ewidencja.lista.get(1).czyJestDyrektorem());
        Assertions.assertEquals(2, ewidencja.liczbaPracownikow);
        Assertions.assertEquals(pesel, ewidencja.listaPesel.get(1));
    }
    //1.5
    @Test
    public void addPracownik_10LosowychPracownikow(){

        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "91083006139";
        String imie2 = "Tadeusz";
        String nazwisko2 = "Drozda";
        BigDecimal wynagrodzenie2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal prowizja2 = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        String pesel3 = "97083006140";
        String imie3 = "Karolina";
        String nazwisko3 = "Pytel";
        BigDecimal wynagrodzenie3 = new BigDecimal(1000);
        String num3 = "505980224";
        BigDecimal dodatek3 = new BigDecimal(500);
        String karta3 = "2";
        BigDecimal limit3 = new BigDecimal(9700);

        String pesel4 = "93123006141";
        String imie4 = "Eliza";
        String nazwisko4 = "Dla";
        BigDecimal wynagrodzenie4 = new BigDecimal(6454);
        String num4 = "505980868";
        BigDecimal prowizja4 = new BigDecimal(4);
        BigDecimal limit4 = new BigDecimal(120);

        String pesel5 = "91082103338";
        String imie5 = "Edward";
        String nazwisko5 = "Kielich";
        BigDecimal wynagrodzenie5 = new BigDecimal(1234);
        String num5 = "505980868";
        BigDecimal dodatek5 = new BigDecimal(12500);
        String karta5 = "3";
        BigDecimal limit5 = new BigDecimal(30);

        String pesel6 = "94051006122";
        String imie6 = "Patrycja";
        String nazwisko6 = "Kostecka";
        BigDecimal wynagrodzenie6 = new BigDecimal(8800);
        String num6 = "505980868";
        BigDecimal prowizja6 = new BigDecimal(1);
        BigDecimal limit6 = new BigDecimal(2000);

        String pesel7 = "96082806128";
        String imie7 = "Michal";
        String nazwisko7 = "Redkwa";
        BigDecimal wynagrodzenie7 = new BigDecimal(1000);
        String num7 = "505980868";
        BigDecimal dodatek7 = new BigDecimal(150);
        String karta7 = "4";
        BigDecimal limit7 = new BigDecimal(300);

        String pesel8 = "96010106139";
        String imie8 = "Sebastian";
        String nazwisko8 = "Dyba";
        BigDecimal wynagrodzenie8 = new BigDecimal(34400);
        String num8 = "505980868";
        BigDecimal prowizja8 = new BigDecimal(23);
        BigDecimal limit8 = new BigDecimal(0);

        String pesel9 = "98083001111";
        String imie9 = "Anna";
        String nazwisko9 = "Przybyszewska";
        BigDecimal wynagrodzenie9 = new BigDecimal(7000);
        String num9 = "505980868";
        BigDecimal dodatek9 = new BigDecimal(1);
        String karta9 = "5";
        BigDecimal limit9 = new BigDecimal(3);

        String pesel10 = "99023006139";
        String imie10 = "Oskar";
        String nazwisko10 = "Nagroda";
        BigDecimal wynagrodzenie10 = new BigDecimal(2000);
        String num10 = "505980868";
        BigDecimal prowizja10 = new BigDecimal(40);
        BigDecimal limit10 = new BigDecimal(2550);

        Ewidencja ewidencja = new Ewidencja();

        //when
        Ewidencja.Pracownik result = ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie, num, dodatek, karta, limit);
        Ewidencja.Pracownik result2 = ewidencja.addHandlowiec(pesel2, imie2, nazwisko2, wynagrodzenie2, num2, prowizja2, limit2);
        Ewidencja.Pracownik result3 = ewidencja.addDyrektor(pesel3, imie3, nazwisko3, wynagrodzenie3, num3, dodatek3, karta3, limit3);
        Ewidencja.Pracownik result4 = ewidencja.addHandlowiec(pesel4, imie4, nazwisko4, wynagrodzenie4, num4, prowizja4, limit4);
        Ewidencja.Pracownik result5 = ewidencja.addDyrektor(pesel5, imie5, nazwisko5, wynagrodzenie5, num5, dodatek5, karta5, limit5);
        Ewidencja.Pracownik result6 = ewidencja.addHandlowiec(pesel6, imie6, nazwisko6, wynagrodzenie6, num6, prowizja6, limit6);
        Ewidencja.Pracownik result7 = ewidencja.addDyrektor(pesel7, imie7, nazwisko7, wynagrodzenie7, num7, dodatek7, karta7, limit7);
        Ewidencja.Pracownik result8 = ewidencja.addHandlowiec(pesel8, imie8, nazwisko8, wynagrodzenie8, num8, prowizja8, limit8);
        Ewidencja.Pracownik result9 = ewidencja.addDyrektor(pesel9, imie9, nazwisko9, wynagrodzenie9, num9, dodatek9, karta9, limit9);
        Ewidencja.Pracownik result10 = ewidencja.addHandlowiec(pesel10, imie10, nazwisko10, wynagrodzenie10, num10, prowizja10, limit10);


        //then
        Assertions.assertEquals(10, ewidencja.liczbaPracownikow);
        Assertions.assertEquals(result, ewidencja.lista.get(0));
        Assertions.assertEquals(pesel, ewidencja.listaPesel.get(0));
        Assertions.assertEquals(result2, ewidencja.lista.get(1));
        Assertions.assertEquals(pesel2, ewidencja.listaPesel.get(1));
        Assertions.assertEquals(result3, ewidencja.lista.get(2));
        Assertions.assertEquals(pesel3, ewidencja.listaPesel.get(2));
        Assertions.assertEquals(result4, ewidencja.lista.get(3));
        Assertions.assertEquals(pesel4, ewidencja.listaPesel.get(3));
        Assertions.assertEquals(result5, ewidencja.lista.get(4));
        Assertions.assertEquals(pesel5, ewidencja.listaPesel.get(4));
        Assertions.assertEquals(result6, ewidencja.lista.get(5));
        Assertions.assertEquals(pesel6, ewidencja.listaPesel.get(5));
        Assertions.assertEquals(result7, ewidencja.lista.get(6));
        Assertions.assertEquals(pesel7, ewidencja.listaPesel.get(6));
        Assertions.assertEquals(result8, ewidencja.lista.get(7));
        Assertions.assertEquals(pesel8, ewidencja.listaPesel.get(7));
        Assertions.assertEquals(result9, ewidencja.lista.get(8));
        Assertions.assertEquals(pesel9, ewidencja.listaPesel.get(8));
        Assertions.assertEquals(result10, ewidencja.lista.get(9));
        Assertions.assertEquals(pesel10, ewidencja.listaPesel.get(9));
    }
    //1.6
    @Test
    public void usunPracownika_Handlowiec_KontenerZawieraPracownikow() {
        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String imie2 = "Tadeusz";
        String nazwisko2 = "Drozda";
        BigDecimal wynagrodzenie2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();

        //when
        Ewidencja.Pracownik result = ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie, num, dodatek, karta, limit);
        ewidencja.addHandlowiec(pesel2, imie2, nazwisko2, wynagrodzenie2, num2, prowizja, limit2);
        ewidencja.usunPracownika(1);

        //then
        Assertions.assertEquals(1, ewidencja.liczbaPracownikow);
        Assertions.assertEquals(result, ewidencja.lista.get(0));
        Assertions.assertEquals(pesel, ewidencja.listaPesel.get(0));
    }
    //1.7
    @Test
    public void usunPracownika_Dyrektor_KontenerZawieraPracownikow(){
        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String imie2 = "Tadeusz";
        String nazwisko2 = "Drozda";
        BigDecimal wynagrodzenie2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();

        //when
        ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie, num, dodatek, karta, limit);
        Ewidencja.Pracownik result = ewidencja.addHandlowiec(pesel2, imie2, nazwisko2, wynagrodzenie2, num2, prowizja, limit2);
        ewidencja.usunPracownika(0);

        //then
        Assertions.assertEquals(1, ewidencja.liczbaPracownikow);
        Assertions.assertEquals(result, ewidencja.lista.get(0));
        Assertions.assertEquals(pesel2, ewidencja.listaPesel.get(0));
    }
    //1.8
    @RunWith(Parameterized.class)
    public static class sprawdzPoprawnoscPesel_Correct {

        @Parameters
        public static Collection<Object[]> testParameters() {
            return Arrays.asList(new Object[][]{
                    {"98083006138", true}, {"97072963579", true},
                    {"96022134778", true}, {"95032295336", true},
                    {"94061153676", true}, {"93051254237", true},
                    {"92113032555", true}, {"91120172544", true},
                    {"90090265423", true}, {"89031212347", true},
            });
        }

        private final String pesel;
        private final boolean expected;

        public sprawdzPoprawnoscPesel_Correct(String input, boolean expected) {
            this.pesel = input;
            this.expected = expected;
        }

        @Test
        public void Test_sprawdzPoprawnoscPesel_Correct() {
            //given
            Ewidencja ewidencja = new Ewidencja();

            //then
            Assertions.assertEquals(expected, ewidencja.sprawdzPoprawnoscPesel(pesel));
        }
    }

    @RunWith(Parameterized.class)
    public static class sprawdzPoprawnoscPesel_Wrong {

        @Parameters
        public static Collection<Object[]> testParameters() {
            return Arrays.asList(new Object[][]{
                    {"99131111234", false}, {"98123264864", false},
                    {"01332264375", false}, {"22345623978", false},
                    {"66373965483", false}, {"78446777564", false},
                    {"33994353426", false}, {"95453125256", false},
                    {"92635456879", false}, {"00000000000", false},
            });
        }

        private final String pesel;
        private final boolean expected;

        public sprawdzPoprawnoscPesel_Wrong(String input, boolean expected) {
            this.pesel = input;
            this.expected = expected;
        }

        @Test
        @Order(9)
        public void Test_sprawdzPoprawnoscPesel_Wrong() {
            //given
            Ewidencja ewidencja = new Ewidencja();

            //then
            Assertions.assertEquals(expected, ewidencja.sprawdzPoprawnoscPesel(pesel));
        }
    }

    //1.9
    @Test
    public void zapisKopii_Successful() throws IOException{

        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String imie2 = "Tadeusz";
        String nazwisko2 = "Drozda";
        BigDecimal wynagrodzenie2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();
        ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie,num, dodatek, karta , limit);
        ewidencja.addHandlowiec(pesel2, imie2, nazwisko2, wynagrodzenie2, num2, prowizja, limit2);

        //when
        Ewidencja resultOne = ewidencja.zapisKopii(ewidencja, "pierwsza", "zip");
        Ewidencja resultTwo = ewidencja.zapisKopii(ewidencja, "pierwsza", "gz");

        //then
        Assertions.assertEquals(ewidencja, resultOne);
        Assertions.assertEquals(ewidencja, resultTwo);
    }

    //1.10
    @Test
    public void odczytDanych_Successful_zip() throws IOException{
        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String imie2 = "Tadeusz";
        String nazwisko2 = "Drozda";
        BigDecimal wynagrodzenie2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();
        ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie,num, dodatek, karta , limit);
        ewidencja.addHandlowiec(pesel2, imie2, nazwisko2, wynagrodzenie2, num2, prowizja, limit2);

        //when
        ewidencja.zapisKopii(ewidencja,"druga", "zip");
        Ewidencja result = ewidencja.odczytDanych("druga.zip");

        //then
        Assertions.assertTrue(ewidencja.lista.get(0).equals(result.lista.get(0)));
        Assertions.assertTrue(ewidencja.lista.get(1).equals(result.lista.get(1)));
    }

    @Test
    public void odczytDanych_Successful_gz() throws IOException{
        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String imie2 = "Tadeusz";
        String nazwisko2 = "Drozda";
        BigDecimal wynagrodzenie2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();
        ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie,num, dodatek, karta , limit);
        ewidencja.addHandlowiec(pesel2, imie2, nazwisko2, wynagrodzenie2,num2, prowizja, limit2);

        //when
        ewidencja.zapisKopii(ewidencja, "druga", "gz");
        Ewidencja result = ewidencja.odczytDanych("druga.tar.gz");

        //then
        Assertions.assertTrue(ewidencja.lista.get(0).equals(result.lista.get(0)));
        Assertions.assertTrue(ewidencja.lista.get(1).equals(result.lista.get(1)));
    }

    //1.11 & 1.12
    @Test
    public void odczytDanych_Unsuccessful_throwsNoSuchFileException(){
        //given
        Ewidencja ewidencja = new Ewidencja();

        //then
        Assertions.assertThrows(NoSuchFileException.class, () -> ewidencja.odczytDanych("tenPlikNieIstnieje.zip"));
    }

    //2
    @RunWith(Parameterized.class)
    public static class sprawdzUnikalnoscPesel_TwoStrings {

        private final String peselOne;
        private final String peselTwo;
        private final boolean expected;

        @Parameters(name= " [{index}]: {0}, {1}")
         public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"98083006138","97072963579", true}, {"97072963579","97072963579", false},
                    {"96022134778","97072963579", true}, {"95032295336","96022134778", true},
                    {"94061153676","94061153676", false}, {"93051254237","93051254237", false},
                    {"92113032555","92113032555", false}, {"91120172544","91120172544", false},
                    {"90090265423","89031212347", true}, {"89031212347","90090265423", true},
            });
        }

        public sprawdzUnikalnoscPesel_TwoStrings(String inputOne,String inputTwo, boolean expected) {
            this.peselOne = inputOne;
            this.peselTwo = inputTwo;
            this.expected = expected;
        }

        @Test
        public void Test_sprawdzUnikalnoscPesel_TwoStrings() {
            //given
            Ewidencja ewidencja = new Ewidencja();
            ewidencja.listaPesel.add(peselOne);

            //then
            Assertions.assertEquals(expected, ewidencja.sprawdzUnikalnoscPesel(peselTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class sprawdzUnikalnoscPesel_FiveStrings {

        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"98083006138","96022134778","95032295336","94061153676","97072963579", true},
                    {"90072363579","97072963579","95032295336","94061153676","97072963579", false},
                    {"86022134778","93051254237","95032295336","94061153676","97072963579", true},
                    {"94061153676","93051254237","94061153676","95032295336","94061153676", false},
            });
        }

        private final String peselOne;
        private final String peselTwo;
        private final String peselThree;
        private final String peselFour;
        private final String peselFive;
        private final boolean expected;

        public sprawdzUnikalnoscPesel_FiveStrings(String inputOne,String inputTwo,String inputThree,String inputFour,String inputFive, boolean expected) {
            this.peselOne = inputOne;
            this.peselTwo = inputTwo;
            this.peselThree = inputThree;
            this.peselFour= inputFour;
            this.peselFive= inputFive;
            this.expected = expected;
        }

        @Test
        public void Test_sprawdzUnikalnoscPesel_FiveStrings() {
            //given
            Ewidencja ewidencja = new Ewidencja();
            ewidencja.listaPesel.add(peselOne);
            ewidencja.listaPesel.add(peselTwo);
            ewidencja.listaPesel.add(peselThree);
            ewidencja.listaPesel.add(peselFour);

            //then
            Assertions.assertEquals(expected, ewidencja.sprawdzUnikalnoscPesel(peselFive));
        }
    }

    @Test
    public void czyKtosJestZatrudniony_pustyKontener(){
        //given
        Ewidencja ewidencja = new Ewidencja();

        //then
        Assertions.assertFalse(ewidencja.czyKtosJestZatrudniony());
    }

    @Test
    public void czyKtosJestZatrudniony_KontenerZawieraPracownikow(){
        //given
        String pesel = "98083006138";
        String imie = "Tadeusz";
        String nazwisko = "Drozda";
        BigDecimal wynagrodzenie = new BigDecimal(3400);
        String num = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();
        ewidencja.addHandlowiec(pesel, imie, nazwisko, wynagrodzenie, num, prowizja, limit);

        //then
        Assertions.assertTrue(ewidencja.czyKtosJestZatrudniony());
    }

    @Test
    public void listaPracownikow_zapelnionyKontener(){
        //given
        String pesel = "98083006138";
        String imie = "Tadeusz";
        String nazwisko = "Drozda";
        BigDecimal wynagrodzenie = new BigDecimal(3400);
        String num = "505980868";
        BigDecimal prowizja = new BigDecimal(3);
        BigDecimal limit = new BigDecimal(250);

        Ewidencja ewidencja = new Ewidencja();
        ewidencja.addHandlowiec(pesel, imie, nazwisko, wynagrodzenie, num, prowizja, limit);

        //when
        Ewidencja.Pracownik test = ewidencja.listaPracownikow(0);

        //then
        Assertions.assertNotNull(test);
    }

    @Test
    public void listaPracownikow_pustyKontener(){
        //given
        Ewidencja ewidencja = new Ewidencja();

        //when
        Ewidencja.Pracownik result = ewidencja.listaPracownikow(0);

        //then
        Assertions.assertNull(result);
    }

    @Test
    public void addPracownik(){
        //given
        String pesel = "98083006138";
        String imie = "Mariusz";
        String nazwisko = "Budzynski";
        BigDecimal wynagrodzenie = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal dodatek = new BigDecimal(1500);
        String karta = "1";
        BigDecimal limit = new BigDecimal(3000);

        Ewidencja ewidencja = new Ewidencja();

        //when
        Ewidencja.Pracownik test = ewidencja.addDyrektor(pesel, imie, nazwisko, wynagrodzenie,num, dodatek, karta , limit);
        ewidencja.addPracownik(test);

        //then
        Assertions.assertEquals(test, ewidencja.lista.get(1));
        Assertions.assertEquals(2, ewidencja.liczbaPracownikow);
        Assertions.assertEquals(pesel, ewidencja.listaPesel.get(1));
    }

    @Test
    public void odczytDanych(){

    }

}