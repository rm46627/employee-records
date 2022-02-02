import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import reg.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

class TestRegister {

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
    public void addTrader_emptyRegister(){

        //given
        String pesel = "98083006138";
        String name = "Tadeusz";
        String surname = "Drozda";
        BigDecimal salary = new BigDecimal(3400);
        String num = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit = new BigDecimal(250);

        Register register = new Register();

        //when
        Employee result = register.addTrader(pesel, name, surname, salary, num, commision, limit);

        //then
        Assertions.assertEquals(result, register.getEmployee(0));
        Assertions.assertEquals(1, register.getEmployeeCounter());
        Assertions.assertEquals(pesel, register.getPesel(0));
    }
    //1.2
    @Test
    public void addDirector_emptyRegister(){

        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        Register register = new Register();

        //when
        Employee result = register.addDirector(pesel, name, surname, salary,num, bonus, card , limit);

        //then
        Assertions.assertEquals(result, register.getEmployee(0));
        Assertions.assertEquals(1, register.getEmployeeCounter());
        Assertions.assertEquals(pesel, register.getPesel(0));
    }
    //1.3
    @Test
    public void addTrader_RegisterNotEmpty(){

        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Register register = new Register();

        //when
        register.addDirector(pesel, name, surname, salary,num, bonus, card , limit);
        Employee result = register.addTrader(pesel2, name2, surname2, salary2, num2, commision, limit2);

        //then
        Assertions.assertEquals(result, register.getEmployee(1));
        Assertions.assertEquals(2, register.getEmployeeCounter());
        Assertions.assertEquals(pesel2, register.getPesel(1));
    }
    //1.4
    @Test
    public void addDirector_RegisterNotEmpty() {
        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Register register = new Register();

        //when
        register.addTrader(pesel2, name2, surname2, salary2, num2, commision, limit2);
        Employee result = register.addDirector(pesel, name, surname, salary, num, bonus, card, limit);

        //then
        Assertions.assertEquals(result, register.getEmployee(1));
        Assertions.assertEquals(2, register.getEmployeeCounter());
        Assertions.assertEquals(pesel, register.getPesel(1));
    }
    //1.5
    @Test
    public void add10Employees(){

        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "91083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision2 = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        String pesel3 = "97083006140";
        String name3 = "Karolina";
        String surname3 = "Pytel";
        BigDecimal salary3 = new BigDecimal(1000);
        String num3 = "505980224";
        BigDecimal bonus3 = new BigDecimal(500);
        String card3 = "2";
        BigDecimal limit3 = new BigDecimal(9700);

        String pesel4 = "93123006141";
        String name4 = "Eliza";
        String surname4 = "Dla";
        BigDecimal salary4 = new BigDecimal(6454);
        String num4 = "505980868";
        BigDecimal commision4 = new BigDecimal(4);
        BigDecimal limit4 = new BigDecimal(120);

        String pesel5 = "91082103338";
        String name5 = "Edward";
        String surname5 = "Kielich";
        BigDecimal salary5 = new BigDecimal(1234);
        String num5 = "505980868";
        BigDecimal bonus5 = new BigDecimal(12500);
        String card5 = "3";
        BigDecimal limit5 = new BigDecimal(30);

        String pesel6 = "94051006122";
        String name6 = "Patrycja";
        String surname6 = "Kostecka";
        BigDecimal salary6 = new BigDecimal(8800);
        String num6 = "505980868";
        BigDecimal commision6 = new BigDecimal(1);
        BigDecimal limit6 = new BigDecimal(2000);

        String pesel7 = "96082806128";
        String name7 = "Michal";
        String surname7 = "Redkwa";
        BigDecimal salary7 = new BigDecimal(1000);
        String num7 = "505980868";
        BigDecimal bonus7 = new BigDecimal(150);
        String card7 = "4";
        BigDecimal limit7 = new BigDecimal(300);

        String pesel8 = "96010106139";
        String name8 = "Sebastian";
        String surname8 = "Dyba";
        BigDecimal salary8 = new BigDecimal(34400);
        String num8 = "505980868";
        BigDecimal commision8 = new BigDecimal(23);
        BigDecimal limit8 = new BigDecimal(0);

        String pesel9 = "98083001111";
        String name9 = "Anna";
        String surname9 = "Przybyszewska";
        BigDecimal salary9 = new BigDecimal(7000);
        String num9 = "505980868";
        BigDecimal bonus9 = new BigDecimal(1);
        String card9 = "5";
        BigDecimal limit9 = new BigDecimal(3);

        String pesel10 = "99023006139";
        String name10 = "Oskar";
        String surname10 = "Nagroda";
        BigDecimal salary10 = new BigDecimal(2000);
        String num10 = "505980868";
        BigDecimal commision10 = new BigDecimal(40);
        BigDecimal limit10 = new BigDecimal(2550);

        Register register = new Register();

        //when
        Employee result = register.addDirector(pesel, name, surname, salary, num, bonus, card, limit);
        Employee result2 = register.addTrader(pesel2, name2, surname2, salary2, num2, commision2, limit2);
        Employee result3 = register.addDirector(pesel3, name3, surname3, salary3, num3, bonus3, card3, limit3);
        Employee result4 = register.addTrader(pesel4, name4, surname4, salary4, num4, commision4, limit4);
        Employee result5 = register.addDirector(pesel5, name5, surname5, salary5, num5, bonus5, card5, limit5);
        Employee result6 = register.addTrader(pesel6, name6, surname6, salary6, num6, commision6, limit6);
        Employee result7 = register.addDirector(pesel7, name7, surname7, salary7, num7, bonus7, card7, limit7);
        Employee result8 = register.addTrader(pesel8, name8, surname8, salary8, num8, commision8, limit8);
        Employee result9 = register.addDirector(pesel9, name9, surname9, salary9, num9, bonus9, card9, limit9);
        Employee result10 = register.addTrader(pesel10, name10, surname10, salary10, num10, commision10, limit10);


        //then
        Assertions.assertEquals(10, register.getEmployeeCounter());
        Assertions.assertEquals(result, register.getEmployee(0));
        Assertions.assertEquals(pesel, register.getPesel(0));
        Assertions.assertEquals(result2, register.getEmployee(1));
        Assertions.assertEquals(pesel2, register.getPesel(1));
        Assertions.assertEquals(result3, register.getEmployee(2));
        Assertions.assertEquals(pesel3, register.getPesel(2));
        Assertions.assertEquals(result4, register.getEmployee(3));
        Assertions.assertEquals(pesel4, register.getPesel(3));
        Assertions.assertEquals(result5, register.getEmployee(4));
        Assertions.assertEquals(pesel5, register.getPesel(4));
        Assertions.assertEquals(result6, register.getEmployee(5));
        Assertions.assertEquals(pesel6, register.getPesel(5));
        Assertions.assertEquals(result7, register.getEmployee(6));
        Assertions.assertEquals(pesel7, register.getPesel(6));
        Assertions.assertEquals(result8, register.getEmployee(7));
        Assertions.assertEquals(pesel8, register.getPesel(7));
        Assertions.assertEquals(result9, register.getEmployee(8));
        Assertions.assertEquals(pesel9, register.getPesel(8));
        Assertions.assertEquals(result10, register.getEmployee(9));
        Assertions.assertEquals(pesel10, register.getPesel(9));
    }
    //1.6
    @Test
    public void removeEmployee_Trader_RegisterNotEmpty() {
        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Register register = new Register();

        //when
        Employee result = register.addDirector(pesel, name, surname, salary, num, bonus, card, limit);
        Employee fired = register.addTrader(pesel2, name2, surname2, salary2, num2, commision, limit2);
        register.removeEmployee(fired);

        //then
        Assertions.assertEquals(1, register.getEmployeeCounter());
        Assertions.assertEquals(result, register.getEmployee(0));
        Assertions.assertEquals(pesel, register.getPesel(0));
    }
    //1.7
    @Test
    public void removeEmployee_Director_RegisterNotEmpty(){
        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Register register = new Register();

        //when
        Employee fired = register.addDirector(pesel, name, surname, salary, num, bonus, card, limit);
        Employee result = register.addTrader(pesel2, name2, surname2, salary2, num2, commision, limit2);
        register.removeEmployee(fired);

        //then
        Assertions.assertEquals(1, register.getEmployeeCounter());
        Assertions.assertEquals(result, register.getEmployee(0));
        Assertions.assertEquals(pesel2, register.getPesel(0));
    }
    //1.8

