package syncronizedOnlineBanking;

/**
 * Deposit and withdraw functionality for the Online Banking Application without
   using synchronized.
 * 
 * @author Hp 
 * The synchronized methods prevent more than one thread from accessing an object's critical
   method code simultaneously.
 */

public class SyncronizedOnlineBanking implements Runnable {

	private Balance bal = new Balance();

	public static void main(String[] args) {

		Thread thread1 = new Thread(new SyncronizedOnlineBanking());
		Thread thread2 = new Thread(new SyncronizedOnlineBanking());
		thread1.setName("Nitish");
		thread2.setName("Tanya");
		thread1.start();
		thread2.start();
	}

	class Balance {
		private int balance = 50;

		public int getBal() {
			return balance;
		}

		public void withdraw(int amount) {
			balance = balance - amount;
		}
	}

	@Override
	public void run() {

		for (int r = 0; r < 3; r++) {
			accntStatus(20);
			if (bal.getBal() < 0) {
				System.out.println("account is overdrawn!");
			}
		}
	}

	/*
	 *Synchronization is the solution to prevents race conditions from happening.
	 * The synchronized keyword places a lock on an important object or piece of code 
	    to make sure that only one thread at a time will have access.
	 */
	
	
	
	// Creating synchronized method to overcome race around condition.
	private synchronized void accntStatus(int amt) {
		if (bal.getBal() >= amt) {
			System.out.println(Thread.currentThread().getName() + " is withdrawling");
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
			}
			bal.withdraw(amt);
			System.out.println(Thread.currentThread().getName() + " is depositing");
		} else {
			System.err
					.println(Thread.currentThread().getName() + " has not enough balance to withdraw " + bal.getBal());

		}
	}
}
