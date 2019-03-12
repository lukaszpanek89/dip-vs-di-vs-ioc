package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.transaction;

import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TransactionManager {

	private int transactionDepth = 0;

	private boolean shouldCommit = true;

	public void beforeInvocation() {
		++transactionDepth;
		if (transactionDepth == 1) {
			beginTransaction();
		} else {
			continuePreviouslyBegunTransaction();
		}
	}

	public void afterSuccessfulInvocation() {
		--transactionDepth;
		if (transactionDepth == 0) {
			if (shouldCommit) {
				commitTransaction();
			} else {
				rollbackTransaction();
			}
		}
	}

	public void afterFailedInvocation() {
		shouldCommit = false;
		--transactionDepth;
		if (transactionDepth == 0) {
			rollbackTransaction();
		}
	}

	private void beginTransaction() {
		printServiceMessage(this, "Begins transaction");
	}

	private void continuePreviouslyBegunTransaction() {
		printServiceMessage(this, "Continues previously begun transaction");
	}

	private void commitTransaction() {
		printServiceMessage(this, "Commits transaction");
	}

	private void rollbackTransaction() {
		printServiceMessage(this, "Rollbacks transaction");
	}

}
