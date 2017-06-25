using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var rnd = new Random();
            int tries = 10;
            int n = 100;
            int dispersion = 10;
            for (int i = 0; i < tries; i++)
            {
                int[] a = new int[n];
                for (int j = 0; j < n; j++)
                {
                    a[j] = rnd.Next(dispersion);
                }

                int[] dominants = Dominants(a, n / 10);

                foreach (var d in dominants)
                    Console.Write($"{d} ");

                Console.WriteLine();
            }
            Console.ReadKey();
        }

        // For any general k, you require O(n log k) time.
        // The trick is that the dominant element remains same if you delete any k distinct items from the array.
        public static int[] Dominants(int[] arr, int k)
        {
            HashSet<int> t = new HashSet<int>();
            int lo = 0;
            int hi = arr.Length - 1;
            while (lo < hi)
            {
                var x = arr[lo];
                if (!t.Contains(x))
                {
                    t.Add(x);

                    Swap(arr, lo, hi--);
                    if (t.Count > k)
                        t.Clear();
                }
                else
                    lo++;
            }

            return arr;

        }

        private static void Swap(int[] arr, int i, int j)
        {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }
}