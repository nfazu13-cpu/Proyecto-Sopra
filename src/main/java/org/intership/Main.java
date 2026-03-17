package org.intership;

import org.intership.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);
        Product product1 = new Product("Camiseta", "Camiseta de algodón", 19.99);

        System.out.println("Hola introduce tu nombre: ");
        String name = scanner.nextLine();
        User user = new User();
        System.out.println("Hola " + name);

        List<Employee> employees = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        int number = 0;
        do {
            System.out.println("¡Hello there!");
            System.out.println("Introduce un número: ");
            System.out.println("Estas son las opciones: ");

            System.out.println("pulsa '1', para consultar productos");
            System.out.println("pulsa '2', para consultar el saldo de tu cuenta");
            System.out.println("0. Salir");


            //TODO: esto lo harán los nanos
            System.out.println("pulsa '1', para poder realizar una compra");
            System.out.println("pulsa '3', para poder gestionar el stock");
            System.out.println("pulsa '4', para poder añadir un empleado"); //y añades más productos (limite y product new)
            System.out.println("pulsa '5', para poder consultar movimientos realizados");
            System.out.println("pulsa '6', para poder añadir un cliente");
            System.out.println("pulsa '7', para poder obtener el listado de empleados");


            // Empiezas por 0 empleados = 5 productos
            // 1 empleado = 10 productos
            // 2 empleados = 15 productos
            // ..... y así sucesivamente, cada empleado añade 5 productos al stock

            //Fecha de caducidad de los productos


            number = scanner.nextInt();
            switch (number) {
                case 1:
                    products.forEach(product -> System.out.println(product.toString()));
                    List<Product> productsToBuy = new ArrayList<>();
                    boolean continueBuying = true;
                    Scanner buyScanner = new Scanner(System.in);
                    do {
                        System.out.println("¿Qué producto quieres comprar? indica el identificador del producto");
                        Long idToBuy = Long.parseLong(buyScanner.nextLine());

                        products.stream().filter(product -> product.getId().equals(idToBuy)).findFirst()
                                .ifPresentOrElse(product -> {
                                    productsToBuy.add(product);
                                    System.out.println("Producto añadido a la compra.");
                                }, () -> System.out.println("Producto no encontrado. Intenta de nuevo."));
                        System.out.println("¿Quieres seguir comprando más productos? (s/n)");
                        String moreProducts = buyScanner.nextLine();
                        continueBuying = moreProducts.equals("s");
                    }
                    while (continueBuying);


                    Purchase newPurchase = new Purchase();
                    newPurchase.setProducts(productsToBuy);
                    System.out.println(newPurchase.toString());
                    break;
                case 2:
                    System.out.println("El saldo de tu cuenta es: " + user.getBalance());
                    number = 0;
                    break;
                case 3:
                    int stockOption = -1;
                    Scanner stockScanner = new Scanner(System.in);
                    while (stockOption != 0) {
                        System.out.println("\nGestión de stock:");
                        System.out.println("1. Ver stock");
                        System.out.println("2. Añadir producto");
                        System.out.println("3. Eliminar producto");
                        System.out.println("0. Salir de gestión de stock");
                        System.out.print("Selecciona una opción: ");
                        try {
                            stockOption = Integer.parseInt(stockScanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opción no válida. Intenta de nuevo.");
                            continue;
                        }
                        switch (stockOption) {
                            case 1:
                                System.out.println("Mostrando stock actual...");
                                Map<String, Integer> stockMap = products.stream()
                                        .collect(Collectors.toMap(Product::getName, Product::getStock));

                                stockMap.forEach((productName, stock) ->
                                        System.out.println("Producto: " + productName + ", Stock: " + stock));
                                break;
                            case 2:
                                System.out.println("Añadiendo producto al stock...");
                                Product newProduct = new Product();
                                System.out.println("Introduce el nombre del producto: ");
                                newProduct.setName(stockScanner.nextLine());
                                System.out.println("Introduce la descripción del producto: ");
                                newProduct.setDescription(stockScanner.nextLine());
                                System.out.println("Introduce el precio del producto: ");
                                try {
                                    newProduct.setPrice(Double.parseDouble(stockScanner.nextLine())); // nextDouble?
                                    products.add(newProduct);
                                    System.out.println("Producto añadido al stock.");
                                } catch (NumberFormatException e) {
                                    System.out.println("Precio no válido. Producto no añadido.");
                                }
                                break;
                            case 3:
                                System.out.println("Indica el id del producto que quieres eliminar...");
                                Long idToRemove = scanner.nextLong();
                                products.stream().filter(product -> product.getId().equals(idToRemove)).findFirst()
                                        .ifPresentOrElse(product -> {
                                            products.remove(product);
                                            System.out.println("Producto eliminado.");
                                        }, () -> System.out.println("Producto no encontrado."));

                                break;
                            case 0:
                                System.out.println("Saliendo de la gestión de stock...");
                                break;
                            default:
                                System.out.println("Opción no válida. Intenta de nuevo.");
                        }
                    }
                    stockScanner.close();
                    break;
                case 4:
                    System.out.println(" Introduce el nombre del empleado: ");
                    String nameEmployee = scanner.nextLine();
                    System.out.println(" Introduce el email del empleado: ");
                    String emailEmployee = scanner.nextLine();
                    Employee employee = new Employee();
                    employee.setName(nameEmployee);
                    employee.setEmail(emailEmployee);
                    employees.add(employee);
                    break;
                case 5:
                    System.out.println("El número es uno");
                    break;
                case 6:
                    System.out.println(" Introduce el nombre del cliente: ");
                    String nameCustomer = scanner.nextLine();
                    System.out.println(" Introduce el email del empleado: ");
                    String emailCustomer = scanner.nextLine();
                    Customer customer = new Customer();
                    customer.setName(nameCustomer);
                    customer.setEmail(emailCustomer);
                    customers.add(customer);
                    break;
                case 0:
                    System.out.println("General Kenobi");
                    break;


            }
        }
        while (number != 9);

    }
}