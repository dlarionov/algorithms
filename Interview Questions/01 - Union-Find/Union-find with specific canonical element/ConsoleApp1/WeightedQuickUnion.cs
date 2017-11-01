using System;

namespace ConsoleApp1
{
    public class WeightedQuickUnion
    {
        int[] _arr;
        int[] _sz;
        int[] _max;

        public WeightedQuickUnion(int n)
        {
            if (n < 2)
                throw new ArgumentException();

            _arr = new int[n];
            _max = new int[n];
            _sz = new int[n];
            for (int i = 0; i < n; i++)
            {
                _arr[i] = i;
                _max[i] = i;
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
                _max[j] = Math.Max(_max[i], _max[j]);
            }
            else
            {
                _arr[j] = i;
                _sz[i] += _sz[j];
                _max[i] = Math.Max(_max[i], _max[j]);
            }
        }

        public int FindMax(int i)
        {
            int j = _max[i];
            while (_arr[i] != i)
            {
                i = _arr[i];
                if (_max[i] > j)
                    j = _max[i];
            }
            return j;
        }
    }
}
