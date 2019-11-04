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
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        if (amount != other.amount)
            return false;
        if (currencyCode == null) {
            if (other.currencyCode != null)
                return false;
        } else if (!currencyCode.equals(other.currencyCode)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int CONSTANT = 17;
        int result = 1;
        result = CONSTANT * result + amount;
        result = CONSTANT * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
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
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Voucher other = (Voucher) obj;
        if (money == null) {
            if (other.money != null)
                return false;
        } else if (!money.equals(other.money))
            return false;
        if (store == null) {
            if (other.store != null)
                return false;
        } else if (!store.equals(other.store))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int CONSTANT = 31;
        int result = 1;
        result = CONSTANT * result + ((money == null) ? 0 : money.hashCode());
        result = CONSTANT * result + ((store == null) ? 0 : store.hashCode());
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
