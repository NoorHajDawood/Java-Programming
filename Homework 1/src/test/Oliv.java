package test;

public class Oliv
{
    int luckyNumber;
    Oliv inner;
    public Oliv()
    {
        luckyNumber = 14;
    }
    public Oliv(Oliv oliv)
    {
        inner = oliv;
    }
    public Oliv(int num)
    {
        luckyNumber = num;
    }
    public void setLucky(int num)
    {
        luckyNumber = 6;
    }
    public void setInner()
    {
        inner = new Oliv()
        {
            public void setLucky(int num)
            {
                luckyNumber = num;
            }
        };
    }
    public static void main(String args[])
    {
        Oliv oliv = new Oliv(new Oliv(new Oliv(34)));
        oliv.inner.inner.setInner();
        oliv.inner.inner.inner.setLucky(12);
        System.out.println(oliv.inner.luckyNumber);
    }
}