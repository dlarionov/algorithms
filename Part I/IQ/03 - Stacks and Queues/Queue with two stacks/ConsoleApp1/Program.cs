using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var queue = new TwoStackQueue<int>();

            for (int i = 0; i < 10; i++)
            {
                queue.enqueue(i);
            }

            while (queue.size() > 0)
            {
                Console.Write($"{queue.dequeue()} ");
            }

            Console.ReadKey();
        }
    }
}