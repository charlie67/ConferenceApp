package to.charlie.conferenceapp.ui.sessionList;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.Session;

public class SessionsRecyclerWithListAdapter extends RecyclerView.Adapter<SessionsRecyclerWithListAdapter.ViewHolder>
{
	private List<Session> dataSet;
	private boolean favouritesList = false;
	private boolean speakersList = false;

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
		//create a new view for the recycler item
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_session_item, parent, false);
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
	public void changeDataSet(List<Session> dataSet)
	{
		this.dataSet = dataSet;
		notifyDataSetChanged();
	}

	/**
	 * Set that this recycler view is being used for the favourites list or the speakers list
	 *
	 * @param favouritesList true if this is being used to show the favourites list otherwise false
	 */
	public void setListDisplayType(boolean favouritesList, boolean speakersList)
	{
		this.favouritesList = favouritesList;
		this.speakersList = speakersList;
	}

	/**
	 * Provides a reference to the views for each data item. Caches as much as possible.
	 * Complex data items may need more than one view per item, and
	 * you provide access to all the views for a data item in a view holder
	 */
	class ViewHolder extends RecyclerView.ViewHolder
	{
		TextView sessionDay;
		TextView sessionTime;
		TextView sessionTitle;
		ImageView sessionTypeImage;

		View itemView;

		Locale currentLocale;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			this.itemView = itemView;

			Resources resources = itemView.getResources();

			currentLocale = resources.getConfiguration().getLocales().get(0);
			this.sessionDay = itemView.findViewById(R.id.session_day_text_view);
			this.sessionTime = itemView.findViewById(R.id.session_time_text_view);
			this.sessionTitle = itemView.findViewById(R.id.session_title_text_view);
			this.sessionTypeImage = itemView.findViewById(R.id.session_type_image_view);
		}

		/**
		 * Where the view is bound to data within the session
		 *
		 * @param session The current data item
		 */
		void bindDataSet(Session session)
		{
			final NavController navController = Navigation.findNavController((FragmentActivity) itemView.getContext(), R.id.nav_host_fragment);

			if (favouritesList)
			{
				itemView.setOnClickListener(v -> navController.navigate(R.id.action_favourites_fragment_to_session_item_view, session.toBundle()));
			}
			else if (speakersList)
			{
				itemView.setOnClickListener(v -> navController.navigate(R.id.action_speakerSessionListFragment_to_sessionItemFragment, session.toBundle()));
			}
			else
			{
				itemView.setOnClickListener(v -> navController.navigate(R.id.action_session_fragment_to_session_item_view, session.toBundle()));
			}

			String dayOfWeek = LocalDate.parse(session.getSessionDate()).getDayOfWeek().getDisplayName(TextStyle.FULL, currentLocale);
			sessionDay.setText(dayOfWeek);
			sessionTime.setText(session.getTimeStart());
			sessionTitle.setText(session.getTitle());

			//set the icon type for the session
			switch (session.getSessionType())
			{
				case TALK:
					sessionTypeImage.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),
									R.drawable.ic_chat_bubble_black_24dp));
					break;

				case LUNCH:
					sessionTypeImage.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),
									R.drawable.ic_restaurant_black_24dp));
					break;

				case COFFEE:
					sessionTypeImage.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),
									R.drawable.ic_weekend_black_24dp));
					break;

				case DINNER:
					sessionTypeImage.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),
									R.drawable.ic_room_service_black_24dp));
					break;

				case WORKSHOP:
					sessionTypeImage.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),
									R.drawable.ic_stay_current_portrait_black_24dp));
					break;

				case REGISTRATION:
					sessionTypeImage.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),
									R.drawable.ic_work_black_24dp));
					break;

			}
		}
	}
}
