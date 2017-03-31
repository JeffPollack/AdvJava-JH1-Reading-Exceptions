package jh1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Reading_With_Exceptions {
	FileInputStream fis = null;
	FileOutputStream fos = null;
	Scanner read = null;
	PrintStream ps = null;
	
	
	void copyInts(Scanner scanner, PrintStream printStream)
	{
		int setnum = 0;
		for (int i=0; i<2; ++i)
		{
			if (scanner.hasNext())
			{
				try
				{
					setnum = scanner.nextInt();            
					//printStream.println(setnum);
				}
				catch (InputMismatchException e)
				{
					System.out.println(e+ " bad integer: "+ scanner.next());
				}
			}
		}
		
		int count = 0;
		while (scanner.hasNext())
		{
			if(count == setnum && setnum != 0)
				break;
			try
			{
				int x = scanner.nextInt();           
				//printStream.printf(x + " ");
				count++;
				if(count % 10 == 0)
				 printStream.println(x);
				else
				 printStream.printf(x + " ");
				//System.out.println(x);
			}
			
			
			catch (InputMismatchException e)
			{
				System.out.println(e+ " bad integer: "+ scanner.next());
			}
		}

	}
	
	void process(String inputFileName, String outputFileName)
    {
        Scanner scanner=null;
        PrintStream printStream=null;
        
        try
        {
            FileInputStream fi = new FileInputStream(inputFileName);
            scanner = new Scanner(fi);
            FileOutputStream fo = new FileOutputStream(outputFileName, true);
            
            printStream = new PrintStream(fo);
            copyInts(scanner, printStream);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("error: "+e);
        }
        finally
        {
            if (scanner != null)
                scanner.close();
            if (printStream != null)
                printStream.close();
        }
    }
        
    public static void main(String[] args) {
        Reading_With_Exceptions rwe = new Reading_With_Exceptions();
        //rwe.process(args, outputFileName);
        for (int i=0; i < args.length -1; i++)
        {
            System.out.println("\n\n=========== Processing "+ args[i] + " ==========\n");
            rwe.process(args[i], args[args.length - 1]);
        }
        System.out.println();
        printToScreen(args[args.length - 1]);
    }

    // For the last step, we Copy the contents of the file to the screen
    private static void printToScreen(String filename)
    {
        Scanner scan = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            scan = new Scanner(fis);
            while (scan.hasNextLine())
            {
                System.out.println(scan.nextLine());
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("printToScreen: can't open: " + filename);
        }
        finally
        {
            if (scan != null)
                scan.close();
        }
    }
}