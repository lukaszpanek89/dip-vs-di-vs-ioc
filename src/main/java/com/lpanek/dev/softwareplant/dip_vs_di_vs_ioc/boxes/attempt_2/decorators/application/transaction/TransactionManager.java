package com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.boxes.attempt_2.decorators.application.transaction;

import static com.lpanek.dev.softwareplant.dip_vs_di_vs_ioc.util.Util.printServiceMessage;

public class TransactionManager {

	private int transactionDepth = 0;

	private boolean shouldCommit = true;

	public void beginTransaction() {
		++transactionDepth;
		if (transactionDepth == 1) {
			doBeginTransaction();
		} else {
			continuePreviouslyBegunTransaction();
		}
	}

	public void commitTransaction() {
		--transactionDepth;
		if (transactionDepth == 0) {
			if (shouldCommit) {
				doCommitTransaction();
			} else {
				doRollbackTransaction();
			}
		}
	}

	public void rollbackTransaction() {
		shouldCommit = false;
		--transactionDepth;
		if (transactionDepth == 0) {
			doRollbackTransaction();
		}
	}

	private void doBeginTransaction() {
		printServiceMessage(this, "Begins transaction");
	}

	private void continuePreviouslyBegunTransaction() {
		printServiceMessage(this, "Continues previously begun transaction");
	}

	private void doCommitTransaction() {
		printServiceMessage(this, "Commits transaction");
	}

	private void doRollbackTransaction() {
		printServiceMessage(this, "Rollbacks transaction");
	}

}
