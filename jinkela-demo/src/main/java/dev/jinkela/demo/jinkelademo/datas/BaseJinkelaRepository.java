package dev.jinkela.demo.jinkelademo.datas;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import dev.jinkela.demo.jinkelademo.exceptions.DataConflictException;
import dev.jinkela.demo.jinkelademo.exceptions.DbSaveException;

@NoRepositoryBean
public interface BaseJinkelaRepository<T, ID>
        extends ListCrudRepository<T, ID>, PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {

    default <S extends T> S saveOrUpdate(S entity) {
        try {
            return save(entity);
        } catch (DbActionExecutionException e) {
            if (e.getCause() instanceof DuplicateKeyException) {
                throw (DuplicateKeyException) e.getCause();
            }
            throw e;
        }

    }

    default <S extends T> S saveOrUpdate2(S entity) {
        try {
            return save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof DuplicateKeyException) {
                throw new DataConflictException();
            } else {
                throw new DbSaveException();
            }
        }

    }
}
