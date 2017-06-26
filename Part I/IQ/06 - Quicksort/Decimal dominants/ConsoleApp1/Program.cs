﻿using System;
using System.Linq;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var rnd = new Random();
            int tries = 10000;
            int n = 1000;
            int k = 10; // kth dominant
            int density = 10;
            int errors = 0;
            for (int i = 0; i < tries; i++)
            {
                int[] a = new int[n];
                for (int j = 0; j < n; j++)
                    a[j] = rnd.Next(density);

                int[] linear = KthDominants(a, k);
                int[] brute = KthDominantsBrute(a, k);

                Array.Sort(linear);
                Array.Sort(brute);

                if (!Equals(linear, brute))
                    errors++;
            }
            Console.WriteLine($"tries: {tries} errors: {errors}");
            Console.ReadKey();
        }

        public static int[] KthDominantsBrute(int[] arr, int k)
        {
            int count = arr.Length / k;
            var list = arr
                .GroupBy(i => i)
                .Select(i => new { Key = i.Key, Count = i.Count() })
                .Where(i => i.Count >= count)
                .Select(i => i.Key)
                .ToArray();
            return list;
        }

        public static int[] KthDominants(int[] arr, int k)
        {
            var data = new Dominants(arr.Length, k);
            for (int i = 0; i < arr.Length; i++)
            {
                data.Push(arr[i]);
            }
            return data.ToArray();
        }

        public static bool Equals(int[] a, int[] b)
        {
            if (a.Length != b.Length)
                return false;
            for (int i = 0; i < a.Length; i++)
            {
                if (a[i] != b[i])
                    return false;
            }
            return true;
        }
    }
}