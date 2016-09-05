/**
* Assignment 2                TimeList.java             Due: 26 April 2013
* Login in: cs12sbd
**/

/**
* Class TimeList - Class header comment
* This class is for calculating the time for List12. This class is tested on 
* either LinkedList or ArrayList and front or back. 
* @author Pin Chu cs12sbd
*/


import java.util.ArrayList;

public class TimeList<E>
{
  public static void main(String args[])
  {
    if (args.length != 2)
    {
      System.out.println
        ("Usage: java TimeList [LinkedList or ArrayList] [front or back]");
      System.exit(1);
    }
    String type = args[0];
    String direction = args[1];
    
    // Declare two different lists: LinkedList and ArrayList.
    List12<Integer> linkedList = new List12<Integer>();
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    // Declare an time array with size of 50.
    double[] exTime = new double [50];
    
    // for loop for N ranging from 2,000 to 50,000 in steps of 1000
    for(int i = 2000; i <= 50000; i+= 1000)
    {
      int a = 0;
      // To attempt to run the Java garbage collector before.
      System.gc();
      // It returns at the start of the current system time in nanoseconds.
      long start = System.nanoTime();
      // The first option is front and LinkedList.
      if(direction == "front" && type == "LinkedList")
      {
        linkedList.add(0,i);
      }
      // The second option is front and ArrayList.
      if(direction == "front" && type == "ArrayList")
      {
        arrayList.add(0,i);
      }
      // The third option is back and LinkedList.
      if(direction == "back" && type == "LinkedList")
      {
        linkedList.add(i);
      }
      // The forth option is back and ArrayList.
      if(direction == "back" && type == "ArrayList")
      {
        arrayList.add(i);
      }
      // It returns at the end of the current system time in nanoseconds.
      long end = System.nanoTime();
      // The results of the calculation stored into the time array.
      exTime[a] = (end - start)/ 1.0e9;
      // Clear out the lists.
      linkedList.clear();
      arrayList.clear();
      a++;
      computeAndDisplay(exTime, i);
    }
  }
    
  public static void computeAndDisplay(double arr[], int number)
  {
    double sum = 0.0;
    double len = arr.length;
    for(int i = 0; i < len; i++)
    {
      sum = sum + arr[i];
    }
    double mean = sum/len;
    
    double squaredSum = 0.0;
    for(int i = 0; i < len; i++)
    {
      squaredSum = (sum + arr[i]) * (sum + arr[i]);
    }
    
    double standardDevi = Math.sqrt((squaredSum/len) - (mean*mean));
    System.out.format("%d %.6f\t %.6f\n",number, mean, standardDevi);
    
  }
}

      
      
      