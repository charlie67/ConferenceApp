package to.charlie.conferenceapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface LocationDao
{
	@Query("SELECT * FROM locations where id = :id")
	LiveData<Location> findLocationById(String id);
}
