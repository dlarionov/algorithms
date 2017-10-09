using System;

namespace UsefulThings
{
    public class MinPQ
    {
        int[] _pq;
        int _size;

        public MinPQ()
        {
            _pq = new int[4];
        }

        public int Count
        {
            get { return _size; }
        }

        private void Swap(int i, int j)
        {
            if (i == j)
                return;

            var x = _pq[i];
            _pq[i] = _pq[j];
            _pq[j] = x;
        }

        private void Resize(int capacity)
        {
            var copy = new int[capacity];
            // indices start at 1
            for (int i = 1; i <= _size; i++)
            {
                copy[i] = _pq[i];
            }
            _pq = copy;
        }

        private void Up(int k)
        {
            while (k > 1 && _pq[k] < _pq[k / 2])
            {
                Swap(k, k / 2);
                k = k / 2;
            }
        }

        private void Down(int k)
        {
            while (2 * k <= _size)
            {
                int j = 2 * k;
                if (j < _size && _pq[j] > _pq[j + 1])
                    j++;
                if (_pq[k] < _pq[j])
                    break;
                Swap(k, j);
                k = j;
            }
        }

        public void Push(int x)
        {
            if (_size == _pq.Length - 1)
            {
                Resize(2 * _pq.Length);
            }

            // indices start at 1
            _pq[++_size] = x;
            Up(_size);
        }

        public int Pop()
        {
            if (_size < 1)
                throw new IndexOutOfRangeException();

            if (_pq.Length == 4 * _size)
            {
                Resize(_pq.Length / 2);
            }

            var x = _pq[1];
            Swap(1, _size--);
            Down(1);
            return x;
        }

        public int Peek()
        {
            if (_size < 1)
                throw new IndexOutOfRangeException();
            return _pq[1];
        }
    }
}
