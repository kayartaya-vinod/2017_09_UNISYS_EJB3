package training.dao;

import javax.ejb.Remote;

@Remote
public interface JpaTester {
	public void testJpaFeature();
}
