package lab1;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main{
    static final String yes ="y";
    static final String no = "n";

    public static final String sandwich = "Sandwich";
    public static final String coctail = "Coctail";
    public static final String dessert = "Dessert";

    static final String calorie_сalculator = "-calories";
    static final String sort = "-sort";

    public static void main(String []args) throws Exception{
        Scanner in = new Scanner(System.in);

        Food[] breakfast = new Food[20];
        int itemsSoFar = 0;
        System.out.println("Do you want something to eat? Press 'y' if yes or 'n' if no.");
        String choice = in.nextLine().toLowerCase();

        switch (choice){
            case yes:
                while(true){
                    System.out.println("What would you like to eat?");
                    System.out.println("We have:\n- "+ sandwich + "\n- " + coctail + "\n- " + dessert);
                    System.out.println("\nEnter the name of what you want.");

                    addElement(in, breakfast, itemsSoFar);
                    itemsSoFar++;

                    System.out.println("Anything else? Press 'y' if yes or 'n' if no.");
                    choice = in.nextLine().toLowerCase();

                    if(choice.equals(no)){
                        System.out.print("If you want to know the number of calories input '-calories'. ");
                        System.out.println("Else press 'n'.");
                        choice = in.nextLine().toLowerCase();
                        if(choice.equals(calorie_сalculator)){
                            Nutritious.calculateCalories(breakfast);
                        }
                        System.out.print("If you want to sort breakfast input '-sort'. ");
                        System.out.println("Else press 'n'.");
                        choice = in.nextLine().toLowerCase();
                        if(choice.equals(sort)){
                            sortByCalories(breakfast);
                        }

                        System.out.println("Your breakfast:");
                        output(breakfast);
                        System.out.println("Have a good day)");
                        break;
                    }
                }
                break;

            case no:
                System.out.println("Why are you still here?");
                break;

            default:
                System.out.println("Wrong input.");
        }
        in.close();
    }

    private static void addElement(Scanner in, Food[] breakfast, int itemsSoFar)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String foodName = in.nextLine();
        try{
            Class<?> myClass = Class.forName("lab1." + foodName);
            breakfast[itemsSoFar] = (Food)myClass.getConstructor().newInstance();
            itemsSoFar++;
        } catch(ClassNotFoundException e){
            System.out.println("Class not found.");
        }
    }

    private static void sortByCalories(Food[] breakfast) {
        Arrays.sort(breakfast, new Comparator<Food>() {
            public int compare(Food f1, Food f2) {
                if (f1==null) return 1;
                if (f2==null) return -1;
                if(((Food)f1).calories==((Food)f2).calories) return 0;
                if(((Food)f1).calories>((Food)f2).calories) return -1;
                return 1;
            }
        });
    }

    public static void output(Food[] array){
        for(int i=0;array[i]!=null;i++){
            System.out.println(array[i]);
        }
    }
}