using System;

namespace ConsoleApp1
{
    public class QuickUnion
    {
        int[] _arr;

        public QuickUnion(int n)
        {
            if (n < 2)
                throw new ArgumentException();

            // array with one additional element
            _arr = new int[n + 1];
            for (int i = 0; i <= n; i++)
            {
                _arr[i] = i;
            }
        }

        public void Remove(int x)
        {
            if (x < 0 || x > _arr.Length - 2)
                throw new ArgumentException();

            Union(x, x + 1);
        }

        public int Successor(int x)
        {
            int root = Root(x);
            return root > _arr.Length - 2 ? -1 : root;
        }

        private int Root(int i)
        {
            int j = i;
            while (_arr[j] != j)
            {
                j = _arr[j];
            }

            // path compression
            while (_arr[i] != i)
            {
                i = _arr[i];
                _arr[i] = j;
            }

            return j;
        }

        public bool Connected(int a, int b)
        {
            int i = Root(a);
            int j = Root(b);

            return i == j;
        }

        public void Union(int a, int b)
        {
            int i = Root(a);
            int j = Root(b);

            if (i == j)
                return;

            // parent always greather then child
            _arr[i] = j;
        }
    }
}
