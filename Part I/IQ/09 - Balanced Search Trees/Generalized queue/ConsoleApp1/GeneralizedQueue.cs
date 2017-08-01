using System;

namespace ConsoleApp1
{
    public class GeneralizedQueue<T>
    {
        Tree<int, T> _tree;
        int _index;

        public GeneralizedQueue()
        {
            _tree = new Tree<int, T>();
        }

        public void AddLast(T item)
        {
            _tree.Add(_index++, item);
        }

        public void RemoveFirst()
        {
            if (_index < 1)
                throw new IndexOutOfRangeException();

            _tree.DeleteMin();
        }

        public T Get(int ith)
        {
            return _tree.Get(ith);
        }

        public void Remove(int ith)
        {
            _tree.Delete(ith);
        }
    }
}
