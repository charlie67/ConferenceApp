package to.charlie.conferenceapp.model;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface SpeakerDao
{
	@Query("SELECT * FROM speakers where id = :id")
	Speaker findSpeakerById(String id);
}
