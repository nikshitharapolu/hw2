package model;

import java.util.List;
import java.util.stream.Collectors;

/*
 * To filter the list of transactions based on the specific amount entered.
 */
public class AmountFilter implements TransactionFilter {
    private final double amount;

    public AmountFilter(double amount) {
        this.amount = amount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream()
                           .filter(t -> t.getAmount() == amount)
                           .collect(Collectors.toList());
    }
}