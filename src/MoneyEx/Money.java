package MoneyEx;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

class Money{
    //private int amount;
    private Currencies currency;
    private BigDecimal amount;

    private static HashMap<Currencies, BigDecimal> currenciesExchangeRate;

    static {
        currenciesExchangeRate = new HashMap<>();

        currenciesExchangeRate.put(Currencies.PLN, new BigDecimal(1));   //tworzenie domyslnej wartosci dla waluty bazowej

        //getExchangeRatesFromMarket - to moglaby byc metoda odczytujaca kursy z zewnetrznego zrodla, tymczasem domyslnie ustawione na 1
        for (Currencies curr : Currencies.values()) {
            currenciesExchangeRate.put(curr, new BigDecimal(1));
        }
    }

    public Money(BigDecimal anAmount, Currencies currency) {
        this.amount = anAmount;        //zaklada sie ze dwie ostatnie cyfry tworza ulamkowa czesc jednostki pienieznej
        this.amount.setScale(2, BigDecimal.ROUND_DOWN);
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public Money add(Money m) throws CurrencyDifferenceException {
        if (this.getCurrency() == m.getCurrency()) {
            return new Money(getAmount().add(m.getAmount()), getCurrency());
        } else {
            throw new CurrencyDifferenceException();
        }
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Money) {
            Money a = (Money) anObject;

            return a.toString().equals(this.toString());
        }
        return false;

    }

    public void setExchangeRateRelativelyToBaseValue(BigDecimal ExchangeRate) throws IllegalExchangeRateException {
        if (currency.equals(Currencies.PLN)) {
            throw new IllegalExchangeRateException();
        }
        else if(ExchangeRate.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalExchangeRateException();
        }

        else{
            currenciesExchangeRate.put(currency, ExchangeRate);
        }
    }

    @Override
    public String toString() {

        return amount.setScale(2, BigDecimal.ROUND_DOWN).toPlainString() + " " + currency.getSymbol();

    }

    public void amountMultiply(BigDecimal aRate) {

        BigDecimal newAmount = this.amount;
        newAmount.multiply(aRate);
        newAmount.setScale(2, BigDecimal.ROUND_DOWN);

        this.amount = newAmount;
    }

    public BigDecimal getExchangeRate() {
        return this.currenciesExchangeRate.get(this.currency);
    }


    public Money convertTo(Currencies newCurrency) {

        Money convertedGeneric = new Money(new BigDecimal(0),newCurrency);

        Currencies convertedCurrency = newCurrency;
        BigDecimal convertedAmount = this.getAmount().multiply(this.getExchangeRate()).divide(convertedGeneric.getExchangeRate(), 2, RoundingMode.DOWN);

        return new Money(convertedAmount,newCurrency);
    }

    public int compareTo(Money comparedAmount) {

        return this.amount.compareTo(comparedAmount.convertTo(this.currency).getAmount());
    }



}