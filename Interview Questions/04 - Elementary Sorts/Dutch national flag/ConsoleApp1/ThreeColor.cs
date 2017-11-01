namespace ConsoleApp1
{
    public class ThreeColor
    {
        private int[] _arr;
        private int _swap;
        private int _color;

        public ThreeColor(int[] arr)
        {
            _arr = arr;

            int i = 0;
            int j = 0;
            int k = _arr.Length - 1;

            while (j <= k)
            {
                var r = Color(_arr[j]);
                if (r < 0)
                {
                    Swap(i, j);
                    i++;
                    j++;
                }
                else if (r > 0)
                {
                    Swap(j, k);
                    k--;
                }
                else
                {
                    j++;
                }
            }
        }

        public bool Test()
        {
            for (int i = 1; i < _arr.Length; i++)
            {
                if (_arr[i] < _arr[i - 1])
                    return false;
            }

            return _arr.Length >= _swap && _arr.Length >= _color;
        }

        private void Swap(int i, int j)
        {
            _swap++;
            int swap = _arr[i];
            _arr[i] = _arr[j];
            _arr[j] = swap;
        }

        private int Color(int i)
        {
            _color++;
            return i;
        }
    }
}
