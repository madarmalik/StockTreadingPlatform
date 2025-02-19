
import java.util.*;
class Stock {
    String name;
    double price;
    
    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
class User {
    String name;
    double balance;
    Map<String, Integer> portfolio;
    
    User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }
    
    void buyStock(String stockName, double price, int quantity) {
        double cost = price * quantity;
        if (balance >= cost) {
            balance -= cost;
            portfolio.put(stockName, portfolio.getOrDefault(stockName, 0) + quantity);
            System.out.println("Purchased " + quantity + " shares of " + stockName);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
    
    void sellStock(String stockName, double price, int quantity) {
        if (portfolio.getOrDefault(stockName, 0) >= quantity) {
            balance += price * quantity;
            portfolio.put(stockName, portfolio.get(stockName) - quantity);
            if (portfolio.get(stockName) == 0) portfolio.remove(stockName);
            System.out.println("Sold " + quantity + " shares of " + stockName);
        } else {
            System.out.println("Insufficient shares to sell!");
        }
    }
    
    void checkBalance() {
        System.out.println("Balance: " + balance);
    }
    
    void viewPortfolio() {
        System.out.println("Portfolio: " + portfolio);
    }
}
public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        User user = new User("Malik", 5000);
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("Apple", 150));
        market.put("GOOGL", new Stock("Google", 2800));
        market.put("TSLA", new Stock("Tesla", 900));
        
        while (true) {
            System.out.println("\n1. Buy Stock\n2. Sell Stock\n3. Check Balance\n4. View Portfolio\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter stock symbol (AAPL, GOOGL, TSLA): ");
                    String buyStock = scanner.next();
                    if (market.containsKey(buyStock)) {
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        user.buyStock(buyStock, market.get(buyStock).price, qty);
                    } else {
                        System.out.println("Invalid stock!");
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String sellStock = scanner.next();
                    if (user.portfolio.containsKey(sellStock)) {
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        user.sellStock(sellStock, market.get(sellStock).price, qty);
                    } else {
                        System.out.println("You don't own this stock!");
                    }
                    break;
                case 3:
                    user.checkBalance();
                    break;
                case 4:
                    user.viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
