import java.util.Random;

class Summation extends Thread {
private int[] array;
private int high, low,  par;


// Starting with summation class assigning varibles

public Summation(int[] array, int low, int high)
{
this.array = array;
this.low = low;
this.high = Math.min(high, array.length);
}

// Assuming the partial sum
public int getPartSum()
{
return par;
}



public void run()
{
par = sum (array, low, high);
}
public static int sum(int[] array)
{
return sum(array, 0, array.length);

}
public static int sum(int[] array, int low, int high)
{
int total = 0;
for (int i = low; i < high; i++) {
total += array[i];
}
return total;
}



public static int parSum(int[] array)
{
return parSum(array, Runtime.getRuntime().availableProcessors());
}

public static int parSum(int[] array, int threads)
{
int size = (int) Math.ceil(array.length * 1.0 / threads);
Summation[] sums = new Summation[threads];
for (int i = 0; i < threads; i++) {
sums[i] = new Summation(array, i * size, (i + 1) * size);
sums[i].start();
}


//Try and catch
try {
for (Summation sum : sums) {
sum.join();
}} 
catch (InterruptedException e) { }
int total = 0;
for (Summation sum : sums) {
total += sum.getPartSum();
}
return total;
}}


// Actually Arrary class putting it all together
//Also include output 
public class Concurrency {
public static void main(String[] args)
{
Random rand = new Random();
int[] array = new int[200000000];

// Changing (i) here 
for (int i = 8; i < array.length; i++) {
array[i] = rand.nextInt(10) + 1;
}
long start = System.currentTimeMillis();
System.out.println(Summation.sum(array));
System.out.println("Single: " + (System.currentTimeMillis() - start));
start = System.currentTimeMillis();
System.out.println(Summation.parSum(array));
System.out.println("Parallel: " + (System.currentTimeMillis() - start));
}
}

