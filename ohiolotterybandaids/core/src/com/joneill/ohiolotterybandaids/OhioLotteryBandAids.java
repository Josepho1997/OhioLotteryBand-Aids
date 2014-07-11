package com.joneill.ohiolotterybandaids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class OhioLotteryBandAids {
	public static final float MIN_MOVE_SPEED = 3;
	public static final float MAX_MOVE_SPEED = 10;
	public static final float MIN_SCALE = 0.5f;
	public static final float MAX_SCALE = 3;
	public static float HEIGHT_ADJUSTMENT_SUB = (256 - 24) / 2;

	public Vector2 position;
	public Vector3 rgb;
	public Vector2 velocity;
	public Rectangle bounds;
	public float scale;
	public Texture ohiolotterybandaids;

	ShapeRenderer debugRender;

	public OhioLotteryBandAids(Vector2 position, Vector3 rgb) {
		this.position = position;
		this.rgb = rgb;
		velocity = new Vector2(
				MathUtils.random(MIN_MOVE_SPEED, MAX_MOVE_SPEED),
				MathUtils.random(MIN_MOVE_SPEED, MAX_MOVE_SPEED));
		scale = MathUtils.random(MIN_SCALE, MAX_SCALE);
		ohiolotterybandaids = new Texture("ohiolotterybandaid.png");
		bounds = new Rectangle(position.x, position.y - HEIGHT_ADJUSTMENT_SUB
				/ scale, ohiolotterybandaids.getWidth() / scale,
				(ohiolotterybandaids.getHeight() / scale)
						- (HEIGHT_ADJUSTMENT_SUB * 2 / scale));
		// Correct position if it is out of bounds
		if (position.x + bounds.getWidth() > Gdx.graphics.getWidth()) {
			position.x -= bounds.getWidth();
		}
		if (position.y + bounds.getHeight() > Gdx.graphics.getHeight()) {
			position.y -= bounds.getWidth();
		}
		debugRender = new ShapeRenderer();
	}

	public void update(float deltaTime) {
		position.x += velocity.x;
		position.y += velocity.y;
		bounds.setPosition(position.x, position.y + HEIGHT_ADJUSTMENT_SUB
				/ scale);
		if (bounds.x + bounds.getWidth() >= Gdx.graphics.getWidth()
				|| position.x <= 0) {
			velocity.x = -velocity.x;
		}
		if (bounds.y + bounds.getHeight() >= Gdx.graphics.getHeight()
				|| bounds.y <= 0) {
			velocity.y = -velocity.y;
		}
	}

	public void render(SpriteBatch batch) {
		batch.setColor(rgb.x, rgb.y, rgb.z, 1);
		batch.draw(ohiolotterybandaids, position.x, position.y,
				ohiolotterybandaids.getWidth() / scale,
				ohiolotterybandaids.getHeight() / scale);
		batch.setColor(1, 1, 1, 1);
	}

	public void debug() {
		debugRender.begin(ShapeType.Line);
		debugRender.rect(bounds.x, bounds.y, bounds.getWidth(),
				bounds.getHeight());
		debugRender.end();
	}
}
