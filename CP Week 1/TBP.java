import java.util.*;
class TravelPlan {
    private String destination;
    private int days;
    private double budget;
    public TravelPlan(String destination, int days, double budget) {
        this.destination = destination;
        this.days = days;
        this.budget = budget;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }
    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }
}
public class TBP{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<TravelPlan> plans = new ArrayList<>();
        int choice, n;
        do {
            System.out.println("\n===== Travel Budget Planner =====");
            System.out.println("1. Create Plan");
            System.out.println("2. View Plans");
            System.out.println("3. Update Plan");
            System.out.println("4. Delete Plan");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();
                    System.out.print("Enter Number of Days: ");
                    int days = sc.nextInt();
                    System.out.print("Enter Total Budget: ");
                    double budget = sc.nextDouble();
                    plans.add(new TravelPlan(destination, days, budget));
                    System.out.println("Travel Plan Created");
                    break;
                case 2:
                    if (plans.isEmpty()) {
                        System.out.println("No Plans Found");
                        break;
                    }
                    for (int i = 0; i < plans.size(); i++) {
                        TravelPlan p = plans.get(i);
                        double travel = p.getBudget() * 0.25;
                        double hotel = p.getBudget() * 0.40;
                        double food = p.getBudget() * 0.25;
                        double misc = p.getBudget() * 0.10;
                        System.out.println("\nPlan " + (i + 1));
                        System.out.println("Destination : " + p.getDestination());
                        System.out.println("Days : " + p.getDays());
                        System.out.println("Total Budget : " + p.getBudget());
                        System.out.println("Travel : " + travel);
                        System.out.println("Hotel : " + hotel);
                        System.out.println("Food : " + food);
                        System.out.println("Miscellaneous : " + misc);
                    }
                    break;
                case 3:
                    if (plans.isEmpty()) {
                        System.out.println("No Plans Found");
                        break;
                    }
                    System.out.print("Enter Plan Number: ");
                    n = sc.nextInt();
                    sc.nextLine();
                    if (n < 1 || n > plans.size()) {
                        System.out.println("Invalid Plan");
                        break;
                    }
                    TravelPlan p = plans.get(n - 1);
                    System.out.print("Enter Destination: ");
                    p.setDestination(sc.nextLine());
                    System.out.print("Enter Number of Days: ");
                    p.setDays(sc.nextInt());
                    System.out.print("Enter Total Budget: ");
                    p.setBudget(sc.nextDouble());
                    System.out.println("Plan Updated");
                    break;
                case 4:
                    if (plans.isEmpty()) {
                        System.out.println("No Plans Found");
                        break;
                    }
                    System.out.print("Enter Plan Number: ");
                    n = sc.nextInt();
                    if (n < 1 || n > plans.size()) {
                        System.out.println("Invalid Plan");
                        break;
                    }
                    plans.remove(n - 1);
                    System.out.println("Plan Deleted");
                    break;
                case 5:
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 5);
        sc.close();
    }
}