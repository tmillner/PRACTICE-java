package Threads;

/**
 * Created by Trevor on 10/31/2014.
 */
public class SleepInterruptAndJoin {
	private static void threadMessage(String message) {
		Thread currentThread = Thread.currentThread();
		System.out.format("%s thread says: %s%n", currentThread, message);
	}

	private static class MessageThread implements Runnable {

		private String[] string = {"今天是万圣节", "于是我很兴奋",
		"不得不请几个朋友一起玩", "希望时间不要过的太快", "这样不会浪费这个好日子"};

		@Override
		public void run() {
			try
			{
				for (int i = 0; i < string.length; i++)
				{
					Thread.sleep(2000);
					threadMessage(string[i]);
				}
			} catch(InterruptedException e){
				threadMessage("Hey, You interrupted me!");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		long defaultWaitTime = 1000*60&60; // 1hr

		if(args.length>0){
			try
			{
				defaultWaitTime = 1000 * Long.parseLong(args[0]); // Seconds
			} catch(NumberFormatException e){
				System.err.println("Argument must be a number");
				System.exit(1);
			}
		}

		threadMessage("About to run program from the main thread");
		long startTime = System.currentTimeMillis();
		Thread secondThread = new Thread(new MessageThread());
		secondThread.start();

		threadMessage("Waiting for 2nd thread (message thread) to finish");
		while(secondThread.isAlive()){
			threadMessage("Still processing");
			// Wait maximum of 1 second for message thread to finish
			secondThread.join(1000);

			if(System.currentTimeMillis() - startTime > defaultWaitTime &&
					secondThread.isAlive()){
				threadMessage("Tired of waiting, commencing interrupt");
				secondThread.interrupt();

				secondThread.join();
			}
		}
		threadMessage("Main Thread finished executing! :)");
	}
}