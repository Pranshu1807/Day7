import java.util.ArrayList;
import java.util.Scanner;

class Stock {
    public String name;
    public int noOfShare, sharePrice;

    public Stock(String name, int noOfShare, int sharePrice) {
        this.name = name;
        this.noOfShare = noOfShare;
        this.sharePrice = sharePrice;
    }

    public int value() {
        return this.noOfShare * this.sharePrice;
    }
}

public class StockManagement {
    public static ArrayList<Stock> stocks = new ArrayList<Stock>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of stocks");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the name of stock " + (i + 1));
            String name = sc.nextLine();
            System.out.println("Enter the share price of stock " + (i + 1));
            int sharePrice = sc.nextInt();
            System.out.println("Enter the no of shares of stock " + (i + 1));
            int noOfShare = sc.nextInt();
            sc.nextLine();
            Stock stock = new Stock(name, noOfShare, sharePrice);
            stocks.add(stock);
        }

        int totalValue = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Value of stock " + (i + 1) + " is " + stocks.get(i).value());
            totalValue += stocks.get(i).value();
        }
        System.out.println("Total value of stocks is " + totalValue);
    }
}