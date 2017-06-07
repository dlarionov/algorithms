using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 100000;
            int density = 3;
            int range = n * density;
            int tries = 20;
            var rnd = new Random();

            // create array of random distinct integers
            int[] arr = new int[n];
            var set = new HashSet<int>();
            for (int i = 0; i < n; i++)
            {
                int x = rnd.Next(range);
                while (set.Contains(x))
                {
                    x = rnd.Next(range);
                }
                set.Add(x);
            }
            set.CopyTo(arr);

            // make array bitonic
            int mid = new Random().Next(0, n);
            Array.Sort(arr, 0, mid);
            Array.Sort(arr, mid, n - mid, new ReverseComparer<int>());

            // create and test bitonic
            var bitonic = new BitonicArray(arr);
            Console.WriteLine($"n={n} density={density} tries={tries}\nx\tn\t3lgn\t2lgn");
            for (int i = 0; i < tries; i++)
            {
                int x = rnd.Next(range);
                Console.WriteLine($"{x}\t{bitonic.FindLinear(x)}\t{bitonic.FindLogarithmicBad(x)}\t{bitonic.FindLogarithmicGood(x)}");
            }

            Console.ReadKey();
        }
    }
}