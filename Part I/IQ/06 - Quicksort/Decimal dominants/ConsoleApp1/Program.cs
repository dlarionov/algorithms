using System;
using System.Linq;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var rnd = new Random();
            int tries = 10000;
            int n = 10000;
            int k = 100; // kth dominant
            int d = 100;
            int errors = 0;
            for (int i = 0; i < tries; i++)
            {
                int[] a = new int[n];
                for (int j = 0; j < n; j++)
                    a[j] = rnd.Next(d);

                int[] calc = KthDominants(a, k);
                int[] test = KthDominantsBrute(a, k);

                if (!Equals(calc, test))
                {
                    //foreach (var j in calc)
                    //    Console.Write($"{j} ");
                    //Console.WriteLine();

                    //foreach (var j in test)
                    //    Console.Write($"{j} ");
                    //Console.WriteLine();

                    errors++;
                }
            }
            Console.WriteLine($"tries: {tries} errors: {errors}");
            Console.ReadKey();
        }

        public static int[] KthDominantsBrute(int[] arr, int k)
        {
            var cnt = arr.Length / k;
            var list = arr.GroupBy(x => x)
                .Select(x => new { item = x.Key, count = x.Count() })
                .Where(x => x.count >= cnt)
                .OrderBy(x => x.item)
                .ToList();

            //foreach (var i in list)
            //    Console.Write($"{i.item} {i.count}\n");

            return list.Select(i => i.item).ToArray();
        }

        // The trick is that the dominant element remains same if you delete any k distinct items from the array.
        public static int[] KthDominants(int[] arr, int k)
        {
            // k munus one counters
            var list = new Dictionary<int, int>();
            int grade = 1;
            for (int i = 0; i < arr.Length; i++)
            {
                var x = arr[i];
                if (list.ContainsKey(x))
                    list[x]++;
                else
                {
                    if (list.Count < k)
                        list.Add(x, 1);
                    else
                    {
                        foreach (var j in list.Keys.ToArray())
                        {
                            if (list[j] > 1)
                                list[j]--;
                            else
                                list.Remove(j);
                        }
                        grade++;
                    }
                }
            }

            var cnt = arr.Length / k - grade;
            return list
                .Where(i => i.Value > cnt)
                .Select(i => i.Key)
                .OrderBy(i => i)
                .ToArray();
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