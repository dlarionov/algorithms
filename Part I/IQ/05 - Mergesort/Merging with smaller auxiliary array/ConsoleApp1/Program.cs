using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] a = new int[] { 5, -2, 4, 42, 0, 3, 2, 1 };
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
            Merge2(a, aux, lo, mid, hi);
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

        private static void Merge2(int[] a, int[] aux, int lo, int mid, int hi)
        {
            Console.WriteLine($"({lo}, {mid}, {hi})");
            for (int k = lo; k <= hi; k++)
                Console.Write($"{a[k]} ");
            Console.WriteLine();

            for (int k = lo; k <= mid; k++)
                aux[k] = a[k];

            int i = lo;
            int j = mid + 1;
            for (int k = lo; k <= mid; k++)
            {
                if (i > mid)
                    a[k] = aux[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (a[j] < aux[i])
                    a[k] = a[j++];
                else
                    a[k] = aux[i++];
            }

            int separator = 0;
            for (int k = lo; k <= hi - j; k++)
            {
                aux[k] = a[k + j];
                separator++;
            }

            j = i;
            i = lo;
            for (int k = mid + 1; k <= hi; k++)
            {
                if (i > separator)
                    a[k] = aux[j++];
                else if (j > mid)
                    a[k] = aux[i++];
                else if (aux[j] < aux[i])
                    a[k] = aux[j++];
                else
                    a[k] = aux[i++];
            }

            for (int k = lo; k <= hi; k++)
                Console.Write($"{a[k]} ");
            Console.WriteLine();
        }
    }
}