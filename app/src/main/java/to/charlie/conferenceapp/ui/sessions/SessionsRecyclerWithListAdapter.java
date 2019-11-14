package to.charlie.conferenceapp.ui.sessions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import to.charlie.conferenceapp.R;
import to.charlie.conferenceapp.model.Session;

public class SessionsRecyclerWithListAdapter extends RecyclerView.Adapter<SessionsRecyclerWithListAdapter.ViewHolder>
{
	private final Context context;
	private List<Session> dataSet;
	private View.OnClickListener clickListener;

	public SessionsRecyclerWithListAdapter(Context context)
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
		TextView sessionTitle;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);
			this.sessionTitle = itemView.findViewById(R.id.sessionTitleTextView);
		}

		/**
		 * Where the view is bound to data within the session
		 *
		 * @param session The current data item
		 */
		void bindDataSet(Session session)
		{
			sessionTitle.setText(session.getTitle());
		}
	}
}
