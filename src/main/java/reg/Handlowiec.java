package reg;

import java.io.Serializable;
import java.math.BigDecimal;

class Handlowiec extends Employee implements Serializable {
    private BigDecimal commision;

    public Handlowiec(){}

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