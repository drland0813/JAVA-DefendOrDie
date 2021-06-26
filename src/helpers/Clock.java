package helpers;
import org.lwjgl.Sys;
public class Clock {

	public static boolean pause = false;
	public static long LastFrame, totalTime;
	public static float d = 0, multiplier =1;
	
	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	public static float getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - LastFrame);
		LastFrame = getTime();
		//System.out.println(delta * 0.01f);
		if (delta * 0.001f > 0.05f) {
			return 0.05f;
		}
		return delta * 0.001f;
	}
	public static float Delta() {
		if(pause) 
			return 0;
		else 
			return d * multiplier;
	}
	
	public static float TotalTime() {
		return totalTime;
	}
	public static float Multiplier() {
		return multiplier;
	}
	public static void Update() {
		d  = getDelta();
		totalTime += d;
	}
	public static void ChangeMultiplier(Float change) {
		if (multiplier + change < -1 && multiplier + change > 7)  {
			
		}else {
			multiplier += change;
		}
	}
	public static void Pause() {
		if(pause)
		{
			pause = false;
		}
		else {
			pause = true;
		}
	}
	public static boolean getPause()
	{
		return pause;
	}
	public static void  setPause(boolean a)
	{
		 pause = a;
	}
}
