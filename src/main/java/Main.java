import reg.Register;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
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
        Register resultTwo = register.saveRegistryBackup(register, "pierwsza", "gz");
        File file = new File("pierwsza.tar.gz");
        file.delete();
    }
}