package com.joneill.ohiolotterybandaids;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class OhioLotteryBandAidsGame extends ApplicationAdapter {
	private static boolean DEBUG = false;
	private static final int OHIO_LOTTERY_BAND_AIDS_COUNT = 100;
	public SpriteBatch batch;
	public Random ran;
	private ArrayList<OhioLotteryBandAids> ohioLotteryBandAids;

	@Override
	public void create() {
		batch = new SpriteBatch();
		ran = new Random();
		ohioLotteryBandAids = new ArrayList<OhioLotteryBandAids>(OHIO_LOTTERY_BAND_AIDS_COUNT);
		for (int i = 0; i < OHIO_LOTTERY_BAND_AIDS_COUNT; i++) {
			OhioLotteryBandAids ohioLotteryBandAidObject = null;
			ohioLotteryBandAidObject = new OhioLotteryBandAids(new Vector2(
					ran.nextFloat() * Gdx.graphics.getWidth(), ran.nextFloat()
							* Gdx.graphics.getHeight()), new Vector3(
					ran.nextFloat(), ran.nextFloat(), ran.nextFloat()));
			ohioLotteryBandAids.add(ohioLotteryBandAidObject);
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(ran.nextFloat(), ran.nextFloat(), ran.nextFloat(),
				1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (OhioLotteryBandAids ohioLotteryBandAidsObject : ohioLotteryBandAids) {
			update(ohioLotteryBandAidsObject);
			ohioLotteryBandAidsObject.render(batch);
			if (DEBUG)
				debug(ohioLotteryBandAidsObject);
		}
		batch.end();
	}

	private void update(OhioLotteryBandAids ohioLotteryBandAids) {
		ohioLotteryBandAids.update(Gdx.graphics.getDeltaTime());
	}

	private void debug(OhioLotteryBandAids ohioLotteryBandAids) {
		ohioLotteryBandAids.debug();
	}
}
