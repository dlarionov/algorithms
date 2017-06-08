using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int size = 100;
            int density = 3;
            int range = size * density;
            int tries = 10000;
            int searches = 10;
            var rnd = new Random();
            int errors = 0;

            for (int j = 0; j < tries; j++)
            { 
                // create array of random distinct integers
                int[] arr = new int[size];
                var set = new HashSet<int>();
                for (int i = 0; i < size; i++)
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
                int mid = new Random().Next(0, size);
                Array.Sort(arr, 0, mid);
                Array.Sort(arr, mid, size - mid, new ReverseComparer<int>());

                // create and test bitonic
                
                var bitonic = new BitonicArray(arr);
                for (int i = 0; i < searches; i++)
                {
                    int x = rnd.Next(range);
                    int a = bitonic.FindLinear(x);
                    int b = bitonic.FindLogarithmicBad(x);
                    int c = bitonic.FindLogarithmicGood(x);

                    if (a != b || a != c)
                    {
                        Console.WriteLine($"{x}\t{a}\t{b}\t{c}");
                        errors++;
                    }
                }
            }

            Console.WriteLine($"\nTotal searches: {tries * searches}; errors: {errors}\nPress any key...");
            Console.ReadKey();
        }
    }
}