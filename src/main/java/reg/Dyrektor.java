package reg;

import java.io.Serializable;
import java.math.BigDecimal;

class Dyrektor extends Employee implements Serializable {

    private BigDecimal bonus;
    private String businessCard;

    public Dyrektor(){}

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