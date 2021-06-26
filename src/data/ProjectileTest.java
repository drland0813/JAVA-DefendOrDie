package data;

import java.security.KeyStore.PrivateKeyEntry;
import static helpers.Clock.*;


public class ProjectileTest  extends Projectile{

	public ProjectileTest(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	private int timeToDame = 5;
	private float test = 100;
	
	@Override
	public void damage()
	{
		super.setAlive(false);
	}

	@Override
	public void Sound() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * private void test() { for (int i = 0; i < 5; i++) { damage(); } }
	 */
}
