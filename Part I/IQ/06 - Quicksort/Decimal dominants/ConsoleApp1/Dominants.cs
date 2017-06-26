using System.Collections.Generic;
using System.Linq;

namespace ConsoleApp1
{
    public class Dominants
    {
        int _size;
        int _kth;
        int _grade;
        Dictionary<int, int> _counters;

        public Dominants(int size, int kth)
        {
            _size = size;
            _kth = kth;
            _counters = new Dictionary<int, int>();
        }

        public void Push(int key)
        {
            if (_counters.ContainsKey(key))
                _counters[key]++;
            else
            {
                // The trick is that the dominant element remains same 
                // if you delete any k distinct items from the array.
                if (_counters.Count < _kth)
                    _counters.Add(key, 1);
                else
                {
                    foreach (var i in _counters.Keys.ToArray())
                    {
                        if (_counters[i] > 1)
                            _counters[i]--;
                        else
                            _counters.Remove(i);
                    }
                    _grade++;
                }
            }
        }

        public int[] ToArray()
        {
            var cnt = _size / _kth - (_grade + 1);
            return _counters
                .Where(i => i.Value > cnt)
                .Select(i => i.Key)
                .ToArray();
        }
    }
}
