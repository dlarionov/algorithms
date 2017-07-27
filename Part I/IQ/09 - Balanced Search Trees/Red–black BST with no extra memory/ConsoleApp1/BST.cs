using System;

namespace ConsoleApp1
{
    /// <summary>
    /// Red-black binary search tree
    /// </summary>
    public class BST<T> where T : IComparable<T>
    {
        public Node<T> Root { get; private set; }

        public Node<T> Get(T key)
        {
            var x = Root;
            while (x != null)
            {
                if (x.Key.CompareTo(key) < 0) x = x.Right;
                else if (x.Key.CompareTo(key) > 0) x = x.Left;
                else return x;
            }
            return null;
        }

        public void Put(T key)
        {
            Root = Put(Root, key);
        }

        private Node<T> Put(Node<T> h, T key)
        {
            if (h == null)
                return new Node<T> { Key = key, IsRed = true };

            if (h.Key.CompareTo(key) < 0) h.Right = Put(h.Right, key);
            else if (h.Key.CompareTo(key) > 0) h.Left = Put(h.Left, key);
            else { }

            if (IsRed(h.Right) && !IsRed(h.Left)) h = RotateLeft(h);
            if (IsRed(h.Left) && IsRed(h.Left.Left)) h = RotateRight(h);
            if (IsRed(h.Left) && IsRed(h.Right)) FlipColors(h);

            return h;
        }

        private bool IsRed(Node<T> x)
        {
            if (x == null)
                return false;
            return x.IsRed;
        }

        private Node<T> RotateLeft(Node<T> h)
        {
            // assert isRed(h.right);
            var x = h.Right;
            h.Right = x.Left;
            x.Left = h;
            x.IsRed = h.IsRed;
            h.IsRed = true;
            return x;
        }

        private Node<T> RotateRight(Node<T> h)
        {
            // assert isRed(h.left);
            var x = h.Left;
            h.Left = x.Right;
            x.Right = h;
            x.IsRed = h.IsRed;
            h.IsRed = true;
            return x;
        }

        private void FlipColors(Node<T> h)
        {
            //assert !isRed(h);
            //assert isRed(h.left);
            //assert isRed(h.right);
            h.IsRed = true;
            h.Left.IsRed = false;
            h.Right.IsRed = false;
        }
    }
}
