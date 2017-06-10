using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int range = 100;
            int tries = 10;
            var stack = new MaxStack();
            var rnd = new Random();
            for (int i = 0; i < tries; i++)
            {
                int j = rnd.Next(range);
                stack.Push(j);
                Console.Write($"push: {j} max: {stack.Max()}\n");
            }
            Console.WriteLine();

            while (stack.Size() > 0)
            {
                Console.Write($"max: {stack.Max()} pop: {stack.Pop()}\n");
            }
            Console.WriteLine();
            Console.ReadKey();
        }
    }
}