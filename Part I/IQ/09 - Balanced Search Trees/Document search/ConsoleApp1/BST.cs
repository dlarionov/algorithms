using System;

namespace ConsoleApp1
{
    /// <summary>
    /// Red-black binary search tree
    /// </summary>
    /// <typeparam name="TKey">Key</typeparam>
    /// <typeparam name="TValue">Value</typeparam>
    public class BST<TKey, TValue> where TKey : IComparable<TKey>
    {
        public Node<TKey, TValue> Root { get; private set; }

        public TValue Get(TKey key)
        {
            var x = Root;
            while (x != null)
            {
                if (x.Key.CompareTo(key) < 0) x = x.Right;
                else if (x.Key.CompareTo(key) > 0) x = x.Left;
                else return x.Value;
            }
            return default(TValue);
        }

        public void Put(TKey key, TValue value)
        {
            Root = Put(Root, key, value);
        }

        private Node<TKey, TValue> Put(Node<TKey, TValue> h, TKey key, TValue value)
        {
            if (h == null)
                return new Node<TKey, TValue> { Key = key, Value = value, IsRed = true };

            if (h.Key.CompareTo(key) < 0) h.Right = Put(h.Right, key, value);
            else if (h.Key.CompareTo(key) > 0) h.Left = Put(h.Left, key, value);
            else h.Value = value;

            if (IsRed(h.Right) && !IsRed(h.Left)) h = RotateLeft(h);
            if (IsRed(h.Left) && IsRed(h.Left.Left)) h = RotateRight(h);
            if (IsRed(h.Left) && IsRed(h.Right)) FlipColors(h);

            return h;
        }

        private bool IsRed(Node<TKey, TValue> x)
        {
            if (x == null)
                return false;
            return x.IsRed;
        }

        private Node<TKey, TValue> RotateLeft(Node<TKey, TValue> h)
        {
            // assert isRed(h.right);
            var x = h.Right;
            h.Right = x.Left;
            x.Left = h;
            x.IsRed = h.IsRed;
            h.IsRed = true;
            return x;
        }

        private Node<TKey, TValue> RotateRight(Node<TKey, TValue> h)
        {
            // assert isRed(h.left);
            var x = h.Left;
            h.Left = x.Right;
            x.Right = h;
            x.IsRed = h.IsRed;
            h.IsRed = true;
            return x;
        }

        private void FlipColors(Node<TKey, TValue> h)
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
