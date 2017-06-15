using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
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

            head = Shuffle(head, n);

            current = head;
            while (current != null)
            {
                Console.Write($"{current.Id} ");
                current = current.Next;
            }

            Console.ReadKey();
        }

        private static bool Rnd()
        {
            return new Random().Next(100) < 50;
        }

        public static Node Merge(Node left, Node right)
        {
            Node root;
            if (Rnd())
            {
                root = left;
                left = left.Next;
            }
            else
            {
                root = right;
                right = right.Next;
            }

            while (left != null && right != null)
            {
                if (left == null)
                {
                    root.Next = right;
                    right = right.Next;
                }
                else if (right == null)
                {
                    root.Next = left;
                    left = left.Next;
                }
                else if (Rnd())
                {
                    root.Next = left;
                    left = left.Next;
                }
                else
                {
                    root.Next = right;
                    right = right.Next;
                }
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