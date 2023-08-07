import java.util.ArrayList;
import java.util.Scanner;

public class Workshop {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese algunos números para sumar solo los pares: ");
        sumaNumsPares(almacenarNumeros(input));
    }
    public static ArrayList<Integer> almacenarNumeros(Scanner input) {
        ArrayList<Integer> nums = new ArrayList<>();
        int num;
        do {
            System.out.println("Ingrese un numero (0 para terminar): ");
            num = input.nextInt();
            if (num != 0) {
                nums.add(num);
            }
        } while (num != 0);
        return nums;
    }
    public static void sumaNumsPares(ArrayList<Integer> nums) {
        int sum = 0;
        for (int n : nums) {
            if (n % 2 == 0) {
                sum += n;
            }
        }
        System.out.println("La suma de los números impares ingresados es: " + sum);
    }
}
