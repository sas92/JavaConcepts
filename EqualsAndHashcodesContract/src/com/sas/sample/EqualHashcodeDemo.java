package com.sas.sample;

import java.util.HashMap;
import java.util.Map;

/**
 * Reference: https://www.baeldung.com/java-equals-hashcode-contracts
 * <p>
 * The equals() method must be:
 * * reflexive: an object must equal itself
 * * symmetric: x.equals(y) must return the same result as y.equals(x)
 * * transitive: if x.equals(y) and y.equals(z) then also x.equals(z)
 * * consistent: the value of equals() should change only if a property that is contained in equals() changes (no randomness allowed)
 * <p>
 * All three criteria in the contract of hashCode() mention in some ways the equals() method:
 * * internal consistency: the value of hashCode() may only change if a property that is in equals() changes
 * * equals consistency: objects that are equal to each other must return the same hashCode
 * * collisions: unequal objects may have the same hashCode
 */

class Money {
    private int amount;
    private String currencyCode;

    public Money(int amount, String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Money))
            return false;
        Money other = (Money) obj;
        boolean amountEquals = this.amount == other.amount;
        boolean currencyCodeEquals = (null == this.currencyCode && null == other.currencyCode) ||
                (null != this.currencyCode && this.currencyCode.equals(other.currencyCode));
        return amountEquals == currencyCodeEquals;
    }

    @Override
    public int hashCode() {
        final int CONSTANT = 17;
        int result = CONSTANT;
        result += amount;
        if (currencyCode != null)
            result += currencyCode.hashCode() * CONSTANT;
        return result;
    }
}

class Voucher {
    private Money money;
    private String store;

    public Voucher(int amount, String currencyCode, String store) {
        this.money = new Money(amount, currencyCode);
        this.store = store;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Voucher))
            return false;
        Voucher other = (Voucher) obj;
        boolean moneyEquals = (null == this.money && null == other.money) ||
                (null != this.money && this.money.equals(other.money));
        boolean storeEquals = (null == this.store && null == other.store) ||
                (null != this.store && this.store.equals(other.store));
        return moneyEquals == storeEquals;
    }

    @Override
    public int hashCode() {
        final int CONSTANT = 31;
        int result = CONSTANT;
        if (money != null)
            result += money.hashCode() * CONSTANT;
        if (store != null)
            result += store.hashCode() * CONSTANT;
        return result;
    }
}

public class EqualHashcodeDemo {
    public static void main(String[] args) {
        // Check equals() contracts
        Money money = new Money(42, "USD");
        Voucher voucher = new Voucher(42, "USD", "Amazon");
        System.out.println(voucher.equals(money));  // false
        System.out.println(money.equals(voucher));  // false

        // Check hashCode() contracts
        Map<Money, String> users = new HashMap<>();
        users.put(new Money(42, "USD"), "Brian");
        System.out.println(users.get(money));       // Brian
    }
}
