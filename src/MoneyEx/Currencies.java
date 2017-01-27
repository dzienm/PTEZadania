package MoneyEx;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mdziendzikowski on 2017-01-26.
 */
public enum Currencies {

    PLN("PLN"),
    USD("USD"),
    EUR("EUR"),
    CHF("CHF");

    private String symbol;

    private Currencies(String symb) {
        this.symbol = symb;
    }

    public String getSymbol() {
        return this.symbol;
    }


}


