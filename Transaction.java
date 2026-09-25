public class Transaction {
    double amount;
    String type;
    String category;
    String date;
    String description;
      public Transaction(double amount, String type, String category,
                       String date, String description) {

        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
        this.description = description;
    } 
}