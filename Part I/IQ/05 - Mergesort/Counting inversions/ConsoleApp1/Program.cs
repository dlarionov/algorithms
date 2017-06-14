using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var data = new List<int[]>
            {
                new int[] { 1, 2, 3, 4 },
                new int[] { 1, 2, 4, 3 },
                new int[] { 2, 1, 4, 3 },
                new int[] { 4, 1, 2, 3 },
            };

            foreach (var a in data)
            {
                for (int k = 0; k < a.Length; k++)
                    Console.Write($"{a[k]} ");
                Console.WriteLine();

                int inversions = MergeSort(a);

                for (int k = 0; k < a.Length; k++)
                    Console.Write($"{a[k]} ");
                Console.WriteLine($"\ninversions: {inversions}");
            }

            Console.ReadKey();
        }

        public static int MergeSort(int[] a)
        {
            int[] aux = new int[a.Length];
            return Sort(a, aux, 0, a.Length - 1);
        }

        private static int Sort(int[] a, int[] aux, int lo, int hi)
        {
            if (hi <= lo)
                return 0;
            int mid = lo + (hi - lo) / 2;
            int left = Sort(a, aux, lo, mid);
            int right = Sort(a, aux, mid + 1, hi);
            int self = Merge(a, aux, lo, mid, hi);
            return left + right + self;
        }

        private static int Merge(int[] a, int[] aux, int lo, int mid, int hi)
        {
            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];

            int i = lo;
            int j = mid + 1;
            int inversions = 0;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid)
                    a[k] = aux[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (aux[j] < aux[i])
                {
                    a[k] = aux[j++];
                    inversions++;
                }
                else
                    a[k] = aux[i++];
            }
            return inversions;
        }
    }
}