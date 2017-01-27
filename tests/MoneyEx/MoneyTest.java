package MoneyEx;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class MoneyTest {

    private Money m12CHF;
    private Money m14CHF;


    @Before
    public void setUp() throws Exception {
        m12CHF = new Money(new BigDecimal("12"), Currencies.CHF);
        m14CHF = new Money(new BigDecimal("14"), Currencies.CHF);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testEquals() {

        assertTrue(!m12CHF.equals(null));
        assertTrue(m12CHF.equals(m12CHF));
        assertTrue(m12CHF.equals(new Money(new BigDecimal("12"), Currencies.CHF)));
        assertFalse(m12CHF.equals(m14CHF));
        assertTrue(m12CHF.equals(new Money(new BigDecimal("12.00"), Currencies.CHF)));
        assertTrue(m12CHF.equals(new Money(new BigDecimal("12.005"), Currencies.CHF)));
        assertTrue(m12CHF.equals(new Money(new BigDecimal("12.009"), Currencies.CHF)));
    }

    @Test
    public void testSimpleAdd() throws CurrencyDifferenceException {

        Money expected = new Money(new BigDecimal("26.0"), Currencies.CHF);
        Money result = m12CHF.add(m14CHF); //
        assertTrue(expected.equals(result)); //
    }

    @Test(expected = CurrencyDifferenceException.class)
    public void addingTwoDifferentCurrenciesThrows() throws CurrencyDifferenceException {
        Money a = new Money(new BigDecimal(12), Currencies.PLN);
        Money b = new Money(new BigDecimal("14"), Currencies.CHF);

        a.add(b);
    }


    @Test
    public void toStringMethodReturnsAmountAndCurrencySymbol() {
        Money m = new Money(new BigDecimal(15), Currencies.PLN);
        String mString = "15.00 PLN";
        assertTrue(mString.equals(m.toString()));

        m = new Money(new BigDecimal("15"), Currencies.PLN);
        assertTrue(mString.equals(m.toString()));

        m = new Money(new BigDecimal("15.0"), Currencies.PLN);
        assertTrue(mString.equals(m.toString()));

        m = new Money(new BigDecimal("15.00"), Currencies.PLN);
        assertTrue(mString.equals(m.toString()));

        m = new Money(new BigDecimal("15.003"), Currencies.PLN);
        assertTrue(mString.equals(m.toString()));

        m = new Money(new BigDecimal("15.006"), Currencies.PLN);
        assertTrue(mString.equals(m.toString()));

        m = new Money(new BigDecimal("15.009"), Currencies.PLN);
        assertTrue(mString.equals(m.toString()));
    }

    //ponizszy test powinien zostac zmodyfikowany po dodaniu metod wczytujacych kursy walut z zewnetrznego zrodla - czy ten powinien byc na poczatku??? zwlaszcza czy ma wlasciwy sens jak jest blok Before???
    //nie dziala jak sie umiesci za blokiem zmienajacym ExchangeRate - nie dziala wogole jak sa inne testy zmieniajace cos w polu statycznym
    //jak w takim razie testowac statyczne pola - w oddzielnych klasach testowych?
//    @Test
//    public void testStaticBlockofClassInitialization_whenInitializingClass_ExchangeRatesSetDefault() {
//
//        Money m;
//
//        for (Currencies curr : Currencies.values()) {
//            m = new Money(new BigDecimal(1), curr);
//            assertTrue(new BigDecimal(1).equals(m.getExchangeRate()));
//        }
//    }

    @Test
    public void amountMultiplyTest() {
        Money m = new Money(new BigDecimal(10), Currencies.USD);
        BigDecimal ExchangeRate = new BigDecimal("2.5");
        m.amountMultiply(ExchangeRate);
        m.equals(new Money(new BigDecimal("25"), Currencies.USD));

        ExchangeRate = new BigDecimal("1.41");
        m = new Money(new BigDecimal(10), Currencies.USD);
        m.amountMultiply(ExchangeRate);
        m.equals(new Money(new BigDecimal("14.1"), Currencies.USD));
    }


    @Test
    public void AttemptingToSetExchangeRateOfBaseCurrency_IllegalExchangeRateExceptionIsReturned() {
        Money m = new Money(new BigDecimal(532), Currencies.PLN);
        try {
            m.setExchangeRateRelativelyToBaseValue(new BigDecimal(4));
            fail("Expected exception"); //jedyny sposob zeby to nie zostalo wywolane to zeby s.pop rzucilo wyjatkiem
        } catch (IllegalExchangeRateException e) {
        }
    }

    @Test
    public void AttemptingToSetNegativeExchangeRateOfCurrency_IllegalExchangeRateExceptionIsReturned() {
        Money m = new Money(new BigDecimal(532), Currencies.USD);
        try {
            m.setExchangeRateRelativelyToBaseValue(new BigDecimal(-14.34));
            fail("Expected exception"); //jedyny sposob zeby to nie zostalo wywolane to zeby s.pop rzucilo wyjatkiem
        } catch (IllegalExchangeRateException e) {
        }
    }

    @Test   //test od razu funkcji getExchangeRate - ciezko jest testowac getters i setters oddzielnie
    public void testOfOneTimeCallingSetExchangeRateMethodForSingleMoneyDifferentThanBaseValue() throws IllegalExchangeRateException {
        Money m = new Money(new BigDecimal(2341), Currencies.CHF);
        String aRate = "2.456";
        m.setExchangeRateRelativelyToBaseValue(new BigDecimal(aRate));
        assertTrue(m.getExchangeRate().toString().equals(aRate));
    }

    @Test
    public void SettingExchangeRateForASecondTime_theRateIsChangedForAllGivenCurrencyInstances() throws IllegalExchangeRateException {
        Money m1 = new Money(new BigDecimal(121), Currencies.CHF);
        m1.setExchangeRateRelativelyToBaseValue(new BigDecimal("1.234"));

        Money m2 = new Money(new BigDecimal(153),Currencies.CHF);
        m2.setExchangeRateRelativelyToBaseValue(new BigDecimal("2.3567"));

        assertTrue(m2.getExchangeRate().equals(m1.getExchangeRate()));
    }

    @Test
    public void CurrencyConverterTests() throws IllegalExchangeRateException{
        Money mUSD = new Money(new BigDecimal(15),Currencies.USD);
        mUSD.setExchangeRateRelativelyToBaseValue(new BigDecimal("2"));

        Money mEUR = new Money(new BigDecimal("7.5"),Currencies.EUR);
        mEUR.setExchangeRateRelativelyToBaseValue(new BigDecimal("4"));
        assertTrue(mEUR.equals(mUSD.convertTo(Currencies.EUR)));

        Money mPLN = new Money(new BigDecimal(30),Currencies.PLN);
        assertTrue(mPLN.equals(mUSD.convertTo(Currencies.PLN)));
    }

    @Test
    public void TestOfMoneyComparaTo() throws IllegalExchangeRateException{

        Money m10PLN = new Money(new BigDecimal(10),Currencies.PLN);
        assertEquals(0,m10PLN.compareTo(m10PLN));

        Money m20PLN = new Money(new BigDecimal(20),Currencies.PLN);
        assertTrue(m20PLN.compareTo(m10PLN)>0);

        Money m10USD = new Money(new BigDecimal(10),Currencies.USD);
        m10USD.setExchangeRateRelativelyToBaseValue(new BigDecimal("4"));
        assertTrue(m10USD.compareTo(m10PLN)>0);

        m10USD.setExchangeRateRelativelyToBaseValue(new BigDecimal("4.05"));
        assertTrue(m10USD.compareTo(m10PLN)>0);
    }

    //testy puste (dla 100% pokrycia linii kodu)
    @Test
    public void CurrencyDifferenceException_defaultConstructorTest() {
        CurrencyDifferenceException exceptionTest = new CurrencyDifferenceException();
    }

    //testy puste (dla 100% pokrycia linii kodu)
    @Test
    public void IllegalExchangeRateException_defaultConstructorTest() {
        IllegalExchangeRateException exceptionTest = new IllegalExchangeRateException();
    }


}