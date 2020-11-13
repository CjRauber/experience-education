/* Cody Rauber
 * 9/20/20
 * Cash Register Project 1
 */

import java.util.Scanner;
import java.text.DecimalFormat;

public class CashRegister {

	public static void main(String[] args) {
		
		//Constant variables
		double ITEM1 = 29.99;
		double ITEM2 = 10.99;	
		double ITEM3 = 4.49;
		double TAXRATE = .07;
		int COUPON10 = 10;
		int COUPON5 = 5;
		int COUPON3 = 3;
		
		
		//Variables
		double itemTotal = 0.0;
		int selection = 5;
		int selection2 = 5;
		int couponTotal = 0;
		double subtotal = 0.0;
		double taxAmount = 0.0;
		double total = 0;
		double moneyIn = 0.0;
		double changeDue = 0.0;
		
		//user scanner for items
		Scanner newIn = new Scanner(System.in);
		
		//Begin while loop to choose items
		while (selection != 0) {
			
			System.out.println("Please pick the items you want, enter 0 to exit:");
			System.out.println("1. Item 1 $29.99");
			System.out.println("2. Item 2 $10.99");
			System.out.println("3. Item 3 $4.49");
			
			System.out.printf("\n\nYour running total is: $%.2f", itemTotal);
			
			//user input for items
			selection = newIn.nextInt();
			
				switch(selection) 
				{
				case 1:
					itemTotal += ITEM1;
					break;
					
				case 2:
					itemTotal += ITEM2;
					break;
					
				case 3:
					itemTotal += ITEM3;
					break;
					
				case 0:
					System.out.print("All done ordering. ");
					break;
					
				default:
					System.out.println("Pick a valid choice.");
					break;
					
				} // end of switch

		} // end of while
		
		System.out.printf("Your order total is: %.2f ", itemTotal);
		
		//Start of coupon section
		
		//user scanner for coupons
				Scanner newIn2 = new Scanner(System.in);
				
				subtotal = itemTotal;
				
				//Begin while loop to choose coupons
				while (selection2 != 0) {
					
					System.out.println("\n\nPlease pick the coupon that you would like, enter 0 to exit:");
					System.out.println("1. $10 off any purchase.");
					System.out.println("2. $5 off any purchase.");
					System.out.println("3. $3 off any purchase.");
					
					System.out.printf("\n\nYour running total is: $%.2f", subtotal);
					
					//user input for coupons
					selection2 = newIn2.nextInt();
						
						switch(selection2) 
						{
						case 1:
							subtotal -= COUPON10;
							couponTotal += COUPON10;
							break;
							
						case 2:
							subtotal -= COUPON5;
							couponTotal += COUPON5;
							break;
							
						case 3:
							subtotal -= COUPON3;
							couponTotal += COUPON3;
							break;
							
						case 0:
							System.out.print("\nAll done choosing coupons. ");
							break;
							
						default:
							System.out.println("\nPick a valid choice.");
							break;
							
						} // end of coupon switch

					} // end of coupon while
				
				
					if (subtotal < 0) {
						System.out.println("\nI guess your item is free today! Try choosing less coupons!");
						return;
					} // end of itemTotal if
				
					
					// Calculate the totals and print them
					taxAmount = (subtotal * TAXRATE);
					total = subtotal + taxAmount;
					
			DecimalFormat df = new DecimalFormat("#.##");
			System.out.printf("%n%nTotals: %n Item Total: $%.2f %n Coupon total: $%d %n Subtotal: $%.2f %n Tax Rate: %.2f %n Tax Amount: $%.2f %n", itemTotal, couponTotal, subtotal, TAXRATE, taxAmount);
			System.out.println("\nTotal: $" + df.format(total));
			
			//Section for inputting money
			Scanner newDouble = new Scanner(System.in);
			
			System.out.println("\n\nPlease insert money amount: ");
			
			moneyIn = newDouble.nextDouble();
			
			if (moneyIn > total) {
				
			changeDue = (moneyIn - total);
			
			System.out.printf("%nYou inserted: $%.2f ", moneyIn);
			System.out.printf("%nChange due: $%.2f ", changeDue);
			
			} else {
				System.out.println("You do not have enough money!");
				return;
			} // if/else for moneyIn
			
			// Change due section
			// Checks what bills and coins are due
			double twenty = changeDue / 20;
			if (twenty > 1) {
				changeDue = changeDue % 20;
				System.out.printf("%n%.0f $20 bills.", twenty);
			} 
			
			double ten = changeDue / 10;
			if (ten > 1) {
				changeDue = changeDue % 10;
				System.out.printf("%n%.0f $10 bills.", ten);
			}
			
			double five = changeDue / 5;
			if (five > 1) {
				changeDue = changeDue % 5;
				System.out.printf("%n%.0f $5 bills.", five);
			}
			
			double one = changeDue / 1;
			if (one > 1) {
				changeDue = changeDue % 1;
				System.out.printf("%n%.0f $1 bills.", one);
			}
			
			double quarter = changeDue / .25;
			if (quarter > 1) {
				changeDue = changeDue % .25;
				System.out.printf("%n%.0f quarters.", quarter);
			}
			
			double dime = changeDue / .10;
			if (dime > 1) {
				changeDue = changeDue % .10;
				System.out.printf("%n%.0f dimes.", twenty);
			}
			
			double nickel = changeDue / .05;
			if (nickel > 1) {
				changeDue = changeDue % .05;
				System.out.printf("%n%.0f nickels.", nickel);
			}
			
			double penny = changeDue / .01;
			if (penny > 1) {
				changeDue = changeDue % .01;
				System.out.printf("%n%.0f pennies.", penny);
			}
			
			System.out.println("\n-----------------------");
			System.out.println("\n*** END OF PROGRAM ***");
		
	} // end of main

}// end of class