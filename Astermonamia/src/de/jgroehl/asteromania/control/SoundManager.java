package de.jgroehl.asteromania.control;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager
{

	private final SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 100);
	private static final int NUMBER_OF_SOUNDS = 10;
	private static final double NUMBER_OF_HIT_SOUNDS = 3;
	private static final int CLICK_ID = 0;
	private static final int SHOT_ID = 1;
	private static final int COIN_ID = 2;
	private static final int HIT1_ID = 3;
	private static final int HIT2_ID = 4;
	private static final int EXPLOSION_ID = 5;
	private static final int PAYING_ID = 6;
	private static final int HEALTH_POWER_UP_ID = 7;
	private static final int SHIELD_WARD_ID = 8;
	private static final int CHECKPOINT_ID = 9;
	private final int[] sounds;

	public SoundManager(Context context)
	{
		this.sounds = new int[NUMBER_OF_SOUNDS];

		sounds[CLICK_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.click, 100);
		sounds[SHOT_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.shoot, 100);
		sounds[COIN_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.coin, 100);
		sounds[HIT1_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.hit, 100);
		sounds[HIT2_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.hit3, 100);
		sounds[EXPLOSION_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.boom, 100);
		sounds[PAYING_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.paying, 100);
		sounds[HEALTH_POWER_UP_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.power_up, 100);
		sounds[SHIELD_WARD_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.shield_ward, 100);
		sounds[CHECKPOINT_ID] = soundPool.load(context, de.jgroehl.asteromania.R.raw.checkpoint, 100);
	}

	public void playClickSound()
	{
		playSound(CLICK_ID);
	}

	private void playSound(int soundNumber)
	{
		playSound(soundNumber, 1.0f);
	}

	private void playSound(int soundNumber, float rate, float volume)
	{
		if (soundNumber < 0 || soundNumber > sounds.length - 1)
			throw new IllegalArgumentException("No such sound in library.");

		soundPool.play(sounds[soundNumber], volume, volume, 1, 0, rate);
	}

	private void playSound(int soundNumber, float rate)
	{
		playSound(soundNumber, rate, 1.0f);
	}

	public void playNormalShotSound()
	{
		playSound(SHOT_ID, 1.6f);
	}

	public void playEnemyShotSound()
	{
		playSound(SHOT_ID, 0.5f);
	}

	public void playCoinSound()
	{
		playSound(COIN_ID, 1.0f, 0.75f);
	}

	public void playPlayerHitSound()
	{
		playHitSound(0.8f);
	}

	public void playEnemyHitSound()
	{
		playHitSound(1.2f);
	}

	private void playHitSound(float rate)
	{
		int value = (int) (Math.random() * NUMBER_OF_HIT_SOUNDS);
		switch (value)
		{
			case 1:
			default:
				playSound(HIT1_ID, rate, 0.1f);
				break;
			case 2:
				playSound(HIT2_ID, rate, 0.1f);
				break;
		}
	}

	public void playExplosionSound()
	{
		playSound(EXPLOSION_ID, 0.8f, 0.15f);
	}

	public void playPayingSound()
	{
		playSound(PAYING_ID);
	}

	public void playHealthPowerUpSound()
	{
		playSound(HEALTH_POWER_UP_ID, 1.0f, 0.75f);
	}

	public void playShieldHitSound()
	{
		playSound(SHIELD_WARD_ID);
	}

	public void playCheckpointSound()
	{
		playSound(CHECKPOINT_ID);
	}

}
