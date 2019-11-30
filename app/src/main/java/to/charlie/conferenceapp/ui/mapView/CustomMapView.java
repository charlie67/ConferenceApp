package to.charlie.conferenceapp.ui.mapView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

import com.google.android.gms.maps.MapView;

public class CustomMapView extends MapView
{

	private ViewParent mViewParent;

	public CustomMapView(Context context)
	{
		super(context);
	}

	public CustomMapView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public CustomMapView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onInterceptTouchEvent(final MotionEvent event)
	{
		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				if (null == mViewParent)
				{
					getParent().requestDisallowInterceptTouchEvent(true);
				}
				else
				{
					mViewParent.requestDisallowInterceptTouchEvent(true);
				}
				break;
			case MotionEvent.ACTION_UP:
				if (null == mViewParent)
				{
					getParent().requestDisallowInterceptTouchEvent(false);
				}
				else
				{
					mViewParent.requestDisallowInterceptTouchEvent(false);
				}
				break;
			default:
				break;
		}

		return super.onInterceptTouchEvent(event);
	}
}