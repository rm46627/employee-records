package registerLogic;

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
    protected BigDecimal getBonus(){
        return bonus;
    }
    protected String getBusinessCard(){
        return businessCard;
    }

    public String printData() {
        StringBuilder out = new StringBuilder(String.format("Director%n%s%nbonus: %s%nbussines card: %s", printCommonData(), bonus, businessCard));
        if(customLabel.size() > 0) {
            out.append("\ncustom data:\n");
            for (int i = 0; i < customLabel.size(); i++) {
                out.append(String.format("%20s: %s", customLabel.get(i), customData.get(i)));
            }
        }
        return out.toString();
    }
}