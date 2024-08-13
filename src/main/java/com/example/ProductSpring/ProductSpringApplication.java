/**
 * Entry point for the Spring Boot application that manages a personal inventory of products.
 * This class initializes the Spring context, provides a menu-driven command-line interface
 * for interacting with the {@link ProductService}, and handles user input for various
 * operations such as adding, listing, updating, and deleting products.
 */

package com.example.ProductSpring;

import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ProductSpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProductSpringApplication.class, args);
		ProductService service = context.getBean(ProductService.class);


		System.out.println("--------------------------------------------");
		System.out.println("Welcome to your Personal Inventory!");
		System.out.println("--------------------------------------------");

		boolean conditionCheck = true;
		Scanner scan = new Scanner(System.in);

		while (conditionCheck) {
            System.out.println("\nWhat would you like to do? "
                    + "\n1) Add an item"
                    + "\n2) List all items"
                    + "\n3) Update an item"
                    + "\n4) Delete an item"
                    + "\n5) Exit");

            int userInput = scan.nextInt();
            scan.nextLine(); // Consume the newline character

            switch (userInput) {
                case 1:
                    addProduct(service, scan);
                    break;
                case 2:
					readProducts(service);
					break;
				case 3:
					updateProduct(service,scan);
					break;
				case 4:
					deleteProduct(service,scan);
					break;
                case 5:
                    conditionCheck = false; // Exit the loop when the user chooses to exit
					System.out.println("\nHave a good day!\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
	}

	public static void addProduct(ProductService service, Scanner scanner) {

		System.out.println("How many items do you want to log?");
		int howMany = scanner.nextInt();
		scanner.nextLine();

		for(int i = 0; i<howMany;i++) {
			System.out.println("Please enter the following");

			System.out.print("Name: ");
			String name = scanner.nextLine();

			System.out.print("Type: ");
			String type = scanner.nextLine();

			System.out.print("Location: ");
			String location = scanner.nextLine();

			System.out.print("Quantity: ");
			int quantity = scanner.nextInt();
			scanner.nextLine();

			Product newProduct = new Product(name,type,location,quantity);
			service.addProduct(newProduct);
		}
	}

	public static void readProducts(ProductService service) {
		List<Product> products = service.getAllProducts();
		for(Product p : products) {
			System.out.println(p);
		}
	}

	public static void updateProduct(ProductService service, Scanner scanner) {
		System.out.println("Enter the name of the product you want to update:");
		String name = scanner.nextLine();

		// Retrieve the existing product from the database
		Product existingProduct = service.getProduct(name);

		if (existingProduct != null) {
			// Product found, prompt for new information
			System.out.println("Enter new information for the product:");

			System.out.print("New Name: ");
			String newName = scanner.nextLine();

			System.out.print("New Type: ");
			String newType = scanner.nextLine();

			System.out.print("New Location: ");
			String newLocation = scanner.nextLine();

			System.out.print("New Quantity: ");
			int newQuantity = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			// Update the existing product
			existingProduct.setName(newName);
			existingProduct.setType(newType);
			existingProduct.setLocation(newLocation);
			existingProduct.setQuantity(newQuantity);

			// Save the updated product back to the database
			service.updateProduct(existingProduct);

			System.out.println("Product updated successfully.");
		} else {
			System.out.println("Product not found in the database.");
		}
	}

	@Transactional // needed for the ProductDB to delete
	public static void deleteProduct(ProductService service, Scanner scanner) {
		System.out.println("How many items do you want to delete?");
		int howMany = scanner.nextInt();
		scanner.nextLine();

		for(int i = 0; i<howMany;i++) {
			System.out.println("Please enter the following");

			System.out.print("Name: ");
			String name = scanner.nextLine();

			String search = service.findProduct(name);

			if (name != null) {
				// Product found, so delete it
				service.deleteProduct(name);
				System.out.println("Product deleted successfully.");
			} else {
				// Product not found, display a message
				System.out.println("Product not found in the database.");
			}
		}
	}








}
