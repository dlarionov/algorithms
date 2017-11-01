using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] a = new int[] { 5, -2, 4, 42, 0, 3, 2, 1, 7 };
            for (int k = 0; k < a.Length; k++)
                Console.Write($"{a[k]} ");
            Console.WriteLine();

            MergeSort(a);

            for (int k = 0; k < a.Length; k++)
                Console.Write($"{a[k]} ");
            Console.WriteLine();

            Console.ReadKey();
        }

        public static void MergeSort(int[] a)
        {
            int[] aux = new int[a.Length / 2 + 1];
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
            for (int k = lo; k <= mid; k++)
                aux[k - lo] = a[k];

            int i = lo;
            int j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid)
                    a[k] = a[j++];
                else if (j > hi)
                    a[k] = aux[i++ - lo];
                else if (a[j] < aux[i - lo])
                    a[k] = a[j++];
                else
                    a[k] = aux[i++ - lo];
            }
        }
    }
}