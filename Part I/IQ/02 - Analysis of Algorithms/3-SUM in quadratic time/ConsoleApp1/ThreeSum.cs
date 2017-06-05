using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApp1
{
    public class ThreeSum
    {
        public ThreeSum(int[] arr)
        {
            int[] copy = new int[arr.Length];
            Array.Copy(arr, copy, arr.Length);
            Array.Sort(copy);

            for (int i = 0; i < copy.Length; i++)
            {
                int x = copy[i];
                int lo = 0;
                int hi = copy.Length - 1;

                while (lo < hi)
                {
                    int y = copy[lo];
                    int z = copy[hi];
                    int sum = y + z;

                    if (hi == i || sum > x)
                        hi--;
                    else if (lo == i || sum < x)
                        lo++;
                    else
                    {
                        // TODO
                        break;
                    }
                }
            }
        }
    }
}
