package to.charlie.conferenceapp.ui.sessions;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.Session;

public class SessionsRecyclerWithListAdapter extends RecyclerView.Adapter<SessionsRecyclerWithListAdapter.ViewHolder>
{
	private final Context context;
	private List<Session> dataSet;
	private View.OnClickListener clickListener;

	SessionsRecyclerWithListAdapter(Context context)
	{
		this.context = context;
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
	 * Allows clicks events to be caught
	 *
	 * @param itemClickListener The provided listener invoked when someone clicks on an item in the grid
	 */
	void setOnClickListener(View.OnClickListener itemClickListener)
	{
		this.clickListener = itemClickListener;
	}

	/**
	 * If the data set is reset, then we need to let the adapter know.
	 *
	 * @param dataSet The updated dataSet
	 */
	void changeDataSet(List<Session> dataSet)
	{
		this.dataSet = dataSet;
		notifyDataSetChanged();
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
		TextView sessionType;

		private String sessionId;

		Locale currentLocale;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);
			itemView.setOnClickListener(v -> Toast.makeText(itemView.getContext(), "Clicked session " + sessionId, Toast.LENGTH_SHORT).show());
			Resources resources = itemView.getResources();

			currentLocale = resources.getConfiguration().getLocales().get(0);
			this.sessionDay = itemView.findViewById(R.id.session_day_text_view);
			this.sessionTime = itemView.findViewById(R.id.session_time_text_view);
			this.sessionTitle = itemView.findViewById(R.id.session_title_text_view);
			this.sessionType = itemView.findViewById(R.id.session_type_text_view);

			//this sets the correct width of the text views so that the day time section occupies a
			// quarter of the width and the title occupies the remainder
			int screenWidthDp = resources.getConfiguration().screenWidthDp;
			//2 dp margin either side of the screen
			int screenWidthDpNoMargin = screenWidthDp - 2 - 2;

			int dateAreaDpWidth = (int) (screenWidthDpNoMargin * 0.25);
			int titleAreaDpWidth = (int) (screenWidthDpNoMargin * 0.75);

			int dateAreaPxWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dateAreaDpWidth, resources.getDisplayMetrics());

			int titleAreaPxWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, titleAreaDpWidth, resources.getDisplayMetrics());

			sessionDay.setWidth(dateAreaPxWidth);
			sessionTime.setWidth(dateAreaPxWidth);
			sessionTitle.setWidth(titleAreaPxWidth);
		}

		/**
		 * Where the view is bound to data within the session
		 *
		 * @param session The current data item
		 */
		void bindDataSet(Session session)
		{
			sessionId = session.getId();
			String dayOfWeek = LocalDate.parse(session.getSessionDate()).getDayOfWeek().getDisplayName(TextStyle.FULL, currentLocale);
			sessionDay.setText(dayOfWeek);
			sessionTime.setText(session.getTimeStart());
			sessionTitle.setText(session.getTitle());
			sessionType.setText(session.getSessionType().getTypeName());
		}
	}
}
