package to.charlie.conferenceapp.ui.speakers;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.Speaker;
import to.charlie.conferenceapp.model.util.ResourceUtil;
import to.charlie.conferenceapp.ui.timetableList.NavigationType;

import static to.charlie.conferenceapp.ui.timetableList.TimetableListFragment.NAVIGATION_TYPE_BUNDLE_KEY;
import static to.charlie.conferenceapp.ui.timetableList.TimetableListFragment.SPEAKER_ID_BUNDLE_KEY;

/**
 * Recycler view to show the speaker information
 *
 * @author Charlie Robinson
 * @version 2/12/19
 */
public class SpeakersRecyclerWithListAdapter extends RecyclerView.Adapter<SpeakersRecyclerWithListAdapter.ViewHolder>
{
	private List<Speaker> dataSet;

	/**
	 * Provides a reference to the views for each data item. Caches as much as possible.
	 * Complex data items may need more than one view per item, and
	 * you provide access to all the views for a data item in a view holder
	 */
	class ViewHolder extends RecyclerView.ViewHolder
	{
		ImageView speakerImage;
		TextView speakerName;

		View itemView;

		AssetManager assetManger;

		ViewHolder(View itemView)
		{
			super(itemView);
			assetManger = itemView.getResources().getAssets();
			this.itemView = itemView;

			this.speakerImage = itemView.findViewById(R.id.speaker_list_speaker_image);
			this.speakerName = itemView.findViewById(R.id.speaker_list_speaker_name);
		}

		/**
		 * This is where the view is bound to data within the data
		 *
		 * @param speaker The current data item
		 */
		void bindDataSet(Speaker speaker)
		{
			speakerName.setText(speaker.getName());

			ResourceUtil.setImageOnImageViewAsync(speakerImage, "images/" + speaker.getId().trim() + ".jpg", assetManger);

			final NavController navController = Navigation.findNavController((FragmentActivity) itemView.getContext(), R.id.nav_host_fragment);


			Bundle navigationBundle = new Bundle();
			navigationBundle.putString(SPEAKER_ID_BUNDLE_KEY, speaker.getId());
			navigationBundle.putString(NAVIGATION_TYPE_BUNDLE_KEY, NavigationType.SPEAKER.toString());

			itemView.setOnClickListener(v -> navController.navigate(R.id.action_speakersFragment_to_timetableListFragment,
							navigationBundle));
		}
	}

	/**
	 * Called by the RecyclerView asking for a ViewHolder object
	 *
	 * @param parent   The parent view (e.g. GridLayout)
	 * @param viewType An id to identify the kind of view to inflate, e.g. where different
	 *                 kinds of view are displayed for different items in the list: by default 0
	 * @return The new ViewHolder object that will be cached by the RecyclerView
	 */
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		//create the new view
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_speaker_item,
						parent, false);
		return new ViewHolder(view);
	}

	/**
	 * Replace the contents of a view
	 *
	 * @param holder   The current view being displayed
	 * @param position The position of that view in the grid being displayed
	 */
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position)
	{
		// Delegate the binding to the ViewHolder
		if (dataSet != null)
		{
			holder.bindDataSet(dataSet.get(position));
		}
	}

	/**
	 * Return the size of your dataset (invoked by the layout manager)
	 *
	 * @return The size of the dataset. The layout manager needs to know.
	 */
	@Override
	public int getItemCount()
	{
		if (dataSet != null)
		{
			return dataSet.size();
		}
		else
		{
			return 0;
		}
	}

	/**
	 * If the data set is reset, then we need to let the adapter know.
	 *
	 * @param dataSet The updated dataSet
	 */
	void changeDataSet(List<Speaker> dataSet)
	{
		this.dataSet = dataSet;
		notifyDataSetChanged();
	}
}
