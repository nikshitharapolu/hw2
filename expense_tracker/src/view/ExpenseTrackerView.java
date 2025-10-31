package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;

  private JComboBox<String> filterComboBox;
  private JButton filterButton;
  private JPanel filterPanel;

  JComboBox<String> categoryComboBox;
  JTextField comparatorValueField;
  JComboBox<String> comparatorComboBox;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(800, 600); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    filterComboBox = new JComboBox<>(new String[]{"Select Filter", "Amount", "Category"});
    filterButton = new JButton("Filter");

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);


    filterPanel = new JPanel();
    filterPanel.add(new JLabel("Filter"));
    filterPanel.add(filterComboBox);
    filterPanel.add(filterButton);

    // Set up the main panel to use BoxLayout for stacking inputPanel and filterPanel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));  // Stack components vertically
    mainPanel.add(inputPanel);  // Add the input panel
    mainPanel.add(filterPanel);  // Add the filter panel below filter combo box
  
    // Add panels to frame
    add(mainPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);

  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }

  public void selectFilter() {

    //To Remove everything from filterPanel except the filterComboBox
    filterPanel.removeAll();
    filterPanel.add(new JLabel("Filter"));
    filterPanel.add(filterComboBox);

    // To get the selected filter type 
    String filter = (String) filterComboBox.getSelectedItem();

    if(filter.equals("Category"))
    {

      categoryComboBox = new JComboBox<>(new String[]{"food", "travel", "bills", "entertainment", "other"});

      filterPanel.add(new JLabel("Category"));
      filterPanel.add(categoryComboBox);

    }
    else if (filter.equals("Amount")) 
    {
      comparatorValueField = new JTextField(10);
      comparatorComboBox = new JComboBox<>(new String[]{"=", "<", "<=", ">" , ">="});

      filterPanel.add(new JLabel("Comparator"));
      filterPanel.add(comparatorComboBox);
      filterPanel.add(new JLabel("Value"));
      filterPanel.add(comparatorValueField);

    }

    //Filter Button
    filterPanel.add(Box.createHorizontalStrut(20));
    filterPanel.add(filterButton);

    //To refresh UI to reflect the changes
    filterPanel.revalidate();
    filterPanel.repaint();
  }

  // Getter and setter methods for the components
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {

    if(amountField.getText().isEmpty()) 
    {
      return 0;
    }
    else 
    {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }

  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public JComboBox<String> getFilterComboBox() {
    return filterComboBox;
  }

  public void setFilterComboBox(JComboBox<String> filterComboBox) {
    this.filterComboBox = filterComboBox;
  }

  public JButton getFilterButton() {
    return filterButton;
  }

  public void setFilterButton(JButton filterButton) {
    this.filterButton = filterButton;
  }

  public JComboBox<String> getCategoryComboBox() {
    return categoryComboBox;
  }

  public void setCategoryComboBox(JComboBox<String> categoryComboBox) {
    this.categoryComboBox = categoryComboBox;
  }

  public JComboBox<String> getComparatorComboBox() {
    return comparatorComboBox;
  }

  public void setComparatorComboBox(JComboBox<String> comparatorComboBox) {
    this.comparatorComboBox = comparatorComboBox;
  }

  public double getComparatorValueField() {

    if(comparatorValueField.getText().isEmpty()) 
    {
      return 0;
    }
    else 
    {
      double amount = Double.parseDouble(comparatorValueField.getText());
      return amount;
    }

  }

  public void setComparatorValueField(JTextField comparatorValueField) {
    this.comparatorValueField = comparatorValueField;
  }

}
