package pl.kodujdlapolski.na4lapy.repository.database;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.BaseEntity;

public class DatabaseServiceImpl implements DatabaseService {

    private DatabaseHelper mDatabaseHelper;

    @Inject
    public DatabaseServiceImpl(DatabaseHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
    }

    @Override
    public <T> T findOneById(Long id, Class clazz) throws SQLException {
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        Dao<T, Long> dao = getDao(clazz, connectionSource);
        T entity = dao.queryForId(id);
        connectionSource.close();
        return entity;
    }

    @Override
    public <T> List<T> findAll(Class clazz) throws SQLException {
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        Dao<T, Long> dao = getDao(clazz, connectionSource);
        List<T> list = dao.queryForAll();
        connectionSource.close();
        return list;
    }

    @Override
    public <T1, T2> List<T1> findAllByForeignId(Long id, Class sourceClass, Class foreignClass) throws SQLException {
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        Dao<T1, Long> sourceDao = getDao(sourceClass, connectionSource);
        Dao<T2, Long> foreignDao = getDao(foreignClass, connectionSource);

        QueryBuilder<T2, Long> foreignQb = foreignDao.queryBuilder();
        foreignQb.where().eq(BaseEntity.COLUMN_NAME_ID, id);
        QueryBuilder<T1, Long> sourceQb = sourceDao.queryBuilder();
        List<T1> list = sourceQb.join(foreignQb).query();
        connectionSource.close();
        return list;
    }

    @Override
    public <T> int save(T entity) throws SQLException {
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        Dao<T, Long> dao = getDao(entity.getClass(), connectionSource);
        int count = dao.createOrUpdate(entity).getNumLinesChanged();
        connectionSource.close();
        return count;
    }

    @Override
    public <T> int saveAll(final List<T> entities) throws Exception {
        if (entities.isEmpty()) {
            return 0;
        }
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        final Dao<T, Long> dao = getDao(entities.get(0).getClass(), connectionSource);
        final int[] count = {0};
        dao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (T entity : entities) {
                    count[0] += dao.createOrUpdate(entity).getNumLinesChanged();
                }
                return null;
            }
        });
        connectionSource.close();
        return count[0];
    }

    private <T> Dao<T, Long> getDao(Class clazz, ConnectionSource connectionSource) throws SQLException {
        return DaoManager.createDao(connectionSource, clazz);
    }
}
