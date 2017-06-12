using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            // var r = new ThreeColor(new int[] { 0, 1, 2, 1, 1, 1 }).Test();

            int n = 10;
            int tries = 10;
            var rnd = new Random();
            int errors = 0;

            for (int j = 0; j < tries; j++)
            {
                int[] a = new int[n];
                for (int i = 0; i < n; i++)
                {
                    a[i] = rnd.Next(3);
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