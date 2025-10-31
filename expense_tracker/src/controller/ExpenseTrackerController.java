package controller;

import view.ExpenseTrackerView;

import java.util.List;

import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import model.Transaction;
import model.TransactionFilter;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  
  // Other controller methods

  public boolean filter(String category) {
    
    if(!InputValidation.isValidCategory(category)) {
      return false;
    }

    TransactionFilter amountFilter = new CategoryFilter(category);
    List<Transaction> filteredTransactions = amountFilter.filter(model.getTransactions());
    view.refreshTable(filteredTransactions);
    model.setFilteredTransactions(filteredTransactions);

    return true;
  }

  public boolean filter(double value, String comparator) {
    if(!InputValidation.isValidAmount(value)) {
      return false;
    }
    TransactionFilter amountFilter = new AmountFilter(value, comparator);
    List<Transaction> filteredTransactions = amountFilter.filter(model.getTransactions());
    model.setFilteredTransactions(filteredTransactions);
    view.refreshTable(filteredTransactions);

    return true;
  }
}