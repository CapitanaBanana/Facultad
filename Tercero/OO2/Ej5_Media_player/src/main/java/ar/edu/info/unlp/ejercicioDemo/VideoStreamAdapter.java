package ar.edu.info.unlp.ejercicioDemo;

public class VideoStreamAdapter implements Media {
  private VideoStream adaptee;

  public VideoStreamAdapter(VideoStream adaptee){
    this.adaptee = adaptee;
  }
  public void Play(){
    adaptee.Reproduce();
  }
}
