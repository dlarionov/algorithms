namespace UsefulThings
{
    public static class HeapSort
    {
        public static void Sort(int[] pq)
        {
            int n = pq.Length;
            for (int k = n / 2; k >= 1; k--)
                Sink(pq, k, n);
            while (n > 1)
            {
                Exch(pq, 1, n--);
                Sink(pq, 1, n);
            }
        }

        /// <summary>
        /// Restores the heap invariant.
        /// </summary>
        private static void Sink(int[] pq, int k, int n)
        {
            while (2 * k <= n)
            {
                int j = 2 * k;
                if (j < n && Less(pq, j, j + 1))
                    j++;
                if (!Less(pq, k, j))
                    break;
                Exch(pq, k, j);
                k = j;
            }
        }

        // Indices are "off-by-one" to support 1-based indexing.

        private static bool Less(int[] pq, int i, int j)
        {
            return pq[i - 1] < pq[j - 1];
        }

        private static void Exch(int[] pq, int i, int j)
        {
            var swap = pq[i - 1];
            pq[i - 1] = pq[j - 1];
            pq[j - 1] = swap;
        }
    }
}
