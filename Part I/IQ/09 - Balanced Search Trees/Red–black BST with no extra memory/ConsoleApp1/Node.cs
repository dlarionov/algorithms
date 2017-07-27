using System;

namespace ConsoleApp1
{
    public class Node<T> where T : IComparable<T>
    {
        public T Key { get; set; }
        public Node<T> Left { get; set; }
        public Node<T> Right { get; set; }

        public int Compare(T x)
        {
            int r;
            if (x.CompareTo(Key) > 0) r = 1;
            else if (x.CompareTo(Key) < 0) r = -1;
            else r = 0;
            return IsRed(this) ? -r : r;
        }

        public static bool IsRed(Node<T> x)
        {
            if (x == null)
                return false;
            if (x.Left == null && x.Right == null)
                return false;
            if (x.Left == null)
                return x.Right.Key.CompareTo(x.Key) < 0;
            if (x.Right == null)
                return x.Left.Key.CompareTo(x.Key) > 0;

            return x.Left.Key.CompareTo(x.Right.Key) > 0;
        }

        public void Swap()
        {
            var x = Left;
            Left = Right;
            Right = x;
        }
    }
}
