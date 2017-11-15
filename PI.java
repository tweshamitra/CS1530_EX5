import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;
public class PI{
    public static long inside_circle = 0;

    public static void main(String [] args){
        long num_threads = 0;
        long tot_iterations = 0;
        
        if(args.length != 2 ){
            System.out.println("Enter 2 arguments");
            System.exit(0);
        }
        try{
            num_threads = Long.parseLong(args[0]);
            tot_iterations =  Long.parseLong(args[1]);
        }
        catch(Exception e){
            System.out.println("Invalid input");
            System.exit(0);
        }
        long iterations_per_thread = tot_iterations/num_threads;
        double upper_bound = 1.0001;
        Thread [] ts = new Thread[(int)num_threads];
        for(int i = 0; i < (int)num_threads; i ++){
            Thread t =  new Thread( () ->{
                calculate(upper_bound,iterations_per_thread);
            });
            ts[i] = t;
        }
        for(int x = 0; x <(int) num_threads; x ++){
            ts[x].start();
        }
        for(int x = 0; x <(int) num_threads; x ++){
            try{
                ts[x].join();
            }catch(InterruptedException iex){

            }
            
        }
        double ans = ((double)inside_circle/tot_iterations) *4;
        double ratio = ((double)inside_circle/tot_iterations);
        System.out.println("Total: " + tot_iterations);
        System.out.println("Inside: " + inside_circle);
        System.out.println("Ratio: " +  ratio);
        
        System.out.println("PI: "+ ans);


    }

    public static void calculate(double UB, long iterations){
        long counter = 0;
        for (int i = 0; i < iterations; i++){
            double x = ThreadLocalRandom.current().nextDouble(0, UB);
            double y = ThreadLocalRandom.current().nextDouble(0,UB);
           
            double solution = (double)(Math.pow(x,2) + Math.pow(y,2));
            if(solution < 1.0){
                inside_circle ++;
            }
        }
    }
}