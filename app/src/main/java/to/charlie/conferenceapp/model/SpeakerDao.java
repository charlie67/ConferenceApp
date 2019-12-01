package to.charlie.conferenceapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SpeakerDao
{
	@Query("SELECT * FROM speakers where id = :id")
	LiveData<Speaker> findSpeakerById(String id);

	@Query("SELECT * FROM speakers")
	LiveData<List<Speaker>> getAllSpeakers();
}
