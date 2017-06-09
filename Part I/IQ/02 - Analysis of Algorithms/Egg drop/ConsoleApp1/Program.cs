using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int max = 100;
            int tries = 1000000;
            var rnd = new Random();
            int errors = 0;

            // new Experiment(5, 3).Version2(100, 100);

            for (int j = 0; j < tries; j++)
            {
                int n = rnd.Next(1, max);
                int t = rnd.Next(1, n + 1);
                int logn = (int)Math.Ceiling(Math.Log(n, 2));
                int logt = (int)Math.Ceiling(Math.Log(t, 2));
                int sqrtn = (int)Math.Ceiling(Math.Sqrt(n));
                int sqrtt = (int)Math.Ceiling(Math.Sqrt(t));

                var ex = new Experiment(n, t);
                int t0 = ex.Version0(t);
                int t1 = ex.Version1(logn + 1, logn + 1);
                int t2 = ex.Version2(logt + 2, 2 * logt + 2);
                int t3 = ex.Version3(2 * sqrtn - 1);
                int t4 = ex.Version4(4 * sqrtt);
                if (t != t0 || t != t1 || t != t2 || t != t3|| t != t4)
                {
                    Console.WriteLine($"{n}\t{t0}\t{t1}\t{t2}\t{t3}\t{t4}");
                    errors++;
                }
            }

            Console.WriteLine($"Total tries: {tries}; errors: {errors}\nPress any key...");
            Console.ReadKey();
        }
    }
}