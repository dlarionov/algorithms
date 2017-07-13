using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            var rnd = new Random();
            var bst = new BST();
            for (int i = 0; i < 100; i++)
            {
                bst.Add(i);
            }

            var root = bst.Root;

            Console.ReadKey();
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