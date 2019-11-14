package to.charlie.conferenceapp.model;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface LocationDao
{
	@Query("SELECT * FROM locations where id = :id")
	Location findLocationById(String id);
}
