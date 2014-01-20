package projet.model.material;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projet.model.material.classic.operatingsystempowered.OperatingSystemPowered;
import projet.model.material.classic.operatingsystempowered.Tablette;
import projet.model.os.OS;

public class TestOperatingSystemPowered {
	OperatingSystemPowered osp;
	final OS COS = OS.Android;

	@Before
	public void setUp() throws Exception {
		osp = new Tablette(COS);
	}

	@Test
	public void testOperatingSystemPowered() {
		assertEquals(true, osp instanceof OperatingSystemPowered);
	}

	@Test
	public void testGetOs() {
		assertEquals(COS, osp.getOs());
	}

}
