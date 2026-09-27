import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Gui {
    public static void filterTable(JTextField searchField,
                               TableRowSorter<DefaultTableModel> sorter) {

    String searchText = searchField.getText();

    if (searchText.trim().length() == 0) {

        sorter.setRowFilter(null);

    } else {

        sorter.setRowFilter(
                RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(searchText))
        );
    }
}
    static ExpenseManager manager = new ExpenseManager();

    public static void main(String[] args) {
        manager.loadTransactions();

        // Main window
        JFrame frame = new JFrame("Expense Tracker");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Background
        frame.getContentPane().setBackground(new Color(245, 247, 250));

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel("EXPENSE TRACKER");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(270, 30, 300, 50);

        frame.add(title);

        // =========================
        // BALANCE CARD
        // =========================

        JPanel balanceCard = new JPanel();
        balanceCard.setBounds(50, 110, 210, 120);
        balanceCard.setBackground(Color.WHITE);
        balanceCard.setLayout(null);

        JLabel balance = new JLabel("<html>Balance<br>₹" + manager.getBalance() + "</html>");
        balance.setFont(new Font("Arial", Font.BOLD, 18));
        balance.setBounds(20, 20, 170, 80);

        balanceCard.add(balance);
        frame.add(balanceCard);

        // =========================
        // INCOME CARD
        // =========================

        JPanel incomeCard = new JPanel();
        incomeCard.setBounds(295, 110, 210, 120);
        incomeCard.setBackground(Color.WHITE);
        incomeCard.setLayout(null);

        JLabel income = new JLabel("<html>Income<br>₹" + manager.getTotalIncome() + "</html>");
        income.setFont(new Font("Arial", Font.BOLD, 18));
        income.setBounds(20, 20, 170, 80);

        incomeCard.add(income);
        frame.add(incomeCard);

        // =========================
        // EXPENSE CARD
        // =========================

        JPanel expenseCard = new JPanel();
        expenseCard.setBounds(540, 110, 210, 120);
        expenseCard.setBackground(Color.WHITE);
        expenseCard.setLayout(null);

        JLabel expenses = new JLabel("<html>Expenses<br>₹" + manager.getTotalExpenses() + "</html>");
        expenses.setFont(new Font("Arial", Font.BOLD, 18));
        expenses.setBounds(20, 20, 170, 80);

        expenseCard.add(expenses);
        frame.add(expenseCard);

        DefaultTableModel tableModel = new DefaultTableModel();

tableModel.addColumn("Amount");
tableModel.addColumn("Type");
tableModel.addColumn("Category");
tableModel.addColumn("Date");
tableModel.addColumn("Description");
JLabel searchLabel = new JLabel("Search:");
searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
searchLabel.setBounds(50, 315, 60, 30);
frame.add(searchLabel);

JTextField searchField = new JTextField();
searchField.setBounds(110, 315, 300, 30);
frame.add(searchField);

JTable transactionTable = new JTable(tableModel);

TableRowSorter<DefaultTableModel> sorter =
        new TableRowSorter<>(tableModel);
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        filterTable(searchField, sorter);
    }

    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        filterTable(searchField, sorter);
    }

    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        filterTable(searchField, sorter);
    }

});


transactionTable.setRowSorter(sorter);
for (Transaction transaction : manager.transactions) {

    tableModel.addRow(new Object[]{
        transaction.amount,
        transaction.type,
        transaction.category,
        transaction.date,
        transaction.description
    });
}
JScrollPane scrollPane = new JScrollPane(transactionTable);
scrollPane.setBounds(50, 350, 700, 180);
frame.add(scrollPane);
        // =========================
        // ADD TRANSACTION BUTTON
        // =========================

JButton addButton = new JButton("+ ADD TRANSACTION");

addButton.setFont(new Font("Arial", Font.BOLD, 16));
addButton.setBounds(285, 270, 230, 50);

frame.add(addButton);

addButton.addActionListener(e -> {
    JFrame transactionFrame = new JFrame("Add Transaction");
    transactionFrame.setSize(450, 420);
    transactionFrame.setLayout(null);
    transactionFrame.setLocationRelativeTo(frame);

    JLabel typeLabel = new JLabel("Type:");
    typeLabel.setFont(new Font("Arial", Font.BOLD, 16));
    typeLabel.setBounds(50, 90, 100, 30);
    transactionFrame.add(typeLabel);

    String[] types = {"Expense", "Income"};
    javax.swing.JComboBox<String> typeBox = new javax.swing.JComboBox<>(types);
    typeBox.setBounds(160, 90, 200, 30);
    transactionFrame.add(typeBox);

    JLabel amountLabel = new JLabel("Amount:");
    amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
    amountLabel.setBounds(50, 40, 100, 30);
    transactionFrame.add(amountLabel);

    javax.swing.JTextField amountField = new javax.swing.JTextField();
    amountField.setBounds(160, 40, 200, 30);
    transactionFrame.add(amountField);

    transactionFrame.setVisible(true);
    JLabel categoryLabel = new JLabel("Category:");
categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
categoryLabel.setBounds(50, 140, 100, 30);
 JButton saveButton = new JButton("SAVE TRANSACTION");

saveButton.setFont(new Font("Arial", Font.BOLD, 14));
saveButton.setBounds(120, 300, 210, 45);

transactionFrame.add(saveButton);
transactionFrame.add(categoryLabel);

String[] categories = {
    "Food",
    "Travel",
    "Shopping",
    "Bills",
    "Education",
    "Salary",
    "Other"
};

javax.swing.JComboBox<String> categoryBox =
        new javax.swing.JComboBox<>(categories);

categoryBox.setBounds(160, 140, 200, 30);

transactionFrame.add(categoryBox);

JLabel dateLabel = new JLabel("Date:");
dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
dateLabel.setBounds(50, 190, 100, 30);

transactionFrame.add(dateLabel);

javax.swing.JTextField dateField = new javax.swing.JTextField();
dateField.setBounds(160, 190, 200, 30);

transactionFrame.add(dateField);

JLabel descriptionLabel = new JLabel("Description:");
descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
descriptionLabel.setBounds(50, 240, 100, 30);

transactionFrame.add(descriptionLabel);

javax.swing.JTextField descriptionField = new javax.swing.JTextField();
descriptionField.setBounds(160, 240, 200, 30);

transactionFrame.add(descriptionField);

saveButton.addActionListener(event -> {
  String amountText = amountField.getText();

double amount;

try {
    amount = Double.parseDouble(amountText);
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(
        frame,
        "Please enter a valid amount."
    );
    return;
}

    String type = (String) typeBox.getSelectedItem();
    String category = (String) categoryBox.getSelectedItem();
    String date = dateField.getText();
    String description = descriptionField.getText();

    Transaction transaction = new Transaction(
        amount,
        type,
        category,
        date,
        description
    );
    manager.addTransaction(transaction);
    tableModel.addRow(new Object[]{
        amount,
        type,
        category,
        date,
        description
});
    manager.saveTransactionsInBackground();
    
    System.out.println("Amount entered: " + amountText);
});
});
JButton deleteButton = new JButton("DELETE TRANSACTION");

deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
deleteButton.setBounds(285, 530, 230, 45);

frame.add(deleteButton);
deleteButton.addActionListener(e -> {

    int selectedRow = transactionTable.getSelectedRow();

    if (selectedRow != -1) {

        manager.deleteTransaction(selectedRow);
        tableModel.removeRow(selectedRow);
        manager.saveTransactions();

    }

});
JButton editButton = new JButton("EDIT TRANSACTION");

editButton.setFont(new Font("Arial", Font.BOLD, 14));
editButton.setBounds(530, 530, 230, 45);

frame.add(editButton);
editButton.addActionListener(e -> {

    int selectedRow = transactionTable.getSelectedRow();

    if (selectedRow != -1) {

        Transaction selectedTransaction = manager.transactions.get(selectedRow);
        JFrame editFrame = new JFrame("Edit Transaction");
editFrame.setSize(450, 420);
editFrame.setLayout(null);
editFrame.setLocationRelativeTo(frame);
JLabel editAmountLabel = new JLabel("Amount:");
editAmountLabel.setBounds(50, 40, 100, 30);
editFrame.add(editAmountLabel);

JTextField editAmountField =
        new JTextField(String.valueOf(selectedTransaction.amount));
editAmountField.setBounds(150, 40, 220, 30);
editFrame.add(editAmountField);
JLabel editTypeLabel = new JLabel("Type:");
editTypeLabel.setBounds(50, 90, 100, 30);
editFrame.add(editTypeLabel);

String[] editTypes = {"Expense", "Income"};

JComboBox<String> editTypeBox =
        new JComboBox<>(editTypes);

editTypeBox.setSelectedItem(selectedTransaction.type);
editTypeBox.setBounds(150, 90, 220, 30);

editFrame.add(editTypeBox);
JLabel editCategoryLabel = new JLabel("Category:");
editCategoryLabel.setBounds(50, 140, 100, 30);
editFrame.add(editCategoryLabel);

String[] editCategories = {
    "Food",
    "Travel",
    "Shopping",
    "Bills",
    "Education",
    "Salary",
    "Other"
};

JComboBox<String> editCategoryBox =
        new JComboBox<>(editCategories);

editCategoryBox.setSelectedItem(selectedTransaction.category);
editCategoryBox.setBounds(150, 140, 220, 30);

editFrame.add(editCategoryBox);
JLabel editDateLabel = new JLabel("Date:");
editDateLabel.setBounds(50, 190, 100, 30);
editFrame.add(editDateLabel);

JTextField editDateField =
        new JTextField(selectedTransaction.date);

editDateField.setBounds(150, 190, 220, 30);

editFrame.add(editDateField);
JLabel editDescriptionLabel = new JLabel("Description:");
editDescriptionLabel.setBounds(50, 240, 100, 30);
editFrame.add(editDescriptionLabel);

JTextField editDescriptionField =
        new JTextField(selectedTransaction.description);

editDescriptionField.setBounds(150, 240, 220, 30);

editFrame.add(editDescriptionField);
JButton updateButton = new JButton("UPDATE TRANSACTION");

updateButton.setFont(new Font("Arial", Font.BOLD, 14));
updateButton.setBounds(120, 300, 210, 45);

editFrame.add(updateButton);

updateButton.addActionListener(evt -> {

    double updatedAmount =
            Double.parseDouble(editAmountField.getText());

    String updatedType =
            (String) editTypeBox.getSelectedItem();

    String updatedCategory =
            (String) editCategoryBox.getSelectedItem();

    String updatedDate =
            editDateField.getText();

    String updatedDescription =
            editDescriptionField.getText();
            Transaction updatedTransaction = new Transaction(
        updatedAmount,
        updatedType,
        updatedCategory,
        updatedDate,
        updatedDescription
);
manager.updateTransaction(selectedRow, updatedTransaction);
tableModel.setValueAt(updatedAmount, selectedRow, 0);
tableModel.setValueAt(updatedType, selectedRow, 1);
tableModel.setValueAt(updatedCategory, selectedRow, 2);
tableModel.setValueAt(updatedDate, selectedRow, 3);
tableModel.setValueAt(updatedDescription, selectedRow, 4);
manager.saveTransactions();

});
editFrame.setVisible(true);

    }
   

});


        // Show window
        frame.setVisible(true);
    }
}