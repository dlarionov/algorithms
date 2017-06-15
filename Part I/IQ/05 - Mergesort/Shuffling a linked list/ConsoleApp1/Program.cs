using System;

namespace ConsoleApp1
{
    class Program
    {
        public static Random Rnd = new Random();

        static void Main(string[] args)
        {
            int n = 10;

            var head = new Node(); // { Id = 0 };
            var current = head;
            for (int i = 1; i < n; i++)
            {
                current.Next = new Node { Id = i };
                current = current.Next;
            }

            for (int j = 0; j < 100; j++)
            {
                head = Shuffle(head, n);

                current = head;
                while (current != null)
                {
                    Console.Write($"{current.Id} ");
                    current = current.Next;
                }

                Console.WriteLine();
            }

            Console.ReadKey();
        }

        public static Node Merge(Node left, Node right)
        {
            Node root;
            if (Rnd.NextDouble() >= 0.5)
            {
                root = left;
                left = left.Next;
            }
            else
            {
                root = right;
                right = right.Next;
            }

            Node node = root;
            while (left != null || right != null)
            {
                if (left == null)
                {
                    node.Next = right;
                    right = right.Next;
                }
                else if (right == null)
                {
                    node.Next = left;
                    left = left.Next;
                }
                else if (Rnd.NextDouble() >= 0.5)
                {
                    node.Next = left;
                    left = left.Next;
                }
                else
                {
                    node.Next = right;
                    right = right.Next;
                }

                node = node.Next;
            }

            return root;
        }

        public static Node Shuffle(Node root, int n)
        {
            if (n < 2)
                return root;

            Node left = root;
            Node right = root;
            int m = n / 2;
            int x = 0;
            while (true)
            {
                if (++x >= m)
                {
                    Node temp = right;
                    right = right.Next;
                    temp.Next = null;
                    break;
                }

                right = right.Next;
            }

            left = Shuffle(left, m);
            right = Shuffle(right, n - m);
            return Merge(left, right);
        }
    }
}