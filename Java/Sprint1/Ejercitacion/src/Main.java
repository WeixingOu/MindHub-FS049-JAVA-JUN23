import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Presentate");
        presentacion(input);

        System.out.println("Ingrese tres números para determinar el máximo entre ellos: ");
        maximoEntreTresNumeros(input.nextInt(), input.nextInt(), input.nextInt());

        System.out.println("Ingrese un número para verificar si es par o impar: ");
        verficarParidad(input.nextInt());

        System.out.println("Ingrese dos textos para comparar si son iguales o diferentes: ");
        compararDosTextos(input.nextLine(), input.nextLine());

        System.out.println("Ingrese algunos números que serán almacenados: ");
        System.out.println(almacenarNumeros(input));

        imprimirMensajeBienvenida();

        System.out.println("Ingrese un número para verificar si es primo o no: ");
        System.out.println(verificarPrimidad(input.nextInt()));

        System.out.println("Ingrese algunos números para sumar solo los pares: ");
        sumaNumsPares(almacenarNumeros(input));

        System.out.println("Ingrese números para imprimir solo los números pares y sumar solo los números primos: ");
        imprimirNumsParesSumaPrimos(almacenarNumeros(input));

        calculadora(input);

        boliche(input);

        input.close();
    }
    /* Ejercicio 1 */
    public static void presentacion(Scanner input) {
        System.out.println("Ingrese su nombre: ");
        String nombre = input.nextLine();
        System.out.println("Ingrese su apellido: ");
        String apellido = input.nextLine();
        System.out.println("Ingrese su edad: ");
        int edad = input.nextInt();
        System.out.println("Tu nombre es " + nombre + " " + apellido + " y tienes " + edad + " años");
    }
    /* Ejercicio 2 */
    public static void maximoEntreTresNumeros(int num1, int num2, int num3) {
        if (num1 >= num2 && num1 >= num3) {
            System.out.println("El número mayor es " + num1);
        } else if (num2 >= num1 && num2 >= num3) {
            System.out.println("El número mayor es " + num2);
        } else {
            System.out.println("El número mayor es " + num3);
        }
    }
    /* Ejercicio 3 */
    public static void verficarParidad(int num) {
        if (num % 2 == 0) {
            System.out.println("El numero " + num + " es par");
        } else {
            System.out.println("El numero " + num + " es impar");
        }
    }
    /* Ejercicio 4 */
    public static void compararDosTextos(String txt1, String txt2) {
        if (txt1.equalsIgnoreCase(txt2)) {
            System.out.println("Los textos son iguales" + txt1 + "-" + txt2);
        } else {
            System.out.println("Los textos no son iguales" + txt1 + "-" + txt2);
        }
    }
    /* Ejercicio 5 */
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
    /* Ejercicio 6 */
    public static void imprimirMensajeBienvenida() {
        System.out.println("¡Bienvenido!");
    }
    /* Ejercicio 7 */
    public static String verificarParidad(int num) {
        if (num % 2 == 0) {
            return "par";
        } else {
            return "impar";
        }
    }
    /* Ejercicio 8 */
    public static boolean verificarPrimidad(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num - 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    /* Ejercicio 9 */
    public static void sumaNumsPares(ArrayList<Integer> nums) {
        int sum = 0;
        for (int n : nums) {
            if (n % 2 != 0) {
                sum += n;
            }
        }
        System.out.println("La suma de los números impares ingresados es: " + sum);
    }
    /* Ejercicio 10 */
    public static void imprimirNumsParesSumaPrimos(ArrayList<Integer> nums) {
        int sum = 0;
        ArrayList<Integer> numsPares = new ArrayList<>();
        for (int n : nums) {
            if (n % 2 == 0) {
                numsPares.add(n);
            }

            if (verificarPrimidad(n)) {
                sum += n;
            }
        }
        System.out.println("Los números pares ingresados: " + numsPares);
        System.out.println("La suma de los números primos: " + sum);
    }
    /* Ejercicio 11 */
    public static void calculadora(Scanner scanner) {
        int opcion;
        double numero1, numero2;
        do {
            System.out.println("\nCalculadora básica");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el primer número: ");
                    numero1 = scanner.nextDouble();
                    System.out.print("Ingrese el segundo número: ");
                    numero2 = scanner.nextDouble();
                    System.out.println("La suma es: " + (numero1 + numero2));
                    break;
                case 2:
                    System.out.print("Ingrese el primer número: ");
                    numero1 = scanner.nextDouble();
                    System.out.print("Ingrese el segundo número: ");
                    numero2 = scanner.nextDouble();
                    System.out.println("La resta es: " + (numero1 - numero2));
                    break;
                case 3:
                    System.out.print("Ingrese el primer número: ");
                    numero1 = scanner.nextDouble();
                    System.out.print("Ingrese el segundo número: ");
                    numero2 = scanner.nextDouble();
                    System.out.println("El producto es: " + (numero1 * numero2));
                    break;
                case 4:
                    System.out.print("Ingrese el primer número: ");
                    numero1 = scanner.nextDouble();
                    System.out.print("Ingrese el segundo número: ");
                    numero2 = scanner.nextDouble();
                    if (numero2 != 0) {
                        System.out.println("El cociente es: " + (numero1 / numero2));
                    } else {
                        System.out.println("Error: No se puede dividir por cero.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }
    /* Ejercicio 12 */
    public static void boliche(Scanner scanner) {
        int capacidadMaxima = 500;
        int capacidadActual = 0;
        int dineroRecaudado = 0;
        int opcion;
        do {
            System.out.println("1. Ingreso de datos.");
            System.out.println("2. Capacidad disponible.");
            System.out.println("3. Dinero recaudado.");
            System.out.println("4. Salir del sistema.");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (capacidadActual == capacidadMaxima) {
                        System.out.println("Lo siento, el boliche está al máximo de su capacidad.");
                        continue;
                    }
                    System.out.println("Ingrese el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la edad: ");
                    int edad = scanner.nextInt();
                    if (edad < 21) {
                        System.out.println("Lo siento, sólo se permiten mayores de 21 años.");
                        continue;
                    }
                    System.out.print("Ingrese el DNI: ");
                    String dni = scanner.next();
                    System.out.print("Ingrese el tipo de pase (1: Normal, 2: VIP, 3: Descuento): ");
                    int tipoPase = scanner.nextInt();
                    int precioEntrada = 0;
                    if (tipoPase == 1) {
                        precioEntrada = 1500;
                    } else if (tipoPase == 2) {
                        precioEntrada = 0;
                    } else if (tipoPase == 3) {
                        precioEntrada = 750;
                    }
                    System.out.println("Precio de la entrada: " + precioEntrada);
                    dineroRecaudado += precioEntrada;
                    capacidadActual += 1;
                    System.out.println("Bienvenido al boliche, " + nombre + "!");
                    break;
                case 2:
                    System.out.println("Capacidad disponible: " + (capacidadMaxima - capacidadActual));
                    break;
                case 3:
                    System.out.println("Dinero recaudado: " + dineroRecaudado);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elige una opción válida.");

            }
        } while (opcion != 0);
    }
}
