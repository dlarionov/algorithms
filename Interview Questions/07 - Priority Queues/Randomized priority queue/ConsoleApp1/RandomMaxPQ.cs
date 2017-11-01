using System;

namespace ConsoleApp1
{
    public class RandomMaxPQ<T> where T : IComparable<T>
    {
        Random _rnd;
        T[] _pq;
        int _size;

        public RandomMaxPQ(Random rnd)
        {
            _pq = new T[4];
            _rnd = rnd;
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
            T[] copy = new T[capacity];
            // indices start at 1
            for (int i = 1; i <= _size; i++)
            {
                copy[i] = _pq[i];
            }
            _pq = copy;
        }

        private void Up(int k)
        {
            while (k > 1 && _pq[k].CompareTo(_pq[k / 2]) > 0)
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
                if (j < _size && _pq[j].CompareTo(_pq[j + 1]) < 0)
                    j++;
                if (_pq[k].CompareTo(_pq[j]) > 0)
                    break;
                Swap(k, j);
                k = j;
            }
        }

        public void Push(T x)
        {
            if (_size == _pq.Length - 1)
            {
                Resize(2 * _pq.Length);
            }

            // indices start at 1
            _pq[++_size] = x;
            Up(_size);
        }

        public T DeleteMax()
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
            _pq[_size + 1] = default(T);
            return x;
        }

        public T GetMax()
        {
            if (_size < 1)
                throw new IndexOutOfRangeException();
            return _pq[1];
        }

        public T DeleteRandom()
        {
            if (_size < 1)
                throw new IndexOutOfRangeException();

            if (_pq.Length == 4 * _size)
            {
                Resize(_pq.Length / 2);
            }

            int i = _rnd.Next(1, _size + 1);
            var x = _pq[i];
            Swap(i, _size--);
            Down(i);
            _pq[_size + 1] = default(T);
            return x;
        }

        public T GetRandom()
        {
            return _pq[_rnd.Next(1, _size + 1)];
        }
    }
}
