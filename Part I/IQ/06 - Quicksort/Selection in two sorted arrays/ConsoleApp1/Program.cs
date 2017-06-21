using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] a = { 1, 3, 5, 7, 9 };
            int[] b = { 2, 4, 6, 8 };

            for (int i = 1; i <= a.Length + b.Length; i++)
            {
                int k = FindKth(a, b, i);
            }

            Console.ReadKey();
        }

        public static int FindKth(int[] a, int[] b, int k)
        {
            if (k < 1 || k > a.Length + b.Length)
                throw new ArgumentException();

            int lo = -1;
            int hi = a.Length - 1;
            int i, j;

            while (hi - lo > 1)
            {
                i = lo + (hi - lo) / 2;
                j = k - i - 2;

                if (j < 0) // i + 1 >= k
                    hi = i;
                else if (j > b.Length - 1)
                    lo = i;
                else if (a[i] < b[j])
                    hi = i;
                else
                    lo = i;
            }

            i = hi;
            j = k - i - 2;

            int r;
            if (i + 1 >= k)
            {
                r = a[i];
            }
            else if (j > b.Length - 1)
            {
                r = -1; // TODO
            }
            else if (a[i] < b[j])
            {
                if (i < 1)
                    r = b[j];
                else
                    r = a[i - 1] < b[j]
                        ? a[i - 1]
                        : b[j];
            }
            else
            {
                if (j + 1 > b.Length - 1)
                    r = a[i];
                else
                    r = a[i] < b[j + 1]
                        ? a[i]
                        : b[j + 1];
            }

            Console.WriteLine($"Kth: {k} ({i}, {j}) {r}");

            return r;
        }
    }
}