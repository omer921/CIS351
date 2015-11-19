public class TankCar extends RailroadCar
{
  
  private double l, r;
  
  public TankCar(double l, double r)
  {
      this.l = l;
      this.r = r;
  }
  
  public double volume()
  {
    return (Math.PI * r * r * l);
  }
  
  public double getR()
  {
    return r;
  }
  public double getL()
  {
    return l;
  }
  public void setR(double r)
  {
    this.r = r;
  }
  public void setL(double l)
  {
    this.l = l;
  }
  
}