using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var pq = new RandomMaxPQ<int>(new Random());
            for (int i = 1; i < 20; i++)
            {
                pq.Push(i);
            }

            while (pq.Count > 0)
            {
                Console.WriteLine($"{pq.DeleteRandom()}");
            }

            Console.ReadKey();
        }
    }
}