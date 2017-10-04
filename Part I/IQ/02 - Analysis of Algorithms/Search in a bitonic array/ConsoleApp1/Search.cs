using System;

namespace ConsoleApp1
{
    public static class Search
    {
        /// <summary>
        /// Exactly log(n) on average and worst cases.
        /// Note: System.Array.BinarySerch uses 2*log(n) in the worst case.
        /// </summary>
        /// <returns>array index</returns>
        public static int BinarySearch(int[] arr, int index, int count, int value, bool asc = true)
        {
            if (index < 0 || count < 0)
                throw new ArgumentOutOfRangeException();
            if (arr.Length < index + count)
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
                if ((asc && arr[mid] < value) || (!asc && arr[mid] > value))
                    lo = mid;
                else
                    hi = mid;
            }

            return arr[hi] == value ? hi : -1;
        }
    }
}
