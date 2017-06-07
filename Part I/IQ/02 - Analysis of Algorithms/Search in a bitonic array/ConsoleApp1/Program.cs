using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 20;
            int density = 3;

            // create array of random distinct integers
            var rand = new Random();
            int[] arr = new int[n];
            int range = n * density;
            var set = new HashSet<int>();
            for (int i = 0; i < n; i++)
            {
                int x = rand.Next(range);
                while (set.Contains(x))
                {
                    x = rand.Next(range);
                }
                set.Add(x);
            }
            set.CopyTo(arr);

            // create and print bitonic
            var bitonic = new BitonicArray<int>(arr);
            foreach (var i in bitonic.ToArray())
                Console.Write($"{i} ");
            Console.WriteLine();

            // test
            Console.WriteLine("x\t n\t 3logn\t 2logn2");
            var rnd = new Random();
            for (int i = 0; i < n; i++)
            {
                int j = rnd.Next(n * density);
                Console.WriteLine($"{j}\t{bitonic.FindLinear(j)}\t{bitonic.FindLogarithmicBad(j)}\t{bitonic.FindLogarithmicGood(j)}");
            }

            Console.ReadKey();
        }
    }
}