using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 100;
            int tries = 10000;
            var rnd = new Random();
            int errors = 0;

            for (int j = 0; j < tries; j++)
            {
                int[] a = new int[n];
                for (int i = 0; i < n; i++)
                {
                    a[i] = rnd.Next(-1, 2);
                }

                var passed = new ThreeColor(a).Test();
                if (!passed)
                {
                    errors++;
                    foreach (var i in a)
                        Console.Write($"{i} ");
                    Console.WriteLine();
                }
            }

            Console.WriteLine($"Total tries: {tries}; errors: {errors}\nPress any key...");
            Console.ReadKey();
        }
    }
}