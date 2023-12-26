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

class StockAccount {
    String name;
    ArrayList<Stock> stocksInAccount = new ArrayList<Stock>();

    StockAccount(String name) {
        this.name = name;
    }

    public double valueOf() {
        double sum = 0;
        for (int i = 0; i < this.stocksInAccount.size(); i++) {
            sum += this.stocksInAccount.get(i).sharePrice * this.stocksInAccount.get(i).noOfShare;
        }
        return sum;
    }

    public void buy(String name, int ammount, int sharePrice) {
        boolean find = false;
        for (int i = 0; i < this.stocksInAccount.size(); i++) {
            if (this.stocksInAccount.get(i).name.equals(name)) {
                this.stocksInAccount.get(i).noOfShare += ammount;
                find = true;
            }
        }
        if (!find) {
            this.stocksInAccount.add(new Stock(name, ammount, sharePrice));
        }
    }

    public void sell(String name, int ammount, ArrayList<Stock> stocks) {
        boolean possible = false;
        for (int i = 0; i < this.stocksInAccount.size(); i++) {
            if (this.stocksInAccount.get(i).name.equals(name)) {

                if (this.stocksInAccount.get(i).noOfShare >= ammount) {
                    this.stocksInAccount.get(i).noOfShare -= ammount;
                    for (int j = 0; j < stocks.size(); j++) {
                        if (stocks.get(i).name.equals(name)) {
                            stocks.get(i).noOfShare += ammount;
                        }
                    }
                    possible = true;
                }
            }
        }
        if (!possible) {
            System.out.println("Not enough shares of this stock to sell");
            return;
        }
        System.out.println("Stocks sold succesfully");
    }

    public void report() {
        for (int i = 0; i < this.stocksInAccount.size(); i++) {
            Stock stock = this.stocksInAccount.get(i);
            System.out.println("Name of stock " + stock.name + "  No of Shares: " + stock.noOfShare + " Share Price: "
                    + stock.sharePrice + " Value of Stock " + stock.value());
        }
    }
}

public class StockManagement {
    public static ArrayList<Stock> stocks = new ArrayList<Stock>();

    public static void stocksReport() {
        for (int i = 0; i < stocks.size(); i++) {
            Stock stock = stocks.get(i);
            System.out.println("Name of stock " + stock.name + "  No of Shares: " + stock.noOfShare + " Share Price: "
                    + stock.sharePrice + " Value of Stock " + stock.value());
        }
    }

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

        StockAccount stockAccount = new StockAccount("Account 1");
        boolean end = false;

        System.out.println("\n");
        while (true) {
            System.out.println("What do you want to do");
            System.out.println("Enter 0 to end");
            System.out.println("Enter 1 to buy a stock");
            System.out.println("Enter 2 to sell a stock");
            System.out.println("Enter 3 to get value of stock in the Account");
            System.out.println("Enter 4 to get the report of the stocks in the Account");
            System.out.println("Enter 5 to get all the stocks report ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 0: {
                    end = true;
                    break;
                }
                case 1: {
                    System.out.println("Enter the name of the stock");
                    String name = sc.nextLine();
                    System.out.println("Enter the quantity to buy");
                    int amount = sc.nextInt();
                    boolean find = false;
                    int sharePrice = 0;
                    for (int i = 0; i < stocks.size(); i++) {
                        if (stocks.get(i).name.equals(name)) {
                            if (stocks.get(i).noOfShare >= amount) {
                                stocks.get(i).noOfShare -= amount;
                                sharePrice = stocks.get(i).sharePrice;
                                find = true;
                            }
                        }
                    }
                    if (!find) {
                        System.out.println("The stock is not avaliable");
                    } else {
                        stockAccount.buy(name, amount, sharePrice);
                        System.out.println("\n Stock bought Successfully");
                    }
                    break;

                }
                case 2: {
                    System.out.println("Enter the name of the stock");
                    String name = sc.nextLine();
                    System.out.println("Enter the quantity to sell");
                    int amount = sc.nextInt();
                    stockAccount.sell(name, amount, stocks);

                    break;
                }

                case 3: {
                    System.out.println("Total value of the account is " + stockAccount.valueOf());
                    break;
                }
                case 4: {
                    stockAccount.report();
                    break;
                }
                case 5: {
                    stocksReport();
                    break;
                }
                default: {
                    System.out.println("Enter a value between 0-5");
                    break;
                }
            }
            if (end)
                break;
        }
    }
}