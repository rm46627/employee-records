package register;

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

    public String printData() {
        StringBuilder out = new StringBuilder(String.format("Trader%n%s%ncommision: %s", printCommonData(),commision));
        if(customLabel.size() > 0) {
            out.append("\nCustom data:");
            for (int i = 0; i < customLabel.size(); i++) {
                out.append(String.format("%-20s: %s", customLabel.get(i), customData.get(i)));
            }
        }
        return out.toString();
    }
}