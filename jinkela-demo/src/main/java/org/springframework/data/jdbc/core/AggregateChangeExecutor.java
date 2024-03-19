/*
 * Copyright 2020-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.jdbc.core;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.relational.core.conversion.AggregateChange;
import org.springframework.data.relational.core.conversion.DbAction;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;

import java.util.List;

/**
 * Executes an {@link MutableAggregateChange}.
 *
 * @author Jens Schauder
 * @author Myeonghyeon Lee
 * @author Chirag Tailor
 * @since 2.0
 */
class AggregateChangeExecutor {

	private final JdbcConverter converter;
	private final DataAccessStrategy accessStrategy;

	AggregateChangeExecutor(JdbcConverter converter, DataAccessStrategy accessStrategy) {

		this.converter = converter;
		this.accessStrategy = accessStrategy;
	}

	/**
	 * Execute a save aggregate change. It returns the resulting root entities, with all changes that might apply. This
	 * might be the original instances or new instances, depending on their mutability.
	 * 
	 * @param aggregateChange the aggregate change to be executed. Must not be {@literal null}.
	 * @param <T> the type of the aggregate root.
	 * @return the aggregate roots resulting from the change, if there are any. May be empty.
	 * @since 3.0
	 */
	<T> List<T> executeSave(AggregateChange<T> aggregateChange) {

		JdbcAggregateChangeExecutionContext executionContext = new JdbcAggregateChangeExecutionContext(converter,
				accessStrategy);

		aggregateChange.forEachAction(action -> execute(action, executionContext));

		return executionContext.populateIdsIfNecessary();
	}

	/**
	 * Execute a delete aggregate change.
	 *
	 * @param aggregateChange the aggregate change to be executed. Must not be {@literal null}.
	 * @param <T> the type of the aggregate root.
	 * @since 3.0
	 */
	<T> void executeDelete(AggregateChange<T> aggregateChange) {

		JdbcAggregateChangeExecutionContext executionContext = new JdbcAggregateChangeExecutionContext(converter,
				accessStrategy);

		aggregateChange.forEachAction(action -> execute(action, executionContext));
	}

	private void execute(DbAction<?> action, JdbcAggregateChangeExecutionContext executionContext) {

		try {
			if (action instanceof DbAction.InsertRoot) {
				executionContext.executeInsertRoot((DbAction.InsertRoot<?>) action);
			} else if (action instanceof DbAction.BatchInsertRoot<?>) {
				executionContext.executeBatchInsertRoot((DbAction.BatchInsertRoot<?>) action);
			} else if (action instanceof DbAction.Insert) {
				executionContext.executeInsert((DbAction.Insert<?>) action);
			} else if (action instanceof DbAction.BatchInsert) {
				executionContext.executeBatchInsert((DbAction.BatchInsert<?>) action);
			} else if (action instanceof DbAction.UpdateRoot) {
				executionContext.executeUpdateRoot((DbAction.UpdateRoot<?>) action);
			} else if (action instanceof DbAction.Delete) {
				executionContext.executeDelete((DbAction.Delete<?>) action);
			} else if (action instanceof DbAction.BatchDelete<?>) {
				executionContext.executeBatchDelete((DbAction.BatchDelete<?>) action);
			} else if (action instanceof DbAction.DeleteAll) {
				executionContext.executeDeleteAll((DbAction.DeleteAll<?>) action);
			} else if (action instanceof DbAction.DeleteRoot) {
				executionContext.executeDeleteRoot((DbAction.DeleteRoot<?>) action);
			} else if (action instanceof DbAction.BatchDeleteRoot) {
				executionContext.executeBatchDeleteRoot((DbAction.BatchDeleteRoot<?>) action);
			} else if (action instanceof DbAction.DeleteAllRoot) {
				executionContext.executeDeleteAllRoot((DbAction.DeleteAllRoot<?>) action);
			} else if (action instanceof DbAction.AcquireLockRoot) {
				executionContext.executeAcquireLock((DbAction.AcquireLockRoot<?>) action);
			} else if (action instanceof DbAction.AcquireLockAllRoot) {
				executionContext.executeAcquireLockAllRoot((DbAction.AcquireLockAllRoot<?>) action);
			} else {
				throw new RuntimeException("unexpected action");
			}
		} catch (Exception e) {

			if (e instanceof OptimisticLockingFailureException) {
				throw e;
			}

      // 抛出错误
      if (e instanceof DuplicateKeyException) {
				throw e;
			}

			throw new DbActionExecutionException(action, e);
		}
	}
}
