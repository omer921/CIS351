public class BoxCar extends RailroadCar
{
  private double h, l;
  public BoxCar()
  {
   h = 55;
   w = 9.4;
   l = 10;
    
  }
  
  public BoxCar(double h, double w, double l)
  {
    this.h = h;
    this.w = w;
    this.l = l;
  }
  
  public double volume()
  {
    return h*l*w ;
  }
  
  public double getH()
  {
    return h;
  }
  public double getW()
  {
    return w;
  }
  public double getL()
  {
    return l;
  }
  public void setH(double h)
  {
    this.h = h;
  }
  public void setW(double w)
  {
    this.w = w;
  }
  public void setL(double l)
  {
    this.l = l;
  }
}