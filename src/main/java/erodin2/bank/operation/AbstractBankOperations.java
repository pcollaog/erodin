package erodin2.bank.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pcollaog
 * 
 */
public abstract class AbstractBankOperations {

	private static Logger logger = LoggerFactory
			.getLogger(AbstractBankOperations.class);

	public void locateMovements(String months) {
		locateMovementsInternal(months);
	}

	protected abstract void locateMovementsInternal(String months);

}
