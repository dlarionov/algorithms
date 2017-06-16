using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 16;
            var rnd = new Random();

            var nuts = new int[n];
            var bolts = new int[n];
            for (int i = 0; i < n; i++)
            {
                nuts[i] = i;
                bolts[i] = i;
            }

            nuts.Shuffle(rnd);
            bolts.Shuffle(rnd);

            //int[] nuts = { 1, 2, 0, 3 };
            //int[] bolts = { 3, 0, 2, 1 };

            for (int i = 0; i < n; i++)
            {
                Console.WriteLine($"{nuts[i]} {bolts[i]}");
            }
            Console.WriteLine();

            PickUp(nuts, bolts, 0, n - 1);

            for (int i = 0; i < n; i++)
            {
                Console.WriteLine($"{nuts[i]} {bolts[i]}");
            }
            Console.WriteLine();

            Console.ReadKey();
        }

        public static void PickUp(int[] nuts, int[] bolts, int lo, int hi)
        {
            if (hi <= lo)
                return;

            int i = Partition(nuts, bolts[lo], lo, hi);
            int j = Partition(bolts, nuts[i], lo, hi);

            PickUp(nuts, bolts, lo, j - 1);
            PickUp(nuts, bolts, j + 1, hi);
        }

        private static int Partition(int[] a, int x, int lo, int hi)
        {
            int i = lo, j = hi, k = -1;
            while (true)
            {
                while (a[i] <= x)
                {
                    if (k < 0 && a[i] == x)
                        k = i;
                    if (i == hi)
                        break;
                    i++;
                }

                while (a[j] > x)
                {
                    if (j == lo)
                        break;
                    j--;
                }

                if (i >= j) break;
                a.Swap(i, j);
            }

            a.Swap(k, j);
            return j;
        }
    }
}