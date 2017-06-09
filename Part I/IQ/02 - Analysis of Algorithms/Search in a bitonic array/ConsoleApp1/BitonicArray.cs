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

        /// <summary>
        /// ~ n
        /// </summary>
        /// <returns>array index</returns>
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
        /// ~ 3*log(n)
        /// </summary>
        /// <returns>array index</returns>
        public int FindLogarithmicBad(int value)
        {
            int maximum = FindMax(); // maximim could be cached
            return DownSearch(0, _bitonic.Length, maximum, value);
        }

        /// <summary>
        /// Exactly log(n) on average and worst cases
        /// </summary>
        /// <returns>array index</returns>
        public int FindMax()
        {
            // lo is the left index of the array minus one
            // This trick makes it easier to work with borders
            int lo = -1;
            int hi = _bitonic.Length - 1;
            int mid;
            while (hi - lo > 1)
            {
                mid = lo + ((hi - lo) / 2);
                if (_bitonic[mid] < _bitonic[mid + 1])
                    lo = mid;
                else
                    hi = mid;
            }
            return hi;
        }

        /// <summary>
        /// ~ 2 log (n)
        /// Can be used when A[mid] >= value or mid = maximum
        /// </summary>
        /// <returns>array index</returns>
        public int DownSearch(int index, int count, int mid, int value)
        {
            if (mid < index || mid > index + count)
                throw new ArgumentException();

            int result = BinarySearch(index, mid - index, value);
            if (result < 0)
                result = BinarySearch(mid, index + count - mid, value, false);
            return result;
        }


        /// <summary>
        /// Exactly log(n) on average and worst cases.
        /// Note: System.Array.BinarySerch uses 2*log(n) in the worst case.
        /// </summary>
        /// <returns>array index</returns>
        public int BinarySearch(int index, int count, int value, bool asc = true)
        {
            if (index < 0 || count < 0)
                throw new ArgumentOutOfRangeException();
            if (_bitonic.Length < index + count)
                throw new ArgumentException();

            if (count == 0)
                return -1;

            // "lo minus one" trick
            int lo = index - 1;
            int hi = index + count - 1;
            int mid;
            while (hi - lo > 1)
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
        /// ~ 2*log(n)
        /// </summary>
        /// <returns>array index</returns>
        public int FindLogarithmicGood(int value)
        {
            // no "lo minus one" trick, 
            // we should check A[lo] and A[hi] at the end
            int lo = 0;
            int hi = _bitonic.Length - 1;
            int mid;
            while (hi - lo > 1)
            {
                mid = lo + ((hi - lo) / 2);
                if (value < _bitonic[mid])
                {
                    return DownSearch(lo, hi - lo + 1, mid, value);
                }
                else
                {
                    if (_bitonic[mid] < _bitonic[mid + 1])
                        lo = mid;
                    else
                        hi = mid;
                }
            }

            return _bitonic[lo] == value 
                ? lo 
                : _bitonic[hi] == value 
                    ? hi 
                    : -1;
        }
    }
}
