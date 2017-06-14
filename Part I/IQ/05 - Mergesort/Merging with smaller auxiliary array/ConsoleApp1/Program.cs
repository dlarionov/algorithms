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
            Merge3(a, aux, lo, mid, hi);
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

            for (int l = 0; l <= mid - lo; l++)
                aux[l] = a[l + lo];

            int i = 0;
            int j = mid + 1;
            for (int k = lo; k <= mid; k++)
            {
                if (i > mid - lo)
                    a[k] = a[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (a[j] < aux[i])
                    a[k] = a[j++];
                else
                    a[k] = aux[i++];
            }

            for (int l = 0; l <= hi - j; l++)
                aux[l] = a[l + j];

            int quater = hi - j;
            j = i;
            i = 0;
            for (int k = mid + 1; k <= hi; k++)
            {
                if (i > quater)
                    a[k] = aux[j++];
                else if (j > mid - lo)
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

        private static void Merge3(int[] a, int[] aux, int lo, int mid, int hi)
        {
            for (int k = lo; k <= mid; k++)
                aux[k] = a[k];

            int i = lo;
            int j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid)
                    a[k] = a[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (a[j] < aux[i])
                    a[k] = a[j++];
                else
                    a[k] = aux[i++];
            }
        }
    }
}