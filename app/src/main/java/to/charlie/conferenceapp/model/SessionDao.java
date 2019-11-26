package to.charlie.conferenceapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

import to.charlie.conferenceapp.model.util.SessionTypeConverter;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters({SessionTypeConverter.class})
public interface SessionDao
{
	@Query("SELECT * FROM sessions where id = :id")
	Session findSessionById(String id);

	@Update(onConflict = REPLACE)
	void updateSession(Session session);

	@Query("SELECT * FROM sessions")
	LiveData<List<Session>> getAllSessions();

	@Query("SELECT * FROM sessions where favourite = 0")
	LiveData<List<Session>> getAllFavouriteSessions();
}
