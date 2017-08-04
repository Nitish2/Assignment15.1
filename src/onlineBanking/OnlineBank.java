package onlineBanking;

/**
 * Deposit and withdraw functionality for the Online Banking Application without 
   using synchronized.
 * 
 * @author Hp
 *  Although each time you run this code the output might be a little different.
 */
//Creates class and implements with Runnable interface 
public class OnlineBank implements Runnable {

	private Balance bal = new Balance(); // Creating object of Balance class

	public static void main(String[] args) {
        // Creating threads 
		Thread thread1 = new Thread(new OnlineBank()); 
		Thread thread2 = new Thread(new OnlineBank());
		//Initializing the names to the threads 
		thread1.setName("Nitish");
		thread2.setName("Tanya");
		// Starting the threads 
		thread1.start();
		thread2.start();
	}

	class Balance { //Creates Balance class
		private int balance = 50;

		public int getBal() { //Creates getBal method 
			return balance;  //Return balance 
		}

		public void withdrawal(int amount) { //Creates withdrawal method
			balance = balance - amount;  // It will deduct the amount from the Current balance 
		}
	}

	@Override
	public void run() {  // Creates Run Method 

		for (int r = 0; r<3 ; r++) {  // creating for loop 
			accntStatus(20); // If there's enough in the account make the withdrawal.
			if (bal.getBal() < 0 ) { // if user trying to withdraw greater amount
				System.out.println("Insufficient amount");
			}
		}
	}
/**For the first three attempts,everything is fine.
 This problem is known as a "race condition," where multiple threads can access the same resource 
 and can produce corrupted data.
	**/
	
	// Creating accntStatus method which cause race around condition . 
	private void accntStatus(int amt) { 
		if (bal.getBal() >= amt) { // if user trying to withdraw 
			System.out.println(Thread.currentThread().getName() + " is withdrawling");//Print satement
			try {  //try block 
				Thread.sleep(100); // Put thread to sleep 
			} catch (InterruptedException ex) { // Catch block 
			}
			bal.withdrawal(amt); // Calling withdrawal method 
			System.out.println(Thread.currentThread().getName() + " is depositing");
		} else { // Else statement 
			System.err.println(Thread.currentThread().getName() + " has not enough balance to withdraw "
					+ bal.getBal());

		}
	}
}
