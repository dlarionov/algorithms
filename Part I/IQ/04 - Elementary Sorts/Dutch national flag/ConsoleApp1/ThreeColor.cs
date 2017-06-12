using System;

namespace ConsoleApp1
{
    public class ThreeColor
    {
        private int[] _arr;
        private int _swap;
        private int _color;

        public ThreeColor(int[] arr)
        {
            int n = arr.Length;
            if (n < 3)
                throw new ArgumentOutOfRangeException();

            _arr = new int[n];
            for (int i = 0; i < n; i++)
            {
                _arr[i] = arr[i];
            }

            int index = 0;
            int x0 = 0;
            int y0 = 1;
            int z0 = 2;
            int x1 = 0;
            int y1 = 1;
            int z1 = 2;

            while (index < n)
            {
                int row = index % 3;
                switch (row)
                {
                    case 0: if (x0 > index) { index++; continue; } break;
                    case 1: if (y0 > index) { index++; continue; } break;
                    case 2: if (z0 > index) { index++; continue; } break;
                }

                var clr = Color(_arr[index]);
                switch (clr)
                {
                    case 0:
                        if (index == x0)
                        {
                            index++;
                            x0 = x0 + 3;
                        }
                        else
                        {
                            if (x0 >= n)
                            {
                                if (row == 1)
                                {
                                    Swap(index, y1);
                                    y1 = y1 + 3;
                                    if (y1 > y0)
                                        y0 = y1;
                                }
                                else if (row == 2)
                                {
                                    Swap(index, z1);
                                    z1 = z1 + 3;
                                    if (z1 > z0)
                                        z0 = z1;
                                }
                            }
                            else
                            {
                                Swap(index, x0);
                                x0 = x0 + 3;
                            }
                        }
                        break;
                    case 1:
                        if (index == y0)
                        {
                            index++;
                            y0 = y0 + 3;
                        }
                        else
                        {
                            if (y0 >= n)
                            {
                                if (row == 2)
                                {
                                    Swap(index, z1);
                                    z1 = z1 + 3;
                                    if (z1 > z0)
                                        z0 = z1;
                                }
                                else if (row == 0)
                                {
                                    Swap(index, x1);
                                    x1 = x1 + 3;
                                    if (x1 > x0)
                                        x0 = x1;
                                }
                            }
                            else
                            {
                                Swap(index, y0);
                                y0 = y0 + 3;
                            }
                        }
                        break;
                    case 2:
                        if (index == z0)
                        {
                            index++;
                            z0 = z0 + 3;
                        }
                        else
                        {
                            if (z0 >= n)
                            {
                                if (row == 0)
                                {
                                    Swap(index, x1);
                                    x1 = x1 + 3;
                                    if (x1 > x0)
                                        x0 = x1;
                                }
                                else if (row == 1)
                                {
                                    Swap(index, y1);
                                    y1 = y1 + 3;
                                    if (y1 > y0)
                                        y0 = y1;
                                }
                            }
                            else
                            {
                                Swap(index, z0);
                                z0 = z0 + 3;
                            }
                        }
                        break;
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
