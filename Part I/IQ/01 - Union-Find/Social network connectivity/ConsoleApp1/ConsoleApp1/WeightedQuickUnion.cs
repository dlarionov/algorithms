using System;

namespace ConsoleApp1
{
    public class WeightedQuickUnion
    {
        int[] _arr;
        int[] _sz;
        int _cnt;

        public WeightedQuickUnion(int n)
        {
            if (n < 2)
                throw new ArgumentException();

            _cnt = n;
            _arr = new int[n];
            _sz = new int[n];
            for (int i = 0; i < n; i++)
            {
                _arr[i] = i;
                _sz[i] = 1;
            }
        }

        private int Root(int i)
        {
            while (_arr[i] != i)
            {
                i = _arr[i];
            }
            return i;
        }

        /// <summary>
        /// Number of linked components
        /// </summary>
        /// <returns></returns>
        public int Count()
        {
            return _cnt;
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

            if (_sz[i] < _sz[j])
            {
                _arr[i] = j;
                _sz[j] += _sz[i];
            }
            else
            {
                _arr[j] = i;
                _sz[i] += _sz[j];
            }

            _cnt--;
        }
    }
}
