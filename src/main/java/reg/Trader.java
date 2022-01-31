package reg;

import java.io.Serializable;
import java.math.BigDecimal;

class Trader extends Employee implements Serializable {
    private BigDecimal commision;

    public Trader(){}

    protected void setCommision(BigDecimal percent){
        commision = percent;
    }

    protected BigDecimal getCommision(){
        return commision;
    }
    protected BigDecimal getBonus(){ return null; }
    protected String getBusinessCard(){
        return null;
    }
}