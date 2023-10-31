package output;

public class OutputUserData {
    public static void main(String[] args) {
        if(args.length!=2)
        {
            System.out.println("Missing parameters.");
            return;
        }
        System.out.println("Name: " + args[0]);
        System.out.println("Surname: " + args[1]);
    }
}