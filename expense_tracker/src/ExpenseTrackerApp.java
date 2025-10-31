import javax.swing.JOptionPane;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Category Filter 
    view.getFilterComboBox()
        .addActionListener(e -> {view.selectFilter();});

    //Filter Button
    view.getFilterButton()
        .addActionListener(e -> {
        String selectedFilter = (String) view.getFilterComboBox()
                                             .getSelectedItem();
        boolean success = false;
        if(selectedFilter != null) {
          if(selectedFilter.equals("Category")){
          success = controller.filter((String) view.getCategoryComboBox().getSelectedItem());
        }
          else if(selectedFilter.equals("Amount")) {
          success = controller.filter(view.getComparatorValueField(), (String) view.getComparatorComboBox().getSelectedItem());
        } 
        else {
          controller.refresh();
          success = true;
        }
        
        if(!success) {
          JOptionPane.showMessageDialog(view, "Oops! Something went wrong.");
          view.toFront();
        }
      }
    });
  }
}