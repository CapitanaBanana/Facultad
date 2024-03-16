package ar.edu.unlp.info.oo1.ej10_jobScheduler;

public class FIFO extends JobDescription{
  public JobDescription next(){
    JobDescription nextJob = null;
    nextJob = jobs.get(0);
    this.unschedule(nextJob);
    return nextJob;
  }
}
