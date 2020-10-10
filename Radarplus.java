
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                  //
//     oksun6 username: lharms                                                                                      //
//     Lancen Harms                                                                                                 //
//     300285304                                                                                                    //
//     Programming Project#1, COSC 111                                                                              //
//                                      ~Radar Gun of (limited) Justice~                                            //
//                                                                                                                  //
//     In this lab I will be creating a radar program that determines the value of a speeding ticket based on       //
//     velocity, speed limit, number of prior offenses, and whether the driver was in a double zone. I will use     //
//     while loops and if statements that call functions to calculate ticket values based on characters and         //
//                                    integers that are entered by the user                                         //
//                                                                                                                  //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;

public class Radarplus {
	public static void main(String[] aaaaaargs) {
		int velocity = 0;
		// This integer is how fast the vehicle was moving in km/h.
		int limit = 0;
		// This integer is the speed limit in km/h where the driver was speeding.
		int priors = 0;
		// This integer is how many tickets the driver has received in the past.
		int baseTicketValue = 0, afterDoubleValue = 0, finalTicketValue = 0;
		// These three integers are the main data points used in my calculation
		// functions.
		boolean doubleZone = false;
		// This boolean data point is whether the driver was in a double zone (2 *
		// ticket value)
		String s = new String();
		// This string is used to represent the Scanner line and refresh the Scanner
		// integer.
		Scanner radarBot = new Scanner(System.in);
		// This Scanner is used to translate chars and integers entered by the user.

		System.out.print("Hello, Officer Harms. Let's issue some tickets. What is the speed limit? (km/h): ");
		// Entering 0 here will print the farewell message and close the program.
		limit = radarBot.nextInt();
		// The user will type an integer and a return carriage. This reads the
		// integer...
		s = radarBot.nextLine();
		// ...and this refreshes the unread return carriage.

		while (limit > 0) {
			System.out.print("Are we in a double zone? : ");
			s = radarBot.nextLine();
			if (s.charAt(0) == 'y' || s.charAt(0) == 'Y')
				doubleZone = true;
			// s.charAt(0) checks the first letter of the "String" entered by the user.
			else if (s.charAt(0) == 'n' || s.charAt(0) == 'N')
				doubleZone = false;
			System.out.print("What is the vehicles velocity? (km/h): ");
			// Entering 0 here will restart the limit while loop.
			velocity = radarBot.nextInt();
			s = radarBot.nextLine();
			while (velocity > 0) {
				if (velocity > limit + 9)
				// I have decided to instill a 9km/h grace period...
				// ...because I am a gracious officer.
				{
					System.out.print("How many prior violations does the driver have? : ");
					priors = radarBot.nextInt();
					s = radarBot.nextLine();
					if (limit + 9 < velocity && velocity < limit + 20)
						baseTicketValue = 138;
					// I used numbers from ICBC to make the program as accurate as possible.
					else if (limit + 19 < velocity && velocity < limit + 40)
						baseTicketValue = 196;
					// $138 is the low end of a standard ticket, $196 is the high end.
					else if (limit + 39 < velocity)
						baseTicketValue = 368;
					// ...and $368 is the low end of an excessive speeding ticket.
					if (doubleZone == true)
						afterDoubleValue = calculateAfterDouble(baseTicketValue, 2);
					else if (doubleZone == false)
						afterDoubleValue = calculateAfterDouble(baseTicketValue, 1);
					if (priors > 0)
						finalTicketValue = calculateFinalTicket(afterDoubleValue, (square(priors)));
					else
						finalTicketValue = calculateFinalTicket(afterDoubleValue, 1);

					System.out.println("Ticket Value: $" + finalTicketValue);
				} else if (velocity <= limit + 9)
					System.out.println("No ticket...");

				System.out.print("What is the new velocity? (km/h): ");
				// Entering 0 here will also restart the limit while loop.
				velocity = radarBot.nextInt();
				s = radarBot.nextLine();
			}
			System.out.print("Let's try a new area! What is the new speed limit? (km/h): ");
			// Entering 0 here will also print the farewell message and close the program.
			limit = radarBot.nextInt();
			s = radarBot.nextLine();
		}
		System.out.println("Farewell, Officer Harms. Have a good day/night.");
		radarBot.close();
	}

	public static int square(int priors)
	// This function is used to calculate the square of the priors integer.
	{
		int temp;
		temp = priors * priors;
		return temp;
	}

	public static int calculateAfterDouble(int ticket, int dzone)
	// This function is used to calculate and apply the double zone penalty onto the
	// ticket value.
	{
		int temp;
		temp = ticket * dzone;
		return temp;
	}

	public static int calculateFinalTicket(int ticket, int priors)
	// This function is used to calculate and apply the priors penalty onto the
	// ticket value.
	{
		int temp;
		temp = ticket * priors;
		return temp;
	}
}
// That's it. That's all. Thank you.