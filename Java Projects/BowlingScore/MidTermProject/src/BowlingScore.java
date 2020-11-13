// Cody Rauber 
// 10.12.2020
// Mid Term Bowling Project: Allows user input to show and calculate bowling scores.

import java.util.Scanner;

public class BowlingScore {
	
	public static void main(String[] args) {
		
		int[] scores = new int[23]; // declare the array for scores
		
		int frame = 1; // keeps track of the frame the bowler is on
		int frameScore = 0; // keeps track of each frames score
		int total = 0; // total running score of frames
		
		Scanner input = new Scanner(System.in);
		
		// for loop to count through the frames and throws
		for (int counter = 1; counter < 21; counter++) {
			System.out.printf("---FRAME %d---%n", frame);
			System.out.println("Throw 1: ");
			scores[counter] = input.nextInt();
			counter++;
			if (scores[counter-1] != 10) {    // if first throw is 10, then skip throw 2
			System.out.println("Throw 2: ");
			scores[counter] = input.nextInt();
			} else {  // if it is 10, set throw 2 to 0
				scores[counter] = 0;
			} // end of if/else
			frame++;
		} // end of for
		
		// if strike on 10th frame, add 2 throws. If spare, add 1 throw.
		if (scores[19] == 10) {
			System.out.println("---FRAME 10---");
			System.out.println("Throw 2: ");
			scores[20] = input.nextInt();
			System.out.println("Throw 3: ");
			scores[21] = input.nextInt();
		} else if (scores[19] + scores[20] == 10) {
			System.out.println("Throw 3: ");
			scores[21] = input.nextInt();
		}

		System.out.println("\n\n\n----------------SCORECARD----------------\n\n\n");
		
		// sets frame number back to 1
		frame = 1;
		
		// For loop to calculate score for each frame. Decides between normal frame, spare, and strike. 
		for (int i = 1; i <= 20; i+=2) {
			System.out.printf("%nFRAME %d%n", frame);
			if (scores[i] == 10) { // calculate strike score
				if (i == 19 && scores[19] == 10 && scores[20] == 10 && scores[21] == 10) { // calculate last frame 3 strikes
					frameScore += scores[19] + scores[20] + scores[21] - 40;
				} else if (scores[i+2] == 10 && scores[i+4] == 10) { // if more than 1 strike in a row
					frameScore = scores[i] + scores[i+2] + scores[i+4]; 
				} else { // if a normal single strike frame
				frameScore = scores[i] + scores[i+2] + scores[i+3];
				System.out.println(" X");
				}
			} else if (scores[i] + scores[i+1] == 10) { // calculate spare score
				frameScore = scores[i] + scores[i+1] + scores[i+2];
				System.out.println(" /");
			} else { // normal scoring
				frameScore = scores[i] + scores[i + 1];
			}
			if (i == 19 && (scores[19] + scores[20] == 10)) { // calculate 3rd throw on spare in 10th frame
				frameScore += scores[21];
			} else if (i == 19 && (scores[19] == 10)) { // calculate 3rd throw on strike in 10th frame
				frameScore += scores[21];
			}
			System.out.printf("Frame Score: %d%n", frameScore);
			total += frameScore; // add frameScore to total each loop
			System.out.printf("Total: %d%n", total);
			frame++; // next frame
		} // end of score for
		
		System.out.println("---------------------------\n *** END OF PROGRAM ***");
		
	} // end of main

} // end of class
