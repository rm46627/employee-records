package reg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Employee implements Serializable {

    private boolean headship = false;
    private String pesel;
    private String name;
    private String surname;
    private BigDecimal salary;
    private BigDecimal costLimit;
    private String phoneNumber;
    protected List<String> customLabel = new ArrayList<>();
    protected List<String> customData = new ArrayList<>();

    public Employee(){}

    protected void setHeadship(){
        headship = true;
    }
    protected void setPesel(String newPesel){
        pesel = newPesel;
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

    protected boolean hasHeadship(){
        return headship;
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
    protected String getCustomLabel(int i){ return customLabel.get(i);}
    protected String getCustomData(int i){ return customData.get(i);}
    protected String printCommonData(){
        return String.format("%s %s%npesel: %s%nsalary: %s%ncost limit: %s%nphone number: %s", name, surname, pesel, salary, costLimit, phoneNumber);
    }
    protected List<String> getCustom(int i){
        if(customLabel.size() > 0 && customData.size() > 0 && customLabel.size() > i && customData.size() > i){
            new ArrayList<>(Arrays.asList(customLabel.get(i), customData.get(i)));
        }
        return null;
    }

    public abstract String printData();

    public void addCustom(String label, String data){
        customLabel.add(label);
        customData.add(data);
    }
}