using System;

namespace ConsoleApp1
{
    public static class ArrayExtensions
    {
        // https://stackoverflow.com/a/110570/1392696
        public static void Shuffle<T>(this T[] a, Random rnd)
        {
            int n = a.Length;
            while (n > 1)
            {
                int k = rnd.Next(n--);
                a.Swap(n, k);
            }
        }

        public static void Swap<T>(this T[] a, int i, int j)
        {
            var x = a[i];
            a[i] = a[j];
            a[j] = x;
        }
    }
}
