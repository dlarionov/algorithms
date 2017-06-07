using System;

namespace ConsoleApp1
{
    public class BitonicArray
    {
        readonly int[] _bitonic;

        public BitonicArray(int[] bitonic)
        {
            _bitonic = bitonic ?? throw new ArgumentNullException();
        }

        public int[] ToArray()
        {
            return _bitonic;
        }

        public int FindLinear(int value)
        {
            for (int i = 0; i < _bitonic.Length; i++)
            {
                if (_bitonic[i] == value)
                    return i;
            }
            return -1;
        }

        /// <summary>
        /// 3*log(n)
        /// </summary>
        public int FindLogarithmicBad(int value)
        {
            int maximum = FindMax();
            int result = BinarySearch(0, maximum, value);
            return result < 0
                ? BinarySearch(maximum, _bitonic.Length - maximum, value, false)
                : result;
        }

        private int FindMax()
        {
            // lo is the left index of the array minus one
            // we never call arr[-1] so there is nothing wrong
            int lo = -1;
            int hi = _bitonic.Length - 1;
            int mid;
            while (hi - lo > 1) // (lo, hi]
            {
                mid = lo + ((hi - lo) / 2);
                if (_bitonic[mid] < _bitonic[mid + 1])
                    lo = mid;
                else
                    hi = mid;
            }
            return hi;
        }

        private int BinarySearch(int index, int count, int value, bool asc = true)
        {
            if (index < 0 || count < 0)
                throw new ArgumentOutOfRangeException();
            if (_bitonic.Length < index + count)
                throw new ArgumentException();

            if (count == 0)
                return -1;

            int lo = index - 1;
            int hi = index + count - 1;
            int mid;
            while (hi - lo > 1) // (lo, hi]
            {
                mid = lo + ((hi - lo) / 2);
                if ((asc && _bitonic[mid] < value) || (!asc && _bitonic[mid] > value))
                    lo = mid;
                else
                    hi = mid;
            }

            return _bitonic[hi] == value ? hi : -1;
        }

        /// <summary>
        /// 2*log(n)
        /// </summary>
        public int FindLogarithmicGood(int value)
        {
            // TODO

            return -1;
        }
    }
}
