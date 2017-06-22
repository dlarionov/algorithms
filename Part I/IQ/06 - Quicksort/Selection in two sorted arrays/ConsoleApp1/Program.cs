﻿using System;

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

            // left minus one and right pus one trick
            int lo = -1;
            int hi = a.Length;
            int i, j;

            while (hi - lo > 1)
            {
                i = lo + (hi - lo) / 2;
                j = k - i - 2;

                if (j < 0)
                    hi = i;
                else if (j > b.Length - 1)
                    lo = i;
                else if (a[i] < b[j])
                    lo = i;
                else
                    hi = i;
            }

            // all items greather ok equal hi is is ok
            i = hi;
            j = k - i - 2;
            int r;
            if (hi == a.Length)
                r=  b[j];
            else
                r = j + 1 > b.Length -1
                    ? a[i]
                    : Math.Min(a[i], b[j + 1]);

            Console.WriteLine($"Kth: {k} ({lo}, {hi}) {r}");

            return r;
        }
    }
}