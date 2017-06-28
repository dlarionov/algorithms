using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var max = new MaxPQ<int>();
            //var min = new MinPQ<int>();
            for (int i = 0; i < 10; i++)
            {
                max.Push(i);
                //min.Push(i);
            }

            while (max.Count > 0)// && min.Count > 0)
            {
                Console.WriteLine($"{max.Pop()}");
            }

            Console.ReadKey();
        }
    }
}