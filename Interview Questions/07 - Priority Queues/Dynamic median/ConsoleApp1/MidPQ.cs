using System;

namespace ConsoleApp1
{
    public class MidPQ<T> where T : IComparable<T>
    {
        MinPQ<T> _min;
        MaxPQ<T> _max;

        public MidPQ()
        {
            _min = new MinPQ<T>();
            _max = new MaxPQ<T>();
        }

        public int Count
        {
            get { return _max.Count + _min.Count; }
        }

        public void Push(T x)
        {
            _min.Push(x);

            if (_min.Count > _max.Count)
                _max.Push(_min.Pop());
        }

        public T Pop()
        {
            if (Count < 1)
                throw new IndexOutOfRangeException();

            return _max.Count > _min.Count
                ? _max.Pop()
                : _min.Pop();
        }

        public T Peek()
        {
            if (Count < 1)
                throw new IndexOutOfRangeException();

            return _max.Count > _min.Count
                ? _max.Peek()
                : _min.Peek();
        }
    }
}
