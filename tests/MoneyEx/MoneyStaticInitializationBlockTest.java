package MoneyEx;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * Created by mdziendzikowski on 2017-01-27.
 */
public class MoneyStaticInitializationBlockTest {

    @Test
    public void testStaticBlockofClassInitialization_whenInitializingClass_ExchangeRatesSetDefault() {

        Money m;

        for (Currencies curr : Currencies.values()) {
            m = new Money(new BigDecimal(1), curr);
            assertTrue(new BigDecimal(1).equals(m.getExchangeRate()));
        }
    }

}
