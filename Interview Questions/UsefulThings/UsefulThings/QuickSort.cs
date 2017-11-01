using System;

namespace UsefulThings
{
    public static class QuickSort
    {
        public static void Shuffle(this Random rng, int[] array)
        {
            int n = array.Length;
            while (n > 1)
            {
                int k = rng.Next(n--);
                Swap(array, n, k);
            }
        }

        public static void Sort(int[] a)
        {
            Sort(a, 0, a.Length - 1);
        }

        private static void Sort(int[] a, int lo, int hi)
        {
            if (hi <= lo)
                return;

            int j = Partition(a, lo, hi);
            Sort(a, lo, j - 1);
            Sort(a, j + 1, hi);
        }

        private static int Partition(int[] a, int lo, int hi)
        {
            int i = lo;
            int j = hi + 1;
            while (true)
            {
                while (a[++i] < a[lo])
                    if (i == hi) break;
                while (a[lo] < a[--j])
                    if (j == lo) break;

                if (i >= j) break;
                Swap(a, i, j);
            }
            Swap(a, lo, j);
            return j;
        }

        private static void Swap(int[] a, int i, int j)
        {
            var x = a[i];
            a[i] = a[j];
            a[j] = x;
        }
    }
}
