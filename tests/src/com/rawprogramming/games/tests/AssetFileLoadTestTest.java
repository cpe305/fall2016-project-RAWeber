package com.rawprogramming.games.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class AssetFileLoadTestTest {

	@Test
	public void badlogicLogoFileExists() {
		assertTrue(Gdx.files.internal("../android/assets/BasicTower.png").exists());
	}
}
