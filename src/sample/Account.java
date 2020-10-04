package sample;

import java.util.ArrayList;
import java.util.Date;

public class Account
{
    String name;
    int id;
    int balance;
    ArrayList<Transaction> transactions;

    public Account(String name, int id, int balance)
    {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    class Transaction
    {
        Date date;
        char type;
        double amount;
        double balance;
        String description;

        public Transaction(Date date, char type, double amount, double balance, String description)
        {
            this.date = date;
            this.type = type;
            this.amount = amount;
            this.balance = balance;
            this.description = description;
        }
    }
}
