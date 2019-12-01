package to.charlie.conferenceapp.ui.speakers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.SpeakerListViewModel;

public class SpeakersFragment extends Fragment
{
	private SpeakersRecyclerWithListAdapter speakersRecyclerAdapter;
	private static final int GRID_COLUMN_COUNT = 3;

	public SpeakersFragment()
	{
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_speakers, container, false);

		SpeakerListViewModel speakerListViewModel =
						ViewModelProviders.of(this).get(SpeakerListViewModel.class);
		speakersRecyclerAdapter = speakerListViewModel.getAdapter();

		if (speakersRecyclerAdapter == null)
		{
			speakersRecyclerAdapter = new SpeakersRecyclerWithListAdapter();
			speakerListViewModel.getSpeakerList().observe(this, speakers -> speakersRecyclerAdapter.changeDataSet(speakers));
			speakerListViewModel.setAdapter(speakersRecyclerAdapter);
		}

		RecyclerView listSpeakers = view.findViewById(R.id.speaker_list);
		listSpeakers.setLayoutManager(new GridLayoutManager(getContext(), GRID_COLUMN_COUNT));
		listSpeakers.setAdapter(speakersRecyclerAdapter);

		return view;
	}
}
