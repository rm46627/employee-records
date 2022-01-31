package reg;

import java.io.Serializable;
import java.math.BigDecimal;

class Director extends Employee implements Serializable {

    private BigDecimal bonus;
    private String businessCard;

    public Director(){}

    protected void setBonus(BigDecimal sum){
        bonus = sum;
    }
    protected void setBusinessCard(String newBusinessCard){
        businessCard = newBusinessCard;
    }

    protected BigDecimal getCommision(){ return null;}
    protected BigDecimal getBonus(){
        return bonus;
    }
    protected String getBusinessCard(){
        return businessCard;
    }
}