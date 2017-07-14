using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 100;
            var rnd = new Random();
            var bst = new BST();

            for (int i = 0; i < n; i++)
            {
                bst.Add(rnd.Next(n));
            }

            Traverse(bst.Root, (i) => Console.Write($"{i} "));

            Console.ReadKey();
        }

        public static void Traverse(Node node, Action<int> action)
        {
            var x = node;
            while (x != null)
            {
                action(x.Key);
                x = x.Right;
            }
        }

        public static bool IsBST(Node node, int min = int.MinValue, int max = int.MaxValue)
        {
            if (node == null)
                return true;

            if (node.Key >= max || node.Key <= min)
                return false;

            return IsBST(node.Left, min, node.Key) && IsBST(node.Right, node.Key, max);
        }
    }
}