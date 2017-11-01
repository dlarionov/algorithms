using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var max = new MaxPQ<int>();
            var min = new MinPQ<int>();
            var mid = new MidPQ<int>();
            for (int i = 0; i < 10; i++)
            {
                max.Push(i);
                min.Push(i);
                mid.Push(i);
            }

            while (max.Count > 0 && min.Count > 0)
            {
                Console.WriteLine($"{max.Pop()} {min.Pop()} {mid.Pop()}");
            }

            Console.ReadKey();
        }
    }
}