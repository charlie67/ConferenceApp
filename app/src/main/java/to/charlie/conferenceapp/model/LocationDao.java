package to.charlie.conferenceapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

/**
 * Location DAO. Responsible for CRUD operations for locations
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
@Dao
public interface LocationDao
{
	@Query("SELECT * FROM locations where id = :id")
	LiveData<Location> findLocationById(String id);
}
