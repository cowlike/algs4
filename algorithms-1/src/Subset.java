public class Subset {
    
   public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
       String s[] = StdIn.readAllStrings();
       RandomizedQueue<String> rq = new RandomizedQueue<String>();
       
       for (String str : s) {
           rq.enqueue(str);
       }
       
       for (int i = 0; i < n; i++) {
           StdOut.println(rq.dequeue());
       }
   }
}