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
            int tries = 20;

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

            // make array bitonic
            int mid = new Random().Next(0, n);
            Array.Sort(arr, 0, mid);
            Array.Sort(arr, mid, n - mid, new ReverseComparer<int>());

            // create bitonic
            var bitonic = new BitonicArray(arr);

            // test
            Console.WriteLine($"n={n} density={density} tries={tries}\nx\tn\t3lgn\t2lgn");
            var rnd = new Random();
            for (int i = 0; i < tries; i++)
            {
                int x = rnd.Next(n * density);
                Console.WriteLine($"{x}\t{bitonic.FindLinear(x)}\t{bitonic.FindLogarithmicBad(x)}\t{bitonic.FindLogarithmicGood(x)}");
            }

            Console.ReadKey();
        }
    }
}