import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ExpenseManager {

    ArrayList<Transaction> transactions = new ArrayList<>();
   public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public void saveTransactions() {

    try {

        FileWriter writer = new FileWriter("data/transactions.txt");

        for (Transaction transaction : transactions) {

            writer.write(
                transaction.amount + "," +
                transaction.type + "," +
                transaction.category + "," +
                transaction.date + "," +
                transaction.description + "\n"
            );
        }

        writer.close();

        System.out.println("Transactions saved successfully!");

    } catch (IOException e) {

        System.out.println("Error saving transactions.");
    }
}
public void loadTransactions() {

    try {

        BufferedReader reader =
                new BufferedReader(new FileReader("data/transactions.txt"));

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",",-1);

            double amount = Double.parseDouble(data[0]);
            String type = data[1];
            String category = data[2];
            String date = data[3];
            String description = data[4];

            Transaction transaction =
                    new Transaction(amount, type, category, date, description);

            transactions.add(transaction);
        }

        reader.close();

        System.out.println("Transactions loaded successfully!");

    } catch (IOException e) {

        System.out.println("Error loading transactions.");
    }
}

    public void displayTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println("--------------------");
            System.out.println("Amount: " + transaction.amount);
            System.out.println("Type: " + transaction.type);
            System.out.println("Category: " + transaction.category);
            System.out.println("Date: " + transaction.date);
            System.out.println("Description: " + transaction.description);
        }
    }

    public double getTotalIncome() {
        double totalIncome = 0;

        for (Transaction transaction : transactions) {
            if (transaction.type.equals("Income")) {
                totalIncome = totalIncome + transaction.amount;
            }
        }

        return totalIncome;
    }
    public double getTotalExpenses() {

    double totalExpenses = 0;

    for (Transaction transaction : transactions) {

        if (transaction.type.equals("Expense")) {
            totalExpenses = totalExpenses + transaction.amount;
        }
    }

    return totalExpenses;
}
public double getBalance() {

    return getTotalIncome() - getTotalExpenses();
}
public void deleteTransaction(int index) {

    transactions.remove(index);
}
public void updateTransaction(int index, Transaction transaction) {

    transactions.set(index, transaction);
}
}