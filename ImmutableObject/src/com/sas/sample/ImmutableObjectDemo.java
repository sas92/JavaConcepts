package com.sas.sample;

/**
 * Reference: https://www.baeldung.com/java-immutable-object
 */

final class Money {
    private final int amount;
    private final String currencyCode;

    public Money(final int amount, final String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}

public class ImmutableObjectDemo {
    public static void main(String[] args) {
        Money money = new Money(42, "USD");
        System.out.println(money);
    }
}
