package to.charlie.conferenceapp.model;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.TypeConverters;

import to.charlie.conferenceapp.model.util.SessionTypeConverter;

@Dao
@TypeConverters({SessionTypeConverter.class})
public interface SessionDao
{
	@Query("SELECT * FROM sessions where id = :id")
	Session findSessionById(String id);
}
