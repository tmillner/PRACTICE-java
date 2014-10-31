package Threads;

/**
 * Created by Trevor on 10/31/2014.
 */
public class Sleeping {

	public static void main(String[] args) throws InterruptedException
	{
		String[] strings = {"First sentence", "Second sentence", "Another Sentence (3)",
			"Last, 4th sentence woohoo!!"};
		try
		{
			for (int i = 0; i < strings.length; i++)
			{
				Thread.sleep(Long.parseLong(args[0]));
				System.out.println(strings[i]);
			}
		} catch (InterruptedException e){
			System.out.println("Program has been terminated in main thread");
			return;
		}
	}
}
