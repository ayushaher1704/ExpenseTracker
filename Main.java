public class Main {

    public static void main(String[] args) {

        ExpenseManager manager = new ExpenseManager();
        manager.loadTransactions();
        manager.displayTransactions();

System.out.println();
System.out.println("Total Income: ₹" + manager.getTotalIncome());
System.out.println("Total Expenses: ₹" + manager.getTotalExpenses());
System.out.println("Balance: ₹" + manager.getBalance());

        Transaction t1 = new Transaction(
            250,
            "Expense",
            "Food",
            "30/08/2026",
            "Lunch"
        );

        Transaction t2 = new Transaction(
            100,
            "Expense",
            "Travel",
            "30/08/2026",
            "Train"
        );Transaction t3 = new Transaction(
    20000,
    "Income",
    "Salary",
    "30/08/2026",
    "Monthly salary"
);


        manager.addTransaction(t1);
        manager.addTransaction(t2);
        manager.addTransaction(t3);
        manager.displayTransactions();
        System.out.println();
        System.out.println("Total Income: " + manager.getTotalIncome());
        System.out.println("Total Expenses: " + manager.getTotalExpenses());
        System.out.println("Balance: " + manager.getBalance());
        manager.saveTransactions();
    }
}