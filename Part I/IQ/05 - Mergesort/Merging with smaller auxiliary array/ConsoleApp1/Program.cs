using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] a = new int[] { 5, -2, 4, 42, 0, 0, 3, 2, 1, 1 };
            MergeSort(a);
            foreach (var i in a)
                Console.Write($"{i} ");
            Console.WriteLine();
            Console.ReadKey();
        }

        public static void MergeSort(int[] a)
        {
            int[] aux = new int[a.Length];
            Sort(a, aux, 0, a.Length - 1);
        }

        private static void Sort(int[] a, int[] aux, int lo, int hi)
        {
            if (hi <= lo)
                return;
            int mid = lo + (hi - lo) / 2;
            Sort(a, aux, lo, mid);
            Sort(a, aux, mid + 1, hi);
            Merge(a, aux, lo, mid, hi);
        }

        private static void Merge(int[] a, int[] aux, int lo, int mid, int hi)
        {
            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];

            int i = lo;
            int j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid)
                    a[k] = aux[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (aux[j] < aux[i])
                    a[k] = aux[j++];
                else
                    a[k] = aux[i++];
            }
        }
    }
}