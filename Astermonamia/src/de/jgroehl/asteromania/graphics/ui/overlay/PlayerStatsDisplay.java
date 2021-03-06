package de.jgroehl.asteromania.graphics.ui.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import de.jgroehl.api.control.BaseGameHandler;
import de.jgroehl.api.graphics.GameObject;
import de.jgroehl.api.graphics.animated.AnimatedGraphicsObject;
import de.jgroehl.api.graphics.animated.SimpleAnimatedObject;
import de.jgroehl.asteromania.R;
import de.jgroehl.asteromania.control.PlayerInfo;
import de.jgroehl.asteromania.control.callbacks.ShotFiredCallback;
import de.jgroehl.asteromania.graphics.player.SpaceShip;

public class PlayerStatsDisplay extends GameObject
{

	private final PlayerInfo playerInfo;
	private final AnimatedGraphicsObject spaceship;
	private Paint headingPaint;
	private Paint textPaint;

	public PlayerStatsDisplay(PlayerInfo playerInfo, Context context)
	{
		super(0, 0, context);
		this.playerInfo = playerInfo;

		spaceship = new SimpleAnimatedObject(0.05f, 0.3f, de.jgroehl.asteromania.R.drawable.rotating_spaceship, 0.3f,
				15, 100, context);
	}

	@Override
	public void draw(Canvas c)
	{

		if (headingPaint == null)
		{
			headingPaint = new Paint();
			headingPaint.setColor(Color.rgb(250, 200, 100));
			headingPaint.setTextSize(c.getHeight() / 10);
		}
		if (textPaint == null)
		{
			textPaint = new Paint();
			textPaint.setColor(Color.rgb(250, 250, 150));
			textPaint.setTextSize(c.getHeight() / 20);
		}

		c.drawText(getContext().getResources().getString(de.jgroehl.asteromania.R.string.spaceship_statistics),
				0.2f * c.getWidth(), 0.15f * c.getHeight(), headingPaint);
		spaceship.draw(c);
		c.drawText(getContext().getResources().getString(de.jgroehl.asteromania.R.string.lifepoints) + ": "
				+ playerInfo.getHealthPoints().getCurrentValue() + " / " + playerInfo.getHealthPoints().getMaximum(),
				0.4f * c.getWidth(), 0.3f * c.getHeight(), textPaint);
		c.drawText(getContext().getResources().getString(de.jgroehl.asteromania.R.string.bonus_damage) + ": "
				+ playerInfo.getBonusDamage(), 0.4f * c.getWidth(), 0.4f * c.getHeight(), textPaint);
		c.drawText(getContext().getResources().getString(de.jgroehl.asteromania.R.string.speed) + ": "
				+ (int) (playerInfo.getMaxSpeedFactor() * 100) + "%", 0.4f * c.getWidth(), 0.5f * c.getHeight(),
				textPaint);
		c.drawText(getContext().getResources().getString(de.jgroehl.asteromania.R.string.shot_speed) + ": "
				+ (int) (playerInfo.getShotSpeedFactor() * 100) + "%", 0.4f * c.getWidth(), 0.6f * c.getHeight(),
				textPaint);
		c.drawText(getContext().getResources().getString(de.jgroehl.asteromania.R.string.shot_frequency) + ": "
				+ (int) (playerInfo.getShotFrequencyFactor() * 100) + "%", 0.4f * c.getWidth(), 0.7f * c.getHeight(),
				textPaint);
		c.drawText(getContext().getResources().getString(R.string.dps) + ": " + calculateDps(), 0.05f * c.getWidth(),
				0.8f * c.getHeight(), textPaint);
		c.drawText(getContext().getResources().getString(R.string.accuracy) + ": " + playerInfo.getAccuracy() * 100
				+ "%", 0.05f * c.getWidth(), 0.9f * c.getHeight(), textPaint);
		c.drawText(getContext().getResources().getString(R.string.ship_value) + ": " + playerInfo.getAccumulatedWorth()
				+ " " + getContext().getResources().getString(R.string.coins), 0.05f * c.getWidth(), c.getHeight(),
				textPaint);

	}

	private int calculateDps()
	{
		double factor = 1;
		if (playerInfo.purchasedTripleShot())
		{
			factor = 3;
		}
		else if (playerInfo.purchasedDoubleShot())
		{
			factor = 2;
		}
		return (int) ((SpaceShip.BASIC_SHOT_DAMAGE + playerInfo.getBonusDamage())
				/ (ShotFiredCallback.BASIC_SHOOT_FREQUENCY / playerInfo.getShotFrequencyFactor() / 1000) * factor);
	}

	@Override
	public void update(BaseGameHandler gameHandler)
	{
		spaceship.update(gameHandler);
	}

}