    @ParameterizedTest
    @ValueSource(strings = {"98083006138", "97072963579", "96022134778", "95032295336", "94061153676", "93051254237", "92113032555", "91120172544", "90090265423", "89031212347" })
    void checkPesels_correct(String pesel) {
        Register validator = new Register();
        Assertions.assertTrue(validator.isPeselValid(pesel));
    }

    @ParameterizedTest
    @ValueSource(strings = {"99131111234", "98123264864", "01332264375", "22345623978", "66373965483", "78446777564", "33994353426", "95453125256", "92635456879", "00000000000" })
    void checkPesels_wrong(String pesel) {
        Register validator = new Register();
        Assertions.assertFalse(validator.isPeselValid(pesel));
    }

    @ParameterizedTest
    @MethodSource("provide2Parameters_peselUniqueness")
    void peselUniqueness_2strings(String pesel1, String pesel2, boolean result) {
        Register validator = new Register();
        validator.addPesel(pesel1);
        Assertions.assertEquals(result, validator.isPeselUnique(pesel2));
    }
    private static Stream<Arguments> provide2Parameters_peselUniqueness() {
        return Stream.of(
                Arguments.of("98083006138", "97072963579", true),
                Arguments.of("97072963579", "97072963579", false),
                Arguments.of("96022134778", "97072963579", true),
                Arguments.of("95032295336", "96022134778", true),
                Arguments.of("94061153676", "94061153676", false),
                Arguments.of("93051254237", "93051254237", false),
                Arguments.of("92113032555", "92113032555", false),
                Arguments.of("91120172544", "91120172544", false),
                Arguments.of("90090265423", "89031212347", true),
                Arguments.of("89031212347", "90090265423", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provide5Parameters_peselUniqueness")
    void peselUniqueness_5strings(String pesel1, String pesel2, String pesel3, String pesel4, String pesel5, boolean result) {
        Register validator = new Register();
        validator.addPesel(pesel1);
        validator.addPesel(pesel2);
        validator.addPesel(pesel3);
        validator.addPesel(pesel4);
        Assertions.assertEquals(result, validator.isPeselUnique(pesel5));
    }
    private static Stream<Arguments> provide5Parameters_peselUniqueness() {
        return Stream.of(
                Arguments.of("98083006138","96022134778","95032295336","94061153676","97072963579", true),
                Arguments.of("90072363579","97072963579","95032295336","94061153676","97072963579", false),
                Arguments.of("86022134778","93051254237","95032295336","94061153676","97072963579", true),
                Arguments.of("94061153676","93051254237","94061153676","95032295336","94061153676", false)
        );
    }

    @Test
    public void isRegistryEmpty_False(){
        //given
        Register register = new Register();

        register.addTrader("98083005544", "Andrzej", "Grabowski", new BigDecimal(5500), "505976372", new BigDecimal(5), new BigDecimal(3999));

        //then
        Assertions.assertFalse(register.isRegistryEmpty());
    }

    @Test
    public void isRegistryEmpty_True(){
        //given
        Register register = new Register();

        //then
        Assertions.assertTrue(register.isRegistryEmpty());
    }

    @Test
    public void employeeList_RegisterNotEmpty(){
        //given
        String pesel = "98083006138";
        String name = "Tadeusz";
        String surname = "Drozda";
        BigDecimal salary = new BigDecimal(3400);
        String num = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit = new BigDecimal(250);

        Register register = new Register();
        register.addTrader(pesel, name, surname, salary, num, commision, limit);

        //when
        Employee test = register.getEmployee(0);

        //then
        Assertions.assertNotNull(test);
    }

    @Test
    public void employeeList_emptyRegister(){
        //given
        Register register = new Register();

        //when
        boolean result = register.isRegistryEmpty();

        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void addEmployee(){
        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        Register register = new Register();

        //when
        Employee test = register.addDirector(pesel, name, surname, salary,num, bonus, card , limit);
        register.addEmployee(test);

        //then
        Assertions.assertEquals(test, register.getEmployee(1));
        Assertions.assertEquals(2, register.getEmployeeCounter());
        Assertions.assertEquals(pesel, register.getPesel(1));
    }

    @Test
    public void saveBackup_Successful() throws IOException, ExecutionException, InterruptedException {

        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Register register = new Register();
        register.addDirector(pesel, name, surname, salary,num, bonus, card , limit);
        register.addTrader(pesel2, name2, surname2, salary2, num2, commision, limit2);

        //when
        Register resultOne = register.saveRegistryBackup(register, "pierwsza", "zip");
        Register resultTwo = register.saveRegistryBackup(register, "pierwsza", "gz");

        //then
        Assertions.assertEquals(register, resultOne);
        Assertions.assertEquals(register, resultTwo);

        //end
        File file = new File("pierwsza.zip");
        file.delete();
        File file2 = new File("pierwsza.tar.gz");
        file2.delete();
    }

    @Test
    public void readBackup_Successful_zip() throws IOException, ExecutionException, InterruptedException {
        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Register register = new Register();
        register.addDirector(pesel, name, surname, salary,num, bonus, card , limit);
        register.addTrader(pesel2, name2, surname2, salary2, num2, commision, limit2);

        //when
        register.saveRegistryBackup(register,"druga", "zip");
        Register result = register.readRegistryBackup("druga.zip");

        //then
        Assertions.assertEquals(register.getEmployee(0).printData(), result.getEmployee(0).printData());
        Assertions.assertEquals(register.getEmployee(1).printData(), result.getEmployee(1).printData());

        //end
        File file = new File("druga.zip");
        file.delete();
    }

    @Test
    public void readBackup_Successful_gz() throws IOException, ExecutionException, InterruptedException {
        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Register register = new Register();
        register.addDirector(pesel, name, surname, salary,num, bonus, card , limit);
        register.addTrader(pesel2, name2, surname2, salary2,num2, commision, limit2);

        //when
        register.saveRegistryBackup(register, "druga", "gz");
        Register result = register.readRegistryBackup("druga.tar.gz");

        //then
        Assertions.assertEquals(register.getEmployee(0).printData(), result.getEmployee(0).printData());
        Assertions.assertEquals(register.getEmployee(1).printData(), result.getEmployee(1).printData());

        //end
        File file = new File("druga.tar.gz");
        file.delete();
    }

    @Test
    public void readBackup_Unsuccessful_throwsNoSuchFileException(){
        //given
        Register register = new Register();

        //then
        Assertions.assertThrows(NoSuchFileException.class, () -> register.readRegistryBackup("tenPlikNieIstnieje.zip"));

        //end
        File file = new File("tenPlikNieIstnieje");
        file.delete();
    }

    @Test
    public void addCustomDataToEmployee(){
        //given
        String pesel = "98083006138";
        String name = "Mariusz";
        String surname = "Budzynski";
        BigDecimal salary = new BigDecimal(13000);
        String num = "505980868";
        BigDecimal bonus = new BigDecimal(1500);
        String card = "1";
        BigDecimal limit = new BigDecimal(3000);

        String pesel2 = "98083006139";
        String name2 = "Tadeusz";
        String surname2 = "Drozda";
        BigDecimal salary2 = new BigDecimal(3400);
        String num2 = "505980868";
        BigDecimal commision = new BigDecimal(3);
        BigDecimal limit2 = new BigDecimal(250);

        Register register = new Register();
        register.addDirector(pesel, name, surname, salary,num, bonus, card , limit);
        register.addTrader(pesel2, name2, surname2, salary2,num2, commision, limit2);

        register.getEmployee(0).addCustom("city", "Szczecin");
        register.getEmployee(0).addCustom("hobby", "painting");
        register.getEmployee(1).addCustom("city", "Warszawa");
        register.getEmployee(1).addCustom("hobby", "tennis");

        Assertions.assertTrue(register.getEmployee(0).printData().contains("Szczecin"));
        Assertions.assertTrue(register.getEmployee(0).printData().contains("painting"));
        Assertions.assertTrue(register.getEmployee(1).printData().contains("Warszawa"));
        Assertions.assertTrue(register.getEmployee(1).printData().contains("tennis"));
    }

}